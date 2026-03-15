package com.memedream.classicmobs.world.tree;

import com.memedream.classicmobs.init.ModTreeFeatures;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class PalmTreeFoliagePlacer extends FoliagePlacer {

    public static final MapCodec<PalmTreeFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(i -> foliagePlacerParts(i)
        .and(i.group(
            IntProvider.codec(1, 5).fieldOf("branching_leaf_length").forGetter(o -> o.branchingLeafLength),
            IntProvider.codec(1, 5).fieldOf("diagonal_branching_leaf_length").forGetter(o -> o.diagonalBranchingLeafLength)))
        .apply(i, PalmTreeFoliagePlacer::new));

    private final IntProvider branchingLeafLength;
    private final IntProvider diagonalBranchingLeafLength;

    public PalmTreeFoliagePlacer(IntProvider radius, IntProvider offset, IntProvider branchingLeafLength, IntProvider diagonalBranchingLeafLength) {
        super(radius, offset);
        this.branchingLeafLength = branchingLeafLength;
        this.diagonalBranchingLeafLength = diagonalBranchingLeafLength;
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return ModTreeFeatures.PALM_TREE_FOLIAGE.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader level, FoliageSetter foliageSetter, RandomSource random, TreeConfiguration config, int treeHeight, FoliageAttachment foliageAttachment, int foliageHeight, int leafRadius, int offset) {
        BlockPos pos = foliageAttachment.pos().above(offset);
        BlockPos.MutableBlockPos placePos = new BlockPos.MutableBlockPos();
        int branchingLength = this.branchingLeafLength.sample(random);

        this.placeLeavesRow(level, foliageSetter, random, config, pos, leafRadius, 1, false);

        for (Direction direction : Direction.Plane.HORIZONTAL) {
            //create straight branching leaves
            for (int length = leafRadius; length <= branchingLength; length++) {
                placePos.setWithOffset(pos, direction.getStepX() * length, 0, direction.getStepZ() * length);
                tryPlaceLeaf(level, foliageSetter, random, config, placePos);
            }
            placePos.setWithOffset(pos, direction.getStepX() * (branchingLength + 1), -1, direction.getStepZ() * (branchingLength + 1));
            tryPlaceLeaf(level, foliageSetter, random, config, placePos);

            //now diagonals!
            Direction clockwise = direction.getClockWise();
            int diagonalBranchingLength = this.diagonalBranchingLeafLength.sample(random);
            for (int length = 1; length <= diagonalBranchingLength; length++) {
                placePos.setWithOffset(pos, (direction.getStepX() + clockwise.getStepX()) * length, 0, (direction.getStepZ() + clockwise.getStepZ()) * length);
                tryPlaceLeaf(level, foliageSetter, random, config, placePos);
            }

            placePos.setWithOffset(pos, (direction.getStepX() + clockwise.getStepX()) * (diagonalBranchingLength + 1), -1, (direction.getStepZ() + clockwise.getStepZ()) * (diagonalBranchingLength + 1));
            tryPlaceLeaf(level, foliageSetter, random, config, placePos);
        }
    }

    @Override
    public int foliageHeight(RandomSource random, int treeHeight, TreeConfiguration config) {
        return 1;
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource random, int dx, int y, int dz, int currentRadius, boolean doubleTrunk) {
        return y == 0 ? (dx > 1 || dz > 1) && dx != 0 && dz != 0 : dx == currentRadius && dz == currentRadius && currentRadius > 0;
    }
}
