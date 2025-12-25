package com.memedream.classicmobs.init;

import com.memedream.classicmobs.ClassicMobs;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ClassicMobs.MOD_ID);

    public static final Supplier<CreativeModeTab> CLASSIC_MOBS_TAB = CREATIVE_MODE_TAB.register("classic_mobs_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.RAW_DODO.get()))
                    .title(Component.translatable("creativetab.classic_mobs.classic_mobs_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.DODO_SPWAN_EGG);
                        output.accept(ModItems.RAW_DODO);
                        output.accept(ModItems.COOKED_DODO);
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
                    }).build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
    }