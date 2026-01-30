package com.memedream.classicmobs.client.event;

import com.memedream.classicmobs.ClassicMobs;
import com.memedream.classicmobs.client.shader.ModRenderTypes;
import com.memedream.classicmobs.init.ModEffects;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.ShapeRenderer;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.data.AtlasIds;
import net.minecraft.util.ARGB;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.shapes.Shapes;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.server.ServerLifecycleHooks;

import java.util.*;

public class FaeCurseHandler {

    private static final List<BlockInfo> ORE_POSITIONS = new ArrayList<>();

    public static void tickFaeEffect(PlayerTickEvent.Post event) {
        if (event.getEntity().level().isClientSide()) {
            if (event.getEntity().hasEffect(ModEffects.FAE_CURSE)) {
                Level level = event.getEntity().level();
                ORE_POSITIONS.clear();
                for (BlockPos pos : BlockPos.withinManhattan(event.getEntity().blockPosition(), 30, 40, 30)) {
                    if (level.isLoaded(pos) && !level.isOutsideBuildHeight(pos) && level.getBlockState(pos).is(Tags.Blocks.ORES)) {
                        int dist = pos.distManhattan(event.getEntity().blockPosition());
                        ORE_POSITIONS.add(new BlockInfo(pos.immutable(), Mth.clamp(1.0F - (dist / 25.0F), 0.0F, 1.0F)));
                    }
                }
            } else {
                ORE_POSITIONS.clear();
            }
        }
    }

    public static void renderOutlinedBlocks(RenderLevelStageEvent.AfterWeather event) {
        if (!ORE_POSITIONS.isEmpty() && Minecraft.getInstance().level != null) {
            Camera camera = Minecraft.getInstance().gameRenderer.getMainCamera();
            MultiBufferSource.BufferSource source = Minecraft.getInstance().renderBuffers().bufferSource();
            VertexConsumer consumer = source.getBuffer(ModRenderTypes.faeOutline());
            for (BlockInfo info : ORE_POSITIONS) {
                BlockPos pos = info.pos();
                BlockState state = Minecraft.getInstance().level.getBlockState(pos);
                ShapeRenderer.renderShape(event.getPoseStack(), consumer, Shapes.block(), pos.getX() - camera.position().x(), pos.getY() - camera.position().y(), pos.getZ() - camera.position().z(), ARGB.color(info.alpha(), getOreColor(pos, state)), 2.0F);
            }
            source.endBatch(ModRenderTypes.faeOutline());
        }
    }

    //bad code ahead!
    //here be dragons!

    private static final ItemStackRenderState scratchRenderState = new ItemStackRenderState();
    private static final Map<String, Integer> TEXTURES_TO_COLOR = new HashMap<>();

    private static int getOreColor(BlockPos pos, BlockState state) {
        String oreName = state.getBlock().getName().getString();
        if (TEXTURES_TO_COLOR.get(oreName) != null) {
            return TEXTURES_TO_COLOR.get(oreName);
        } else {
            int color = 0XFFFFFFFF;
            try {
                List<ItemStack> drops = state.getDrops(new LootParams.Builder(ServerLifecycleHooks.getCurrentServer().overworld())
                    .withParameter(LootContextParams.TOOL, new ItemStack(Items.NETHERITE_PICKAXE))
                    .withParameter(LootContextParams.ORIGIN, pos.getCenter()));
                if (!drops.isEmpty()) {
                    color = getAverageColor(getSprite(drops.getFirst()));
                }
            } catch (NullPointerException e) {
                ClassicMobs.LOGGER.warn("Could not fetch average ore color for resource {}, defaulting to white.", oreName);
            }
            TEXTURES_TO_COLOR.put(oreName, color);
            return color;
        }
    }

    private static int getAverageColor(TextureAtlasSprite image) {
        float red = 0;
        float green = 0;
        float blue = 0;
        float count = 0;
        int uMax = image.contents().width();
        int vMax = image.contents().height();
        for (float i = 0; i < uMax; i++) {
            for (float j = 0; j < vMax; j++) {
                int color = image.getPixelRGBA(0, (int) i, (int) j);
                int alpha = color >> 24 & 0xFF;
                if (alpha != 255) {
                    continue;
                }
                red += color >> 16 & 0xFF;
                green += color >> 8 & 0xFF;
                blue += color & 0xFF;
                count++;
            }
        }
        return 255 << 24 | (int) (red / count) << 16 | (int) (green / count) << 8 | (int) (blue / count);
    }

    private static TextureAtlasSprite getSprite(ItemStack itemStack) {
        Minecraft.getInstance().getItemModelResolver().updateForTopItem(scratchRenderState, itemStack, ItemDisplayContext.NONE, null, null, 0);
        TextureAtlasSprite icon = scratchRenderState.pickParticleIcon(RandomSource.create());
        return icon != null ? icon : Minecraft.getInstance().getAtlasManager().getAtlasOrThrow(AtlasIds.ITEMS).missingSprite();
    }

    private record BlockInfo(BlockPos pos, float alpha) {
    }
}
