package com.memedream.classicmobs.client.renderer.layer;

import com.memedream.classicmobs.ClassicMobs;
import com.memedream.classicmobs.client.ModModelLayers;
import com.memedream.classicmobs.client.event.ModClientRegistrationEvents;
import com.memedream.classicmobs.client.model.BoundBolaModel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Unit;

public class BolaLayer<S extends LivingEntityRenderState, M extends EntityModel<S>> extends RenderLayer<S, M> {

    private static final Identifier TEXTURE = ClassicMobs.prefix("textures/entity/bola_bound.png");
    private final BoundBolaModel model;

    public BolaLayer(RenderLayerParent<S, M> renderer) {
        super(renderer);
        this.model = new BoundBolaModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModModelLayers.BOUND_BOLA));
    }

    @Override
    public void submit(PoseStack stack, SubmitNodeCollector collector, int lightCoords, S state, float yRot, float xRot) {
        if (Boolean.TRUE.equals(state.getRenderData(ModClientRegistrationEvents.BOLA_BOUND))) {
            state.walkAnimationSpeed *= 0.5F;
            state.walkAnimationPos *= 0.5F;
            stack.pushPose();
            stack.translate(0.0F, -0.25F, 0.0F);
            stack.scale(1.2F + state.boundingBoxWidth, 1.0F, 1.2F + state.boundingBoxWidth);
            collector.order(1).submitModel(this.model, Unit.INSTANCE, stack, this.model.renderType(TEXTURE), state.lightCoords, OverlayTexture.NO_OVERLAY, state.outlineColor, null);
            stack.popPose();
        }
    }
}
