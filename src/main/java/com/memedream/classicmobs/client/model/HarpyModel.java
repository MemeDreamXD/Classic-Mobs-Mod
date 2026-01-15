package com.memedream.classicmobs.client.model;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

public class HarpyModel extends EntityModel<LivingEntityRenderState> {

    public HarpyModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition create() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(36, 0).addBox(-4.0F, -4.0F, -8.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(36, 16).addBox(-4.0F, -4.0F, -8.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, 3.0F, -1.0F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-7.0F, -15.0F, -1.0F, 10.0F, 15.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(0, 23).addBox(-7.0F, -15.0F, -1.0F, 10.0F, 9.0F, 8.0F, new CubeDeformation(0.6F)), PartPose.offset(2.0F, 16.0F, -3.0F));

        PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(18, 40).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(54, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.3F)), PartPose.offset(3.0F, 16.0F, 0.0F));

        PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(18, 52).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(54, 40).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.3F)), PartPose.offset(-3.0F, 16.0F, 0.0F));

        PartDefinition left_wing = partdefinition.addOrReplaceChild("left_wing", CubeListBuilder.create().texOffs(36, 32).addBox(0.0F, 0.0F, -4.0F, 1.0F, 12.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(34, 52).addBox(1.0F, 12.0F, -4.0F, 0.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, 2.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

        PartDefinition right_wing = partdefinition.addOrReplaceChild("right_wing", CubeListBuilder.create().texOffs(0, 40).addBox(-1.0F, 0.0F, -4.0F, 1.0F, 12.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(50, 52).addBox(-1.0F, 12.0F, -4.0F, 0.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 2.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

        return LayerDefinition.create(meshdefinition, 70, 64);
    }

    //TODO: Needs animations. Should be able to both fly like a parrot and glide like a phantom.
    @Override
    public void setupAnim(LivingEntityRenderState state) {

    }
}
