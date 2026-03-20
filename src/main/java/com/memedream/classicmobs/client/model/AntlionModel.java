package com.memedream.classicmobs.client.model;

import com.memedream.classicmobs.client.state.AntlionRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class AntlionModel extends EntityModel<AntlionRenderState> {

    private final ModelPart head;
    private final ModelPart jaw1;
    private final ModelPart jaw2;
    private final ModelPart leg1;
    private final ModelPart leg2;
    private final ModelPart leg3;
    private final ModelPart leg4;
    private final ModelPart leg5;
    private final ModelPart leg6;
    private final ModelPart[] legs;

    public AntlionModel(ModelPart root) {
        super(root);
        this.head = root.getChild("head");
        this.jaw1 = this.head.getChild("jaw1");
        this.jaw2 = this.head.getChild("jaw2");
        this.leg1 = root.getChild("leg1");
        this.leg2 = root.getChild("leg2");
        this.leg3 = root.getChild("leg3");
        this.leg4 = root.getChild("leg4");
        this.leg5 = root.getChild("leg5");
        this.leg6 = root.getChild("leg6");
        this.legs = new ModelPart[]{this.leg1, this.leg2, this.leg3, this.leg4, this.leg5, this.leg6};
    }

    public static LayerDefinition create() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(50, 0).addBox(-4.0F, -3.0F, -7.0F, 8.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 21.0F, 1.0F));

        PartDefinition jaw2 = head.addOrReplaceChild("jaw2", CubeListBuilder.create().texOffs(0, 24).mirror().addBox(0.0F, -4.0F, -12.0F, 4.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)).mirror(false)
            .texOffs(32, 24).mirror().addBox(4.0F, -4.0F, -12.0F, 2.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.0F, 3.0F, -6.0F, 0.0F, 0.6109F, 0.0F));

        PartDefinition jaw1 = head.addOrReplaceChild("jaw1", CubeListBuilder.create().texOffs(0, 24).addBox(-4.0F, -4.0F, -12.0F, 4.0F, 4.0F, 12.0F, new CubeDeformation(0.0F))
            .texOffs(32, 24).addBox(-6.0F, -4.0F, -12.0F, 2.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 3.0F, -6.0F, 0.0F, -0.6109F, 0.0F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 8.0F));

        PartDefinition body_r1 = body.addOrReplaceChild("body_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -9.0F, -7.0F, 10.0F, 9.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, 0.0F, 0.0F));

        PartDefinition leg1 = partdefinition.addOrReplaceChild("leg1", CubeListBuilder.create(), PartPose.offset(-5.0F, 19.0F, 14.0F));

        PartDefinition leg1_r1 = leg1.addOrReplaceChild("leg1_r1", CubeListBuilder.create().texOffs(50, 16).addBox(-1.0F, -4.0F, -1.0F, 10.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, 3.0F, 0.0F, 0.0F, 0.0873F, -0.3491F));

        PartDefinition leg2 = partdefinition.addOrReplaceChild("leg2", CubeListBuilder.create(), PartPose.offset(5.0F, 19.0F, 14.0F));

        PartDefinition leg2_r1 = leg2.addOrReplaceChild("leg2_r1", CubeListBuilder.create().texOffs(50, 16).mirror().addBox(-9.0F, -4.0F, -1.0F, 10.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(8.0F, 3.0F, 0.0F, 0.0F, -0.0873F, 0.3491F));

        PartDefinition leg3 = partdefinition.addOrReplaceChild("leg3", CubeListBuilder.create(), PartPose.offset(-5.0F, 20.0F, 9.0F));

        PartDefinition leg3_r1 = leg3.addOrReplaceChild("leg3_r1", CubeListBuilder.create().texOffs(50, 16).addBox(-1.0F, -4.0F, -0.5F, 10.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, 2.0F, -1.0F, 0.0F, 0.0F, -0.2182F));

        PartDefinition leg4 = partdefinition.addOrReplaceChild("leg4", CubeListBuilder.create(), PartPose.offset(5.0F, 20.0F, 9.0F));

        PartDefinition leg4_r1 = leg4.addOrReplaceChild("leg4_r1", CubeListBuilder.create().texOffs(50, 16).mirror().addBox(-9.0F, -4.0F, -0.5F, 10.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(8.0F, 2.0F, -1.0F, 0.0F, 0.0F, 0.2182F));

        PartDefinition leg5 = partdefinition.addOrReplaceChild("leg5", CubeListBuilder.create(), PartPose.offset(-5.0F, 21.0F, 4.0F));

        PartDefinition leg5_r1 = leg5.addOrReplaceChild("leg5_r1", CubeListBuilder.create().texOffs(50, 16).addBox(-1.0F, -4.0F, -1.0F, 10.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, 1.0F, -1.0F, 0.0F, 0.0F, -0.0873F));

        PartDefinition leg6 = partdefinition.addOrReplaceChild("leg6", CubeListBuilder.create(), PartPose.offset(5.0F, 21.0F, 4.0F));

        PartDefinition leg6_r1 = leg6.addOrReplaceChild("leg6_r1", CubeListBuilder.create().texOffs(50, 16).mirror().addBox(-9.0F, -4.0F, -1.0F, 10.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(8.0F, 1.0F, -1.0F, 0.0F, 0.0F, 0.0873F));

        return LayerDefinition.create(meshdefinition, 80, 48);
    }

    @Override
    public void setupAnim(AntlionRenderState state) {
        this.head.yRot = state.yRot * Mth.DEG_TO_RAD;
        this.head.xRot = state.xRot * Mth.DEG_TO_RAD;
        if (!state.underground) {
            if (state.walkAnimationSpeed <= 0.1F && state.ageInTicks % 100 > 0 && state.ageInTicks % 100 < 14) {
                this.jaw1.yRot = Math.min(0.0F, Mth.triangleWave(state.ageInTicks % 100, 7.0F));
                this.jaw2.yRot = Math.max(0.0F, -Mth.triangleWave(state.ageInTicks % 100, 7.0F));
            } else {
                this.jaw1.yRot = Math.min(0.0F, (Mth.cos(state.walkAnimationPos) * 0.6F) * state.walkAnimationSpeed);
                this.jaw2.yRot = Math.max(0.0F, -(Mth.cos(state.walkAnimationPos) * 0.6F) * state.walkAnimationSpeed);
            }
        }  else if (state.attackTimer > 0) {
            this.jaw1.yRot = Math.min(0.0F, Mth.triangleWave(state.attackTimer % 100, 7.0F));
            this.jaw2.yRot = Math.max(0.0F, -Mth.triangleWave(state.attackTimer % 100, 7.0F));
        } else {
            this.jaw1.yRot = 0.2F * Mth.sin(state.ageInTicks / 10) / 2 - 0.4F;
            this.jaw2.yRot = -0.2F * Mth.sin(state.ageInTicks / 10) / 2 + 0.4F;
        }

        for (ModelPart leg : this.legs) {
            leg.visible = !state.underground;
        }

        this.leg1.zRot = -Mth.PI / 4;
        this.leg2.zRot = Mth.PI / 4;
        this.leg3.zRot = -0.58119464F;
        this.leg4.zRot = 0.58119464F;
        this.leg5.zRot = -0.58119464F;
        this.leg6.zRot = 0.58119464F;
        this.leg1.yRot = Mth.PI / 4;
        this.leg2.yRot = -Mth.PI / 4;
        this.leg3.yRot = Mth.PI / 8;
        this.leg4.yRot = -Mth.PI / 8;
        this.leg5.yRot = -Mth.PI / 8;
        this.leg6.yRot = Mth.PI / 8;
        float f1 = -(Mth.cos(state.walkAnimationPos * 0.6662F * 2.0F + 0.0F) * 0.4F) * state.walkAnimationSpeed;
        float f2 = -(Mth.cos(state.walkAnimationPos * 0.6662F * 2.0F + Mth.PI) * 0.4F) * state.walkAnimationSpeed;
        float f3 = -(Mth.cos(state.walkAnimationPos * 0.6662F * 2.0F + (Mth.PI / 2)) * 0.4F) * state.walkAnimationSpeed;
        float f4 = -(Mth.cos(state.walkAnimationPos * 0.6662F * 2.0F + (Mth.PI * 3.0F / 2.0F)) * 0.4F) * state.walkAnimationSpeed;
        float f5 = Mth.abs(Mth.sin(state.walkAnimationPos * 0.6662F + 0.0F) * 0.4F) * state.walkAnimationSpeed;
        float f6 = Mth.abs(Mth.sin(state.walkAnimationPos * 0.6662F + Mth.PI) * 0.4F) * state.walkAnimationSpeed;
        this.leg1.yRot += f1;
        this.leg2.yRot -= f1;
        this.leg3.yRot += f2;
        this.leg4.yRot -= f2;
        this.leg5.yRot += f3;
        this.leg6.yRot -= f3;
        this.leg1.zRot += f4;
        this.leg2.zRot -= f4;
        this.leg3.zRot += f5;
        this.leg4.zRot -= f5;
        this.leg5.zRot += f6;
        this.leg6.zRot -= f6;
    }
}
