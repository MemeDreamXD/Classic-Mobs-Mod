package com.memedream.classicmobs.client.event;

import com.memedream.classicmobs.client.ModModelLayers;
import com.memedream.classicmobs.client.model.AntlionModel;
import com.memedream.classicmobs.client.model.DodoModel;
import com.memedream.classicmobs.client.model.HagModel;
import com.memedream.classicmobs.client.model.MyrmexModel;
import com.memedream.classicmobs.client.model.HarpyModel;
import com.memedream.classicmobs.client.renderer.AntlionRenderer;
import com.memedream.classicmobs.client.renderer.DodoRenderer;
import com.memedream.classicmobs.client.renderer.HagRenderer;
import com.memedream.classicmobs.client.renderer.MyrmexRenderer;
import com.memedream.classicmobs.client.renderer.HarpyRenderer;
import com.memedream.classicmobs.init.ModEntities;
import com.memedream.classicmobs.init.ModItems;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.DyedItemColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;

public class ModClientRegistrationEvents {

    public static void init(IEventBus bus) {
        bus.addListener(ModClientRegistrationEvents::registerRenderers);
        bus.addListener(ModClientRegistrationEvents::registerModelLayers);
        bus.addListener(ModClientRegistrationEvents::registerItemColors);
    }

    private static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.DODO.get(), DodoRenderer::new);
        event.registerEntityRenderer(ModEntities.ANTLION.get(), AntlionRenderer::new);
        event.registerEntityRenderer(ModEntities.MYRMEX.get(), MyrmexRenderer::new);
        event.registerEntityRenderer(ModEntities.HAG.get(), HagRenderer::new);
        event.registerEntityRenderer(ModEntities.HARPY.get(), HarpyRenderer::new);
    }

    private static void registerModelLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.DODO, DodoModel::create);
        event.registerLayerDefinition(ModModelLayers.ANTLION, AntlionModel::create);
        event.registerLayerDefinition(ModModelLayers.MYRMEX, MyrmexModel::create);
        event.registerLayerDefinition(ModModelLayers.HAG, HagModel::create);
        event.registerLayerDefinition(ModModelLayers.HARPY, HarpyModel::create);
    }

    private static void registerItemColors(RegisterColorHandlersEvent.Item event) {
        event.register(
                (stack, index) -> index > 0 ? -1 : DyedItemColor.getOrDefault(stack, DyedItemColor.LEATHER_COLOR),
                ModItems.CHITIN_HELMET,
                ModItems.CHITIN_CHESTPLATE,
                ModItems.CHITIN_LEGGINGS,
                ModItems.CHITIN_BOOTS
        );
    }
}
