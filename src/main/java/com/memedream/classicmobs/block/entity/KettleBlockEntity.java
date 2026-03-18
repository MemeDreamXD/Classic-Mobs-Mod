package com.memedream.classicmobs.block.entity;

import com.memedream.classicmobs.init.ModBlockEntities;
import com.memedream.classicmobs.init.ModTags;
import com.memedream.classicmobs.inventory.KettleMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public class KettleBlockEntity extends BaseContainerBlockEntity {

    private static final Component DEFAULT_NAME = Component.translatable("container.classicmobs.kettle");
    private NonNullList<ItemStack> items = NonNullList.withSize(5, ItemStack.EMPTY);
    private int brewTime;
    private int brewTimeTotal;
    private int smokeColor = -1;

    private boolean heated;

    protected final ContainerData data = new ContainerData() {
        @Override
        public int get(int dataId) {
            return switch (dataId) {
                case 0 -> KettleBlockEntity.this.brewTime;
                case 1 -> KettleBlockEntity.this.brewTimeTotal;
                case 2 -> KettleBlockEntity.this.heated ? 1 : 0;
                default -> 0;
            };
        }

        @Override
        public void set(int dataId, int value) {
            switch (dataId) {
                case 0 -> KettleBlockEntity.this.brewTime = value;
                case 1 -> KettleBlockEntity.this.brewTimeTotal = value;
                case 2 -> KettleBlockEntity.this.heated = value == 1;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    };

    public KettleBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.KETTLE.get(), pos, state);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        ContainerHelper.saveAllItems(output, this.items);
        output.putBoolean("heated", this.heated);
        output.putInt("brew_time", this.brewTime);
        output.putInt("brew_time_total", this.brewTimeTotal);
        output.putInt("smoke_color", this.smokeColor);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(input, this.items);
        this.heated = input.getBooleanOr("heated", false);
        this.brewTime = input.getIntOr("brew_time", 0);
        this.brewTimeTotal = input.getIntOr("brew_time_total", 0);
        this.smokeColor = input.getIntOr("smoke_color", -1);
    }

    @Override
    protected Component getDefaultName() {
        return DEFAULT_NAME;
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> items) {
        this.items = items;
    }

    @Override
    protected AbstractContainerMenu createMenu(int containerId, Inventory inventory) {
        return new KettleMenu(containerId, inventory, this, this.data);
    }

    @Override
    public int getContainerSize() {
        return this.items.size();
    }

    public int getSmokeColor() {
        return this.smokeColor;
    }

    public boolean isHeated() {
        return this.heated;
    }

    public void updateHeated(BlockState belowState) {
        this.heated = belowState.is(ModTags.Blocks.KETTLE_HEATER);
        this.setChanged();
        this.getLevel().sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 3);
    }

    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return this.saveCustomOnly(registries);
    }
}
