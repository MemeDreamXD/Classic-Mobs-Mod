package com.memedream.classicmobs.init;

import com.memedream.classicmobs.ClassicMobs;
import com.memedream.classicmobs.block.*;
import com.memedream.classicmobs.item.MeatBlockItem;
import com.memedream.classicmobs.item.ModFoodProperties;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    // Initializing list of blocks that we're going to register
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ClassicMobs.MOD_ID);

    public static final DeferredBlock<Block> TRICKLITH_BLOCK = registerBlock("tricklith_block",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).requiresCorrectToolForDrops().instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.DRIPSTONE_BLOCK).randomTicks().strength(1.5F, 3.0F)));

    public static final DeferredBlock<PointedTricklithBlock> POINTED_TRICKLITH = registerBlock("pointed_tricklith",
            () -> new PointedTricklithBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).requiresCorrectToolForDrops().forceSolidOn().instrument(NoteBlockInstrument.BASEDRUM).noOcclusion().sound(SoundType.POINTED_DRIPSTONE).randomTicks().strength(1.5F, 3.0F).dynamicShape().offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY)));

    //TODO: Figure out the name for this block lol
    //public static final DeferredBlock<Block> UNDERSHALE = registerBlock("undershale",
    //        () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.DEEPSLATE).requiresCorrectToolForDrops().instrument(NoteBlockInstrument.BASEDRUM).strength(4.5F, 6.0F).sound(SoundType.DEEPSLATE)));

    //TODO: Give logic to use different textures depending on stack height of the block.
    public static final DeferredBlock<Block> CAVERRNACK = registerBlock("caverrnack",
            () -> new CaverrnackBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().instrument(NoteBlockInstrument.SNARE).strength(2.0F, 4.0F).sound(SoundType.NETHERRACK)));

    public static final DeferredBlock<Block> GUNPOWDER_BLOCK = registerBlock("gunpowder_block",
            () -> new GunpowderBlock(
                    BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sound(SoundType.SAND)));

    public static final DeferredBlock<Block> ROTTEN_FLESH_BLOCK = registerBlockEdible("rotten_flesh_block",
            () -> new RottenFleshBlock(BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.ZOMBIE).strength(2.0F).sound(SoundType.FROGSPAWN)),
            ModFoodProperties.ROTTEN_FLESH_BLOCK);

    //TODO: Should reflect projectiles
    public static final DeferredBlock<Block> CHITIN_BLOCK = registerBlock("chitin_block",
            () -> new Block(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().mapColor(MapColor.COLOR_BROWN).instrument(NoteBlockInstrument.DIDGERIDOO).strength(1.5F).sound(SoundType.PACKED_MUD)));

    //TODO: Disallow entities from falling through
    public static final DeferredBlock<Block> STRING_BLOCK = registerBlock("string_block",
            () -> new WebBlock(BlockBehaviour.Properties.of().noOcclusion().mapColor(MapColor.SNOW).forceSolidOn().noCollission().instrument(NoteBlockInstrument.GUITAR).strength(0.8F).sound(SoundType.COBWEB).ignitedByLava()));

    public static final DeferredBlock<Block> MAGMA_CREAM_BLOCK = registerBlock("magma_cream_block",
            () -> new MagmaCreamBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE).noOcclusion().instrument(NoteBlockInstrument.PLING).strength(0.8F).sound(SoundType.SLIME_BLOCK), 1f));

    // TODO: Function like an openblocks elevator.
    public static final DeferredBlock<Block> PHANTOM_MEMBRANE_BLOCK = registerBlock("phantom_membrane_block",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).instrument(NoteBlockInstrument.XYLOPHONE).strength(0.8F).sound(SoundType.SOUL_SOIL)));

    //TODO: Texture needs redone BAD do not let me forget this. Also, should be a tempt item for wolves.
    public static final DeferredBlock<Block> BLOCK_OF_BONES = registerBlock("block_of_bones",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).instrument(NoteBlockInstrument.XYLOPHONE).requiresCorrectToolForDrops().strength(2.0F).noOcclusion().sound(SoundType.BONE_BLOCK)));

    //TODO: Speed up "furnace blocks" that the tip is pointing towards.
    public static final DeferredBlock<BlazeRodBlock> BLAZE_ROD_BLOCK = registerBlock("blaze_rod_block",
            () -> new BlazeRodBlock(BlockBehaviour.Properties.of().forceSolidOff().instabreak().lightLevel(p_187435_ -> 14).sound(SoundType.WOOD).noOcclusion()));

    public static final DeferredBlock<EnderPearlBlock> ENDER_PEARL_BLOCK = registerBlock("ender_pearl_block",
            () -> new EnderPearlBlock(BlockBehaviour.Properties.of().instabreak().sound(SoundType.AMETHYST)));

    //TODO: Push entites away from the tip like a fan block.
    public static final DeferredBlock<BreezeRodBlock> BREEZE_ROD_BLOCK = registerBlock("breeze_rod_block",
            () -> new BreezeRodBlock(BlockBehaviour.Properties.of().forceSolidOff().noOcclusion().instabreak().sound(SoundType.WOOD).noOcclusion()));

    //TODO: Should be able to ferment when right clicked with sugar+brown mushroom in main/offhand
    public static final DeferredBlock<Block> SPIDER_EYE_BLOCK = registerBlockEdible("spider_eye_block",
            () -> new SpiderEyeBlock(BlockBehaviour.Properties.of().noOcclusion().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.COW_BELL).strength(2.0F).sound(SoundType.FROGSPAWN)),
            ModFoodProperties.SPIDER_EYE_BLOCK);

    public static final DeferredBlock<Block> FERMENTED_SPIDER_EYE_BLOCK = registerBlock("fermented_spider_eye_block",
            () -> new SpiderEyeBlock(BlockBehaviour.Properties.of().noOcclusion().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.COW_BELL).strength(2.0F).sound(SoundType.FROGSPAWN)));

    public static final DeferredBlock<Block> COOKED_BEEF_BLOCK = registerBlockEdible("cooked_beef_block",
            () -> new MeatBlock(MeatBlock.BoneType.NORMAL, BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.COW_BELL).strength(2.0F).sound(SoundType.FROGSPAWN)),
            ModFoodProperties.COOKED_BEEF_BLOCK);

    public static final DeferredBlock<Block> COOKED_MUTTON_BLOCK = registerBlockEdible("cooked_mutton_block",
            () -> new MeatBlock(MeatBlock.BoneType.NORMAL, BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.COW_BELL).strength(2.0F).sound(SoundType.FROGSPAWN)),
            ModFoodProperties.COOKED_MUTTON_BLOCK);

    public static final DeferredBlock<Block> COOKED_PORK_BLOCK = registerBlockEdible("cooked_pork_block",
            () -> new MeatBlock(MeatBlock.BoneType.NORMAL, BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.COW_BELL).strength(2.0F).sound(SoundType.FROGSPAWN)),
            ModFoodProperties.COOKED_PORK_BLOCK);

    public static final DeferredBlock<Block> COOKED_RABBIT_BLOCK = registerBlockEdible("cooked_rabbit_block",
            () -> new MeatBlock(MeatBlock.BoneType.THIN, BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.COW_BELL).strength(2.0F).sound(SoundType.FROGSPAWN)),
            ModFoodProperties.COOKED_RABBIT_BLOCK);

    public static final DeferredBlock<Block> COOKED_CHICKEN_BLOCK = registerBlockEdible("cooked_chicken_block",
            () -> new MeatBlock(MeatBlock.BoneType.THIN, BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.COW_BELL).strength(2.0F).sound(SoundType.FROGSPAWN)),
            ModFoodProperties.COOKED_CHICKEN_BLOCK);

    public static final DeferredBlock<Block> COOKED_SALMON_BLOCK = registerBlockEdible("cooked_salmon_block",
            () -> new MeatBlock(MeatBlock.BoneType.NONE, BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.COW_BELL).strength(2.0F).sound(SoundType.FROGSPAWN)),
            ModFoodProperties.COOKED_SALMON_BLOCK);

    public static final DeferredBlock<Block> COOKED_COD_BLOCK = registerBlockEdible("cooked_cod_block",
            () -> new MeatBlock(MeatBlock.BoneType.NONE, BlockBehaviour.Properties.of().mapColor(MapColor.RAW_IRON).instrument(NoteBlockInstrument.COW_BELL).strength(2.0F).sound(SoundType.FROGSPAWN)),
            ModFoodProperties.COOKED_COD_BLOCK);

    public static final DeferredBlock<Block> TROPICAL_FISH_BLOCK = registerBlockEdible("tropical_fish_block",
            () -> new MeatBlock(MeatBlock.BoneType.NONE, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE).instrument(NoteBlockInstrument.COW_BELL).strength(2.0F).sound(SoundType.FROGSPAWN)),
            ModFoodProperties.TROPICAL_FISH_BLOCK);

    public static final DeferredBlock<Block> PUFFERFISH_BLOCK = BLOCKS.register("pufferfish_block",
            () -> new ConfiguredDirectionalBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).instrument(NoteBlockInstrument.COW_BELL).strength(2.0F).sound(SoundType.FROGSPAWN)));

    public static final DeferredBlock<Block> RAW_BEEF_BLOCK = registerBlockEdible("raw_beef_block",
            () -> new RawMeatBlock(COOKED_BEEF_BLOCK, MeatBlock.BoneType.NORMAL, BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.COW_BELL).strength(2.0F).sound(SoundType.FROGSPAWN)),
            ModFoodProperties.RAW_BEEF_BLOCK);

    public static final DeferredBlock<Block> RAW_MUTTON_BLOCK = registerBlockEdible("raw_mutton_block",
            () -> new RawMeatBlock(COOKED_MUTTON_BLOCK, MeatBlock.BoneType.NORMAL, BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.COW_BELL).strength(2.0F).sound(SoundType.FROGSPAWN)),
            ModFoodProperties.RAW_MUTTON_BLOCK);

    public static final DeferredBlock<Block> RAW_PORK_BLOCK = registerBlockEdible("raw_pork_block",
            () -> new RawMeatBlock(COOKED_PORK_BLOCK, MeatBlock.BoneType.NORMAL, BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.COW_BELL).strength(2.0F).sound(SoundType.FROGSPAWN)),
            ModFoodProperties.RAW_PORK_BLOCK);

    public static final DeferredBlock<Block> RAW_RABBIT_BLOCK = registerBlockEdible("raw_rabbit_block",
            () -> new RawMeatBlock(COOKED_RABBIT_BLOCK, MeatBlock.BoneType.THIN, BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.COW_BELL).strength(2.0F).sound(SoundType.FROGSPAWN)),
            ModFoodProperties.RAW_RABBIT_BLOCK);

    public static final DeferredBlock<Block> RAW_CHICKEN_BLOCK = registerBlockEdible("raw_chicken_block",
            () -> new RawMeatBlock(COOKED_CHICKEN_BLOCK, MeatBlock.BoneType.THIN, BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.COW_BELL).strength(2.0F).sound(SoundType.FROGSPAWN)),
            ModFoodProperties.RAW_CHICKEN_BLOCK);

    public static final DeferredBlock<Block> RAW_SALMON_BLOCK = registerBlockEdible("raw_salmon_block",
            () -> new RawMeatBlock(COOKED_SALMON_BLOCK, MeatBlock.BoneType.NONE, BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.COW_BELL).strength(2.0F).sound(SoundType.FROGSPAWN)),
            ModFoodProperties.RAW_SALMON_BLOCK);

    public static final DeferredBlock<Block> RAW_COD_BLOCK = registerBlockEdible("raw_cod_block",
            () -> new RawMeatBlock(COOKED_COD_BLOCK, MeatBlock.BoneType.NONE, BlockBehaviour.Properties.of().mapColor(MapColor.RAW_IRON).instrument(NoteBlockInstrument.COW_BELL).strength(2.0F).sound(SoundType.FROGSPAWN)),
            ModFoodProperties.RAW_COD_BLOCK);

    // TODO: Make Dyeable.
    public static final DeferredBlock<Block> LEATHER_BLOCK = registerBlock("leather_block",
            () -> new ConfiguredDirectionalBlock(BlockBehaviour.Properties.of().mapColor(MapColor.DIRT).instrument(NoteBlockInstrument.COW_BELL).strength(0.8F).sound(SoundType.WOOL)));
//TODO: Make rabbit hide "slab-slab" block (should be able to be stacked four times to create a full block"


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
        ModItems.ITEMS.register(name, () -> new MeatBlockItem(block.get(), new Item.Properties().food(foodProperties)));
    }
}
