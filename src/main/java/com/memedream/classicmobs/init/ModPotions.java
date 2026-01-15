package com.memedream.classicmobs.init;

import com.memedream.classicmobs.ClassicMobs;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModPotions {
    public static final DeferredRegister<Potion> POTIONS =
            DeferredRegister.create(BuiltInRegistries.POTION, ClassicMobs.MOD_ID);

    public static final Holder<Potion> FAE_CURSE_POTION = POTIONS.register("fae_curse", () -> new Potion("fae_curse", new MobEffectInstance(ModEffects.FAE_CURSE, 3600, 0)));
    public static final Holder<Potion> STENCH_POTION = POTIONS.register("stench", () -> new Potion("stench", new MobEffectInstance(ModEffects.STENCH, 1200, 0)));
}
