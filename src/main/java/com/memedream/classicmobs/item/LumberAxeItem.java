package com.memedream.classicmobs.item;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ToolMaterial;

public class LumberAxeItem extends AxeItem implements AOEItem {
    public LumberAxeItem(ToolMaterial material, float attackDamageBaseline, float attackSpeedBaseline, Properties properties) {
        super(material, attackDamageBaseline, attackSpeedBaseline, properties);
    }
}