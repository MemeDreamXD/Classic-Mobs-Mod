package com.memedream.classicmobs.init;

import com.memedream.classicmobs.ClassicMobs;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.*;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

public class ModTags {

    public static class Biomes {
        public static final TagKey<Biome> SPAWNS_PALM_TREES = create("spawns_palm_trees");
        public static final TagKey<Biome> SPAWNS_RARE_PALM_TREES = create("spawns_rare_palm_trees");

        private static TagKey<Biome> create(String tagName) {
            return TagKey.create(Registries.BIOME, ClassicMobs.prefix(tagName));
        }
    }

    public static class Blocks {
        public static final TagKey<Block> MINEABLE_WITH_MATTOCK = create("mineable/mattock");
        public static final TagKey<Block> MINEABLE_WITH_PICKAXE_AXE = create("mineable/pickaxe_axe");

        private static TagKey<Block> create(String tagName) {
            return BlockTags.create(ClassicMobs.prefix(tagName));
        }
    }

    public static class Items {
        public static final TagKey<Item> DODO_FOOD = create("dodo_food");
        public static final TagKey<Item> REPAIRS_CHITIN_ARMOR = create("repairs_chitin_armor");

        private static TagKey<Item> create(String tagName) {
            return ItemTags.create(ClassicMobs.prefix(tagName));
        }
    }

    public static class Entities {

        public static final TagKey<EntityType<?>> BOLA_IMMUNE = create("bola_immune");

        private static TagKey<EntityType<?>> create(String tagName) {
            return TagKey.create(Registries.ENTITY_TYPE, ClassicMobs.prefix(tagName));
        }
    }
}
