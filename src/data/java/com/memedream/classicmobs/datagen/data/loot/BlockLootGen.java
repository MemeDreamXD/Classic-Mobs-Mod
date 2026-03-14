package com.memedream.classicmobs.datagen.data.loot;

import com.memedream.classicmobs.init.ModBlocks;
import com.memedream.classicmobs.init.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.Set;
import java.util.stream.Collectors;

public class BlockLootGen extends BlockLootSubProvider {

    protected BlockLootGen(HolderLookup.Provider provider) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), provider);
    }

    @Override
    protected void generate() {
        this.add(ModBlocks.RUBY_ORE.get(), block -> this.createOreDrop(block, ModItems.RUBY.get()));

        //natural blocks
        this.dropSelf(ModBlocks.PALM_LOG.get());
        this.dropSelf(ModBlocks.STRIPPED_PALM_LOG.get());
        this.dropSelf(ModBlocks.PALM_PLANKS.get());

        //storage blocks
        this.dropSelf(ModBlocks.GUNPOWDER_BLOCK.get());
        this.dropSelf(ModBlocks.ROTTEN_FLESH_BLOCK.get());
        this.dropSelf(ModBlocks.CHITIN_BLOCK.get());
        this.dropSelf(ModBlocks.STRING_BLOCK.get());
        this.dropSelf(ModBlocks.MAGMA_CREAM_BLOCK.get());
        this.dropSelf(ModBlocks.PHANTOM_MEMBRANE_BLOCK.get());
        this.dropSelf(ModBlocks.BLOCK_OF_BONES.get());
        this.dropSelf(ModBlocks.BLAZE_ROD_BLOCK.get());
        this.dropSelf(ModBlocks.ENDER_PEARL_BLOCK.get());
        this.dropSelf(ModBlocks.BREEZE_ROD_BLOCK.get());
        this.dropSelf(ModBlocks.SPIDER_EYE_BLOCK.get());
        this.dropSelf(ModBlocks.FERMENTED_SPIDER_EYE_BLOCK.get());
        this.dropSelf(ModBlocks.LEATHER_BLOCK.get());

        //meat blocks
        this.dropSelf(ModBlocks.RAW_BEEF_BLOCK.get());
        this.dropSelf(ModBlocks.COOKED_BEEF_BLOCK.get());
        this.dropSelf(ModBlocks.RAW_MUTTON_BLOCK.get());
        this.dropSelf(ModBlocks.COOKED_MUTTON_BLOCK.get());
        this.dropSelf(ModBlocks.RAW_PORK_BLOCK.get());
        this.dropSelf(ModBlocks.COOKED_PORK_BLOCK.get());
        this.dropSelf(ModBlocks.RAW_RABBIT_BLOCK.get());
        this.dropSelf(ModBlocks.COOKED_RABBIT_BLOCK.get());
        this.dropSelf(ModBlocks.RAW_CHICKEN_BLOCK.get());
        this.dropSelf(ModBlocks.COOKED_CHICKEN_BLOCK.get());
        this.dropSelf(ModBlocks.RAW_COD_BLOCK.get());
        this.dropSelf(ModBlocks.COOKED_COD_BLOCK.get());
        this.dropSelf(ModBlocks.RAW_SALMON_BLOCK.get());
        this.dropSelf(ModBlocks.COOKED_SALMON_BLOCK.get());
        this.dropSelf(ModBlocks.TROPICAL_FISH_BLOCK.get());
        this.dropSelf(ModBlocks.PUFFERFISH_BLOCK.get());
        this.dropSelf(ModBlocks.RAW_DODO_BLOCK.get());
        this.dropSelf(ModBlocks.COOKED_DODO_BLOCK.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(DeferredHolder::value).collect(Collectors.toList());
    }
}
