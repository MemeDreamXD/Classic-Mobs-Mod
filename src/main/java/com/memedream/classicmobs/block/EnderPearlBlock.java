package com.memedream.classicmobs.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class EnderPearlBlock extends Block {
    public static final MapCodec<EnderPearlBlock> CODEC = simpleCodec(EnderPearlBlock::new);
    private static final VoxelShape SHAPE = Block.box(3.0, 5.0, 3.0, 13.0, 15.0, 13.0);


    public EnderPearlBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected MapCodec<? extends EnderPearlBlock> codec() {
        return CODEC;
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        //This is just the vanilla chorus fruit method lol
        if (entity instanceof LivingEntity livingEntity && !level.isClientSide()) {
            for (int i = 0; i < 16; i++) {
                double d0 = entity.getX() + (entity.getRandom().nextDouble() - 0.5) * 16.0;
                double d1 = Mth.clamp(
                        entity.getY() + (double)(entity.getRandom().nextInt(16) - 8),
                        level.getMinY(),
                        level.getMinY() + ((ServerLevel)level).getLogicalHeight() - 1
                );
                double d2 = entity.getZ() + (entity.getRandom().nextDouble() - 0.5) * 16.0;
                if (entity.isPassenger()) {
                    entity.stopRiding();
                }

                Vec3 vec3 = entity.position();
                if (livingEntity.randomTeleport(d0, d1, d2, true)) {
                    level.gameEvent(GameEvent.TELEPORT, vec3, GameEvent.Context.of(entity));
                    SoundSource soundsource;
                    SoundEvent soundevent;
                        soundevent = SoundEvents.CHORUS_FRUIT_TELEPORT;
                        soundsource = SoundSource.NEUTRAL;


                    level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), soundevent, soundsource);
                    entity.resetFallDistance();
                    break;
                }
            }
        }
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
            spawnParticles(level, pos);
    }

    private static void spawnParticles(Level level, BlockPos pos) {
        RandomSource randomsource = level.getRandom();

        for (Direction direction : Direction.values()) {
            BlockPos blockpos = pos.relative(direction);
            if (!level.getBlockState(blockpos).isSolidRender()) {
                Direction.Axis direction$axis = direction.getAxis();
                double d1 = direction$axis == Direction.Axis.X ? 0.5D + 0.5625D * direction.getStepX() : randomsource.nextDouble();
                double d2 = direction$axis == Direction.Axis.Y ? 0.5D + 0.5625D * direction.getStepY() : randomsource.nextDouble();
                double d3 = direction$axis == Direction.Axis.Z ? 0.5D + 0.5625D * direction.getStepZ() : randomsource.nextDouble();
                level.addParticle(ParticleTypes.PORTAL, pos.getX() + d1, pos.getY() + d2, pos.getZ() + d3, 0.0, 0.0, 0.0);
            }
        }
    }

    @Override
    protected boolean isPathfindable(BlockState state, PathComputationType pathComputationType) {
        return false;
    }
}
