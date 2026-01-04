package com.memedream.classicmobs.item;

import com.memedream.classicmobs.init.ModTags;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;

public class PickaxeAxeItem extends DiggerItem {
    public PickaxeAxeItem(Tier tier, Properties properties) {
        super(tier, ModTags.Blocks.MINEABLE_WITH_PICKAXE_AXE, properties);
    }
    @Override
    public boolean canPerformAction(ItemStack stack, net.neoforged.neoforge.common.ItemAbility itemAbility) {
        return ModItemAbilities.DEFAULT_PICKAXE_AXE_ACTIONS.contains(itemAbility);
    }
}
