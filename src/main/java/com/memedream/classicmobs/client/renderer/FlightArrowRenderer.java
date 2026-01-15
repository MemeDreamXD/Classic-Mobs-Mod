package com.memedream.classicmobs.client.renderer;

import com.memedream.classicmobs.ClassicMobs;
import com.memedream.classicmobs.entity.FlightArrow;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.ArrowRenderState;
import net.minecraft.resources.Identifier;

public class FlightArrowRenderer extends ArrowRenderer<FlightArrow, ArrowRenderState> {

    public static final Identifier TEXTURE = ClassicMobs.prefix("textures/entity/flight_arrow.png");

    public FlightArrowRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ArrowRenderState createRenderState() {
        return new ArrowRenderState();
    }

    @Override
    protected Identifier getTextureLocation(ArrowRenderState state) {
        return TEXTURE;
    }
}
