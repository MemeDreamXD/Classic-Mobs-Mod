package com.memedream.classicmobs.init;

import com.memedream.classicmobs.ClassicMobs;
import com.memedream.classicmobs.effect.FaeCurseEffect;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, ClassicMobs.MOD_ID);

    public static final Holder<MobEffect> FAE_CURSE_EFFECT = MOB_EFFECTS.register("fae_curse",
            () -> new FaeCurseEffect(MobEffectCategory.NEUTRAL, 0x9edd2e).addAttributeModifier(Attributes.LUCK, ResourceLocation.fromNamespaceAndPath(ClassicMobs.MOD_ID, "fae_curse"), 1.5f,
                    AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));

    public static final Holder<MobEffect> STENCH_EFFECT = MOB_EFFECTS.register("stench",
            () -> new FaeCurseEffect(MobEffectCategory.HARMFUL, 0x15140f));

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
