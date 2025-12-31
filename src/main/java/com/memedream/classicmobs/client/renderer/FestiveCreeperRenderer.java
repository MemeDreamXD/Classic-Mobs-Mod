package com.memedream.classicmobs.client.renderer;

import com.memedream.classicmobs.ClassicMobs;
import com.memedream.classicmobs.client.ModModelLayers;
import com.memedream.classicmobs.client.model.FestiveCreeperModel;
import com.memedream.classicmobs.client.model.RocketCreeperModel;
import com.memedream.classicmobs.entity.FestiveCreeperEntity;
import com.memedream.classicmobs.entity.RocketCreeperEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class FestiveCreeperRenderer extends MobRenderer<FestiveCreeperEntity, FestiveCreeperModel> {

    private static final ResourceLocation TEXTURE = ClassicMobs.prefix("textures/entity/festive_creeper.png");

    public FestiveCreeperRenderer(EntityRendererProvider.Context context) {
        super(context, new FestiveCreeperModel(context.bakeLayer(ModModelLayers.FESTIVE_CREEPER)), 0.4F);
    }

    @Override
    public ResourceLocation getTextureLocation(FestiveCreeperEntity entity) {
        return TEXTURE;
    }
}