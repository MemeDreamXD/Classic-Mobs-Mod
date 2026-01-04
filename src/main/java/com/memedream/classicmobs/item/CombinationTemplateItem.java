package com.memedream.classicmobs.item;

import com.memedream.classicmobs.ClassicMobs;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.SmithingTemplateItem;

import java.util.List;

public class CombinationTemplateItem extends SmithingTemplateItem {

    private static final Component COMBINATION_UPGRADE = Component.translatable(Util.makeDescriptionId("upgrade", ClassicMobs.prefix("combination_upgrade"))).withStyle(ChatFormatting.GRAY);
    private static final Component COMBINATION_UPGRADE_APPLIES_TO = Component.translatable(Util.makeDescriptionId("item", ClassicMobs.prefix("smithing_template.combination_upgrade.applies_to"))).withStyle(ChatFormatting.BLUE);
    private static final Component COMBINATION_UPGRADE_INGREDIENTS = Component.translatable(Util.makeDescriptionId("item", ClassicMobs.prefix("smithing_template.combination_upgrade.ingredients"))).withStyle(ChatFormatting.BLUE);
    private static final Component COMBINATION_UPGRADE_BASE_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", ClassicMobs.prefix("smithing_template.combination_upgrade.base_slot_description")));
    private static final Component COMBINATION_UPGRADE_ADDITIONS_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", ClassicMobs.prefix("smithing_template.combination_upgrade.additions_slot_description")));
    private static final ResourceLocation EMPTY_SLOT_AXE = ClassicMobs.prefix("item/empty_item_slot_axe");
    private static final ResourceLocation EMPTY_SLOT_PICKAXE = ClassicMobs.prefix("item/empty_item_slot_pickaxe");
    private static final ResourceLocation EMPTY_SLOT_SHOVEL = ClassicMobs.prefix("item/empty_item_slot_shovel");
    private static final ResourceLocation EMPTY_SLOT_HOE = ClassicMobs.prefix("item/empty_item_slot_hoe");

    public CombinationTemplateItem() {
        super(COMBINATION_UPGRADE_APPLIES_TO,
                COMBINATION_UPGRADE_INGREDIENTS,
                COMBINATION_UPGRADE,
                COMBINATION_UPGRADE_BASE_SLOT_DESCRIPTION,
                COMBINATION_UPGRADE_ADDITIONS_SLOT_DESCRIPTION,
                List.of(EMPTY_SLOT_AXE, EMPTY_SLOT_PICKAXE, EMPTY_SLOT_HOE, EMPTY_SLOT_SHOVEL),
                List.of(EMPTY_SLOT_PICKAXE, EMPTY_SLOT_AXE, EMPTY_SLOT_SHOVEL, EMPTY_SLOT_HOE));
    }
}
