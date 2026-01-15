package com.memedream.classicmobs.block;

import com.memedream.classicmobs.entity.FallingGunpowderEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.ColorRGBA;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ColoredFallingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.ItemAbilities;
import org.jspecify.annotations.Nullable;

public class GunpowderBlock extends ColoredFallingBlock {
    public GunpowderBlock(Properties properties) {
        super(new ColorRGBA(-8356741), properties);
    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (isFree(level.getBlockState(pos.below())) && pos.getY() >= level.getMinY()) {
            FallingGunpowderEntity gunpowder = FallingGunpowderEntity.fall(level, pos, state);
            this.falling(gunpowder);
        }
    }

    @Override
    public boolean onCaughtFire(BlockState state, Level world, BlockPos pos, @Nullable Direction face, @Nullable LivingEntity igniter) {
        return explode(world, pos, igniter);
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, @Nullable Orientation orientation, boolean movedByPiston) {
        if (level.hasNeighborSignal(pos) && this.onCaughtFire(state, level, pos, null, null)) {
            level.removeBlock(pos, false);
        }
    }

    @Override
    public void onLand(Level level, BlockPos pos, BlockState state, BlockState replaceableState, FallingBlockEntity fallingBlock) {
        if (replaceableState.is(BlockTags.FIRE) || replaceableState.getFluidState().is(FluidTags.LAVA)) {
            this.onCaughtFire(state, level, pos, null, null);
            level.removeBlock(pos, false);
        }
    }

    public static boolean explode(Level level, BlockPos pos, @Nullable LivingEntity entity) {
        return explode(level, Vec3.atCenterOf(pos), entity);
    }

    public static boolean explode(Level level, Vec3 vec3, @Nullable LivingEntity entity) {
        if (!level.isClientSide()) {
            level.explode(
                    entity,
                    Explosion.getDefaultDamageSource(level, entity),
                    null,
                    vec3.x,
                    vec3.y,
                    vec3.z,
                    4.0F * 1.6F,
                    false,
                    Level.ExplosionInteraction.TNT
            );
            return true;
        }
        return false;
    }

    @Override
    protected InteractionResult useItemOn(
            ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult
    ) {
        if (!stack.canPerformAction(ItemAbilities.FIRESTARTER_LIGHT)) {
            return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
        } else {
            this.onCaughtFire(state, level, pos, hitResult.getDirection(), player);
            level.setBlock(pos, Blocks.AIR.defaultBlockState(), 11);
            Item item = stack.getItem();
            if (stack.isDamageableItem()) {
                stack.hurtAndBreak(1, player, hand);
            } else {
                stack.consume(1, player);
            }

            player.awardStat(Stats.ITEM_USED.get(item));
            return InteractionResult.SUCCESS;
        }
    }

    @Override
    protected void onProjectileHit(Level level, BlockState state, BlockHitResult hit, Projectile projectile) {
        if (level instanceof ServerLevel serverLevel) {
            BlockPos blockpos = hit.getBlockPos();
            Entity entity = projectile.getOwner();
            if (projectile.isOnFire() && projectile.mayInteract(serverLevel, blockpos)) {
                this.onCaughtFire(state, level, blockpos, null, entity instanceof LivingEntity ? (LivingEntity) entity : null);
                level.removeBlock(blockpos, false);
            }
        }
    }

    @Override
    public void wasExploded(ServerLevel level, BlockPos pos, Explosion explosion) {
        explode(level, pos, null);
    }

    @Override
    public boolean canDropFromExplosion(BlockState state, BlockGetter level, BlockPos pos, Explosion explosion) {
        return false;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 100;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 15;
    }
}
