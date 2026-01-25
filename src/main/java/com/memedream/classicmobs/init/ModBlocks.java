package com.memedream.classicmobs.init;

import com.memedream.classicmobs.ClassicMobs;
import com.memedream.classicmobs.block.*;
import com.memedream.classicmobs.item.components.ModFoodProperties;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ClassicMobs.MOD_ID);

    public static final DeferredBlock<Block> TRICKLITH_BLOCK = registerWithItem("tricklith_block", Block::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).requiresCorrectToolForDrops().instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.DRIPSTONE_BLOCK).randomTicks().strength(1.5F, 3.0F));
    public static final DeferredBlock<PointedTricklithBlock> POINTED_TRICKLITH = registerWithItem("pointed_tricklith", PointedTricklithBlock::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).requiresCorrectToolForDrops().forceSolidOn().instrument(NoteBlockInstrument.BASEDRUM).noOcclusion().sound(SoundType.POINTED_DRIPSTONE).randomTicks().strength(1.5F, 3.0F).dynamicShape().offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY));

    public static final DeferredBlock<Block> RUBY_ORE = registerWithItem("ruby_ore", properties -> new DropExperienceBlock(UniformInt.of(3, 7), properties), () -> BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().sound(SoundType.NETHER_ORE).strength(3.0F, 3.0F), () -> new Item.Properties().useBlockDescriptionPrefix().rarity(Rarity.UNCOMMON));

    //TODO: Figure out a texture for this block lol
    //public static final DeferredBlock<Block> UNDERSHALE = registerBlock("undershale",() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.DEEPSLATE).requiresCorrectToolForDrops().instrument(NoteBlockInstrument.BASEDRUM).strength(4.5F, 6.0F).sound(SoundType.DEEPSLATE)));
    public static final DeferredBlock<Block> UNDERSHALE_BRICKS = registerWithItem("undershale_bricks", Block::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.DEEPSLATE).requiresCorrectToolForDrops().instrument(NoteBlockInstrument.BASEDRUM).strength(4.5F, 6.0F).sound(SoundType.DEEPSLATE));
    public static final DeferredBlock<Block> CHISELED_UNDERSHALE = registerWithItem("chiseled_undershale", Block::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.DEEPSLATE).requiresCorrectToolForDrops().instrument(NoteBlockInstrument.BASEDRUM).strength(4.5F, 6.0F).sound(SoundType.DEEPSLATE));
    public static final DeferredBlock<Block> POLISHED_UNDERSHALE = registerWithItem("polished_undershale", Block::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.DEEPSLATE).requiresCorrectToolForDrops().instrument(NoteBlockInstrument.BASEDRUM).strength(4.5F, 6.0F).sound(SoundType.DEEPSLATE));
    public static final DeferredBlock<Block> CARVED_UNDERSHALE_TILE = registerWithItem("carved_undershale_tile", TileBlock::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.DEEPSLATE).requiresCorrectToolForDrops().instrument(NoteBlockInstrument.BASEDRUM).strength(4.5F, 6.0F).sound(SoundType.DEEPSLATE));
    public static final DeferredBlock<Block> CARVED_UNDERSHALE_TILE_EDGE = registerWithItem("carved_undershale_tile_edge", TileEdgeBlock::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.DEEPSLATE).requiresCorrectToolForDrops().instrument(NoteBlockInstrument.BASEDRUM).strength(4.5F, 6.0F).sound(SoundType.DEEPSLATE));
    public static final DeferredBlock<Block> NACRITE = registerWithItem("nacrite", Block::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_MAGENTA).requiresCorrectToolForDrops().instrument(NoteBlockInstrument.BASEDRUM).strength(2.625F, 3.375F).sound(SoundType.CALCITE));
    public static final DeferredBlock<Block> CAVERRNACK = registerWithItem("caverrnack", CaverrnackBlock::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().instrument(NoteBlockInstrument.SNARE).strength(2.0F, 4.0F).sound(SoundType.NETHERRACK));

    public static final DeferredBlock<Block> GUNPOWDER_BLOCK = registerWithItem("gunpowder_block", GunpowderBlock::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sound(SoundType.SAND).ignitedByLava());
    public static final DeferredBlock<Block> ROTTEN_FLESH_BLOCK = registerWithItem("rotten_flesh_block", RottenFleshBlock::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.ZOMBIE).strength(2.0F).sound(SoundType.FROGSPAWN), () -> new Item.Properties().useBlockDescriptionPrefix().food(ModFoodProperties.ROTTEN_FLESH_BLOCK, ModFoodProperties.MEAT_EFFECT_CONSUMABLE.apply(new MobEffectInstance(MobEffects.HUNGER, 600, 8), 0.8F)));
    public static final DeferredBlock<Block> CHITIN_BLOCK = registerWithItem("chitin_block", Block::new, () -> BlockBehaviour.Properties.of().requiresCorrectToolForDrops().mapColor(MapColor.COLOR_BROWN).instrument(NoteBlockInstrument.DIDGERIDOO).strength(1.5F).sound(SoundType.PACKED_MUD));
    public static final DeferredBlock<Block> STRING_BLOCK = registerWithItem("string_block", WebBlock::new, () -> BlockBehaviour.Properties.of().noOcclusion().mapColor(MapColor.SNOW).forceSolidOn().noCollision().instrument(NoteBlockInstrument.GUITAR).strength(0.8F).sound(SoundType.COBWEB).ignitedByLava());
    public static final DeferredBlock<Block> MAGMA_CREAM_BLOCK = registerWithItem("magma_cream_block", properties -> new MagmaCreamBlock(1.0F, properties), () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE).noOcclusion().instrument(NoteBlockInstrument.PLING).strength(0.8F).sound(SoundType.SLIME_BLOCK));
    public static final DeferredBlock<Block> PHANTOM_MEMBRANE_BLOCK = registerWithItem("phantom_membrane_block", Block::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).instrument(NoteBlockInstrument.XYLOPHONE).strength(0.8F).sound(SoundType.SOUL_SOIL));
    //TODO: Texture/model needs redone BAD do not let me forget this. Also, should be a tempt item for wolves.
    public static final DeferredBlock<Block> BLOCK_OF_BONES = registerWithItem("block_of_bones", RotatedPillarBlock::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.SAND).instrument(NoteBlockInstrument.XYLOPHONE).requiresCorrectToolForDrops().strength(2.0F).noOcclusion().sound(SoundType.BONE_BLOCK));
    //TODO: Speed up "furnace blocks" that the tip is pointing towards.
    public static final DeferredBlock<BlazeRodBlock> BLAZE_ROD_BLOCK = registerWithItem("blaze_rod_block", BlazeRodBlock::new, () -> BlockBehaviour.Properties.of().forceSolidOff().instabreak().lightLevel(p_187435_ -> 14).sound(SoundType.WOOD).noOcclusion());
    public static final DeferredBlock<EnderPearlBlock> ENDER_PEARL_BLOCK = registerWithItem("ender_pearl_block", EnderPearlBlock::new, () -> BlockBehaviour.Properties.of().instabreak().sound(SoundType.AMETHYST));
    //TODO: Push entites away from the tip like a fan block.
    public static final DeferredBlock<BreezeRodBlock> BREEZE_ROD_BLOCK = registerWithItem("breeze_rod_block", BreezeRodBlock::new, () -> BlockBehaviour.Properties.of().forceSolidOff().noOcclusion().instabreak().sound(SoundType.WOOD).noOcclusion());
    //TODO: Should be able to ferment when right clicked with sugar+brown mushroom in main/offhand
    public static final DeferredBlock<Block> SPIDER_EYE_BLOCK = registerWithItem("spider_eye_block", SpiderEyeBlock::new, () -> BlockBehaviour.Properties.of().noOcclusion().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.COW_BELL).strength(2.0F).sound(SoundType.FROGSPAWN), () -> new Item.Properties().useBlockDescriptionPrefix().food(ModFoodProperties.SPIDER_EYE_BLOCK, ModFoodProperties.MEAT_EFFECT_CONSUMABLE.apply(new MobEffectInstance(MobEffects.POISON, 100, 8), 1.0F)));
    public static final DeferredBlock<Block> FERMENTED_SPIDER_EYE_BLOCK = registerWithItem("fermented_spider_eye_block", SpiderEyeBlock::new, () -> BlockBehaviour.Properties.of().noOcclusion().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.COW_BELL).strength(2.0F).sound(SoundType.FROGSPAWN));

    public static final DeferredBlock<Block> COOKED_BEEF_BLOCK = registerWithItem("cooked_beef_block", properties -> new MeatBlock(MeatBlock.BoneType.NORMAL, properties), () -> BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.COW_BELL).strength(2.0F).sound(SoundType.FROGSPAWN), () -> new Item.Properties().useBlockDescriptionPrefix().food(ModFoodProperties.COOKED_BEEF_BLOCK, ModFoodProperties.DEFAULT_MEAT_CONSUMABLE).usingConvertsTo(Items.BONE));
    public static final DeferredBlock<Block> COOKED_MUTTON_BLOCK = registerWithItem("cooked_mutton_block", properties -> new MeatBlock(MeatBlock.BoneType.NORMAL, properties), () -> BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.COW_BELL).strength(2.0F).sound(SoundType.FROGSPAWN), () -> new Item.Properties().useBlockDescriptionPrefix().food(ModFoodProperties.COOKED_MUTTON_BLOCK, ModFoodProperties.DEFAULT_MEAT_CONSUMABLE).usingConvertsTo(Items.BONE));
    public static final DeferredBlock<Block> COOKED_PORK_BLOCK = registerWithItem("cooked_pork_block", properties -> new MeatBlock(MeatBlock.BoneType.NORMAL, properties), () -> BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.COW_BELL).strength(2.0F).sound(SoundType.FROGSPAWN), () -> new Item.Properties().useBlockDescriptionPrefix().food(ModFoodProperties.COOKED_PORK_BLOCK, ModFoodProperties.DEFAULT_MEAT_CONSUMABLE).usingConvertsTo(Items.BONE));
    public static final DeferredBlock<Block> COOKED_DODO_BLOCK = registerWithItem("cooked_dodo_block", properties -> new MeatBlock(MeatBlock.BoneType.NORMAL, properties), () -> BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.COW_BELL).strength(2.0F).sound(SoundType.FROGSPAWN), () -> new Item.Properties().useBlockDescriptionPrefix().food(ModFoodProperties.COOKED_DODO_BLOCK, ModFoodProperties.MEAT_EFFECT_CONSUMABLE.apply(new MobEffectInstance(MobEffects.SATURATION, 200, 8), 1.0F)).usingConvertsTo(Items.BONE));
    public static final DeferredBlock<Block> COOKED_RABBIT_BLOCK = registerWithItem("cooked_rabbit_block", properties -> new MeatBlock(MeatBlock.BoneType.THIN, properties), () -> BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.COW_BELL).strength(2.0F).sound(SoundType.FROGSPAWN), () -> new Item.Properties().useBlockDescriptionPrefix().food(ModFoodProperties.COOKED_RABBIT_BLOCK, ModFoodProperties.DEFAULT_MEAT_CONSUMABLE).usingConvertsTo(Items.BONE));
    public static final DeferredBlock<Block> COOKED_CHICKEN_BLOCK = registerWithItem("cooked_chicken_block", properties -> new MeatBlock(MeatBlock.BoneType.THIN, properties), () -> BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.COW_BELL).strength(2.0F).sound(SoundType.FROGSPAWN), () -> new Item.Properties().useBlockDescriptionPrefix().food(ModFoodProperties.COOKED_CHICKEN_BLOCK, ModFoodProperties.DEFAULT_MEAT_CONSUMABLE).usingConvertsTo(Items.BONE));
    public static final DeferredBlock<Block> COOKED_SALMON_BLOCK = registerWithItem("cooked_salmon_block", properties -> new MeatBlock(MeatBlock.BoneType.NONE, properties), () -> BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.COW_BELL).strength(2.0F).sound(SoundType.FROGSPAWN), () -> new Item.Properties().useBlockDescriptionPrefix().food(ModFoodProperties.COOKED_SALMON_BLOCK, ModFoodProperties.DEFAULT_MEAT_CONSUMABLE));
    public static final DeferredBlock<Block> COOKED_COD_BLOCK = registerWithItem("cooked_cod_block", properties -> new MeatBlock(MeatBlock.BoneType.NONE, properties), () -> BlockBehaviour.Properties.of().mapColor(MapColor.RAW_IRON).instrument(NoteBlockInstrument.COW_BELL).strength(2.0F).sound(SoundType.FROGSPAWN), () -> new Item.Properties().useBlockDescriptionPrefix().food(ModFoodProperties.COOKED_COD_BLOCK, ModFoodProperties.DEFAULT_MEAT_CONSUMABLE));

    public static final DeferredBlock<Block> TROPICAL_FISH_BLOCK = registerWithItem("tropical_fish_block", properties -> new MeatBlock(MeatBlock.BoneType.NONE, properties), () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE).instrument(NoteBlockInstrument.COW_BELL).strength(2.0F).sound(SoundType.FROGSPAWN), () -> new Item.Properties().useBlockDescriptionPrefix().food(ModFoodProperties.TROPICAL_FISH_BLOCK, ModFoodProperties.DEFAULT_MEAT_CONSUMABLE));
    public static final DeferredBlock<Block> PUFFERFISH_BLOCK = registerWithItem("pufferfish_block", ConfiguredDirectionalBlock::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).instrument(NoteBlockInstrument.COW_BELL).strength(2.0F).sound(SoundType.FROGSPAWN), () -> new Item.Properties().useBlockDescriptionPrefix().food(ModFoodProperties.PUFFERFISH_BLOCK, ModFoodProperties.PUFFERFISH_BLOCK_CONSUMABLE));
    public static final DeferredBlock<Block> RAW_BEEF_BLOCK = registerWithItem("raw_beef_block", properties -> new RawMeatBlock(COOKED_BEEF_BLOCK, MeatBlock.BoneType.NORMAL, properties), () -> BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.COW_BELL).strength(2.0F).sound(SoundType.FROGSPAWN), () -> new Item.Properties().useBlockDescriptionPrefix().food(ModFoodProperties.RAW_BEEF_BLOCK, ModFoodProperties.DEFAULT_MEAT_CONSUMABLE).usingConvertsTo(Items.BONE));
    public static final DeferredBlock<Block> RAW_MUTTON_BLOCK = registerWithItem("raw_mutton_block", properties -> new RawMeatBlock(COOKED_MUTTON_BLOCK, MeatBlock.BoneType.NORMAL, properties), () -> BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.COW_BELL).strength(2.0F).sound(SoundType.FROGSPAWN), () -> new Item.Properties().useBlockDescriptionPrefix().food(ModFoodProperties.RAW_MUTTON_BLOCK, ModFoodProperties.DEFAULT_MEAT_CONSUMABLE).usingConvertsTo(Items.BONE));
    public static final DeferredBlock<Block> RAW_PORK_BLOCK = registerWithItem("raw_pork_block", properties -> new RawMeatBlock(COOKED_PORK_BLOCK, MeatBlock.BoneType.NORMAL, properties), () -> BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.COW_BELL).strength(2.0F).sound(SoundType.FROGSPAWN), () -> new Item.Properties().useBlockDescriptionPrefix().food(ModFoodProperties.RAW_PORK_BLOCK, ModFoodProperties.DEFAULT_MEAT_CONSUMABLE).usingConvertsTo(Items.BONE));
    public static final DeferredBlock<Block> RAW_DODO_BLOCK = registerWithItem("raw_dodo_block", properties -> new RawMeatBlock(COOKED_DODO_BLOCK, MeatBlock.BoneType.NORMAL, properties), () -> BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.COW_BELL).strength(2.0F).sound(SoundType.FROGSPAWN), () -> new Item.Properties().useBlockDescriptionPrefix().food(ModFoodProperties.RAW_DODO_BLOCK, ModFoodProperties.MEAT_EFFECT_CONSUMABLE.apply(new MobEffectInstance(MobEffects.HUNGER, 600, 8), 0.3F)).usingConvertsTo(Items.BONE));
    public static final DeferredBlock<Block> RAW_RABBIT_BLOCK = registerWithItem("raw_rabbit_block", properties -> new RawMeatBlock(COOKED_RABBIT_BLOCK, MeatBlock.BoneType.THIN, properties), () -> BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.COW_BELL).strength(2.0F).sound(SoundType.FROGSPAWN), () -> new Item.Properties().useBlockDescriptionPrefix().food(ModFoodProperties.RAW_RABBIT_BLOCK, ModFoodProperties.DEFAULT_MEAT_CONSUMABLE).usingConvertsTo(Items.BONE));
    public static final DeferredBlock<Block> RAW_CHICKEN_BLOCK = registerWithItem("raw_chicken_block", properties -> new RawMeatBlock(COOKED_CHICKEN_BLOCK, MeatBlock.BoneType.THIN, properties), () -> BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.COW_BELL).strength(2.0F).sound(SoundType.FROGSPAWN), () -> new Item.Properties().useBlockDescriptionPrefix().food(ModFoodProperties.RAW_CHICKEN_BLOCK, ModFoodProperties.MEAT_EFFECT_CONSUMABLE.apply(new MobEffectInstance(MobEffects.HUNGER, 600, 8), 0.3F)).usingConvertsTo(Items.BONE));
    public static final DeferredBlock<Block> RAW_SALMON_BLOCK = registerWithItem("raw_salmon_block", properties -> new RawMeatBlock(COOKED_SALMON_BLOCK, MeatBlock.BoneType.NONE, properties), () -> BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.COW_BELL).strength(2.0F).sound(SoundType.FROGSPAWN), () -> new Item.Properties().useBlockDescriptionPrefix().food(ModFoodProperties.RAW_SALMON_BLOCK, ModFoodProperties.DEFAULT_MEAT_CONSUMABLE));
    public static final DeferredBlock<Block> RAW_COD_BLOCK = registerWithItem("raw_cod_block", properties -> new RawMeatBlock(COOKED_COD_BLOCK, MeatBlock.BoneType.NONE, properties), () -> BlockBehaviour.Properties.of().mapColor(MapColor.RAW_IRON).instrument(NoteBlockInstrument.COW_BELL).strength(2.0F).sound(SoundType.FROGSPAWN), () -> new Item.Properties().useBlockDescriptionPrefix().food(ModFoodProperties.RAW_COD_BLOCK, ModFoodProperties.DEFAULT_MEAT_CONSUMABLE));

    // TODO: Make Dyeable.
    public static final DeferredBlock<Block> LEATHER_BLOCK = registerWithItem("leather_block", ConfiguredDirectionalBlock::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.DIRT).instrument(NoteBlockInstrument.COW_BELL).strength(0.8F).sound(SoundType.WOOL));
    //TODO: Make rabbit hide "slab-slab" block (should be able to be stacked four times to create a full block"

    public static <T extends Block> DeferredBlock<T> register(String name, Function<BlockBehaviour.Properties, T> block, Supplier<BlockBehaviour.Properties> properties) {
        return BLOCKS.register(name, () -> block.apply(properties.get().setId(ResourceKey.create(Registries.BLOCK, ClassicMobs.prefix(name)))));
    }

    public static <T extends Block> DeferredBlock<T> registerWithItem(String name, Function<BlockBehaviour.Properties, T> block, Supplier<BlockBehaviour.Properties> properties) {
        return registerWithItem(name, block, properties, () -> new Item.Properties().useBlockDescriptionPrefix());
    }

    public static <T extends Block> DeferredBlock<T> registerWithItem(String name, Function<BlockBehaviour.Properties, T> block, Supplier<BlockBehaviour.Properties> properties, Supplier<Item.Properties> itemProperties) {
        DeferredBlock<T> ret = BLOCKS.register(name, () -> block.apply(properties.get().setId(ResourceKey.create(Registries.BLOCK, ClassicMobs.prefix(name)))));
        ModItems.register(name, itemProps -> new BlockItem(ret.get(), itemProps), itemProperties);
        return ret;
    }
}
