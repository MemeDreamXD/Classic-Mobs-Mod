package com.memedream.classicmobs.datagen.data;

import com.memedream.classicmobs.ClassicMobs;
import com.memedream.classicmobs.init.ModBiomeModifiers;
import com.memedream.classicmobs.init.ModDamageTypes;
import com.memedream.classicmobs.init.ModFeatures;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class RegistryDataGen extends DatapackBuiltinEntriesProvider {

    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
        .add(Registries.DAMAGE_TYPE, ModDamageTypes::bootstrap)
        .add(Registries.CONFIGURED_FEATURE, ModFeatures::bootstrapConfiguredFeatures)
        .add(Registries.PLACED_FEATURE, ModFeatures::bootstrapPlacedFeatures)
        .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ModBiomeModifiers::bootstrap);

    public RegistryDataGen(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider, BUILDER, Set.of("minecraft", ClassicMobs.MOD_ID));
    }
}
