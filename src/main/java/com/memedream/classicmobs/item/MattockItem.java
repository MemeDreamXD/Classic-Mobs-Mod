package com.memedream.classicmobs.item;

import com.memedream.classicmobs.init.ModTags;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
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
import org.jspecify.annotations.Nullable;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class MattockItem extends Item {

    public MattockItem(ToolMaterial material, float attackDamageBaseline, float attackSpeedBaseline, Properties properties) {
        super(properties.tool(material, ModTags.Blocks.MINEABLE_WITH_MATTOCK, attackDamageBaseline, attackSpeedBaseline, 0.0F));
    }

    @Override
    public boolean canPerformAction(ItemInstance instance, ItemAbility ability) {
        return ModItemAbilities.DEFAULT_MATTOCK_ACTIONS.contains(ability);
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        if (state.is(ModTags.Blocks.MINEABLE_WITH_MATTOCK) && state.is(BlockTags.MINEABLE_WITH_SHOVEL) && state.is(BlockTags.MINEABLE_WITH_HOE)) {
            return super.getDestroySpeed(stack, state) * 2.0F;
        } else return super.getDestroySpeed(stack, state);
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
                        context.getItemInHand().hurtAndBreak(1, player, context.getHand());
                    }
                }

                return InteractionResult.SUCCESS;
            } else {
                return InteractionResult.PASS;
            }
        }
    }

    //[VanillaCopy] of ShovelItem.useOn
    public static InteractionResult tryUseAsShovel(Level level, BlockPos pos, @Nullable Player player, BlockState state, UseOnContext context) {
        if (context.getClickedFace() != Direction.DOWN) {
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
                        context.getItemInHand().hurtAndBreak(1, player, context.getHand());
                    }
                }
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }
}
