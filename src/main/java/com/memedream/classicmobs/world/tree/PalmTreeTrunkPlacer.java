package com.memedream.classicmobs.world.tree;

import com.memedream.classicmobs.init.ModTreeFeatures;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.IntProviders;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.List;
import java.util.function.BiConsumer;

public class PalmTreeTrunkPlacer extends TrunkPlacer {

    public static final MapCodec<PalmTreeTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(instance -> trunkPlacerParts(instance).and(instance.group(
            IntProviders.POSITIVE_CODEC.fieldOf("segment_lengths").forGetter(o -> o.segmentSplit),
            Codec.BOOL.fieldOf("generate_root_stumps").forGetter(o -> o.generateRootBlocks)))
        .apply(instance, PalmTreeTrunkPlacer::new));

    private final IntProvider segmentSplit;
    private final boolean generateRootBlocks;

    public PalmTreeTrunkPlacer(int baseHeight, int randHeightA, int randHeightB, IntProvider segmentSplit, boolean generateRootBlocks) {
        super(baseHeight, randHeightA, randHeightB);
        this.segmentSplit = segmentSplit;
        this.generateRootBlocks = generateRootBlocks;
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return ModTreeFeatures.PALM_TREE_TRUNK.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(WorldGenLevel level, BiConsumer<BlockPos, BlockState> trunkSetter, RandomSource random, int treeHeight, BlockPos origin, TreeConfiguration config) {
        Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);
        int logHeight = treeHeight - 1;
        BlockPos.MutableBlockPos pos = origin.mutable();
        placeBelowTrunkBlock(level, trunkSetter, random, pos.below(), config);

        if (this.generateRootBlocks) {
            BlockPos placePos = origin.relative(direction.getOpposite());
            this.placeLog(level, trunkSetter, random, placePos, config);
            placeBelowTrunkBlock(level, trunkSetter, random, placePos.below(), config);

            placePos = origin.relative(random.nextBoolean() ? direction.getClockWise() : direction.getCounterClockWise());
            this.placeLog(level, trunkSetter, random, placePos, config);
            placeBelowTrunkBlock(level, trunkSetter, random, placePos.below(), config);
        }

        int lastBendPos = 0;
        int splitCount = this.segmentSplit.sample(random);
        for (int height = 0; height <= logHeight; height++) {
            if (height - lastBendPos == splitCount) {
                pos.move(direction);
                lastBendPos = height;
            }
            this.placeLog(level, trunkSetter, random, pos, config);
            pos.move(Direction.UP);
        }
        return List.of(new FoliagePlacer.FoliageAttachment(pos.move(Direction.DOWN).immutable(), 0, false));
    }
}
