package com.memedream.classicmobs.datagen.data.loot;

import com.memedream.classicmobs.datagen.data.loot.block.BlockLootGen;
import com.memedream.classicmobs.datagen.data.loot.block.BlockModifierLootGen;
import com.memedream.classicmobs.init.ModLootTables;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.WritableRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.util.ProblemReporter;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContextSource;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class LootGen extends LootTableProvider {

    public LootGen(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, ModLootTables.getBuiltinLootTables(), List.of(
            new LootTableProvider.SubProviderEntry(BlockLootGen::new, LootContextParamSets.BLOCK),
            new LootTableProvider.SubProviderEntry(EntityLootGen::new, LootContextParamSets.ENTITY),
            new LootTableProvider.SubProviderEntry(BlockModifierLootGen::new, LootContextParamSets.BLOCK)
        ), provider);
    }

    @Override
    protected void validate(WritableRegistry<LootTable> tables, ValidationContextSource validationContext, ProblemReporter.Collector problems) {

    }
}
