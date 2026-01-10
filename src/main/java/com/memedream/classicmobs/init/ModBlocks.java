package com.memedream.classicmobs.init;

import com.memedream.classicmobs.ClassicMobs;
import com.memedream.classicmobs.block.*;
import com.memedream.classicmobs.item.ModFoodProperties;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
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

    public static final DeferredBlock<Block> RUBY_ORE = registerBlock("ruby_ore",
            () -> new DropExperienceBlock(UniformInt.of(3, 7), BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().sound(SoundType.NETHER_ORE).strength(3.0F, 3.0F)),
            new Item.Properties().rarity(Rarity.UNCOMMON));

    //TODO: Figure out a texture for this block lol
    //public static final DeferredBlock<Block> UNDERSHALE = registerBlock("undershale",
    //        () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.DEEPSLATE).requiresCorrectToolForDrops().instrument(NoteBlockInstrument.BASEDRUM).strength(4.5F, 6.0F).sound(SoundType.DEEPSLATE)));

    public static final DeferredBlock<Block> UNDERSHALE_BRICKS = registerBlock("undershale_bricks",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.DEEPSLATE).requiresCorrectToolForDrops().instrument(NoteBlockInstrument.BASEDRUM).strength(4.5F, 6.0F).sound(SoundType.DEEPSLATE)));

    public static final DeferredBlock<Block> CHISELED_UNDERSHALE = registerBlock("chiseled_undershale",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.DEEPSLATE).requiresCorrectToolForDrops().instrument(NoteBlockInstrument.BASEDRUM).strength(4.5F, 6.0F).sound(SoundType.DEEPSLATE)));

    public static final DeferredBlock<Block> POLISHED_UNDERSHALE = registerBlock("polished_undershale",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.DEEPSLATE).requiresCorrectToolForDrops().instrument(NoteBlockInstrument.BASEDRUM).strength(4.5F, 6.0F).sound(SoundType.DEEPSLATE)));

    public static final DeferredBlock<Block> NACRITE = registerBlock("nacrite",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_MAGENTA).requiresCorrectToolForDrops().instrument(NoteBlockInstrument.BASEDRUM).strength(2.625F, 3.375F).sound(SoundType.CALCITE)));

    //TODO: Give logic to use different textures depending on stack height of the block.
    public static final DeferredBlock<Block> CAVERRNACK = registerBlock("caverrnack",
            () -> new CaverrnackBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().instrument(NoteBlockInstrument.SNARE).strength(2.0F, 4.0F).sound(SoundType.NETHERRACK)));

    public static final DeferredBlock<Block> GUNPOWDER_BLOCK = registerBlock("gunpowder_block",
            () -> new GunpowderBlock(
                    BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sound(SoundType.SAND)));

    public static final DeferredBlock<Block> ROTTEN_FLESH_BLOCK = registerBlock("rotten_flesh_block",
            () -> new RottenFleshBlock(BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.ZOMBIE).strength(2.0F).sound(SoundType.FROGSPAWN)),
            new Item.Properties().food(ModFoodProperties.ROTTEN_FLESH_BLOCK));

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

    //TODO: Texture/model needs redone BAD do not let me forget this. Also, should be a tempt item for wolves.
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
    public static final DeferredBlock<Block> SPIDER_EYE_BLOCK = registerBlock("spider_eye_block",
            () -> new SpiderEyeBlock(BlockBehaviour.Properties.of().noOcclusion().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.COW_BELL).strength(2.0F).sound(SoundType.FROGSPAWN)),
            new Item.Properties().food(ModFoodProperties.SPIDER_EYE_BLOCK));

    public static final DeferredBlock<Block> FERMENTED_SPIDER_EYE_BLOCK = registerBlock("fermented_spider_eye_block",
            () -> new SpiderEyeBlock(BlockBehaviour.Properties.of().noOcclusion().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.COW_BELL).strength(2.0F).sound(SoundType.FROGSPAWN)));

    public static final DeferredBlock<Block> COOKED_BEEF_BLOCK = registerBlock("cooked_beef_block",
            () -> new MeatBlock(MeatBlock.BoneType.NORMAL, BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.COW_BELL).strength(2.0F).sound(SoundType.FROGSPAWN)),
            new Item.Properties().food(ModFoodProperties.COOKED_BEEF_BLOCK));

    public static final DeferredBlock<Block> COOKED_MUTTON_BLOCK = registerBlock("cooked_mutton_block",
            () -> new MeatBlock(MeatBlock.BoneType.NORMAL, BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.COW_BELL).strength(2.0F).sound(SoundType.FROGSPAWN)),
            new Item.Properties().food(ModFoodProperties.COOKED_MUTTON_BLOCK));

    public static final DeferredBlock<Block> COOKED_PORK_BLOCK = registerBlock("cooked_pork_block",
            () -> new MeatBlock(MeatBlock.BoneType.NORMAL, BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.COW_BELL).strength(2.0F).sound(SoundType.FROGSPAWN)),
            new Item.Properties().food(ModFoodProperties.COOKED_PORK_BLOCK));

    public static final DeferredBlock<Block> COOKED_RABBIT_BLOCK = registerBlock("cooked_rabbit_block",
            () -> new MeatBlock(MeatBlock.BoneType.THIN, BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.COW_BELL).strength(2.0F).sound(SoundType.FROGSPAWN)),
            new Item.Properties().food(ModFoodProperties.COOKED_RABBIT_BLOCK));

    public static final DeferredBlock<Block> COOKED_CHICKEN_BLOCK = registerBlock("cooked_chicken_block",
            () -> new MeatBlock(MeatBlock.BoneType.THIN, BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.COW_BELL).strength(2.0F).sound(SoundType.FROGSPAWN)),
            new Item.Properties().food(ModFoodProperties.COOKED_CHICKEN_BLOCK));

    public static final DeferredBlock<Block> COOKED_SALMON_BLOCK = registerBlock("cooked_salmon_block",
            () -> new MeatBlock(MeatBlock.BoneType.NONE, BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.COW_BELL).strength(2.0F).sound(SoundType.FROGSPAWN)),
            new Item.Properties().food(ModFoodProperties.COOKED_SALMON_BLOCK));

    public static final DeferredBlock<Block> COOKED_COD_BLOCK = registerBlock("cooked_cod_block",
            () -> new MeatBlock(MeatBlock.BoneType.NONE, BlockBehaviour.Properties.of().mapColor(MapColor.RAW_IRON).instrument(NoteBlockInstrument.COW_BELL).strength(2.0F).sound(SoundType.FROGSPAWN)),
            new Item.Properties().food(ModFoodProperties.COOKED_COD_BLOCK));

    public static final DeferredBlock<Block> TROPICAL_FISH_BLOCK = registerBlock("tropical_fish_block",
            () -> new MeatBlock(MeatBlock.BoneType.NONE, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE).instrument(NoteBlockInstrument.COW_BELL).strength(2.0F).sound(SoundType.FROGSPAWN)),
            new Item.Properties().food(ModFoodProperties.TROPICAL_FISH_BLOCK));

    public static final DeferredBlock<Block> PUFFERFISH_BLOCK = BLOCKS.register("pufferfish_block",
            () -> new ConfiguredDirectionalBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).instrument(NoteBlockInstrument.COW_BELL).strength(2.0F).sound(SoundType.FROGSPAWN)));

    public static final DeferredBlock<Block> RAW_BEEF_BLOCK = registerBlock("raw_beef_block",
            () -> new RawMeatBlock(COOKED_BEEF_BLOCK, MeatBlock.BoneType.NORMAL, BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.COW_BELL).strength(2.0F).sound(SoundType.FROGSPAWN)),
            new Item.Properties().food(ModFoodProperties.RAW_BEEF_BLOCK));

    public static final DeferredBlock<Block> RAW_MUTTON_BLOCK = registerBlock("raw_mutton_block",
            () -> new RawMeatBlock(COOKED_MUTTON_BLOCK, MeatBlock.BoneType.NORMAL, BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.COW_BELL).strength(2.0F).sound(SoundType.FROGSPAWN)),
            new Item.Properties().food(ModFoodProperties.RAW_MUTTON_BLOCK));

    public static final DeferredBlock<Block> RAW_PORK_BLOCK = registerBlock("raw_pork_block",
            () -> new RawMeatBlock(COOKED_PORK_BLOCK, MeatBlock.BoneType.NORMAL, BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.COW_BELL).strength(2.0F).sound(SoundType.FROGSPAWN)),
            new Item.Properties().food(ModFoodProperties.RAW_PORK_BLOCK));

    public static final DeferredBlock<Block> RAW_RABBIT_BLOCK = registerBlock("raw_rabbit_block",
            () -> new RawMeatBlock(COOKED_RABBIT_BLOCK, MeatBlock.BoneType.THIN, BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.COW_BELL).strength(2.0F).sound(SoundType.FROGSPAWN)),
            new Item.Properties().food(ModFoodProperties.RAW_RABBIT_BLOCK));

    public static final DeferredBlock<Block> RAW_CHICKEN_BLOCK = registerBlock("raw_chicken_block",
            () -> new RawMeatBlock(COOKED_CHICKEN_BLOCK, MeatBlock.BoneType.THIN, BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.COW_BELL).strength(2.0F).sound(SoundType.FROGSPAWN)),
            new Item.Properties().food(ModFoodProperties.RAW_CHICKEN_BLOCK));

    public static final DeferredBlock<Block> RAW_SALMON_BLOCK = registerBlock("raw_salmon_block",
            () -> new RawMeatBlock(COOKED_SALMON_BLOCK, MeatBlock.BoneType.NONE, BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.COW_BELL).strength(2.0F).sound(SoundType.FROGSPAWN)),
            new Item.Properties().food(ModFoodProperties.RAW_SALMON_BLOCK));

    public static final DeferredBlock<Block> RAW_COD_BLOCK = registerBlock("raw_cod_block",
            () -> new RawMeatBlock(COOKED_COD_BLOCK, MeatBlock.BoneType.NONE, BlockBehaviour.Properties.of().mapColor(MapColor.RAW_IRON).instrument(NoteBlockInstrument.COW_BELL).strength(2.0F).sound(SoundType.FROGSPAWN)),
            new Item.Properties().food(ModFoodProperties.RAW_COD_BLOCK));

    // TODO: Make Dyeable.
    public static final DeferredBlock<Block> LEATHER_BLOCK = registerBlock("leather_block",
            () -> new ConfiguredDirectionalBlock(BlockBehaviour.Properties.of().mapColor(MapColor.DIRT).instrument(NoteBlockInstrument.COW_BELL).strength(0.8F).sound(SoundType.WOOL)));
//TODO: Make rabbit hide "slab-slab" block (should be able to be stacked four times to create a full block"

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        return registerBlock(name, block, new Item.Properties());
    }

    // Main function that registers the block & item using helper
    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block, Item.Properties properties) {
        // This registers the block itself
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, properties);
        return toReturn;
    }

    // Helper function to create and register a block's associated item if we want to set custom properties
    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block, Item.Properties properties) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), properties));
    }
}
