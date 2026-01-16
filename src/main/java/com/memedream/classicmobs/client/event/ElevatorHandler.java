package com.memedream.classicmobs.client.event;

import com.memedream.classicmobs.init.ModBlocks;
import com.memedream.classicmobs.network.ElevatorTeleportPacket;
import net.minecraft.client.player.ClientInput;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Input;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.client.event.MovementInputUpdateEvent;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;
import org.jspecify.annotations.Nullable;

public class ElevatorHandler {

    private static boolean lastSneaking;
    private static boolean lastJumping;

    public static void handleElevatorTeleport(MovementInputUpdateEvent event) {
        Player player = event.getEntity();
        ClientInput input = event.getInput();
        if (player.isSpectator() || !player.isAlive() || player.getAbilities().flying)
            return;

        boolean sneaking = input.keyPresses.shift();
        if (lastSneaking != sneaking) {
            lastSneaking = sneaking;
            if (sneaking) {
                tryTeleport(player, Direction.DOWN);
            }
        }

        boolean jumping = input.keyPresses.jump();
        if (lastJumping != jumping) {
            lastJumping = jumping;
            if (jumping) {
                tryTeleport(player, Direction.UP);
            }
        }

        //TODO while this does suppress the jump when teleporting, it also makes it so you cant jump at all when on the block
        if (player.level().getBlockState(player.getOnPos()).is(ModBlocks.PHANTOM_MEMBRANE_BLOCK)) {
            input.keyPresses = new Input(false, false, false, false, false, input.keyPresses.shift(), input.keyPresses.sprint());
        }
    }

    private static void tryTeleport(Player player, Direction facing) {
        Level level = player.level();

        BlockPos fromPos = getOriginElevator(player);
        if (fromPos == null) {
            return;
        }

        BlockPos.MutableBlockPos toPos = fromPos.mutable();

        var fromElevator = ElevatorTeleportPacket.ensureElevatorBlock(level.getBlockState(fromPos));
        if (fromElevator == null) {
            return;
        }

        while (true) {
            toPos.setY(toPos.getY() + facing.getStepY());
            if (level.isOutsideBuildHeight(toPos) ||
                Math.abs(toPos.getY() - fromPos.getY()) > level.getHeight()) {
                break;
            }

            var toElevator = ElevatorTeleportPacket.ensureElevatorBlock(level.getBlockState(toPos));
            if (toElevator != null && ElevatorTeleportPacket.isValidPos(level, toPos)) {
                ClientPacketDistributor.sendToServer(new ElevatorTeleportPacket(fromPos, toPos));
                break;
            }
        }
    }

    @Nullable
    private static BlockPos getOriginElevator(Player player) {
        BlockPos pos = player.blockPosition();

        for (int i = 0; i < 6; i++) {
            if (ElevatorTeleportPacket.ensureElevatorBlock(player.level().getBlockState(pos)) != null) {
                if (!ElevatorTeleportPacket.isValidPos(player.level(), pos)) {
                    return null;
                }
                return pos;
            }
            pos = pos.below();
        }

        // Elevator doesn't exist or it's invalid
        return null;
    }
}
