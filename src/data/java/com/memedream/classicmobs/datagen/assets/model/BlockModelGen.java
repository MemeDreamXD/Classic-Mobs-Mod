package com.memedream.classicmobs.datagen.assets.model;

import com.memedream.classicmobs.block.KettleBlock;
import com.memedream.classicmobs.block.MeatBlock;
import com.memedream.classicmobs.init.ModBlocks;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.BlockModelDefinitionGenerator;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.Block;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

//TODO Gizmo: finish block datagen
public class BlockModelGen extends BlockModelGenerators {

    public BlockModelGen(Consumer<BlockModelDefinitionGenerator> blockStateOutput, ItemModelOutput itemModelOutput, BiConsumer<Identifier, ModelInstance> modelOutput) {
        super(blockStateOutput, itemModelOutput, modelOutput);
    }

    @Override
    public void run() {
        this.wrapBlockItem(ModBlocks.CHITIN_BLOCK.get(), this::createTrivialCube);
        this.wrapBlockItem(ModBlocks.GUNPOWDER_BLOCK.get(), this::createTrivialCube);
        this.wrapBlockItem(ModBlocks.LEATHER_BLOCK.get(), block -> this.blockStateOutput.accept(createSimpleBlock(block, plainVariant(TexturedModel.CUBE_TOP_BOTTOM.create(block, this.modelOutput)))));
        this.wrapBlockItem(ModBlocks.PHANTOM_MEMBRANE_BLOCK.get(), this::createTrivialCube);
        this.wrapBlockItem(ModBlocks.ROTTEN_FLESH_BLOCK.get(), this::createTrivialCube);
        this.wrapBlockItem(ModBlocks.RUBY_ORE.get(), this::createTrivialCube);
        this.wrapBlockItem(ModBlocks.RUBY_BLOCK.get(), this::createTrivialCube);
        this.wrapBlockItem(ModBlocks.PALM_PLANKS.get(), this::createTrivialCube);
        this.woodProvider(ModBlocks.PALM_LOG.get()).logWithHorizontal(ModBlocks.PALM_LOG.get()).wood(ModBlocks.PALM_WOOD.get());
        this.woodProvider(ModBlocks.STRIPPED_PALM_LOG.get()).logWithHorizontal(ModBlocks.STRIPPED_PALM_LOG.get()).wood(ModBlocks.STRIPPED_PALM_WOOD.get());
        TextureSlot overlay = TextureSlot.create("overlay");
        Identifier blockModel = ModelTemplates.create("palm_leaves", TextureSlot.PARTICLE, TextureSlot.ALL, overlay).extend().parent(Identifier.withDefaultNamespace("block/block"))
            .element(builder -> builder.allFaces((direction, faceBuilder) -> faceBuilder.texture(TextureSlot.ALL).tintindex(0).uvs(0, 0, 16, 16).cullface(direction)))
            .element(builder -> builder.allFaces((direction, faceBuilder) -> faceBuilder.texture(overlay).tintindex(1).uvs(0, 0, 16, 16).cullface(direction))).build()
            .create(ModBlocks.PALM_LEAVES.get(), TextureMapping.cube(ModBlocks.PALM_LEAVES.get())
                .put(overlay, TextureMapping.getBlockTexture(ModBlocks.PALM_LEAVES.get(), "_overlay"))
                .put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(ModBlocks.PALM_LEAVES.get())), this.modelOutput);
        this.blockStateOutput.accept(createSimpleBlock(ModBlocks.PALM_LEAVES.get(), plainVariant(blockModel)));
        this.registerSimpleTintedItemModel(ModBlocks.PALM_LEAVES.get(), blockModel, ItemModelUtils.constantTint(FoliageColor.FOLIAGE_DEFAULT));
        this.createPlantWithDefaultItem(ModBlocks.PALM_SAPLING.get(), ModBlocks.POTTED_PALM_SAPLING.get(), BlockModelGenerators.PlantType.NOT_TINTED);

