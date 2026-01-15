package com.memedream.classicmobs.item;

import com.memedream.classicmobs.entity.FlightArrow;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.Nullable;

public class FlightArrowItem extends ArrowItem {

    public FlightArrowItem(Properties properties) {
        super(properties);
    }

    public AbstractArrow createArrow(Level level, ItemStack ammo, LivingEntity shooter, @Nullable ItemStack weapon) {
        return new FlightArrow(level, shooter, ammo.copyWithCount(1), weapon);
    }

    @Override
    public Projectile asProjectile(Level level, Position pos, ItemStack stack, Direction direction) {
        FlightArrow flightArrow = new FlightArrow(level, pos.x(), pos.y(), pos.z(), stack.copyWithCount(1), null);
        flightArrow.pickup = AbstractArrow.Pickup.ALLOWED;
        return flightArrow;
    }
}
