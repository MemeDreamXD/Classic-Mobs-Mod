package com.memedream.classicmobs.init;

import com.memedream.classicmobs.ClassicMobs;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ModBiomeModifiers {

    private static final ResourceKey<BiomeModifier> PALM_TREE_SPAWNS = ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ClassicMobs.prefix("palm_trees"));
    private static final ResourceKey<BiomeModifier> RARE_PALM_TREE_SPAWNS = ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ClassicMobs.prefix("rare_palm_trees"));

    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        context.register(PALM_TREE_SPAWNS, new BiomeModifiers.AddFeaturesBiomeModifier(
            context.lookup(Registries.BIOME).getOrThrow(ModTags.Biomes.SPAWNS_PALM_TREES),
            HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(ModFeatures.PALM_TREE_PF)),
            GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(RARE_PALM_TREE_SPAWNS, new BiomeModifiers.AddFeaturesBiomeModifier(
            context.lookup(Registries.BIOME).getOrThrow(ModTags.Biomes.SPAWNS_RARE_PALM_TREES),
            HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(ModFeatures.RARE_PALM_TREE_PF)),
            GenerationStep.Decoration.VEGETAL_DECORATION));
    }
}
