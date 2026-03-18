package com.memedream.classicmobs.init;

import com.memedream.classicmobs.ClassicMobs;
import com.memedream.classicmobs.block.entity.BlazeRodBlockEntity;
import com.memedream.classicmobs.block.entity.BreezeRodBlockEntity;
import com.memedream.classicmobs.block.entity.KettleBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlockEntities {

	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, ClassicMobs.MOD_ID);

	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<BlazeRodBlockEntity>> BLAZE_ROD = BLOCK_ENTITIES.register("blaze_rod", () -> new BlockEntityType<>(BlazeRodBlockEntity::new, ModBlocks.BLAZE_ROD_BLOCK.get()));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<BreezeRodBlockEntity>> BREEZE_ROD = BLOCK_ENTITIES.register("breeze_rod", () -> new BlockEntityType<>(BreezeRodBlockEntity::new, ModBlocks.BREEZE_ROD_BLOCK.get()));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<KettleBlockEntity>> KETTLE = BLOCK_ENTITIES.register("kettle", () -> new BlockEntityType<>(KettleBlockEntity::new, ModBlocks.KETTLE.get()));
}
