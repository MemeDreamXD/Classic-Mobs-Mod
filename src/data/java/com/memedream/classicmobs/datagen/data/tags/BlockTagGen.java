package com.memedream.classicmobs.datagen.data.tags;

import com.memedream.classicmobs.ClassicMobs;
import com.memedream.classicmobs.init.ModBlocks;
import com.memedream.classicmobs.init.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

import java.util.concurrent.CompletableFuture;

public class BlockTagGen extends BlockTagsProvider {

    public BlockTagGen(PackOutput output, CompletableFuture<HolderLookup.Provider> future) {
        super(output, future, ClassicMobs.MOD_ID);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(ModTags.Blocks.MINEABLE_WITH_MATTOCK).addTags(
            BlockTags.MINEABLE_WITH_SHOVEL,
            BlockTags.MINEABLE_WITH_HOE
        );

        this.tag(ModTags.Blocks.MINEABLE_WITH_PICKAXE_AXE).addTags(
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
            ModBlocks.COOKED_DODO_BLOCK.get(),
            ModBlocks.TROPICAL_FISH_BLOCK.get(),
            ModBlocks.PUFFERFISH_BLOCK.get(),
            ModBlocks.RAW_BEEF_BLOCK.get(),
            ModBlocks.RAW_MUTTON_BLOCK.get(),
            ModBlocks.RAW_PORK_BLOCK.get(),
            ModBlocks.RAW_RABBIT_BLOCK.get(),
            ModBlocks.RAW_CHICKEN_BLOCK.get(),
            ModBlocks.RAW_SALMON_BLOCK.get(),
            ModBlocks.RAW_COD_BLOCK.get(),
            ModBlocks.RAW_DODO_BLOCK.get(),
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
            ModBlocks.RAW_DODO_BLOCK.get(),
            ModBlocks.COOKED_DODO_BLOCK.get(),
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
            ModBlocks.BLOCK_OF_BONES.get(),
            ModBlocks.RUBY_ORE.get()
        );

        this.tag(BlockTags.NEEDS_IRON_TOOL).add(
            ModBlocks.RUBY_ORE.get()
        );

        this.tag(BlockTags.MINEABLE_WITH_SHOVEL).add(
            ModBlocks.GUNPOWDER_BLOCK.get()
        );

        this.tag(BlockTags.SWORD_EFFICIENT).add(
            ModBlocks.STRING_BLOCK.get()
        );

        this.tag(Tags.Blocks.STORAGE_BLOCKS_SLIME).add(
            ModBlocks.MAGMA_CREAM_BLOCK.get()
        );
    }
}
