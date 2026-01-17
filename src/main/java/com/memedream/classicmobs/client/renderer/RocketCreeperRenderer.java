package com.memedream.classicmobs.client.renderer;

import com.memedream.classicmobs.ClassicMobs;
import com.memedream.classicmobs.client.state.RocketCreeperRenderState;
import com.memedream.classicmobs.entity.RocketCreeperEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.monster.creeper.CreeperModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;

public class RocketCreeperRenderer extends MobRenderer<RocketCreeperEntity, RocketCreeperRenderState, CreeperModel> {

    private static final Identifier TEXTURE = ClassicMobs.prefix("textures/entity/rocket_creeper.png");

    public RocketCreeperRenderer(EntityRendererProvider.Context context) {
        super(context, new CreeperModel(context.bakeLayer(ModelLayers.CREEPER)), 0.4F);
    }

    @Override
    protected void scale(RocketCreeperRenderState state, PoseStack poseStack) {
        if (!state.launched) {
            float g = state.swelling;
            float wobble = 1.0F + Mth.sin(g * 100.0F) * g * 0.02F;
            g = Mth.clamp(g, 0.0F, 1.0F);
            g *= g;
            g *= g;
            float s = (1.0F + g * 0.4F) * wobble;
            float hs = (1.0F + g * 0.1F) / wobble;
            poseStack.scale(s, hs, s);
        } else {
            poseStack.translate(0.0D, -(state.entityType.getHeight() / 2), 0.0D);
            poseStack.mulPose(Axis.XP.rotationDegrees(state.launchRot));
            poseStack.mulPose(Axis.YP.rotationDegrees(state.yRot + (state.launchRot)));
            poseStack.translate(0.0D, (state.entityType.getHeight() / 2), 0.0D);
        }
    }

    @Override
    protected float getWhiteOverlayProgress(RocketCreeperRenderState state) {
        if (!state.launched) {
            float step = state.swelling;
            return (int) (step * 20.0F) % 2 == 0 ? 0.0F : Mth.clamp(step, 0.5F, 1.0F);
        }
        return 0.0F;
    }

    @Override
    public RocketCreeperRenderState createRenderState() {
        return new RocketCreeperRenderState();
    }

    @Override
    public void extractRenderState(RocketCreeperEntity entity, RocketCreeperRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.swelling = entity.getSwelling(partialTicks);
        state.isPowered = entity.isPowered();
        state.launched = entity.hasLaunched();
        state.launchRot = entity.getLaunchRot(partialTicks);
    }

    @Override
    public Identifier getTextureLocation(RocketCreeperRenderState entity) {
        return TEXTURE;
    }
}