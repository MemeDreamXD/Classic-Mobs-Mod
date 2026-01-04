package com.memedream.classicmobs.client.model;

import com.memedream.classicmobs.entity.AntlionEntity;
import com.memedream.classicmobs.entity.HagEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;

public class HagModel extends HierarchicalModel<HagEntity> implements ArmedModel {

    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart arm_right;
    private final ModelPart arm_left;
    private final ModelPart leg_right;
    private final ModelPart leg_left;

    public HagModel(ModelPart root) {
        this.root = root;
        this.head = root.getChild("head");
        this.arm_right = root.getChild("arm_right");
        this.arm_left = root.getChild("arm_left");
        this.leg_left = root.getChild("leg_left");
        this.leg_right = root.getChild("leg_right");

    }

    public static LayerDefinition create() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition arm_left = partdefinition.addOrReplaceChild("arm_left", CubeListBuilder.create().texOffs(48, 31).addBox(0.0F, -2.0F, -2.0F, 4.0F, 16.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(6.0F, 8.0F, 0.0F));

        PartDefinition arm_right = partdefinition.addOrReplaceChild("arm_right", CubeListBuilder.create().texOffs(48, 51).addBox(-4.0F, -2.0F, -2.0F, 4.0F, 16.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.0F, 8.0F, 0.0F));

        PartDefinition leg_left = partdefinition.addOrReplaceChild("leg_left", CubeListBuilder.create().texOffs(0, 52).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 16.0F, 0.0F));

        PartDefinition leg_right = partdefinition.addOrReplaceChild("leg_right", CubeListBuilder.create().texOffs(16, 52).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 16.0F, 0.0F));

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(48, 0).addBox(-4.0F, -5.0F, -6.0F, 8.0F, 8.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(32, 52).addBox(-1.5F, -4.0F, -7.0F, 3.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(48, 14).addBox(-4.0F, -5.0F, -4.0F, 8.0F, 13.0F, 4.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, 10.0F, -6.0F));

        PartDefinition tooth5_r1 = head.addOrReplaceChild("tooth5_r1", CubeListBuilder.create().texOffs(40, 53).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 2.0F, -6.0F, 0.1309F, 0.0F, 0.0F));

        PartDefinition tooth4_r1 = head.addOrReplaceChild("tooth4_r1", CubeListBuilder.create().texOffs(46, 52).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 0.0F, -6.0F, -0.3491F, 0.0F, 0.0F));

        PartDefinition tooth3_r1 = head.addOrReplaceChild("tooth3_r1", CubeListBuilder.create().texOffs(44, 52).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, -6.0F, -0.0873F, 0.0F, 0.0F));

        PartDefinition tooth2_r1 = head.addOrReplaceChild("tooth2_r1", CubeListBuilder.create().texOffs(42, 52).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, -6.0F, 0.6545F, 0.0F, 0.0F));

        PartDefinition tooth1_r1 = head.addOrReplaceChild("tooth1_r1", CubeListBuilder.create().texOffs(40, 52).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 2.0F, -6.0F, 0.2182F, 0.0F, 0.0F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 28).addBox(-6.0F, -20.0F, -2.0F, 12.0F, 12.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-6.0F, -20.0F, -2.0F, 12.0F, 16.0F, 12.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, 24.0F, -4.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public ModelPart root() {
        return this.root;
    }

    //TODO: Needs item attack animation
    @Override
    public void setupAnim(HagEntity entity, float limbSwing, float limbSwingAmount, float partialTick, float netHeadYaw, float headPitch) {
        this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
        this.head.xRot = headPitch * ((float)Math.PI / 180F);
        this.leg_right.xRot = -1.5F * Mth.triangleWave(limbSwing, 13.0F) * limbSwingAmount;
        this.leg_left.xRot = 1.5F * Mth.triangleWave(limbSwing, 13.0F) * limbSwingAmount;
        this.leg_right.yRot = 0.0F;
        this.leg_left.yRot = 0.0F;
        this.arm_right.xRot = -1.0F * Mth.triangleWave(limbSwing, 7.0F) * limbSwingAmount;
        this.arm_left.xRot = 1.0F * Mth.triangleWave(limbSwing, 7.0F) * limbSwingAmount;
        this.arm_right.yRot = 0.0F;
        this.arm_left.yRot = 0.0F;
    }

    @Override
    public void translateToHand(HumanoidArm side, PoseStack poseStack) {
        this.getArm(side).translateAndRotate(poseStack);
        poseStack.translate(side == HumanoidArm.LEFT ? 0.05D : -0.05D, 0.2D, 0.1D);
    }

    protected ModelPart getArm(HumanoidArm side) {
        return side == HumanoidArm.LEFT ? this.arm_left : this.arm_right;
    }
}
