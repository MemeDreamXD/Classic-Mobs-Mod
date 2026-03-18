package com.memedream.classicmobs.init;

import com.memedream.classicmobs.ClassicMobs;
import com.memedream.classicmobs.inventory.KettleMenu;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModMenuTypes {

    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(Registries.MENU, ClassicMobs.MOD_ID);

    public static final DeferredHolder<MenuType<?>, MenuType<KettleMenu>> KETTLE = MENUS.register("kettle", () -> new MenuType<>(KettleMenu::new, FeatureFlags.REGISTRY.allFlags()));
}
