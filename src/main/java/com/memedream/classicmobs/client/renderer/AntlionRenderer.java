package com.memedream.classicmobs.client.renderer;

import com.memedream.classicmobs.ClassicMobs;
import com.memedream.classicmobs.client.ModModelLayers;
import com.memedream.classicmobs.client.model.AntlionModel;
import com.memedream.classicmobs.client.state.AntlionRenderState;
import com.memedream.classicmobs.entity.AntlionEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;

public class AntlionRenderer extends MobRenderer<AntlionEntity, AntlionRenderState, AntlionModel> {

    private static final Identifier TEXTURE = ClassicMobs.prefix("textures/entity/antlion.png");

    public AntlionRenderer(EntityRendererProvider.Context context) {
        super(context, new AntlionModel(context.bakeLayer(ModModelLayers.ANTLION)), 0.4F);
    }

    @Override
    protected void scale(AntlionRenderState state, PoseStack stack) {
        if (state.digTimer > 0) {
            state.walkAnimationPos = state.ageInTicks;
            state.walkAnimationSpeed = 0.5F;
            float progress = state.digTimer / 30.0F;
            stack.translate(0.0D, 1.5D * progress, -1.0D * progress);
            stack.mulPose(Axis.XP.rotationDegrees(60.0F * progress));
        } else if (state.huntEmergeTimer > 0) {
            state.xRot = 0.0F;
            float progress = state.huntEmergeTimer / 40.0F;
            stack.translate(0.0D, 1.5D - 0.8D * progress, 0.0D);
            stack.mulPose(Axis.XP.rotationDegrees(-90.0F));
        } else if (state.emergeTimer > 0) {
            state.walkAnimationPos = state.ageInTicks;
            state.walkAnimationSpeed = 0.5F;
            float progress = state.emergeTimer / 30.0F;
            stack.translate(0.0D, 0.75D - 0.75D * progress, 0.0D);
            stack.mulPose(Axis.XP.rotationDegrees(-90.0F + 90.0F * progress));
        } else if (state.attackTimer > 0) {
            state.yRot = Mth.sin(state.attackTimer) * 2;
            state.xRot = Mth.cos(state.attackTimer) * 2;
            stack.mulPose(Axis.XP.rotationDegrees(-90.0F));
        }
    }

    @Override
    public AntlionRenderState createRenderState() {
        return new AntlionRenderState();
    }

    @Override
    public void extractRenderState(AntlionEntity entity, AntlionRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.underground = entity.isHunting() || entity.isAttacking();
        state.digTimer = entity.isDigging() || entity.isPanicDigging() ? entity.digTimer : -1;
        state.huntEmergeTimer = entity.isHunting() ? entity.huntTimer : -1;
        state.attackTimer = entity.isAttacking() ? entity.attackTimer : -1;
        state.emergeTimer = entity.isEmerging() ? entity.emergeTimer : -1;
    }

    @Override
    public Identifier getTextureLocation(AntlionRenderState state) {
        return TEXTURE;
    }
}