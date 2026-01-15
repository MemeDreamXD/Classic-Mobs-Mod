package com.memedream.classicmobs.event;

import com.memedream.classicmobs.init.ModEntities;
import com.memedream.classicmobs.init.ModItems;
import com.memedream.classicmobs.init.ModPotions;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;

public class ModRegistrationEvents {

    public static void init(IEventBus bus) {
        bus.addListener(ModRegistrationEvents::registerAttributes);
        bus.addListener(ModRegistrationEvents::registerPlacements);
        NeoForge.EVENT_BUS.addListener(ModRegistrationEvents::onBrewingRecipeRegister);
        NeoForge.EVENT_BUS.addListener(AoeToolEvents::harvestWithMultitools);
    }

    @SuppressWarnings("unchecked") //entities added this way will always extend LivingEntity
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        ModEntities.ATTRIBUTES.forEach((type, builder) -> event.put((EntityType<? extends LivingEntity>) type.value(), builder.get().build()));
    }

    @SuppressWarnings("unchecked") //PAIN
    public static void registerPlacements(RegisterSpawnPlacementsEvent event) {
        ModEntities.SPAWN_PREDICATES.forEach((type, predicate) -> event.register((EntityType<Entity>) type.value(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (SpawnPlacements.SpawnPredicate<Entity>) predicate, RegisterSpawnPlacementsEvent.Operation.REPLACE));
    }

    public static void onBrewingRecipeRegister(RegisterBrewingRecipesEvent event) {
        PotionBrewing.Builder builder = event.getBuilder();

        builder.addMix(Potions.AWKWARD, ModItems.LOCK_OF_HAG.asItem(), ModPotions.FAE_CURSE_POTION);
        builder.addMix(Potions.MUNDANE, ModItems.LOCK_OF_HAG.asItem(), ModPotions.STENCH_POTION);
    }
}
