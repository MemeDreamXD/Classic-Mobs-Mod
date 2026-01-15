package com.memedream.classicmobs.client.renderer;

import com.memedream.classicmobs.ClassicMobs;
import com.memedream.classicmobs.client.ModModelLayers;
import com.memedream.classicmobs.client.model.AntlionModel;
import com.memedream.classicmobs.entity.AntlionEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;

public class AntlionRenderer extends MobRenderer<AntlionEntity, LivingEntityRenderState, AntlionModel> {

    private static final Identifier TEXTURE = ClassicMobs.prefix("textures/entity/antlion.png");

    public AntlionRenderer(EntityRendererProvider.Context context) {
        super(context, new AntlionModel(context.bakeLayer(ModModelLayers.ANTLION)), 0.4F);
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