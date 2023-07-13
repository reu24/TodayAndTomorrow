package com.reu_24.tat.container;

import com.reu_24.tat.tilentity.ElectricityGuiTileEntity;
import com.reu_24.tat.tilentity.LaserTransmitterTileEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.RegistryObject;

public class LaserTransmitterContainer extends EnergyContainer {

    protected LaserTransmitterTileEntity tileEntity;

    // Server Constructor
    public <T extends Container> LaserTransmitterContainer(int windowId, PlayerInventory playerInventory, TileEntity tile, RegistryObject<ContainerType<T>> containerType) {
        super(windowId, playerInventory, (ElectricityGuiTileEntity)tile, containerType);
        tileEntity = (LaserTransmitterTileEntity) tile;

        addPlayerInvSlots(playerInventory);
    }

    // Client Constructor
    public <T extends Container> LaserTransmitterContainer(int windowId, PlayerInventory playerInventory, PacketBuffer data, RegistryObject<ContainerType<T>> containerType) {
        this(windowId, playerInventory, getTileEntity(playerInventory, data), containerType);
    }

    public LaserTransmitterTileEntity getTileEntity() {
        return tileEntity;
    }
}
