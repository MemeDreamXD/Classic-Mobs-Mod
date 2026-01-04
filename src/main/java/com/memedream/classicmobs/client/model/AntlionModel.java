package com.memedream.classicmobs.client.model;

import com.memedream.classicmobs.entity.AntlionEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class AntlionModel extends HierarchicalModel<AntlionEntity> {

    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart jaw1;
    private final ModelPart jaw2;
    private final ModelPart leg1;
    private final ModelPart leg2;
    private final ModelPart leg3;
    private final ModelPart leg4;
    private final ModelPart leg5;
    private final ModelPart leg6;

    public AntlionModel(ModelPart root) {
        this.root = root;
        this.head = root.getChild("head");
        this.jaw1 = this.head.getChild("jaw1");
        this.jaw2 = this.head.getChild("jaw2");
        this.leg1 = root.getChild("leg1");
        this.leg2 = root.getChild("leg2");
        this.leg3 = root.getChild("leg3");
        this.leg4 = root.getChild("leg4");
        this.leg5 = root.getChild("leg5");
        this.leg6 = root.getChild("leg6");
    }

    public static LayerDefinition create() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(50, 0).addBox(-4.0F, -3.0F, -7.0F, 8.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 21.0F, 1.0F));

        PartDefinition jaw2 = head.addOrReplaceChild("jaw2", CubeListBuilder.create().texOffs(32, 24).addBox(0.0F, -4.0F, -12.0F, 4.0F, 4.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(28, 40).addBox(4.0F, -4.0F, -12.0F, 2.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 3.0F, -6.0F, 0.0F, 0.6109F, 0.0F));

        PartDefinition jaw1 = head.addOrReplaceChild("jaw1", CubeListBuilder.create().texOffs(0, 24).addBox(-4.0F, -4.0F, -12.0F, 4.0F, 4.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(0, 40).addBox(-6.0F, -4.0F, -12.0F, 2.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 3.0F, -6.0F, 0.0F, -0.6109F, 0.0F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 8.0F));

        PartDefinition body_r1 = body.addOrReplaceChild("body_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -9.0F, -7.0F, 10.0F, 9.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, 0.0F, 0.0F));

        PartDefinition leg1 = partdefinition.addOrReplaceChild("leg1", CubeListBuilder.create(), PartPose.offset(-5.0F, 19.0F, 14.0F));

        PartDefinition leg1_r1 = leg1.addOrReplaceChild("leg1_r1", CubeListBuilder.create().texOffs(50, 13).addBox(-1.0F, -4.0F, -1.0F, 10.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, 3.0F, 0.0F, 0.0F, 0.0873F, -0.3491F));

        PartDefinition leg2 = partdefinition.addOrReplaceChild("leg2", CubeListBuilder.create(), PartPose.offset(5.0F, 19.0F, 14.0F));

        PartDefinition leg2_r1 = leg2.addOrReplaceChild("leg2_r1", CubeListBuilder.create().texOffs(56, 40).addBox(-9.0F, -4.0F, -1.0F, 10.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.0F, 3.0F, 0.0F, 0.0F, -0.0873F, 0.3491F));

        PartDefinition leg3 = partdefinition.addOrReplaceChild("leg3", CubeListBuilder.create(), PartPose.offset(-5.0F, 20.0F, 9.0F));

        PartDefinition leg3_r1 = leg3.addOrReplaceChild("leg3_r1", CubeListBuilder.create().texOffs(50, 13).addBox(-1.0F, -4.0F, -0.5F, 10.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, 2.0F, -1.0F, 0.0F, 0.0F, -0.2182F));

        PartDefinition leg4 = partdefinition.addOrReplaceChild("leg4", CubeListBuilder.create(), PartPose.offset(5.0F, 20.0F, 9.0F));

        PartDefinition leg4_r1 = leg4.addOrReplaceChild("leg4_r1", CubeListBuilder.create().texOffs(56, 40).addBox(-9.0F, -4.0F, -0.5F, 10.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.0F, 2.0F, -1.0F, 0.0F, 0.0F, 0.2182F));

        PartDefinition leg5 = partdefinition.addOrReplaceChild("leg5", CubeListBuilder.create(), PartPose.offset(-5.0F, 21.0F, 4.0F));

        PartDefinition leg5_r1 = leg5.addOrReplaceChild("leg5_r1", CubeListBuilder.create().texOffs(50, 13).addBox(-1.0F, -4.0F, -1.0F, 10.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, 1.0F, -1.0F, 0.0F, 0.0F, -0.0873F));

        PartDefinition leg6 = partdefinition.addOrReplaceChild("leg6", CubeListBuilder.create(), PartPose.offset(5.0F, 21.0F, 4.0F));

        PartDefinition leg6_r1 = leg6.addOrReplaceChild("leg6_r1", CubeListBuilder.create().texOffs(56, 40).addBox(-9.0F, -4.0F, -1.0F, 10.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.0F, 1.0F, -1.0F, 0.0F, 0.0F, 0.0873F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public ModelPart root() {
        return this.root;
    }

    //TODO: Finish WIP Animations. Not sure what kind of math it would need to fix the jaws, they're placeholders for now. Leg animations use spider math but look fine since the model is so different, idm it.
    @Override
    public void setupAnim(AntlionEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.yRot = netHeadYaw * (float) (Math.PI / 180.0);
        this.head.xRot = headPitch * (float) (Math.PI / 180.0);
        this.jaw1.yRot = -0.7F * Mth.triangleWave(limbSwing, 14.0F) * limbSwingAmount;
        this.jaw2.yRot = 0.7F * Mth.triangleWave(limbSwing, 14.0F) * limbSwingAmount;
        this.leg1.zRot = (float) (-Math.PI / 4);
        this.leg2.zRot = (float) (Math.PI / 4);
        this.leg3.zRot = -0.58119464F;
        this.leg4.zRot = 0.58119464F;
        this.leg5.zRot = -0.58119464F;
        this.leg6.zRot = 0.58119464F;
        this.leg1.yRot = (float) (Math.PI / 4);
        this.leg2.yRot = (float) (-Math.PI / 4);
        this.leg3.yRot = (float) (Math.PI / 8);
        this.leg4.yRot = (float) (-Math.PI / 8);
        this.leg5.yRot = (float) (-Math.PI / 8);
        this.leg6.yRot = (float) (Math.PI / 8);
        float f1 = -(Mth.cos(limbSwing * 0.6662F * 2.0F + 0.0F) * 0.4F) * limbSwingAmount;
        float f2 = -(Mth.cos(limbSwing * 0.6662F * 2.0F + (float) Math.PI) * 0.4F) * limbSwingAmount;
        float f3 = -(Mth.cos(limbSwing * 0.6662F * 2.0F + (float) (Math.PI / 2)) * 0.4F) * limbSwingAmount;
        float f4 = -(Mth.cos(limbSwing * 0.6662F * 2.0F + (float) (Math.PI * 3.0 / 2.0)) * 0.4F) * limbSwingAmount;
        float f5 = Math.abs(Mth.sin(limbSwing * 0.6662F + 0.0F) * 0.4F) * limbSwingAmount;
        float f6 = Math.abs(Mth.sin(limbSwing * 0.6662F + (float) Math.PI) * 0.4F) * limbSwingAmount;
        this.leg1.yRot += f1;
        this.leg2.yRot += -f1;
        this.leg3.yRot += f2;
        this.leg4.yRot += -f2;
        this.leg5.yRot += f3;
        this.leg6.yRot += -f3;
        this.leg1.zRot += f4;
        this.leg2.zRot += -f4;
        this.leg3.zRot += f5;
        this.leg4.zRot += -f5;
        this.leg5.zRot += f6;
        this.leg6.zRot += -f6;
    }
}
