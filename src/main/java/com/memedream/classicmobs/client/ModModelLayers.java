package com.memedream.classicmobs.client;

import com.memedream.classicmobs.ClassicMobs;
import net.minecraft.client.model.geom.ModelLayerLocation;

public class ModModelLayers {

    public static final ModelLayerLocation DODO = register("dodo");

    private static ModelLayerLocation register(String name, String type) {
        return new ModelLayerLocation(ClassicMobs.prefix(name), type);
    }

    private static ModelLayerLocation register(String name) {
        return new ModelLayerLocation(ClassicMobs.prefix(name), "main");
    }

}
