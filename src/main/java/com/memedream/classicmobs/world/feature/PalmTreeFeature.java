package com.memedream.classicmobs.world.feature;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelWriter;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.phys.shapes.BitSetDiscreteVoxelShape;
import net.minecraft.world.phys.shapes.DiscreteVoxelShape;

import java.util.Iterator;
import java.util.List;
import java.util.OptionalInt;
import java.util.Set;
import java.util.function.BiConsumer;

public class PalmTreeFeature extends TreeFeature {
    public PalmTreeFeature(Codec<TreeConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<TreeConfiguration> context) {
        final WorldGenLevel level = context.level();
        RandomSource random = context.random();
        BlockPos origin = context.origin();
        TreeConfiguration config = context.config();
        Set<BlockPos> rootPositions = Sets.newHashSet();
        Set<BlockPos> trunks = Sets.newHashSet();
        final Set<BlockPos> foliage = Sets.newHashSet();
        Set<BlockPos> decorations = Sets.newHashSet();
        BiConsumer<BlockPos, BlockState> rootSetter = (pos, state) -> {
            rootPositions.add(pos.immutable());
            level.setBlock(pos, state, 19);
        };
        BiConsumer<BlockPos, BlockState> trunkSetter = (pos, state) -> {
            trunks.add(pos.immutable());
            level.setBlock(pos, state, 19);
        };
        FoliagePlacer.FoliageSetter foliageSetter = new FoliagePlacer.FoliageSetter() {
            @Override
            public void set(BlockPos pos, BlockState state) {
                foliage.add(pos.immutable());
                level.setBlock(pos, state, 19);
            }

            @Override
            public boolean isSet(BlockPos pos) {
                return foliage.contains(pos);
            }
        };
        BiConsumer<BlockPos, BlockState> decorationSetter = (pos, state) -> {
            decorations.add(pos.immutable());
            level.setBlock(pos, state, 19);
        };
        boolean result = this.doPlace(level, random, origin, rootSetter, trunkSetter, foliageSetter, config);
        if (result && (!trunks.isEmpty() || !foliage.isEmpty())) {
            if (!config.decorators.isEmpty()) {
                TreeDecorator.Context decoratorContext = new TreeDecorator.Context(level, decorationSetter, random, trunks, foliage, rootPositions);
                config.decorators.forEach(decorator -> decorator.place(decoratorContext));
            }

            return BoundingBox.encapsulatingPositions(Iterables.concat(rootPositions, trunks, foliage, decorations)).map(bounds -> {
                DiscreteVoxelShape shape = updateLeaves(level, bounds, trunks, decorations, rootPositions);
                StructureTemplate.updateShapeAtEdge(level, 3, shape, bounds.minX(), bounds.minY(), bounds.minZ());
                return true;
            }).orElse(false);
        } else {
            return false;
        }
    }

    private static DiscreteVoxelShape updateLeaves(LevelAccessor level, BoundingBox bounds, Set<BlockPos> logs, Set<BlockPos> decorationSet, Set<BlockPos> rootPositions) {
        DiscreteVoxelShape shape = new BitSetDiscreteVoxelShape(bounds.getXSpan(), bounds.getYSpan(), bounds.getZSpan());
        int maxDistance = 7;
        List<Set<BlockPos>> toCheck = Lists.newArrayList();

        for (int i = 0; i < maxDistance; i++) {
            toCheck.add(Sets.newHashSet());
        }

        for (BlockPos pos : Lists.newArrayList(Sets.union(decorationSet, rootPositions))) {
            if (bounds.isInside(pos)) {
                shape.fill(pos.getX() - bounds.minX(), pos.getY() - bounds.minY(), pos.getZ() - bounds.minZ());
            }
        }

        BlockPos.MutableBlockPos neighborPos = new BlockPos.MutableBlockPos();
        int smallestDistance = 0;
        toCheck.getFirst().addAll(logs);

        while (true) {
            while (smallestDistance >= maxDistance || !toCheck.get(smallestDistance).isEmpty()) {
                if (smallestDistance >= maxDistance) {
                    return shape;
                }

                Iterator<BlockPos> iterator = toCheck.get(smallestDistance).iterator();
                BlockPos posx = iterator.next();
                iterator.remove();
                if (bounds.isInside(posx)) {
                    if (smallestDistance != 0) {
                        BlockState state = level.getBlockState(posx);
                        setBlockKnownShape(level, posx, state.setValue(BlockStateProperties.DISTANCE, smallestDistance));
                    }

                    shape.fill(posx.getX() - bounds.minX(), posx.getY() - bounds.minY(), posx.getZ() - bounds.minZ());

                    for (int x = -1; x <= 1; x++) {
                        for (int y = -1; y <= 1; y++) {
                            for (int z = -1; z <= 1; z++) {
                                neighborPos.setWithOffset(posx, x, y, z);
                                if (bounds.isInside(neighborPos)) {
                                    int xInShape = neighborPos.getX() - bounds.minX();
                                    int yInShape = neighborPos.getY() - bounds.minY();
                                    int zinShape = neighborPos.getZ() - bounds.minZ();
                                    if (!shape.isFull(xInShape, yInShape, zinShape)) {
                                        BlockState currentState = level.getBlockState(neighborPos);
                                        OptionalInt distance = LeavesBlock.getOptionalDistanceAt(currentState);
                                        if (distance.isPresent()) {
                                            int newDistance = Math.min(distance.getAsInt(), smallestDistance + 1);
                                            if (newDistance < maxDistance) {
                                                toCheck.get(newDistance).add(neighborPos.immutable());
                                                smallestDistance = Math.min(smallestDistance, newDistance);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            smallestDistance++;
        }
    }

    private static void setBlockKnownShape(LevelWriter level, BlockPos pos, BlockState blockState) {
        level.setBlock(pos, blockState, 19);
    }
}
