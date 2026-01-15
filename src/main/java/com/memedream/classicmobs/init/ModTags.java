package com.memedream.classicmobs.init;

import com.memedream.classicmobs.ClassicMobs;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {

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
}
