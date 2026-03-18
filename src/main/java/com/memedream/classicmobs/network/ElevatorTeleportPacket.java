package com.memedream.classicmobs.network;

import com.memedream.classicmobs.ClassicMobs;
import com.memedream.classicmobs.init.ModBlocks;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.jspecify.annotations.Nullable;

public record ElevatorTeleportPacket(BlockPos from, BlockPos to) implements CustomPacketPayload {
    public static final Type<ElevatorTeleportPacket> TYPE = new Type<>(ClassicMobs.prefix("elevator_teleport"));

    public static final StreamCodec<ByteBuf, ElevatorTeleportPacket> STREAM_CODEC = StreamCodec.composite(
        BlockPos.STREAM_CODEC,
        ElevatorTeleportPacket::from,
        BlockPos.STREAM_CODEC,
        ElevatorTeleportPacket::to,
        ElevatorTeleportPacket::new
    );

    @Override
    public Type<ElevatorTeleportPacket> type() {
        return TYPE;
    }

    public static void handle(ElevatorTeleportPacket packet, IPayloadContext context) {
        Player player = context.player();
        if (isBadPacket(packet, player)) return;

        Level level = player.level();
        BlockPos toPos = packet.to();

        //place on center of block and account for partial blocks on top of elevators, like slabs
        double blockShapeAbove = level.getBlockState(toPos.above()).getBlockSupportShape(level, toPos.above()).max(Direction.Axis.Y);
        double toX = toPos.getX() + 0.5D;
        double toY = Math.max(toPos.getY() + 1.0D, toPos.getY() + 1.0D + blockShapeAbove);
        double toZ = toPos.getZ() + 0.5D;

        context.player().teleportTo(toX, toY, toZ);
        player.setDeltaMovement(player.getDeltaMovement().multiply(1.0D, 0.0D, 1.0D));
        level.playSound(null, toPos, SoundEvents.PLAYER_TELEPORT, SoundSource.BLOCKS, 1.0F, 1.0F);
    }

    private static boolean isBadPacket(ElevatorTeleportPacket packet, Player player) {
        if (!player.isAlive())
            return true;

        Level level = player.level();
        BlockPos fromPos = packet.from();
        BlockPos toPos = packet.to();

        //ensure both spots are loaded
        if (!level.isLoaded(fromPos) || !level.isLoaded(toPos))
            return true;

        // This ensures the player is still standing on the "from" elevator
        if (player.blockPosition().distManhattan(fromPos) > 6)
            return true;

        //elevators need to be in the samex and z space
        if (fromPos.getX() != toPos.getX() || fromPos.getZ() != toPos.getZ())
            return true;

        //teleports shouldnt happen in the exact same block space however
        if (fromPos.getY() == toPos.getY())
            return true;

        Block fromElevator = ensureElevatorBlock(level.getBlockState(fromPos));
        Block toElevator = ensureElevatorBlock(level.getBlockState(toPos));

        if (fromElevator == null || toElevator == null)
            return true;

        return !isValidPos(level, toPos, player);
    }

    public static boolean isValidPos(Level getter, BlockPos pos, Player player) {
        AABB box = player.getDimensions(Pose.STANDING).makeBoundingBox(Vec3.ZERO);
        return getter.noCollision(box.move(pos.above()));
    }

    @Nullable
    public static Block ensureElevatorBlock(BlockState state) {
        if (state.is(ModBlocks.PHANTOM_MEMBRANE_BLOCK)) {
            return state.getBlock();
        }

        return null;
    }
}
