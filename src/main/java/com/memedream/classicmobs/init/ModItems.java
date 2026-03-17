package com.memedream.classicmobs.init;

import com.memedream.classicmobs.ClassicMobs;
import com.memedream.classicmobs.item.*;
import com.memedream.classicmobs.item.components.ModFoodProperties;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;
import java.util.function.Supplier;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ClassicMobs.MOD_ID);

    // given names for items must be JSON compliant (no spaces all lowercase)
    public static final DeferredItem<Item> RAW_DODO = register("raw_dodo", Item::new, () -> new Item.Properties().food(ModFoodProperties.RAW_DODO, ModFoodProperties.RAW_DODO_CONSUMABLE));
    public static final DeferredItem<Item> COOKED_DODO = register("cooked_dodo", Item::new, () -> new Item.Properties().food(ModFoodProperties.COOKED_DODO, ModFoodProperties.COOKED_DODO_CONSUMABLE));
    public static final DeferredItem<Item> CHERRIES = register("cherries", Item::new, () -> new Item.Properties().food(ModFoodProperties.CHERRIES, ModFoodProperties.CHERRIES_CONSUMABLE));
    public static final DeferredItem<Item> DATE = register("date", Item::new, () -> new Item.Properties().food(ModFoodProperties.DATE, ModFoodProperties.DATE_CONSUMABLE));
    public static final DeferredItem<Item> PERSIMMON = register("persimmon", Item::new, () -> new Item.Properties().food(ModFoodProperties.PERSIMMON, ModFoodProperties.PERSIMMON_CONSUMABLE));
    public static final DeferredItem<Item> COOKED_BIRCH_NUT = register("cooked_birch_nut", Item::new, () -> new Item.Properties().food(ModFoodProperties.COOKED_BIRCH_NUT, ModFoodProperties.COOKED_BIRCH_NUT_CONSUMABLE));

    public static final DeferredItem<Item> PINECONE = register("pinecone", Item::new, Item.Properties::new);
    public static final DeferredItem<Item> BIRCH_NUT = register("birch_nut", Item::new, Item.Properties::new);
    public static final DeferredItem<Item> CHITIN = register("chitin", Item::new, Item.Properties::new);
    public static final DeferredItem<Item> HARPY_FEATHER = register("harpy_feather", Item::new, Item.Properties::new);
    public static final DeferredItem<Item> LOCK_OF_HAG = register("lock_of_hag", Item::new, Item.Properties::new);
    public static final DeferredItem<Item> GAZING_PEARL = register("gazing_pearl", Item::new, Item.Properties::new);
    public static final DeferredItem<Item> POP_POWDER = register("pop_powder", Item::new, Item.Properties::new);
    public static final DeferredItem<Item> BLAST_POWDER = register("blast_powder", Item::new, Item.Properties::new);
    public static final DeferredItem<Item> CHEM_POWDER = register("chem_powder", Item::new, Item.Properties::new);
    public static final DeferredItem<Item> RUBY = register("ruby", Item::new, () -> new Item.Properties().rarity(Rarity.UNCOMMON));
    public static final DeferredItem<Item> FLIGHT_ARROW = register("flight_arrow", FlightArrowItem::new, Item.Properties::new);
    public static final DeferredItem<Item> BOLA = register("bola", BolaItem::new, () -> new Item.Properties().stacksTo(16));

    public static final DeferredItem<PickaxeAxeItem> WOODEN_PICKAXE_AXE = register("wooden_pickaxe_axe", properties -> new PickaxeAxeItem(ToolMaterial.WOOD, 6.0f, -3.2f, properties), Item.Properties::new);
    public static final DeferredItem<MattockItem> WOODEN_MATTOCK = register("wooden_mattock", properties -> new MattockItem(ToolMaterial.WOOD, 1.5f, -3.0f, properties), Item.Properties::new);
    public static final DeferredItem<SpadeItem> WOODEN_SPADE = register("wooden_spade", properties -> new SpadeItem(ToolMaterial.WOOD, 1.5f, -3.0f, properties), Item.Properties::new);
    public static final DeferredItem<LumberAxeItem> WOODEN_LUMBER_AXE = register("wooden_lumber_axe", properties -> new LumberAxeItem(ToolMaterial.WOOD, 1.5f, -3.0f, properties), Item.Properties::new);
    public static final DeferredItem<HammerItem> WOODEN_HAMMER = register("wooden_hammer", properties -> new HammerItem(ToolMaterial.WOOD, 1.0f, -2.8f, properties), Item.Properties::new);
    public static final DeferredItem<ScytheItem> WOODEN_SCYTHE = register("wooden_scythe", properties -> new ScytheItem(ToolMaterial.WOOD, 0.0f, -3.0f, properties), Item.Properties::new);
    public static final DeferredItem<KnifeItem> WOODEN_KNIFE = register("wooden_knife", properties -> new KnifeItem(ToolMaterial.WOOD, 2.0F, -1.8F, properties), Item.Properties::new);

    public static final DeferredItem<PickaxeAxeItem> STONE_PICKAXE_AXE = register("stone_pickaxe_axe", properties -> new PickaxeAxeItem(ToolMaterial.STONE, 7.0f, -3.2f, properties), Item.Properties::new);
    public static final DeferredItem<MattockItem> STONE_MATTOCK = register("stone_mattock", properties -> new MattockItem(ToolMaterial.STONE, 1.5f, -3.0f, properties), Item.Properties::new);
    public static final DeferredItem<SpadeItem> STONE_SPADE = register("stone_spade", properties -> new SpadeItem(ToolMaterial.STONE, 1.5f, -3.0f, properties), Item.Properties::new);
    public static final DeferredItem<LumberAxeItem> STONE_LUMBER_AXE = register("stone_lumber_axe", properties -> new LumberAxeItem(ToolMaterial.STONE, 7.0f, -3.2f, properties), Item.Properties::new);
    public static final DeferredItem<HammerItem> STONE_HAMMER = register("stone_hammer", properties -> new HammerItem(ToolMaterial.STONE, 1.0f, -2.8f, properties), Item.Properties::new);
    public static final DeferredItem<ScytheItem> STONE_SCYTHE = register("stone_scythe", properties -> new ScytheItem(ToolMaterial.STONE, -1.0f, -2.0f, properties), Item.Properties::new);
    public static final DeferredItem<KnifeItem> STONE_KNIFE = register("stone_knife", properties -> new KnifeItem(ToolMaterial.STONE, 2.0F, -1.8F, properties), Item.Properties::new);

    public static final DeferredItem<PickaxeAxeItem> COPPER_PICKAXE_AXE = register("copper_pickaxe_axe", properties -> new PickaxeAxeItem(ToolMaterial.COPPER, 7.0f, -3.2f, properties), Item.Properties::new);
    public static final DeferredItem<MattockItem> COPPER_MATTOCK = register("copper_mattock", properties -> new MattockItem(ToolMaterial.COPPER, 1.5f, -3.0f, properties), Item.Properties::new);
    public static final DeferredItem<SpadeItem> COPPER_SPADE = register("copper_spade", properties -> new SpadeItem(ToolMaterial.COPPER, 1.5f, -3.0f, properties), Item.Properties::new);
    public static final DeferredItem<LumberAxeItem> COPPER_LUMBER_AXE = register("copper_lumber_axe", properties -> new LumberAxeItem(ToolMaterial.COPPER, 7.0f, -3.2f, properties), Item.Properties::new);
    public static final DeferredItem<HammerItem> COPPER_HAMMER = register("copper_hammer", properties -> new HammerItem(ToolMaterial.COPPER, 1.0f, -2.8f, properties), Item.Properties::new);
    public static final DeferredItem<ScytheItem> COPPER_SCYTHE = register("copper_scythe", properties -> new ScytheItem(ToolMaterial.COPPER, -1.0f, -2.0f, properties), Item.Properties::new);
    public static final DeferredItem<KnifeItem> COPPER_KNIFE = register("copper_knife", properties -> new KnifeItem(ToolMaterial.COPPER, 2.0F, -1.8F, properties), Item.Properties::new);

    public static final DeferredItem<PickaxeAxeItem> IRON_PICKAXE_AXE = register("iron_pickaxe_axe", properties -> new PickaxeAxeItem(ToolMaterial.IRON, 6.0f, -3.1f, properties), Item.Properties::new);
    public static final DeferredItem<MattockItem> IRON_MATTOCK = register("iron_mattock", properties -> new MattockItem(ToolMaterial.IRON, 1.5f, -3.0f, properties), Item.Properties::new);
    public static final DeferredItem<SpadeItem> IRON_SPADE = register("iron_spade", properties -> new SpadeItem(ToolMaterial.IRON, 1.5f, -3.0f, properties), Item.Properties::new);
    public static final DeferredItem<LumberAxeItem> IRON_LUMBER_AXE = register("iron_lumber_axe", properties -> new LumberAxeItem(ToolMaterial.IRON, 6.0f, -3.1f, properties), Item.Properties::new);
    public static final DeferredItem<HammerItem> IRON_HAMMER = register("iron_hammer", properties -> new HammerItem(ToolMaterial.IRON, 1.0f, -2.8f, properties), Item.Properties::new);
    public static final DeferredItem<ScytheItem> IRON_SCYTHE = register("iron_scythe", properties -> new ScytheItem(ToolMaterial.IRON, -2.0f, -1.0f, properties), Item.Properties::new);
    public static final DeferredItem<KnifeItem> IRON_KNIFE = register("iron_knife", properties -> new KnifeItem(ToolMaterial.IRON, 2.0F, -1.8F, properties), Item.Properties::new);

    public static final DeferredItem<PickaxeAxeItem> GOLDEN_PICKAXE_AXE = register("golden_pickaxe_axe", properties -> new PickaxeAxeItem(ToolMaterial.GOLD, 6.0f, -3.0f, properties), Item.Properties::new);
    public static final DeferredItem<MattockItem> GOLDEN_MATTOCK = register("golden_mattock", properties -> new MattockItem(ToolMaterial.GOLD, 1.5f, -3.0f, properties), Item.Properties::new);
    public static final DeferredItem<SpadeItem> GOLDEN_SPADE = register("golden_spade", properties -> new SpadeItem(ToolMaterial.GOLD, 1.5f, -3.0f, properties), Item.Properties::new);
    public static final DeferredItem<LumberAxeItem> GOLDEN_LUMBER_AXE = register("golden_lumber_axe", properties -> new LumberAxeItem(ToolMaterial.GOLD, 6.0f, -3.0f, properties), Item.Properties::new);
    public static final DeferredItem<HammerItem> GOLDEN_HAMMER = register("golden_hammer", properties -> new HammerItem(ToolMaterial.GOLD, 1.0f, -2.8f, properties), Item.Properties::new);
    public static final DeferredItem<ScytheItem> GOLDEN_SCYTHE = register("golden_scythe", properties -> new ScytheItem(ToolMaterial.GOLD, 0.0f, -3.0f, properties), Item.Properties::new);
    public static final DeferredItem<KnifeItem> GOLDEN_KNIFE = register("golden_knife", properties -> new KnifeItem(ToolMaterial.GOLD, 2.0F, -1.8F, properties), Item.Properties::new);

    public static final DeferredItem<PickaxeAxeItem> DIAMOND_PICKAXE_AXE = register("diamond_pickaxe_axe", properties -> new PickaxeAxeItem(ToolMaterial.DIAMOND, 5.0f, -3.0f, properties), Item.Properties::new);
    public static final DeferredItem<MattockItem> DIAMOND_MATTOCK = register("diamond_mattock", properties -> new MattockItem(ToolMaterial.DIAMOND, 1.5f, -3.0f, properties), Item.Properties::new);
    public static final DeferredItem<SpadeItem> DIAMOND_SPADE = register("diamond_spade", properties -> new SpadeItem(ToolMaterial.DIAMOND, 1.4f, -3.0f, properties), Item.Properties::new);
    public static final DeferredItem<LumberAxeItem> DIAMOND_LUMBER_AXE = register("diamond_lumber_axe", properties -> new LumberAxeItem(ToolMaterial.DIAMOND, 5.0f, -3.0f, properties), Item.Properties::new);
    public static final DeferredItem<HammerItem> DIAMOND_HAMMER = register("diamond_hammer", properties -> new HammerItem(ToolMaterial.DIAMOND, 1.0f, -2.8f, properties), Item.Properties::new);
    public static final DeferredItem<ScytheItem> DIAMOND_SCYTHE = register("diamond_scythe", properties -> new ScytheItem(ToolMaterial.DIAMOND, -3.0f, 0.0f, properties), Item.Properties::new);
    public static final DeferredItem<KnifeItem> DIAMOND_KNIFE = register("diamond_knife", properties -> new KnifeItem(ToolMaterial.DIAMOND, 2.0F, -1.8F, properties), Item.Properties::new);

    public static final DeferredItem<PickaxeAxeItem> NETHERITE_PICKAXE_AXE = register("netherite_pickaxe_axe", properties -> new PickaxeAxeItem(ToolMaterial.NETHERITE, 5.0f, -3.0f, properties), Item.Properties::new);
    public static final DeferredItem<MattockItem> NETHERITE_MATTOCK = register("netherite_mattock", properties -> new MattockItem(ToolMaterial.NETHERITE, 1.5f, -3.0f, properties), Item.Properties::new);
    public static final DeferredItem<SpadeItem> NETHERITE_SPADE = register("netherite_spade", properties -> new SpadeItem(ToolMaterial.NETHERITE, 1.5f, -3.0f, properties), Item.Properties::new);
    public static final DeferredItem<LumberAxeItem> NETHERITE_LUMBER_AXE = register("netherite_lumber_axe", properties -> new LumberAxeItem(ToolMaterial.NETHERITE, 5.0f, -3.0f, properties), Item.Properties::new);
    public static final DeferredItem<HammerItem> NETHERITE_HAMMER = register("netherite_hammer", properties -> new HammerItem(ToolMaterial.NETHERITE, 1.0f, -2.8f, properties), Item.Properties::new);
    public static final DeferredItem<ScytheItem> NETHERITE_SCYTHE = register("netherite_scythe", properties -> new ScytheItem(ToolMaterial.NETHERITE, -4.0f, 0.0f, properties), Item.Properties::new);
    public static final DeferredItem<KnifeItem> NETHERITE_KNIFE = register("netherite_knife", properties -> new KnifeItem(ToolMaterial.NETHERITE, 2.0F, -1.8F, properties), Item.Properties::new);

    public static final DeferredItem<Item> CHITIN_HELMET = register("chitin_helmet", Item::new, () -> new Item.Properties().humanoidArmor(ModArmorMaterials.CHITIN, ArmorType.HELMET));
    public static final DeferredItem<Item> CHITIN_CHESTPLATE = register("chitin_chestplate", Item::new, () -> new Item.Properties().humanoidArmor(ModArmorMaterials.CHITIN, ArmorType.CHESTPLATE));
    public static final DeferredItem<Item> CHITIN_LEGGINGS = register("chitin_leggings", Item::new, () -> new Item.Properties().humanoidArmor(ModArmorMaterials.CHITIN, ArmorType.LEGGINGS));
    public static final DeferredItem<Item> CHITIN_BOOTS = register("chitin_boots", Item::new, () -> new Item.Properties().humanoidArmor(ModArmorMaterials.CHITIN, ArmorType.BOOTS));
    public static final DeferredItem<Item> CHITIN_UPGRADE_SMITHING_TEMPLATE = register("chitin_upgrade_smithing_template", ChitinTemplateItem::new, Item.Properties::new);

    public static final DeferredItem<Item> COMBINATION_UPGRADE_SMITHING_TEMPLATE = register("combination_upgrade_smithing_template", CombinationTemplateItem::new, Item.Properties::new);

    public static <T extends Item> DeferredItem<T> register(String name, Function<Item.Properties, T> item, Supplier<Item.Properties> properties) {
        return ITEMS.register(name, () -> item.apply(properties.get().setId(ResourceKey.create(Registries.ITEM, ClassicMobs.prefix(name)))));
    }
}
