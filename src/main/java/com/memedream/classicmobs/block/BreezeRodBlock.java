package com.memedream.classicmobs.block;

import com.memedream.classicmobs.block.entity.BreezeRodBlockEntity;
import com.memedream.classicmobs.init.ModBlockEntities;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RodBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import org.jspecify.annotations.Nullable;

public class BreezeRodBlock extends RodBlock implements EntityBlock {

    public static final MapCodec<BreezeRodBlock> CODEC = simpleCodec(BreezeRodBlock::new);

    @Override
    public MapCodec<BreezeRodBlock> codec() {
        return CODEC;
    }

    public BreezeRodBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.UP));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction direction = context.getClickedFace();
        BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos().relative(direction.getOpposite()));
        return blockstate.is(this) && blockstate.getValue(FACING) == direction ? this.defaultBlockState().setValue(FACING, direction.getOpposite()) : this.defaultBlockState().setValue(FACING, direction);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        Direction direction = state.getValue(FACING);
        double x = pos.getX() + 0.55D - random.nextFloat() * 0.1F;
        double y = pos.getY() + 0.55D - random.nextFloat() * 0.1F;
        double z = pos.getZ() + 0.55D - random.nextFloat() * 0.1F;
        double r = 0.4F - (random.nextFloat() + random.nextFloat()) * 0.4F;
        if (random.nextInt(5) == 0) {
            level.addParticle(
                    ParticleTypes.CLOUD,
                    x + (double)direction.getStepX() * r,
                    y + (double)direction.getStepY() * r,
                    z + (double)direction.getStepZ() * r,
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

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BreezeRodBlockEntity(pos, state);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> type) {
        return !level.isClientSide() ? BaseEntityBlock.createTickerHelper(type, ModBlockEntities.BREEZE_ROD.get(), BreezeRodBlockEntity::tick) : null;
    }
}
