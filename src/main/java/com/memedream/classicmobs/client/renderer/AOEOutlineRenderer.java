package com.memedream.classicmobs.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.ShapeRenderer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.BlockOutlineRenderState;
import net.minecraft.client.renderer.state.level.LevelRenderState;
import net.minecraft.core.BlockPos;
import net.minecraft.util.ARGB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.client.CustomBlockOutlineRenderer;

import java.util.List;

public class AOEOutlineRenderer implements CustomBlockOutlineRenderer {

    private final List<BlockOutlineRenderState> neighboringOutlines;

    public AOEOutlineRenderer(List<BlockOutlineRenderState> neighboringOutlines) {
        this.neighboringOutlines = neighboringOutlines;
    }

    @Override
    public boolean render(BlockOutlineRenderState renderState, MultiBufferSource.BufferSource buffer, PoseStack poseStack, boolean translucentPass, LevelRenderState levelRenderState) {
        for (BlockOutlineRenderState state : this.neighboringOutlines) {
            if (state.isTranslucent() == translucentPass) {
                BlockPos pos = state.pos();
                Vec3 cameraPos = levelRenderState.cameraRenderState.pos;
                if (state.highContrast()) {
                    VertexConsumer consumer = buffer.getBuffer(RenderTypes.secondaryBlockOutline());
                    ShapeRenderer.renderShape(poseStack, consumer, state.shape(), pos.getX() - cameraPos.x(), pos.getY() - cameraPos.y(), pos.getZ() - cameraPos.z(), -16777216, 7.0F);
                }

                VertexConsumer consumer = buffer.getBuffer(RenderTypes.lines());
                int outlineColor = state.highContrast() ? -11010079 : ARGB.black(102);
                ShapeRenderer.renderShape(poseStack, consumer, state.shape(), pos.getX() - cameraPos.x(), pos.getY() - cameraPos.y(), pos.getZ() - cameraPos.z(), outlineColor, Minecraft.getInstance().getWindow().getAppropriateLineWidth());
            }
        }

        return false;
    }
}
