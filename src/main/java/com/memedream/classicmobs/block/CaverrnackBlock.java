package com.memedream.classicmobs.block;

import com.memedream.classicmobs.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class CaverrnackBlock extends Block {

    public static final IntegerProperty LAYER = IntegerProperty.create("layer", 0, 3);

    public CaverrnackBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(LAYER, 0));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(LAYER, calculatePlacementGradient(context.getLevel(), context.getClickedPos()));
    }

    private static int calculatePlacementGradient(Level level, BlockPos pos) {
        BlockState belowState = level.getBlockState(pos.below());
        if (belowState.is(ModBlocks.CAVERRNACK)) {
            return Mth.clamp(belowState.getValue(LAYER) - 1, 0, 3);
        }
        return 3;
    }

    @Override
    protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess ticks, BlockPos pos, Direction directionToNeighbor, BlockPos neighborPos, BlockState neighborState, RandomSource random) {
        if (directionToNeighbor == Direction.UP) {
            if (neighborState.is(ModBlocks.CAVERRNACK)) {
                if (countCaverrnackBelow(level, pos) == 0) return state.setValue(LAYER, 3);
                return state.setValue(LAYER, Math.min(neighborState.getValue(LAYER) + 1, 3));
            } else {
                return state.setValue(LAYER, 3 - countCaverrnackBelow(level, pos));
            }
        } else if (directionToNeighbor == Direction.DOWN) {
            int above = countCaverrnackAbove(level, pos);
            int below = countCaverrnackBelow(level, pos);
            if (below == 0) {
                return state.setValue(LAYER, 3);
            }else if (below < 3 && above == 0) {
                return state.setValue(LAYER, 3 - below);
            } else {
                return state.setValue(LAYER, above);
            }
        }

        return super.updateShape(state, level, ticks, pos, directionToNeighbor, neighborPos, neighborState, random);
    }

    private static int countCaverrnackBelow(LevelReader level, BlockPos pos) {
        int amount = 0;
        do {
            amount++;
        } while (level.getBlockState(pos.below(amount)).is(ModBlocks.CAVERRNACK) && amount <= 3);
        return amount - 1;
    }

    private static int countCaverrnackAbove(LevelReader level, BlockPos pos) {
        int amount = 0;
        do {
            amount++;
        } while (level.getBlockState(pos.above(amount)).is(ModBlocks.CAVERRNACK) && amount <= 3);
        return amount - 1;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LAYER);
    }
}
