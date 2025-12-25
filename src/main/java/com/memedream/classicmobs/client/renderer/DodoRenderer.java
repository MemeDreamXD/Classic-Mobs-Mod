package com.memedream.classicmobs.client.renderer;

import com.memedream.classicmobs.ClassicMobs;
import com.memedream.classicmobs.client.ModModelLayers;
import com.memedream.classicmobs.client.model.DodoModel;
import com.memedream.classicmobs.entity.DodoEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class DodoRenderer extends MobRenderer<DodoEntity, DodoModel> {

    private static final ResourceLocation TEXTURE = ClassicMobs.prefix("textures/entity/dodo.png");

    public DodoRenderer(EntityRendererProvider.Context context) {
        super(context, new DodoModel(context.bakeLayer(ModModelLayers.DODO)), 0.4F);
    }

    @Override
    protected float getBob(DodoEntity entity, float partialTick) {
        float f = Mth.lerp(partialTick, entity.oFlap, entity.flap);
        float f1 = Mth.lerp(partialTick, entity.oFlapSpeed, entity.flapSpeed);
        return (Mth.sin(f) + 1.0F) * f1;
    }

    @Override
    public ResourceLocation getTextureLocation(DodoEntity entity) {
        return TEXTURE;
    }
}
