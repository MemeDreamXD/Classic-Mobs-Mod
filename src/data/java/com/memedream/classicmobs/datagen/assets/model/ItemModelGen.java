package com.memedream.classicmobs.datagen.assets.model;

import com.memedream.classicmobs.client.item.BolaSwing;
import com.memedream.classicmobs.client.item.KnifeStab;
import com.memedream.classicmobs.init.ModEntities;
import com.memedream.classicmobs.init.ModItems;
import com.memedream.classicmobs.item.KnifeItem;
import net.minecraft.client.color.item.Dye;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.renderer.item.properties.conditional.IsUsingItem;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.DyedItemColor;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.function.BiConsumer;

public class ItemModelGen extends ItemModelGenerators {
    public ItemModelGen(ItemModelOutput itemModelOutput, BiConsumer<Identifier, ModelInstance> modelOutput) {
        super(itemModelOutput, modelOutput);
    }

    @Override
    public void run() {
        for (DeferredHolder<Item, ? extends Item> egg : ModEntities.SPAWN_EGGS.getEntries()) {
            this.generateFlatItem(egg.get(), ModelTemplates.FLAT_ITEM);
        }
        this.generateFlatItem(ModItems.CHERRIES.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.DATE.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.PERSIMMON.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.COOKED_BIRCH_NUT.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.RAW_DODO.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.COOKED_DODO.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.FLIGHT_ARROW.get(), ModelTemplates.FLAT_ITEM);

        ModelTemplate bolaSwing = ModelTemplates.createItem("classic_mobs:bola_swinging", TextureSlot.LAYER0);
        this.itemModelOutput.accept(ModItems.BOLA.get(), ItemModelUtils.conditional(new IsUsingItem(), ItemModelUtils.rangeSelect(new BolaSwing(),
            ItemModelUtils.plainModel(this.createFlatItemModel(ModItems.BOLA.get(), "_1", bolaSwing)),
            ItemModelUtils.override(ItemModelUtils.plainModel(this.createFlatItemModel(ModItems.BOLA.get(), "_2", bolaSwing)), 0.25F),
            ItemModelUtils.override(ItemModelUtils.plainModel(this.createFlatItemModel(ModItems.BOLA.get(), "_3", bolaSwing)), 0.5F),
            ItemModelUtils.override(ItemModelUtils.plainModel(this.createFlatItemModel(ModItems.BOLA.get(), "_4", bolaSwing)), 0.75F)),
            ItemModelUtils.plainModel(this.createFlatItemModel(ModItems.BOLA.get(), ModelTemplates.FLAT_ITEM))));
        this.generateFlatItem(ModItems.BIRCH_NUT.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.PINECONE.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.LOCK_OF_HAG.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.HARPY_FEATHER.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.GAZING_PEARL.get(), ModelTemplates.FLAT_ITEM);
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
        this.createKnife(ModItems.WOODEN_KNIFE);
        this.generateFlatItem(ModItems.STONE_HAMMER.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateFlatItem(ModItems.STONE_LUMBER_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateFlatItem(ModItems.STONE_MATTOCK.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateFlatItem(ModItems.STONE_SCYTHE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateFlatItem(ModItems.STONE_SPADE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateFlatItem(ModItems.STONE_PICKAXE_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.createKnife(ModItems.STONE_KNIFE);
        this.generateFlatItem(ModItems.COPPER_HAMMER.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateFlatItem(ModItems.COPPER_LUMBER_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateFlatItem(ModItems.COPPER_MATTOCK.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateFlatItem(ModItems.COPPER_SCYTHE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateFlatItem(ModItems.COPPER_SPADE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateFlatItem(ModItems.COPPER_PICKAXE_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.createKnife(ModItems.COPPER_KNIFE);
        this.generateFlatItem(ModItems.GOLDEN_HAMMER.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateFlatItem(ModItems.GOLDEN_LUMBER_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateFlatItem(ModItems.GOLDEN_MATTOCK.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateFlatItem(ModItems.GOLDEN_SCYTHE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateFlatItem(ModItems.GOLDEN_SPADE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateFlatItem(ModItems.GOLDEN_PICKAXE_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.createKnife(ModItems.GOLDEN_KNIFE);
        this.generateFlatItem(ModItems.IRON_HAMMER.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateFlatItem(ModItems.IRON_LUMBER_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateFlatItem(ModItems.IRON_MATTOCK.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateFlatItem(ModItems.IRON_SCYTHE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateFlatItem(ModItems.IRON_SPADE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateFlatItem(ModItems.IRON_PICKAXE_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.createKnife(ModItems.IRON_KNIFE);
        this.generateFlatItem(ModItems.DIAMOND_HAMMER.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateFlatItem(ModItems.DIAMOND_LUMBER_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateFlatItem(ModItems.DIAMOND_MATTOCK.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateFlatItem(ModItems.DIAMOND_SCYTHE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateFlatItem(ModItems.DIAMOND_SPADE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateFlatItem(ModItems.DIAMOND_PICKAXE_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.createKnife(ModItems.DIAMOND_KNIFE);
        this.generateFlatItem(ModItems.NETHERITE_HAMMER.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateFlatItem(ModItems.NETHERITE_LUMBER_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateFlatItem(ModItems.NETHERITE_MATTOCK.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateFlatItem(ModItems.NETHERITE_SCYTHE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateFlatItem(ModItems.NETHERITE_SPADE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateFlatItem(ModItems.NETHERITE_PICKAXE_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.createKnife(ModItems.NETHERITE_KNIFE);
    }

    private void createKnife(DeferredItem<KnifeItem> knife) {
        ModelTemplate stabModel = ModelTemplates.createItem("classic_mobs:knife_stab", TextureSlot.LAYER0);
        this.itemModelOutput.accept(knife.get(), ItemModelUtils.conditional(new KnifeStab(), ItemModelUtils.plainModel(stabModel.create(ModelLocationUtils.getModelLocation(knife.get(), "_stab"), TextureMapping.layer0(knife.get()), this.modelOutput)), ItemModelUtils.plainModel(this.createFlatItemModel(knife.get(), ModelTemplates.FLAT_HANDHELD_ITEM))));
    }
}
