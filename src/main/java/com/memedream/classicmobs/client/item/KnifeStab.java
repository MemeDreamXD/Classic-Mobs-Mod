package com.memedream.classicmobs.client.item;

import com.mojang.serialization.MapCodec;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.properties.conditional.ConditionalItemModelProperty;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.Nullable;

//cant use IsKeybindDown for this as keybinds are not available during datagen
public record KnifeStab() implements ConditionalItemModelProperty {

    public static final MapCodec<KnifeStab> MAP_CODEC = MapCodec.unit(new KnifeStab());

    @Override
    public MapCodec<? extends ConditionalItemModelProperty> type() {
        return MAP_CODEC;
    }

    @Override
    public boolean get(ItemStack stack, @Nullable ClientLevel level, @Nullable LivingEntity owner, int seed, ItemDisplayContext context) {
        return owner != null && owner.isShiftKeyDown() && owner.onGround();
    }
}
