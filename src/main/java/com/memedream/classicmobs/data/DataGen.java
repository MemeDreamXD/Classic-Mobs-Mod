package com.memedream.classicmobs.data;

import com.memedream.classicmobs.ClassicMobs;
import com.memedream.classicmobs.data.tags.BlockTagGen;
import com.memedream.classicmobs.data.tags.DamageTagGen;
import com.memedream.classicmobs.data.tags.EntityTagGen;
import com.memedream.classicmobs.data.tags.ItemTagGen;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = ClassicMobs.MOD_ID)
public class DataGen {

	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		PackOutput output = event.getGenerator().getPackOutput();
		ExistingFileHelper helper = event.getExistingFileHelper();

		DatapackBuiltinEntriesProvider datapackProvider = new RegistryDataGen(output, event.getLookupProvider());
		CompletableFuture<HolderLookup.Provider> lookupProvider = datapackProvider.getRegistryProvider();

		BlockTagGen blockTag = new BlockTagGen(output, lookupProvider, helper);
		generator.addProvider(event.includeServer(), blockTag);
		generator.addProvider(event.includeServer(), new ItemTagGen(output, lookupProvider, blockTag.contentsGetter(), helper));
		generator.addProvider(event.includeServer(), new EntityTagGen(output, lookupProvider, helper));
		generator.addProvider(event.includeServer(), new DamageTagGen(output, lookupProvider, helper));
	}
}
