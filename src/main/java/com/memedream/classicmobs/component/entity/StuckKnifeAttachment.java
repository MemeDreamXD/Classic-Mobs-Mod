package com.memedream.classicmobs.component.entity;

import com.memedream.classicmobs.network.UpdateStuckKnivesPacket;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.ArrayList;
import java.util.List;

public class StuckKnifeAttachment {

    private List<ItemStack> stuckKnives;

    public static final MapCodec<StuckKnifeAttachment> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            ItemStack.CODEC.listOf().fieldOf("stuck_gladiuses").forGetter(o -> o.stuckKnives))
        .apply(instance, StuckKnifeAttachment::new));

    public StuckKnifeAttachment() {
        this(new ArrayList<>());
    }

    public StuckKnifeAttachment(List<ItemStack> knives) {
        this.stuckKnives = knives;
    }

    public List<ItemStack> getStuckKnives() {
        return this.stuckKnives;
    }

    public void addKnifeToEntity(ItemStack stack, LivingEntity entity) {
        List<ItemStack> newList = new ArrayList<>(this.stuckKnives);
        newList.add(stack);
        this.stuckKnives = newList;
        PacketDistributor.sendToPlayersTrackingEntity(entity, new UpdateStuckKnivesPacket(entity.getId(), this.stuckKnives));
    }
}
