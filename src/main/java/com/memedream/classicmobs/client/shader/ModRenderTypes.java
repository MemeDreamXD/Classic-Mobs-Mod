package com.memedream.classicmobs.client.shader;

import net.minecraft.client.renderer.rendertype.*;

public class ModRenderTypes extends RenderTypes {

    private static final RenderType FAE_OUTLINE = RenderType.create("classic_mobs:fae_outline",
        RenderSetup.builder(ModRenderPipelines.FAE_OUTLINE)
            .setLayeringTransform(LayeringTransform.NO_LAYERING)
            .setOutputTarget(OutputTarget.ITEM_ENTITY_TARGET)
            .createRenderSetup()
    );

    public static RenderType faeOutline() {
        return FAE_OUTLINE;
    }
}
