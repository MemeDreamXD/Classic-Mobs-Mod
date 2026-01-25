package com.memedream.classicmobs.client.renderer;

import com.memedream.classicmobs.ClassicMobs;
import com.memedream.classicmobs.client.ModModelLayers;
import com.memedream.classicmobs.client.model.MimicModel;
import com.memedream.classicmobs.client.state.MimicRenderState;
import com.memedream.classicmobs.entity.MimicEntity;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import net.neoforged.fml.ModList;

public class MimicRenderer extends MobRenderer<MimicEntity, MimicRenderState, MimicModel> {

    private static final Identifier TEXTURE = ClassicMobs.prefix("textures/entity/mimic/mimic.png");
    private static final Identifier CHRISTMAS_TEXTURE = ClassicMobs.prefix("textures/entity/mimic/mimic_christmas.png");
    private static final Identifier LOOTR_TEXTURE = ClassicMobs.prefix("textures/entity/mimic/mimic_lootr.png");
    private static final Identifier OLD_LOOTR_TEXTURE = ClassicMobs.prefix("textures/entity/mimic/mimic_old_lootr.png");

    private final boolean christmas;

    public MimicRenderer(EntityRendererProvider.Context context) {
        super(context, new MimicModel(context.bakeLayer(ModModelLayers.MIMIC)), 0.0F);
        this.christmas = ChestRenderer.xmasTextures();
    }

    @Override
    public MimicRenderState createRenderState() {
        return new MimicRenderState();
    }

    @Override
    public void extractRenderState(MimicEntity entity, MimicRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.isInvisible = false;
        state.ticksInAir = entity.ticksInAir;
    }

    @Override
    protected float getFlipDegrees() {
        return 0.0F;
    }

    @Override
    public Identifier getTextureLocation(MimicRenderState state) {
        //TODO reimplement once lootr is ported
        if (ModList.get().isLoaded("lootr")) {
//            if (!ConfigManager.isVanillaTextures()) {
//                if (ConfigManager.isNewTextures()) {
//                    return LOOTR_TEXTURE;
//                } else {
//                    return OLD_LOOTR_TEXTURE;
//                }
//            }
        }
        return this.christmas ? CHRISTMAS_TEXTURE : TEXTURE;
    }
}
