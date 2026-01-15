package com.memedream.classicmobs.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SpiderEyeBlock extends Block {
    public static final MapCodec<SpiderEyeBlock> CODEC = simpleCodec(SpiderEyeBlock::new);
    private static final VoxelShape SHAPE = Block.box(5.0, 0.0, 5.0, 11.0, 6.0, 11.0);


    public SpiderEyeBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }
//TODO: Add method to begin fermenting if right clicked with a sugar/brown mushroom in main/offhand

    @Override
    protected MapCodec<? extends SpiderEyeBlock> codec() {
        return CODEC;
    }

}
