package com.memedream.classicmobs.entity.ai.goals;

import com.memedream.classicmobs.entity.AntlionEntity;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class AntlionAttackGoal extends MeleeAttackGoal {

    public AntlionAttackGoal(AntlionEntity mob, double speedModifier, boolean followingTargetEvenIfNotSeen) {
        super(mob, speedModifier, followingTargetEvenIfNotSeen);
    }

    @Override
    public boolean canUse() {
        if (!((AntlionEntity)this.mob).canMove()) return false;
        return super.canUse();
    }
}
