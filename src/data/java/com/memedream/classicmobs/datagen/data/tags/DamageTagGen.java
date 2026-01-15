package com.memedream.classicmobs.datagen.data.tags;

import com.memedream.classicmobs.ClassicMobs;
import com.memedream.classicmobs.init.ModDamageTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.KeyTagProvider;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageType;

import java.util.concurrent.CompletableFuture;

public class DamageTagGen extends KeyTagProvider<DamageType> {

    public DamageTagGen(PackOutput output, CompletableFuture<HolderLookup.Provider> future) {
        super(output, Registries.DAMAGE_TYPE, future, ClassicMobs.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(DamageTypeTags.BYPASSES_ARMOR).add(
            ModDamageTypes.ADVENTUROUS_EATER
        );

        this.tag(DamageTypeTags.BYPASSES_EFFECTS).add(
            ModDamageTypes.ADVENTUROUS_EATER
        );

        this.tag(DamageTypeTags.BYPASSES_ENCHANTMENTS).add(
            ModDamageTypes.ADVENTUROUS_EATER
        );

        this.tag(DamageTypeTags.BYPASSES_RESISTANCE).add(
            ModDamageTypes.ADVENTUROUS_EATER
        );

        this.tag(DamageTypeTags.NO_KNOCKBACK).add(
            ModDamageTypes.ADVENTUROUS_EATER
        );

        this.tag(DamageTypeTags.BYPASSES_WOLF_ARMOR).add(
            ModDamageTypes.ADVENTUROUS_EATER
        );
    }
}
