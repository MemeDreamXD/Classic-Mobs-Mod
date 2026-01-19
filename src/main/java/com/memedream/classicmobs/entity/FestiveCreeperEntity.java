package com.memedream.classicmobs.entity;

import com.memedream.classicmobs.entity.ai.goals.FestiveCreeperAttackGoal;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.feline.Cat;
import net.minecraft.world.entity.animal.feline.Ocelot;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class FestiveCreeperEntity extends Creeper implements RangedAttackMob {

    public FestiveCreeperEntity(EntityType<? extends Creeper> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, Ocelot.class, 6.0F, 1.0D, 1.2D));
        this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, Cat.class, 6.0F, 1.0D, 1.2D));
        this.goalSelector.addGoal(4, new FestiveCreeperAttackGoal(this, 1.0D, 40, 10.0F));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.8D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
    }

    @Override
    protected boolean isImmobile() {
        return super.isImmobile() || this.getSwellDir() == 1;
    }

    @Override
    public boolean causeFallDamage(double fallDistance, float damageModifier, DamageSource damageSource) {
        boolean damaged = super.causeFallDamage(fallDistance, damageModifier, damageSource);
        //cancel falling swell vanilla creepers have
        this.swell = 0;
        return damaged;
    }

    @Override
    public void performRangedAttack(LivingEntity target, float velocity) {
        FestiveTntEntity tnt = new FestiveTntEntity(this.level(), this);
        Vec3 movement = new Vec3(
            (target.getX() - tnt.getX()) / 18D,
            (target.getY() - tnt.getY()) / 18D + 0.5D,
            (target.getZ() - tnt.getZ()) / 18D);
        tnt.setDeltaMovement(movement);
        double d0 = movement.horizontalDistance();
        tnt.setXRot((float) (Mth.atan2(movement.x, movement.z) * Mth.RAD_TO_DEG));
        tnt.setYRot((float) (Mth.atan2(movement.y, d0) * Mth.RAD_TO_DEG));
        tnt.setOldPosAndRot();
        this.level().addFreshEntity(tnt);
    }

    @Override
    public void explodeCreeper() {
        if (this.level() instanceof ServerLevel level) {
            for (int i = 0; i < 10; i++) {
                FestiveTntEntity tnt = new FestiveTntEntity(level, this);
                Vec3 movement = new Vec3(this.getRandom().nextDouble() * 0.75D - 0.325D, 0.35D, this.getRandom().nextDouble() * 0.75D - 0.325D);
                tnt.setDeltaMovement(movement);
                double d0 = movement.horizontalDistance();
                tnt.setXRot((float) (Mth.atan2(movement.x, movement.z) * Mth.RAD_TO_DEG));
                tnt.setYRot((float) (Mth.atan2(movement.y, d0) * Mth.RAD_TO_DEG));
                tnt.setOldPosAndRot();
                tnt.maxOnGroundTime = 0;
                this.level().addFreshEntity(tnt);
            }
        }
        super.explodeCreeper();
    }

    @Override
    public boolean ignoreExplosion(Explosion explosion) {
        if (explosion.getDirectSourceEntity() instanceof FestiveTntEntity tnt) {
            return tnt.getOwner() instanceof FestiveCreeperEntity;
        }
        return false;
    }
}
