package com.memedream.classicmobs.client.item;

import com.mojang.serialization.MapCodec;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.properties.numeric.RangeSelectItemModelProperty;
import net.minecraft.client.renderer.item.properties.numeric.UseDuration;
import net.minecraft.world.entity.ItemOwner;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.Nullable;

public record BolaSwing() implements RangeSelectItemModelProperty {

    public static final MapCodec<BolaSwing> MAP_CODEC = MapCodec.unit(new BolaSwing());

    @Override
    public float get(ItemStack stack, @Nullable ClientLevel level, @Nullable ItemOwner owner, int seed) {
        LivingEntity entity = owner == null ? null : owner.asLivingEntity();
        if (entity != null && entity.getUseItem() == stack) {
            float tickPercent = Math.min(1.0F, UseDuration.useDuration(stack, entity) / 40.0F);
            int ticksUsed = stack.getUseDuration(entity) - entity.getUseItemRemainingTicks();
            return (ticksUsed * tickPercent) / 2.0F % 4 * 0.25F;
        }
        return 0.0F;
    }

    @Override
    public MapCodec<? extends RangeSelectItemModelProperty> type() {
        return MAP_CODEC;
    }
}
