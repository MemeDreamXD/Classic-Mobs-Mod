package com.memedream.classicmobs.datagen.data;

import com.memedream.classicmobs.init.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;
import net.neoforged.neoforge.registries.datamaps.builtin.Strippable;

import java.util.concurrent.CompletableFuture;

public class DataMapGen extends DataMapProvider {

    public DataMapGen(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather(HolderLookup.Provider provider) {
        var stripMap = this.builder(NeoForgeDataMaps.STRIPPABLES);
        stripMap.add(ModBlocks.PALM_LOG, new Strippable(ModBlocks.STRIPPED_PALM_LOG.get()), false);
        stripMap.add(ModBlocks.PALM_WOOD, new Strippable(ModBlocks.STRIPPED_PALM_WOOD.get()), false);

        var compostMap = this.builder(NeoForgeDataMaps.COMPOSTABLES);
        compostMap.add(ModBlocks.PALM_LEAVES.getId(), new Compostable(0.1F), false);
        compostMap.add(ModBlocks.PALM_SAPLING.getId(), new Compostable(0.5F), false);
    }
}
