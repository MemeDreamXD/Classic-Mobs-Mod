package com.memedream.classicmobs.init;

import com.memedream.classicmobs.ClassicMobs;
import com.memedream.classicmobs.entity.AntlionEntity;
import com.memedream.classicmobs.entity.DodoEntity;
import com.memedream.classicmobs.entity.HagEntity;
import com.memedream.classicmobs.entity.MyrmexEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, ClassicMobs.MOD_ID);

    public static final Supplier<EntityType<DodoEntity>> DODO =
            ENTITY_TYPES.register("dodo", () -> EntityType.Builder.of(DodoEntity::new, MobCategory.CREATURE)
                    .sized(0.75f, 0.75f).build(ClassicMobs.prefix("dodo").toString()));

    public static final Supplier<EntityType<AntlionEntity>> ANTLION =
            ENTITY_TYPES.register("antlion", () -> EntityType.Builder.of(AntlionEntity::new, MobCategory.MONSTER)
                    .sized(0.8f, 0.4f).build(ClassicMobs.prefix("antlion").toString()));

    public static final Supplier<EntityType<MyrmexEntity>> MYRMEX =
            ENTITY_TYPES.register("myrmex", () -> EntityType.Builder.of(MyrmexEntity::new, MobCategory.MONSTER)
                    .sized(0.8f, 0.4f).build(ClassicMobs.prefix("myrmex").toString()));

    public static final Supplier<EntityType<HagEntity>> HAG =
            ENTITY_TYPES.register("hag", () -> EntityType.Builder.of(HagEntity::new, MobCategory.MONSTER)
                    .sized(0.8f, 0.4f).build(ClassicMobs.prefix("hag").toString()));
}
