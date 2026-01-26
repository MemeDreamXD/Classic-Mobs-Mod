package com.memedream.classicmobs.entity;

import com.memedream.classicmobs.client.ClassicMobsClient;
import com.memedream.classicmobs.init.*;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public class BolaEntity extends ThrowableProjectile {

    public BolaEntity(EntityType<? extends ThrowableProjectile> type, Level level) {
        super(type, level);
    }

    public BolaEntity(Level level, LivingEntity shooter) {
        super(ModEntities.BOLA.get(), shooter.getX(), shooter.getEyeY() - 0.1F, shooter.getZ(), level);
        this.setOwner(shooter);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder entityData) {

    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    protected boolean canHitEntity(Entity entity) {
        return !entity.is(ModTags.Entities.BOLA_IMMUNE) && super.canHitEntity(entity);
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        if (this.level() instanceof ServerLevel level) {
            this.spawnAtLocation(level, ModItems.BOLA);
        }
        this.discard();
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        if (result.getEntity() instanceof LivingEntity entity && !entity.hasEffect(ModEffects.BOUND)) {
            entity.addEffect(new MobEffectInstance(ModEffects.BOUND, 120));
            this.playSound(ModSounds.BOLA_SNAG.get(), 5.0F, 1.0F);
            this.discard();
        }
    }

    @Override
    public void onAddedToLevel() {
        super.onAddedToLevel();
        if (this.level().isClientSide()) {
            ClassicMobsClient.attachBolaSound(this);
        }
    }
}
