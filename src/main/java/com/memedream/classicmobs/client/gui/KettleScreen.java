package com.memedream.classicmobs.client.gui;

import com.memedream.classicmobs.ClassicMobs;
import com.memedream.classicmobs.client.gui.pip.FluidRenderState;
import com.memedream.classicmobs.inventory.KettleMenu;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.fluids.FluidStack;

public class KettleScreen extends AbstractContainerScreen<KettleMenu> {

    private static final Identifier BACKGROUND = ClassicMobs.prefix("textures/gui/kettle.png");
    private static final Identifier BREW_PROGRESS_SPRITE = ClassicMobs.prefix("container/kettle/brew_progress");
    private static final Identifier LIT_SPRITE = ClassicMobs.prefix("container/kettle/lit");
    private static final Identifier TANK_SPRITE = ClassicMobs.prefix("container/kettle/tank_overlay");

    public KettleScreen(KettleMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title, 174, 188);
    }

    @Override
    protected void init() {
        super.init();
        this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float a, int xm, int ym) {
        int xo = this.leftPos;
        int yo = this.topPos;
        graphics.blit(RenderPipelines.GUI_TEXTURED, BACKGROUND, xo, yo, 0.0F, 0.0F, this.imageWidth, this.imageHeight, 256, 256);
        int burnProgressWidth = Mth.ceil(this.menu.getBrewProgress() * 24.0F);
        graphics.blitSprite(RenderPipelines.GUI_TEXTURED, BREW_PROGRESS_SPRITE, 24, 16, 0, 0, xo + 102, yo + 45, burnProgressWidth, 16);

        if (this.menu.isHeated()) {
            graphics.blitSprite(RenderPipelines.GUI_TEXTURED, LIT_SPRITE, 14, 14, 0, 0, xo + 133, yo + 77, 14, 14);
        }

        graphics.submitPictureInPictureRenderState(new FluidRenderState(new FluidStack(Fluids.WATER, 1), xo + 131, yo + 31, xo + 149, yo + 72, null));
        graphics.blitSprite(RenderPipelines.GUI_TEXTURED, TANK_SPRITE, 18, 41, 0, 0, xo + 131, yo + 31, 18, 41);
    }
}
