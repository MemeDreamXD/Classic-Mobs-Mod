package com.memedream.classicmobs.client.renderer;

import com.memedream.classicmobs.ClassicMobs;
import com.memedream.classicmobs.client.ModModelLayers;
import com.memedream.classicmobs.client.model.MyrmexModel;
import com.memedream.classicmobs.entity.MyrmexEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;


public class MyrmexRenderer extends MobRenderer<MyrmexEntity, MyrmexModel> {

    private static final ResourceLocation TEXTURE = ClassicMobs.prefix("textures/entity/myrmex.png");

    public MyrmexRenderer(EntityRendererProvider.Context context) {
        super(context, new MyrmexModel(context.bakeLayer(ModModelLayers.MYRMEX)), 0.4F);
    }
// TODO: Get multiple textures for Myrmex variants
    @Override
    public ResourceLocation getTextureLocation(MyrmexEntity entity) {
        return TEXTURE;
    }
}