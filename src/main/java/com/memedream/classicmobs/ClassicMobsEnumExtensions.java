package com.memedream.classicmobs;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.item.properties.numeric.UseDuration;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.phys.Vec2;
import net.neoforged.fml.common.asm.enumextension.EnumProxy;
import net.neoforged.neoforge.client.IArmPoseTransformer;

@SuppressWarnings("unused")
public class ClassicMobsEnumExtensions {

    /**
     * {@link net.minecraft.client.model.HumanoidModel.ArmPose}<p/>
     */
    public static final EnumProxy<HumanoidModel.ArmPose> CLASSIC_MOBS_BOLA_SWING = new EnumProxy<>(HumanoidModel.ArmPose.class, false, false, (IArmPoseTransformer) (model, state, arm) -> {
        float initialXRot = (float) Math.toRadians(165.0D);
        float initialZRot = (float) Math.toRadians(30.0D);

        float tickPercent = Math.min(1.0F, state.ticksUsingItem(arm) / 40.0F);
        float ticksUsed = state.ticksUsingItem(arm);
        float armSwing = (ticksUsed * tickPercent) / 2.0F;
        if (arm == HumanoidArm.RIGHT) {
            Vec2 movement = calculateCirclePoint(armSwing);
            model.rightArm.setRotation(-initialXRot - movement.x, 0.0F, -initialZRot + movement.y);
        } else {
            Vec2 movement = calculateCirclePoint(-armSwing);
            model.leftArm.setRotation(-initialXRot - movement.x, 0.0F, initialZRot - movement.y);
        }
    });

    private static Vec2 calculateCirclePoint(float progress) {
        double rads = Math.toRadians(progress % 360);
        return new Vec2(Mth.sin(rads * 70) / 5, Mth.cos(rads * 70) / 5);
    }
}
