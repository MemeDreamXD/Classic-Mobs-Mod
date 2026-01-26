package com.memedream.classicmobs.client.model;

import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.util.Unit;

public class BoundBolaModel extends Model<Unit> {

    public BoundBolaModel(ModelPart root) {
        super(root, RenderTypes::entityCutoutNoCull);
    }

    public static LayerDefinition create() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create()
                .texOffs(4, 20).addBox(3.5F, -1.5F, 3.5F, 1.0F, 1.0F, 1.0F)
                .texOffs(0, 3).addBox(-6.0F, -3.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(-0.0009F))
                .texOffs(11, 21).addBox(5.5F, -2.25F, -0.5F, 2.0F, 0.0F, 1.0F)
                .texOffs(0, 10).addBox(-4.0F, -2.0F, -4.0F, 8.0F, 2.0F, 8.0F),
            PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition cube_r1 = bb_main.addOrReplaceChild("cube_r1", CubeListBuilder.create()
                .texOffs(11, 21).addBox(-0.5F, -0.25F, -0.5F, 2.0F, 0.0F, 1.0F),
            PartPose.offsetAndRotation(-4.0F, -1.0F, -4.0F, 0.0F, 1.5708F, 0.0F));

        PartDefinition cube_r2 = bb_main.addOrReplaceChild("cube_r2", CubeListBuilder.create()
                .texOffs(8, 20).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F),
            PartPose.offsetAndRotation(-4.0F, -1.0F, -4.0F, 0.0F, 0.7854F, 0.0F));

        PartDefinition cube_r3 = bb_main.addOrReplaceChild("cube_r3", CubeListBuilder.create()
                .texOffs(0, 0).addBox(-4.0F, -2.0F, -4.0F, 8.0F, 2.0F, 8.0F),
            PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        PartDefinition cube_r4 = bb_main.addOrReplaceChild("cube_r4", CubeListBuilder.create()
                .texOffs(8, 20).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F),
            PartPose.offsetAndRotation(6.0F, -2.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        PartDefinition cube_r5 = bb_main.addOrReplaceChild("cube_r5", CubeListBuilder.create()
                .texOffs(0, 0).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 1.0F, 2.0F),
            PartPose.offsetAndRotation(-5.0F, 1.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        return LayerDefinition.create(meshdefinition, 32, 22);
    }
}