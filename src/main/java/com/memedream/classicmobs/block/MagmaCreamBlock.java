package com.memedream.classicmobs.block;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class MagmaCreamBlock extends Block {

    public static final MapCodec<MagmaCreamBlock> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.FLOAT.fieldOf("fire_damage").forGetter(o -> o.fireDamage),
            propertiesCodec())
        .apply(instance, MagmaCreamBlock::new));

    private final float fireDamage;

    public MagmaCreamBlock(float fireDamage, BlockBehaviour.Properties properties) {
        super(properties);
        this.fireDamage = fireDamage;
    }

    @Override
    public MapCodec<MagmaCreamBlock> codec() {
        return CODEC;
    }

    protected boolean skipRendering(BlockState state, BlockState adjacentBlockState, Direction side) {
        return adjacentBlockState.is(this) || super.skipRendering(state, adjacentBlockState, side);
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if (!entity.isSteppingCarefully() && entity instanceof LivingEntity && !entity.fireImmune()) {
            entity.hurt(level.damageSources().inFire(), this.fireDamage);
            super.stepOn(level, pos, state, entity);
            entity.setRemainingFireTicks(entity.getRemainingFireTicks() + 1);
            if (entity.getRemainingFireTicks() == 0) {
                entity.igniteForSeconds(8.0F);
            }
        }
    }
}