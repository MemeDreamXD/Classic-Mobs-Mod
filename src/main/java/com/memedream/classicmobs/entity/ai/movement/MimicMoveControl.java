package com.memedream.classicmobs.entity.ai.movement;

import com.memedream.classicmobs.entity.MimicEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;

public class MimicMoveControl extends MoveControl {

    private final MimicEntity mimic;
    private float rotationDegrees;
    private int jumpDelay;

    public MimicMoveControl(MimicEntity mimic) {
        super(mimic);
        this.mimic = mimic;
        this.rotationDegrees = 180 * mimic.getYRot() / Mth.PI;
        this.jumpDelay = mimic.getRandom().nextInt(320) + 640;
    }

    public void setDirection(float rotation, boolean shouldJump) {
        this.rotationDegrees = rotation;
        if (shouldJump && this.jumpDelay > 10) {
            this.jumpDelay = 10;
        }
    }

    public void setSpeed(double speed) {
        this.speedModifier = speed;
        this.operation = Operation.MOVE_TO;
    }

    @Override
    public void tick() {
        this.mimic.yHeadRot = this.mimic.yBodyRot = this.rotlerp(this.mimic.getYRot(), this.rotationDegrees, 90);
        this.mimic.setYRot(this.mimic.yHeadRot);
        if (this.operation != Operation.MOVE_TO) {
            this.mimic.setZza(0);
        } else {
            this.operation = Operation.WAIT;
            if (this.mimic.onGround()) {
                this.mimic.setSpeed((float) (this.mimic.getAttributeValue(Attributes.MOVEMENT_SPEED) * this.speedModifier));
                if (this.jumpDelay-- > 0) {
                    this.mimic.xxa = this.mimic.zza = 0;
                    this.mimic.setSpeed(0);
                } else {
                    this.jumpDelay = this.mimic.getRandom().nextInt(320) + 640;

                    this.mimic.getJumpControl().jump();
                    this.mimic.playSound(SoundEvents.CHEST_OPEN, 1.0F, this.mimic.getVoicePitch());
                }
            } else {
                this.mimic.setSpeed((float) (this.mimic.getAttributeValue(Attributes.MOVEMENT_SPEED) * this.speedModifier));
            }
        }
    }
}