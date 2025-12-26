package com.memedream.classicmobs.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.armadillo.Armadillo;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class AntlionEntity extends Monster {

    // Variables go here

    public AntlionEntity(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        // LOL I didn't know spiders did this
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Armadillo.class, 6.0F, 1.0, 1.2, p_320185_ -> !((Armadillo)p_320185_).isScared()));
        // Attack animation we want to use goes in place of leap
        //this.goalSelector.addGoal(3, new LeapAtTargetGoal(this, 0.4F));
        // TODO: Using MeleeAttackGoal for now, would like custom attack goal that makes him pinch his lil pinchers
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0f, false));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.8));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 12.0F).add(Attributes.ARMOR, 7F).add(Attributes.MOVEMENT_SPEED, 0.3F);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.SPIDER_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.SPIDER_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.SPIDER_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos p_33804_, BlockState p_33805_) {
        this.playSound(SoundEvents.SPIDER_STEP, 0.15F, 1.0F);
    }
}
