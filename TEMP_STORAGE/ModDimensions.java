package com.memedream.classicmobs.init;

import com.memedream.classicmobs.ClassicMobs;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.SurfaceRuleData;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ARGB;
import net.minecraft.util.TriState;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.attribute.BedRule;
import net.minecraft.world.attribute.EnvironmentAttributeMap;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.FixedBiomeSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.*;

import java.util.List;
import java.util.Optional;

public class ModDimensions {

    public static final ResourceKey<Level> UNDERWORLD_KEY = ResourceKey.create(Registries.DIMENSION, ClassicMobs.prefix("underworld"));
    public static final ResourceKey<LevelStem> UNDERWORLD_DIM = ResourceKey.create(Registries.LEVEL_STEM, ClassicMobs.prefix("underworld"));
    public static final ResourceKey<DimensionType> UNDERWORLD_DIM_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE, ClassicMobs.prefix("underworld"));
    public static final ResourceKey<NoiseGeneratorSettings> UNDERWORLD_NOISE_SETTINGS = ResourceKey.create(Registries.NOISE_SETTINGS, ClassicMobs.prefix("underworld"));

    public static void bootstrapType(BootstrapContext<DimensionType> context) {
        context.register(UNDERWORLD_DIM_TYPE, new DimensionType(
            true,
            false,
            false,
            false,
            1.0F,
            0,
            256,
            256,
            BlockTags.INFINIBURN_OVERWORLD,
            0.0F,
            new DimensionType.MonsterSettings(ConstantInt.ZERO, 0),
            DimensionType.Skybox.NONE,
            DimensionType.CardinalLightType.DEFAULT,
            EnvironmentAttributeMap.builder()
                .set(EnvironmentAttributes.BED_RULE, new BedRule(BedRule.Rule.NEVER, BedRule.Rule.NEVER, false, Optional.empty()))
                .set(EnvironmentAttributes.CLOUD_COLOR, ARGB.white(0.0F))
                .set(EnvironmentAttributes.CLOUD_HEIGHT, -1.0F)
                .set(EnvironmentAttributes.SKY_LIGHT_LEVEL, 0.0F)
                .set(EnvironmentAttributes.PIGLINS_ZOMBIFY, false)
                .set(EnvironmentAttributes.CAN_START_RAID, false)
                .set(EnvironmentAttributes.EYEBLOSSOM_OPEN, TriState.FALSE)
                .set(EnvironmentAttributes.TURTLE_EGG_HATCH_CHANCE, 0.0F)
                .set(EnvironmentAttributes.BEES_STAY_IN_HIVE, true)
                .set(EnvironmentAttributes.CAN_PILLAGER_PATROL_SPAWN, false)
                .set(EnvironmentAttributes.CAN_START_RAID, false)
                .set(EnvironmentAttributes.SKY_LIGHT_COLOR, 0)
                .set(EnvironmentAttributes.NIGHT_VISION_COLOR, 0)
                .set(EnvironmentAttributes.AMBIENT_LIGHT_COLOR, 0)
                .set(EnvironmentAttributes.BLOCK_LIGHT_TINT, 0)
                .build(),
            HolderSet.empty(),
            Optional.empty()
        ));
    }

    public static void bootstrapDimension(BootstrapContext<LevelStem> context) {
        context.register(UNDERWORLD_DIM, new LevelStem(context.lookup(Registries.DIMENSION_TYPE).getOrThrow(UNDERWORLD_DIM_TYPE), new NoiseBasedChunkGenerator(new FixedBiomeSource(context.lookup(Registries.BIOME).getOrThrow(Biomes.THE_VOID)), context.lookup(Registries.NOISE_SETTINGS).getOrThrow(UNDERWORLD_NOISE_SETTINGS))));
    }

    public static void bootstrapNoiseSettings(BootstrapContext<NoiseGeneratorSettings> context) {
        context.register(UNDERWORLD_NOISE_SETTINGS, new NoiseGeneratorSettings(
            new NoiseSettings(0, 256, 1, 2),
            Blocks.BEDROCK.defaultBlockState(),
            Blocks.WATER.defaultBlockState(),
            new NoiseRouter(
                DensityFunctions.zero(), //barrier
                DensityFunctions.zero(), //fluid level floodedness
                DensityFunctions.zero(), //fluid level spread
                DensityFunctions.zero(), //lava
                DensityFunctions.shiftedNoise2d(
                    new DensityFunctions.HolderHolder(context.lookup(Registries.DENSITY_FUNCTION).getOrThrow(ResourceKey.create(Registries.DENSITY_FUNCTION, Identifier.withDefaultNamespace("shift_x")))),
                    new DensityFunctions.HolderHolder(context.lookup(Registries.DENSITY_FUNCTION).getOrThrow(ResourceKey.create(Registries.DENSITY_FUNCTION, Identifier.withDefaultNamespace("shift_z")))),
                    0.25D,
                    context.lookup(Registries.NOISE).getOrThrow(Noises.TEMPERATURE)), //temperature
                DensityFunctions.shiftedNoise2d(
                    new DensityFunctions.HolderHolder(context.lookup(Registries.DENSITY_FUNCTION).getOrThrow(ResourceKey.create(Registries.DENSITY_FUNCTION, Identifier.withDefaultNamespace("shift_x")))),
                    new DensityFunctions.HolderHolder(context.lookup(Registries.DENSITY_FUNCTION).getOrThrow(ResourceKey.create(Registries.DENSITY_FUNCTION, Identifier.withDefaultNamespace("shift_z")))),
                    0.25D,
                    context.lookup(Registries.NOISE).getOrThrow(Noises.VEGETATION)), //vegetation
                DensityFunctions.zero(), //continents
                DensityFunctions.zero(), //erosion
                DensityFunctions.zero(), //depth
                DensityFunctions.zero(), //ridges
                DensityFunctions.zero(), //surface level
                DensityFunctions.mul(
                    DensityFunctions.constant(0.64D),
                    DensityFunctions.interpolated(
                        DensityFunctions.blendDensity(
                            DensityFunctions.add(
                                DensityFunctions.constant(-0.234375D),
                                DensityFunctions.mul(
                                    DensityFunctions.yClampedGradient(4, 32, 0, 1.5D),
                                    DensityFunctions.add(
                                        DensityFunctions.constant(0.234375D),
                                        DensityFunctions.add(
                                            DensityFunctions.constant(-23.6D),
                                            DensityFunctions.mul(
                                                DensityFunctions.yClampedGradient(220, 256, 1, 0),
                                                DensityFunctions.add(
                                                    DensityFunctions.constant(23.5375D),
                                                    new DensityFunctions.HolderHolder(context.lookup(Registries.DENSITY_FUNCTION).getOrThrow(ResourceKey.create(Registries.DENSITY_FUNCTION, Identifier.withDefaultNamespace("nether/base_3d_noise"))))
                                                )
                                            )
                                        )
                                    )
                                )
                            )
                        )
                    )
                ).squeeze(), //final density
                DensityFunctions.zero(), //vein toggle
                DensityFunctions.zero(), //vein ridged
                DensityFunctions.zero() //vein gap
            ),
            SurfaceRules.state(Blocks.BEDROCK.defaultBlockState()),
            List.of(),
            0,
            true,
            false,
            false,
            false
        ));
    }
}
