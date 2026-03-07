package com.memedream.classicmobs.block.entity;

import com.memedream.classicmobs.block.BlazeRodBlock;
import com.memedream.classicmobs.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class BlazeRodBlockEntity extends BlockEntity {

	public BlazeRodBlockEntity(BlockPos pos, BlockState state) {
		super(ModBlockEntities.BLAZE_ROD.get(), pos, state);
	}

	public static void tick(Level level, BlockPos pos, BlockState state, BlazeRodBlockEntity entity) {
		Direction facing = state.getValue(BlazeRodBlock.FACING);
		BlockPos furnacePos = pos.relative(facing);
		if (level.getBlockEntity(furnacePos) instanceof AbstractFurnaceBlockEntity furnace) {
			AbstractFurnaceBlockEntity.serverTick((ServerLevel) level, furnacePos, level.getBlockState(furnacePos), furnace);
		}
	}
}
