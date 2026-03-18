package com.memedream.classicmobs.client.gui.pip;

import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.render.pip.PictureInPictureRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import org.joml.Matrix4f;

//TODO
//GRAHHHHH I HATE THESE FUCKASS THINGS WHY DO THEY NEVER WORK
//I miss being able to just render things in GUIs without this special bullshit
public class FluidRenderer extends PictureInPictureRenderer<FluidRenderState> {

    public FluidRenderer(MultiBufferSource.BufferSource bufferSource) {
        super(bufferSource);
    }

    @Override
    public Class<FluidRenderState> getRenderStateClass() {
        return FluidRenderState.class;
    }

    @Override
    protected void renderToTexture(FluidRenderState state, PoseStack stack) {
        TextureAtlasSprite sprite = Minecraft.getInstance().getAtlasManager().get(Sheets.BLOCKS_MAPPER.apply(IClientFluidTypeExtensions.of(state.stack().getFluid()).getStillTexture(state.stack())));
        int color = IClientFluidTypeExtensions.of(state.stack().getFluid()).getTintColor(state.stack());
        int desiredWidth = state.x1() - state.x0();
        int desiredHeight = state.y1() - state.y0();
        int xTileCount = desiredWidth / 16;
        int xRemainder = desiredWidth - (xTileCount * 16);
        int yTileCount = desiredHeight / 16;
        int yRemainder = desiredHeight - (yTileCount * 16);
        float uMin = sprite.getU0();
        float uMax = sprite.getU1();
        float vMin = sprite.getV0();
        float vMax = sprite.getV1();
        float uDif = uMax - uMin;
        float vDif = vMax - vMin;
        VertexConsumer consumer = this.bufferSource.getBuffer(RenderTypes.fireScreenEffect(sprite.atlasLocation()));
        Matrix4f matrix4f = stack.last().pose();
        for (int xTile = 0; xTile <= xTileCount; xTile++) {
            int width = (xTile == xTileCount) ? xRemainder : 16;
            if (width == 0) {
                break;
            }
            int x = state.x0() + (xTile * 16);
            int maskRight = 16 - width;
            int shiftedX = x + 16 - maskRight;
            float uLocalDif = uDif * maskRight / 16;

            for (int yTile = 0; yTile <= yTileCount; yTile++) {
                int height = (yTile == yTileCount) ? yRemainder : 16;
                if (height == 0) {
                    break;
                }
                int y = state.y0() - ((yTile + 1) * 16);
                int maskTop = 16 - height;
                float vLocalDif = vDif * maskTop / 16;

                consumer.addVertex(matrix4f, x, y + 16, 0).setUv(uMin + uLocalDif, vMax).setColor(color);
                consumer.addVertex(matrix4f, shiftedX, y + 16, 0).setUv(uMax, vMax).setColor(color);
                consumer.addVertex(matrix4f, shiftedX, y + maskTop, 0).setUv(uMax, vMin + vLocalDif).setColor(color);
                consumer.addVertex(matrix4f, x, y + maskTop, 0).setUv(uMin + uLocalDif, vMin + vLocalDif).setColor(color);
            }
        }
        this.bufferSource.endBatch();
    }

    @Override
    protected String getTextureLabel() {
        return "Fluid Tank";
    }
}
