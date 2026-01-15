package com.memedream.classicmobs.client.model;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

public class MyrmexModel extends EntityModel<LivingEntityRenderState> {

    public MyrmexModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition create() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 20).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(32, 24).addBox(-4.0F, -13.0F, -4.0F, 8.0F, 5.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-5.0F, -2.0F, -5.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 6).addBox(2.0F, -2.0F, -5.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -2.0F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(40, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 52).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 8.0F, 4.0F, new CubeDeformation(0.3F)), PartPose.offset(0.0F, 0.0F, -2.0F));

        PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(48, 24).addBox(0.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(48, 40).addBox(0.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.5F)), PartPose.offset(4.0F, 2.0F, -2.0F));

        PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(0, 36).addBox(-4.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(32, 40).addBox(-4.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.5F)), PartPose.offset(-4.0F, 2.0F, -2.0F));

        PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(16, 36).addBox(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(1.9F, 12.0F, -2.0F));

        PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(48, 0).addBox(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.9F, 12.0F, -2.0F));

        PartDefinition right_leg_back = partdefinition.addOrReplaceChild("right_leg_back", CubeListBuilder.create().texOffs(48, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 12.0F, 3.0F));

        PartDefinition left_leg_back = partdefinition.addOrReplaceChild("left_leg_back", CubeListBuilder.create().texOffs(16, 36).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 12.0F, 3.0F));

        PartDefinition body_bottom = partdefinition.addOrReplaceChild("body_bottom", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -20.0F, -2.0F, 8.0F, 8.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, -2.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    //TODO: Needs animations. This one should be simple, it's basically a zombie with two extra legs. It'll eventually need to be able to wield genric tool items and a shield while still looking correct.
    @Override
    public void setupAnim(LivingEntityRenderState state) {

    }
}
