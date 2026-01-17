package com.memedream.classicmobs.entity.ai.goals;

import com.memedream.classicmobs.entity.RocketCreeperEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Creeper;
import org.jspecify.annotations.Nullable;

import java.util.EnumSet;

public class RocketCreeperSwellGoal extends Goal {

    private final RocketCreeperEntity creeper;
    private @Nullable LivingEntity target;

    public RocketCreeperSwellGoal(RocketCreeperEntity creeper) {
        this.creeper = creeper;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        LivingEntity target = this.creeper.getTarget();
        return this.creeper.getSwellDir() > 0 || target != null && !target.isDeadOrDying() && this.creeper.distanceToSqr(target) < 9.0D;
    }

    @Override
    public void start() {
        this.creeper.getNavigation().stop();
        this.target = this.creeper.getTarget();
    }

    @Override
    public void stop() {
        this.target = null;
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    @Override
    public void tick() {
        if (this.target != null && !this.target.isDeadOrDying()) {
            if (this.creeper.distanceToSqr(this.target) > 144.0D) {
                this.creeper.setSwellDir(-1);
            } else if (!this.creeper.getSensing().hasLineOfSight(this.target)) {
                this.creeper.setSwellDir(-1);
            } else {
                this.creeper.setSwellDir(1);
            }
        } else {
            this.creeper.setSwellDir(-1);
        }
    }
}
