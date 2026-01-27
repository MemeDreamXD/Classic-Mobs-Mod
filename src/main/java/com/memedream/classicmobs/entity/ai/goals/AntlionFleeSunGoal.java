package com.memedream.classicmobs.entity.ai.goals;

import com.memedream.classicmobs.entity.AntlionEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.ai.goal.FleeSunGoal;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

public class AntlionFleeSunGoal extends FleeSunGoal {
    public AntlionFleeSunGoal(AntlionEntity mob, double speedModifier) {
        super(mob, speedModifier);
    }

    @Override
    public boolean canUse() {
        if (this.mob.getTarget() != null) {
            return false;
        } else if (!this.mob.level().isBrightOutside()) {
            return false;
        } else if (!((AntlionEntity)this.mob).canMove()) {
            return false;
        } else if (!this.mob.level().canSeeSky(this.mob.blockPosition())) {
            return false;
        } else {
            return this.setWantedPos();
        }
    }

    @Override
    protected @Nullable Vec3 getHidePos() {
        RandomSource random = this.mob.getRandom();
        BlockPos pos = this.mob.blockPosition();

        for (int i = 0; i < 10; i++) {
            BlockPos randomPos = pos.offset(random.nextInt(20) - 10, random.nextInt(6) - 3, random.nextInt(20) - 10);
            if (this.mob.level().getBlockState(randomPos).is(BlockTags.SAND)) {
                return Vec3.atBottomCenterOf(randomPos);
            }
        }

        return null;
    }

    @Override
    public void stop() {
        if (this.mob.level().getBlockState(this.mob.getOnPos()).is(BlockTags.SAND)) {
            ((AntlionEntity)this.mob).setStateTo(AntlionEntity.AntlionState.PANIC_DIGGING);
        }
    }
}
