package com.memedream.classicmobs.item;

import com.memedream.classicmobs.init.ModTags;
import com.mojang.datafixers.util.Pair;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LevelEvent;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class MattockItem extends DiggerItem {

    public MattockItem(Tier tier, Properties properties) {
        super(tier, ModTags.Blocks.MINEABLE_WITH_MATTOCK, properties);
    }

    @Override
    public boolean canPerformAction(ItemStack stack, ItemAbility itemAbility) {
        return ModItemAbilities.DEFAULT_MATTOCK_ACTIONS.contains(itemAbility);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos blockpos = context.getClickedPos();
        Player player = context.getPlayer();

        if (context.isSecondaryUseActive()) {
            //attempt to use as shovel first
            InteractionResult shovelResult = tryUseAsShovel(level, blockpos, player, level.getBlockState(blockpos), context);
            //if shovel action fails, try hoe
            if (!shovelResult.consumesAction()) {
                return tryUseAsHoe(level, blockpos, player, level.getBlockState(blockpos), context);
            } else {
                return shovelResult;
            }
        } else {
            //attempt to use as hoe first
            InteractionResult hoeResult = tryUseAsHoe(level, blockpos, player, level.getBlockState(blockpos), context);
            //if hoe action fails, try shovel
            if (!hoeResult.consumesAction()) {
                return tryUseAsShovel(level, blockpos, player, level.getBlockState(blockpos), context);
            } else {
                return hoeResult;
            }
        }
    }

    //[VanillaCopy] of HoeItem.useOn
    public static InteractionResult tryUseAsHoe(Level level, BlockPos pos, @Nullable Player player, BlockState state, UseOnContext context) {
        BlockState toolModifiedState = state.getToolModifiedState(context, ItemAbilities.HOE_TILL, false);
        Pair<Predicate<UseOnContext>, Consumer<UseOnContext>> pair = toolModifiedState == null ? null : Pair.of(ctx -> true, HoeItem.changeIntoState(toolModifiedState));
        if (pair == null) {
            return InteractionResult.PASS;
        } else {
            Predicate<UseOnContext> predicate = pair.getFirst();
            Consumer<UseOnContext> consumer = pair.getSecond();
            if (predicate.test(context)) {
                level.playSound(player, pos, SoundEvents.HOE_TILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                if (!level.isClientSide()) {
                    consumer.accept(context);
                    if (player != null) {
                        context.getItemInHand().hurtAndBreak(1, player, LivingEntity.getSlotForHand(context.getHand()));
                    }
                }

                return InteractionResult.sidedSuccess(level.isClientSide);
            } else {
                return InteractionResult.PASS;
            }
        }
    }

    //[VanillaCopy] of ShovelItem.useOn
    private static InteractionResult tryUseAsShovel(Level level, BlockPos pos, @Nullable Player player, BlockState state, UseOnContext context) {
        if (context.getClickedFace() == Direction.DOWN) {
            return InteractionResult.PASS;
        } else {
            BlockState flattenedState = state.getToolModifiedState(context, ItemAbilities.SHOVEL_FLATTEN, false);
            BlockState newState;
            if (flattenedState != null && level.getBlockState(pos.above()).isAir()) {
                level.playSound(player, pos, SoundEvents.SHOVEL_FLATTEN, SoundSource.BLOCKS, 1.0F, 1.0F);
                newState = flattenedState;
            } else if ((newState = state.getToolModifiedState(context, ItemAbilities.SHOVEL_DOUSE, false)) != null) {
                if (!level.isClientSide()) {
                    level.levelEvent(null, LevelEvent.SOUND_EXTINGUISH_FIRE, pos, 0);
                }

            }

            if (newState != null) {
                if (!level.isClientSide()) {
                    level.setBlock(pos, newState, Block.UPDATE_ALL_IMMEDIATE);
                    level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, newState));
                    if (player != null) {
                        context.getItemInHand().hurtAndBreak(1, player, LivingEntity.getSlotForHand(context.getHand()));
                    }
                }
                return InteractionResult.sidedSuccess(level.isClientSide());
            }
            return InteractionResult.PASS;
        }
    }
}
