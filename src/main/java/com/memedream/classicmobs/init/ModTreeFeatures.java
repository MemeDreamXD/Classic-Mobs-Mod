package com.memedream.classicmobs.init;

import com.memedream.classicmobs.ClassicMobs;
import com.memedream.classicmobs.world.tree.PalmTreeFoliagePlacer;
import com.memedream.classicmobs.world.tree.PalmTreeTrunkPlacer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModTreeFeatures {

    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACERS = DeferredRegister.create(Registries.FOLIAGE_PLACER_TYPE, ClassicMobs.MOD_ID);
    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACERS = DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, ClassicMobs.MOD_ID);

    public static final DeferredHolder<FoliagePlacerType<?>, FoliagePlacerType<PalmTreeFoliagePlacer>> PALM_TREE_FOLIAGE = FOLIAGE_PLACERS.register("palm_tree", () -> new FoliagePlacerType<>(PalmTreeFoliagePlacer.CODEC));

    public static final DeferredHolder<TrunkPlacerType<?>, TrunkPlacerType<PalmTreeTrunkPlacer>> PALM_TREE_TRUNK = TRUNK_PLACERS.register("palm_tree", () -> new TrunkPlacerType<>(PalmTreeTrunkPlacer.CODEC));

}
