package com.memedream.classicmobs.init;

import com.memedream.classicmobs.ClassicMobs;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageEffects;
import net.minecraft.world.damagesource.DamageScaling;
import net.minecraft.world.damagesource.DamageType;

public class ModDamageTypes {

    public static final ResourceKey<DamageType> ADVENTUROUS_EATER = ResourceKey.create(Registries.DAMAGE_TYPE, ClassicMobs.prefix("adventurous_eater"));

    public static void bootstrap(BootstrapContext<DamageType> context) {
        context.register(ADVENTUROUS_EATER, new DamageType("classic_mobs.adventurous_eater", DamageScaling.ALWAYS, 0.0F, DamageEffects.THORNS));
    }
}
