package com.memedream.classicmobs.init;

import com.memedream.classicmobs.ClassicMobs;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> TABS =
        DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ClassicMobs.MOD_ID);

    public static final Supplier<CreativeModeTab> CLASSIC_MOBS_TAB = TABS.register("classic_mobs_tab", () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.RAW_DODO.get()))
        .title(Component.translatable("creativetab.classic_mobs.classic_mobs_tab"))
        .displayItems((itemDisplayParameters, output) -> {
            ModEntities.SPAWN_EGGS.getEntries().forEach(holder -> output.accept(holder.get()));

            output.accept(ModItems.RAW_DODO);
            output.accept(ModItems.COOKED_DODO);
            output.accept(ModItems.CHITIN);
            output.accept(ModItems.HARPY_FEATHER);
            output.accept(ModItems.LOCK_OF_HAG);
            output.accept(ModItems.POP_POWDER);
            output.accept(ModItems.BLAST_POWDER);
            output.accept(ModItems.CHEM_POWDER);
            output.accept(ModItems.GAZING_PEARL);

            output.accept(ModItems.CHITIN_HELMET);
            output.accept(ModItems.CHITIN_CHESTPLATE);
            output.accept(ModItems.CHITIN_LEGGINGS);
            output.accept(ModItems.CHITIN_BOOTS);
            output.accept(ModItems.CHITIN_UPGRADE_SMITHING_TEMPLATE);
            output.accept(ModItems.FLIGHT_ARROW);
            output.accept(ModItems.BOLA);
            output.accept(ModItems.COMBINATION_UPGRADE_SMITHING_TEMPLATE);
            output.accept(ModItems.WOODEN_PICKAXE_AXE);
            output.accept(ModItems.WOODEN_MATTOCK);
            output.accept(ModItems.WOODEN_SPADE);
            output.accept(ModItems.WOODEN_LUMBER_AXE);
            output.accept(ModItems.WOODEN_HAMMER);
            output.accept(ModItems.WOODEN_SCYTHE);
            //output.accept(ModItems.WOODEN_KNIFE);
            output.accept(ModItems.STONE_PICKAXE_AXE);
            output.accept(ModItems.STONE_MATTOCK);
            output.accept(ModItems.STONE_SPADE);
            output.accept(ModItems.STONE_LUMBER_AXE);
            output.accept(ModItems.STONE_HAMMER);
            output.accept(ModItems.STONE_SCYTHE);
            output.accept(ModItems.COPPER_PICKAXE_AXE);
            output.accept(ModItems.COPPER_MATTOCK);
            output.accept(ModItems.COPPER_SPADE);
            output.accept(ModItems.COPPER_LUMBER_AXE);
            output.accept(ModItems.COPPER_HAMMER);
            output.accept(ModItems.COPPER_SCYTHE);
            output.accept(ModItems.IRON_PICKAXE_AXE);
            output.accept(ModItems.IRON_MATTOCK);
            output.accept(ModItems.IRON_SPADE);
            output.accept(ModItems.IRON_LUMBER_AXE);
            output.accept(ModItems.IRON_HAMMER);
            output.accept(ModItems.IRON_SCYTHE);
            output.accept(ModItems.GOLDEN_PICKAXE_AXE);
            output.accept(ModItems.GOLDEN_MATTOCK);
            output.accept(ModItems.GOLDEN_SPADE);
            output.accept(ModItems.GOLDEN_LUMBER_AXE);
            output.accept(ModItems.GOLDEN_HAMMER);
            output.accept(ModItems.GOLDEN_SCYTHE);
            output.accept(ModItems.DIAMOND_PICKAXE_AXE);
            output.accept(ModItems.DIAMOND_MATTOCK);
            output.accept(ModItems.DIAMOND_SPADE);
            output.accept(ModItems.DIAMOND_LUMBER_AXE);
            output.accept(ModItems.DIAMOND_HAMMER);
            output.accept(ModItems.DIAMOND_SCYTHE);
            output.accept(ModItems.NETHERITE_PICKAXE_AXE);
            output.accept(ModItems.NETHERITE_MATTOCK);
            output.accept(ModItems.NETHERITE_SPADE);
            output.accept(ModItems.NETHERITE_LUMBER_AXE);
            output.accept(ModItems.NETHERITE_HAMMER);
            output.accept(ModItems.NETHERITE_SCYTHE);

            output.accept(ModItems.RUBY);
            output.accept(ModBlocks.RUBY_ORE);
            output.accept(ModBlocks.PALM_LEAVES);
            output.accept(ModBlocks.PALM_LOG);
            output.accept(ModBlocks.PALM_WOOD);
            output.accept(ModBlocks.STRIPPED_PALM_LOG);
            output.accept(ModBlocks.STRIPPED_PALM_WOOD);
            output.accept(ModBlocks.PALM_SAPLING);
            output.accept(ModBlocks.PALM_PLANKS);

            output.accept(ModBlocks.LEATHER_BLOCK);
            output.accept(ModBlocks.SPIDER_EYE_BLOCK);
            output.accept(ModBlocks.FERMENTED_SPIDER_EYE_BLOCK);
            output.accept(ModBlocks.STRING_BLOCK);
            output.accept(ModBlocks.CHITIN_BLOCK);
            output.accept(ModBlocks.BLOCK_OF_BONES);
            output.accept(ModBlocks.MAGMA_CREAM_BLOCK);
            output.accept(ModBlocks.PHANTOM_MEMBRANE_BLOCK);
            output.accept(ModBlocks.BLAZE_ROD_BLOCK);
            output.accept(ModBlocks.BREEZE_ROD_BLOCK);
            output.accept(ModBlocks.GUNPOWDER_BLOCK);
            output.accept(ModBlocks.ENDER_PEARL_BLOCK);
            output.accept(ModBlocks.ROTTEN_FLESH_BLOCK);
            output.accept(ModBlocks.RAW_BEEF_BLOCK);
            output.accept(ModBlocks.COOKED_BEEF_BLOCK);
            output.accept(ModBlocks.RAW_MUTTON_BLOCK);
            output.accept(ModBlocks.COOKED_MUTTON_BLOCK);
            output.accept(ModBlocks.RAW_PORK_BLOCK);
            output.accept(ModBlocks.COOKED_PORK_BLOCK);
            output.accept(ModBlocks.RAW_RABBIT_BLOCK);
            output.accept(ModBlocks.COOKED_RABBIT_BLOCK);
            output.accept(ModBlocks.RAW_CHICKEN_BLOCK);
            output.accept(ModBlocks.COOKED_CHICKEN_BLOCK);
            output.accept(ModBlocks.RAW_SALMON_BLOCK);
            output.accept(ModBlocks.COOKED_SALMON_BLOCK);
            output.accept(ModBlocks.RAW_COD_BLOCK);
            output.accept(ModBlocks.COOKED_COD_BLOCK);
            output.accept(ModBlocks.RAW_DODO_BLOCK);
            output.accept(ModBlocks.COOKED_DODO_BLOCK);
            output.accept(ModBlocks.TROPICAL_FISH_BLOCK);
            output.accept(ModBlocks.PUFFERFISH_BLOCK);

            output.accept(ModBlocks.KETTLE);
        }).build());
}