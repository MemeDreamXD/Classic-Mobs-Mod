package com.memedream.classicmobs.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class MagmaCreamBlock extends Block {
    private static final int SECONDS_ON_FIRE = 8;
    private final float fireDamage;

    public MagmaCreamBlock(BlockBehaviour.Properties properties, float fireDamage) {
        super(properties);
        this.fireDamage = fireDamage;
    }

    protected MapCodec<? extends MagmaCreamBlock> codec() {
        return null;
    }

    //@Override
    //public static final MapCodec<MagmaCreamBlock> CODEC = simpleCodec(MagmaCreamBlock::new);

   // @Override
    //public MapCodec<MagmaCreamBlock> codec() {
    //    return CODEC;
    //}

    protected boolean skipRendering(BlockState state, BlockState adjacentBlockState, Direction side) {
        return adjacentBlockState.is(this) ? true : super.skipRendering(state, adjacentBlockState, side);
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if (!entity.isSteppingCarefully() && entity instanceof LivingEntity && !entity.fireImmune()) {
                entity.setRemainingFireTicks(entity.getRemainingFireTicks() + 1);
                if (entity.getRemainingFireTicks() == 0) {
                    entity.igniteForSeconds(8.0F);
                }

            }

        entity.hurt(level.damageSources().inFire(), this.fireDamage);
        super.stepOn(level, pos, state, entity);
    }
}