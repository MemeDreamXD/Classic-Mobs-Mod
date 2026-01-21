package com.memedream.classicmobs.item.recipe;

import com.memedream.classicmobs.init.ModRecipes;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;
import net.minecraft.world.item.crafting.display.SmithingRecipeDisplay;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class MultitoolCombinationRecipe implements SmithingRecipe {

    private final Optional<Ingredient> template;
    private final Ingredient base;
    private final Ingredient addition;
    private final ItemStackTemplate result;
    private @Nullable PlacementInfo placementInfo;

    public MultitoolCombinationRecipe(Optional<Ingredient> template, Ingredient base, Ingredient addition, ItemStackTemplate result) {
        this.template = template;
        this.base = base;
        this.addition = addition;
        this.result = result;
    }

    @Override
    public ItemStack assemble(SmithingRecipeInput input) {
        ItemStack copy = input.base().copy();
        ItemEnchantments.Mutable enchants = new ItemEnchantments.Mutable(copy.getOrDefault(DataComponents.ENCHANTMENTS, ItemEnchantments.EMPTY));
        if (input.addition().has(DataComponents.ENCHANTMENTS)) {
            input.addition().get(DataComponents.ENCHANTMENTS).entrySet().forEach(enchantment -> {
                if (enchants.getLevel(enchantment.getKey()) < enchantment.getIntValue()) {
                    enchants.set(enchantment.getKey(), enchantment.getIntValue());
                }
            });
        }
        enchants.removeIf(holder -> !input.addition().supportsEnchantment(holder));
        copy.set(DataComponents.ENCHANTMENTS, enchants.toImmutable());

        return TransmuteRecipe.createWithOriginalComponents(this.result, copy);
    }

    @Override
    public Optional<Ingredient> templateIngredient() {
        return this.template;
    }

    @Override
    public Ingredient baseIngredient() {
        return this.base;
    }

    @Override
    public Optional<Ingredient> additionIngredient() {
        return Optional.of(this.addition);
    }

    @Override
    public RecipeSerializer<MultitoolCombinationRecipe> getSerializer() {
        return ModRecipes.MULTITOOL_COMBINATION.get();
    }

    @Override
    public PlacementInfo placementInfo() {
        if (this.placementInfo == null) {
            this.placementInfo = PlacementInfo.createFromOptionals(List.of(this.template, Optional.of(this.base), Optional.of(this.addition)));
        }

        return this.placementInfo;
    }

    @Override
    public List<RecipeDisplay> display() {
        return List.of(
            new SmithingRecipeDisplay(
                Ingredient.optionalIngredientToDisplay(this.template),
                this.base.display(),
                this.addition.display(),
                new SlotDisplay.ItemStackSlotDisplay(this.result),
                new SlotDisplay.ItemSlotDisplay(Items.SMITHING_TABLE)
            )
        );
    }

    public static class Serializer implements RecipeSerializer<MultitoolCombinationRecipe> {
        private static final MapCodec<MultitoolCombinationRecipe> CODEC = RecordCodecBuilder.mapCodec(
            r -> r.group(
                    Ingredient.CODEC.optionalFieldOf("template").forGetter(o -> o.template),
                    Ingredient.CODEC.fieldOf("base").forGetter(o -> o.base),
                    Ingredient.CODEC.fieldOf("addition").forGetter(o -> o.addition),
                    ItemStackTemplate.CODEC.fieldOf("result").forGetter(o -> o.result)
                )
                .apply(r, MultitoolCombinationRecipe::new)
        );
        public static final StreamCodec<RegistryFriendlyByteBuf, MultitoolCombinationRecipe> STREAM_CODEC = StreamCodec.composite(
            Ingredient.OPTIONAL_CONTENTS_STREAM_CODEC,
            r -> r.template,
            Ingredient.CONTENTS_STREAM_CODEC,
            r -> r.base,
            Ingredient.CONTENTS_STREAM_CODEC,
            r -> r.addition,
            ItemStackTemplate.STREAM_CODEC,
            r -> r.result,
            MultitoolCombinationRecipe::new
        );

        @Override
        public MapCodec<MultitoolCombinationRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, MultitoolCombinationRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
