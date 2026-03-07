package com.memedream.classicmobs.client.event;

import com.google.common.reflect.TypeToken;
import com.memedream.classicmobs.ClassicMobs;
import com.memedream.classicmobs.client.ModModelLayers;
import com.memedream.classicmobs.client.audio.BolaSoundInstance;
import com.memedream.classicmobs.client.item.BolaSwing;
import com.memedream.classicmobs.client.model.*;
import com.memedream.classicmobs.client.particle.FleshDripParticle;
import com.memedream.classicmobs.client.renderer.*;
import com.memedream.classicmobs.client.renderer.layer.BolaLayer;
import com.memedream.classicmobs.client.shader.ModRenderPipelines;
import com.memedream.classicmobs.entity.BolaEntity;
import com.memedream.classicmobs.init.ModEffects;
import com.memedream.classicmobs.init.ModEntities;
import com.memedream.classicmobs.init.ModItems;
import com.memedream.classicmobs.init.ModParticles;
import com.memedream.classicmobs.item.AOEItem;
import com.memedream.classicmobs.item.BolaItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.FallingBlockRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.state.BlockOutlineRenderState;
import net.minecraft.core.BlockPos;
import net.minecraft.util.context.ContextKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.ClientHooks;
import net.neoforged.neoforge.client.event.*;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.client.renderstate.RegisterRenderStateModifiersEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;

import java.util.ArrayList;
import java.util.List;

public class ModClientRegistrationEvents {

    public static final ContextKey<Boolean> BOLA_BOUND = new ContextKey<>(ClassicMobs.prefix("bola_bound"));

    public static void init(IEventBus bus) {
        bus.addListener(ModClientRegistrationEvents::registerRenderers);
        bus.addListener(ModClientRegistrationEvents::registerModelLayers);
        bus.addListener(ModClientRegistrationEvents::registerParticles);
        bus.addListener(ModClientRegistrationEvents::registerPipelines);
        bus.addListener(ModClientRegistrationEvents::registerProperties);
        bus.addListener(EntityRenderersEvent.AddLayers.class, ModClientRegistrationEvents::addAdditionalLayers);
        bus.addListener(ModClientRegistrationEvents::registerCustomRenderData);
        bus.addListener(ModClientRegistrationEvents::registerExtensions);
        NeoForge.EVENT_BUS.addListener(ModClientRegistrationEvents::displayAOEHitboxes);
        NeoForge.EVENT_BUS.addListener(ElevatorHandler::handleElevatorTeleport);

        NeoForge.EVENT_BUS.addListener(FaeCurseHandler::tickFaeEffect);
        NeoForge.EVENT_BUS.addListener(FaeCurseHandler::renderOutlinedBlocks);
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
        event.registerEntityRenderer(ModEntities.MIMIC.get(), MimicRenderer::new);
        event.registerEntityRenderer(ModEntities.BOLA.get(), BolaRenderer::new);
    }

    private static void registerModelLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.DODO, DodoModel::create);
        event.registerLayerDefinition(ModModelLayers.DODO_BABY, () -> DodoModel.create().apply(DodoModel.BABY_TRANSFORMER));
        event.registerLayerDefinition(ModModelLayers.ANTLION, AntlionModel::create);
        event.registerLayerDefinition(ModModelLayers.MYRMEX, MyrmexModel::create);
        event.registerLayerDefinition(ModModelLayers.HAG, HagModel::create);
        event.registerLayerDefinition(ModModelLayers.HARPY, HarpyModel::create);
        event.registerLayerDefinition(ModModelLayers.FESTIVE_TNT, FestiveTNTModel::create);
        event.registerLayerDefinition(ModModelLayers.MIMIC, MimicModel::create);
        event.registerLayerDefinition(ModModelLayers.FLYING_BOLA, FlyingBolaModel::create);
        event.registerLayerDefinition(ModModelLayers.BOUND_BOLA, BoundBolaModel::create);
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
                    if (!AOEItem.isValidForOutline(event.getLevel(), pos, event.getBlockPos(), player.getMainHandItem())) continue;
                    boolean isBlockTranslucent = Minecraft.getInstance().getBlockRenderer().getBlockModel(state).hasTranslucency(event.getLevel(), pos, state);
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

    private static void registerPipelines(RegisterRenderPipelinesEvent event) {
        event.registerPipeline(ModRenderPipelines.FAE_OUTLINE);
    }

    private static void registerProperties(RegisterRangeSelectItemModelPropertyEvent event) {
        event.register(ClassicMobs.prefix("bola_swing"), BolaSwing.MAP_CODEC);
    }

    //Adam, I need you to read this carefully.
    //I know what I am doing.
    //Do not perform casts like this without talking to me first.
    //please please please please please
    @SuppressWarnings("unchecked")
    private static <T extends LivingEntity, S extends LivingEntityRenderState, M extends EntityModel<S>> void addAdditionalLayers(EntityRenderersEvent.AddLayers event) {
        event.getEntityTypes().forEach(type -> {
            EntityRenderer<?, ?> renderer = event.getRenderer(type);
            if (renderer instanceof LivingEntityRenderer<?, ?, ?> entityRenderer) {
                attachRenderLayers((LivingEntityRenderer<T, S, M>) entityRenderer);
            }
        });
    }

    private static <T extends LivingEntity, S extends LivingEntityRenderState, M extends EntityModel<S>> void attachRenderLayers(LivingEntityRenderer<T, S, M> renderer) {
        renderer.addLayer(new BolaLayer<>(renderer));
    }

    private static void registerCustomRenderData(RegisterRenderStateModifiersEvent event) {
        event.registerEntityModifier(new TypeToken<LivingEntityRenderer<?, ?, ?>>() {}, (living, state) -> state.setRenderData(BOLA_BOUND, living.getAttribute(Attributes.MOVEMENT_SPEED).hasModifier(ClassicMobs.prefix("bound"))));
    }

    private static void registerExtensions(RegisterClientExtensionsEvent event) {
        event.registerItem(new BolaItem.BolaAnimation(), ModItems.BOLA);
    }
}
