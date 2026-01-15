package com.memedream.classicmobs.item;

import com.memedream.classicmobs.ClassicMobs;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Util;
import net.minecraft.world.item.SmithingTemplateItem;

import java.util.List;

public class ChitinTemplateItem extends SmithingTemplateItem {

    public static final Component CHITIN_UPGRADE_APPLIES_TO = Component.translatable(Util.makeDescriptionId("item", ClassicMobs.prefix("smithing_template.chitin_upgrade.applies_to"))).withStyle(ChatFormatting.BLUE);
    public static final Component CHITIN_UPGRADE_INGREDIENTS = Component.translatable(Util.makeDescriptionId("item", ClassicMobs.prefix("smithing_template.chitin_upgrade.ingredients"))).withStyle(ChatFormatting.BLUE);
    public static final Component CHITIN_UPGRADE_BASE_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", ClassicMobs.prefix("smithing_template.chitin_upgrade.base_slot_description")));
    public static final Component CHITIN_UPGRADE_ADDITIONS_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", ClassicMobs.prefix("smithing_template.chitin_upgrade.additions_slot_description")));
    private static final Identifier EMPTY_SLOT_HELMET = Identifier.withDefaultNamespace("container/slot/helmet");
    private static final Identifier EMPTY_SLOT_CHESTPLATE = Identifier.withDefaultNamespace("container/slot/chestplate");
    private static final Identifier EMPTY_SLOT_LEGGINGS = Identifier.withDefaultNamespace("container/slot/leggings");
    private static final Identifier EMPTY_SLOT_BOOTS = Identifier.withDefaultNamespace("container/slot/eboots");
    private static final Identifier EMPTY_SLOT_CHITIN = ClassicMobs.prefix("container/slot/chitin");

    public ChitinTemplateItem(Properties properties) {
        super(CHITIN_UPGRADE_APPLIES_TO,
                CHITIN_UPGRADE_INGREDIENTS,
                CHITIN_UPGRADE_BASE_SLOT_DESCRIPTION,
                CHITIN_UPGRADE_ADDITIONS_SLOT_DESCRIPTION,
                List.of(EMPTY_SLOT_HELMET, EMPTY_SLOT_CHESTPLATE, EMPTY_SLOT_LEGGINGS, EMPTY_SLOT_BOOTS),
                List.of(EMPTY_SLOT_CHITIN),
                properties);
    }
}
