package com.memedream.classicmobs.item;

import com.memedream.classicmobs.init.ModTags;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.neoforge.common.ItemAbility;
import org.jspecify.annotations.Nullable;

import java.util.Optional;

public class PickaxeAxeItem extends Item {

    public PickaxeAxeItem(ToolMaterial material, float attackDamageBaseline, float attackSpeedBaseline, Properties properties) {
        super(properties.tool(material, ModTags.Blocks.MINEABLE_WITH_PICKAXE_AXE, attackDamageBaseline, attackSpeedBaseline, 5.0F));
    }

    @Override
    public boolean canPerformAction(ItemStack stack, ItemAbility itemAbility) {
        return ModItemAbilities.DEFAULT_PICKAXE_AXE_ACTIONS.contains(itemAbility);
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        if (state.is(ModTags.Blocks.MINEABLE_WITH_PICKAXE_AXE) && state.is(BlockTags.MINEABLE_WITH_PICKAXE) && state.is(BlockTags.MINEABLE_WITH_AXE)) {
            return super.getDestroySpeed(stack, state) * 2.0F;
        } else return super.getDestroySpeed(stack, state);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos blockpos = context.getClickedPos();
        Player player = context.getPlayer();
        if (AOEItem.playerHasBlockingItemUseIntent(context)) {
            return InteractionResult.PASS;
        } else {
            Optional<BlockState> optional = evaluateNewBlockState(level, blockpos, player, level.getBlockState(blockpos), context);
            if (optional.isEmpty()) {
                return InteractionResult.PASS;
            } else {
                ItemStack itemstack = context.getItemInHand();
                if (player instanceof ServerPlayer) {
                    CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer)player, blockpos, itemstack);
                }

                level.setBlock(blockpos, optional.get(), 11);
                level.gameEvent(GameEvent.BLOCK_CHANGE, blockpos, GameEvent.Context.of(player, optional.get()));
                if (player != null) {
                    itemstack.hurtAndBreak(1, player, context.getHand().asEquipmentSlot());
                }

                return InteractionResult.SUCCESS;
            }
        }
    }

    //[VanillaCopy] of AxeItem.evaluateNewBlockState
    public static Optional<BlockState> evaluateNewBlockState(Level level, BlockPos pos, @Nullable Player player, BlockState oldState, UseOnContext context) {
        Optional<BlockState> strippedBlock = Optional.ofNullable(oldState.getToolModifiedState(context, net.neoforged.neoforge.common.ItemAbilities.AXE_STRIP, false));
        if (strippedBlock.isPresent()) {
            level.playSound(player, pos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0F, 1.0F);
            return strippedBlock;
        } else {
            Optional<BlockState> scrapedBlock = Optional.ofNullable(oldState.getToolModifiedState(context, net.neoforged.neoforge.common.ItemAbilities.AXE_SCRAPE, false));
            if (scrapedBlock.isPresent()) {
                spawnSoundAndParticle(level, pos, player, oldState, SoundEvents.AXE_SCRAPE, 3005);
                return scrapedBlock;
            } else {
                Optional<BlockState> waxoffBlock = Optional.ofNullable(oldState.getToolModifiedState(context, net.neoforged.neoforge.common.ItemAbilities.AXE_WAX_OFF, false));
                if (waxoffBlock.isPresent()) {
                    spawnSoundAndParticle(level, pos, player, oldState, SoundEvents.AXE_WAX_OFF, 3004);
                    return waxoffBlock;
                } else {
                    return Optional.empty();
                }
            }
        }
    }

    //[VanillaCopy] of AxeItem.spawnSoundAndParticle
    private static void spawnSoundAndParticle(Level level, BlockPos pos, @Nullable Player player, BlockState oldState, SoundEvent soundEvent, int particle) {
        level.playSound(player, pos, soundEvent, SoundSource.BLOCKS, 1.0F, 1.0F);
        level.levelEvent(player, particle, pos, 0);
        if (oldState.getBlock() instanceof ChestBlock && oldState.getValue(ChestBlock.TYPE) != ChestType.SINGLE) {
            BlockPos neighborPos = ChestBlock.getConnectedBlockPos(pos, oldState);
            level.gameEvent(GameEvent.BLOCK_CHANGE, neighborPos, GameEvent.Context.of(player, level.getBlockState(neighborPos)));
            level.levelEvent(player, particle, neighborPos, 0);
        }
    }
}
