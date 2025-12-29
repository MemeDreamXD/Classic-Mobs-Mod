package com.memedream.classicmobs;

import com.memedream.classicmobs.client.event.ModClientRegistrationEvents;
import com.memedream.classicmobs.event.ModRegistrationEvents;
import com.memedream.classicmobs.init.*;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;

@Mod(ClassicMobs.MOD_ID)
public class ClassicMobs {

    public static final String MOD_ID = "classic_mobs";
    public static final Logger LOGGER = LogUtils.getLogger();

    public ClassicMobs(IEventBus bus, ModContainer container, Dist dist) {
        //only fire client events if actually on the client
        if (dist.isClient()) {
            ModClientRegistrationEvents.init(bus);
        }
        ModRegistrationEvents.init(bus);

        ModItems.ITEMS.register(bus);
        ModBlocks.BLOCKS.register(bus);
        ModSounds.SOUND_EVENTS.register(bus);
        ModCreativeModeTabs.TABS.register(bus);
        ModEntities.ENTITY_TYPES.register(bus);
        ModArmorMaterials.MATERIALS.register(bus);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        //...we'll do this if we actually add a config file
        //container.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    public static ResourceLocation prefix(String name) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, name);
    }
}
