package com.memedream.classicmobs.client.model;

import com.memedream.classicmobs.client.state.MimicRenderState;
import com.memedream.classicmobs.entity.MimicEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class MimicModel extends EntityModel<MimicRenderState> {

    private final ModelPart lid;
    private final ModelPart base;

	public MimicModel(ModelPart root) {
        super(root);
		this.lid = root.getChild("lid");
        this.base = root.getChild("base");
	}

	public static LayerDefinition create() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		partdefinition.addOrReplaceChild("lid", CubeListBuilder.create().texOffs(0, 24).addBox(-7.0F, -5.0F, -14.0F, 14.0F, 5.0F, 14.0F)
		.texOffs(0, 43).addBox(-1.0F, -2.0F, -15.0F, 2.0F, 4.0F, 1.0F)
		.texOffs(-14, 50).addBox(-7.0F, -1.02F, -14.0F, 14.0F, 0.0F, 14.0F)
		.texOffs(-5, 33).addBox(-2.5F, -1.01F, -9.5F, 5.0F, 0.0F, 5.0F)
		.texOffs(11, 46).addBox(-5.0F, -0.5F, -12.0F, 10.0F, 3.0F, 0.0F)
		.texOffs(31, 37).addBox(-5.0F, -0.5F, -12.0F, 0.0F, 3.0F, 6.0F)
		.texOffs(31, 37).addBox(5.0F, -0.5F, -12.0F, 0.0F, 3.0F, 6.0F), PartPose.offset(0.0F, 15.0F, 7.0F));

        partdefinition.addOrReplaceChild("base", CubeListBuilder.create().texOffs(0, 0).addBox(-7.0F, 0.0F, -14.0F, 14.0F, 10.0F, 14.0F)
            .texOffs(11, 43).addBox(-5.0F, -2.5F, -12.0F, 10.0F, 3.0F, 0.0F)
            .texOffs(31, 40).addBox(-5.0F, -2.5F, -12.0F, 0.0F, 3.0F, 6.0F)
            .texOffs(31, 40).addBox(5.0F, -2.5F, -12.0F, 0.0F, 3.0F, 6.0F), PartPose.offset(0.0F, 14.0F, 7.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

    @Override
    public void setupAnim(MimicRenderState state) {
        if (state.ticksInAir > 0) {
            this.lid.xRot = Math.max(-45, (state.ticksInAir - 1 + state.partialTick) * -6) * 0.0174533F;
            this.base.xRot = Math.min(25, (state.ticksInAir - 1 + state.partialTick) * 3) * 0.0174533F;
        } else {
            this.lid.xRot = 0;
            this.base.xRot = 0;
        }
    }
}