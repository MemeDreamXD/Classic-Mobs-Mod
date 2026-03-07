package com.memedream.classicmobs.client.shader;

import com.memedream.classicmobs.ClassicMobs;
import com.mojang.blaze3d.pipeline.DepthStencilState;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.platform.CompareOp;
import net.minecraft.client.renderer.RenderPipelines;

public class ModRenderPipelines extends RenderPipelines {

    public static final RenderPipeline FAE_OUTLINE = RenderPipeline.builder(LINES_SNIPPET)
            .withDepthStencilState(new DepthStencilState(CompareOp.ALWAYS_PASS, false))
            .withLocation(ClassicMobs.prefix("pipeline/fae_outline"))
            .build();
}
