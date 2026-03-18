package com.memedream.classicmobs.init;

import com.memedream.classicmobs.ClassicMobs;
import com.memedream.classicmobs.world.feature.PalmTreeFeature;
import com.memedream.classicmobs.world.tree.PalmTreeFoliagePlacer;
import com.memedream.classicmobs.world.tree.PalmTreeTrunkPlacer;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.RuleBasedStateProvider;
import net.minecraft.world.level.levelgen.placement.*;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

public class ModFeatures {

    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(Registries.FEATURE, ClassicMobs.MOD_ID);

    public static final DeferredHolder<Feature<?>, Feature<TreeConfiguration>> PALM_TREE = FEATURES.register("palm_tree", () -> new PalmTreeFeature(TreeConfiguration.CODEC));


    public static final ResourceKey<ConfiguredFeature<?, ?>> PALM_TREE_CF = registerCF("palm_tree");

    public static final ResourceKey<PlacedFeature> PALM_TREE_PF = registerPF("palm_tree");
    public static final ResourceKey<PlacedFeature> RARE_PALM_TREE_PF = registerPF("rare_palm_tree");


    public static ResourceKey<ConfiguredFeature<?, ?>> registerCF(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ClassicMobs.prefix(name));
    }

    public static ResourceKey<PlacedFeature> registerPF(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ClassicMobs.prefix(name));
    }

    public static void bootstrapConfiguredFeatures(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        context.register(PALM_TREE_CF, new ConfiguredFeature<>(PALM_TREE.get(), new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(ModBlocks.PALM_LOG.get()),
            new PalmTreeTrunkPlacer(6, 0, 0, ConstantInt.of(2), true),
            BlockStateProvider.simple(ModBlocks.PALM_LEAVES.get()),
            new PalmTreeFoliagePlacer(ConstantInt.of(1), ConstantInt.ZERO, ConstantInt.of(3), ConstantInt.of(1)),
            new TwoLayersFeatureSize(1, 0, 1))
            .ignoreVines()
            .belowTrunkProvider(RuleBasedStateProvider.ifTrueThenProvide(BlockPredicate.matchesTag(BlockTags.GRASS_BLOCKS), Blocks.DIRT)) //only replace if on grass since we grow on sand naturally
            .build()));
    }

    public static void bootstrapPlacedFeatures(BootstrapContext<PlacedFeature> context) {
        context.register(PALM_TREE_PF, new PlacedFeature(context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(PALM_TREE_CF), List.of(
            RarityFilter.onAverageOnceEvery(5),
            InSquarePlacement.spread(),
            SurfaceWaterDepthFilter.forMaxDepth(0),
            PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
            BlockPredicateFilter.forPredicate(BlockPredicate.matchesTag(new Vec3i(0, -1, 0), BlockTags.SAND)),
            BiomeFilter.biome()
        )));

        context.register(RARE_PALM_TREE_PF, new PlacedFeature(context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(PALM_TREE_CF), List.of(
            RarityFilter.onAverageOnceEvery(70),
            InSquarePlacement.spread(),
            SurfaceWaterDepthFilter.forMaxDepth(0),
            PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
            BlockPredicateFilter.forPredicate(BlockPredicate.matchesTag(new Vec3i(0, -1, 0), BlockTags.SAND)),
            BiomeFilter.biome()
        )));
    }
}
