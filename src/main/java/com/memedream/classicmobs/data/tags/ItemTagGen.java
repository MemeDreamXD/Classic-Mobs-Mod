package com.memedream.classicmobs.data.tags;

import com.memedream.classicmobs.ClassicMobs;
import com.memedream.classicmobs.init.ModBlocks;
import com.memedream.classicmobs.init.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class ItemTagGen extends ItemTagsProvider {
	public static final TagKey<Item> DAILY_DODO_FOOD = create("daily_dodo_food");
	public static final TagKey<Item> DODO_FOOD = create("dodo_food");

    public ItemTagGen(PackOutput output, CompletableFuture<HolderLookup.Provider> future, CompletableFuture<TagLookup<Block>> provider, ExistingFileHelper helper) {
        super(output, future, provider, ClassicMobs.MOD_ID, helper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
		this.tag(DAILY_DODO_FOOD).add(
				Items.CACTUS
		);

		this.tag(DODO_FOOD).add(
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

        this.tag(Tags.Items.FOODS).add(
				ModItems.RAW_DODO.asItem(),
				ModItems.COOKED_DODO.asItem(),
                ModBlocks.ROTTEN_FLESH_BLOCK.asItem(),
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
                ModBlocks.RAW_COD_BLOCK.asItem()
        );

		this.tag(ItemTags.MEAT).add(
				ModItems.RAW_DODO.asItem(),
				ModItems.COOKED_DODO.asItem(),
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
				ModItems.RAW_DODO.asItem(),
				ModBlocks.RAW_BEEF_BLOCK.asItem(),
				ModBlocks.RAW_MUTTON_BLOCK.asItem(),
				ModBlocks.RAW_PORK_BLOCK.asItem(),
				ModBlocks.RAW_RABBIT_BLOCK.asItem(),
				ModBlocks.RAW_CHICKEN_BLOCK.asItem()
		);

		this.tag(Tags.Items.FOODS_COOKED_MEAT).add(
				ModItems.COOKED_DODO.asItem(),
				ModBlocks.COOKED_BEEF_BLOCK.asItem(),
				ModBlocks.COOKED_MUTTON_BLOCK.asItem(),
				ModBlocks.COOKED_PORK_BLOCK.asItem(),
				ModBlocks.COOKED_RABBIT_BLOCK.asItem(),
				ModBlocks.COOKED_CHICKEN_BLOCK.asItem()
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
                ModItems.HARPY_FEATHER.asItem()
        );

        this.tag(ItemTags.DYEABLE).add(
                ModItems.CHITIN_HELMET.asItem(),
                ModItems.CHITIN_CHESTPLATE.asItem(),
                ModItems.CHITIN_LEGGINGS.asItem(),
                ModItems.CHITIN_BOOTS.asItem()
        );

        this.tag(ItemTags.FREEZE_IMMUNE_WEARABLES).add(
                ModItems.CHITIN_HELMET.asItem(),
                ModItems.CHITIN_CHESTPLATE.asItem(),
                ModItems.CHITIN_LEGGINGS.asItem(),
                ModItems.CHITIN_BOOTS.asItem()
        );

        this.tag(ItemTags.HEAD_ARMOR).add(
                ModItems.CHITIN_HELMET.asItem()
        );

        this.tag(ItemTags.HEAD_ARMOR_ENCHANTABLE).add(
                ModItems.CHITIN_HELMET.asItem()
        );

        this.tag(ItemTags.CHEST_ARMOR).add(
                ModItems.CHITIN_CHESTPLATE.asItem()
        );

        this.tag(ItemTags.CHEST_ARMOR_ENCHANTABLE).add(
                ModItems.CHITIN_CHESTPLATE.asItem()
        );

        this.tag(ItemTags.LEG_ARMOR).add(
                ModItems.CHITIN_LEGGINGS.asItem()
        );

        this.tag(ItemTags.LEG_ARMOR_ENCHANTABLE).add(
                ModItems.CHITIN_LEGGINGS.asItem()
        );

        this.tag(ItemTags.FOOT_ARMOR).add(
                ModItems.CHITIN_BOOTS.asItem()
        );

        this.tag(ItemTags.FOOT_ARMOR_ENCHANTABLE).add(
                ModItems.CHITIN_BOOTS.asItem()
        );

        this.tag(ItemTags.PICKAXES).add(
                ModItems.WOODEN_PICKAXE_AXE.asItem(),
                ModItems.STONE_PICKAXE_AXE.asItem(),
                ModItems.IRON_PICKAXE_AXE.asItem(),
                ModItems.GOLDEN_PICKAXE_AXE.asItem(),
                ModItems.DIAMOND_PICKAXE_AXE.asItem(),
                ModItems.NETHERITE_PICKAXE_AXE.asItem(),
				ModItems.WOODEN_HAMMER.asItem(),
				ModItems.STONE_HAMMER.asItem(),
				ModItems.IRON_HAMMER.asItem(),
				ModItems.GOLDEN_HAMMER.asItem(),
				ModItems.DIAMOND_HAMMER.asItem(),
				ModItems.NETHERITE_HAMMER.asItem()
        );

        this.tag(ItemTags.AXES).add(
                ModItems.WOODEN_PICKAXE_AXE.asItem(),
                ModItems.STONE_PICKAXE_AXE.asItem(),
                ModItems.IRON_PICKAXE_AXE.asItem(),
                ModItems.GOLDEN_PICKAXE_AXE.asItem(),
                ModItems.DIAMOND_PICKAXE_AXE.asItem(),
                ModItems.NETHERITE_PICKAXE_AXE.asItem(),
				ModItems.WOODEN_LUMBER_AXE.asItem(),
				ModItems.STONE_LUMBER_AXE.asItem(),
				ModItems.IRON_LUMBER_AXE.asItem(),
				ModItems.GOLDEN_LUMBER_AXE.asItem(),
				ModItems.DIAMOND_LUMBER_AXE.asItem(),
				ModItems.NETHERITE_LUMBER_AXE.asItem()
        );

        this.tag(ItemTags.CLUSTER_MAX_HARVESTABLES).add(
                ModItems.WOODEN_PICKAXE_AXE.asItem(),
                ModItems.STONE_PICKAXE_AXE.asItem(),
                ModItems.IRON_PICKAXE_AXE.asItem(),
                ModItems.GOLDEN_PICKAXE_AXE.asItem(),
                ModItems.DIAMOND_PICKAXE_AXE.asItem(),
                ModItems.NETHERITE_PICKAXE_AXE.asItem(),
				ModItems.WOODEN_HAMMER.asItem(),
				ModItems.STONE_HAMMER.asItem(),
				ModItems.IRON_HAMMER.asItem(),
				ModItems.GOLDEN_HAMMER.asItem(),
				ModItems.DIAMOND_HAMMER.asItem(),
				ModItems.NETHERITE_HAMMER.asItem()
        );

        this.tag(ItemTags.SHOVELS).add(
                ModItems.WOODEN_MATTOCK.asItem(),
                ModItems.STONE_MATTOCK.asItem(),
                ModItems.IRON_MATTOCK.asItem(),
                ModItems.GOLDEN_MATTOCK.asItem(),
                ModItems.DIAMOND_MATTOCK.asItem(),
                ModItems.NETHERITE_MATTOCK.asItem(),
				ModItems.WOODEN_SPADE.asItem(),
				ModItems.STONE_SPADE.asItem(),
				ModItems.IRON_SPADE.asItem(),
				ModItems.GOLDEN_SPADE.asItem(),
				ModItems.DIAMOND_SPADE.asItem(),
				ModItems.NETHERITE_SPADE.asItem()
        );

        this.tag(ItemTags.HOES).add(
                ModItems.WOODEN_MATTOCK.asItem(),
                ModItems.STONE_MATTOCK.asItem(),
                ModItems.IRON_MATTOCK.asItem(),
                ModItems.GOLDEN_MATTOCK.asItem(),
                ModItems.DIAMOND_MATTOCK.asItem(),
                ModItems.NETHERITE_MATTOCK.asItem(),
				ModItems.WOODEN_SCYTHE.asItem(),
				ModItems.STONE_SCYTHE.asItem(),
				ModItems.IRON_SCYTHE.asItem(),
				ModItems.GOLDEN_SCYTHE.asItem(),
				ModItems.DIAMOND_SCYTHE.asItem(),
				ModItems.NETHERITE_SCYTHE.asItem()
        );

        this.tag(Tags.Items.STORAGE_BLOCKS_SLIME).add(
				ModBlocks.MAGMA_CREAM_BLOCK.asItem()
		);

		this.tag(ItemTags.ARROWS).add(
				ModItems.FLIGHT_ARROW.asItem()
		);

		this.tag(ItemTags.TRIM_TEMPLATES).add(
				ModItems.CHITIN_UPGRADE_SMITHING_TEMPLATE.asItem()
		);
    }

    public static TagKey<Item> create(String tagName) {
        return ItemTags.create(ClassicMobs.prefix(tagName));
    }
}
