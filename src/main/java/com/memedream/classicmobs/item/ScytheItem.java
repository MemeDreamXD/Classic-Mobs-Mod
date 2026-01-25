package com.memedream.classicmobs.item;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;

public class ScytheItem extends BasicAOEItem {

    public ScytheItem(ToolMaterial material, float attackDamageBaseline, float attackSpeedBaseline, Properties properties) {
        super(properties.hoe(material, attackDamageBaseline, attackSpeedBaseline));
    }

    @Override
    public boolean canPerformAction(ItemStack stack, ItemAbility itemAbility) {
        return ItemAbilities.DEFAULT_HOE_ACTIONS.contains(itemAbility);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        boolean success = false;
        if (context.getPlayer() != null) {
            for (BlockPos pos : AOEItem.getBlocksToBeDestroyed(1, context.getClickedPos(), context.getPlayer())) {
                UseOnContext ctx = new UseOnContext(context.getPlayer(), context.getHand(), new BlockHitResult(context.getClickLocation(), context.getClickedFace(), pos, context.isInside()));
                success |= MattockItem.tryUseAsHoe(context.getLevel(), pos, context.getPlayer(), context.getLevel().getBlockState(pos), ctx).consumesAction();
            }
        }
        return success ? InteractionResult.SUCCESS : InteractionResult.PASS;
    }
}