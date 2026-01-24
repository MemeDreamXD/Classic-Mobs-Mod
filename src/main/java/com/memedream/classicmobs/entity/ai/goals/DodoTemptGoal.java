package com.memedream.classicmobs.entity.ai.goals;

import com.memedream.classicmobs.entity.DodoEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.Nullable;

import java.util.EnumSet;
import java.util.function.Predicate;

public class DodoTemptGoal extends Goal {

    private static final TargetingConditions TEMPT_TARGETING = TargetingConditions.forNonCombat().ignoreLineOfSight();
    private final TargetingConditions followConditions;
    private final TargetingConditions jumpConditions;
    protected final DodoEntity dodo;
    protected final double speedModifier;
    protected @Nullable Player player;
    private final Predicate<ItemStack> items;

    public DodoTemptGoal(DodoEntity dodo, double speedModifier, Predicate<ItemStack> items) {
        this.dodo = dodo;
        this.speedModifier = speedModifier;
        this.items = items;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        this.followConditions = TEMPT_TARGETING.copy().selector((target, _) -> target instanceof Player targetPlayer && this.shouldFollow(targetPlayer));
        this.jumpConditions = TEMPT_TARGETING.copy().selector((target, _) -> target instanceof Player targetPlayer && this.shouldJumpFor(targetPlayer));
    }

    @Override
    public boolean canUse() {
        this.player = getServerLevel(this.dodo).getNearestPlayer(this.followConditions.range(this.dodo.getAttributeValue(Attributes.TEMPT_RANGE)), this.dodo);
        if (this.player != null) {
            return true;
        }
        this.player = getServerLevel(this.dodo).getNearestPlayer(this.jumpConditions.range(this.dodo.getAttributeValue(Attributes.TEMPT_RANGE)), this.dodo);
        if (this.player != null) {
            this.dodo.setJumpy(true);
            return true;
        }
        return false;
    }

    @Override
    public boolean canContinueToUse() {
        if (this.player != null && this.dodo.isJumpy() && !this.shouldJumpFor(this.player)) return false;
        return super.canContinueToUse();
    }

    private boolean shouldFollow(Player player) {
        return this.items.test(player.getMainHandItem()) || this.items.test(player.getOffhandItem());
    }

    private boolean shouldJumpFor(Player player) {
        return player.getInventory().contains(this.items) && !this.shouldFollow(player) && this.dodo.canFallInLove() && this.dodo.canBreed();
    }

    @Override
    public void stop() {
        this.player = null;
        this.dodo.setJumpy(false);
        this.dodo.getNavigation().stop();
    }

    @Override
    public void tick() {
        this.dodo.getLookControl().setLookAt(this.player, this.dodo.getMaxHeadYRot() + 20, this.dodo.getMaxHeadXRot());
        if (this.dodo.distanceToSqr(this.player) < 6.25D) {
            this.dodo.getNavigation().stop();
        } else if (!this.dodo.isJumpy()) {
            this.dodo.getNavigation().moveTo(this.player, this.speedModifier);
        }
    }
}
