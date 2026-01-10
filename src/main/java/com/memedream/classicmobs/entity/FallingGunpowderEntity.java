package com.memedream.classicmobs.entity;

import com.memedream.classicmobs.block.GunpowderBlock;
import com.memedream.classicmobs.init.ModEntities;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class FallingGunpowderEntity extends FallingBlockEntity {
    public FallingGunpowderEntity(EntityType<? extends FallingBlockEntity> entityType, Level level) {
        super(entityType, level);
    }

    private FallingGunpowderEntity(Level level, double x, double y, double z, BlockState state) {
        this(ModEntities.FALLING_GUNPOWDER.get(), level);
        this.blockState = state;
        this.blocksBuilding = true;
        this.setPos(x, y, z);
        this.setDeltaMovement(Vec3.ZERO);
        this.xo = x;
        this.yo = y;
        this.zo = z;
        this.setStartPos(this.blockPosition());
    }

    public static FallingGunpowderEntity fall(Level level, BlockPos pos, BlockState blockState) {
        FallingGunpowderEntity gunpowder = new FallingGunpowderEntity(level, pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, blockState);
        level.setBlock(pos, blockState.getFluidState().createLegacyBlock(), Block.UPDATE_ALL);
        level.addFreshEntity(gunpowder);
        return gunpowder;
    }

    @Override
    public void tick() {
        if (this.isOnFire() || this.isInLava()) {
            GunpowderBlock.explode(this.level(), this.position(), null);
            this.discard();
            return;
        }
        super.tick();
    }

    @Override
    public void lavaHurt() {
        GunpowderBlock.explode(this.level(), this.position(), null);
        this.discard();
    }

    @Override
    public boolean displayFireAnimation() {
        return this.isOnFire() && !this.isSpectator();
    }
}
