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
                output.accept(ModItems.DODO_SPAWN_EGG);
                output.accept(ModItems.ANTLION_SPAWN_EGG);
                output.accept(ModItems.MYRMEX_SPAWN_EGG);
                output.accept(ModItems.HAG_SPAWN_EGG);
                output.accept(ModItems.HARPY_SPAWN_EGG);
                output.accept(ModItems.ROCKET_SPAWN_EGG);
                output.accept(ModItems.SUPPORT_SPAWN_EGG);
                output.accept(ModItems.FESTIVE_SPAWN_EGG);
                output.accept(ModItems.RAW_DODO);
                output.accept(ModItems.COOKED_DODO);
                output.accept(ModItems.CHITIN);
                output.accept(ModItems.HARPY_FEATHER);
                output.accept(ModItems.CHITIN_HELMET);
                output.accept(ModItems.CHITIN_CHESTPLATE);
                output.accept(ModItems.CHITIN_LEGGINGS);
                output.accept(ModItems.CHITIN_BOOTS);
                output.accept(ModItems.CHITIN_UPGRADE_SMITHING_TEMPLATE);
                output.accept(ModBlocks.GUNPOWDER_BLOCK);
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
                output.accept(ModBlocks.LEATHER_BLOCK);
                output.accept(ModBlocks.CHITIN_BLOCK);
                output.accept(ModBlocks.MAGMA_CREAM_BLOCK);
                output.accept(ModBlocks.PHANTOM_MEMBRANE_BLOCK);
            }).build());
}