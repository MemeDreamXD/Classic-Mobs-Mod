package com.memedream.classicmobs.client.renderer;

import com.memedream.classicmobs.ClassicMobs;
import com.memedream.classicmobs.client.ModModelLayers;
import com.memedream.classicmobs.client.model.HarpyModel;
import com.memedream.classicmobs.entity.HarpyEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;

public class HarpyRenderer extends MobRenderer<HarpyEntity, LivingEntityRenderState, HarpyModel> {

    private static final Identifier TEXTURE = ClassicMobs.prefix("textures/entity/harpy.png");

    public HarpyRenderer(EntityRendererProvider.Context context) {
        super(context, new HarpyModel(context.bakeLayer(ModModelLayers.HARPY)), 0.4F);
    }

    @Override
    public LivingEntityRenderState createRenderState() {
        return new LivingEntityRenderState();
    }

    @Override
    public Identifier getTextureLocation(LivingEntityRenderState state) {
        return TEXTURE;
    }
}