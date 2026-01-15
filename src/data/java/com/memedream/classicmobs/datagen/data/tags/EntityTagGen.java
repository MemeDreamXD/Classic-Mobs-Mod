package com.memedream.classicmobs.datagen.data.tags;

import com.memedream.classicmobs.ClassicMobs;
import com.memedream.classicmobs.init.ModEntities;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.tags.EntityTypeTags;

import java.util.concurrent.CompletableFuture;

public class EntityTagGen extends EntityTypeTagsProvider {

    public EntityTagGen(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider, ClassicMobs.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(EntityTypeTags.ARROWS).add(
            ModEntities.FLIGHT_ARROW.get()
        );

        this.tag(EntityTypeTags.REDIRECTABLE_PROJECTILE).add(
            ModEntities.FESTIVE_TNT.get()
        );

        this.tag(EntityTypeTags.FALL_DAMAGE_IMMUNE).add(
            ModEntities.DODO.get(),
            ModEntities.HARPY.get()
        );

        this.tag(EntityTypeTags.ARTHROPOD).add(
            ModEntities.ANTLION.get()
        );
    }
}
