package com.memedream.classicmobs.client.renderer;

import com.memedream.classicmobs.ClassicMobs;
import com.memedream.classicmobs.client.ModModelLayers;
import com.memedream.classicmobs.client.model.HagModel;
import com.memedream.classicmobs.client.model.HarpyModel;
import com.memedream.classicmobs.entity.HagEntity;
import com.memedream.classicmobs.entity.HarpyEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class HarpyRenderer extends MobRenderer<HarpyEntity, HarpyModel> {

    private static final ResourceLocation TEXTURE = ClassicMobs.prefix("textures/entity/harpy.png");

    public HarpyRenderer(EntityRendererProvider.Context context) {
        super(context, new HarpyModel(context.bakeLayer(ModModelLayers.HARPY)), 0.4F);
    }

    @Override
    public ResourceLocation getTextureLocation(HarpyEntity entity) {
        return TEXTURE;
    }
}