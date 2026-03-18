package com.memedream.classicmobs.inventory;

import com.memedream.classicmobs.ClassicMobs;
import com.memedream.classicmobs.init.ModItems;
import com.memedream.classicmobs.init.ModMenuTypes;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;

public class KettleMenu extends AbstractContainerMenu {

    private final Container container;
    private final ContainerData data;

    public KettleMenu(int containerId, Inventory inventory) {
        this(containerId, inventory, new SimpleContainer(5), new SimpleContainerData(3));
    }

    public KettleMenu(int containerId, Inventory inventory, Container kettle, ContainerData kettleData) {
        super(ModMenuTypes.KETTLE.get(), containerId);
        checkContainerSize(kettle, 5);
        checkContainerDataCount(kettleData, 3);
        this.container = kettle;
        this.data = kettleData;

        this.addSlot(new Slot(kettle, 0, 55, 46) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.is(ModItems.PRIMROSE);
            }
        }.setBackground(ClassicMobs.prefix("container/slot/primrose")));

        for (int i = 0; i < 4; i++) {
            Direction dir = Direction.values()[i + 2];

            int xOffs = 24 * dir.getStepX();
            int yOffs = 24 * dir.getStepZ();

            this.addSlot(new Slot(kettle, i + 1, 55 + xOffs, 46 + yOffs));
        }

        this.addStandardInventorySlots(inventory, 7, 107);
        this.addDataSlots(kettleData);
    }

    public float getBrewProgress() {
        int current = this.data.get(0);
        int total = this.data.get(1);
        return total != 0 && current != 0 ? Mth.clamp((float) current / total, 0.0F, 1.0F) : 0.0F;
    }

    public boolean isHeated() {
        return this.data.get(2) == 1;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int slotIndex) {
        ItemStack clicked = ItemStack.EMPTY;
        Slot slot = this.slots.get(slotIndex);
        if (slot != null && slot.hasItem()) {
            ItemStack stack = slot.getItem();
            clicked = stack.copy();
            if (slotIndex > 4) {
                if (!this.moveItemStackTo(stack, 0, 5, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(stack, 5, 41, false)) {
                return ItemStack.EMPTY;
            }

            if (stack.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (stack.getCount() == clicked.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, clicked);
        }

        return clicked;
    }

    @Override
    public boolean stillValid(Player player) {
        return this.container.stillValid(player);
    }
}
