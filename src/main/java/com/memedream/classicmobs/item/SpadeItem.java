package com.memedream.classicmobs.item;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.context.UseOnContext;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;

public class SpadeItem extends BasicAOEItem {

    public SpadeItem(ToolMaterial material, float attackDamageBaseline, float attackSpeedBaseline, Properties properties) {
        super(properties.shovel(material, attackDamageBaseline, attackSpeedBaseline));
    }

    @Override
    public boolean canPerformAction(ItemInstance instance, ItemAbility ability) {
        return ItemAbilities.DEFAULT_SHOVEL_ACTIONS.contains(ability);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        boolean success = false;
        if (context.getPlayer() != null) {
            for (BlockPos pos : AOEItem.getBlocksToBeDestroyed(1, context.getClickedPos(), context.getPlayer())) {
                success |= MattockItem.tryUseAsShovel(context.getLevel(), pos, context.getPlayer(), context.getLevel().getBlockState(pos), context).consumesAction();
            }
        }
        return success ? InteractionResult.SUCCESS : InteractionResult.PASS;
    }
}