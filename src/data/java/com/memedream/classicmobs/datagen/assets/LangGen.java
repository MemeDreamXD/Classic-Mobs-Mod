package com.memedream.classicmobs.datagen.assets;

import com.google.common.collect.ImmutableList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.memedream.classicmobs.ClassicMobs;
import com.memedream.classicmobs.init.ModBlocks;
import com.memedream.classicmobs.init.ModEffects;
import com.memedream.classicmobs.init.ModEntities;
import com.memedream.classicmobs.init.ModItems;
import com.memedream.classicmobs.item.ChitinTemplateItem;
import com.memedream.classicmobs.item.CombinationTemplateItem;
import com.memedream.classicmobs.util.LangConversionHelper;
import com.memedream.classicmobs.util.LangFormatSplitter;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class LangGen extends LanguageProvider {

    public static final Map<String, String> SUBTITLE_GENERATOR = new HashMap<>();
    private final PackOutput output;
    public final Map<String, String> upsideDownEntries = new HashMap<>();

    public LangGen(PackOutput output) {
        super(output, ClassicMobs.MOD_ID, "en_us");
        this.output = output;
    }

    @Override
    protected void addTranslations() {
        this.addBlock(ModBlocks.RUBY_ORE, "Ruby Ore");
        this.addBlock(ModBlocks.PALM_LOG, "Palm Log");
        this.addBlock(ModBlocks.STRIPPED_PALM_LOG, "Stripped Palm Log");
        this.addBlock(ModBlocks.PALM_PLANKS, "Palm Planks");
        this.addBlock(ModBlocks.PALM_LEAVES, "Palm Leaves");
        this.addBlock(ModBlocks.PALM_SAPLING, "Palm Sapling");
        this.addBlock(ModBlocks.POTTED_PALM_SAPLING, "Palm Sapling");
        this.addBlock(ModBlocks.KETTLE, "Tea Kettle");

        this.addBlock(ModBlocks.GUNPOWDER_BLOCK, "Block of Gunpowder");
        this.addBlock(ModBlocks.STRING_BLOCK, "Block of String");
        this.addBlock(ModBlocks.ENDER_PEARL_BLOCK, "Ender Pearl Block");
        this.addBlock(ModBlocks.SPIDER_EYE_BLOCK, "Spider Eye Block");
        this.addBlock(ModBlocks.FERMENTED_SPIDER_EYE_BLOCK, "Fermented Spider Eye Block");
        this.addBlock(ModBlocks.BLOCK_OF_BONES, "Block of Bones");
        this.addBlock(ModBlocks.CHITIN_BLOCK, "Block of Chitin");
        this.addBlock(ModBlocks.MAGMA_CREAM_BLOCK, "Block of Magma Cream");
        this.addBlock(ModBlocks.ROTTEN_FLESH_BLOCK, "Block of Rotten Flesh");
        this.addBlock(ModBlocks.PHANTOM_MEMBRANE_BLOCK, "Block of Phantom Membrane");
        this.addBlock(ModBlocks.LEATHER_BLOCK, "Block of Leather");
        this.addBlock(ModBlocks.BLAZE_ROD_BLOCK, "Blaze Rod Block");
        this.addBlock(ModBlocks.BREEZE_ROD_BLOCK, "Breeze Rod Block");

        this.addBlock(ModBlocks.RAW_BEEF_BLOCK, "Raw Beef Block");
        this.addBlock(ModBlocks.RAW_MUTTON_BLOCK, "Raw Mutton Block");
        this.addBlock(ModBlocks.RAW_PORK_BLOCK, "Raw Pork Block");
        this.addBlock(ModBlocks.RAW_RABBIT_BLOCK, "Raw Rabbit Block");
        this.addBlock(ModBlocks.RAW_CHICKEN_BLOCK, "Raw Chicken Block");
        this.addBlock(ModBlocks.RAW_COD_BLOCK, "Raw Cod Block");
        this.addBlock(ModBlocks.RAW_DODO_BLOCK, "Raw Dodo Block");
        this.addBlock(ModBlocks.RAW_SALMON_BLOCK, "Raw Salmon Block");
        this.addBlock(ModBlocks.COOKED_BEEF_BLOCK, "Steak Block");
        this.addBlock(ModBlocks.COOKED_MUTTON_BLOCK, "Cooked Mutton Block");
        this.addBlock(ModBlocks.COOKED_PORK_BLOCK, "Cooked Pork Block");
        this.addBlock(ModBlocks.COOKED_RABBIT_BLOCK, "Cooked Rabbit Block");
        this.addBlock(ModBlocks.COOKED_CHICKEN_BLOCK, "Cooked Chicken Block");
        this.addBlock(ModBlocks.COOKED_COD_BLOCK, "Cooked Cod Block");
        this.addBlock(ModBlocks.COOKED_DODO_BLOCK, "Cooked Dodo Block");
        this.addBlock(ModBlocks.COOKED_SALMON_BLOCK, "Cooked Salmon Block");
        this.addBlock(ModBlocks.TROPICAL_FISH_BLOCK, "Tropical Fish Block");
        this.addBlock(ModBlocks.PUFFERFISH_BLOCK, "Pufferfish Block");

        this.addItem(ModItems.RAW_DODO, "Raw Dodo");
        this.addItem(ModItems.COOKED_DODO, "Cooked Dodo");
        this.addItem(ModItems.RUBY, "Ruby");
        this.addItem(ModItems.LOCK_OF_HAG, "Lock of Hag");
        this.addItem(ModItems.HARPY_FEATHER, "Harpy Feather");
        this.addItem(ModItems.GAZING_PEARL, "Gazing Pearl");
        this.addItem(ModItems.POP_POWDER, "Pop Powder");
        this.addItem(ModItems.BLAST_POWDER, "Blast Powder");
        this.addItem(ModItems.CHEM_POWDER, "Chem Powder");
        this.addItem(ModItems.FLIGHT_ARROW, "Flight Arrow");
        this.addItem(ModItems.BOLA, "Bola");
        this.addItem(ModItems.CHITIN, "Chitin");
        this.addItem(ModItems.CHITIN_HELMET, "Chitin Helmet");
        this.addItem(ModItems.CHITIN_CHESTPLATE, "Chitin Chestplate");
        this.addItem(ModItems.CHITIN_LEGGINGS, "Chitin Leggings");
        this.addItem(ModItems.CHITIN_BOOTS, "Chitin Boots");

        this.addItem(ModItems.CHITIN_UPGRADE_SMITHING_TEMPLATE, "Chitin Upgrade");
        this.add(ChitinTemplateItem.CHITIN_UPGRADE_APPLIES_TO.getString(), "Leather Armor");
        this.add(ChitinTemplateItem.CHITIN_UPGRADE_INGREDIENTS.getString(), "Chitin");
        this.add(ChitinTemplateItem.CHITIN_UPGRADE_ADDITIONS_SLOT_DESCRIPTION.getString(), "Add Chitin");
        this.add(ChitinTemplateItem.CHITIN_UPGRADE_BASE_SLOT_DESCRIPTION.getString(), "Add leather armor");

        this.addItem(ModItems.COMBINATION_UPGRADE_SMITHING_TEMPLATE, "Combination Upgrade");
        this.add(CombinationTemplateItem.COMBINATION_UPGRADE_APPLIES_TO.getString(), "Basic tools");
        this.add(CombinationTemplateItem.COMBINATION_UPGRADE_INGREDIENTS.getString(), "Compatible tool");
        this.add(CombinationTemplateItem.COMBINATION_UPGRADE_ADDITIONS_SLOT_DESCRIPTION.getString(), "Add compatible tool");
        this.add(CombinationTemplateItem.COMBINATION_UPGRADE_BASE_SLOT_DESCRIPTION.getString(), "Add basic tool");

        this.addItem(ModItems.PRIMROSE, "Primrose");
        this.addItem(ModItems.BIRCH_NUT, "Birch Nut");
        this.addItem(ModItems.COOKED_BIRCH_NUT, "Cooked Birch Nut");
        this.addItem(ModItems.PINECONE, "Pinecone");
        this.addItem(ModItems.BANANA, "Banana");
        this.addItem(ModItems.DATE, "Date");
        this.addItem(ModItems.PERSIMMON, "Persimmon");
        this.addItem(ModItems.CHERRIES, "Cherries");
        this.addItem(ModItems.PALE_APPLE, "Pale Apple");

        this.addItem(ModItems.WOODEN_PICKAXE_AXE, "Wooden Pickaxe-Axe");
        this.addItem(ModItems.WOODEN_MATTOCK, "Wooden Mattock");
        this.addItem(ModItems.WOODEN_SPADE, "Wooden Spade");
        this.addItem(ModItems.WOODEN_LUMBER_AXE, "Wooden Lumber");
        this.addItem(ModItems.WOODEN_SCYTHE, "Wooden Scythe");
        this.addItem(ModItems.WOODEN_HAMMER, "Wooden Hammer");
        this.addItem(ModItems.WOODEN_KNIFE, "Wooden Knife");

        this.addItem(ModItems.STONE_PICKAXE_AXE, "Stone Pickaxe-Axe");
        this.addItem(ModItems.STONE_MATTOCK, "Stone Mattock");
        this.addItem(ModItems.STONE_SPADE, "Stone Spade");
        this.addItem(ModItems.STONE_LUMBER_AXE, "Stone Lumber");
        this.addItem(ModItems.STONE_SCYTHE, "Stone Scythe");
        this.addItem(ModItems.STONE_HAMMER, "Stone Hammer");
        this.addItem(ModItems.STONE_KNIFE, "Stone Knife");

        this.addItem(ModItems.COPPER_PICKAXE_AXE, "Copper Pickaxe-Axe");
        this.addItem(ModItems.COPPER_MATTOCK, "Copper Mattock");
        this.addItem(ModItems.COPPER_SPADE, "Copper Spade");
        this.addItem(ModItems.COPPER_LUMBER_AXE, "Copper Lumber");
        this.addItem(ModItems.COPPER_SCYTHE, "Copper Scythe");
        this.addItem(ModItems.COPPER_HAMMER, "Copper Hammer");
        this.addItem(ModItems.COPPER_KNIFE, "Copper Knife");

        this.addItem(ModItems.GOLDEN_PICKAXE_AXE, "Golden Pickaxe-Axe");
        this.addItem(ModItems.GOLDEN_MATTOCK, "Golden Mattock");
        this.addItem(ModItems.GOLDEN_SPADE, "Golden Spade");
        this.addItem(ModItems.GOLDEN_LUMBER_AXE, "Golden Lumber");
        this.addItem(ModItems.GOLDEN_SCYTHE, "Golden Scythe");
        this.addItem(ModItems.GOLDEN_HAMMER, "Golden Hammer");
        this.addItem(ModItems.GOLDEN_KNIFE, "Golden Knife");

        this.addItem(ModItems.IRON_PICKAXE_AXE, "Iron Pickaxe-Axe");
        this.addItem(ModItems.IRON_MATTOCK, "Iron Mattock");
        this.addItem(ModItems.IRON_SPADE, "Iron Spade");
        this.addItem(ModItems.IRON_LUMBER_AXE, "Iron Lumber");
        this.addItem(ModItems.IRON_SCYTHE, "Iron Scythe");
        this.addItem(ModItems.IRON_HAMMER, "Iron Hammer");
        this.addItem(ModItems.IRON_KNIFE, "Iron Knife");

        this.addItem(ModItems.DIAMOND_PICKAXE_AXE, "Diamond Pickaxe-Axe");
        this.addItem(ModItems.DIAMOND_MATTOCK, "Diamond Mattock");
        this.addItem(ModItems.DIAMOND_SPADE, "Diamond Spade");
        this.addItem(ModItems.DIAMOND_LUMBER_AXE, "Diamond Lumber");
        this.addItem(ModItems.DIAMOND_SCYTHE, "Diamond Scythe");
        this.addItem(ModItems.DIAMOND_HAMMER, "Diamond Hammer");
        this.addItem(ModItems.DIAMOND_KNIFE, "Diamond Knife");

        this.addItem(ModItems.NETHERITE_PICKAXE_AXE, "Netherite Pickaxe-Axe");
        this.addItem(ModItems.NETHERITE_MATTOCK, "Netherite Mattock");
        this.addItem(ModItems.NETHERITE_SPADE, "Netherite Spade");
        this.addItem(ModItems.NETHERITE_LUMBER_AXE, "Netherite Lumber");
        this.addItem(ModItems.NETHERITE_SCYTHE, "Netherite Scythe");
        this.addItem(ModItems.NETHERITE_HAMMER, "Netherite Hammer");
        this.addItem(ModItems.NETHERITE_KNIFE, "Netherite Knife");

        this.addEntityAndEgg(ModEntities.ANTLION, "Antlion");
        this.addEntityAndEgg(ModEntities.DODO, "Dodo");
        this.addEntityAndEgg(ModEntities.HAG, "Hag");
        this.addEntityAndEgg(ModEntities.HARPY, "Harpy");
        this.addEntityAndEgg(ModEntities.MYRMEX, "Myrmex");
        this.addEntityAndEgg(ModEntities.FESTIVE_CREEPER, "Festive Creeper");
        this.addEntityAndEgg(ModEntities.SUPPORT_CREEPER, "Support Creeper");
        this.addEntityAndEgg(ModEntities.ROCKET_CREEPER, "Rocket Creeper");
        this.addEntityType(ModEntities.FESTIVE_TNT, "Festive TNT");
        this.addEntityType(ModEntities.FALLING_GUNPOWDER, "Falling Gunpowder");
        this.addEntityType(ModEntities.FLIGHT_ARROW, "Flight Arrow");
        this.addEntityType(ModEntities.MIMIC, "Mimic");

        this.addEffect(ModEffects.FAE_CURSE, "Fae Curse");
        this.addEffect(ModEffects.STENCH, "Stench");
        this.addEffect(ModEffects.EMPOWER, "Empower");
        this.addEffect(ModEffects.FORTIFY, "Fortify");
        this.addEffect(ModEffects.VELOCITY, "Velocity");
        this.addEffect(ModEffects.BOUND, "bound");

        this.addDeathMessage("adventurous_eater", "%1$s was an adventurous eater");

        this.add("creativetab.classic_mobs.classic_mobs_tab", "Classic Mobs");

        this.add("container.classicmobs.kettle", "Tea Kettle");

        SUBTITLE_GENERATOR.forEach(this::add);
    }

    public void addEntityAndEgg(DeferredHolder<EntityType<?>, ? extends EntityType<?>> entity, String name) {
        this.addEntityType(entity, name);
        this.add("item.classic_mobs." + entity.getId().getPath() + "_spawn_egg", name + " Spawn Egg");
    }

    public void addDeathMessage(String key, String name) {
        this.add("death.attack.classic_mobs." + key, name);
    }

    public void addTrim(String key, String name) {
        this.add("trim_material.classic_mobs." + key, name + " Material");
    }

    public void translateTag(TagKey<?> tag, String name) {
        this.add(String.format("tag.%s.%s.%s", tag.registry().identifier().getPath(), tag.location().getNamespace(), tag.location().getPath().replace('/', '.')), name);
    }

    @Override
    public void add(String key, String value) {
        super.add(key, value);
        List<LangFormatSplitter.Component> splitEnglish = LangFormatSplitter.split(value);
        this.upsideDownEntries.put(key, LangConversionHelper.convertComponents(splitEnglish));
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        //generate normal lang file
        CompletableFuture<?> languageGen = super.run(cache);
        ImmutableList.Builder<CompletableFuture<?>> futuresBuilder = new ImmutableList.Builder<>();
        futuresBuilder.add(languageGen);

        //generate en_ud file
        JsonObject upsideDownFile = new JsonObject();
        this.upsideDownEntries.forEach(upsideDownFile::addProperty);
        futuresBuilder.add(DataProvider.saveStable(cache, upsideDownFile, this.output.getOutputFolder(PackOutput.Target.RESOURCE_PACK).resolve(ClassicMobs.MOD_ID).resolve("lang").resolve("en_ud.json")));

        return CompletableFuture.allOf(futuresBuilder.build().toArray(CompletableFuture[]::new));
    }
}
