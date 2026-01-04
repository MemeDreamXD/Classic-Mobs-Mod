package com.memedream.classicmobs.client.renderer;

import com.memedream.classicmobs.ClassicMobs;
import com.memedream.classicmobs.client.ModModelLayers;
import com.memedream.classicmobs.entity.FestiveTntEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class FestiveTntRenderer extends EntityRenderer<FestiveTntEntity> {

    private static final ResourceLocation TEXTURE = ClassicMobs.prefix("textures/entity/festive_tnt.png");
    private final ModelPart tnt;

    public FestiveTntRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.tnt = context.bakeLayer(ModModelLayers.FESTIVE_TNT);
    }

    @Override
    public void render(FestiveTntEntity entity, float entityYaw, float partialTick, PoseStack stack, MultiBufferSource buffer, int light) {
        stack.pushPose();
        stack.translate(0.0F, 0.25F, 0.0F);
        stack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTick, entity.yRotO, entity.getYRot())));
        stack.mulPose(Axis.XP.rotationDegrees(Mth.lerp(partialTick, entity.xRotO, entity.getXRot())));
        stack.scale(0.5F, -0.5F, -0.5F);

        int fuse = entity.getFuse();
        if (fuse > -1 && (float) fuse - partialTick + 1.0F < 10.0F) {
            float f = 1.0F - ((float) fuse - partialTick + 1.0F) / 10.0F;
            f = Mth.clamp(f, 0.0F, 1.0F);
            f *= f;
            f *= f;
            float f1 = 1.0F + f * 0.3F;
            stack.scale(f1, f1, f1);
        }

        this.tnt.render(stack, buffer.getBuffer(RenderType.entityCutoutNoCull(this.getTextureLocation(entity))), light, getOverlay(fuse));
        stack.popPose();

        super.render(entity, entityYaw, partialTick, stack, buffer, light);
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

    public static LayerDefinition createModel() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        partdefinition.addOrReplaceChild("head", CubeListBuilder.create()
                        .texOffs(0, 0).addBox(-8.0F, -8.0F, -8.0F, 16.0F, 16.0F, 16.0F),
                PartPose.ZERO);

        return LayerDefinition.create(meshdefinition, 64, 32);
    }

    @Override
    public ResourceLocation getTextureLocation(FestiveTntEntity entity) {
        return TEXTURE;
    }
}
