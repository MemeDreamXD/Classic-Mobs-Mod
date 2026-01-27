package com.memedream.classicmobs.entity.ai.goals;

import com.memedream.classicmobs.entity.AntlionEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.level.LevelReader;

public class AntlionBurrowGoal extends MoveToBlockGoal {

    public AntlionBurrowGoal(AntlionEntity mob, double speedModifier) {
        super(mob, speedModifier, 8);
    }

    @Override
    public boolean canUse() {
        if (this.mob.getTarget() != null) {
            return false;
        } else if (this.mob.level().isBrightOutside()) {
            return false;
        } else if (!((AntlionEntity) this.mob).canMove()) {
            return false;
        } else if (((AntlionEntity) this.mob).buryCooldown > 0) {
            return false;
        }
        return super.canUse();
    }

    @Override
    public boolean canContinueToUse() {
        if (this.mob.getTarget() != null) {
            return false;
        } else if (this.mob.level().isBrightOutside()) {
            return false;
        } else if (!((AntlionEntity) this.mob).canMove()) {
            return false;
        } else if (((AntlionEntity) this.mob).buryCooldown > 0) {
            return false;
        }
        return super.canContinueToUse();
    }

    @Override
    protected boolean isValidTarget(LevelReader level, BlockPos pos) {
        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                BlockPos checkPos = pos.offset(x, 0, z);
                if (!level.getBlockState(checkPos).is(BlockTags.SAND) || !level.isEmptyBlock(checkPos.above())) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void tick() {
        super.tick();
        if (this.isReachedTarget()) {
            ((AntlionEntity) this.mob).setStateTo(AntlionEntity.AntlionState.DIGGING);
        }
    }
}
