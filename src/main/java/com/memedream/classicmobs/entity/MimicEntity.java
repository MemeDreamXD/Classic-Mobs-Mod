package com.memedream.classicmobs.entity;

import com.memedream.classicmobs.entity.ai.goals.MimicAttackGoal;
import com.memedream.classicmobs.entity.ai.goals.MimicFloatGoal;
import com.memedream.classicmobs.entity.ai.goals.MimicHopGoal;
import com.memedream.classicmobs.entity.ai.movement.MimicMoveControl;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.fluids.FluidType;
import org.jspecify.annotations.Nullable;

public class MimicEntity extends Mob implements Enemy {

    public int ticksInAir;
    public int attackCooldown;
    @Nullable
    public Direction facing;
    private int tickRate = 1;

    public MimicEntity(EntityType<? extends Mob> type, Level level) {
        super(type, level);
        this.moveControl = new MimicMoveControl(this);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
            .add(Attributes.MAX_HEALTH, 30.0D)
            .add(Attributes.MOVEMENT_SPEED, 0.8D)
            .add(Attributes.ATTACK_DAMAGE, 4.0D)
            .add(Attributes.KNOCKBACK_RESISTANCE, 1.0D)
            .add(Attributes.JUMP_STRENGTH, 0.45D)
            .add(Attributes.BURNING_TIME, 1.5D)
            .add(Attributes.STEP_HEIGHT, 1.0D)
            .add(Attributes.EXPLOSION_KNOCKBACK_RESISTANCE, 1.0D);
    }

    @Override
    public MimicMoveControl getMoveControl() {
        return (MimicMoveControl) this.moveControl;
    }

    public void setFacing(@Nullable Direction facing) {
        this.facing = facing;
        if (facing != null) {
            this.getMoveControl().setDirection(facing.toYRot(), false);
        }
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new MimicFloatGoal(this));
        this.goalSelector.addGoal(1, new MimicAttackGoal(this));
        this.goalSelector.addGoal(2, new MimicHopGoal(this));
        this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, Player.class, true) {
            @Override
            protected double getFollowDistance() {
                return super.getFollowDistance() / 2.5D;
            }
        });
    }

    @Override
    public boolean canBeCollidedWith(@Nullable Entity other) {
        return true;
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.putInt("ticks_in_air", this.ticksInAir);
        if (this.facing != null) {
            output.putString("facing", this.facing.name());
        }
    }

    @Override
    protected void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);
        this.ticksInAir = input.getIntOr("ticks_in_air", 0);
        String facing = input.getString("facing").orElse(null);
        this.setFacing(facing == null ? null : Direction.byName(facing.toLowerCase()));
    }

    @Override
    public void tick() {
        if (this.tickCount % 20 == 0) {
            if (this.getTarget() != null || this.level().isClientSide()
                || !this.onGround() || this.isDeadOrDying()
                || this.isPassenger() || !this.getPassengers().isEmpty()
                || this.level().getNearestPlayer(this, 32) != null) {
                this.tickRate = 1;
            } else {
                this.tickRate = 20;
            }
        }

        if (this.tickCount % this.tickRate != 0) {
            return;
        }
        super.tick();

        if (this.isInWater()) {
            this.ticksInAir = 0;
        } else if (!this.onGround()) {
            this.ticksInAir++;
        } else {
            if (this.ticksInAir > 0) {
                this.playSound(SoundEvents.CHEST_CLOSE, 1.0F, this.getVoicePitch());
                this.ticksInAir = 0;
            }
        }

        if (!this.level().isClientSide() && this.onGround() && this.getTarget() == null) {
            this.moveOrInterpolateTo(Vec3.atBottomCenterOf(this.blockPosition()));
            this.setFacing(this.getNearestViewDirection());
        }

        if (this.attackCooldown > 0) {
            this.attackCooldown--;
        }
    }

    @Override
    public boolean hurtServer(ServerLevel level, DamageSource source, float amount) {
        if (source.getEntity() instanceof Player player) {
            this.setTarget(player);
        }

        if (this.ticksInAir <= 0 && (source.getWeaponItem() == null || !source.getWeaponItem().is(ItemTags.AXES))) amount = amount / 2;

        if (this.onGround() && this.getRandom().nextBoolean()) {
            this.getMoveControl().setDirection(this.getRandom().nextInt(4) * 90, true);
        }

        return super.hurtServer(level, source, amount);
    }

    @Override
    public void playerTouch(Player player) {
        if (this.level() instanceof ServerLevel level) {
            if (this.attackCooldown <= 0
                && player.level().getDifficulty() != Difficulty.PEACEFUL
                && this.isAlive()
                && this.isWithinMeleeAttackRange(player)
                && this.hasLineOfSight(player)
            ) {
                this.attackCooldown = 20;
                DamageSource damageSource = this.damageSources().mobAttack(this);
                if (player.hurtServer(level, damageSource, (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE))) {
                    EnchantmentHelper.doPostAttackEffects(level, player, damageSource);
                }
            }
        }
    }

    @Override
    public float getSecondsToDisableBlocking() {
        return 5.0F;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.WOOD_BREAK;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ZOMBIE_BREAK_WOODEN_DOOR;
    }

    @Override
    public boolean isInvisible() {
        return true;
    }

    @Override
    public boolean isPushedByFluid(FluidType type) {
        return this.getTarget() != null && super.isPushedByFluid(type);
    }

    @Override
    protected int decreaseAirSupply(int i) {
        return this.getTarget() != null ? i : super.decreaseAirSupply(i);
    }

    @Override
    public SoundSource getSoundSource() {
        return SoundSource.HOSTILE;
    }

    @Override
    public boolean removeWhenFarAway(double distSqr) {
        return false;
    }
}
