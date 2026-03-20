package com.memedream.classicmobs.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.entity.state.ArmedEntityRenderState;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;

public class HagModel extends EntityModel<ArmedEntityRenderState> implements ArmedModel<ArmedEntityRenderState> {

    private final ModelPart head;
    private final ModelPart arm_right;
    private final ModelPart arm_left;
    private final ModelPart leg_right;
    private final ModelPart leg_left;

    public HagModel(ModelPart root) {
        super(root);
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

        PartDefinition arm_right = partdefinition.addOrReplaceChild("arm_right", CubeListBuilder.create().texOffs(48, 31).mirror().addBox(-4.0F, -2.0F, -2.0F, 4.0F, 16.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-6.0F, 8.0F, 0.0F));

        PartDefinition leg_left = partdefinition.addOrReplaceChild("leg_left", CubeListBuilder.create().texOffs(0, 52).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 16.0F, 0.0F));

        PartDefinition leg_right = partdefinition.addOrReplaceChild("leg_right", CubeListBuilder.create().texOffs(0, 52).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-3.0F, 16.0F, 0.0F));

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(48, 0).addBox(-4.0F, -5.0F, -6.0F, 8.0F, 8.0F, 6.0F, new CubeDeformation(0.0F))
            .texOffs(0, 0).addBox(-1.5F, -4.0F, -7.0F, 3.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(48, 14).addBox(-4.0F, -5.0F, -4.0F, 8.0F, 13.0F, 4.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, 10.0F, -6.0F));

        PartDefinition tooth5_r1 = head.addOrReplaceChild("tooth5_r1", CubeListBuilder.create().texOffs(0, 11).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 2.0F, -6.0F, 0.1309F, 0.0F, 0.0F));

        PartDefinition tooth4_r1 = head.addOrReplaceChild("tooth4_r1", CubeListBuilder.create().texOffs(0, 11).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 0.0F, -6.0F, -0.3491F, 0.0F, 0.0F));

        PartDefinition tooth3_r1 = head.addOrReplaceChild("tooth3_r1", CubeListBuilder.create().texOffs(0, 11).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, -6.0F, -0.0873F, 0.0F, 0.0F));

        PartDefinition tooth2_r1 = head.addOrReplaceChild("tooth2_r1", CubeListBuilder.create().texOffs(0, 11).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, -6.0F, 0.6545F, 0.0F, 0.0F));

        PartDefinition tooth1_r1 = head.addOrReplaceChild("tooth1_r1", CubeListBuilder.create().texOffs(0, 11).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 2.0F, -6.0F, 0.2182F, 0.0F, 0.0F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 28).addBox(-6.0F, -20.0F, -2.0F, 12.0F, 12.0F, 12.0F, new CubeDeformation(0.0F))
            .texOffs(0, 0).addBox(-6.0F, -20.0F, -2.0F, 12.0F, 16.0F, 12.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, 24.0F, -4.0F));

        return LayerDefinition.create(meshdefinition, 80, 64);
    }

    //TODO: Needs item attack animation
    @Override
    public void setupAnim(ArmedEntityRenderState state) {
        this.head.yRot = state.yRot * Mth.DEG_TO_RAD;
        this.head.xRot = state.xRot * Mth.DEG_TO_RAD;
        this.leg_right.xRot = -1.5F * Mth.triangleWave(state.walkAnimationPos, 13.0F) * state.walkAnimationSpeed;
        this.leg_left.xRot = 1.5F * Mth.triangleWave(state.walkAnimationPos, 13.0F) * state.walkAnimationSpeed;
        this.leg_right.yRot = 0.0F;
        this.leg_left.yRot = 0.0F;
        this.arm_right.xRot = -1.0F * Mth.triangleWave(state.walkAnimationPos, 7.0F) * state.walkAnimationSpeed;
        this.arm_left.xRot = 1.0F * Mth.triangleWave(state.walkAnimationPos, 7.0F) * state.walkAnimationSpeed;
        this.arm_right.yRot = 0.0F;
        this.arm_left.yRot = 0.0F;
    }

    @Override
    public void translateToHand(ArmedEntityRenderState state, HumanoidArm arm, PoseStack stack) {
        this.getArm(arm).translateAndRotate(stack);
        stack.translate(arm == HumanoidArm.LEFT ? 0.05D : -0.05D, 0.2D, 0.1D);
    }

    protected ModelPart getArm(HumanoidArm side) {
        return side == HumanoidArm.LEFT ? this.arm_left : this.arm_right;
    }
}
