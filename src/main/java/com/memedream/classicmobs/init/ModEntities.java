package com.memedream.classicmobs.init;

import com.memedream.classicmobs.ClassicMobs;
import com.memedream.classicmobs.entity.*;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jspecify.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

//TODO spawn placements as needed
public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, ClassicMobs.MOD_ID);
    public static final DeferredRegister<Item> SPAWN_EGGS = DeferredRegister.create(Registries.ITEM, ClassicMobs.MOD_ID);
    public static final Map<Holder<EntityType<?>>, Supplier<AttributeSupplier.Builder>> ATTRIBUTES = new HashMap<>();
    public static final Map<Holder<EntityType<?>>, SpawnPlacements.SpawnPredicate<?>> SPAWN_PREDICATES = new HashMap<>();

    public static final DeferredHolder<EntityType<?>, EntityType<DodoEntity>> DODO = registerWithEgg("dodo", EntityType.Builder.of(DodoEntity::new, MobCategory.CREATURE).sized(0.75F, 0.75F), DodoEntity::createAttributes, null);
    public static final DeferredHolder<EntityType<?>, EntityType<AntlionEntity>> ANTLION = registerWithEgg("antlion", EntityType.Builder.of(AntlionEntity::new, MobCategory.MONSTER).sized(1.4F, 0.7F), AntlionEntity::createAttributes, null);
    public static final DeferredHolder<EntityType<?>, EntityType<MyrmexEntity>> MYRMEX = registerWithEgg("myrmex", EntityType.Builder.of(MyrmexEntity::new, MobCategory.MONSTER).sized(0.7F, 1.95F), MyrmexEntity::createAttributes, null);
    public static final DeferredHolder<EntityType<?>, EntityType<HagEntity>> HAG = registerWithEgg("hag", EntityType.Builder.of(HagEntity::new, MobCategory.MONSTER).sized(0.8F, 1.1F), HagEntity::createAttributes, null);
    public static final DeferredHolder<EntityType<?>, EntityType<HarpyEntity>> HARPY = registerWithEgg("harpy", EntityType.Builder.of(HarpyEntity::new, MobCategory.MONSTER).sized(0.8F, 1.6F), HarpyEntity::createAttributes, null);
    public static final DeferredHolder<EntityType<?>, EntityType<RocketCreeperEntity>> ROCKET_CREEPER = registerWithEgg("rocket_creeper", EntityType.Builder.of(RocketCreeperEntity::new, MobCategory.MONSTER).sized(0.6F, 1.7F), RocketCreeperEntity::createAttributes, null);
    public static final DeferredHolder<EntityType<?>, EntityType<SupportCreeperEntity>> SUPPORT_CREEPER = registerWithEgg("support_creeper", EntityType.Builder.of(SupportCreeperEntity::new, MobCategory.MONSTER).sized(0.6F, 1.7F), SupportCreeperEntity::createAttributes, null);
    public static final DeferredHolder<EntityType<?>, EntityType<FestiveCreeperEntity>> FESTIVE_CREEPER = registerWithEgg("festive_creeper", EntityType.Builder.of(FestiveCreeperEntity::new, MobCategory.MONSTER).sized(0.6F, 1.7F), FestiveCreeperEntity::createAttributes, null);

    //misc.
    public static final DeferredHolder<EntityType<?>, EntityType<FestiveTntEntity>> FESTIVE_TNT = registerMisc("festive_tnt", EntityType.Builder.<FestiveTntEntity>of(FestiveTntEntity::new, MobCategory.MISC).sized(0.5F, 0.5F));
    public static final DeferredHolder<EntityType<?>, EntityType<FallingGunpowderEntity>> FALLING_GUNPOWDER = registerMisc("falling_gunpowder", EntityType.Builder.of(FallingGunpowderEntity::new, MobCategory.MISC).sized(0.98F, 0.98F).clientTrackingRange(10).updateInterval(20));
    public static final DeferredHolder<EntityType<?>, EntityType<FlightArrow>> FLIGHT_ARROW = registerMisc("flight_arrow", EntityType.Builder.<FlightArrow>of(FlightArrow::new, MobCategory.MISC).sized(0.5f, 0.5f).eyeHeight(0.13F).clientTrackingRange(4).updateInterval(20));

    public static <E extends Entity> DeferredHolder<EntityType<?>, EntityType<E>> registerMisc(String name, EntityType.Builder<E> builder) {
        return ENTITY_TYPES.register(name, () -> builder.noLootTable().build(createIDFor(name)));
    }

    public static <E extends LivingEntity> DeferredHolder<EntityType<?>, EntityType<E>> registerWithAttributes(String name, EntityType.Builder<E> builder, Supplier<AttributeSupplier.Builder> attributes) {
        DeferredHolder<EntityType<?>, EntityType<E>> ret = ENTITY_TYPES.register(name, () -> builder.build(createIDFor(name)));
        ATTRIBUTES.put(ret, attributes);
        return ret;
    }

    public static <E extends LivingEntity> DeferredHolder<EntityType<?>, EntityType<E>> registerWithPlacement(String name, EntityType.Builder<E> builder, Supplier<AttributeSupplier.Builder> attributes, SpawnPlacements.@Nullable SpawnPredicate<E> predicate) {
        DeferredHolder<EntityType<?>, EntityType<E>> ret = registerWithAttributes(name, builder, attributes);
        if (predicate != null) {
            SPAWN_PREDICATES.put(ret, predicate);
        }
        return ret;
    }

    public static <E extends Mob> DeferredHolder<EntityType<?>, EntityType<E>> registerWithEgg(String name, EntityType.Builder<E> builder, Supplier<AttributeSupplier.Builder> attributes, SpawnPlacements.@Nullable SpawnPredicate<E> predicate) {
        DeferredHolder<EntityType<?>, EntityType<E>> ret = registerWithPlacement(name, builder, attributes, predicate);
        SPAWN_EGGS.register(name + "_spawn_egg", () -> new SpawnEggItem(new Item.Properties().spawnEgg(ret.get()).setId(ResourceKey.create(Registries.ITEM, ClassicMobs.prefix(name + "_spawn_egg")))));
        return ret;
    }

    private static ResourceKey<EntityType<?>> createIDFor(String name) {
        return ResourceKey.create(Registries.ENTITY_TYPE, ClassicMobs.prefix(name));
    }
}