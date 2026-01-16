package com.memedream.classicmobs.entity;

import com.memedream.classicmobs.init.ModEntities;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.neoforged.neoforge.event.EventHooks;

public class FestiveTntEntity extends ThrowableProjectile {

    private static final EntityDataAccessor<Integer> FUSE_TIME = SynchedEntityData.defineId(FestiveTntEntity.class, EntityDataSerializers.INT);
    private int timeOnGround;
    private static final float ROTATION_FACTOR = 10.0F;

    public FestiveTntEntity(EntityType<? extends ThrowableProjectile> type, Level level) {
        super(type, level);
    }

    public FestiveTntEntity(Level level, LivingEntity shooter) {
        super(ModEntities.FESTIVE_TNT.get(), shooter.getX(), shooter.getEyeY() - 0.1F, shooter.getZ(), level);
        this.setOwner(shooter);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(FUSE_TIME, 80);
    }

    @Override
    public void tick() {
        this.applyGravity();
        this.applyEffectsFromBlocks();
        this.move(MoverType.SELF, this.getDeltaMovement());
        this.setDeltaMovement(this.getDeltaMovement().scale(0.98));
        if (this.onGround()) {
            this.setDeltaMovement(this.getDeltaMovement().multiply(0.85D, -0.5D, 0.85D));
            this.timeOnGround++;
            this.setXRot(Math.round(this.getYRot() / 90.0F) * 90.0F);
        } else {
            this.timeOnGround = 0;
            //this.setYRot(this.getYRot() + ROTATION_FACTOR);
            this.setXRot(this.getXRot() + ROTATION_FACTOR);
        }

        int i = this.getFuse() - 1;
        this.setFuse(i);
        if (i <= 0 || this.timeOnGround > 10) {
            this.explode();
        } else {
            this.updateInWaterStateAndDoFluidPushing();
            if (this.level().isClientSide()) {
                this.level().addParticle(ParticleTypes.SMOKE, this.getX(), this.getY() + 0.25D, this.getZ(), 0.0, 0.0, 0.0);
            }
        }

        HitResult result = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);

        if (result.getType() != HitResult.Type.MISS && this.isAlive() && !EventHooks.onProjectileImpact(this, result)) {
            this.hitTargetOrDeflectSelf(result);
            this.needsSync = true;
        }
    }

    //vanilla clamps xRot to be between -90 and 90 for some reason.
    //we dont want that here as the TNT should be free to rotate as it pleases
    @Override
    public void setXRot(float xRot) {
        this.xRot = xRot;
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        this.explode();
    }

    private void explode() {
        this.discard();
        if (!this.level().isClientSide()) {
            this.level().explode(
                this,
                this.getX(),
                this.getY(0.0625),
                this.getZ(),
                2.0F,
                false,
                Level.ExplosionInteraction.NONE
            );
        }
    }

    @Override
    protected void readAdditionalSaveData(ValueInput input) {
        this.setFuse(input.getShortOr("fuse", (short) 80));
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput output) {
        output.putShort("fuse", (short) this.getFuse());
    }

    @Override
    protected Entity.MovementEmission getMovementEmission() {
        return Entity.MovementEmission.NONE;
    }

    public void setFuse(int life) {
        this.getEntityData().set(FUSE_TIME, life);
    }

    public int getFuse() {
        return this.getEntityData().get(FUSE_TIME);
    }

    @Override
    public boolean ignoreExplosion(Explosion explosion) {
        return true;
    }
}
