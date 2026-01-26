package com.memedream.classicmobs.client.renderer;

import com.memedream.classicmobs.ClassicMobs;
import com.memedream.classicmobs.client.ModModelLayers;
import com.memedream.classicmobs.client.model.HagModel;
import com.memedream.classicmobs.entity.HagEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.client.renderer.entity.state.ArmedEntityRenderState;
import net.minecraft.resources.Identifier;

public class HagRenderer extends MobRenderer<HagEntity, ArmedEntityRenderState, HagModel> {

    private static final Identifier TEXTURE = ClassicMobs.prefix("textures/entity/hag.png");

    public HagRenderer(EntityRendererProvider.Context context) {
        super(context, new HagModel(context.bakeLayer(ModModelLayers.HAG)), 0.4F);
        this.addLayer(new ItemInHandLayer<>(this));
    }

    @Override
    public ArmedEntityRenderState createRenderState() {
        return new ArmedEntityRenderState();
    }

    @Override
    public void extractRenderState(HagEntity entity, ArmedEntityRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        ArmedEntityRenderState.extractArmedEntityRenderState(entity, state, this.itemModelResolver, partialTicks);
    }

    @Override
    public Identifier getTextureLocation(ArmedEntityRenderState state) {
        return TEXTURE;
    }
}