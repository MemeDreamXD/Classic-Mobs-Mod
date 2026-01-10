package com.memedream.classicmobs.item;

import com.memedream.classicmobs.data.tags.BlockTagGen;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LevelEvent;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Optional;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class PickaxeAxeItem extends DiggerItem {

    public PickaxeAxeItem(Tier tier, Properties properties) {
        super(tier, BlockTagGen.MINEABLE_WITH_PICKAXE_AXE, properties);
    }

    @Override
    public boolean canPerformAction(ItemStack stack, ItemAbility itemAbility) {
        return ModItemAbilities.DEFAULT_PICKAXE_AXE_ACTIONS.contains(itemAbility);
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        if (state.is(BlockTagGen.MINEABLE_WITH_PICKAXE_AXE) && state.is(BlockTags.MINEABLE_WITH_PICKAXE) && state.is(BlockTags.MINEABLE_WITH_AXE)) {
            return super.getDestroySpeed(stack, state) * 2.0F;
        } else return super.getDestroySpeed(stack, state);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos blockpos = context.getClickedPos();
        Player player = context.getPlayer();
        if (this.playerHasShieldUseIntent(context)) return InteractionResult.PASS;
        else return tryUseAsAxe(level, blockpos, player, level.getBlockState(blockpos), context);
    }

    private boolean playerHasShieldUseIntent(UseOnContext context) {
        Player player = context.getPlayer();
        if (player == null) return false;
        return context.getHand().equals(InteractionHand.MAIN_HAND) && player.getOffhandItem().canPerformAction(ItemAbilities.SHIELD_BLOCK) && !player.isSecondaryUseActive();
    }

    //"light" copy of AxeItem.useOn and AxeItem.evaluateNewBlockState.
    //rather than returning when hitting an optional that works, we keep assigning action to a different tool state until it succeeds.
    //once one does succeed, we then perform the actual block change logic
    private static InteractionResult tryUseAsAxe(Level level, BlockPos pos, @Nullable Player player, BlockState state, UseOnContext context) {
        Optional<BlockState> action = Optional.ofNullable(state.getToolModifiedState(context, ItemAbilities.AXE_STRIP, false));
        if (action.isPresent()) {
            level.playSound(player, pos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0F, 1.0F);
        } else {
            action = Optional.ofNullable(state.getToolModifiedState(context, ItemAbilities.AXE_SCRAPE, false));
            if (action.isPresent()) {
                level.playSound(player, pos, SoundEvents.AXE_SCRAPE, SoundSource.BLOCKS, 1.0F, 1.0F);
                level.levelEvent(player, LevelEvent.PARTICLES_SCRAPE, pos, 0);
            } else {
                action = Optional.ofNullable(state.getToolModifiedState(context, ItemAbilities.AXE_WAX_OFF, false));
                if (action.isPresent()) {
                    level.playSound(player, pos, SoundEvents.AXE_WAX_OFF, SoundSource.BLOCKS, 1.0F, 1.0F);
                    level.levelEvent(player, LevelEvent.PARTICLES_WAX_OFF, pos, 0);
                }
            }
        }

        if (action.isPresent()) {
            ItemStack itemstack = context.getItemInHand();
            if (player instanceof ServerPlayer) {
                CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer)player, pos, itemstack);
            }

            level.setBlock(pos, action.get(), Block.UPDATE_ALL_IMMEDIATE);
            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, action.get()));
            if (player != null) {
                itemstack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(context.getHand()));
            }

            return InteractionResult.sidedSuccess(level.isClientSide());
        }
        return InteractionResult.PASS;
    }
}
