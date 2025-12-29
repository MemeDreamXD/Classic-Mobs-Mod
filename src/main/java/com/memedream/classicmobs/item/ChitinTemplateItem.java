package com.memedream.classicmobs.item;

import com.memedream.classicmobs.ClassicMobs;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.SmithingTemplateItem;

import java.util.List;

public class ChitinTemplateItem extends SmithingTemplateItem {

    private static final Component CHITIN_UPGRADE = Component.translatable(Util.makeDescriptionId("upgrade", ClassicMobs.prefix("chitin_upgrade"))).withStyle(ChatFormatting.GRAY);
    private static final Component CHITIN_UPGRADE_APPLIES_TO = Component.translatable(Util.makeDescriptionId("item", ClassicMobs.prefix("smithing_template.chitin_upgrade.applies_to"))).withStyle(ChatFormatting.BLUE);
    private static final Component CHITIN_UPGRADE_INGREDIENTS = Component.translatable(Util.makeDescriptionId("item", ClassicMobs.prefix("smithing_template.chitin_upgrade.ingredients"))).withStyle(ChatFormatting.BLUE);
    private static final Component CHITIN_UPGRADE_BASE_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", ClassicMobs.prefix("smithing_template.chitin_upgrade.base_slot_description")));
    private static final Component CHITIN_UPGRADE_ADDITIONS_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", ClassicMobs.prefix("smithing_template.chitin_upgrade.additions_slot_description")));
    private static final ResourceLocation EMPTY_SLOT_HELMET = ResourceLocation.withDefaultNamespace("item/empty_armor_slot_helmet");
    private static final ResourceLocation EMPTY_SLOT_CHESTPLATE = ResourceLocation.withDefaultNamespace("item/empty_armor_slot_chestplate");
    private static final ResourceLocation EMPTY_SLOT_LEGGINGS = ResourceLocation.withDefaultNamespace("item/empty_armor_slot_leggings");
    private static final ResourceLocation EMPTY_SLOT_BOOTS = ResourceLocation.withDefaultNamespace("item/empty_armor_slot_boots");
    private static final ResourceLocation EMPTY_SLOT_CHITIN = ClassicMobs.prefix("item/empty_chitin_slot");

    public ChitinTemplateItem() {
        super(CHITIN_UPGRADE_APPLIES_TO,
                CHITIN_UPGRADE_INGREDIENTS,
                CHITIN_UPGRADE,
                CHITIN_UPGRADE_BASE_SLOT_DESCRIPTION,
                CHITIN_UPGRADE_ADDITIONS_SLOT_DESCRIPTION,
                List.of(EMPTY_SLOT_HELMET, EMPTY_SLOT_CHESTPLATE, EMPTY_SLOT_LEGGINGS, EMPTY_SLOT_BOOTS),
                List.of(EMPTY_SLOT_CHITIN));
    }
}
