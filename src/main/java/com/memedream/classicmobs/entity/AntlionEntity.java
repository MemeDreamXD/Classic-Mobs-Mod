package com.memedream.classicmobs.entity;

import com.memedream.classicmobs.entity.ai.goals.AntlionAttackGoal;
import com.memedream.classicmobs.entity.ai.goals.AntlionBurrowGoal;
import com.memedream.classicmobs.entity.ai.goals.AntlionFleeSunGoal;
import com.memedream.classicmobs.init.ModEntityDataSerializers;
import com.memedream.classicmobs.init.ModSounds;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.armadillo.Armadillo;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;

import java.util.Locale;
import java.util.function.IntFunction;

public class AntlionEntity extends Monster {

    public static final EntityDataAccessor<AntlionState> STATE = SynchedEntityData.defineId(AntlionEntity.class, ModEntityDataSerializers.ANTLION_STATE.get());

    private static final EntityDimensions HUNTING_DIMENSIONS = EntityDimensions.scalable(0.7F, 0.7F);
    private static final EntityDimensions ATTACKING_DIMENSIONS = EntityDimensions.scalable(0.8F, 1.2F);

    public int huntTimer;
    public int digTimer;
    public int emergeTimer;
    public int attackTimer;

    public int idleTimeInSand;
    public int buryCooldown;

