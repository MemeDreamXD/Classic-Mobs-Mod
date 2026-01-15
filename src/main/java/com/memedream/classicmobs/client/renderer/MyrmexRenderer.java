package com.memedream.classicmobs.client.renderer;

import com.memedream.classicmobs.ClassicMobs;
import com.memedream.classicmobs.client.ModModelLayers;
import com.memedream.classicmobs.client.model.MyrmexModel;
import com.memedream.classicmobs.entity.MyrmexEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;


public class MyrmexRenderer extends MobRenderer<MyrmexEntity, LivingEntityRenderState, MyrmexModel> {

    private static final Identifier TEXTURE = ClassicMobs.prefix("textures/entity/myrmex.png");

    public MyrmexRenderer(EntityRendererProvider.Context context) {
        super(context, new MyrmexModel(context.bakeLayer(ModModelLayers.MYRMEX)), 0.4F);
    }

    @Override
    public LivingEntityRenderState createRenderState() {
        return new LivingEntityRenderState();
    }

    // TODO: Get multiple textures for Myrmex variants
    @Override
    public Identifier getTextureLocation(LivingEntityRenderState state) {
        return TEXTURE;
    }
}