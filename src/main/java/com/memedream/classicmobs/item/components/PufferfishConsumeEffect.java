package com.memedream.classicmobs.item.components;

import com.memedream.classicmobs.init.ModConsumeEffects;
import com.memedream.classicmobs.init.ModDamageTypes;
import com.mojang.serialization.MapCodec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.consume_effects.ConsumeEffect;
import net.minecraft.world.level.Level;

public class PufferfishConsumeEffect implements ConsumeEffect {

    private static final PufferfishConsumeEffect INSTANCE = new PufferfishConsumeEffect();
    public static final MapCodec<PufferfishConsumeEffect> CODEC = MapCodec.unit(INSTANCE);
    public static final StreamCodec<RegistryFriendlyByteBuf, PufferfishConsumeEffect> STREAM_CODEC = StreamCodec.unit(INSTANCE);

    @Override
    public Type<? extends ConsumeEffect> getType() {
        return ModConsumeEffects.PUFFERFISH.get();
    }

    @Override
    public boolean apply(Level level, ItemStack stack, LivingEntity user) {
        if (level instanceof ServerLevel serverLevel) {
            return user.hurtServer(serverLevel, level.damageSources().source(ModDamageTypes.ADVENTUROUS_EATER), user.getMaxHealth());
        }
        return true;
    }
}
