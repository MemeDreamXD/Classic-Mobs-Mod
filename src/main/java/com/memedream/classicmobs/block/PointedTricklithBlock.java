package com.memedream.classicmobs.block;

import com.google.common.annotations.VisibleForTesting;
import com.memedream.classicmobs.init.ModBlocks;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.arrow.ThrownTrident;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Fallable;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DripstoneThickness;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.fluids.FluidType;
import org.jspecify.annotations.Nullable;

import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

//TODO Gizmo: clean this class up
public class PointedTricklithBlock extends Block implements Fallable, SimpleWaterloggedBlock {
    public static final MapCodec<PointedTricklithBlock> CODEC = simpleCodec(PointedTricklithBlock::new);
    public static final EnumProperty<Direction> TIP_DIRECTION = BlockStateProperties.VERTICAL_DIRECTION;
    public static final EnumProperty<DripstoneThickness> THICKNESS = BlockStateProperties.DRIPSTONE_THICKNESS;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    private static final VoxelShape TIP_MERGE_SHAPE = Block.box(5.0, 0.0, 5.0, 11.0, 16.0, 11.0);
    private static final VoxelShape TIP_SHAPE_UP = Block.box(5.0, 0.0, 5.0, 11.0, 11.0, 11.0);
    private static final VoxelShape TIP_SHAPE_DOWN = Block.box(5.0, 5.0, 5.0, 11.0, 16.0, 11.0);
    private static final VoxelShape FRUSTUM_SHAPE = Block.box(4.0, 0.0, 4.0, 12.0, 16.0, 12.0);
    private static final VoxelShape MIDDLE_SHAPE = Block.box(3.0, 0.0, 3.0, 13.0, 16.0, 13.0);
    private static final VoxelShape BASE_SHAPE = Block.box(2.0, 0.0, 2.0, 14.0, 16.0, 14.0);
    private static final VoxelShape REQUIRED_SPACE_TO_DRIP_THROUGH_NON_SOLID_BLOCK = Block.box(6.0, 0.0, 6.0, 10.0, 16.0, 10.0);

    @Override
    public MapCodec<PointedTricklithBlock> codec() {
        return CODEC;
    }

