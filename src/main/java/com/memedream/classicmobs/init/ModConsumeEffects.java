package com.memedream.classicmobs.init;

import com.memedream.classicmobs.ClassicMobs;
import com.memedream.classicmobs.item.components.PufferfishConsumeEffect;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.consume_effects.ConsumeEffect;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModConsumeEffects {

    public static final DeferredRegister<ConsumeEffect.Type<?>> CONSUME_EFFECTS = DeferredRegister.create(BuiltInRegistries.CONSUME_EFFECT_TYPE, ClassicMobs.MOD_ID);

    public static final DeferredHolder<ConsumeEffect.Type<?>, ConsumeEffect.Type<PufferfishConsumeEffect>> PUFFERFISH = CONSUME_EFFECTS.register("pufferfish", () -> new ConsumeEffect.Type<>(PufferfishConsumeEffect.CODEC, PufferfishConsumeEffect.STREAM_CODEC));
}
