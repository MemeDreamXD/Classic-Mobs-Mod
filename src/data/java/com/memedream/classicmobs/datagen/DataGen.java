package com.memedream.classicmobs.datagen;

import com.memedream.classicmobs.ClassicMobs;
import com.memedream.classicmobs.datagen.assets.EquipmentAssetGen;
import com.memedream.classicmobs.datagen.assets.LangGen;
import com.memedream.classicmobs.datagen.assets.ParticleGen;
import com.memedream.classicmobs.datagen.assets.SoundGen;
import com.memedream.classicmobs.datagen.assets.model.ModelGen;
import com.memedream.classicmobs.datagen.data.DataMapGen;
import com.memedream.classicmobs.datagen.data.RegistryDataGen;
import com.memedream.classicmobs.datagen.data.loot.LootGen;
import com.memedream.classicmobs.datagen.data.recipe.CraftingGenRunner;
import com.memedream.classicmobs.datagen.data.tags.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = ClassicMobs.MOD_ID)
public class DataGen {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent.Client event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = event.getGenerator().getPackOutput();

        DatapackBuiltinEntriesProvider datapackProvider = new RegistryDataGen(output, event.getLookupProvider());
        CompletableFuture<HolderLookup.Provider> lookupProvider = datapackProvider.getRegistryProvider();

        // -- ASSETS --
        generator.addProvider(true, new SoundGen(output));
        generator.addProvider(true, new LangGen(output));
        generator.addProvider(true, new EquipmentAssetGen(output));
        generator.addProvider(true, new ParticleGen(output));
        generator.addProvider(true, new ModelGen(output));

        // -- DATA --
        generator.addProvider(true, datapackProvider);
        generator.addProvider(true, new CraftingGenRunner(output, lookupProvider));
        generator.addProvider(true, new LootGen(output, lookupProvider));
        generator.addProvider(true, new DataMapGen(output, lookupProvider));
        //tags
        generator.addProvider(true, new BiomeTagGen(output, lookupProvider));
        generator.addProvider(true, new BlockTagGen(output, lookupProvider));
        generator.addProvider(true, new ItemTagGen(output, lookupProvider));
        generator.addProvider(true, new EntityTagGen(output, lookupProvider));
        generator.addProvider(true, new DamageTagGen(output, lookupProvider));
    }
}
