package com.memedream.classicmobs.client.model;

import com.memedream.classicmobs.client.state.DodoRenderState;
import net.minecraft.client.model.BabyModelTransform;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.entity.state.ChickenRenderState;
import net.minecraft.util.Mth;

import java.util.Set;

public class DodoModel extends EntityModel<DodoRenderState> {

    public static final MeshTransformer BABY_TRANSFORMER = new BabyModelTransform(true, 9.0F, 2.0F, 1.95F, 2.0F, 24.0F, Set.of("head", "bill"));
    private final ModelPart head;
    private final ModelPart rightWing;
    private final ModelPart leftWing;
    private final ModelPart rightLeg;
    private final ModelPart leftLeg;

    public DodoModel(ModelPart root) {
        super(root);
        this.head = root.getChild("head");
        ModelPart body = root.getChild("body");
        this.rightWing = body.getChild("right_wing");
        this.leftWing = body.getChild("left_wing");
        this.rightLeg = root.getChild("right_leg");
        this.leftLeg = root.getChild("left_leg");
    }

    public static LayerDefinition create() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create()
                .texOffs(0, 16).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 9.0F, 4.0F),
            PartPose.offset(0.0F, 15.0F, -4.0F));

        PartDefinition bill = head.addOrReplaceChild("bill", CubeListBuilder.create()
                .texOffs(16, 24).addBox(-1.0F, 1.0F, -4.0F, 2.0F, 1.0F, 5.0F)
                .texOffs(16, 16).addBox(-1.0F, -1.0F, -5.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.2F))
                .texOffs(30, 24).addBox(-1.0F, 1.0F, -5.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.1F)),
            PartPose.offsetAndRotation(0.0F, -4.0F, -2.0F, 0.5236F, 0.0F, 0.0F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(),
            PartPose.offsetAndRotation(0.0F, 16.0F, 0.0F, -0.1309F, 0.0F, 0.0F));

        body.addOrReplaceChild("body_r1", CubeListBuilder.create()
                .texOffs(0, 0).addBox(-4.0F, -12.0F, -3.0F, 8.0F, 10.0F, 6.0F),
            PartPose.offsetAndRotation(0.0F, 1.0F, 8.0F, 1.5708F, 0.0F, 0.0F));

        PartDefinition left_wing = body.addOrReplaceChild("left_wing", CubeListBuilder.create()
            .texOffs(0, 37).addBox(0.0F, 0.0F, -2.0F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.1F))
            .texOffs(0, 29).addBox(0.0F, 0.0F, -2.0F, 1.0F, 4.0F, 4.0F)
            .texOffs(32, 26).addBox(1.0F, 0.0F, 2.0F, 0.0F, 4.0F, 1.0F), PartPose.offset(4.0F, -1.0F, 1.0F));

        PartDefinition right_wing = body.addOrReplaceChild("right_wing", CubeListBuilder.create()
                .texOffs(10, 38).addBox(-1.0F, 0.0F, -2.0F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.1F))
                .texOffs(10, 30).addBox(-1.0F, 0.0F, -2.0F, 1.0F, 4.0F, 4.0F)
                .texOffs(32, 31).addBox(-1.0F, 0.0F, 2.0F, 0.0F, 4.0F, 1.0F),
            PartPose.offset(-4.0F, -1.0F, 1.0F));

        PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create()
                .texOffs(20, 30).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 4.0F)
                .texOffs(32, 16).addBox(0.0F, 0.0F, 1.0F, 0.0F, 3.0F, 4.0F),
            PartPose.offsetAndRotation(0.0F, 0.0F, 6.0F, 1.5708F, 0.0F, 0.0F));

        PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create()
                .texOffs(28, 0).addBox(-1.0F, 0.0F, -2.0F, 3.0F, 5.0F, 3.0F),
            PartPose.offset(1.0F, 19.0F, 1.0F));

        PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create()
                .texOffs(28, 8).addBox(-1.0F, 0.0F, -2.0F, 3.0F, 5.0F, 3.0F),
            PartPose.offset(-2.0F, 19.0F, 1.0F));

        return LayerDefinition.create(meshdefinition, 48, 48);
    }

    @Override
    public void setupAnim(DodoRenderState state) {
        this.head.xRot = state.xRot * Mth.DEG_TO_RAD;
        this.head.yRot = state.yRot * Mth.DEG_TO_RAD;
        if (state.isShakingHead) {
            this.head.yRot += 0.6F * Mth.sin(0.6F * state.ageInTicks);
            this.head.xRot = 0.2F;
        } else {
            this.head.zRot = 0.0F;
        }

        if (state.jumpy) {
            this.leftLeg.xRot = 0.0F;
            this.leftLeg.zRot = -0.3F;
            this.rightLeg.xRot = 0.0F;
            this.rightLeg.zRot = 0.3F;
        } else {
            this.rightLeg.xRot = Mth.cos(state.walkAnimationPos * 0.6662F) * 1.4F * state.walkAnimationSpeed;
            this.rightLeg.zRot = 0.0F;
            this.leftLeg.xRot = Mth.cos(state.walkAnimationPos * 0.6662F + Mth.PI) * 1.4F * state.walkAnimationSpeed;
            this.leftLeg.zRot = 0.0F;
        }

        float flapAngle = (Mth.sin(state.flap) + 1.0F) * state.flapSpeed;
        this.rightWing.zRot = flapAngle;
        this.leftWing.zRot = -flapAngle;
    }
}