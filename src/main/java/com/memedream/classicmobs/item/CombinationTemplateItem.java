package com.memedream.classicmobs.item;

import com.memedream.classicmobs.ClassicMobs;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Util;
import net.minecraft.world.item.SmithingTemplateItem;

import java.util.List;

public class CombinationTemplateItem extends SmithingTemplateItem {

    public static final Component COMBINATION_UPGRADE_APPLIES_TO = Component.translatable(Util.makeDescriptionId("item", ClassicMobs.prefix("smithing_template.combination_upgrade.applies_to"))).withStyle(ChatFormatting.BLUE);
    public static final Component COMBINATION_UPGRADE_INGREDIENTS = Component.translatable(Util.makeDescriptionId("item", ClassicMobs.prefix("smithing_template.combination_upgrade.ingredients"))).withStyle(ChatFormatting.BLUE);
    public static final Component COMBINATION_UPGRADE_BASE_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", ClassicMobs.prefix("smithing_template.combination_upgrade.base_slot_description")));
    public static final Component COMBINATION_UPGRADE_ADDITIONS_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", ClassicMobs.prefix("smithing_template.combination_upgrade.additions_slot_description")));
    private static final Identifier EMPTY_SLOT_AXE = Identifier.withDefaultNamespace("container/slot/axe");
    private static final Identifier EMPTY_SLOT_PICKAXE = Identifier.withDefaultNamespace("container/slot/pickaxe");
    private static final Identifier EMPTY_SLOT_SHOVEL = Identifier.withDefaultNamespace("container/slot/shovel");
    private static final Identifier EMPTY_SLOT_HOE = Identifier.withDefaultNamespace("container/slot/hoe");

    public CombinationTemplateItem(Properties properties) {
        super(COMBINATION_UPGRADE_APPLIES_TO,
                COMBINATION_UPGRADE_INGREDIENTS,
                COMBINATION_UPGRADE_BASE_SLOT_DESCRIPTION,
                COMBINATION_UPGRADE_ADDITIONS_SLOT_DESCRIPTION,
                List.of(EMPTY_SLOT_AXE, EMPTY_SLOT_PICKAXE, EMPTY_SLOT_HOE, EMPTY_SLOT_SHOVEL),
                List.of(EMPTY_SLOT_PICKAXE, EMPTY_SLOT_AXE, EMPTY_SLOT_SHOVEL, EMPTY_SLOT_HOE),
                properties);
    }
}
