package com.memedream.classicmobs.data.tags;

import com.memedream.classicmobs.ClassicMobs;
import com.memedream.classicmobs.init.ModEntities;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.tags.EntityTypeTags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class EntityTagGen extends EntityTypeTagsProvider {

	public EntityTagGen(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, @Nullable ExistingFileHelper helper) {
		super(output, provider, ClassicMobs.MOD_ID, helper);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		this.tag(EntityTypeTags.ARROWS).add(
				ModEntities.FLIGHT_ARROW.get()
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
