package com.memedream.classicmobs.client.renderer;

import com.memedream.classicmobs.ClassicMobs;
import com.memedream.classicmobs.entity.SupportCreeperEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.CreeperRenderState;
import net.minecraft.resources.Identifier;

public class SupportCreeperRenderer extends BasicCreeperRenderer<SupportCreeperEntity, CreeperRenderState> {

    private static final Identifier TEXTURE = ClassicMobs.prefix("textures/entity/support_creeper.png");

    public SupportCreeperRenderer(EntityRendererProvider.Context context) {
        super(context);
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