package com.memedream.classicmobs.entity;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.feline.Cat;
import net.minecraft.world.entity.animal.feline.Ocelot;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.SimpleExplosionDamageCalculator;
import net.minecraft.world.phys.AABB;
import net.neoforged.neoforge.common.CommonHooks;

import java.util.List;
import java.util.Optional;

public class SupportCreeperEntity extends Creeper {

    private static final ExplosionDamageCalculator VISUAL_EXPLOSION = new SimpleExplosionDamageCalculator(false, false, Optional.of(0.0F), Optional.empty());

    //TODO use random effect on spawn (using placeholder for now)
    private MobEffectInstance supportEffect = new MobEffectInstance(MobEffects.STRENGTH, 600);

    public SupportCreeperEntity(EntityType<? extends Creeper> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new SwellGoal(this));
        this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, Ocelot.class, 6.0F, 1.0D, 1.2D));
        this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, Cat.class, 6.0F, 1.0D, 1.2D));
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.8D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Mob.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Mob.class, true, (entity, _) -> entity instanceof Enemy && ((Mob)entity).getTarget() != null));
    }

    @Override
    public void explodeCreeper() {
        if (this.level() instanceof ServerLevel level) {
            float explosionMultiplier = this.isPowered() ? 2.0F : 1.0F;
            this.dead = true;

            //TODO custom particles
            this.level().explode(this, null, VISUAL_EXPLOSION, this.getX(), this.getY(), this.getZ(), this.explosionRadius * explosionMultiplier, false, Level.ExplosionInteraction.NONE, this.supportEffect.getParticleOptions(), this.supportEffect.getParticleOptions(), WeightedList.of(), SoundEvents.GENERIC_EXPLODE);

            float doubleRadius = explosionMultiplier * 2.0F;
            List<Entity> list = level.getEntities(this, new AABB(this.position().subtract(doubleRadius - 1.0D), this.position().add(doubleRadius + 1.0D)));

            for (Entity entity : list) {
                if (entity instanceof Mob mob && mob instanceof Enemy && mob.getTarget() != null) {
                    if (CommonHooks.canMobEffectBeApplied(mob, this.supportEffect, this)) {
                        mob.addEffect(this.supportEffect);
                    }
                }
            }

            this.discard();
        }
    }
}
