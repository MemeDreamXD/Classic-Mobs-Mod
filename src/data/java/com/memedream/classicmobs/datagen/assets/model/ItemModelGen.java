package com.memedream.classicmobs.datagen.assets.model;

import com.memedream.classicmobs.init.ModEntities;
import com.memedream.classicmobs.init.ModItems;
import net.minecraft.client.color.item.Dye;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelInstance;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.DyedItemColor;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class ItemModelGen extends ItemModelGenerators {
    public ItemModelGen(ItemModelOutput itemModelOutput, BiConsumer<Identifier, ModelInstance> modelOutput) {
        super(itemModelOutput, modelOutput);
    }

    @Override
    public void run() {
        for (DeferredHolder<Item, ? extends Item> egg : ModEntities.SPAWN_EGGS.getEntries()) {
            this.generateFlatItem(egg.get(), ModelTemplates.FLAT_ITEM);
        }
        this.generateFlatItem(ModItems.RAW_DODO.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.COOKED_DODO.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.FLIGHT_ARROW.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.BOLA.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.LOCK_OF_HAG.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.HARPY_FEATHER.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.POP_POWDER.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.BLAST_POWDER.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.CHEM_POWDER.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.RUBY.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.CHITIN.get(), ModelTemplates.FLAT_ITEM);
        this.itemModelOutput.accept(ModItems.CHITIN_HELMET.get(), ItemModelUtils.tintedModel(this.generateLayeredItem(ModItems.CHITIN_HELMET.get(), TextureMapping.getItemTexture(Items.LEATHER_HELMET), TextureMapping.getItemTexture(ModItems.CHITIN_HELMET.get(), "_overlay")), new Dye(DyedItemColor.LEATHER_COLOR)));
        this.itemModelOutput.accept(ModItems.CHITIN_CHESTPLATE.get(), ItemModelUtils.tintedModel(this.generateLayeredItem(ModItems.CHITIN_CHESTPLATE.get(), TextureMapping.getItemTexture(Items.LEATHER_CHESTPLATE), TextureMapping.getItemTexture(ModItems.CHITIN_CHESTPLATE.get(), "_overlay")), new Dye(DyedItemColor.LEATHER_COLOR)));
        this.itemModelOutput.accept(ModItems.CHITIN_LEGGINGS.get(), ItemModelUtils.tintedModel(this.generateLayeredItem(ModItems.CHITIN_LEGGINGS.get(), TextureMapping.getItemTexture(Items.LEATHER_LEGGINGS), TextureMapping.getItemTexture(ModItems.CHITIN_LEGGINGS.get(), "_overlay")), new Dye(DyedItemColor.LEATHER_COLOR)));
        this.itemModelOutput.accept(ModItems.CHITIN_BOOTS.get(), ItemModelUtils.tintedModel(this.generateLayeredItem(ModItems.CHITIN_BOOTS.get(), TextureMapping.getItemTexture(Items.LEATHER_BOOTS), TextureMapping.getItemTexture(ModItems.CHITIN_BOOTS.get(), "_overlay")), new Dye(DyedItemColor.LEATHER_COLOR)));
        this.generateFlatItem(ModItems.CHITIN_UPGRADE_SMITHING_TEMPLATE.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.COMBINATION_UPGRADE_SMITHING_TEMPLATE.get(), ModelTemplates.FLAT_ITEM);

        this.generateFlatItem(ModItems.WOODEN_HAMMER.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateFlatItem(ModItems.WOODEN_LUMBER_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateFlatItem(ModItems.WOODEN_MATTOCK.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateFlatItem(ModItems.WOODEN_SCYTHE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateFlatItem(ModItems.WOODEN_SPADE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateFlatItem(ModItems.WOODEN_PICKAXE_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateFlatItem(ModItems.STONE_HAMMER.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateFlatItem(ModItems.STONE_LUMBER_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateFlatItem(ModItems.STONE_MATTOCK.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateFlatItem(ModItems.STONE_SCYTHE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateFlatItem(ModItems.STONE_SPADE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateFlatItem(ModItems.STONE_PICKAXE_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateFlatItem(ModItems.COPPER_HAMMER.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateFlatItem(ModItems.COPPER_LUMBER_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateFlatItem(ModItems.COPPER_MATTOCK.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateFlatItem(ModItems.COPPER_SCYTHE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateFlatItem(ModItems.COPPER_SPADE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateFlatItem(ModItems.COPPER_PICKAXE_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateFlatItem(ModItems.GOLDEN_HAMMER.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateFlatItem(ModItems.GOLDEN_LUMBER_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateFlatItem(ModItems.GOLDEN_MATTOCK.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateFlatItem(ModItems.GOLDEN_SCYTHE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateFlatItem(ModItems.GOLDEN_SPADE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateFlatItem(ModItems.GOLDEN_PICKAXE_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateFlatItem(ModItems.IRON_HAMMER.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateFlatItem(ModItems.IRON_LUMBER_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateFlatItem(ModItems.IRON_MATTOCK.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateFlatItem(ModItems.IRON_SCYTHE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateFlatItem(ModItems.IRON_SPADE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateFlatItem(ModItems.IRON_PICKAXE_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateFlatItem(ModItems.DIAMOND_HAMMER.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateFlatItem(ModItems.DIAMOND_LUMBER_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateFlatItem(ModItems.DIAMOND_MATTOCK.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateFlatItem(ModItems.DIAMOND_SCYTHE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateFlatItem(ModItems.DIAMOND_SPADE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateFlatItem(ModItems.DIAMOND_PICKAXE_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateFlatItem(ModItems.NETHERITE_HAMMER.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateFlatItem(ModItems.NETHERITE_LUMBER_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateFlatItem(ModItems.NETHERITE_MATTOCK.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateFlatItem(ModItems.NETHERITE_SCYTHE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateFlatItem(ModItems.NETHERITE_SPADE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateFlatItem(ModItems.NETHERITE_PICKAXE_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
    }
}
