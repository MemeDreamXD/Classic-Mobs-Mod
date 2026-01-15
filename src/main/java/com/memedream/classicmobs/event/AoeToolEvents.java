package com.memedream.classicmobs.event;

import com.memedream.classicmobs.item.AOEItem;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

import java.util.List;
import java.util.Set;

// First 2 events are modified versions of events from https://github.com/CoFH/CoFHCore/blob/1.19.x/src/main/java/cofh/core/event/AreaEffectEvents.java
public class AoeToolEvents {
    private static final Set<BlockPos> HARVESTED_BLOCKS = new ObjectOpenHashSet<>();

    public static void harvestWithMultitools(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        ItemStack mainHandItem = player.getMainHandItem();

        if (mainHandItem.getItem() instanceof AOEItem && player instanceof ServerPlayer serverPlayer) {
            BlockPos initialBlockPos = event.getPos();
            if (HARVESTED_BLOCKS.contains(initialBlockPos)) {
                return;
            }

            for (BlockPos pos : AOEItem.getBlocksToBeDestroyed(1, initialBlockPos, serverPlayer)) {
                if (!AOEItem.isValidBlockToBreak(player.level(), initialBlockPos, pos, mainHandItem)) {
                    continue;
                }

                HARVESTED_BLOCKS.add(pos);
                player.level().levelEvent(2001, pos, Block.getId(event.getLevel().getBlockState(pos)));
                serverPlayer.gameMode.destroyBlock(pos);
                HARVESTED_BLOCKS.remove(pos);
            }
        }
    }

    public static void modifyMultitoolMiningSpeed(PlayerEvent.BreakSpeed event) {
        if (event.isCanceled()) {
            return;
        }

        Player player = event.getEntity();
        ItemStack stack = player.getMainHandItem();
        if (stack.getItem() instanceof AOEItem) {
            event.getPosition().ifPresent(pos -> {
                List<BlockPos> areaBlocks = AOEItem.getBlocksToBeDestroyed(1, pos, player);

                float curHardness = event.getState().getDestroySpeed(player.level(), pos);
                if (curHardness <= 0 || areaBlocks.size() <= 1) {
                    return;
                }
                float areaMod = Mth.clamp(1.0F - 0.01F * areaBlocks.size(), 0.1F, 1.0F);
                event.setNewSpeed(event.getNewSpeed() * areaMod);

                float maxHardness = getMaxHardness(player.level(), areaBlocks, curHardness);
                if (maxHardness > curHardness) {
                    event.setNewSpeed(event.getNewSpeed() * curHardness / maxHardness);
                }
            });
        }
    }

    public static void clearHarvestBlocksIfNeeded(ServerTickEvent.Post event) {
        HARVESTED_BLOCKS.clear();
    }

    private static float getMaxHardness(BlockGetter world, List<BlockPos> areaBlocks, float curHardness) {
        float maxHardness = curHardness;
        float testHardness;

        for (BlockPos pos : areaBlocks) {
            testHardness = world.getBlockState(pos).getDestroySpeed(world, pos);
            if (testHardness > maxHardness) {
                maxHardness = testHardness;
            }
        }
        return maxHardness;
    }

    private static long startBreak;

    //TODO not this
    public static void visuallyHarvestWithMultitools(PlayerInteractEvent.LeftClickBlock event) {
        Player player = event.getEntity();
        ItemStack mainHandItem = event.getItemStack();
        if (mainHandItem.getItem() instanceof AOEItem) {
            if (event.getAction() == PlayerInteractEvent.LeftClickBlock.Action.START) {
                startBreak = event.getLevel().getGameTime();
            } else if (event.getAction() == PlayerInteractEvent.LeftClickBlock.Action.CLIENT_HOLD) {
                long breakTime = event.getLevel().getGameTime() - startBreak;
                float progress = event.getLevel().getBlockState(event.getPos()).getDestroyProgress(player, event.getLevel(), event.getPos()) * (breakTime + 1) * 10;
                int blockCount = 0;
                for (BlockPos pos : AOEItem.getBlocksToBeDestroyed(1, event.getPos(), player)) {
                    if (AOEItem.isValidBlockToBreak(event.getLevel(), event.getPos(), pos, mainHandItem)) {
                        player.level().destroyBlockProgress(10000 + blockCount, pos, (int) progress);
                        blockCount++;
                    }
                }
            } else {
                int blockCount = 0;
                for (BlockPos pos : AOEItem.getBlocksToBeDestroyed(1, event.getPos(), event.getEntity())) {
                    event.getLevel().destroyBlockProgress(10000 + blockCount, pos, -1);
                    blockCount++;
                }
                startBreak = -1;
            }
        }
    }
}