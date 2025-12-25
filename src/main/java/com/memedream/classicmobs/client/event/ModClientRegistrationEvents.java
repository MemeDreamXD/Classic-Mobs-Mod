package com.memedream.classicmobs.client.event;

import com.memedream.classicmobs.client.ModModelLayers;
import com.memedream.classicmobs.client.model.DodoModel;
import com.memedream.classicmobs.client.renderer.DodoRenderer;
import com.memedream.classicmobs.init.ModEntities;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

public class ModClientRegistrationEvents {

    public static void init(IEventBus bus) {
        bus.addListener(ModClientRegistrationEvents::registerRenderers);
        bus.addListener(ModClientRegistrationEvents::registerModelLayers);
    }

    private static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.DODO.get(), DodoRenderer::new);
    }

    private static void registerModelLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.DODO, DodoModel::create);
    }
}
