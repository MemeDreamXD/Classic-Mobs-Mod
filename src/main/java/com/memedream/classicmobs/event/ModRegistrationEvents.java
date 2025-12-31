package com.memedream.classicmobs.event;

import com.memedream.classicmobs.entity.*;
import com.memedream.classicmobs.init.ModEntities;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

public class ModRegistrationEvents {

    public static void init(IEventBus bus) {
        bus.addListener(ModRegistrationEvents::registerAttributes);
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


}
