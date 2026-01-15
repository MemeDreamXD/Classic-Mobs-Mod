package com.memedream.classicmobs.datagen.assets.model;

import com.memedream.classicmobs.ClassicMobs;
import com.memedream.classicmobs.init.ModBlocks;
import com.memedream.classicmobs.init.ModItems;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.core.Holder;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public class ModelGen extends ModelProvider {
    private final PackOutput.PathProvider blocks;
    private final PackOutput.PathProvider items;
    private final PackOutput.PathProvider models;

    public ModelGen(PackOutput packOutput) {
        super(packOutput, ClassicMobs.MOD_ID);
        this.blocks = packOutput.createPathProvider(PackOutput.Target.RESOURCE_PACK, "blockstates");
        this.items = packOutput.createPathProvider(PackOutput.Target.RESOURCE_PACK, "items");
        this.models = packOutput.createPathProvider(PackOutput.Target.RESOURCE_PACK, "models");
    }

    @Override
    public CompletableFuture<?> run(CachedOutput output) {
        ItemInfoCollector itemModelOutput = new ItemInfoCollector(this::getKnownItems);
        BlockStateGeneratorCollector blockModelOutput = new BlockStateGeneratorCollector(this::getKnownBlocks);
        SimpleModelCollector modelOutput = new SimpleModelCollector();
        this.registerModels(new BlockModelGen(blockModelOutput, itemModelOutput, modelOutput), new ItemModelGen(itemModelOutput, modelOutput));
//		blockModelOutput.validate();
//		itemModelOutput.finalizeAndValidate();
        return CompletableFuture.allOf(blockModelOutput.save(output, this.blocks), modelOutput.save(output, this.models), itemModelOutput.save(output, this.items));
    }

    @Override
    protected Stream<? extends Holder<Block>> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream();
    }

    @Override
    protected Stream<? extends Holder<Item>> getKnownItems() {
        return ModItems.ITEMS.getEntries().stream();
    }
}
