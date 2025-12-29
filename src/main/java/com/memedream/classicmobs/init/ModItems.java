package com.memedream.classicmobs.init;

import com.memedream.classicmobs.ClassicMobs;
import com.memedream.classicmobs.item.ChitinTemplateItem;
import com.memedream.classicmobs.item.ModFoodProperties;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SmithingTemplateItem;
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

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