        this.generateMeatBlock(ModBlocks.RAW_BEEF_BLOCK.get(), "");
        this.generateMeatBlock(ModBlocks.COOKED_BEEF_BLOCK.get(), "");
        this.generateMeatBlock(ModBlocks.RAW_MUTTON_BLOCK.get(), "");
        this.generateMeatBlock(ModBlocks.COOKED_MUTTON_BLOCK.get(), "");
        this.generateMeatBlock(ModBlocks.RAW_PORK_BLOCK.get(), "");
        this.generateMeatBlock(ModBlocks.COOKED_PORK_BLOCK.get(), "");
        this.generateMeatBlock(ModBlocks.RAW_DODO_BLOCK.get(), "");
        this.generateMeatBlock(ModBlocks.COOKED_DODO_BLOCK.get(), "");
        this.generateMeatBlock(ModBlocks.RAW_RABBIT_BLOCK.get(), "thin_");
        this.generateMeatBlock(ModBlocks.COOKED_RABBIT_BLOCK.get(), "thin_");
        this.generateMeatBlock(ModBlocks.RAW_CHICKEN_BLOCK.get(), "thin_");
        this.generateMeatBlock(ModBlocks.COOKED_CHICKEN_BLOCK.get(), "thin_");
        this.generateMeatBlock(ModBlocks.RAW_COD_BLOCK.get(), "boneless_");
        this.generateMeatBlock(ModBlocks.COOKED_COD_BLOCK.get(), "boneless_");
        this.generateMeatBlock(ModBlocks.RAW_SALMON_BLOCK.get(), "boneless_");
        this.generateMeatBlock(ModBlocks.COOKED_SALMON_BLOCK.get(), "boneless_");
        this.generateMeatBlock(ModBlocks.TROPICAL_FISH_BLOCK.get(), "boneless_");

        this.wrapBlockItem(ModBlocks.PUFFERFISH_BLOCK.get(), block -> this.blockStateOutput.accept(MultiVariantGenerator.dispatch(block, plainVariant(ModelTemplates.CUBE_BOTTOM_TOP.create(block, new TextureMapping().put(TextureSlot.SIDE, TextureMapping.getBlockTexture(block)).put(TextureSlot.TOP, TextureMapping.getBlockTexture(block, "_top")).put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(block, "_bottom")), this.modelOutput))).with(ROTATIONS_COLUMN_WITH_FACING)));

        this.generateBlockItem(ModBlocks.MAGMA_CREAM_BLOCK.get());
        this.generateBlockItem(ModBlocks.STRING_BLOCK.get());
        this.generateBlockItem(ModBlocks.BLOCK_OF_BONES.get());
        this.generateBlockItem(ModBlocks.BLAZE_ROD_BLOCK.get());
        this.generateBlockItem(ModBlocks.ENDER_PEARL_BLOCK.get());
        this.generateBlockItem(ModBlocks.BREEZE_ROD_BLOCK.get());
        this.generateBlockItem(ModBlocks.SPIDER_EYE_BLOCK.get());
        this.generateBlockItem(ModBlocks.FERMENTED_SPIDER_EYE_BLOCK.get());

        this.wrapBlockItem(ModBlocks.KETTLE.get(), block -> this.blockStateOutput.accept(MultiVariantGenerator.dispatch(block).with(createBooleanModelDispatch(KettleBlock.TRAY, plainVariant(ModelLocationUtils.getModelLocation(block, "_tray")), plainVariant(ModelLocationUtils.getModelLocation(block)))).with(ROTATION_HORIZONTAL_FACING)));
    }

    private void generateMeatBlock(Block block, String prefix) {
        TextureMapping mapping = new TextureMapping().put(TextureSlot.SIDE, TextureMapping.getBlockTexture(block)).put(TextureSlot.END, TextureMapping.getBlockTexture(block, "_top"));
        MultiVariant fullBlock = plainVariant(ModelTemplates.CUBE_COLUMN.create(block, mapping, this.modelOutput));

        this.blockStateOutput.accept(MultiVariantGenerator.dispatch(block)
            .with(PropertyDispatch.initial(MeatBlock.BITES).generate(bites ->
                bites == 0 ? fullBlock : plainVariant(ModelTemplates.create("classic_mobs:template/" + prefix + "meat_block_bite_" + bites, TextureSlot.SIDE, TextureSlot.END).createWithSuffix(block, "_bite_" + bites, mapping, this.modelOutput))))
            .with(ROTATIONS_COLUMN_WITH_FACING));
        this.generateBlockItem(block);
    }

    public void wrapBlockItem(Block block, Consumer<Block> blockRegistry) {
        blockRegistry.accept(block);
        this.generateBlockItem(block);
    }

    public void generateBlockItem(Block block) {
        this.registerSimpleItemModel(block, BuiltInRegistries.BLOCK.getKey(block).withPrefix("block/"));
    }
}
