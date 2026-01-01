package com.memedream.classicmobs.client.renderer;

import com.memedream.classicmobs.ClassicMobs;
import com.memedream.classicmobs.client.ModModelLayers;
import com.memedream.classicmobs.entity.FestiveCreeperEntity;
import net.minecraft.client.model.CreeperModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class FestiveCreeperRenderer extends MobRenderer<FestiveCreeperEntity, CreeperModel<FestiveCreeperEntity>> {

    private static final ResourceLocation TEXTURE = ClassicMobs.prefix("textures/entity/festive_creeper.png");

    public FestiveCreeperRenderer(EntityRendererProvider.Context context) {
        super(context, new CreeperModel<>(context.bakeLayer(ModelLayers.CREEPER)), 0.4F);
    }

    @Override
    public ResourceLocation getTextureLocation(FestiveCreeperEntity entity) {
        return TEXTURE;
    }
}