package com.memedream.classicmobs.init;

import com.google.common.collect.Sets;
import com.memedream.classicmobs.ClassicMobs;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.Collections;
import java.util.Set;

public class ModLootTables {

    private static final Set<ResourceKey<LootTable>> BUILTIN_LOOT_TABLES = Sets.newHashSet();

    public static final ResourceKey<LootTable> BANANA = register("gameplay/modifiers/banana");
    public static final ResourceKey<LootTable> BIRCH_NUT = register("gameplay/modifiers/birch_nut");
    public static final ResourceKey<LootTable> PINECONE = register("gameplay/modifiers/pinecone");
    public static final ResourceKey<LootTable> DATE = register("gameplay/modifiers/date");
    public static final ResourceKey<LootTable> PALE_APPLE = register("gameplay/modifiers/pale_apple");
    public static final ResourceKey<LootTable> CHERRIES = register("gameplay/modifiers/cherries");
    public static final ResourceKey<LootTable> PERSIMMON = register("gameplay/modifiers/persimmon");

    private static ResourceKey<LootTable> register(String id) {
        return register(ResourceKey.create(Registries.LOOT_TABLE, ClassicMobs.prefix(id)));
    }

    private static ResourceKey<LootTable> register(ResourceKey<LootTable> id) {
        if (BUILTIN_LOOT_TABLES.add(id)) {
            return id;
        } else {
            throw new IllegalArgumentException(id + " is already a registered built-in loot table");
        }
    }

    public static Set<ResourceKey<LootTable>> getBuiltinLootTables() {
        return Collections.unmodifiableSet(BUILTIN_LOOT_TABLES);
    }
}
