package com.memedream.classicmobs.init;

import com.memedream.classicmobs.ClassicMobs;
import com.memedream.classicmobs.entity.*;
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
                    .sized(1.4F, 0.7F).build(ClassicMobs.prefix("antlion").toString()));

    public static final Supplier<EntityType<MyrmexEntity>> MYRMEX =
            ENTITY_TYPES.register("myrmex", () -> EntityType.Builder.of(MyrmexEntity::new, MobCategory.MONSTER)
                    .sized(0.7f, 1.95f).build(ClassicMobs.prefix("myrmex").toString()));

    public static final Supplier<EntityType<HagEntity>> HAG =
            ENTITY_TYPES.register("hag", () -> EntityType.Builder.of(HagEntity::new, MobCategory.MONSTER)
                    .sized(0.8f, 1.1f).build(ClassicMobs.prefix("hag").toString()));

    public static final Supplier<EntityType<HarpyEntity>> HARPY =
            ENTITY_TYPES.register("harpy", () -> EntityType.Builder.of(HarpyEntity::new, MobCategory.MONSTER)
                    .sized(0.8f, 1.6f).build(ClassicMobs.prefix("harpy").toString()));

    public static final Supplier<EntityType<RocketCreeperEntity>> ROCKET_CREEPER =
            ENTITY_TYPES.register("rocket_creeper", () -> EntityType.Builder.of(RocketCreeperEntity::new, MobCategory.MONSTER)
                    .sized(0.6f, 1.7f).build(ClassicMobs.prefix("rocket_creeper").toString()));

    public static final Supplier<EntityType<SupportCreeperEntity>> SUPPORT_CREEPER =
            ENTITY_TYPES.register("support_creeper", () -> EntityType.Builder.of(SupportCreeperEntity::new, MobCategory.MONSTER)
                    .sized(0.6f, 1.7f).build(ClassicMobs.prefix("support_creeper").toString()));

    public static final Supplier<EntityType<FestiveCreeperEntity>> FESTIVE_CREEPER =
            ENTITY_TYPES.register("festive_creeper", () -> EntityType.Builder.of(FestiveCreeperEntity::new, MobCategory.MONSTER)
                    .sized(0.6f, 1.7f).build(ClassicMobs.prefix("festive_creeper").toString()));

    public static final Supplier<EntityType<FestiveTntEntity>> FESTIVE_TNT =
            ENTITY_TYPES.register("festive_tnt", () -> EntityType.Builder.<FestiveTntEntity>of(FestiveTntEntity::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F).build(ClassicMobs.prefix("festive_tnt").toString()));

    public static final Supplier<EntityType<FlightArrow>> FLIGHT_ARROW =
            ENTITY_TYPES.register("flight_arrow", () -> EntityType.Builder.<FlightArrow>of(FlightArrow::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f).eyeHeight(0.13F).clientTrackingRange(4).updateInterval(20).build(ClassicMobs.prefix("flight_arrow").toString()));

}