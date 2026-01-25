package com.memedream.classicmobs.entity.ai.goals;

import com.memedream.classicmobs.entity.MimicEntity;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;

import java.util.EnumSet;

public class MimicAttackGoal extends Goal {

    private final MimicEntity mimic;
    private int timeRemaining;

    public MimicAttackGoal(MimicEntity mimic) {
        this.mimic = mimic;
        setFlags(EnumSet.of(Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        LivingEntity target = this.mimic.getTarget();

        return target instanceof Player player
            && player.isAlive()
            && player.level().getDifficulty() != Difficulty.PEACEFUL
            && !player.getAbilities().invulnerable;
    }

    @Override
    public void start() {
        this.timeRemaining = 300;
        super.start();
    }

    @Override
    public boolean canContinueToUse() {
        return this.canUse() && this.timeRemaining-- > 0;
    }

    @Override
    public void tick() {
        super.tick();

        if (this.mimic.getTarget() != null) {
            this.mimic.lookAt(this.mimic.getTarget(), 10, 10);
            this.mimic.getMoveControl().setDirection(this.mimic.getYRot(), true);
        }
    }
}
