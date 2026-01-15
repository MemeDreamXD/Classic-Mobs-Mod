package com.memedream.classicmobs.item;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.context.UseOnContext;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;

public class SpadeItem extends BasicAOEItem {
    public SpadeItem(ToolMaterial material, float attackDamageBaseline, float attackSpeedBaseline, Properties properties) {
        super(properties.shovel(material, attackDamageBaseline, attackSpeedBaseline));
    }

    @Override
    public boolean canPerformAction(ItemStack stack, ItemAbility itemAbility) {
        return ItemAbilities.DEFAULT_SHOVEL_ACTIONS.contains(itemAbility);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        return MattockItem.tryUseAsShovel(context.getLevel(), context.getClickedPos(), context.getPlayer(), context.getLevel().getBlockState(context.getClickedPos()), context);
    }
}