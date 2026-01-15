package com.memedream.classicmobs.datagen.assets.model;

import com.memedream.classicmobs.block.MeatBlock;
import com.memedream.classicmobs.init.ModBlocks;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.BlockModelDefinitionGenerator;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.renderer.block.model.Variant;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.StairsShape;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

//TODO Gizmo: finish block datagen
public class BlockModelGen extends BlockModelGenerators {

    public BlockModelGen(Consumer<BlockModelDefinitionGenerator> blockStateOutput, ItemModelOutput itemModelOutput, BiConsumer<Identifier, ModelInstance> modelOutput) {
        super(blockStateOutput, itemModelOutput, modelOutput);
    }

    @Override
    public void run() {
        this.wrapBlockItem(ModBlocks.CAVERRNACK.get(), this::createTrivialCube);
        this.wrapBlockItem(ModBlocks.CHISELED_UNDERSHALE.get(), this::createTrivialCube);
        this.wrapBlockItem(ModBlocks.CHITIN_BLOCK.get(), this::createTrivialCube);
        this.wrapBlockItem(ModBlocks.GUNPOWDER_BLOCK.get(), this::createTrivialCube);
        this.wrapBlockItem(ModBlocks.LEATHER_BLOCK.get(), block -> this.blockStateOutput.accept(createSimpleBlock(block, plainVariant(TexturedModel.CUBE_TOP_BOTTOM.create(block, this.modelOutput)))));
        this.wrapBlockItem(ModBlocks.NACRITE.get(), this::createTrivialCube);
        this.wrapBlockItem(ModBlocks.PHANTOM_MEMBRANE_BLOCK.get(), this::createTrivialCube);
        this.wrapBlockItem(ModBlocks.POLISHED_UNDERSHALE.get(), block -> {
            Variant[] variants = new Variant[6];
            for (int i = 0; i < 6; i++) {
                String suffix = i == 0 ? "" : ("_" + i);
                variants[i] = plainModel(ModelTemplates.CUBE_COLUMN.createWithSuffix(block, suffix, new TextureMapping()
                    .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(block, suffix))
                    .put(TextureSlot.END, TextureMapping.getBlockTexture(block, "_top")), this.modelOutput));
            }
            this.blockStateOutput.accept(MultiVariantGenerator.dispatch(block, variants(variants)));
        });
        this.wrapBlockItem(ModBlocks.ROTTEN_FLESH_BLOCK.get(), this::createTrivialCube);
        this.wrapBlockItem(ModBlocks.RUBY_ORE.get(), this::createTrivialCube);
        this.wrapBlockItem(ModBlocks.TRICKLITH_BLOCK.get(), this::createTrivialCube);
        this.wrapBlockItem(ModBlocks.UNDERSHALE_BRICKS.get(), this::createTrivialCube);

        this.generateMeatBlock(ModBlocks.RAW_BEEF_BLOCK.get(), "");
        this.generateMeatBlock(ModBlocks.COOKED_BEEF_BLOCK.get(), "");
        this.generateMeatBlock(ModBlocks.RAW_MUTTON_BLOCK.get(), "");
        this.generateMeatBlock(ModBlocks.COOKED_MUTTON_BLOCK.get(), "");
        this.generateMeatBlock(ModBlocks.RAW_PORK_BLOCK.get(), "");
        this.generateMeatBlock(ModBlocks.COOKED_PORK_BLOCK.get(), "");
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
        this.wrapBlockItem(ModBlocks.CARVED_UNDERSHALE_TILE.get(), block -> this.blockStateOutput.accept(MultiVariantGenerator.dispatch(block, plainVariant(ModelLocationUtils.getModelLocation(block))).with(ROTATION_HORIZONTAL_FACING)));
        this.wrapBlockItem(ModBlocks.CARVED_UNDERSHALE_TILE_EDGE.get(), block -> {
            MultiVariant inner = BlockModelGenerators.plainVariant(ModelLocationUtils.getModelLocation(block, "_inner"));
            MultiVariant straight = BlockModelGenerators.plainVariant(ModelLocationUtils.getModelLocation(block));
            MultiVariant outer = BlockModelGenerators.plainVariant(ModelLocationUtils.getModelLocation(block, "_outer"));
            this.blockStateOutput.accept(MultiVariantGenerator.dispatch(block).with(PropertyDispatch.initial(BlockStateProperties.HORIZONTAL_FACING, BlockStateProperties.STAIRS_SHAPE)
                .select(Direction.EAST, StairsShape.STRAIGHT, straight)
                .select(Direction.WEST, StairsShape.STRAIGHT, straight.with(Y_ROT_180).with(UV_LOCK))
                .select(Direction.SOUTH, StairsShape.STRAIGHT, straight.with(Y_ROT_90).with(UV_LOCK))
                .select(Direction.NORTH, StairsShape.STRAIGHT, straight.with(Y_ROT_270).with(UV_LOCK))
                .select(Direction.EAST, StairsShape.OUTER_RIGHT, outer)
                .select(Direction.WEST, StairsShape.OUTER_RIGHT, outer.with(Y_ROT_180).with(UV_LOCK))
                .select(Direction.SOUTH, StairsShape.OUTER_RIGHT, outer.with(Y_ROT_90).with(UV_LOCK))
                .select(Direction.NORTH, StairsShape.OUTER_RIGHT, outer.with(Y_ROT_270).with(UV_LOCK))
                .select(Direction.EAST, StairsShape.OUTER_LEFT, outer.with(Y_ROT_270).with(UV_LOCK))
                .select(Direction.WEST, StairsShape.OUTER_LEFT, outer.with(Y_ROT_90).with(UV_LOCK))
                .select(Direction.SOUTH, StairsShape.OUTER_LEFT, outer)
                .select(Direction.NORTH, StairsShape.OUTER_LEFT, outer.with(Y_ROT_180).with(UV_LOCK))
                .select(Direction.EAST, StairsShape.INNER_RIGHT, inner)
                .select(Direction.WEST, StairsShape.INNER_RIGHT, inner.with(Y_ROT_180).with(UV_LOCK))
                .select(Direction.SOUTH, StairsShape.INNER_RIGHT, inner.with(Y_ROT_90).with(UV_LOCK))
                .select(Direction.NORTH, StairsShape.INNER_RIGHT, inner.with(Y_ROT_270).with(UV_LOCK))
                .select(Direction.EAST, StairsShape.INNER_LEFT, inner.with(Y_ROT_270).with(UV_LOCK))
                .select(Direction.WEST, StairsShape.INNER_LEFT, inner.with(Y_ROT_90).with(UV_LOCK))
                .select(Direction.SOUTH, StairsShape.INNER_LEFT, inner)
                .select(Direction.NORTH, StairsShape.INNER_LEFT, inner.with(Y_ROT_180).with(UV_LOCK))));
        });
        this.generateBlockItem(ModBlocks.STRING_BLOCK.get());
        this.generateBlockItem(ModBlocks.BLOCK_OF_BONES.get());
        this.generateBlockItem(ModBlocks.BLAZE_ROD_BLOCK.get());
        this.generateBlockItem(ModBlocks.ENDER_PEARL_BLOCK.get());
        this.generateBlockItem(ModBlocks.BREEZE_ROD_BLOCK.get());
        this.generateBlockItem(ModBlocks.SPIDER_EYE_BLOCK.get());
        this.generateBlockItem(ModBlocks.FERMENTED_SPIDER_EYE_BLOCK.get());
        this.registerSimpleFlatItemModel(ModBlocks.POINTED_TRICKLITH.asItem());
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
