package com.memedream.classicmobs.entity.ai.goals;

import com.memedream.classicmobs.entity.MimicEntity;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

public class MimicHopGoal extends Goal {

    private final MimicEntity mimic;

    public MimicHopGoal(MimicEntity mimic) {
        this.mimic = mimic;
        this.setFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        return !this.mimic.isPassenger();
    }

    @Override
    public void tick() {
        this.mimic.getMoveControl().setSpeed(1.0D);
    }
}
