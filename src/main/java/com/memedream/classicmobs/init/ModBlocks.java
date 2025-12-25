package com.memedream.classicmobs.init;

import com.memedream.classicmobs.ClassicMobs;
import com.memedream.classicmobs.block.GunpowderBlock;
import com.memedream.classicmobs.block.PufferfishBlock;
import com.memedream.classicmobs.item.ModFoodProperties;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    // Initializing list of blocks that we're going to register
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ClassicMobs.MOD_ID);

    // ColoredFallingBlock needed for falling blocks like sand
    // ColorRGBA is a functionally useless 8 hex number to represent it's hex color (I used the one for gravel)
    public static final DeferredBlock<Block> GUNPOWDER_BLOCK = registerBlock("gunpowder_block",
            () -> new GunpowderBlock(
                    BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sound(SoundType.SAND)));

    public static final DeferredBlock<Block> ROTTEN_FLESH_BLOCK = registerBlockEdible("rotten_flesh_block",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.ZOMBIE).strength(2.0F).sound(SoundType.FROGSPAWN)),
            ModFoodProperties.ROTTEN_FLESH_BLOCK);

    public static final DeferredBlock<Block> RAW_BEEF_BLOCK = registerBlockEdible("raw_beef_block",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.COW_BELL).strength(2.0F).sound(SoundType.FROGSPAWN)),
            ModFoodProperties.RAW_BEEF_BLOCK);

    public static final DeferredBlock<Block> COOKED_BEEF_BLOCK = registerBlockEdible("cooked_beef_block",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.COW_BELL).strength(2.0F).sound(SoundType.FROGSPAWN)),
            ModFoodProperties.COOKED_BEEF_BLOCK);

    public static final DeferredBlock<Block> RAW_MUTTON_BLOCK = registerBlockEdible("raw_mutton_block",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.COW_BELL).strength(2.0F).sound(SoundType.FROGSPAWN)),
            ModFoodProperties.RAW_MUTTON_BLOCK);

    public static final DeferredBlock<Block> COOKED_MUTTON_BLOCK = registerBlockEdible("cooked_mutton_block",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.COW_BELL).strength(2.0F).sound(SoundType.FROGSPAWN)),
            ModFoodProperties.COOKED_MUTTON_BLOCK);

    public static final DeferredBlock<Block> RAW_PORK_BLOCK = registerBlockEdible("raw_pork_block",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.COW_BELL).strength(2.0F).sound(SoundType.FROGSPAWN)),
            ModFoodProperties.RAW_PORK_BLOCK);

    public static final DeferredBlock<Block> COOKED_PORK_BLOCK = registerBlockEdible("cooked_pork_block",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.COW_BELL).strength(2.0F).sound(SoundType.FROGSPAWN)),
            ModFoodProperties.COOKED_PORK_BLOCK);

    public static final DeferredBlock<Block> RAW_RABBIT_BLOCK = registerBlockEdible("raw_rabbit_block",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.COW_BELL).strength(2.0F).sound(SoundType.FROGSPAWN)),
            ModFoodProperties.RAW_RABBIT_BLOCK);

    public static final DeferredBlock<Block> COOKED_RABBIT_BLOCK = registerBlockEdible("cooked_rabbit_block",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.COW_BELL).strength(2.0F).sound(SoundType.FROGSPAWN)),
            ModFoodProperties.COOKED_RABBIT_BLOCK);

    public static final DeferredBlock<Block> RAW_CHICKEN_BLOCK = registerBlockEdible("raw_chicken_block",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.COW_BELL).strength(2.0F).sound(SoundType.FROGSPAWN)),
            ModFoodProperties.RAW_CHICKEN_BLOCK);

    public static final DeferredBlock<Block> COOKED_CHICKEN_BLOCK = registerBlockEdible("cooked_chicken_block",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.COW_BELL).strength(2.0F).sound(SoundType.FROGSPAWN)),
            ModFoodProperties.COOKED_CHICKEN_BLOCK);

    public static final DeferredBlock<Block> RAW_SALMON_BLOCK = registerBlockEdible("raw_salmon_block",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.COW_BELL).strength(2.0F).sound(SoundType.FROGSPAWN)),
            ModFoodProperties.RAW_SALMON_BLOCK);

    public static final DeferredBlock<Block> COOKED_SALMON_BLOCK = registerBlockEdible("cooked_salmon_block",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.COW_BELL).strength(2.0F).sound(SoundType.FROGSPAWN)),
            ModFoodProperties.COOKED_SALMON_BLOCK);

    public static final DeferredBlock<Block> RAW_COD_BLOCK = registerBlockEdible("raw_cod_block",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.COW_BELL).strength(2.0F).sound(SoundType.FROGSPAWN)),
            ModFoodProperties.RAW_COD_BLOCK);

    public static final DeferredBlock<Block> COOKED_COD_BLOCK = registerBlockEdible("cooked_cod_block",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.COW_BELL).strength(2.0F).sound(SoundType.FROGSPAWN)),
            ModFoodProperties.COOKED_COD_BLOCK);

    public static final DeferredBlock<Block> TROPICAL_FISH_BLOCK = registerBlockEdible("tropical_fish_block",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.COW_BELL).strength(2.0F).sound(SoundType.FROGSPAWN)),
            ModFoodProperties.TROPICAL_FISH_BLOCK);

    public static final DeferredBlock<Block> PUFFERFISH_BLOCK = registerBlockEdible("pufferfish_block",
            () -> new PufferfishBlock(BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.COW_BELL).strength(2.0F).sound(SoundType.FROGSPAWN)),
            ModFoodProperties.PUFFERFISH_BLOCK);

    // Main function that registers the block & item using helper
    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        // This registers the block itself
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    // Main function that registers the block & item using helper if it's edible
    private static <T extends Block> DeferredBlock<T> registerBlockEdible(String name, Supplier<T> block, FoodProperties foodProperties) {
        // This registers the block itself
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItemEdible(name, toReturn, foodProperties);
        return toReturn;
    }

    // Helper function to create and register a block's associated item
    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    // Helper function to create and register a block's associated item IF IT IS EDIBLE
    private static <T extends Block> void registerBlockItemEdible(String name, DeferredBlock<T> block, FoodProperties foodProperties) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().food(foodProperties)));
    }

    // Function ships the list of blocks to the mod itself
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
