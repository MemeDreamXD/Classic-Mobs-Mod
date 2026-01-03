package com.memedream.classicmobs.init;

import com.memedream.classicmobs.ClassicMobs;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;

public class ModDamageTypes {

    public static final ResourceKey<DamageType> ADVENTUROUS_EATER = ResourceKey.create(Registries.DAMAGE_TYPE, ClassicMobs.prefix("adventurous_eater"));
}
