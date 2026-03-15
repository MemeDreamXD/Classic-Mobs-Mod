package com.memedream.classicmobs.datagen.data.tags;

import com.memedream.classicmobs.ClassicMobs;
import com.memedream.classicmobs.init.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.world.level.biome.Biomes;
import net.neoforged.neoforge.common.Tags;

import java.util.concurrent.CompletableFuture;

public class BiomeTagGen extends BiomeTagsProvider {

    public BiomeTagGen(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, ClassicMobs.MOD_ID);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void addTags(HolderLookup.Provider registries) {
        this.tag(ModTags.Biomes.SPAWNS_PALM_TREES).addTags(
            Tags.Biomes.IS_RIVER, Tags.Biomes.IS_BEACH
        );

        this.tag(ModTags.Biomes.SPAWNS_RARE_PALM_TREES).addTag(
            Tags.Biomes.IS_DESERT
        );
    }
}
