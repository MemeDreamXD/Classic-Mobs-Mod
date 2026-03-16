package com.memedream.classicmobs.client.renderer.layer;

import com.memedream.classicmobs.client.event.ModClientRegistrationEvents;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class StuckKnifeLayer<S extends LivingEntityRenderState, M extends EntityModel<S>> extends RenderLayer<S, M> {

    private final ItemStackRenderState knifeState = new ItemStackRenderState();
    private final ItemModelResolver itemModelResolver;
    private final List<ModelPart> possibleStuckParts;

    public StuckKnifeLayer(LivingEntityRenderer<?, S, M> renderer) {
        super(renderer);
        this.itemModelResolver = Minecraft.getInstance().getItemModelResolver();
        this.possibleStuckParts = renderer.getModel().allParts().stream().filter(modelPart -> !modelPart.isEmpty()).toList();
    }

    private void submitStuckItem(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, ItemStack knife, int lightCoords, float directionX, float directionY, float directionZ, int outlineColor) {
        float directionXZ = Mth.sqrt(directionX * directionX + directionZ * directionZ);
        float yRot = (float)(Math.atan2(directionX, directionZ) * 180.0F / Mth.PI);
        float xRot = (float)(Math.atan2(directionY, directionXZ) * 180.0F / Mth.PI);
        poseStack.mulPose(Axis.YP.rotationDegrees(yRot - 90));
        poseStack.mulPose(Axis.XP.rotationDegrees(xRot));
        this.itemModelResolver.updateForTopItem(this.knifeState, knife, ItemDisplayContext.GROUND, null, null, 0);
        this.knifeState.submit(poseStack, submitNodeCollector, lightCoords, OverlayTexture.NO_OVERLAY, outlineColor);
    }

    public void submit(PoseStack stack, SubmitNodeCollector collector, int lightCoords, S state, float yRot, float xRot) {
        ModClientRegistrationEvents.StuckKnifeInfo info = state.getRenderData(ModClientRegistrationEvents.STUCK_KNIVES);
        if (info != null) {
            RandomSource random = RandomSource.createThreadLocalInstance(info.entityID());

            for (ItemStack knife : info.stuckKnives()) {
                stack.pushPose();
                ModelPart modelPart = this.possibleStuckParts.get(random.nextInt(this.possibleStuckParts.size()));
                ModelPart.Cube cube = modelPart.getRandomCube(random);
                modelPart.translateAndRotate(stack);
                float midX = random.nextFloat();
                float midY = random.nextFloat();
                float midZ = random.nextFloat();
//                int plane = random.nextInt(3);
//                switch (plane) {
//                    case 0:
//                        midX = snapToFace(midX);
//                        break;
//                    case 1:
//                        midY = snapToFace(midY);
//                        break;
//                    default:
//                        midZ = snapToFace(midZ);
//                }

                stack.translate(Mth.lerp(midX, cube.minX, cube.maxX) / 16.0F, Mth.lerp(midY, cube.minY, cube.maxY) / 16.0F, Mth.lerp(midZ, cube.minZ, cube.maxZ) / 16.0F);
                this.submitStuckItem(stack, collector, knife, lightCoords, -(midX * 2.0F - 1.0F), -(midY * 2.0F - 1.0F), -(midZ * 2.0F - 1.0F), state.outlineColor);
                stack.popPose();
            }
        }
    }

    private static float snapToFace(float value) {
        return value > 0.5F ? 1.0F : 0.75F;
    }
}
