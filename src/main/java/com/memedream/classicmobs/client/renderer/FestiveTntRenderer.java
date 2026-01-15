package com.memedream.classicmobs.client.renderer;

import com.memedream.classicmobs.ClassicMobs;
import com.memedream.classicmobs.client.ModModelLayers;
import com.memedream.classicmobs.client.model.FestiveTNTModel;
import com.memedream.classicmobs.client.state.FestiveTNTRenderState;
import com.memedream.classicmobs.entity.FestiveTntEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;

public class FestiveTntRenderer extends EntityRenderer<FestiveTntEntity, FestiveTNTRenderState> {

    private static final Identifier TEXTURE = ClassicMobs.prefix("textures/entity/festive_tnt.png");
    private final FestiveTNTModel tnt;

    public FestiveTntRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.tnt = new FestiveTNTModel(context.bakeLayer(ModModelLayers.FESTIVE_TNT));
    }

    @Override
    public void submit(FestiveTNTRenderState state, PoseStack stack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        stack.pushPose();
        stack.translate(0.0F, 0.25F, 0.0F);
        stack.mulPose(Axis.YP.rotationDegrees(state.yRot));
        stack.mulPose(Axis.XP.rotationDegrees(state.xRot));
        stack.scale(0.5F, -0.5F, -0.5F);
        float fuse = state.fuseRemainingInTicks;
        if (state.fuseRemainingInTicks < 10.0F) {
            float g = 1.0F - state.fuseRemainingInTicks / 10.0F;
            g = Mth.clamp(g, 0.0F, 1.0F);
            g *= g;
            g *= g;
            float s = 1.0F + g * 0.3F;
            stack.scale(s, s, s);
        }

        submitNodeCollector.submitModel(this.tnt, state, stack, RenderTypes.entityCutoutNoCull(TEXTURE), state.lightCoords, getOverlay((int) fuse), state.outlineColor, null);

        stack.popPose();
        super.submit(state, stack, submitNodeCollector, camera);
    }

    private static int getOverlay(int fuse) {
        int overlay;
        if (fuse / 5 % 2 == 0) {
            overlay = OverlayTexture.pack(OverlayTexture.u(1.0F), 10);
        } else {
            overlay = OverlayTexture.NO_OVERLAY;
        }
        return overlay;
    }

    public FestiveTNTRenderState createRenderState() {
        return new FestiveTNTRenderState();
    }

    @Override
    public void extractRenderState(FestiveTntEntity entity, FestiveTNTRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.fuseRemainingInTicks = entity.getFuse() - partialTicks + 1.0F;
        state.yRot = Mth.lerp(partialTicks, entity.yRotO, entity.getYRot());
        state.xRot = Mth.lerp(partialTicks, entity.xRotO, entity.getXRot());
    }
}
