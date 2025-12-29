package com.memedream.classicmobs.client.model;

import com.memedream.classicmobs.entity.AntlionEntity;
import com.memedream.classicmobs.entity.HagEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class HagModel extends HierarchicalModel<HagEntity> {

    private final ModelPart root;

    public HagModel(ModelPart root) {
        this.root = root;
    }

    public static LayerDefinition create() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition arm_left = partdefinition.addOrReplaceChild("arm_left", CubeListBuilder.create().texOffs(48, 31).addBox(-3.0F, -4.0F, -1.0F, 4.0F, 16.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(9.0F, 10.0F, -1.0F));

        PartDefinition arm_right = partdefinition.addOrReplaceChild("arm_right", CubeListBuilder.create().texOffs(48, 51).addBox(-3.0F, -4.0F, -1.0F, 4.0F, 16.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.0F, 10.0F, -1.0F));

        PartDefinition leg_left = partdefinition.addOrReplaceChild("leg_left", CubeListBuilder.create().texOffs(0, 52).addBox(-3.0F, -6.0F, -3.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 22.0F, 1.0F));

        PartDefinition leg_right = partdefinition.addOrReplaceChild("leg_right", CubeListBuilder.create().texOffs(16, 52).addBox(-3.0F, -6.0F, -3.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 22.0F, 1.0F));

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(48, 0).addBox(-4.0F, -19.0F, -8.0F, 8.0F, 8.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(32, 52).addBox(-1.5F, -18.0F, -9.0F, 3.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(48, 14).addBox(-4.0F, -19.0F, -6.0F, 8.0F, 13.0F, 4.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, 24.0F, -4.0F));

        PartDefinition tooth5_r1 = head.addOrReplaceChild("tooth5_r1", CubeListBuilder.create().texOffs(40, 53).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -12.0F, -8.0F, 0.1309F, 0.0F, 0.0F));

        PartDefinition tooth4_r1 = head.addOrReplaceChild("tooth4_r1", CubeListBuilder.create().texOffs(46, 52).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -14.0F, -8.0F, -0.3491F, 0.0F, 0.0F));

        PartDefinition tooth3_r1 = head.addOrReplaceChild("tooth3_r1", CubeListBuilder.create().texOffs(44, 52).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -14.0F, -8.0F, -0.0873F, 0.0F, 0.0F));

        PartDefinition tooth2_r1 = head.addOrReplaceChild("tooth2_r1", CubeListBuilder.create().texOffs(42, 52).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -12.0F, -8.0F, 0.6545F, 0.0F, 0.0F));

        PartDefinition tooth1_r1 = head.addOrReplaceChild("tooth1_r1", CubeListBuilder.create().texOffs(40, 52).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -12.0F, -8.0F, 0.2182F, 0.0F, 0.0F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 28).addBox(-6.0F, -20.0F, -2.0F, 12.0F, 12.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-6.0F, -20.0F, -2.0F, 12.0F, 16.0F, 12.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, 24.0F, -4.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public ModelPart root() {
        return this.root;
    }

    //TODO: Needs animations. I imagine it would shamble about similarly to an iron golem. Perhaps even have some hunchback? Not sure.
    @Override
    public void setupAnim(HagEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }
}
