package com.memedream.classicmobs.datagen.data.tags;

import com.memedream.classicmobs.ClassicMobs;
import com.memedream.classicmobs.init.ModEntities;
import com.memedream.classicmobs.init.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.neoforged.neoforge.common.Tags;

import java.util.concurrent.CompletableFuture;

public class EntityTagGen extends EntityTypeTagsProvider {

    public EntityTagGen(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider, ClassicMobs.MOD_ID);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(ModTags.Entities.BOLA_IMMUNE).add(
            EntityType.ARMOR_STAND,
            EntityType.COD,
            EntityType.SALMON,
            EntityType.TROPICAL_FISH,
            EntityType.PUFFERFISH,
            EntityType.SLIME,
            EntityType.MAGMA_CUBE,
            EntityType.WARDEN,
            EntityType.GHAST,
            EntityType.HAPPY_GHAST,
            EntityType.ELDER_GUARDIAN
        ).addTags(
            Tags.EntityTypes.BOSSES,
            Tags.EntityTypes.CAPTURING_NOT_SUPPORTED
        ).addOptionalTags(
            TagKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath("c", "slimes")),
            TagKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath("c", "fish"))
        );

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
