package com.memedream.classicmobs.item;

import com.memedream.classicmobs.entity.BolaEntity;
import com.memedream.classicmobs.init.ModSounds;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.ProjectileItem;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import org.jspecify.annotations.Nullable;

public class BolaItem extends Item implements ProjectileItem {

    public BolaItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemUseAnimation getUseAnimation(ItemStack itemStack) {
        return ItemUseAnimation.BOW;
    }

    @Override
    public int getUseDuration(ItemStack itemStack, LivingEntity user) {
        return 72000;
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        player.startUsingItem(hand);
        return InteractionResult.CONSUME;
    }

    @Override
    public void onUseTick(Level level, LivingEntity entity, ItemStack stack, int ticksRemaining) {
        if (!level.isClientSide()) {
            float tickPercent = Math.min(1.0F, (stack.getUseDuration(entity) - ticksRemaining) / 40.0F);
            if ((ticksRemaining - stack.getUseDuration(entity)) % 5 == 0) {
                level.playSound(null, entity.blockPosition(), ModSounds.BOLA_WINDUP.get(), SoundSource.PLAYERS, 1.0F, tickPercent * 2.0F);
            }
        }
    }

    @Override
    public boolean releaseUsing(ItemStack stack, Level level, LivingEntity entity, int remainingTime) {
        if (!(entity instanceof Player player)) return false;
        int timeHeld = this.getUseDuration(stack, player) - remainingTime;
        float pow = getPowerForTime(timeHeld);
        if (pow > 0.2F) {
            BolaEntity bola = new BolaEntity(level, player);
            bola.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, pow, 0.4F);
            level.addFreshEntity(bola);

            stack.consume(1, player);
            level.playSound(null, player.blockPosition(), ModSounds.BOLA_THROW.get(), SoundSource.PLAYERS, 0.5F, 1.0F / (level.getRandom().nextFloat() * 0.4F + 1.2F) + pow * 0.5F);
            player.awardStat(Stats.ITEM_USED.get(this));
            return true;
        }
        return false;
    }

    public static float getPowerForTime(int timeHeld) {
        float pow = timeHeld / 40.0F;
        pow = (pow * pow + pow * 2.0F) / 3.0F;
        if (pow > 1.0F) {
            pow = 1.0F;
        }

        return pow;
    }

    @Override
    public Projectile asProjectile(Level level, Position position, ItemStack itemStack, Direction direction) {
        return new BolaEntity(level, position.x(), position.y(), position.z());
    }

    @Override
    public ProjectileItem.DispenseConfig createDispenseConfig() {
        return ProjectileItem.DispenseConfig.builder()
            .uncertainty(2.0F)
            .build();
    }

    public static class BolaAnimation implements IClientItemExtensions {
        @Override
        public HumanoidModel.@Nullable ArmPose getArmPose(LivingEntity entity, InteractionHand hand, ItemStack stack) {
            if (entity.getUsedItemHand() == hand && entity.getUseItemRemainingTicks() > 0) {
                return HumanoidModel.ArmPose.valueOf("CLASSIC_MOBS_BOLA_SWING");
            }
            return IClientItemExtensions.super.getArmPose(entity, hand, stack);
        }
    }
}
