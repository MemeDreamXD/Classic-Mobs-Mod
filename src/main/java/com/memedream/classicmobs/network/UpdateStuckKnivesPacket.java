package com.memedream.classicmobs.network;

import com.memedream.classicmobs.ClassicMobs;
import com.memedream.classicmobs.component.entity.StuckKnifeAttachment;
import com.memedream.classicmobs.init.ModDataAttachments;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.ArrayList;
import java.util.List;

public record UpdateStuckKnivesPacket(int entityID, List<ItemStack> knifeList) implements CustomPacketPayload {

    public static final Type<UpdateStuckKnivesPacket> TYPE = new Type<>(ClassicMobs.prefix("update_knives_attachment"));
    public static final StreamCodec<RegistryFriendlyByteBuf, UpdateStuckKnivesPacket> STREAM_CODEC = StreamCodec.composite(
        ByteBufCodecs.INT, UpdateStuckKnivesPacket::entityID,
        ItemStack.STREAM_CODEC.apply(ByteBufCodecs.collection(ArrayList::new)), UpdateStuckKnivesPacket::knifeList,
        UpdateStuckKnivesPacket::new);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(UpdateStuckKnivesPacket message, IPayloadContext context) {
        if (context.flow().isClientbound()) {
            context.enqueueWork(() -> {
                Entity entity = context.player().level().getEntity(message.entityID);
                if (entity instanceof LivingEntity living) {
                    living.setData(ModDataAttachments.STUCK_KNIVES, new StuckKnifeAttachment(message.knifeList));
                }
            });
        }
    }
}
