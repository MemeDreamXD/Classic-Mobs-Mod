package com.memedream.classicmobs.item;

import com.memedream.classicmobs.init.ModBlocks;
import com.memedream.classicmobs.init.ModDamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class PufferfishBlockItem extends MeatBlockItem {
    public PufferfishBlockItem(Properties properties) {
        super(ModBlocks.PUFFERFISH_BLOCK.get(), properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        livingEntity.hurt(level.damageSources().source(ModDamageTypes.ADVENTUROUS_EATER), livingEntity.getMaxHealth());
        return super.finishUsingItem(stack, level, livingEntity);
    }
}
