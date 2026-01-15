package com.memedream.classicmobs.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RodBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

public class BlazeRodBlock extends RodBlock {

    public static final MapCodec<BlazeRodBlock> CODEC = simpleCodec(BlazeRodBlock::new);

    @Override
    public MapCodec<BlazeRodBlock> codec() {
        return CODEC;
    }

    public BlazeRodBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.UP));
    }

    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction direction = context.getClickedFace();
        BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos().relative(direction.getOpposite()));
        return blockstate.is(this) && blockstate.getValue(FACING) == direction ? this.defaultBlockState().setValue(FACING, direction.getOpposite()) : this.defaultBlockState().setValue(FACING, direction);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        Direction direction = state.getValue(FACING);
        double x = pos.getX() + 0.55D - (random.nextFloat() * 0.1F);
        double y = pos.getY() + 0.55D - (random.nextFloat() * 0.1F);
        double z = pos.getZ() + 0.55D - (random.nextFloat() * 0.1F);
        double spread = 0.4F - (random.nextFloat() + random.nextFloat()) * 0.4F;
        if (random.nextInt(5) == 0) {
            level.addParticle(ParticleTypes.FLAME,
                x + direction.getStepX() * spread,
                y + direction.getStepY() * spread,
                z + direction.getStepZ() * spread,
                random.nextGaussian() * 0.005D,
                random.nextGaussian() * 0.005D,
                random.nextGaussian() * 0.005D
            );
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}
