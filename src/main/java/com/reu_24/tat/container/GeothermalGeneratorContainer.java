package com.reu_24.tat.container;

import com.reu_24.tat.init.BlockInit;
import com.reu_24.tat.tilentity.ElectricityGuiTileEntity;
import com.reu_24.tat.tilentity.GeothermalGeneratorTileEntity;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.RegistryObject;

public class GeothermalGeneratorContainer extends EnergyContainer {

    protected GeothermalGeneratorTileEntity tileEntity;

    // Server Constructor
    public <T extends Container> GeothermalGeneratorContainer(int windowId, PlayerInventory playerInventory, TileEntity tile, RegistryObject<ContainerType<T>> containerType) {
        super(windowId, playerInventory, (ElectricityGuiTileEntity)tile, containerType);
        tileEntity = (GeothermalGeneratorTileEntity) tile;

        addPlayerInvSlots(playerInventory);
    }

    // Client Constructor
    public <T extends Container> GeothermalGeneratorContainer(int windowId, PlayerInventory playerInventory, PacketBuffer data, RegistryObject<ContainerType<T>> containerType) {
        this(windowId, playerInventory, getTileEntity(playerInventory, data), containerType);
    }

    @OnlyIn(Dist.CLIENT)
    public boolean isProducing() {
        return tileEntity.isProducing();
    }
}
