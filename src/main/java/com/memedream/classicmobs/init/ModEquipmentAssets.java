package com.memedream.classicmobs.init;

import com.memedream.classicmobs.ClassicMobs;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;

import java.util.function.BiConsumer;

public class ModEquipmentAssets {

    public static final ResourceKey<EquipmentAsset> CHITIN = ResourceKey.create(EquipmentAssets.ROOT_ID, ClassicMobs.prefix("chitin"));

    public static void bootstrap(BiConsumer<ResourceKey<EquipmentAsset>, EquipmentClientInfo> consumer) {
        consumer.accept(CHITIN, EquipmentClientInfo.builder()
                .addHumanoidLayers(ClassicMobs.prefix("chitin"), true)
                .addHumanoidLayers(ClassicMobs.prefix("chitin_overlay"), false)
                .build());
    }
}
