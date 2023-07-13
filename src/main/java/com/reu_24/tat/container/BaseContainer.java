package com.reu_24.tat.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraftforge.fml.RegistryObject;

import java.util.Objects;

public abstract class BaseContainer extends Container {

    TileEntity tile;
    protected IWorldPosCallable canInteractWithCallable;

    // Server Constructor
    public <T extends Container> BaseContainer(final int windowId, final PlayerInventory playerInventory, final TileEntity tile, RegistryObject<ContainerType<T>> containerType) {
        super(containerType.get(), windowId);

        canInteractWithCallable = IWorldPosCallable.of(tile.getWorld(), tile.getPos());
        this.tile = tile;
    }

    // Client Constructor
    public <T extends Container> BaseContainer(final int windowId, final PlayerInventory playerInventory, final PacketBuffer data, RegistryObject<ContainerType<T>> containerType) {
        this(windowId, playerInventory, getTileEntity(playerInventory, data), containerType);
    }

    protected void addPlayerInvSlots(PlayerInventory playerInventory) {
        final int slotSizePlus2 = 18;
        final int startX = 8;

        // Main Player Inventory
        final int startY = 84;

        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 9; column++) {
                this.addSlot(new Slot(playerInventory, 9 + (row * 9) + column, startX + (column * slotSizePlus2),
                        startY + (row * slotSizePlus2)));
            }
        }

        // Hotbar
        int hotbarY = 142;
        for (int column = 0; column < 9; column++) {
            this.addSlot(new Slot(playerInventory, column, startX + (column * slotSizePlus2), hotbarY));
        }
    }

    protected static <T extends TileEntity> T getTileEntity(final PlayerInventory playerInv, final PacketBuffer data) {
        Objects.requireNonNull(playerInv, "playerInv cannot be null");
        Objects.requireNonNull(data, "data cannot be null");
        final TileEntity tileAtPos = playerInv.player.world.getTileEntity(data.readBlockPos());
        if (BaseContainer.<T>isInstanceOf(tileAtPos)) {
            return (T) tileAtPos;
        }
        throw new IllegalStateException("TileEntity is not correct " + tileAtPos);
    }

    protected static <T> boolean isInstanceOf(TileEntity tile) {
        try {
            T t = (T)tile;
            return true;
        } catch (ClassCastException e) {
            return false;
        }
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return isWithinUsableDistance(canInteractWithCallable, playerIn, tile.getBlockState().getBlock());
    }

    @Override
    public ItemStack transferStackInSlot(final PlayerEntity player, final int index) {
        ItemStack returnStack = ItemStack.EMPTY;
        final Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            final ItemStack slotStack = slot.getStack();
            returnStack = slotStack.copy();

            final int containerSlots = this.inventorySlots.size() - player.inventory.mainInventory.size();

            if (index >= player.inventory.mainInventory.size()) {
                if (!mergeItemStack(slotStack, 0, this.inventorySlots.size() - containerSlots, true)) {
                    return ItemStack.EMPTY;
                }
            } else {
                if (!mergeItemStack(slotStack, inventorySlots.size() - containerSlots, inventorySlots.size(), false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (slotStack.getCount() == 0) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
            if (slotStack.getCount() == returnStack.getCount()) {
                return ItemStack.EMPTY;
            }
            slot.onTake(player, slotStack);
        }
        return returnStack;
    }
}
