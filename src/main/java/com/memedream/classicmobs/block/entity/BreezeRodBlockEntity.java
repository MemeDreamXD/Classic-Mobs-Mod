package com.memedream.classicmobs.block.entity;

import com.memedream.classicmobs.block.BreezeRodBlock;
import com.memedream.classicmobs.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

public class BreezeRodBlockEntity extends BlockEntity {

	public BreezeRodBlockEntity(BlockPos pos, BlockState state) {
		super(ModBlockEntities.BREEZE_ROD.get(), pos, state);
	}

	public static void tick(Level level, BlockPos pos, BlockState state, BreezeRodBlockEntity entity) {
		var facing = state.getValue(BreezeRodBlock.FACING);
		var entities = level.getEntities(null, new AABB(pos).expandTowards(facing.getUnitVec3()));
		for (Entity pushed : entities) {
			if (pushed.isPushable()) {
				pushed.push(facing.getUnitVec3().multiply(0.25D, 0.25D, 0.25D));

				if (pushed instanceof ServerPlayer player) {
					player.connection.send(new ClientboundSetEntityMotionPacket(player));
				}
			}
		}
	}
}
