package com.memedream.classicmobs.client.renderer;

import com.memedream.classicmobs.ClassicMobs;
import com.memedream.classicmobs.client.ModModelLayers;
import com.memedream.classicmobs.client.model.RocketCreeperModel;
import com.memedream.classicmobs.entity.RocketCreeperEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RocketCreeperRenderer extends MobRenderer<RocketCreeperEntity, RocketCreeperModel> {

    private static final ResourceLocation TEXTURE = ClassicMobs.prefix("textures/entity/rocket_creeper.png");

    public RocketCreeperRenderer(EntityRendererProvider.Context context) {
        super(context, new RocketCreeperModel(context.bakeLayer(ModModelLayers.ROCKET_CREEPER)), 0.4F);
    }

    @Override
    public ResourceLocation getTextureLocation(RocketCreeperEntity entity) {
        return TEXTURE;
    }
}