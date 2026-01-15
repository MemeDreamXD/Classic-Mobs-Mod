package com.memedream.classicmobs.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class RawMeatBlock extends MeatBlock {

    private final Holder<Block> cookedForm;

    public RawMeatBlock(Holder<Block> cookedForm, BoneType type, Properties properties) {
        super(type, properties);
        this.cookedForm = cookedForm;
    }

    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState newState, boolean moving) {
        if (!newState.is(state.getBlock())) {
            if (level.environmentAttributes().getValue(EnvironmentAttributes.WATER_EVAPORATES, pos)) {
                level.scheduleTick(pos, this, 100);
            }
        }
        super.onPlace(state, level, pos, newState, moving);
    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        level.playSound(null, pos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 1.0F, 0.8F);
        level.setBlockAndUpdate(pos, this.cookedForm.value().withPropertiesOf(state));
        level.blockEvent(pos, this, 0, 0);
    }

    @Override
    protected boolean triggerEvent(BlockState state, Level level, BlockPos pos, int id, int type) {
        for (Direction dir : Direction.values()) {
            ParticleUtils.spawnParticlesOnBlockFace(level, pos, ParticleTypes.WHITE_SMOKE, UniformInt.of(20, 60), dir, () -> Vec3.ZERO, 0.05);
        }
        return true;
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (level.environmentAttributes().getValue(EnvironmentAttributes.WATER_EVAPORATES, pos)) {
            ParticleUtils.spawnParticlesOnBlockFace(level, pos, ParticleTypes.LARGE_SMOKE, UniformInt.of(2, 4), Direction.UP, () -> Vec3.ZERO, 0.05);
        }
    }
}
