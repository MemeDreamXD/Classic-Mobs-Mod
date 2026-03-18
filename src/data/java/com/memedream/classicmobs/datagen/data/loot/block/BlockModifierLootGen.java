package com.memedream.classicmobs.datagen.data.loot.block;

import com.memedream.classicmobs.init.ModItems;
import com.memedream.classicmobs.init.ModLootTables;
import net.minecraft.advancements.criterion.DataComponentMatchers;
import net.minecraft.advancements.criterion.EnchantmentPredicate;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.predicates.DataComponentPredicates;
import net.minecraft.core.component.predicates.EnchantmentsPredicate;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.predicates.ExplosionCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.loot.CanItemPerformAbility;

import java.util.List;
import java.util.function.BiConsumer;

public class BlockModifierLootGen implements LootTableSubProvider {

    protected final HolderLookup.Provider registries;

    public BlockModifierLootGen(HolderLookup.Provider registries) {
        this.registries = registries;
    }

    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> output) {
        output.accept(ModLootTables.BIRCH_NUT, this.generateRareDropTable(ModItems.BIRCH_NUT));
        output.accept(ModLootTables.PINECONE, this.generateRareDropTable(ModItems.PINECONE));
        output.accept(ModLootTables.BANANA, this.generateRareDropTable(ModItems.BANANA));
        output.accept(ModLootTables.DATE, this.generateRareDropTable(ModItems.DATE));
        output.accept(ModLootTables.PALE_APPLE, this.generateRareDropTable(ModItems.PALE_APPLE));
        output.accept(ModLootTables.CHERRIES, this.generateRareDropTable(ModItems.CHERRIES));
        output.accept(ModLootTables.PERSIMMON, this.generateRareDropTable(ModItems.PERSIMMON));
    }

    private LootTable.Builder generateRareDropTable(ItemLike item) {
        return LootTable.lootTable().withPool(LootPool.lootPool()
            .setRolls(ConstantValue.exactly(1.0F))
            .when(CanItemPerformAbility.canItemPerformAbility(ItemAbilities.SHEARS_DIG).or(this.hasSilkTouch()).invert())
            .add(LootItem.lootTableItem(item)
                .when(ExplosionCondition.survivesExplosion()))
            .when(BonusLevelTableCondition.bonusLevelFlatChance(this.registries.getOrThrow(Enchantments.FORTUNE), 0.005F, 0.0055555557F, 0.00625F, 0.008333334F, 0.025F))
        );
    }

    protected LootItemCondition.Builder hasSilkTouch() {
        return MatchTool.toolMatches(ItemPredicate.Builder.item()
            .withComponents(DataComponentMatchers.Builder.components()
                .partial(DataComponentPredicates.ENCHANTMENTS, EnchantmentsPredicate.enchantments(
                    List.of(new EnchantmentPredicate(this.registries.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SILK_TOUCH), MinMaxBounds.Ints.atLeast(1))))).build()));
    }
}
