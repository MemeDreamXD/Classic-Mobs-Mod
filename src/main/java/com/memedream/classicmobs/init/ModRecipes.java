package com.memedream.classicmobs.init;

import com.memedream.classicmobs.ClassicMobs;
import com.memedream.classicmobs.item.recipe.MultitoolCombinationRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModRecipes {

    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, ClassicMobs.MOD_ID);
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(Registries.RECIPE_TYPE, ClassicMobs.MOD_ID);

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<MultitoolCombinationRecipe>> MULTITOOL_COMBINATION = RECIPE_SERIALIZERS.register("multitool", MultitoolCombinationRecipe.Serializer::new);

}
