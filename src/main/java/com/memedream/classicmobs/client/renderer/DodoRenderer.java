package com.memedream.classicmobs.client.renderer;

import com.memedream.classicmobs.ClassicMobs;
import com.memedream.classicmobs.client.ModModelLayers;
import com.memedream.classicmobs.client.model.DodoModel;
import com.memedream.classicmobs.client.state.DodoRenderState;
import com.memedream.classicmobs.entity.DodoEntity;
import net.minecraft.client.renderer.entity.AgeableMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.ChickenRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;

public class DodoRenderer extends AgeableMobRenderer<DodoEntity, DodoRenderState, DodoModel> {

    private static final Identifier TEXTURE = ClassicMobs.prefix("textures/entity/dodo.png");

    public DodoRenderer(EntityRendererProvider.Context context) {
        super(context, new DodoModel(context.bakeLayer(ModModelLayers.DODO)), new DodoModel(context.bakeLayer(ModModelLayers.DODO_BABY)), 0.4F);
    }

    @Override
    public DodoRenderState createRenderState() {
        return new DodoRenderState();
    }

    @Override
    public void extractRenderState(DodoEntity entity, DodoRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.flap = Mth.lerp(partialTicks, entity.oFlap, entity.flap);
        state.flapSpeed = Mth.lerp(partialTicks, entity.oFlapSpeed, entity.flapSpeed);
        state.isShakingHead = entity.getHeadShakeTimer() > 0;
        state.jumpy = entity.isJumpy() && entity.getDeltaMovement().y() < 0.0D;
    }

    @Override
    public Identifier getTextureLocation(DodoRenderState state) {
        return TEXTURE;
    }
}
