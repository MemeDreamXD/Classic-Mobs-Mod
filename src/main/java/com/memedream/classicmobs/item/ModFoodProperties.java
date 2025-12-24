package com.memedream.classicmobs.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties {
    public static final FoodProperties RAW_DODO = new FoodProperties.Builder().nutrition(4).saturationModifier(2.4f).effect(() -> new MobEffectInstance(MobEffects.HUNGER, 600), 0.3f).build();
    public static final FoodProperties COOKED_DODO = new FoodProperties.Builder().nutrition(10).saturationModifier(17.0f).effect(() -> new MobEffectInstance(MobEffects.SATURATION, 200), 1.0f).build();
}
