package com.memedream.classicmobs.effect;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class FaeCurseEffect extends MobEffect {

    public FaeCurseEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(ServerLevel serverLevel, LivingEntity mob, int amplification) {
        //TODO: Allow player to see ores through walls by rendering them as if there were no blocks between you and that ore. Perhaps use particle effects instead? Or maybe a glowing effect.
        //TODO: Grant increasing weakness levels to player as they approach the ore, capping at weakness 4 when within reach of it.
        return super.applyEffectTick(serverLevel, mob, amplification);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }
}
