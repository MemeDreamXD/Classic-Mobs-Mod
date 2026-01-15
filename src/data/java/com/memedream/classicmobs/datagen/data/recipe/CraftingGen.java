package com.memedream.classicmobs.datagen.data.recipe;

import com.memedream.classicmobs.ClassicMobs;
import com.memedream.classicmobs.datagen.builder.SmithingMultitoolRecipeBuilder;
import com.memedream.classicmobs.init.ModBlocks;
import com.memedream.classicmobs.init.ModItems;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.SmithingTransformRecipe;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.Tags;

public class CraftingGen extends RecipeProvider {

    protected CraftingGen(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    @Override
    protected void buildRecipes() {
        HolderGetter<Item> getter = this.registries.lookupOrThrow(Registries.ITEM);
        CookingGen.buildRecipes(getter, this.output);

        createStorageBlock(getter, this.output, RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLOCK_OF_BONES, Items.BONE);
        createStorageBlock(getter, this.output, RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLAZE_ROD_BLOCK, Items.BLAZE_ROD);
        createStorageBlock(getter, this.output, RecipeCategory.BUILDING_BLOCKS, ModBlocks.BREEZE_ROD_BLOCK, Items.BREEZE_ROD);
        createStorageBlock(getter, this.output, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHITIN_BLOCK, ModItems.CHITIN);
        createStorageBlock(getter, this.output, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ENDER_PEARL_BLOCK, Items.ENDER_PEARL);
        createStorageBlock(getter, this.output, RecipeCategory.BUILDING_BLOCKS, ModBlocks.FERMENTED_SPIDER_EYE_BLOCK, Items.FERMENTED_SPIDER_EYE);
        createStorageBlock(getter, this.output, RecipeCategory.BUILDING_BLOCKS, ModBlocks.GUNPOWDER_BLOCK, Items.GUNPOWDER);
        createStorageBlock(getter, this.output, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LEATHER_BLOCK, Items.LEATHER);
        createStorageBlock(getter, this.output, RecipeCategory.BUILDING_BLOCKS, ModBlocks.MAGMA_CREAM_BLOCK, Items.MAGMA_CREAM);
        createStorageBlock(getter, this.output, RecipeCategory.BUILDING_BLOCKS, ModBlocks.PHANTOM_MEMBRANE_BLOCK, Items.PHANTOM_MEMBRANE);
        createStorageBlock(getter, this.output, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SPIDER_EYE_BLOCK, Items.SPIDER_EYE);
        createStorageBlock(getter, this.output, RecipeCategory.BUILDING_BLOCKS, ModBlocks.STRING_BLOCK, Items.STRING);

        createStorageBlock(getter, this.output, RecipeCategory.FOOD, ModBlocks.RAW_BEEF_BLOCK, Items.BEEF);
        createStorageBlock(getter, this.output, RecipeCategory.FOOD, ModBlocks.COOKED_BEEF_BLOCK, Items.COOKED_BEEF);
        createStorageBlock(getter, this.output, RecipeCategory.FOOD, ModBlocks.RAW_MUTTON_BLOCK, Items.MUTTON);
        createStorageBlock(getter, this.output, RecipeCategory.FOOD, ModBlocks.COOKED_MUTTON_BLOCK, Items.COOKED_MUTTON);
        createStorageBlock(getter, this.output, RecipeCategory.FOOD, ModBlocks.RAW_PORK_BLOCK, Items.PORKCHOP);
        createStorageBlock(getter, this.output, RecipeCategory.FOOD, ModBlocks.COOKED_PORK_BLOCK, Items.COOKED_PORKCHOP);
        createStorageBlock(getter, this.output, RecipeCategory.FOOD, ModBlocks.RAW_RABBIT_BLOCK, Items.RABBIT);
        createStorageBlock(getter, this.output, RecipeCategory.FOOD, ModBlocks.COOKED_RABBIT_BLOCK, Items.COOKED_RABBIT);
        createStorageBlock(getter, this.output, RecipeCategory.FOOD, ModBlocks.RAW_CHICKEN_BLOCK, Items.CHICKEN);
        createStorageBlock(getter, this.output, RecipeCategory.FOOD, ModBlocks.COOKED_CHICKEN_BLOCK, Items.COOKED_CHICKEN);
        createStorageBlock(getter, this.output, RecipeCategory.FOOD, ModBlocks.RAW_COD_BLOCK, Items.COD);
        createStorageBlock(getter, this.output, RecipeCategory.FOOD, ModBlocks.COOKED_COD_BLOCK, Items.COOKED_COD);
        createStorageBlock(getter, this.output, RecipeCategory.FOOD, ModBlocks.RAW_SALMON_BLOCK, Items.SALMON);
        createStorageBlock(getter, this.output, RecipeCategory.FOOD, ModBlocks.COOKED_SALMON_BLOCK, Items.COOKED_SALMON);
        createStorageBlock(getter, this.output, RecipeCategory.FOOD, ModBlocks.TROPICAL_FISH_BLOCK, Items.TROPICAL_FISH);
        createStorageBlock(getter, this.output, RecipeCategory.FOOD, ModBlocks.PUFFERFISH_BLOCK, Items.PUFFERFISH);
        createStorageBlock(getter, this.output, RecipeCategory.FOOD, ModBlocks.ROTTEN_FLESH_BLOCK, Items.ROTTEN_FLESH);

        ShapedRecipeBuilder.shaped(getter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.TRICKLITH_BLOCK)
            .pattern("pp")
            .pattern("pp")
            .define('p', ModBlocks.POINTED_TRICKLITH)
            .unlockedBy("has_tricklith", this.has(ModBlocks.POINTED_TRICKLITH))
            .save(this.output);

        ShapedRecipeBuilder.shaped(getter, RecipeCategory.BUILDING_BLOCKS, ModItems.BOLA)
            .pattern(" l ")
            .pattern("s s")
            .pattern("hch")
            .define('l', Tags.Items.LEATHERS)
            .define('s', Tags.Items.STRINGS)
            .define('h', ModItems.CHITIN)
            .define('c', Tags.Items.COBBLESTONES)
            .unlockedBy("has_chitin", this.has(ModItems.CHITIN))
            .save(this.output);

        ShapedRecipeBuilder.shaped(getter, RecipeCategory.BUILDING_BLOCKS, ModItems.FLIGHT_ARROW, 4)
            .pattern("f")
            .pattern("s")
            .pattern("h")
            .define('f', Items.FLINT)
            .define('s', Tags.Items.RODS_WOODEN)
            .define('h', ModItems.HARPY_FEATHER)
            .unlockedBy("has_feather", this.has(ModItems.HARPY_FEATHER))
            .save(this.output);

        createMultitools(this.output, ModItems.WOODEN_MATTOCK, Items.WOODEN_SHOVEL, Items.WOODEN_HOE);
        createMultitools(this.output, ModItems.WOODEN_PICKAXE_AXE, Items.WOODEN_PICKAXE, Items.WOODEN_AXE);
        createMultitools(this.output, ModItems.STONE_MATTOCK, Items.STONE_SHOVEL, Items.STONE_HOE);
        createMultitools(this.output, ModItems.STONE_PICKAXE_AXE, Items.STONE_PICKAXE, Items.STONE_AXE);
        createMultitools(this.output, ModItems.COPPER_MATTOCK, Items.COPPER_SHOVEL, Items.COPPER_HOE);
        createMultitools(this.output, ModItems.COPPER_PICKAXE_AXE, Items.COPPER_PICKAXE, Items.COPPER_AXE);
        createMultitools(this.output, ModItems.GOLDEN_MATTOCK, Items.GOLDEN_SHOVEL, Items.GOLDEN_HOE);
        createMultitools(this.output, ModItems.GOLDEN_PICKAXE_AXE, Items.GOLDEN_PICKAXE, Items.GOLDEN_AXE);
        createMultitools(this.output, ModItems.IRON_MATTOCK, Items.IRON_SHOVEL, Items.IRON_HOE);
        createMultitools(this.output, ModItems.IRON_PICKAXE_AXE, Items.IRON_PICKAXE, Items.IRON_AXE);
        createMultitools(this.output, ModItems.DIAMOND_MATTOCK, Items.DIAMOND_SHOVEL, Items.DIAMOND_HOE);
        createMultitools(this.output, ModItems.DIAMOND_PICKAXE_AXE, Items.DIAMOND_PICKAXE, Items.DIAMOND_AXE);
        createMultitools(this.output, ModItems.NETHERITE_MATTOCK, Items.NETHERITE_SHOVEL, Items.NETHERITE_HOE);
        createMultitools(this.output, ModItems.NETHERITE_PICKAXE_AXE, Items.NETHERITE_PICKAXE, Items.NETHERITE_AXE);
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE), Ingredient.of(ModItems.DIAMOND_PICKAXE_AXE), this.tag(ItemTags.NETHERITE_TOOL_MATERIALS), RecipeCategory.TOOLS, ModItems.NETHERITE_PICKAXE_AXE.get())
            .unlocks("has_pickaxe_axe", this.has(ModItems.DIAMOND_PICKAXE_AXE))
            .save(this.output, createKey("smithing/netherite_pickaxe_axe"));

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE), Ingredient.of(ModItems.DIAMOND_MATTOCK), this.tag(ItemTags.NETHERITE_TOOL_MATERIALS), RecipeCategory.TOOLS, ModItems.NETHERITE_MATTOCK.get())
            .unlocks("has_mattock", this.has(ModItems.DIAMOND_MATTOCK))
            .save(this.output, createKey("smithing/netherite_mattock"));

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.CHITIN_UPGRADE_SMITHING_TEMPLATE), Ingredient.of(Items.LEATHER_HELMET), Ingredient.of(ModItems.CHITIN), RecipeCategory.COMBAT, ModItems.CHITIN_HELMET.get())
            .unlocks("has_chitin", this.has(ModItems.CHITIN))
            .save(this.output, createKey("smithing/chitin_helmet"));
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.CHITIN_UPGRADE_SMITHING_TEMPLATE), Ingredient.of(Items.LEATHER_CHESTPLATE), Ingredient.of(ModItems.CHITIN), RecipeCategory.COMBAT, ModItems.CHITIN_CHESTPLATE.get())
            .unlocks("has_chitin", this.has(ModItems.CHITIN))
            .save(this.output, createKey("smithing/chitin_chestplate"));
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.CHITIN_UPGRADE_SMITHING_TEMPLATE), Ingredient.of(Items.LEATHER_LEGGINGS), Ingredient.of(ModItems.CHITIN), RecipeCategory.COMBAT, ModItems.CHITIN_LEGGINGS.get())
            .unlocks("has_chitin", this.has(ModItems.CHITIN))
            .save(this.output, createKey("smithing/chitin_leggings"));
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.CHITIN_UPGRADE_SMITHING_TEMPLATE), Ingredient.of(Items.LEATHER_BOOTS), Ingredient.of(ModItems.CHITIN), RecipeCategory.COMBAT, ModItems.CHITIN_BOOTS.get())
            .unlocks("has_chitin", this.has(ModItems.CHITIN))
            .save(this.output, createKey("smithing/chitin_boots"));
    }

    private static void createMultitools(RecipeOutput output, ItemLike multitool, ItemLike tool1, ItemLike tool2) {
        String multiToolName = BuiltInRegistries.ITEM.getKey(multitool.asItem()).getPath();
        SmithingMultitoolRecipeBuilder.smithing(Ingredient.of(ModItems.COMBINATION_UPGRADE_SMITHING_TEMPLATE), Ingredient.of(tool1), Ingredient.of(tool2), RecipeCategory.TOOLS, multitool.asItem())
            .save(output, createKey("smithing/" + multiToolName + "_a"));

        SmithingMultitoolRecipeBuilder.smithing(Ingredient.of(ModItems.COMBINATION_UPGRADE_SMITHING_TEMPLATE), Ingredient.of(tool2), Ingredient.of(tool1), RecipeCategory.TOOLS, multitool.asItem())
            .save(output, createKey("smithing/" + multiToolName + "_b"));
    }

    private static void createStorageBlock(HolderGetter<Item> getter, RecipeOutput output, RecipeCategory category, ItemLike compressed, ItemLike uncompressed) {
        String compressedName = BuiltInRegistries.ITEM.getKey(compressed.asItem()).getPath();
        String uncompressedName = BuiltInRegistries.ITEM.getKey(uncompressed.asItem()).getPath();
        ShapedRecipeBuilder.shaped(getter, category, compressed)
            .pattern("iii")
            .pattern("iii")
            .pattern("iii")
            .define('i', uncompressed)
            .unlockedBy("has_uncompressed_item", inventoryTrigger(ItemPredicate.Builder.item().of(getter, uncompressed)))
            .save(output, createKey("storage/" + uncompressedName + "_to_" + compressedName));

        ShapelessRecipeBuilder.shapeless(getter, category, uncompressed, 9)
            .requires(compressed)
            .unlockedBy("has_compressed_item", inventoryTrigger(ItemPredicate.Builder.item().of(getter, compressed)))
            .save(output, createKey("storage/" + compressedName + "_to_" + uncompressedName));
    }

    public static ResourceKey<Recipe<?>> createKey(String name) {
        return ResourceKey.create(Registries.RECIPE, ClassicMobs.prefix(name));
    }
}
