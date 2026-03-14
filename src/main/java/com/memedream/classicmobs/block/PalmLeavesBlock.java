package com.memedream.classicmobs.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.TintedParticleLeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

public class PalmLeavesBlock extends TintedParticleLeavesBlock {

    public PalmLeavesBlock(Properties properties) {
        super(0.01F, properties);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState replacedFluidState = context.getLevel().getFluidState(context.getClickedPos());
        BlockState state = this.defaultBlockState().setValue(PERSISTENT, true).setValue(WATERLOGGED, replacedFluidState.is(Fluids.WATER));
        return updateDistance(state, context.getLevel(), context.getClickedPos());
    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        level.setBlock(pos, updateDistance(state, level, pos), 3);
    }

    //[VanillaCopy] of LeavesBlock.updateDistance but check diagonals too
    private static BlockState updateDistance(BlockState state, LevelAccessor level, BlockPos pos) {
        int newDistance = 7;
        BlockPos.MutableBlockPos neighborPos = new BlockPos.MutableBlockPos();

        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                for (int z = -1; z <= 1; z++) {
                    neighborPos.setWithOffset(pos, x, y, z);
                    newDistance = Math.min(newDistance, getOptionalDistanceAt(level.getBlockState(neighborPos)).orElse(7) + 1);
                    if (newDistance == 1) {
                        break;
                    }
                }
            }
        }

        return state.setValue(DISTANCE, newDistance);
    }
}
