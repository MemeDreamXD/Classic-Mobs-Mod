package com.memedream.classicmobs.datagen.data.recipe;

import com.memedream.classicmobs.init.ModBlocks;
import com.memedream.classicmobs.init.ModItems;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.List;
import java.util.Optional;

public class CookingGen {

    public static void buildRecipes(HolderGetter<Item> getter, RecipeOutput output) {
        allCookingRecipesFor(getter, output, ModItems.RAW_DODO, ModItems.COOKED_DODO, 0.35F, 200);
        allCookingRecipesFor(getter, output, ModBlocks.RAW_BEEF_BLOCK, ModBlocks.COOKED_BEEF_BLOCK, 3.15F, 1800);
        allCookingRecipesFor(getter, output, ModBlocks.RAW_MUTTON_BLOCK, ModBlocks.COOKED_MUTTON_BLOCK, 3.15F, 1800);
        allCookingRecipesFor(getter, output, ModBlocks.RAW_PORK_BLOCK, ModBlocks.COOKED_PORK_BLOCK, 3.15F, 1800);
        allCookingRecipesFor(getter, output, ModBlocks.RAW_RABBIT_BLOCK, ModBlocks.COOKED_RABBIT_BLOCK, 3.15F, 1800);
        allCookingRecipesFor(getter, output, ModBlocks.RAW_CHICKEN_BLOCK, ModBlocks.COOKED_CHICKEN_BLOCK, 3.15F, 1800);
        allCookingRecipesFor(getter, output, ModBlocks.RAW_COD_BLOCK, ModBlocks.COOKED_COD_BLOCK, 3.15F, 1800);
        allCookingRecipesFor(getter, output, ModBlocks.RAW_SALMON_BLOCK, ModBlocks.COOKED_SALMON_BLOCK, 3.15F, 1800);
    }

    private static void allCookingRecipesFor(HolderGetter<Item> getter, RecipeOutput recipeOutput, ItemLike input, ItemLike output, float experience, int baseCookTime) {
        String smeltName = BuiltInRegistries.ITEM.getKey(input.asItem()).getPath();
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(input), RecipeCategory.FOOD, output, experience, baseCookTime).unlockedBy("has_meat", inventoryTrigger(ItemPredicate.Builder.item().of(getter, input))).save(recipeOutput, CraftingGen.createKey("smelting/" + smeltName));
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(input), RecipeCategory.FOOD, output, experience, baseCookTime / 2).unlockedBy("has_meat", inventoryTrigger(ItemPredicate.Builder.item().of(getter, input))).save(recipeOutput, CraftingGen.createKey("smoking/" + smeltName));
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(input), RecipeCategory.FOOD, output, experience, baseCookTime * 3).unlockedBy("has_meat", inventoryTrigger(ItemPredicate.Builder.item().of(getter, input))).save(recipeOutput, CraftingGen.createKey("campfiring/" + smeltName));
    }

    protected static Criterion<InventoryChangeTrigger.TriggerInstance> inventoryTrigger(ItemPredicate.Builder predicate) {
        return CriteriaTriggers.INVENTORY_CHANGED.createCriterion(new InventoryChangeTrigger.TriggerInstance(Optional.empty(), InventoryChangeTrigger.TriggerInstance.Slots.ANY, List.of(predicate.build())));
    }
}
