package com.memedream.classicmobs.client.model;

import com.memedream.classicmobs.ClassicMobs;
import com.memedream.classicmobs.entity.AntlionEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class AntlionModel extends EntityModel<AntlionEntity> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    //public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(ClassicMobs.MOD_ID, "antlion"), "main");
    private final ModelPart head;
    private final ModelPart jaw1;
    private final ModelPart jaw2;
    private final ModelPart body;
    private final ModelPart leg1;
    private final ModelPart leg2;
    private final ModelPart leg3;
    private final ModelPart leg4;
    private final ModelPart leg5;
    private final ModelPart leg6;

    public AntlionModel(ModelPart root) {
        this.head = root.getChild("head");
        this.jaw1 = root.getChild("jaw1");
        this.jaw2 = root.getChild("jaw2");
        this.body = root.getChild("body");
        this.leg1 = root.getChild("leg1");
        this.leg2 = root.getChild("leg2");
        this.leg3 = root.getChild("leg3");
        this.leg4 = root.getChild("leg4");
        this.leg5 = root.getChild("leg5");
        this.leg6 = root.getChild("leg6");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(50, 0).addBox(-1.0F, -6.0F, -1.0F, 8.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 24.0F, -3.0F));

        PartDefinition jaw1 = partdefinition.addOrReplaceChild("jaw1", CubeListBuilder.create().texOffs(0, 24).addBox(-4.0F, -4.0F, -12.0F, 4.0F, 4.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(0, 40).addBox(-6.0F, -4.0F, -12.0F, 2.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 24.0F, -3.0F, 0.0F, -0.6109F, 0.0F));

        PartDefinition jaw2 = partdefinition.addOrReplaceChild("jaw2", CubeListBuilder.create().texOffs(32, 24).addBox(0.0F, -2.0F, -12.0F, 4.0F, 4.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(28, 40).addBox(4.0F, -2.0F, -12.0F, 2.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 22.0F, -3.0F, 0.0F, 0.6109F, 0.0F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 10.0F));

        PartDefinition body_r1 = body.addOrReplaceChild("body_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -9.0F, -7.0F, 10.0F, 9.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, 0.0F, 0.0F));

        PartDefinition leg1 = partdefinition.addOrReplaceChild("leg1", CubeListBuilder.create(), PartPose.offset(-13.0F, 24.0F, 16.0F));

        PartDefinition leg1_r1 = leg1.addOrReplaceChild("leg1_r1", CubeListBuilder.create().texOffs(50, 13).addBox(-1.0F, -4.0F, -1.0F, 10.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0873F, -0.3491F));

        PartDefinition leg2 = partdefinition.addOrReplaceChild("leg2", CubeListBuilder.create(), PartPose.offset(13.0F, 24.0F, 16.0F));

        PartDefinition leg2_r1 = leg2.addOrReplaceChild("leg2_r1", CubeListBuilder.create().texOffs(56, 40).addBox(-9.0F, -4.0F, -1.0F, 10.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.0873F, 0.3491F));

        PartDefinition leg3 = partdefinition.addOrReplaceChild("leg3", CubeListBuilder.create(), PartPose.offset(-13.0F, 24.0F, 10.0F));

        PartDefinition leg3_r1 = leg3.addOrReplaceChild("leg3_r1", CubeListBuilder.create().texOffs(50, 13).addBox(-1.0F, -4.0F, -0.5F, 10.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.2182F));

        PartDefinition leg4 = partdefinition.addOrReplaceChild("leg4", CubeListBuilder.create(), PartPose.offset(13.0F, 24.0F, 10.0F));

        PartDefinition leg4_r1 = leg4.addOrReplaceChild("leg4_r1", CubeListBuilder.create().texOffs(56, 40).addBox(-9.0F, -4.0F, -0.5F, 10.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2182F));

        PartDefinition leg5 = partdefinition.addOrReplaceChild("leg5", CubeListBuilder.create(), PartPose.offset(-13.0F, 24.0F, 5.0F));

        PartDefinition leg5_r1 = leg5.addOrReplaceChild("leg5_r1", CubeListBuilder.create().texOffs(50, 13).addBox(-1.0F, -4.0F, -1.0F, 10.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.0873F));

        PartDefinition leg6 = partdefinition.addOrReplaceChild("leg6", CubeListBuilder.create(), PartPose.offset(13.0F, 24.0F, 5.0F));

        PartDefinition leg6_r1 = leg6.addOrReplaceChild("leg6_r1", CubeListBuilder.create().texOffs(56, 40).addBox(-9.0F, -4.0F, -1.0F, 10.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0873F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        head.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        jaw1.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        jaw2.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        leg1.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        leg2.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        leg3.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        leg4.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        leg5.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        leg6.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public void setupAnim(AntlionEntity antlionEntity, float v, float v1, float v2, float v3, float v4) {

    }
}
