package com.memedream.classicmobs.client.state;

import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;

public class ThrownKnifeRenderState extends EntityRenderState {

    public ItemStackRenderState knifeState = new ItemStackRenderState();

    public float yRot;
    public float xRot;
}
