package com.memedream.classicmobs.datagen.assets;

import com.memedream.classicmobs.init.ModEquipmentAssets;
import net.minecraft.client.data.models.EquipmentAssetProvider;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAsset;

import java.util.function.BiConsumer;

public class EquipmentAssetGen extends EquipmentAssetProvider {
    public EquipmentAssetGen(PackOutput output) {
        super(output);
    }

    @Override
    protected void registerModels(BiConsumer<ResourceKey<EquipmentAsset>, EquipmentClientInfo> output) {
        ModEquipmentAssets.bootstrap(output);
    }
}
