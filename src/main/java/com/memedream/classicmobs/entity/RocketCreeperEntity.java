package com.memedream.classicmobs.entity;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class RocketCreeperEntity extends Creeper {

    public static final EntityDataAccessor<Boolean> LAUNCHED = SynchedEntityData.defineId(RocketCreeperEntity.class, EntityDataSerializers.BOOLEAN);
    private float launchRot;
    private float launchRotO;

    public RocketCreeperEntity(EntityType<? extends Creeper> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MOVEMENT_SPEED, 0.25);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(LAUNCHED, false);
    }

    @Override
    public void tick() {
        super.tick();
        this.launchRotO = launchRot;
        if (this.hasLaunched() && !this.onGround()) {
            float xRot = (float)(Mth.atan2(this.getDeltaMovement().y, this.getDeltaMovement().horizontalDistance()) * 180.0F / Mth.PI) - 90;
            this.launchRot = lerpRotation(this.launchRot, xRot);
        }

        if (this.level().isClientSide() && this.hasLaunched() && this.tickCount % 2 == 0) {
            this.level().addParticle(ParticleTypes.FIREWORK,
                this.getX(),
                this.getY(),
                this.getZ(),
                this.getRandom().nextGaussian() * 0.05D,
                -this.getDeltaMovement().y * 0.5D,
                this.getRandom().nextGaussian() * 0.05D);
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
                    1.2D,
                    (this.getTarget().getZ() - this.getZ()) / 6D);
            } else {
                this.setDeltaMovement(
                    this.getDeltaMovement().x(),
                    1.2D,
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
