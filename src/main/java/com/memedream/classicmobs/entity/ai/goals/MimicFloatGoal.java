package com.memedream.classicmobs.entity.ai.goals;

import com.memedream.classicmobs.entity.MimicEntity;
import net.minecraft.world.entity.ai.goal.FloatGoal;

public class MimicFloatGoal extends FloatGoal {

    private final MimicEntity mimic;

    public MimicFloatGoal(MimicEntity mimic) {
        super(mimic);
        this.mimic = mimic;
    }

    @Override
    public boolean canUse() {
        return this.mimic.getTarget() != null && super.canUse();
    }

    @Override
    public void tick() {
        super.tick();
        this.mimic.getMoveControl().setSpeed(1.2D);
    }
}
