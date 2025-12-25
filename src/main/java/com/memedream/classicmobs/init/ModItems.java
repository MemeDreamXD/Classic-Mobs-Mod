package com.memedream.classicmobs.init;

import com.memedream.classicmobs.ClassicMobs;
import com.memedream.classicmobs.item.ModFoodProperties;
import net.minecraft.world.item.Item;
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

    public static final DeferredItem<Item> DODO_SPWAN_EGG = ITEMS.register("dodo_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.DODO, 0x988476, 0x604835,
                    new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
