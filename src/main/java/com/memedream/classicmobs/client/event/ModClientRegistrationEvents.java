package com.memedream.classicmobs.client.event;

import com.memedream.classicmobs.client.ModModelLayers;
import com.memedream.classicmobs.client.model.*;
import com.memedream.classicmobs.client.particle.FleshDripParticle;
import com.memedream.classicmobs.client.renderer.*;
import com.memedream.classicmobs.init.ModEntities;
import com.memedream.classicmobs.init.ModParticles;
import com.memedream.classicmobs.item.AOEItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.FallingBlockRenderer;
import net.minecraft.client.renderer.state.BlockOutlineRenderState;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.ClientHooks;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.ExtractBlockOutlineRenderStateEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.common.NeoForge;

import java.util.ArrayList;
import java.util.List;

public class ModClientRegistrationEvents {

    public static void init(IEventBus bus) {
        bus.addListener(ModClientRegistrationEvents::registerRenderers);
        bus.addListener(ModClientRegistrationEvents::registerModelLayers);
        bus.addListener(ModClientRegistrationEvents::registerParticles);
        NeoForge.EVENT_BUS.addListener(ModClientRegistrationEvents::displayAOEHitboxes);
        NeoForge.EVENT_BUS.addListener(ElevatorHandler::handleElevatorTeleport);
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

    private static void displayAOEHitboxes(ExtractBlockOutlineRenderStateEvent event) {
        if (event.isCanceled()) return;
        if (event.getCamera().entity() instanceof Player player) {
            if (player.getMainHandItem().getItem() instanceof AOEItem) {
                List<BlockOutlineRenderState> states = new ArrayList<>();
                for (BlockPos pos : AOEItem.getBlocksToBeDestroyed(1, event.getBlockPos(), player)) {
                    BlockState state = event.getLevel().getBlockState(pos);
                    if (!AOEItem.isValidBlockToBreak(event.getLevel(), pos, event.getBlockPos(), player.getMainHandItem())) continue;
                    boolean isBlockTranslucent = ClientHooks.isInTranslucentBlockOutlinePass(event.getLevel(), pos, state);
                    boolean highContrast = Minecraft.getInstance().options.highContrastBlockOutline().get();
                    CollisionContext context = CollisionContext.of(player);
                    VoxelShape shape = state.getShape(event.getLevel(), pos, context);
                    var outlines = event.getCustomRenderers();
                    outlines.removeIf(outline -> outline instanceof AOEOutlineRenderer);
                    states.add(new BlockOutlineRenderState(pos, isBlockTranslucent, highContrast, shape, outlines));
                }
                event.addCustomRenderer(new AOEOutlineRenderer(states));
            }
        }
    }
}
