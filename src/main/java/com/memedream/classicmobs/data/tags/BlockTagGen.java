package com.memedream.classicmobs.data.tags;

import com.memedream.classicmobs.ClassicMobs;
import com.memedream.classicmobs.init.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class BlockTagGen extends IntrinsicHolderTagsProvider<Block> {
	public static final TagKey<Block> MINEABLE_WITH_MATTOCK = create("mineable_with_mattock");
	public static final TagKey<Block> MINEABLE_WITH_PICKAXE_AXE = create("mineable_with_pickaxe_axe");

    @SuppressWarnings("deprecation")
    public BlockTagGen(PackOutput output, CompletableFuture<HolderLookup.Provider> future, ExistingFileHelper helper) {
        super(output, Registries.BLOCK, future, block -> block.builtInRegistryHolder().key(), ClassicMobs.MOD_ID, helper);
    }

    @Override
	@SuppressWarnings("unchecked")
    protected void addTags(HolderLookup.Provider provider) {
		this.tag(MINEABLE_WITH_MATTOCK).addTags(
				BlockTags.MINEABLE_WITH_SHOVEL,
				BlockTags.MINEABLE_WITH_HOE
		);

		this.tag(MINEABLE_WITH_PICKAXE_AXE).addTags(
				BlockTags.MINEABLE_WITH_AXE,
				BlockTags.MINEABLE_WITH_PICKAXE
		);

        this.tag(BlockTags.STRIDER_WARM_BLOCKS).add(
                ModBlocks.MAGMA_CREAM_BLOCK.get()
        );

        this.tag(Tags.Blocks.STORAGE_BLOCKS).add(
                ModBlocks.GUNPOWDER_BLOCK.get(),
                ModBlocks.ROTTEN_FLESH_BLOCK.get(),
                ModBlocks.CHITIN_BLOCK.get(),
                ModBlocks.STRING_BLOCK.get(),
                ModBlocks.MAGMA_CREAM_BLOCK.get(),
                ModBlocks.PHANTOM_MEMBRANE_BLOCK.get(),
                ModBlocks.BLOCK_OF_BONES.get(),
                ModBlocks.BLAZE_ROD_BLOCK.get(),
                ModBlocks.ENDER_PEARL_BLOCK.get(),
                ModBlocks.BREEZE_ROD_BLOCK.get(),
                ModBlocks.SPIDER_EYE_BLOCK.get(),
                ModBlocks.FERMENTED_SPIDER_EYE_BLOCK.get(),
                ModBlocks.COOKED_BEEF_BLOCK.get(),
                ModBlocks.COOKED_MUTTON_BLOCK.get(),
                ModBlocks.COOKED_PORK_BLOCK.get(),
                ModBlocks.COOKED_RABBIT_BLOCK.get(),
                ModBlocks.COOKED_CHICKEN_BLOCK.get(),
                ModBlocks.COOKED_SALMON_BLOCK.get(),
                ModBlocks.COOKED_COD_BLOCK.get(),
                ModBlocks.TROPICAL_FISH_BLOCK.get(),
                ModBlocks.PUFFERFISH_BLOCK.get(),
                ModBlocks.RAW_BEEF_BLOCK.get(),
                ModBlocks.RAW_MUTTON_BLOCK.get(),
                ModBlocks.RAW_PORK_BLOCK.get(),
                ModBlocks.RAW_RABBIT_BLOCK.get(),
                ModBlocks.RAW_CHICKEN_BLOCK.get(),
                ModBlocks.RAW_SALMON_BLOCK.get(),
                ModBlocks.RAW_COD_BLOCK.get(),
                ModBlocks.LEATHER_BLOCK.get()
        );

        this.tag(BlockTags.DAMPENS_VIBRATIONS).add(
                ModBlocks.STRING_BLOCK.get(),
                ModBlocks.LEATHER_BLOCK.get()
        );

        this.tag(BlockTags.OCCLUDES_VIBRATION_SIGNALS).add(
                ModBlocks.STRING_BLOCK.get(),
                ModBlocks.LEATHER_BLOCK.get()
        );

        this.tag(BlockTags.MINEABLE_WITH_HOE).add(
                ModBlocks.ROTTEN_FLESH_BLOCK.get(),
				ModBlocks.RAW_BEEF_BLOCK.get(),
				ModBlocks.COOKED_BEEF_BLOCK.get(),
				ModBlocks.RAW_MUTTON_BLOCK.get(),
				ModBlocks.COOKED_MUTTON_BLOCK.get(),
				ModBlocks.RAW_PORK_BLOCK.get(),
				ModBlocks.COOKED_PORK_BLOCK.get(),
				ModBlocks.RAW_RABBIT_BLOCK.get(),
				ModBlocks.COOKED_RABBIT_BLOCK.get(),
				ModBlocks.RAW_CHICKEN_BLOCK.get(),
				ModBlocks.COOKED_CHICKEN_BLOCK.get(),
				ModBlocks.RAW_SALMON_BLOCK.get(),
				ModBlocks.COOKED_SALMON_BLOCK.get(),
				ModBlocks.RAW_COD_BLOCK.get(),
				ModBlocks.COOKED_COD_BLOCK.get(),
				ModBlocks.TROPICAL_FISH_BLOCK.get(),
				ModBlocks.PUFFERFISH_BLOCK.get(),
				ModBlocks.SPIDER_EYE_BLOCK.get(),
				ModBlocks.FERMENTED_SPIDER_EYE_BLOCK.get()
        );

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
				ModBlocks.CHITIN_BLOCK.get(),
				ModBlocks.TRICKLITH_BLOCK.get(),
				ModBlocks.POINTED_TRICKLITH.get(),
				ModBlocks.BLOCK_OF_BONES.get(),
				ModBlocks.CAVERRNACK.get(),
				ModBlocks.RUBY_ORE.get(),
				ModBlocks.UNDERSHALE_BRICKS.get(),
				ModBlocks.CHISELED_UNDERSHALE.get(),
				ModBlocks.POLISHED_UNDERSHALE.get(),
				ModBlocks.NACRITE.get(),
				ModBlocks.CARVED_UNDERSHALE_TILE.get(),
				ModBlocks.CARVED_UNDERSHALE_TILE_STRAIGHT.get(),
				ModBlocks.CARVED_UNDERSHALE_TILE_CORNER.get()
        );

		this.tag(BlockTags.NEEDS_IRON_TOOL).add(
				ModBlocks.RUBY_ORE.get()
		);

        this.tag(BlockTags.MINEABLE_WITH_SHOVEL).add(
				ModBlocks.GUNPOWDER_BLOCK.get(),
				ModBlocks.CAVERRNACK.get()
        );

        this.tag(BlockTags.SWORD_EFFICIENT).add(
				ModBlocks.STRING_BLOCK.get()
		);

        this.tag(Tags.Blocks.STORAGE_BLOCKS_SLIME).add(
				ModBlocks.MAGMA_CREAM_BLOCK.get()
		);
    }

    public static TagKey<Block> create(String tagName) {
        return BlockTags.create(ClassicMobs.prefix(tagName));
    }
}
