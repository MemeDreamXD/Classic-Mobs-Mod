package com.memedream.classicmobs.init;

import com.memedream.classicmobs.ClassicMobs;
import com.memedream.classicmobs.effect.FaeCurseEffect;
import com.memedream.classicmobs.effect.StenchEffect;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEffects {

    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, ClassicMobs.MOD_ID);

    public static final DeferredHolder<MobEffect, MobEffect> FAE_CURSE = MOB_EFFECTS.register("fae_curse", () -> new FaeCurseEffect(MobEffectCategory.NEUTRAL, 0x9edd2e)
        .addAttributeModifier(Attributes.LUCK, Identifier.fromNamespaceAndPath(ClassicMobs.MOD_ID, "fae_curse"), 1.5f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));

    public static final DeferredHolder<MobEffect, MobEffect> STENCH = MOB_EFFECTS.register("stench", () -> new StenchEffect(MobEffectCategory.HARMFUL, 0x15140f));

    public static final DeferredHolder<MobEffect, MobEffect> VELOCITY = MOB_EFFECTS.register("velocity", () -> new FaeCurseEffect(MobEffectCategory.BENEFICIAL, 0xdfbf3d)
        .addAttributeModifier(Attributes.MOVEMENT_SPEED, Identifier.fromNamespaceAndPath(ClassicMobs.MOD_ID, "velocity"), 0.2f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
        .addAttributeModifier(Attributes.ATTACK_SPEED, Identifier.fromNamespaceAndPath(ClassicMobs.MOD_ID, "velocity"), 0.2f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
        .addAttributeModifier(Attributes.BLOCK_BREAK_SPEED, Identifier.fromNamespaceAndPath(ClassicMobs.MOD_ID, "velocity"), 0.2f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));

    public static final DeferredHolder<MobEffect, MobEffect> FORTIFY = MOB_EFFECTS.register("fortify", () -> new FaeCurseEffect(MobEffectCategory.BENEFICIAL, 0xdfbf3d)
        .addAttributeModifier(Attributes.ARMOR, Identifier.fromNamespaceAndPath(ClassicMobs.MOD_ID, "fortify"), 4.0f, AttributeModifier.Operation.ADD_VALUE)
        .addAttributeModifier(Attributes.ARMOR_TOUGHNESS, Identifier.fromNamespaceAndPath(ClassicMobs.MOD_ID, "fortify"), 0.4f, AttributeModifier.Operation.ADD_VALUE));

    public static final DeferredHolder<MobEffect, MobEffect> EMPOWER = MOB_EFFECTS.register("empower", () -> new FaeCurseEffect(MobEffectCategory.BENEFICIAL, 0xdfbf3d)
        .addAttributeModifier(Attributes.ATTACK_DAMAGE, Identifier.fromNamespaceAndPath(ClassicMobs.MOD_ID, "empower"), 0.2f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
        .addAttributeModifier(Attributes.ATTACK_KNOCKBACK, Identifier.fromNamespaceAndPath(ClassicMobs.MOD_ID, "empower"), 0.2f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
}