    public PointedTricklithBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(
            this.stateDefinition
                .any()
                .setValue(TIP_DIRECTION, Direction.UP)
                .setValue(THICKNESS, DripstoneThickness.TIP)
                .setValue(WATERLOGGED, false)
        );
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(TIP_DIRECTION, THICKNESS, WATERLOGGED);
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return isValidPointedTricklithPlacement(level, pos, state.getValue(TIP_DIRECTION));
    }

    @Override
    protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess ticks, BlockPos pos, Direction directionToNeighbour, BlockPos neighbourPos, BlockState neighbourState, RandomSource random) {
        if (state.getValue(WATERLOGGED)) {
            ticks.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }

        if (directionToNeighbour.getAxis() != Direction.Axis.Y) {
            return state;
        } else {
            Direction direction = state.getValue(TIP_DIRECTION);
            if (direction == Direction.DOWN && ticks.getBlockTicks().hasScheduledTick(pos, this)) {
                return state;
            } else if (direction == direction.getOpposite() && !this.canSurvive(state, level, pos)) {
                if (direction == Direction.DOWN) {
                    ticks.scheduleTick(pos, this, 2);
                } else {
                    ticks.scheduleTick(pos, this, 1);
                }

                return state;
            } else {
                boolean flag = state.getValue(THICKNESS) == DripstoneThickness.TIP_MERGE;
                DripstoneThickness dripstoneThickness = calculateDripstoneThickness(level, pos, direction, flag);
                return state.setValue(THICKNESS, dripstoneThickness);
            }
        }
    }

    @Override
    protected void onProjectileHit(Level level, BlockState state, BlockHitResult hit, Projectile projectile) {
        if (level instanceof ServerLevel serverLevel) {
            BlockPos blockpos = hit.getBlockPos();
            if (projectile.mayInteract(serverLevel, blockpos)
                && projectile.mayBreak(serverLevel)
                && projectile instanceof ThrownTrident
                && projectile.getDeltaMovement().length() > 0.6) {
                level.destroyBlock(blockpos, true);
            }
        }
    }

    @Override
    public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, double fallDistance) {
        if (state.getValue(TIP_DIRECTION) == Direction.UP && state.getValue(THICKNESS) == DripstoneThickness.TIP) {
            entity.causeFallDamage(fallDistance + 2.0F, 2.0F, level.damageSources().stalagmite());
        } else {
            super.fallOn(level, state, pos, entity, fallDistance);
        }
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (canDrip(state)) {
            float randomValue = random.nextFloat();
            if (!(randomValue > 0.12F)) {
                getFluidAboveStalactite(level, pos, state)
                    .filter(fluidAbove -> randomValue < 0.02F || canFillCauldron(fluidAbove.fluid))
                    .ifPresent(fluidAbove -> spawnDripParticle(level, pos, state, fluidAbove.fluid, fluidAbove.pos));
            }
        }
    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (isStalagmite(state) && !this.canSurvive(state, level, pos)) {
            level.destroyBlock(pos, true);
        } else {
            spawnFallingStalactite(state, level, pos);
        }
    }

    /**
     * Performs a random tick on a block.
     */
    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        maybeTransferFluid(state, level, pos, random.nextFloat());
        if (random.nextFloat() < 0.011377778F && isStalactiteStartPos(state, level, pos)) {
            growStalactiteOrStalagmiteIfPossible(state, level, pos, random);
        }
    }

    @VisibleForTesting
    public static void maybeTransferFluid(BlockState state, ServerLevel level, BlockPos pos, float randChance) {
        //Neo: remove the water and lava drip chance checks to allow modded fluids to drip into cauldrons
        if (isStalactiteStartPos(state, level, pos)) {
            Optional<FluidInfo> optional = getFluidAboveStalactite(level, pos, state);
            if (optional.isPresent()) {
                Fluid fluid = optional.get().fluid;
                FluidType.DripstoneDripInfo dripInfo = fluid.getFluidType().getDripInfo();
                if (dripInfo != null && !(randChance >= dripInfo.chance())) {
                    BlockPos blockpos = findTip(state, level, pos, 11, false);
                    if (blockpos != null) {
                        if (optional.get().sourceState.is(Blocks.MUD) && fluid == Fluids.WATER) {
                            BlockState blockstate1 = Blocks.CLAY.defaultBlockState();
                            level.setBlockAndUpdate(optional.get().pos, blockstate1);
                            Block.pushEntitiesUp(optional.get().sourceState, blockstate1, level, optional.get().pos);
                            level.gameEvent(GameEvent.BLOCK_CHANGE, optional.get().pos, GameEvent.Context.of(blockstate1));
                            level.levelEvent(1504, blockpos, 0);
                        } else {
                            BlockPos blockpos1 = findFillableCauldronBelowStalactiteTip(level, blockpos, fluid);
                            if (blockpos1 != null) {
                                level.levelEvent(1504, blockpos, 0);
                                int i = blockpos.getY() - blockpos1.getY();
                                int j = 50 + i;
                                BlockState blockstate = level.getBlockState(blockpos1);
                                level.scheduleTick(blockpos1, blockstate.getBlock(), j);
                            }
                        }
                    }
                }
            }
        }
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        LevelAccessor levelaccessor = context.getLevel();
        BlockPos blockpos = context.getClickedPos();
        Direction direction = context.getNearestLookingVerticalDirection().getOpposite();
        Direction direction1 = calculateTipDirection(levelaccessor, blockpos, direction);
        if (direction1 == null) {
            return null;
        } else {
            boolean flag = !context.isSecondaryUseActive();
            DripstoneThickness dripstoneThickness = calculateDripstoneThickness(levelaccessor, blockpos, direction1, flag);
            return this.defaultBlockState()
                .setValue(TIP_DIRECTION, direction1)
                .setValue(THICKNESS, dripstoneThickness)
                .setValue(WATERLOGGED, levelaccessor.getFluidState(blockpos).getType() == Fluids.WATER);
        }
    }

    @Override
    protected FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    protected VoxelShape getOcclusionShape(BlockState state) {
        return Shapes.empty();
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        DripstoneThickness dripstoneThickness = state.getValue(THICKNESS);
        VoxelShape voxelshape;
        if (dripstoneThickness == DripstoneThickness.TIP_MERGE) {
            voxelshape = TIP_MERGE_SHAPE;
        } else if (dripstoneThickness == DripstoneThickness.TIP) {
            if (state.getValue(TIP_DIRECTION) == Direction.DOWN) {
                voxelshape = TIP_SHAPE_DOWN;
            } else {
                voxelshape = TIP_SHAPE_UP;
            }
        } else if (dripstoneThickness == DripstoneThickness.FRUSTUM) {
            voxelshape = FRUSTUM_SHAPE;
        } else if (dripstoneThickness == DripstoneThickness.MIDDLE) {
            voxelshape = MIDDLE_SHAPE;
        } else {
            voxelshape = BASE_SHAPE;
        }

        Vec3 vec3 = state.getOffset(pos);
        return voxelshape.move(vec3.x, 0.0, vec3.z);
    }

    @Override
    protected boolean isCollisionShapeFullBlock(BlockState state, BlockGetter level, BlockPos pos) {
        return false;
    }

    @Override
    protected float getMaxHorizontalOffset() {
        return 0.125F;
    }

    @Override
    public void onBrokenAfterFall(Level level, BlockPos pos, FallingBlockEntity fallingBlock) {
        if (!fallingBlock.isSilent()) {
            level.levelEvent(1045, pos, 0);
        }
    }

    @Override
    public DamageSource getFallDamageSource(Entity entity) {
        return entity.damageSources().fallingStalactite(entity);
    }

    private static void spawnFallingStalactite(BlockState state, ServerLevel level, BlockPos pos) {
        BlockPos.MutableBlockPos blockpos$mutableblockpos = pos.mutable();
        BlockState blockstate = state;

        while (isStalactite(blockstate)) {
            FallingBlockEntity fallingblockentity = FallingBlockEntity.fall(level, blockpos$mutableblockpos, blockstate);
            if (isTip(blockstate, true)) {
                int i = Math.max(1 + pos.getY() - blockpos$mutableblockpos.getY(), 6);
                fallingblockentity.setHurtsEntities(i, 40);
                break;
            }

            blockpos$mutableblockpos.move(Direction.DOWN);
            blockstate = level.getBlockState(blockpos$mutableblockpos);
        }
    }

    @VisibleForTesting
    public static void growStalactiteOrStalagmiteIfPossible(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        BlockState blockstate = level.getBlockState(pos.above(1));
        BlockState blockstate1 = level.getBlockState(pos.above(2));
        if (canGrow(blockstate, blockstate1)) {
            BlockPos blockpos = findTip(state, level, pos, 7, false);
            if (blockpos != null) {
                BlockState blockstate2 = level.getBlockState(blockpos);
                if (canDrip(blockstate2) && canTipGrow(blockstate2, level, blockpos)) {
                    if (random.nextBoolean()) {
                        grow(level, blockpos, Direction.DOWN);
                    } else {
                        growStalagmiteBelow(level, blockpos);
                    }
                }
            }
        }
    }

    private static void growStalagmiteBelow(ServerLevel level, BlockPos pos) {
        BlockPos.MutableBlockPos blockpos$mutableblockpos = pos.mutable();

        for (int i = 0; i < 10; i++) {
            blockpos$mutableblockpos.move(Direction.DOWN);
            BlockState blockstate = level.getBlockState(blockpos$mutableblockpos);
            if (!blockstate.getFluidState().isEmpty()) {
                return;
            }

            if (isUnmergedTipWithDirection(blockstate, Direction.UP) && canTipGrow(blockstate, level, blockpos$mutableblockpos)) {
                grow(level, blockpos$mutableblockpos, Direction.UP);
                return;
            }

            if (isValidPointedTricklithPlacement(level, blockpos$mutableblockpos, Direction.UP) && !level.isWaterAt(blockpos$mutableblockpos.below())) {
                grow(level, blockpos$mutableblockpos.below(), Direction.UP);
                return;
            }

            if (!canDripThrough(level, blockpos$mutableblockpos, blockstate)) {
                return;
            }
        }
    }

    private static void grow(ServerLevel server, BlockPos pos, Direction direction) {
        BlockPos blockpos = pos.relative(direction);
        BlockState blockstate = server.getBlockState(blockpos);
        if (isUnmergedTipWithDirection(blockstate, direction.getOpposite())) {
            createMergedTips(blockstate, server, blockpos);
        } else if (blockstate.isAir() || blockstate.is(Blocks.WATER)) {
            createTricklith(server, blockpos, direction, DripstoneThickness.TIP);
        }
    }

    private static void createTricklith(LevelAccessor level, BlockPos pos, Direction direction, DripstoneThickness thickness) {
        BlockState blockstate = ModBlocks.POINTED_TRICKLITH
            .get().defaultBlockState()
            .setValue(TIP_DIRECTION, direction)
            .setValue(THICKNESS, thickness)
            .setValue(WATERLOGGED, level.getFluidState(pos).getType() == Fluids.WATER);
        level.setBlock(pos, blockstate, 3);
    }

    private static void createMergedTips(BlockState state, LevelAccessor level, BlockPos pos) {
        BlockPos blockpos;
        BlockPos blockpos1;
        if (state.getValue(TIP_DIRECTION) == Direction.UP) {
            blockpos1 = pos;
            blockpos = pos.above();
        } else {
            blockpos = pos;
            blockpos1 = pos.below();
        }

        createTricklith(level, blockpos, Direction.DOWN, DripstoneThickness.TIP_MERGE);
        createTricklith(level, blockpos1, Direction.UP, DripstoneThickness.TIP_MERGE);
    }

    public static void spawnDripParticle(Level level, BlockPos stalactiteTipPos, BlockState stalactiteTipState) {
        getFluidAboveStalactite(level, stalactiteTipPos, stalactiteTipState)
            .ifPresent(fluidAbove -> spawnDripParticle(level, stalactiteTipPos, stalactiteTipState, fluidAbove.fluid, fluidAbove.pos));
    }

    private static void spawnDripParticle(Level level, BlockPos pos, BlockState state, Fluid fluidAbove, BlockPos posAbove) {
        Vec3 vec3 = state.getOffset(pos);
        double x = (double) pos.getX() + 0.5 + vec3.x;
        double y = (double) ((float) (pos.getY() + 1) - 0.6875F) - 0.0625;
        double z = (double) pos.getZ() + 0.5 + vec3.z;
        ParticleOptions dripParticle = getDripParticle(level, fluidAbove, posAbove);
        level.addParticle(dripParticle, x, y, z, 0.0, 0.0, 0.0);
    }

    @Nullable
    private static BlockPos findTip(BlockState state, LevelAccessor level, BlockPos pos, int maxIterations, boolean isTipMerge) {
        if (isTip(state, isTipMerge)) {
            return pos;
        } else {
            Direction direction = state.getValue(TIP_DIRECTION);
            BiPredicate<BlockPos, BlockState> bipredicate = (p_202023_, p_202024_) -> p_202024_.is(ModBlocks.POINTED_TRICKLITH)
                && p_202024_.getValue(TIP_DIRECTION) == direction;
            return findBlockVertical(level, pos, direction.getAxisDirection(), bipredicate, p_154168_ -> isTip(p_154168_, isTipMerge), maxIterations)
                .orElse(null);
        }
    }

    @Nullable
    private static Direction calculateTipDirection(LevelReader level, BlockPos pos, Direction dir) {
        Direction direction;
        if (isValidPointedTricklithPlacement(level, pos, dir)) {
            direction = dir;
        } else {
            if (!isValidPointedTricklithPlacement(level, pos, dir.getOpposite())) {
                return null;
            }

            direction = dir.getOpposite();
        }

        return direction;
    }

    private static DripstoneThickness calculateDripstoneThickness(LevelReader level, BlockPos pos, Direction dir, boolean isTipMerge) {
        Direction direction = dir.getOpposite();
        BlockState blockstate = level.getBlockState(pos.relative(dir));
        if (isPointedTricklithWithDirection(blockstate, direction)) {
            return !isTipMerge && blockstate.getValue(THICKNESS) != DripstoneThickness.TIP_MERGE ? DripstoneThickness.TIP : DripstoneThickness.TIP_MERGE;
        } else if (!isPointedTricklithWithDirection(blockstate, dir)) {
            return DripstoneThickness.TIP;
        } else {
            DripstoneThickness dripstoneThickness = blockstate.getValue(THICKNESS);
            if (dripstoneThickness != DripstoneThickness.TIP && dripstoneThickness != DripstoneThickness.TIP_MERGE) {
                BlockState blockstate1 = level.getBlockState(pos.relative(direction));
                return !isPointedTricklithWithDirection(blockstate1, dir) ? DripstoneThickness.BASE : DripstoneThickness.MIDDLE;
            } else {
                return DripstoneThickness.FRUSTUM;
            }
        }
    }

    public static boolean canDrip(BlockState state) {
        return isStalactite(state) && state.getValue(THICKNESS) == DripstoneThickness.TIP && !state.getValue(WATERLOGGED);
    }

    private static boolean canTipGrow(BlockState state, ServerLevel level, BlockPos pos) {
        Direction direction = state.getValue(TIP_DIRECTION);
        BlockPos blockpos = pos.relative(direction);
        BlockState blockstate = level.getBlockState(blockpos);
        if (!blockstate.getFluidState().isEmpty()) {
            return false;
        } else {
            return blockstate.isAir() || isUnmergedTipWithDirection(blockstate, direction.getOpposite());
        }
    }

    private static Optional<BlockPos> findRootBlock(Level level, BlockPos pos, BlockState state, int maxIterations) {
        Direction direction = state.getValue(TIP_DIRECTION);
        BiPredicate<BlockPos, BlockState> bipredicate = (p_202015_, p_202016_) -> p_202016_.is(ModBlocks.POINTED_TRICKLITH)
            && p_202016_.getValue(TIP_DIRECTION) == direction;
        return findBlockVertical(
            level, pos, direction.getOpposite().getAxisDirection(), bipredicate, p_154245_ -> !p_154245_.is(ModBlocks.POINTED_TRICKLITH), maxIterations
        );
    }

    private static boolean isValidPointedTricklithPlacement(LevelReader level, BlockPos pos, Direction dir) {
        BlockPos blockpos = pos.relative(dir.getOpposite());
        BlockState blockstate = level.getBlockState(blockpos);
        return blockstate.isFaceSturdy(level, blockpos, dir) || isPointedTricklithWithDirection(blockstate, dir);
    }

    private static boolean isTip(BlockState state, boolean isTipMerge) {
        if (!state.is(ModBlocks.POINTED_TRICKLITH)) {
            return false;
        } else {
            DripstoneThickness dripstoneThickness = state.getValue(THICKNESS);
            return dripstoneThickness == DripstoneThickness.TIP || isTipMerge && dripstoneThickness == DripstoneThickness.TIP_MERGE;
        }
    }

    private static boolean isUnmergedTipWithDirection(BlockState state, Direction dir) {
        return isTip(state, false) && state.getValue(TIP_DIRECTION) == dir;
    }

    private static boolean isStalactite(BlockState state) {
        return isPointedTricklithWithDirection(state, Direction.DOWN);
    }

    private static boolean isStalagmite(BlockState state) {
        return isPointedTricklithWithDirection(state, Direction.UP);
    }

    private static boolean isStalactiteStartPos(BlockState state, LevelReader level, BlockPos pos) {
        return isStalactite(state) && !level.getBlockState(pos.above()).is(ModBlocks.POINTED_TRICKLITH);
    }

    @Override
    protected boolean isPathfindable(BlockState state, PathComputationType pathComputationType) {
        return false;
    }

    private static boolean isPointedTricklithWithDirection(BlockState state, Direction dir) {
        return state.is(ModBlocks.POINTED_TRICKLITH) && state.getValue(TIP_DIRECTION) == dir;
    }

    @Nullable
    private static BlockPos findFillableCauldronBelowStalactiteTip(Level level, BlockPos pos, Fluid fluid) {
//        Predicate<BlockState> predicate = p_154162_ -> p_154162_.getBlock() instanceof AbstractCauldronBlock
//                && ((AbstractCauldronBlock)p_154162_.getBlock()).canReceiveStalactiteDrip(fluid);
//        BiPredicate<BlockPos, BlockState> bipredicate = (p_202034_, p_202035_) -> canDripThrough(level, p_202034_, p_202035_);
        //return findBlockVertical(level, pos, Direction.DOWN.getAxisDirection(), bipredicate, predicate, 11).orElse(null);
        return null;
    }

    @Nullable
    public static BlockPos findStalactiteTipAboveCauldron(Level level, BlockPos pos) {
        BiPredicate<BlockPos, BlockState> bipredicate = (p_202030_, p_202031_) -> canDripThrough(level, p_202030_, p_202031_);
        return findBlockVertical(level, pos, Direction.UP.getAxisDirection(), bipredicate, PointedTricklithBlock::canDrip, 11).orElse(null);
    }

    public static Fluid getCauldronFillFluidType(ServerLevel level, BlockPos pos) {
        return getFluidAboveStalactite(level, pos, level.getBlockState(pos))
            .map(p_221858_ -> p_221858_.fluid)
            .filter(PointedTricklithBlock::canFillCauldron)
            .orElse(Fluids.EMPTY);
    }

    private static Optional<PointedTricklithBlock.FluidInfo> getFluidAboveStalactite(Level level, BlockPos pos, BlockState state) {
        return !isStalactite(state) ? Optional.empty() : findRootBlock(level, pos, state, 11).map(rootPos -> {
            BlockPos blockpos = rootPos.above();
            BlockState blockstate = level.getBlockState(blockpos);
            Fluid fluid;
            if (blockstate.is(Blocks.MUD) && !level.environmentAttributes().getValue(EnvironmentAttributes.WATER_EVAPORATES, blockpos)) {
                fluid = Fluids.WATER;
            } else {
                fluid = level.getFluidState(blockpos).getType();
            }

            return new PointedTricklithBlock.FluidInfo(blockpos, fluid, blockstate);
        });
    }

    private static boolean canFillCauldron(Fluid fluid) {
        return fluid.getFluidType().getDripInfo() != null;
    }

    private static boolean canGrow(BlockState tricklithState, BlockState state) {
        return tricklithState.is(ModBlocks.TRICKLITH_BLOCK) && state.is(Blocks.WATER) && state.getFluidState().isSource();
    }

    private static ParticleOptions getDripParticle(Level level, Fluid fluidAbove, BlockPos posAbove) {
        if (fluidAbove.isSame(Fluids.EMPTY)) {
            return level.environmentAttributes().getValue(EnvironmentAttributes.DEFAULT_DRIPSTONE_PARTICLE, posAbove);
        } else {
            ParticleOptions options = fluidAbove.getFluidType().getDripInfo() != null ? fluidAbove.getFluidType().getDripInfo().dripParticle() : ParticleTypes.DRIPPING_DRIPSTONE_WATER;
            if (options == null) options = level.environmentAttributes().getValue(EnvironmentAttributes.DEFAULT_DRIPSTONE_PARTICLE, posAbove);
            return options;
        }
    }

    private static Optional<BlockPos> findBlockVertical(
        LevelAccessor level,
        BlockPos pos,
        Direction.AxisDirection axis,
        BiPredicate<BlockPos, BlockState> positionalStatePredicate,
        Predicate<BlockState> statePredicate,
        int maxIterations
    ) {
        Direction direction = Direction.get(axis, Direction.Axis.Y);
        BlockPos.MutableBlockPos blockpos$mutableblockpos = pos.mutable();

        for (int i = 1; i < maxIterations; i++) {
            blockpos$mutableblockpos.move(direction);
            BlockState blockstate = level.getBlockState(blockpos$mutableblockpos);
            if (statePredicate.test(blockstate)) {
                return Optional.of(blockpos$mutableblockpos.immutable());
            }

            if (level.isOutsideBuildHeight(blockpos$mutableblockpos.getY()) || !positionalStatePredicate.test(blockpos$mutableblockpos, blockstate)) {
                return Optional.empty();
            }
        }

        return Optional.empty();
    }

    private static boolean canDripThrough(BlockGetter level, BlockPos pos, BlockState state) {
        if (state.isAir()) {
            return true;
        } else if (state.isSolidRender()) {
            return false;
        } else if (!state.getFluidState().isEmpty()) {
            return false;
        } else {
            VoxelShape voxelshape = state.getCollisionShape(level, pos);
            return !Shapes.joinIsNotEmpty(REQUIRED_SPACE_TO_DRIP_THROUGH_NON_SOLID_BLOCK, voxelshape, BooleanOp.AND);
        }
    }

    record FluidInfo(BlockPos pos, Fluid fluid, BlockState sourceState) {
    }
}
