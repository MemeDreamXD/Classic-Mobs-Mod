package com.memedream.classicmobs.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties {
    // Nutrition is 1 per half hunger refilled
    // SaturationModifier is a ratio from 0 to 1
    public static final FoodProperties RAW_DODO = new FoodProperties.Builder().nutrition(4).saturationModifier(0.24f).effect(() -> new MobEffectInstance(MobEffects.HUNGER, 600), 0.3f).build();
    public static final FoodProperties COOKED_DODO = new FoodProperties.Builder().nutrition(10).saturationModifier(0.85f).effect(() -> new MobEffectInstance(MobEffects.SATURATION, 200), 1.0f).build();
    public static final FoodProperties ROTTEN_FLESH_BLOCK = new FoodProperties.Builder().nutrition(36).saturationModifier(0.1f).effect(() -> new MobEffectInstance(MobEffects.HUNGER, 600, 9), 0.8f).build();
    public static final FoodProperties RAW_BEEF_BLOCK = new FoodProperties.Builder().nutrition(27).saturationModifier(1.62f).build();
    public static final FoodProperties COOKED_BEEF_BLOCK = new FoodProperties.Builder().nutrition(64).saturationModifier(7.2f).build();
    public static final FoodProperties RAW_MUTTON_BLOCK = new FoodProperties.Builder().nutrition(18).saturationModifier(2.7F).build();
    public static final FoodProperties COOKED_MUTTON_BLOCK = new FoodProperties.Builder().nutrition(54).saturationModifier(7.2F).build();
    public static final FoodProperties RAW_PORK_BLOCK = new FoodProperties.Builder().nutrition(27).saturationModifier(2.7F).build();
    public static final FoodProperties COOKED_PORK_BLOCK = new FoodProperties.Builder().nutrition(64).saturationModifier(7.2f).build();
    public static final FoodProperties RAW_RABBIT_BLOCK = new FoodProperties.Builder().nutrition(27).saturationModifier(2.7F).build();
    public static final FoodProperties COOKED_RABBIT_BLOCK = new FoodProperties.Builder().nutrition(45).saturationModifier(5.4F).build();
    public static final FoodProperties RAW_CHICKEN_BLOCK = new FoodProperties.Builder().nutrition(18).saturationModifier(2.7F).effect(() -> new MobEffectInstance(MobEffects.HUNGER, 600, 9), 0.3F).build();
    public static final FoodProperties COOKED_CHICKEN_BLOCK = new FoodProperties.Builder().nutrition(54).saturationModifier(5.4F).build();
    public static final FoodProperties RAW_SALMON_BLOCK = new FoodProperties.Builder().nutrition(18).saturationModifier(0.9F).build();
    public static final FoodProperties COOKED_SALMON_BLOCK = new FoodProperties.Builder().nutrition(54).saturationModifier(7.2F).build();
    public static final FoodProperties RAW_COD_BLOCK = new FoodProperties.Builder().nutrition(18).saturationModifier(0.9F).build();
    public static final FoodProperties COOKED_COD_BLOCK = new FoodProperties.Builder().nutrition(45).saturationModifier(5.4F).build();
    public static final FoodProperties TROPICAL_FISH_BLOCK = new FoodProperties.Builder().nutrition(9).saturationModifier(0.9F).build();
    //TODO: Make pufferfish block instakill player with "(Player Name) was an adventurous eater" death message.
    public static final FoodProperties PUFFERFISH_BLOCK = new FoodProperties.Builder().nutrition(9).saturationModifier(0.9F).build();
}
