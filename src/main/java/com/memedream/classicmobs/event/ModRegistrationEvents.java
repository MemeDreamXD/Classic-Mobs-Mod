package com.memedream.classicmobs.event;

import com.memedream.classicmobs.entity.*;
import com.memedream.classicmobs.init.ModEntities;
import com.memedream.classicmobs.init.ModItems;
import com.memedream.classicmobs.init.ModPotions;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

public class ModRegistrationEvents {

    public static void init(IEventBus bus) {
        bus.addListener(ModRegistrationEvents::registerAttributes);
        NeoForge.EVENT_BUS.addListener(ModRegistrationEvents::onBrewingRecipeRegister);
    }

    private static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.DODO.get(), DodoEntity.createAttributes().build());
        event.put(ModEntities.ANTLION.get(), AntlionEntity.createAttributes().build());
        event.put(ModEntities.MYRMEX.get(), MyrmexEntity.createAttributes().build());
        event.put(ModEntities.HAG.get(), HagEntity.createAttributes().build());
        event.put(ModEntities.HARPY.get(), HarpyEntity.createAttributes().build());
        event.put(ModEntities.ROCKET_CREEPER.get(), RocketCreeperEntity.createAttributes().build());
        event.put(ModEntities.SUPPORT_CREEPER.get(), SupportCreeperEntity.createAttributes().build());
        event.put(ModEntities.FESTIVE_CREEPER.get(), FestiveCreeperEntity.createAttributes().build());

    }

    public static void onBrewingRecipeRegister(RegisterBrewingRecipesEvent event) {
        PotionBrewing.Builder builder = event.getBuilder();

        builder.addMix(Potions.AWKWARD, ModItems.LOCK_OF_HAG.asItem(), ModPotions.FAE_CURSE_POTION);
        builder.addMix(Potions.MUNDANE, ModItems.LOCK_OF_HAG.asItem(), ModPotions.STENCH_POTION);

    }

}
