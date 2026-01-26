package com.memedream.classicmobs.client.renderer;

import com.memedream.classicmobs.ClassicMobs;
import com.memedream.classicmobs.client.ModModelLayers;
import com.memedream.classicmobs.client.model.FlyingBolaModel;
import com.memedream.classicmobs.entity.BolaEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;

public class BolaRenderer extends EntityRenderer<BolaEntity, EntityRenderState> {

    private static final Identifier TEXTURE = ClassicMobs.prefix("textures/entity/bola_flying.png");
    private final FlyingBolaModel model;

    public BolaRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new FlyingBolaModel(context.bakeLayer(ModModelLayers.FLYING_BOLA));
    }

    @Override
    public void submit(EntityRenderState state, PoseStack stack, SubmitNodeCollector collector, CameraRenderState camera) {
        stack.pushPose();
        stack.translate(0.0F, -1.35F, 0.0F);
        stack.mulPose(Axis.YP.rotationDegrees(state.ageInTicks * 30));
        collector.submitModel(this.model, state, stack, this.model.renderType(TEXTURE), state.lightCoords, OverlayTexture.NO_OVERLAY, state.outlineColor, null);
        stack.popPose();
        super.submit(state, stack, collector, camera);
    }

    @Override
    public EntityRenderState createRenderState() {
        return new EntityRenderState();
    }
}
