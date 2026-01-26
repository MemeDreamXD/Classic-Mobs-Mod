package com.memedream.classicmobs.client.model;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.entity.state.EntityRenderState;

public class FlyingBolaModel extends EntityModel<EntityRenderState> {

    public FlyingBolaModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition create() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition center = partdefinition.addOrReplaceChild("center", CubeListBuilder.create()
                .texOffs(14, 0).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(-0.0009F)),
            PartPose.offset(0.0F, 25.0F, 0.0F));

        PartDefinition center_top_r1 = center.addOrReplaceChild("center_top_r1", CubeListBuilder.create()
                .texOffs(14, 3).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 1.0F, 2.0F),
            PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        PartDefinition arm1 = partdefinition.addOrReplaceChild("arm1", CubeListBuilder.create()
                .texOffs(-2, 0).addBox(-1.0F, -2.0F, -1.5F, 7.0F, 0.0F, 2.0F),
            PartPose.offset(1.0F, 25.0F, 0.0F));

        PartDefinition arm1_stone_r1 = arm1.addOrReplaceChild("arm1_stone_r1", CubeListBuilder.create()
                .texOffs(22, 1).addBox(0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 1.0F),
            PartPose.offsetAndRotation(6.0F, -2.0F, -1.0F, 0.0F, -0.7854F, 0.0F));

        PartDefinition arm3 = partdefinition.addOrReplaceChild("arm3", CubeListBuilder.create()
                .texOffs(22, 4).addBox(-5.5F, -0.5F, 4.5F, 1.0F, 1.0F, 1.0F),
            PartPose.offset(0.0F, 23.0F, 0.0F));

        PartDefinition arm3_c_top_r1 = arm3.addOrReplaceChild("arm3_c_top_r1", CubeListBuilder.create()
                .texOffs(25, 5).addBox(-0.5F, -0.5F, -0.5F, 2.0F, 0.0F, 1.0F),
            PartPose.offsetAndRotation(-5.0F, 0.0F, 5.0F, 0.0F, -0.7854F, 0.0F));

        PartDefinition arm3_string_r1 = arm3.addOrReplaceChild("arm3_string_r1", CubeListBuilder.create()
                .texOffs(-2, 2).addBox(-7.0F, 0.0F, -1.5F, 7.0F, 0.0F, 2.0F),
            PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));

        PartDefinition arm2 = partdefinition.addOrReplaceChild("arm2", CubeListBuilder.create()
                .texOffs(22, 4).addBox(-5.5F, -0.5F, -5.5F, 1.0F, 1.0F, 1.0F),
            PartPose.offset(0.0F, 23.0F, 0.0F));

        PartDefinition arm2_c_top_r1 = arm2.addOrReplaceChild("arm2_c_top_r1", CubeListBuilder.create()
                .texOffs(25, 5).addBox(-0.5F, -0.5F, -0.5F, 2.0F, 0.0F, 1.0F),
            PartPose.offsetAndRotation(-5.0F, 0.0F, -5.0F, 0.0F, 0.7854F, 0.0F));

        PartDefinition arm2_string_r1 = arm2.addOrReplaceChild("arm2_string_r1", CubeListBuilder.create()
                .texOffs(-2, 4).addBox(-7.0F, 0.0F, -0.5F, 7.0F, 0.0F, 2.0F),
            PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        return LayerDefinition.create(meshdefinition, 32, 16);
    }
}