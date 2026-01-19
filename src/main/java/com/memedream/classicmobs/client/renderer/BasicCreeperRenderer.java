package com.memedream.classicmobs.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.monster.creeper.CreeperModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.CreeperRenderState;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.monster.Creeper;

public abstract class BasicCreeperRenderer<T extends Creeper, S extends CreeperRenderState> extends MobRenderer<T, S, CreeperModel> {

    public BasicCreeperRenderer(EntityRendererProvider.Context context) {
        super(context, new CreeperModel(context.bakeLayer(ModelLayers.CREEPER)), 0.4F);
        this.addLayer(new PowerLayer<>(this, context.getModelSet()));
    }

    @Override
    protected void scale(S state, PoseStack stack) {
        float g = state.swelling;
        float wobble = 1.0F + Mth.sin(g * 100.0F) * g * (0.01F * this.getSwellingScale(state));
        g = Mth.clamp(g, 0.0F, 1.0F);
        g *= g;
        g *= g;
        float s = (1.0F + g * 0.4F) * wobble;
        float hs = (1.0F + g * 0.1F) / wobble;
        stack.scale(s, hs, s);
    }

    protected float getSwellingScale(S state) {
        return 1.0F;
    }

    @Override
    protected float getWhiteOverlayProgress(S state) {
        float step = state.swelling;
        return (int)(step * (10.0F * this.getSwellingScale(state))) % 2 == 0 ? 0.0F : Mth.clamp(step, 0.5F, 1.0F);
    }

    @Override
    public void extractRenderState(T entity, S state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.swelling = entity.getSwelling(partialTicks);
        state.isPowered = entity.isPowered();
    }

    private static class PowerLayer<S extends CreeperRenderState> extends RenderLayer<S, CreeperModel> {
        private static final Identifier POWER_LOCATION = Identifier.withDefaultNamespace("textures/entity/creeper/creeper_armor.png");
        private final CreeperModel model;

        public PowerLayer(RenderLayerParent<S, CreeperModel> renderer, EntityModelSet modelSet) {
            super(renderer);
            this.model = new CreeperModel(modelSet.bakeLayer(ModelLayers.CREEPER_ARMOR));
        }

        @Override
        public void submit(PoseStack stack, SubmitNodeCollector collector, int light, S state, float yRot, float xRot) {
            if (state.isPowered) {
                float t = state.ageInTicks;
                collector.order(1).submitModel(this.model, state, stack, RenderTypes.energySwirl(POWER_LOCATION, t * 0.01F % 1.0F, t * 0.01F % 1.0F), light, OverlayTexture.NO_OVERLAY, -8355712, null, state.outlineColor, null);
            }
        }
    }
}
