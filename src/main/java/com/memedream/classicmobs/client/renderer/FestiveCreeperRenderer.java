package com.memedream.classicmobs.client.renderer;

import com.memedream.classicmobs.ClassicMobs;
import com.memedream.classicmobs.entity.FestiveCreeperEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.CreeperRenderState;
import net.minecraft.resources.Identifier;

public class FestiveCreeperRenderer extends BasicCreeperRenderer<FestiveCreeperEntity, CreeperRenderState> {

    private static final Identifier TEXTURE = ClassicMobs.prefix("textures/entity/festive_creeper.png");

    public FestiveCreeperRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public CreeperRenderState createRenderState() {
        return new CreeperRenderState();
    }

    @Override
    public Identifier getTextureLocation(CreeperRenderState state) {
        return TEXTURE;
    }
}