package com.memedream.classicmobs.init;

import com.memedream.classicmobs.ClassicMobs;
import com.memedream.classicmobs.entity.AntlionEntity;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ModEntityDataSerializers {

    public static final DeferredRegister<EntityDataSerializer<?>>SERIALIZERS = DeferredRegister.create(NeoForgeRegistries.ENTITY_DATA_SERIALIZERS, ClassicMobs.MOD_ID);

    public static final DeferredHolder<EntityDataSerializer<?>, EntityDataSerializer<AntlionEntity.AntlionState>> ANTLION_STATE = SERIALIZERS.register("antlion_state", () -> EntityDataSerializer.forValueType(AntlionEntity.AntlionState.STREAM_CODEC));
}
