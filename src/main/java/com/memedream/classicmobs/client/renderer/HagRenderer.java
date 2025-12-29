package com.memedream.classicmobs.client.renderer;

import com.memedream.classicmobs.ClassicMobs;
import com.memedream.classicmobs.client.ModModelLayers;
import com.memedream.classicmobs.client.model.HagModel;
import com.memedream.classicmobs.entity.HagEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class HagRenderer extends MobRenderer<HagEntity, HagModel> {

    private static final ResourceLocation TEXTURE = ClassicMobs.prefix("textures/entity/hag.png");

    public HagRenderer(EntityRendererProvider.Context context) {
        super(context, new HagModel(context.bakeLayer(ModModelLayers.HAG)), 0.4F);
    }

    @Override
    public ResourceLocation getTextureLocation(HagEntity entity) {
        return TEXTURE;
    }
}