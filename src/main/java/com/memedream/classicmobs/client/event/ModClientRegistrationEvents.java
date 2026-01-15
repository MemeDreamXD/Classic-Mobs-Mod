package com.memedream.classicmobs.client.event;

import com.memedream.classicmobs.client.ModModelLayers;
import com.memedream.classicmobs.client.model.*;
import com.memedream.classicmobs.client.particle.FleshDripParticle;
import com.memedream.classicmobs.client.renderer.*;
import com.memedream.classicmobs.init.ModEntities;
import com.memedream.classicmobs.init.ModParticles;
import net.minecraft.client.renderer.entity.FallingBlockRenderer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;

public class ModClientRegistrationEvents {

    public static void init(IEventBus bus) {
        bus.addListener(ModClientRegistrationEvents::registerRenderers);
        bus.addListener(ModClientRegistrationEvents::registerModelLayers);
        bus.addListener(ModClientRegistrationEvents::registerParticles);
    }

    private static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.DODO.get(), DodoRenderer::new);
        event.registerEntityRenderer(ModEntities.ANTLION.get(), AntlionRenderer::new);
        event.registerEntityRenderer(ModEntities.MYRMEX.get(), MyrmexRenderer::new);
        event.registerEntityRenderer(ModEntities.HAG.get(), HagRenderer::new);
        event.registerEntityRenderer(ModEntities.HARPY.get(), HarpyRenderer::new);
        event.registerEntityRenderer(ModEntities.ROCKET_CREEPER.get(), RocketCreeperRenderer::new);
        event.registerEntityRenderer(ModEntities.SUPPORT_CREEPER.get(), SupportCreeperRenderer::new);
        event.registerEntityRenderer(ModEntities.FESTIVE_CREEPER.get(), FestiveCreeperRenderer::new);
        event.registerEntityRenderer(ModEntities.FESTIVE_TNT.get(), FestiveTntRenderer::new);
        event.registerEntityRenderer(ModEntities.FLIGHT_ARROW.get(), FlightArrowRenderer::new);
        event.registerEntityRenderer(ModEntities.FALLING_GUNPOWDER.get(), FallingBlockRenderer::new);
    }

    private static void registerModelLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.DODO, DodoModel::create);
        event.registerLayerDefinition(ModModelLayers.DODO_BABY, () -> DodoModel.create().apply(DodoModel.BABY_TRANSFORMER));
        event.registerLayerDefinition(ModModelLayers.ANTLION, AntlionModel::create);
        event.registerLayerDefinition(ModModelLayers.MYRMEX, MyrmexModel::create);
        event.registerLayerDefinition(ModModelLayers.HAG, HagModel::create);
        event.registerLayerDefinition(ModModelLayers.HARPY, HarpyModel::create);
        event.registerLayerDefinition(ModModelLayers.FESTIVE_TNT, FestiveTNTModel::create);
    }

    private static void registerParticles(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ModParticles.DRIPPING_FLESH.get(), FleshDripParticle.FleshFallProvider::new);
        event.registerSpriteSet(ModParticles.FALLING_FLESH.get(), FleshDripParticle.FleshFallProvider::new);
        event.registerSpriteSet(ModParticles.LANDING_FLESH.get(), FleshDripParticle.FleshLandProvider::new);
    }
}
