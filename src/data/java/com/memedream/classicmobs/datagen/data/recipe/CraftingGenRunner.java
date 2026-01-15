package com.memedream.classicmobs.datagen.data.recipe;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;

import java.util.concurrent.CompletableFuture;

public class CraftingGenRunner extends RecipeProvider.Runner {

    public CraftingGenRunner(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput output) {
        return new CraftingGen(provider, output);
    }

    @Override
    public String getName() {
        return "Classic Mobs: Recipes";
    }
}