    public AntlionEntity(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
        this.lookControl = new AntlionLookControl(this);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(STATE, AntlionState.NONE);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new AntlionFleeSunGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new AntlionBurrowGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, Armadillo.class, 6.0F, 1.0D, 1.2D, entity -> !((Armadillo) entity).isScared()));
        this.goalSelector.addGoal(4, new AntlionAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.8D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true, (_, _) -> AntlionEntity.this.canMove()));
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> accessor) {
        if (STATE.equals(accessor)) {
            this.refreshDimensions();
            this.huntTimer = 0;
            this.digTimer = 0;
            this.attackTimer = 0;
            this.emergeTimer = 0;
        }
        super.onSyncedDataUpdated(accessor);
    }

    @Override
    protected EntityDimensions getDefaultDimensions(Pose pose) {
        if (this.isHunting()) {
            return HUNTING_DIMENSIONS;
        } else if (this.isAttacking()) {
            return ATTACKING_DIMENSIONS;
        }
        return super.getDefaultDimensions(pose);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 12.0F).add(Attributes.ARMOR, 7F).add(Attributes.MOVEMENT_SPEED, 0.3F);
    }

    public AntlionState getState() {
        return this.getEntityData().get(STATE);
    }

    public boolean isDigging() {
        return this.getState() == AntlionState.DIGGING;
    }

    public boolean isEmerging() {
        return this.getState() == AntlionState.EMERGING;
    }

    public boolean isHunting() {
        return this.getState() == AntlionState.HUNTING;
    }

    public boolean isAttacking() {
        return this.getState() == AntlionState.ATTACKING;
    }

    public boolean isPanicDigging() {
        return this.getState() == AntlionState.PANIC_DIGGING;
    }

    public void setStateTo(AntlionState state) {
        this.getEntityData().set(STATE, state);
    }

    public boolean canMove() {
        return this.getState() == AntlionState.NONE;
    }

    @Override
    protected boolean isImmobile() {
        return !this.canMove() || super.isImmobile();
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.store("state", AntlionState.CODEC, this.getState());
        output.putInt("dig_timer", this.digTimer);
        output.putInt("hunt_timer", this.huntTimer);
        output.putInt("emerge_timer", this.emergeTimer);
        output.putInt("attack_timer", this.attackTimer);
        output.putInt("bury_cooldown", this.buryCooldown);
        output.putInt("idle_sand_time", this.idleTimeInSand);
    }

    @Override
    protected void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);
        this.setStateTo(input.read("state", AntlionState.CODEC).orElse(AntlionState.NONE));
        this.digTimer = input.getIntOr("dig_timer", 0);
        this.huntTimer = input.getIntOr("hunt_timer", 0);
        this.emergeTimer = input.getIntOr("emerge_timer", 0);
        this.attackTimer = input.getIntOr("attack_timer", 0);
        this.buryCooldown = input.getIntOr("bury_cooldown", 0);
        this.idleTimeInSand = input.getIntOr("idle_sand_time", 0);

    }

    @Override
    public void tick() {
        super.tick();
        if (this.isPanicDigging() || this.isDigging()) {
            this.digTimer++;
            if (this.level().isClientSide()) {
                this.spawnSandDiggingParticles(0.7F);
            } else {
                if (this.digTimer >= 30) {
                    if (this.isPanicDigging()) {
                        this.discard();
                    } else {
                        this.setStateTo(AntlionState.HUNTING);
                        this.idleTimeInSand = 600 + this.getRandom().nextInt(300);
                        this.goalSelector.getAvailableGoals().forEach(WrappedGoal::stop);
                    }
                }
            }
        } else if (this.isHunting()) {
            if (this.level().isClientSide()) {
                if (this.huntTimer < 40) {
                    this.huntTimer++;
                    if (this.huntTimer > 15) {
                        this.spawnSandDiggingParticles(0.5F);
                    }
                }
            } else {
                if (this.idleTimeInSand-- <= 0) {
                    this.setStateTo(AntlionState.EMERGING);
                }
            }
        } else if (this.isAttacking()) {
            this.attackTimer++;
            if (!this.level().isClientSide()) {
                if (!this.getPassengers().isEmpty()) {
                    Entity victim = this.getPassengers().getFirst();
                    if (this.attackTimer >= 70) {
                        victim.stopRiding();
                        victim.setDeltaMovement(this.getRandom().nextFloat() * 0.75F, 0.1F, this.getRandom().nextFloat() * 0.75F);
                        if (victim instanceof ServerPlayer player) {
                            player.connection.send(new ClientboundSetEntityMotionPacket(player));
                        }
                        victim.needsSync = true;
                        this.setStateTo(AntlionState.EMERGING);
                    } else if (this.attackTimer % 20 == 0) {
                        this.doHurtTarget((ServerLevel) this.level(), victim);
                    }
                } else {
                    this.setStateTo(AntlionState.EMERGING);
                }
            }
        } else if (this.isEmerging()) {
            this.emergeTimer++;
            if (this.level().isClientSide()) {
                this.spawnSandDiggingParticles(0.7F);
            } else {
                this.buryCooldown = 1000;
                if (this.emergeTimer >= 30) {
                    this.setStateTo(AntlionState.NONE);
                }
            }
        }

        if (!this.level().isClientSide()) {
            if (this.buryCooldown > 0) {
                this.buryCooldown--;
            }
        }
    }

    @Override
    public boolean hurtServer(ServerLevel level, DamageSource source, float damage) {
        boolean hurt = super.hurtServer(level, source, damage);
        if (hurt && this.isHunting()) {
            this.setStateTo(AntlionState.EMERGING);
        }
        return hurt;
    }

    @Override
    public void knockback(double power, double xd, double zd) {
        if (this.canMove()) {
            super.knockback(power, xd, zd);
        }
    }

    @Override
    protected void doPush(Entity entity) {
        if (entity instanceof LivingEntity living && EntitySelector.NO_CREATIVE_OR_SPECTATOR.test(living) && !(entity instanceof AntlionEntity) && this.isHunting()) {
            living.startRiding(this, true, true);
            this.setStateTo(AntlionState.ATTACKING);
        }
        super.doPush(entity);
    }

    @Override
    public boolean canRiderInteract() {
        return true;
    }

    @Override
    public Vec3 getDismountLocationForPassenger(LivingEntity passenger) {
        return super.getDismountLocationForPassenger(passenger);
    }

    @Override
    public boolean shouldRiderSit() {
        return false;
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(Entity passenger, EntityDimensions dimensions, float scale) {
        return passenger instanceof Player ? super.getPassengerAttachmentPoint(passenger, dimensions, scale) : new Vec3(0.0D, 0.25D, 0.0D);
    }

    @Override
    public boolean isPushable() {
        return this.canMove() && super.isPushable();
    }

    private void spawnSandDiggingParticles(float range) {
        RandomSource random = this.getRandom();
        BlockState stateBelow = this.getBlockStateOn();
        if (stateBelow.getRenderShape() != RenderShape.INVISIBLE) {
            for (int i = 0; i < 30; i++) {
                double xx = this.getX() + Mth.randomBetween(random, -range, range);
                double yy = this.getY();
                double zz = this.getZ() + Mth.randomBetween(random, -range, range);
                this.level().addParticle(new BlockParticleOption(ParticleTypes.BLOCK, stateBelow), xx, yy, zz, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.ANTLION_IDLE.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return ModSounds.ANTLION_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.ANTLION_DEATH.get();
    }

    @Override
    protected void playStepSound(BlockPos p_33804_, BlockState p_33805_) {
        this.playSound(SoundEvents.SPIDER_STEP, 0.075F, 0.8F);
    }

    public enum AntlionState implements StringRepresentable {
        NONE, //normal state
        DIGGING, //burying itself getting ready to hunt
        EMERGING, //emerging from the sand after attacking or getting hit
        HUNTING, //submerged in sand, ready to attack
        ATTACKING, //has an entity in its grasp
        PANIC_DIGGING; //digging after panicking during the day

        public static final StringRepresentable.EnumCodec<AntlionState> CODEC = StringRepresentable.fromEnum(AntlionState::values);
        public static final IntFunction<AntlionState> BY_ID = ByIdMap.continuous(AntlionState::ordinal, values(), ByIdMap.OutOfBoundsStrategy.ZERO);
        public static final StreamCodec<ByteBuf, AntlionState> STREAM_CODEC = ByteBufCodecs.idMapper(BY_ID, AntlionState::ordinal);

        @Override
        public String getSerializedName() {
            return this.name().toLowerCase(Locale.ROOT);
        }
    }

    public static class AntlionLookControl extends LookControl {
        public AntlionLookControl(AntlionEntity antlion) {
            super(antlion);
        }

        @Override
        public void setLookAt(double x, double y, double z, float yMaxRotSpeed, float xMaxRotAngle) {
            if (((AntlionEntity)this.mob).canMove()) {
                super.setLookAt(x, y, z, yMaxRotSpeed, xMaxRotAngle);
            }
        }
    }
}
