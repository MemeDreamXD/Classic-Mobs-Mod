package com.memedream.classicmobs.datagen.data.loot;

import com.memedream.classicmobs.init.ModEntities;
import com.memedream.classicmobs.init.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.NestedLootTable;
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SmeltItemFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.stream.Stream;

public class EntityLootGen extends EntityLootSubProvider {

    protected EntityLootGen(HolderLookup.Provider provider) {
        super(FeatureFlags.REGISTRY.allFlags(), provider);
    }

    @Override
    public void generate() {
        this.add(ModEntities.ANTLION.get(), LootTable.lootTable()
            .withPool(LootPool.lootPool()
                .add(LootItem.lootTableItem(ModItems.CHITIN)
                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 2)))
                    .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0, 1)))))
            .withPool(LootPool.lootPool()
                .add(LootItem.lootTableItem(Items.SAND)
                    .apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot()))
                    .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0, 1))))));

        this.add(ModEntities.DODO.get(), LootTable.lootTable()
            .withPool(LootPool.lootPool()
                .add(LootItem.lootTableItem(Items.FEATHER)
                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 2)))
                    .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0, 1)))))
            .withPool(LootPool.lootPool()
                .add(LootItem.lootTableItem(ModItems.RAW_DODO)
                    .apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot()))
                    .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0, 1))))));

        this.add(ModEntities.HAG.get(), LootTable.lootTable()
            .withPool(LootPool.lootPool()
                .add(LootItem.lootTableItem(ModItems.LOCK_OF_HAG)
                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 2)))
                    .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0, 1)))))
            .withPool(LootPool.lootPool()
                .add(LootItem.lootTableItem(Items.KELP)
                    .apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot()))
                    .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0, 1))))));

        this.add(ModEntities.HARPY.get(), LootTable.lootTable()
            .withPool(LootPool.lootPool()
                .add(LootItem.lootTableItem(ModItems.HARPY_FEATHER)
                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4)))
                    .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0, 1))))));

        this.add(ModEntities.MYRMEX.get(), LootTable.lootTable()
            .withPool(LootPool.lootPool()
                .add(LootItem.lootTableItem(ModItems.CHITIN)
                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 4)))
                    .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0, 1))))));

        this.add(ModEntities.FESTIVE_CREEPER.get(), this.fromEntityLootTable(EntityType.CREEPER));
        this.add(ModEntities.ROCKET_CREEPER.get(), this.fromEntityLootTable(EntityType.CREEPER));
        this.add(ModEntities.SUPPORT_CREEPER.get(), this.fromEntityLootTable(EntityType.CREEPER));
    }

    public LootTable.Builder emptyLootTable() {
        return LootTable.lootTable();
    }

    public LootTable.Builder fromEntityLootTable(EntityType<?> parent) {
        return LootTable.lootTable()
            .withPool(LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1))
                .add(NestedLootTable.lootTableReference(parent.getDefaultLootTable().orElseThrow())));
    }

    @Override
    protected Stream<EntityType<?>> getKnownEntityTypes() {
        return ModEntities.ENTITY_TYPES.getEntries().stream().map(DeferredHolder::value);
    }
}
