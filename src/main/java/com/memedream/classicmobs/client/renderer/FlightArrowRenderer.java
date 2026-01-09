package com.memedream.classicmobs.client.renderer;

import com.memedream.classicmobs.ClassicMobs;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import com.memedream.classicmobs.entity.FlightArrow;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class FlightArrowRenderer extends ArrowRenderer<FlightArrow> {
    public static final ResourceLocation FLIGHT_ARROW_LOCATION = ResourceLocation.fromNamespaceAndPath(ClassicMobs.MOD_ID, "textures/entity/flight_arrow.png");

    public FlightArrowRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    public @NotNull ResourceLocation getTextureLocation(@NotNull FlightArrow entity) {
        return FLIGHT_ARROW_LOCATION;
    }
}
