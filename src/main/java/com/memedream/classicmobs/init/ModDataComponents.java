package com.memedream.classicmobs.init;

import com.memedream.classicmobs.ClassicMobs;
import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModDataComponents {

    public static final DeferredRegister<DataComponentType<?>> COMPONENTS = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, ClassicMobs.MOD_ID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Float>> KNIFE_BREAK_CHANCE = COMPONENTS.register("knife_break_chance", () -> DataComponentType.<Float>builder().persistent(Codec.floatRange(0.0F, 1.0F)).networkSynchronized(ByteBufCodecs.FLOAT).build());
}
