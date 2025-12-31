package com.memedream.classicmobs.client.renderer;

import com.memedream.classicmobs.ClassicMobs;
import com.memedream.classicmobs.client.ModModelLayers;
import com.memedream.classicmobs.client.model.RocketCreeperModel;
import com.memedream.classicmobs.client.model.SupportCreeperModel;
import com.memedream.classicmobs.entity.RocketCreeperEntity;
import com.memedream.classicmobs.entity.SupportCreeperEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SupportCreeperRenderer extends MobRenderer<SupportCreeperEntity, SupportCreeperModel> {

    private static final ResourceLocation TEXTURE = ClassicMobs.prefix("textures/entity/support_creeper.png");

    public SupportCreeperRenderer(EntityRendererProvider.Context context) {
        super(context, new SupportCreeperModel(context.bakeLayer(ModModelLayers.SUPPORT_CREEPER)), 0.4F);
    }

    @Override
    public ResourceLocation getTextureLocation(SupportCreeperEntity entity) {
        return TEXTURE;
    }
}