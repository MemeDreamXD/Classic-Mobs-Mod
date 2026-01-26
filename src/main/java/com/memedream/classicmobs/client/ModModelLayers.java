package com.memedream.classicmobs.client;

import com.memedream.classicmobs.ClassicMobs;
import net.minecraft.client.model.geom.ModelLayerLocation;

public class ModModelLayers {

    public static final ModelLayerLocation DODO = register("dodo");
    public static final ModelLayerLocation DODO_BABY = register("dodo_baby");
    public static final ModelLayerLocation ANTLION = register("antlion");
    public static final ModelLayerLocation MYRMEX = register("myrmex");
    public static final ModelLayerLocation HAG = register("hag");
    public static final ModelLayerLocation HARPY = register("harpy");
    public static final ModelLayerLocation FESTIVE_TNT = register("festive_tnt");
    public static final ModelLayerLocation MIMIC = register("mimic");
    public static final ModelLayerLocation FLYING_BOLA = register("flying_bola");
    public static final ModelLayerLocation BOUND_BOLA = register("bound_bola");

    private static ModelLayerLocation register(String name) {
        return register(name, "main");
    }

    private static ModelLayerLocation register(String name, String type) {
        return new ModelLayerLocation(ClassicMobs.prefix(name), type);
    }
}
