package com.memedream.classicmobs.entity;

import com.memedream.classicmobs.entity.ai.goals.RocketCreeperSwellGoal;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.feline.Cat;
import net.minecraft.world.entity.animal.feline.Ocelot;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class RocketCreeperEntity extends Creeper {

    public static final EntityDataAccessor<Boolean> LAUNCHED = SynchedEntityData.defineId(RocketCreeperEntity.class, EntityDataSerializers.BOOLEAN);
    private float launchRot;
    private float launchRotO;

    public RocketCreeperEntity(EntityType<? extends Creeper> entityType, Level level) {
        super(entityType, level);
        this.maxSwell = 60;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(LAUNCHED, false);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new RocketCreeperSwellGoal(this));
        this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, Ocelot.class, 6.0F, 1.0D, 1.2D));
        this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, Cat.class, 6.0F, 1.0D, 1.2D));
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.8D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
    }

    @Override
    public void tick() {
        super.tick();
        this.launchRotO = launchRot;
        if (this.hasLaunched() && !this.onGround()) {
            float xRot = (float) (Mth.atan2(this.getDeltaMovement().y, this.getDeltaMovement().horizontalDistance()) * 180.0F / Mth.PI) - 90;
            this.launchRot = lerpRotation(this.launchRot, xRot);
        }

        if (this.level().isClientSide()) {
            if (this.getSwellDir() == 1) {
                this.level().addParticle(ParticleTypes.LARGE_SMOKE,
                    this.getX(),
                    this.getY() + 0.15F,
                    this.getZ(),
                    this.getRandom().nextGaussian() * 0.05D,
                    0.0D,
                    this.getRandom().nextGaussian() * 0.05D);
            }
            if (this.hasLaunched() && this.tickCount % 2 == 0) {
                this.level().addParticle(ParticleTypes.FIREWORK,
                    this.getX(),
                    this.getY(),
                    this.getZ(),
                    this.getRandom().nextGaussian() * 0.05D,
                    -this.getDeltaMovement().y * 0.5D,
                    this.getRandom().nextGaussian() * 0.05D);
            }
        }
    }

    public float getLaunchRot(float partialTicks) {
        return Mth.lerp(partialTicks, this.launchRotO, this.launchRot);
    }

    protected static float lerpRotation(float rotO, float rot) {
        while (rot - rotO < -180.0F) {
            rotO -= 360.0F;
        }

        while (rot - rotO >= 180.0F) {
            rotO += 360.0F;
        }

        return Mth.lerp(0.2F, rotO, rot);
    }

    public boolean hasLaunched() {
        return this.getEntityData().get(LAUNCHED);
    }

    @Override
    public void explodeCreeper() {
        if (!this.hasLaunched()) {
            this.playSound(SoundEvents.FIREWORK_ROCKET_LAUNCH, 1.0F, 0.5F);
            this.setSwellDir(-1);
            this.swell = 0;
            if (this.getTarget() != null) {
                this.setDeltaMovement(
                    (this.getTarget().getX() - this.getX()) / 6D,
                    1.5D,
                    (this.getTarget().getZ() - this.getZ()) / 6D);
            } else {
                this.setDeltaMovement(
                    this.getDeltaMovement().x(),
                    1.5D,
                    this.getDeltaMovement().z());
            }
            this.getEntityData().set(LAUNCHED, true);
        }
    }

    @Override
    public boolean causeFallDamage(double fallDistance, float damageModifier, DamageSource damageSource) {
        if (this.hasLaunched()) this.actuallyExplodeCreeper();
        return false;
    }

    public void actuallyExplodeCreeper() {
        super.explodeCreeper();
    }
}
