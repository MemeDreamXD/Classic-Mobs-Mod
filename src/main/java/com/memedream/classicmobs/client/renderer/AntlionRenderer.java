package com.memedream.classicmobs.client.renderer;

import com.memedream.classicmobs.ClassicMobs;
import com.memedream.classicmobs.client.ModModelLayers;
import com.memedream.classicmobs.client.model.AntlionModel;
import com.memedream.classicmobs.client.model.DodoModel;
import com.memedream.classicmobs.entity.AntlionEntity;
import com.memedream.classicmobs.entity.DodoEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class AntlionRenderer extends MobRenderer<AntlionEntity, AntlionModel> {

    private static final ResourceLocation TEXTURE = ClassicMobs.prefix("textures/entity/antlion.png");

    public AntlionRenderer(EntityRendererProvider.Context context) {
        super(context, new AntlionModel(context.bakeLayer(ModModelLayers.ANTLION)), 0.4F);
    }

    @Override
    public ResourceLocation getTextureLocation(AntlionEntity entity) {
        return TEXTURE;
    }
}