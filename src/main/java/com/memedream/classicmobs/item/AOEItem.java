package com.memedream.classicmobs.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

import java.util.ArrayList;
import java.util.List;

public interface AOEItem {

    //AOE breaking
    static List<BlockPos> getBlocksToBeDestroyed(int range, BlockPos initalBlockPos, Player player) {
        List<BlockPos> positions = new ArrayList<>();

        BlockHitResult traceResult = player.level().clip(new ClipContext(player.getEyePosition(1f),
            (player.getEyePosition(1f).add(player.getViewVector(1f).scale(6f))),
            ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, player));
        if (traceResult.getType() == HitResult.Type.MISS) {
            return positions;
        }

        if (traceResult.getDirection() == Direction.DOWN || traceResult.getDirection() == Direction.UP) {
            for (int x = -range; x <= range; x++) {
                for (int y = -range; y <= range; y++) {
                    positions.add(new BlockPos(initalBlockPos.getX() + x, initalBlockPos.getY(), initalBlockPos.getZ() + y));
                }
            }
        }

        if (traceResult.getDirection() == Direction.NORTH || traceResult.getDirection() == Direction.SOUTH) {
            for (int x = -range; x <= range; x++) {
                for (int y = -range; y <= range; y++) {
                    positions.add(new BlockPos(initalBlockPos.getX() + x, initalBlockPos.getY() + y, initalBlockPos.getZ()));
                }
            }
        }

        if (traceResult.getDirection() == Direction.EAST || traceResult.getDirection() == Direction.WEST) {
            for (int x = -range; x <= range; x++) {
                for (int y = -range; y <= range; y++) {
                    positions.add(new BlockPos(initalBlockPos.getX(), initalBlockPos.getY() + y, initalBlockPos.getZ() + x));
                }
            }
        }

        return positions;
    }

    static boolean isValidForOutline(Level level, BlockPos pos, BlockPos origin, ItemStack stack) {
        if (pos != origin && level.getWorldBorder().isWithinBounds(pos)) {
            if (stack.getItem() instanceof ScytheItem && level.getBlockState(pos).is(BlockTags.DIRT)) {
                return true;
            }
            return canBeBroken(level, pos, stack);
        }
        return false;
    }

    static boolean isValidBlockToBreak(Level level, BlockPos pos, BlockPos origin, ItemStack stack) {
        return pos != origin && level.getWorldBorder().isWithinBounds(pos) && canBeBroken(level, pos, stack);
    }

    static boolean canBeBroken(Level level, BlockPos pos, ItemStack stack) {
        return (stack.isCorrectToolForDrops(level.getBlockState(pos)) || level.getBlockState(pos).getDestroySpeed(level, pos) == 0.0F);
    }

    static boolean playerHasBlockingItemUseIntent(UseOnContext context) {
        Player player = context.getPlayer();
        return context.getHand().equals(InteractionHand.MAIN_HAND)
            && player.getOffhandItem().has(DataComponents.BLOCKS_ATTACKS)
            && !player.isSecondaryUseActive();
    }
}
