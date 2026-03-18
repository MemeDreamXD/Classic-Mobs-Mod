package com.memedream.classicmobs.datagen.data.loot;

import com.memedream.classicmobs.ClassicMobs;
import com.memedream.classicmobs.init.ModLootTables;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.AddTableLootModifier;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;

import java.util.concurrent.CompletableFuture;

public class LootModifierGen extends GlobalLootModifierProvider {

    public LootModifierGen(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, ClassicMobs.MOD_ID);
    }

    @Override
    protected void start() {
        this.add("add_birch_nut", new AddTableLootModifier(new LootItemCondition[]{LootTableIdCondition.builder(Blocks.BIRCH_LEAVES.getLootTable().orElseThrow().identifier()).build()}, ModLootTables.BIRCH_NUT));
        this.add("add_pinecone", new AddTableLootModifier(new LootItemCondition[]{LootTableIdCondition.builder(Blocks.SPRUCE_LEAVES.getLootTable().orElseThrow().identifier()).build()}, ModLootTables.PINECONE));
        this.add("add_banana", new AddTableLootModifier(new LootItemCondition[]{LootTableIdCondition.builder(Blocks.JUNGLE_LEAVES.getLootTable().orElseThrow().identifier()).build()}, ModLootTables.BANANA));
        this.add("add_date", new AddTableLootModifier(new LootItemCondition[]{LootTableIdCondition.builder(Blocks.ACACIA_LEAVES.getLootTable().orElseThrow().identifier()).build()}, ModLootTables.DATE));
        this.add("add_pale_apple", new AddTableLootModifier(new LootItemCondition[]{LootTableIdCondition.builder(Blocks.PALE_OAK_LEAVES.getLootTable().orElseThrow().identifier()).build()}, ModLootTables.PALE_APPLE));
        this.add("add_cherries", new AddTableLootModifier(new LootItemCondition[]{LootTableIdCondition.builder(Blocks.CHERRY_LEAVES.getLootTable().orElseThrow().identifier()).build()}, ModLootTables.CHERRIES));
        this.add("add_persimmon", new AddTableLootModifier(new LootItemCondition[]{LootTableIdCondition.builder(Blocks.MANGROVE_LEAVES.getLootTable().orElseThrow().identifier()).build()}, ModLootTables.PERSIMMON));
    }
}
