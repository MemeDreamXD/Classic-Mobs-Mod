package com.memedream.classicmobs.item;

import net.minecraft.world.item.ToolMaterial;

public class HammerItem extends BasicAOEItem {
    public HammerItem(ToolMaterial material, float attackDamageBaseline, float attackSpeedBaseline, Properties properties) {
        super(properties.pickaxe(material, attackDamageBaseline, attackSpeedBaseline));
    }
}