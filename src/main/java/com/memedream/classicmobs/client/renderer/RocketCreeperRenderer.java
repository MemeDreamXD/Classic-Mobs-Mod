package com.memedream.classicmobs.client.renderer;

import com.memedream.classicmobs.ClassicMobs;
import com.memedream.classicmobs.entity.RocketCreeperEntity;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.monster.creeper.CreeperModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.CreeperRenderState;
import net.minecraft.resources.Identifier;

public class RocketCreeperRenderer extends MobRenderer<RocketCreeperEntity, CreeperRenderState, CreeperModel> {

    private static final Identifier TEXTURE = ClassicMobs.prefix("textures/entity/rocket_creeper.png");

    public RocketCreeperRenderer(EntityRendererProvider.Context context) {
        super(context, new CreeperModel(context.bakeLayer(ModelLayers.CREEPER)), 0.4F);
    }

    @Override
    public CreeperRenderState createRenderState() {
        return new CreeperRenderState();
    }

    @Override
    public Identifier getTextureLocation(CreeperRenderState entity) {
        return TEXTURE;
    }
}