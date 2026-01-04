package com.memedream.classicmobs.entity;

import com.memedream.classicmobs.entity.ai.goals.FestiveCreeperAttackGoal;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.Ocelot;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class FestiveCreeperEntity extends Monster implements RangedAttackMob {

    public FestiveCreeperEntity(EntityType<? extends Monster> entityType, Level level) {
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

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MOVEMENT_SPEED, 0.25);
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
        tnt.setYRot((float) (Mth.atan2(movement.x, movement.z) * Mth.RAD_TO_DEG));
        tnt.setXRot((float) (Mth.atan2(movement.y, d0) * Mth.RAD_TO_DEG) + 180);
        tnt.setOldPosAndRot();
        this.level().addFreshEntity(tnt);
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.CREEPER_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.CREEPER_DEATH;
    }

    @Override
    public boolean ignoreExplosion(Explosion explosion) {
        if (explosion.getDirectSourceEntity() instanceof FestiveTntEntity tnt) {
            return tnt.getOwner() instanceof FestiveCreeperEntity;
        }
        return false;
    }
}
