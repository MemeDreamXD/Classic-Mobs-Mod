package com.memedream.classicmobs.client.renderer;

import com.memedream.classicmobs.client.state.ThrownKnifeRenderState;
import com.memedream.classicmobs.entity.ThrownKnifeEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemDisplayContext;

public class ThrownKnifeRenderer extends EntityRenderer<ThrownKnifeEntity, ThrownKnifeRenderState> {

    private final ItemModelResolver itemModelResolver;

    public ThrownKnifeRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.itemModelResolver = context.getItemModelResolver();
    }

    @Override
    public void submit(ThrownKnifeRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        poseStack.pushPose();
        poseStack.mulPose(Axis.YP.rotationDegrees(state.yRot - 90.0F));
        poseStack.mulPose(Axis.ZP.rotationDegrees(state.xRot - 45.0F));
        poseStack.scale(1.5F, 1.5F, 1.5F);
        state.knifeState.submit(poseStack, submitNodeCollector, state.lightCoords, OverlayTexture.NO_OVERLAY, state.outlineColor);
        poseStack.popPose();
        super.submit(state, poseStack, submitNodeCollector, camera);
    }

    @Override
    public ThrownKnifeRenderState createRenderState() {
        return new ThrownKnifeRenderState();
    }

    @Override
    public void extractRenderState(ThrownKnifeEntity entity, ThrownKnifeRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        this.itemModelResolver.updateForNonLiving(state.knifeState, entity.getPickupItemStackOrigin(), ItemDisplayContext.GROUND, entity);
        state.yRot = Mth.lerp(partialTicks, entity.yRotO, entity.getYRot());
        state.xRot = Mth.lerp(partialTicks, entity.xRotO, entity.getXRot());
    }
}
