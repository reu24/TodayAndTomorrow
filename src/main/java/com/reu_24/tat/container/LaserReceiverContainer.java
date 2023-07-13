package com.reu_24.tat.container;

import com.reu_24.tat.tilentity.ElectricityGuiTileEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.RegistryObject;

public class LaserReceiverContainer extends EnergyContainer {

    // Server Constructor
    public <T extends Container> LaserReceiverContainer(int windowId, PlayerInventory playerInventory, TileEntity tile, RegistryObject<ContainerType<T>> containerType) {
        super(windowId, playerInventory, (ElectricityGuiTileEntity)tile, containerType);

        addPlayerInvSlots(playerInventory);
    }

    // Client Constructor
    public <T extends Container> LaserReceiverContainer(int windowId, PlayerInventory playerInventory, PacketBuffer data, RegistryObject<ContainerType<T>> containerType) {
        this(windowId, playerInventory, getTileEntity(playerInventory, data), containerType);
    }
}
