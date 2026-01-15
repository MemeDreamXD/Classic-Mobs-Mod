package com.memedream.classicmobs.entity;

import com.memedream.classicmobs.init.ModEntities;
import com.memedream.classicmobs.init.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.Nullable;

public class FlightArrow extends AbstractArrow {

    public FlightArrow(EntityType<? extends FlightArrow> entityType, Level level) {
        super(entityType, level);
    }

    public FlightArrow(Level level, LivingEntity owner, ItemStack pickupItemStack, @Nullable ItemStack firedFromWeapon) {
        super(ModEntities.FLIGHT_ARROW.get(), owner, level, pickupItemStack, firedFromWeapon);
    }

    public FlightArrow(Level level, double x, double y, double z, ItemStack pickupItemStack, @Nullable ItemStack firedFromWeapon) {
        super(ModEntities.FLIGHT_ARROW.get(), x, y, z, level, pickupItemStack, firedFromWeapon);
    }

    @Override
    protected double getDefaultGravity() {
        return 0.025F;
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return new ItemStack(ModItems.FLIGHT_ARROW.get());
    }
}
