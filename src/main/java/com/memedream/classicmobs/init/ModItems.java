package com.memedream.classicmobs.init;

import com.memedream.classicmobs.ClassicMobs;
import com.memedream.classicmobs.item.*;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ClassicMobs.MOD_ID);

    // given names for items must be JSON compliant (no spaces all lowercase)
    public static final DeferredItem<Item> RAW_DODO = ITEMS.register("raw_dodo",
            () -> new Item(new Item.Properties().food(ModFoodProperties.RAW_DODO)));

    public static final DeferredItem<Item> COOKED_DODO = ITEMS.register("cooked_dodo",
            () -> new Item(new Item.Properties().food(ModFoodProperties.COOKED_DODO)));

    public static final DeferredItem<Item> CHITIN = ITEMS.register("chitin",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> HARPY_FEATHER = ITEMS.register("harpy_feather",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> LOCK_OF_HAG = ITEMS.register("lock_of_hag",
            () -> new Item(new Item.Properties()));

    //TODO: Make actual arrow lol
    public static final DeferredItem<Item> FLIGHT_ARROW = ITEMS.register("flight_arrow",
            () -> new FlightArrowItem(new Item.Properties()));

    //TODO: Bola functionality
    public static final DeferredItem<Item> BOLA = ITEMS.register("bola",
            () -> new Item(new Item.Properties().stacksTo(16)));

    public static final DeferredItem<PickaxeAxeItem> WOODEN_PICKAXE_AXE = ITEMS.register("wooden_pickaxe_axe",
            () -> new PickaxeAxeItem(Tiers.WOOD, new Item.Properties()
                    .attributes(PickaxeAxeItem.createAttributes(Tiers.WOOD, 6.0f, -3.2f))));

    public static final DeferredItem<MattockItem> WOODEN_MATTOCK = ITEMS.register("wooden_mattock",
            () -> new MattockItem(Tiers.WOOD, new Item.Properties()
                    .attributes(MattockItem.createAttributes(Tiers.WOOD, 1.5f, -3.0f))));

    public static final DeferredItem<SpadeItem> WOODEN_SPADE = ITEMS.register("wooden_spade",
            () -> new SpadeItem(Tiers.WOOD, new Item.Properties()
                    .attributes(ShovelItem.createAttributes(Tiers.WOOD, 1.5f, -3.0f))));

    public static final DeferredItem<LumberAxeItem> WOODEN_LUMBER_AXE = ITEMS.register("wooden_lumber_axe",
            () -> new LumberAxeItem(Tiers.WOOD, new Item.Properties()
                    .attributes(LumberAxeItem.createAttributes(Tiers.WOOD, 1.5f, -3.0f))));

    public static final DeferredItem<HammerItem> WOODEN_HAMMER = ITEMS.register("wooden_hammer",
            () -> new HammerItem(Tiers.WOOD, new Item.Properties()
                    .attributes(HammerItem.createAttributes(Tiers.WOOD, 1.0f, -2.8f))));

    public static final DeferredItem<ScytheItem> WOODEN_SCYTHE = ITEMS.register("wooden_scythe",
            () -> new ScytheItem(Tiers.WOOD, new Item.Properties()
                    .attributes(ScytheItem.createAttributes(Tiers.WOOD, 0.0f, -3.0f))));

    public static final DeferredItem<PickaxeAxeItem> STONE_PICKAXE_AXE = ITEMS.register("stone_pickaxe_axe",
            () -> new PickaxeAxeItem(Tiers.STONE, new Item.Properties()
                    .attributes(PickaxeAxeItem.createAttributes(Tiers.STONE, 7.0f, -3.2f))));

    public static final DeferredItem<MattockItem> STONE_MATTOCK = ITEMS.register("stone_mattock",
            () -> new MattockItem(Tiers.STONE, new Item.Properties()
                    .attributes(MattockItem.createAttributes(Tiers.STONE, 1.5f, -3.0f))));

    public static final DeferredItem<SpadeItem> STONE_SPADE = ITEMS.register("stone_spade",
            () -> new SpadeItem(Tiers.STONE, new Item.Properties()
                    .attributes(ShovelItem.createAttributes(Tiers.STONE, 1.5f, -3.0f))));

    public static final DeferredItem<LumberAxeItem> STONE_LUMBER_AXE = ITEMS.register("stone_lumber_axe",
            () -> new LumberAxeItem(Tiers.STONE, new Item.Properties()
                    .attributes(LumberAxeItem.createAttributes(Tiers.STONE, 7.0f, -3.2f))));

    public static final DeferredItem<HammerItem> STONE_HAMMER = ITEMS.register("stone_hammer",
            () -> new HammerItem(Tiers.STONE, new Item.Properties()
                    .attributes(HammerItem.createAttributes(Tiers.STONE, 1.0f, -2.8f))));

    public static final DeferredItem<ScytheItem> STONE_SCYTHE = ITEMS.register("stone_scythe",
            () -> new ScytheItem(Tiers.STONE, new Item.Properties()
                    .attributes(ScytheItem.createAttributes(Tiers.STONE, -1.0f, -2.0f))));

    public static final DeferredItem<PickaxeAxeItem> IRON_PICKAXE_AXE = ITEMS.register("iron_pickaxe_axe",
            () -> new PickaxeAxeItem(Tiers.IRON, new Item.Properties()
                    .attributes(PickaxeAxeItem.createAttributes(Tiers.IRON, 6.0f, -3.1f))));

    public static final DeferredItem<MattockItem> IRON_MATTOCK = ITEMS.register("iron_mattock",
            () -> new MattockItem(Tiers.IRON, new Item.Properties()
                    .attributes(MattockItem.createAttributes(Tiers.IRON, 1.5f, -3.0f))));

    public static final DeferredItem<SpadeItem> IRON_SPADE = ITEMS.register("iron_spade",
            () -> new SpadeItem(Tiers.IRON, new Item.Properties()
                    .attributes(ShovelItem.createAttributes(Tiers.IRON, 1.5f, -3.0f))));

    public static final DeferredItem<LumberAxeItem> IRON_LUMBER_AXE = ITEMS.register("iron_lumber_axe",
            () -> new LumberAxeItem(Tiers.IRON, new Item.Properties()
                    .attributes(LumberAxeItem.createAttributes(Tiers.IRON, 6.0f, -3.1f))));

    public static final DeferredItem<HammerItem> IRON_HAMMER = ITEMS.register("iron_hammer",
            () -> new HammerItem(Tiers.IRON, new Item.Properties()
                    .attributes(HammerItem.createAttributes(Tiers.IRON, 1.0f, -2.8f))));

    public static final DeferredItem<ScytheItem> IRON_SCYTHE = ITEMS.register("iron_scythe",
            () -> new ScytheItem(Tiers.IRON, new Item.Properties()
                    .attributes(ScytheItem.createAttributes(Tiers.IRON, -2.0f, -1.0f))));

    public static final DeferredItem<PickaxeAxeItem> GOLDEN_PICKAXE_AXE = ITEMS.register("golden_pickaxe_axe",
            () -> new PickaxeAxeItem(Tiers.GOLD, new Item.Properties()
                    .attributes(PickaxeAxeItem.createAttributes(Tiers.GOLD, 6.0f, -3.0f))));

    public static final DeferredItem<MattockItem> GOLDEN_MATTOCK = ITEMS.register("golden_mattock",
            () -> new MattockItem(Tiers.GOLD, new Item.Properties()
                    .attributes(MattockItem.createAttributes(Tiers.GOLD, 1.5f, -3.0f))));

    public static final DeferredItem<SpadeItem> GOLDEN_SPADE = ITEMS.register("golden_spade",
            () -> new SpadeItem(Tiers.GOLD, new Item.Properties()
                    .attributes(ShovelItem.createAttributes(Tiers.GOLD, 1.5f, -3.0f))));

    public static final DeferredItem<LumberAxeItem> GOLDEN_LUMBER_AXE = ITEMS.register("golden_lumber_axe",
            () -> new LumberAxeItem(Tiers.GOLD, new Item.Properties()
                    .attributes(LumberAxeItem.createAttributes(Tiers.GOLD, 6.0f, -3.0f))));

    public static final DeferredItem<HammerItem> GOLDEN_HAMMER = ITEMS.register("golden_hammer",
            () -> new HammerItem(Tiers.GOLD, new Item.Properties()
                    .attributes(HammerItem.createAttributes(Tiers.GOLD, 1.0f, -2.8f))));

    public static final DeferredItem<ScytheItem> GOLDEN_SCYTHE = ITEMS.register("golden_scythe",
            () -> new ScytheItem(Tiers.GOLD, new Item.Properties()
                    .attributes(ScytheItem.createAttributes(Tiers.GOLD, 0.0f, -3.0f))));

    public static final DeferredItem<PickaxeAxeItem> DIAMOND_PICKAXE_AXE = ITEMS.register("diamond_pickaxe_axe",
            () -> new PickaxeAxeItem(Tiers.DIAMOND, new Item.Properties()
                    .attributes(PickaxeAxeItem.createAttributes(Tiers.DIAMOND, 5.0f, -3.0f))));

    public static final DeferredItem<MattockItem> DIAMOND_MATTOCK = ITEMS.register("diamond_mattock",
            () -> new MattockItem(Tiers.DIAMOND, new Item.Properties()
                    .attributes(MattockItem.createAttributes(Tiers.DIAMOND, 1.5f, -3.0f))));

    public static final DeferredItem<SpadeItem> DIAMOND_SPADE = ITEMS.register("diamond_spade",
            () -> new SpadeItem(Tiers.DIAMOND, new Item.Properties()
                    .attributes(ShovelItem.createAttributes(Tiers.DIAMOND, 1.4f, -3.0f))));

    public static final DeferredItem<LumberAxeItem> DIAMOND_LUMBER_AXE = ITEMS.register("diamond_lumber_axe",
            () -> new LumberAxeItem(Tiers.DIAMOND, new Item.Properties()
                    .attributes(LumberAxeItem.createAttributes(Tiers.DIAMOND, 5.0f, -3.0f))));

    public static final DeferredItem<HammerItem> DIAMOND_HAMMER = ITEMS.register("diamond_hammer",
            () -> new HammerItem(Tiers.DIAMOND, new Item.Properties()
                    .attributes(HammerItem.createAttributes(Tiers.DIAMOND, 1.0f, -2.8f))));

    public static final DeferredItem<ScytheItem> DIAMOND_SCYTHE = ITEMS.register("diamond_scythe",
            () -> new ScytheItem(Tiers.DIAMOND, new Item.Properties()
                    .attributes(ScytheItem.createAttributes(Tiers.DIAMOND, -3.0f, 0.0f))));

    public static final DeferredItem<PickaxeAxeItem> NETHERITE_PICKAXE_AXE = ITEMS.register("netherite_pickaxe_axe",
            () -> new PickaxeAxeItem(Tiers.NETHERITE, new Item.Properties()
                    .attributes(PickaxeAxeItem.createAttributes(Tiers.NETHERITE, 5.0f, -3.0f))));

    public static final DeferredItem<MattockItem> NETHERITE_MATTOCK = ITEMS.register("netherite_mattock",
            () -> new MattockItem(Tiers.NETHERITE, new Item.Properties()
                    .attributes(MattockItem.createAttributes(Tiers.NETHERITE, 1.5f, -3.0f))));

    public static final DeferredItem<SpadeItem> NETHERITE_SPADE = ITEMS.register("netherite_spade",
            () -> new SpadeItem(Tiers.NETHERITE, new Item.Properties()
                    .attributes(ShovelItem.createAttributes(Tiers.NETHERITE, 1.5f, -3.0f))));

    public static final DeferredItem<LumberAxeItem> NETHERITE_LUMBER_AXE = ITEMS.register("netherite_lumber_axe",
            () -> new LumberAxeItem(Tiers.NETHERITE, new Item.Properties()
                    .attributes(LumberAxeItem.createAttributes(Tiers.NETHERITE, 5.0f, -3.0f))));

    public static final DeferredItem<HammerItem> NETHERITE_HAMMER = ITEMS.register("netherite_hammer",
            () -> new HammerItem(Tiers.NETHERITE, new Item.Properties()
                    .attributes(HammerItem.createAttributes(Tiers.NETHERITE, 1.0f, -2.8f))));

    public static final DeferredItem<ScytheItem> NETHERITE_SCYTHE = ITEMS.register("netherite_scythe",
            () -> new ScytheItem(Tiers.NETHERITE, new Item.Properties()
                    .attributes(ScytheItem.createAttributes(Tiers.NETHERITE, -4.0f, 0.0f))));

    public static final DeferredItem<Item> DODO_SPAWN_EGG = ITEMS.register("dodo_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.DODO, 0x988476, 0x604835,
                    new Item.Properties()));

    public static final DeferredItem<Item> ANTLION_SPAWN_EGG = ITEMS.register("antlion_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.ANTLION, 0x686658, 0x3a3930,
                    new Item.Properties()));

    public static final DeferredItem<Item> MYRMEX_SPAWN_EGG = ITEMS.register("myrmex_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.MYRMEX, 0x531e10, 0x6e2c19,
                    new Item.Properties()));

    public static final DeferredItem<Item> HAG_SPAWN_EGG = ITEMS.register("hag_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.HAG, 0x19240c, 0x4d6957,
                    new Item.Properties()));

    public static final DeferredItem<Item> HARPY_SPAWN_EGG = ITEMS.register("harpy_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.HARPY, 0x231f32, 0x766a4d,
                    new Item.Properties()));

    public static final DeferredItem<Item> ROCKET_CREEPER_SPAWN_EGG = ITEMS.register("rocket_creeper_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.ROCKET_CREEPER, 0x136ba9, 0x000000,
                    new Item.Properties()));

    public static final DeferredItem<Item> SUPPORT_CREEPER_SPAWN_EGG = ITEMS.register("support_creeper_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.SUPPORT_CREEPER, 0xe2c03c, 0x000000,
                    new Item.Properties()));

    public static final DeferredItem<Item> FESTIVE_CREEPER_SPAWN_EGG = ITEMS.register("festive_creeper_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.FESTIVE_CREEPER, 0xa62b00, 0x000000,
                    new Item.Properties()));

    public static final DeferredItem<ArmorItem> CHITIN_HELMET = ITEMS.register("chitin_helmet",
            () -> new ArmorItem(ModArmorMaterials.CHITIN, ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(10))));

    public static final DeferredItem<ArmorItem> CHITIN_CHESTPLATE = ITEMS.register("chitin_chestplate",
            () -> new ArmorItem(ModArmorMaterials.CHITIN, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(10))));

    public static final DeferredItem<ArmorItem> CHITIN_LEGGINGS = ITEMS.register("chitin_leggings",
            () -> new ArmorItem(ModArmorMaterials.CHITIN, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(10))));

    public static final DeferredItem<ArmorItem> CHITIN_BOOTS = ITEMS.register("chitin_boots",
            () -> new ArmorItem(ModArmorMaterials.CHITIN, ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(10))));

    public static final DeferredItem<Item> CHITIN_UPGRADE_SMITHING_TEMPLATE = ITEMS.register("chitin_upgrade_smithing_template", ChitinTemplateItem::new);

    public static final DeferredItem<Item> COMBINATION_UPGRADE_SMITHING_TEMPLATE = ITEMS.register("combination_upgrade_smithing_template", CombinationTemplateItem::new);

    public static final DeferredItem<Item> PUFFERFISH_BLOCK = ITEMS.register("pufferfish_block",
            () -> new PufferfishBlockItem(new Item.Properties().food(ModFoodProperties.PUFFERFISH_BLOCK)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
