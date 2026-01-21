package com.memedream.classicmobs.datagen.builder;

import com.memedream.classicmobs.item.recipe.MultitoolCombinationRecipe;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.criterion.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;

import java.util.Optional;

public class SmithingMultitoolRecipeBuilder {

    private final Ingredient template;
    private final Ingredient base;
    private final Ingredient addition;
    private final RecipeCategory category;
    private final Item result;

    public SmithingMultitoolRecipeBuilder(Ingredient template, Ingredient base, Ingredient addition, RecipeCategory category, Item result) {
        this.category = category;
        this.template = template;
        this.base = base;
        this.addition = addition;
        this.result = result;
    }

    public static SmithingMultitoolRecipeBuilder smithing(Ingredient template, Ingredient base, Ingredient addition, RecipeCategory category, Item result) {
        return new SmithingMultitoolRecipeBuilder(template, base, addition, category, result);
    }

    public void save(RecipeOutput output, ResourceKey<Recipe<?>> id) {
        Advancement.Builder advancement = output.advancement()
            .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id))
            .rewards(AdvancementRewards.Builder.recipe(id))
            .requirements(AdvancementRequirements.Strategy.OR);
        MultitoolCombinationRecipe recipe = new MultitoolCombinationRecipe(Optional.of(this.template), this.base, this.addition, new ItemStackTemplate(this.result));
        output.accept(id, recipe, advancement.build(id.identifier().withPrefix("recipes/" + this.category.getFolderName() + "/")));
    }
}
