package com.memedream.classicmobs.client.renderer;

import com.memedream.classicmobs.ClassicMobs;
import com.memedream.classicmobs.client.ModModelLayers;
import com.memedream.classicmobs.entity.RocketCreeperEntity;
import com.memedream.classicmobs.entity.SupportCreeperEntity;
import net.minecraft.client.model.CreeperModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SupportCreeperRenderer extends MobRenderer<SupportCreeperEntity, CreeperModel<SupportCreeperEntity>> {

    private static final ResourceLocation TEXTURE = ClassicMobs.prefix("textures/entity/support_creeper.png");

    public SupportCreeperRenderer(EntityRendererProvider.Context context) {
        super(context, new CreeperModel<>(context.bakeLayer(ModelLayers.CREEPER)), 0.4F);
    }

    @Override
    public ResourceLocation getTextureLocation(SupportCreeperEntity entity) {
        return TEXTURE;
    }
}