package com.memedream.classicmobs.datagen.data.tags;

import com.memedream.classicmobs.ClassicMobs;
import com.memedream.classicmobs.init.ModBlocks;
import com.memedream.classicmobs.init.ModItems;
import com.memedream.classicmobs.init.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ItemTagsProvider;

import java.util.concurrent.CompletableFuture;

public class ItemTagGen extends ItemTagsProvider {

    public ItemTagGen(PackOutput output, CompletableFuture<HolderLookup.Provider> future) {
        super(output, future, ClassicMobs.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(ModTags.Items.DODO_FOOD).add(
            Items.APPLE,
            Items.MELON_SLICE,
            Items.PUMPKIN,
            Items.SWEET_BERRIES,
            Items.GLOW_BERRIES,
            Items.CARROT,
            Items.POTATO,
            Items.BEETROOT,
            Items.WHEAT,
            Items.HONEYCOMB,
            Items.COCOA_BEANS
        );

        this.tag(ModTags.Items.REPAIRS_CHITIN_ARMOR).add(ModItems.CHITIN.get());

        this.tag(ItemTags.MEAT).add(
            ModItems.RAW_DODO.get(),
            ModItems.COOKED_DODO.get(),
            ModBlocks.ROTTEN_FLESH_BLOCK.asItem(),
            ModBlocks.COOKED_BEEF_BLOCK.asItem(),
            ModBlocks.COOKED_MUTTON_BLOCK.asItem(),
            ModBlocks.COOKED_PORK_BLOCK.asItem(),
            ModBlocks.COOKED_RABBIT_BLOCK.asItem(),
            ModBlocks.COOKED_CHICKEN_BLOCK.asItem(),
            ModBlocks.RAW_BEEF_BLOCK.asItem(),
            ModBlocks.RAW_MUTTON_BLOCK.asItem(),
            ModBlocks.RAW_PORK_BLOCK.asItem(),
            ModBlocks.RAW_RABBIT_BLOCK.asItem(),
            ModBlocks.RAW_CHICKEN_BLOCK.asItem()
        );

        this.tag(Tags.Items.FOODS_RAW_MEAT).add(
            ModItems.RAW_DODO.get(),
            ModBlocks.RAW_BEEF_BLOCK.asItem(),
            ModBlocks.RAW_MUTTON_BLOCK.asItem(),
            ModBlocks.RAW_PORK_BLOCK.asItem(),
            ModBlocks.RAW_RABBIT_BLOCK.asItem(),
            ModBlocks.RAW_CHICKEN_BLOCK.asItem()
        );

        this.tag(Tags.Items.FOODS_COOKED_MEAT).add(
            ModItems.COOKED_DODO.get(),
            ModBlocks.COOKED_BEEF_BLOCK.asItem(),
            ModBlocks.COOKED_MUTTON_BLOCK.asItem(),
            ModBlocks.COOKED_PORK_BLOCK.asItem(),
            ModBlocks.COOKED_RABBIT_BLOCK.asItem(),
            ModBlocks.COOKED_CHICKEN_BLOCK.asItem()
        );

        this.tag(Tags.Items.FOODS_RAW_FISH).add(
            ModBlocks.RAW_COD_BLOCK.asItem(),
            ModBlocks.RAW_SALMON_BLOCK.asItem(),
            ModBlocks.TROPICAL_FISH_BLOCK.asItem(),
            ModBlocks.PUFFERFISH_BLOCK.asItem()
        );

        this.tag(Tags.Items.FOODS_COOKED_FISH).add(
            ModBlocks.COOKED_COD_BLOCK.asItem(),
            ModBlocks.COOKED_SALMON_BLOCK.asItem()
        );

        this.tag(Tags.Items.FOODS_EDIBLE_WHEN_PLACED).add(
            ModBlocks.COOKED_BEEF_BLOCK.asItem(),
            ModBlocks.COOKED_MUTTON_BLOCK.asItem(),
            ModBlocks.COOKED_PORK_BLOCK.asItem(),
            ModBlocks.COOKED_RABBIT_BLOCK.asItem(),
            ModBlocks.COOKED_CHICKEN_BLOCK.asItem(),
            ModBlocks.COOKED_SALMON_BLOCK.asItem(),
            ModBlocks.COOKED_COD_BLOCK.asItem(),
            ModBlocks.TROPICAL_FISH_BLOCK.asItem(),
            ModBlocks.RAW_BEEF_BLOCK.asItem(),
            ModBlocks.RAW_MUTTON_BLOCK.asItem(),
            ModBlocks.RAW_PORK_BLOCK.asItem(),
            ModBlocks.RAW_RABBIT_BLOCK.asItem(),
            ModBlocks.RAW_CHICKEN_BLOCK.asItem(),
            ModBlocks.RAW_SALMON_BLOCK.asItem(),
            ModBlocks.RAW_COD_BLOCK.asItem()
        );

        this.tag(Tags.Items.STORAGE_BLOCKS).add(
            ModBlocks.GUNPOWDER_BLOCK.asItem(),
            ModBlocks.ROTTEN_FLESH_BLOCK.asItem(),
            ModBlocks.CHITIN_BLOCK.asItem(),
            ModBlocks.STRING_BLOCK.asItem(),
            ModBlocks.MAGMA_CREAM_BLOCK.asItem(),
            ModBlocks.PHANTOM_MEMBRANE_BLOCK.asItem(),
            ModBlocks.BLOCK_OF_BONES.asItem(),
            ModBlocks.BLAZE_ROD_BLOCK.asItem(),
            ModBlocks.ENDER_PEARL_BLOCK.asItem(),
            ModBlocks.BREEZE_ROD_BLOCK.asItem(),
            ModBlocks.SPIDER_EYE_BLOCK.asItem(),
            ModBlocks.FERMENTED_SPIDER_EYE_BLOCK.asItem(),
            ModBlocks.COOKED_BEEF_BLOCK.asItem(),
            ModBlocks.COOKED_MUTTON_BLOCK.asItem(),
            ModBlocks.COOKED_PORK_BLOCK.asItem(),
            ModBlocks.COOKED_RABBIT_BLOCK.asItem(),
            ModBlocks.COOKED_CHICKEN_BLOCK.asItem(),
            ModBlocks.COOKED_SALMON_BLOCK.asItem(),
            ModBlocks.COOKED_COD_BLOCK.asItem(),
            ModBlocks.TROPICAL_FISH_BLOCK.asItem(),
            ModBlocks.PUFFERFISH_BLOCK.asItem(),
            ModBlocks.RAW_BEEF_BLOCK.asItem(),
            ModBlocks.RAW_MUTTON_BLOCK.asItem(),
            ModBlocks.RAW_PORK_BLOCK.asItem(),
            ModBlocks.RAW_RABBIT_BLOCK.asItem(),
            ModBlocks.RAW_CHICKEN_BLOCK.asItem(),
            ModBlocks.RAW_SALMON_BLOCK.asItem(),
            ModBlocks.RAW_COD_BLOCK.asItem(),
            ModBlocks.LEATHER_BLOCK.asItem()
        );

        this.tag(Tags.Items.FEATHERS).add(
            ModItems.HARPY_FEATHER.get()
        );

        this.tag(ItemTags.DYEABLE).add(
            ModItems.CHITIN_HELMET.get(),
            ModItems.CHITIN_CHESTPLATE.get(),
            ModItems.CHITIN_LEGGINGS.get(),
            ModItems.CHITIN_BOOTS.get()
        );

        this.tag(ItemTags.FREEZE_IMMUNE_WEARABLES).add(
            ModItems.CHITIN_HELMET.get(),
            ModItems.CHITIN_CHESTPLATE.get(),
            ModItems.CHITIN_LEGGINGS.get(),
            ModItems.CHITIN_BOOTS.get()
        );

        this.tag(ItemTags.HEAD_ARMOR).add(
            ModItems.CHITIN_HELMET.get()
        );

        this.tag(ItemTags.CHEST_ARMOR).add(
            ModItems.CHITIN_CHESTPLATE.get()
        );

        this.tag(ItemTags.LEG_ARMOR).add(
            ModItems.CHITIN_LEGGINGS.get()
        );

        this.tag(ItemTags.FOOT_ARMOR).add(
            ModItems.CHITIN_BOOTS.get()
        );

        this.tag(ItemTags.PICKAXES).add(
            ModItems.WOODEN_PICKAXE_AXE.get(),
            ModItems.STONE_PICKAXE_AXE.get(),
            ModItems.IRON_PICKAXE_AXE.get(),
            ModItems.GOLDEN_PICKAXE_AXE.get(),
            ModItems.DIAMOND_PICKAXE_AXE.get(),
            ModItems.NETHERITE_PICKAXE_AXE.get(),
            ModItems.WOODEN_HAMMER.get(),
            ModItems.STONE_HAMMER.get(),
            ModItems.IRON_HAMMER.get(),
            ModItems.GOLDEN_HAMMER.get(),
            ModItems.DIAMOND_HAMMER.get(),
            ModItems.NETHERITE_HAMMER.get()
        );

        this.tag(ItemTags.AXES).add(
            ModItems.WOODEN_PICKAXE_AXE.get(),
            ModItems.STONE_PICKAXE_AXE.get(),
            ModItems.IRON_PICKAXE_AXE.get(),
            ModItems.GOLDEN_PICKAXE_AXE.get(),
            ModItems.DIAMOND_PICKAXE_AXE.get(),
            ModItems.NETHERITE_PICKAXE_AXE.get(),
            ModItems.WOODEN_LUMBER_AXE.get(),
            ModItems.STONE_LUMBER_AXE.get(),
            ModItems.IRON_LUMBER_AXE.get(),
            ModItems.GOLDEN_LUMBER_AXE.get(),
            ModItems.DIAMOND_LUMBER_AXE.get(),
            ModItems.NETHERITE_LUMBER_AXE.get()
        );

        this.tag(ItemTags.CLUSTER_MAX_HARVESTABLES).add(
            ModItems.WOODEN_PICKAXE_AXE.get(),
            ModItems.STONE_PICKAXE_AXE.get(),
            ModItems.IRON_PICKAXE_AXE.get(),
            ModItems.GOLDEN_PICKAXE_AXE.get(),
            ModItems.DIAMOND_PICKAXE_AXE.get(),
            ModItems.NETHERITE_PICKAXE_AXE.get(),
            ModItems.WOODEN_HAMMER.get(),
            ModItems.STONE_HAMMER.get(),
            ModItems.IRON_HAMMER.get(),
            ModItems.GOLDEN_HAMMER.get(),
            ModItems.DIAMOND_HAMMER.get(),
            ModItems.NETHERITE_HAMMER.get()
        );

        this.tag(ItemTags.SHOVELS).add(
            ModItems.WOODEN_MATTOCK.get(),
            ModItems.STONE_MATTOCK.get(),
            ModItems.IRON_MATTOCK.get(),
            ModItems.GOLDEN_MATTOCK.get(),
            ModItems.DIAMOND_MATTOCK.get(),
            ModItems.NETHERITE_MATTOCK.get(),
            ModItems.WOODEN_SPADE.get(),
            ModItems.STONE_SPADE.get(),
            ModItems.IRON_SPADE.get(),
            ModItems.GOLDEN_SPADE.get(),
            ModItems.DIAMOND_SPADE.get(),
            ModItems.NETHERITE_SPADE.get()
        );

        this.tag(ItemTags.HOES).add(
            ModItems.WOODEN_MATTOCK.get(),
            ModItems.STONE_MATTOCK.get(),
            ModItems.IRON_MATTOCK.get(),
            ModItems.GOLDEN_MATTOCK.get(),
            ModItems.DIAMOND_MATTOCK.get(),
            ModItems.NETHERITE_MATTOCK.get(),
            ModItems.WOODEN_SCYTHE.get(),
            ModItems.STONE_SCYTHE.get(),
            ModItems.IRON_SCYTHE.get(),
            ModItems.GOLDEN_SCYTHE.get(),
            ModItems.DIAMOND_SCYTHE.get(),
            ModItems.NETHERITE_SCYTHE.get()
        );

        this.tag(ItemTags.ARROWS).add(
            ModItems.FLIGHT_ARROW.get()
        );
    }
}
