package com.reu_24.tat.tilentity;

import com.reu_24.tat.container.LaserReceiverContainer;
import com.reu_24.tat.init.ModContainerTypes;
import com.reu_24.tat.object.block.OrientalGuiBlock;
import com.reu_24.tat.util.BaseItemHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;

public abstract class LaserReceiverTileEntity extends ElectricityGuiTileEntity<BaseItemHandler> {

    public LaserReceiverTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);

        inventory = new BaseItemHandler(0);
    }

    @Override
    protected boolean canReceive() {
        return false;
    }

    @Override
    public Container createMenu(int windowId, PlayerInventory playerInventory, PlayerEntity player) {
        return new LaserReceiverContainer(windowId, playerInventory, this, ModContainerTypes.LASER_RECEIVER);
    }

    public void receive(Direction side, int amount) {
        if (side == getDirection()) {
            energy.receiveEnergyRaw(amount, false);
        }
    }

    public Direction getDirection() {
        return ((OrientalGuiBlock)getBlockState().getBlock()).getFacing(getBlockState());
    }
}
