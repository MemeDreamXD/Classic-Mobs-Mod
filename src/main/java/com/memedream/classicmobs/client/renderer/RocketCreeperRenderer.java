package com.memedream.classicmobs.client.renderer;

import com.memedream.classicmobs.ClassicMobs;
import com.memedream.classicmobs.client.state.RocketCreeperRenderState;
import com.memedream.classicmobs.entity.RocketCreeperEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;

public class RocketCreeperRenderer extends BasicCreeperRenderer<RocketCreeperEntity, RocketCreeperRenderState> {

    private static final Identifier TEXTURE = ClassicMobs.prefix("textures/entity/rocket_creeper.png");

    public RocketCreeperRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected void scale(RocketCreeperRenderState state, PoseStack stack) {
        if (state.launched) {
            stack.translate(0.0D, -(state.entityType.getHeight() / 2.0D), 0.0D);
            stack.mulPose(Axis.XP.rotationDegrees(state.launchRot));
            stack.mulPose(Axis.YP.rotationDegrees(state.yRot + (state.launchRot)));
            stack.translate(0.0D, (state.entityType.getHeight() / 2.0D), 0.0D);
        } else {
            super.scale(state, stack);
        }
    }

    @Override
    protected float getSwellingScale(RocketCreeperRenderState state) {
        return !state.isPowered ? 2.0F : super.getSwellingScale(state);
    }

    @Override
    public RocketCreeperRenderState createRenderState() {
        return new RocketCreeperRenderState();
    }

    @Override
    public void extractRenderState(RocketCreeperEntity entity, RocketCreeperRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.launched = entity.hasLaunched();
        state.launchRot = entity.getLaunchRot(partialTicks);
    }

    @Override
    public Identifier getTextureLocation(RocketCreeperRenderState state) {
        return TEXTURE;
    }
}