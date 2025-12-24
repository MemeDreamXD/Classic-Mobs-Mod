package com.memedream.classicmobs.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties {
    // Nutrition is 1 per half hunger refilled
    // SaturationModifier is a ratio from 0 to 1
    public static final FoodProperties RAW_DODO = new FoodProperties.Builder().nutrition(4).saturationModifier(0.24f).effect(() -> new MobEffectInstance(MobEffects.HUNGER, 600), 0.3f).build();
    public static final FoodProperties COOKED_DODO = new FoodProperties.Builder().nutrition(10).saturationModifier(0.85f).effect(() -> new MobEffectInstance(MobEffects.SATURATION, 200), 1.0f).build();
    public static final FoodProperties ROTTEN_FLESH_BLOCK = new FoodProperties.Builder().nutrition(36).saturationModifier(0.72f).effect(() -> new MobEffectInstance(MobEffects.HUNGER, 600, 7), 1.0f).build();
    public static final FoodProperties RAW_BEEF_BLOCK = new FoodProperties.Builder().nutrition(27).saturationModifier(1.62f).build();
    public static final FoodProperties COOKED_BEEF_BLOCK = new FoodProperties.Builder().nutrition(72).saturationModifier(5.76f).build();
}
