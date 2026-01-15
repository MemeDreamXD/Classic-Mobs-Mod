package com.memedream.classicmobs.init;

import com.google.common.collect.Maps;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;

import java.util.Map;

public class ModArmorMaterials {

    public static final ArmorMaterial CHITIN = new ArmorMaterial(10, makeDefense(2, 3, 4, 2, 4),
            15, SoundEvents.ARMOR_EQUIP_LEATHER, 2.0F, 0.0F, ModTags.Items.REPAIRS_CHITIN_ARMOR, ModEquipmentAssets.CHITIN);

    private static Map<ArmorType, Integer> makeDefense(int boots, int legs, int chest, int helm, int body) {
        return Maps.newEnumMap(Map.of(ArmorType.BOOTS, boots, ArmorType.LEGGINGS, legs, ArmorType.CHESTPLATE, chest, ArmorType.HELMET, helm, ArmorType.BODY, body));
    }
}