package com.memedream.classicmobs.init;

import com.memedream.classicmobs.ClassicMobs;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ClassicMobs.MOD_ID);

    public static final Supplier<CreativeModeTab> CLASSIC_MOBS_TAB = TABS.register("classic_mobs_tab", () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.RAW_DODO.get()))
            .title(Component.translatable("creativetab.classic_mobs.classic_mobs_tab"))
            .displayItems((itemDisplayParameters, output) -> {
                output.accept(ModItems.DODO_SPAWN_EGG);
                output.accept(ModItems.ANTLION_SPAWN_EGG);
                output.accept(ModItems.MYRMEX_SPAWN_EGG);
                output.accept(ModItems.HAG_SPAWN_EGG);
                output.accept(ModItems.HARPY_SPAWN_EGG);
                output.accept(ModItems.ROCKET_CREEPER_SPAWN_EGG);
                output.accept(ModItems.SUPPORT_CREEPER_SPAWN_EGG);
                output.accept(ModItems.FESTIVE_CREEPER_SPAWN_EGG);
                output.accept(ModItems.RAW_DODO);
                output.accept(ModItems.COOKED_DODO);
                output.accept(ModItems.CHITIN);
                output.accept(ModItems.HARPY_FEATHER);
                output.accept(ModItems.LOCK_OF_HAG);
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
                output.accept(ModItems.STONE_PICKAXE_AXE);
                output.accept(ModItems.STONE_MATTOCK);
                output.accept(ModItems.IRON_PICKAXE_AXE);
                output.accept(ModItems.IRON_MATTOCK);
                output.accept(ModItems.GOLDEN_PICKAXE_AXE);
                output.accept(ModItems.GOLDEN_MATTOCK);
                output.accept(ModItems.DIAMOND_PICKAXE_AXE);
                output.accept(ModItems.DIAMOND_MATTOCK);
                output.accept(ModItems.NETHERITE_PICKAXE_AXE);
                output.accept(ModItems.NETHERITE_MATTOCK);
                output.accept(ModBlocks.TRICKLITH_BLOCK);
                output.accept(ModBlocks.POINTED_TRICKLITH);
                output.accept(ModBlocks.LEATHER_BLOCK);
                output.accept(ModBlocks.STRING_BLOCK);
                output.accept(ModBlocks.CHITIN_BLOCK);
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
                output.accept(ModBlocks.TROPICAL_FISH_BLOCK);
                output.accept(ModBlocks.PUFFERFISH_BLOCK);

            }).build());
}