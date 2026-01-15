package com.memedream.classicmobs;

import com.memedream.classicmobs.client.event.ModClientRegistrationEvents;
import com.memedream.classicmobs.event.ModRegistrationEvents;
import com.memedream.classicmobs.init.*;
import com.mojang.logging.LogUtils;
import net.minecraft.resources.Identifier;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

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
        ModEffects.MOB_EFFECTS.register(bus);
        ModParticles.PARTICLES.register(bus);
        ModPotions.POTIONS.register(bus);
        ModEntities.SPAWN_EGGS.register(bus);
        ModConsumeEffects.CONSUME_EFFECTS.register(bus);
        ModRecipes.RECIPE_SERIALIZERS.register(bus);
        ModRecipes.RECIPE_TYPES.register(bus);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        //...we'll do this if we actually add a config file
        //container.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    public static Identifier prefix(String name) {
        return Identifier.fromNamespaceAndPath(MOD_ID, name);
    }
}
