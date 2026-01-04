package com.memedream.classicmobs.init;

import com.memedream.classicmobs.ClassicMobs;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Items {
        public static final TagKey<Item> DODO_FOOD = createTag("dodo_food");
        public static final TagKey<Item> DAILY_DODO_FOOD = createTag("daily_dodo_food");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(ClassicMobs.MOD_ID, name));
        }
    }
    public static class Blocks {
        public static final TagKey<Block> MINEABLE_WITH_PICKAXE_AXE = createTag("mineable_with_pickaxe_axe");
        public static final TagKey<Block> MINEABLE_WITH_MATTOCK = createTag("mineable_with_mattock");

        private static TagKey<net.minecraft.world.level.block.Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(ClassicMobs.MOD_ID, name));
        }
    }
}