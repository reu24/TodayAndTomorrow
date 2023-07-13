package com.reu_24.tat.container;

import com.reu_24.tat.tilentity.ElectricityGuiTileEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.RegistryObject;

public abstract class EnergyContainer extends BaseContainer {

    private ElectricityGuiTileEntity electricityGuiTileEntity;

    public <T extends Container> EnergyContainer(int windowId, PlayerInventory playerInventory, ElectricityGuiTileEntity tile, RegistryObject<ContainerType<T>> containerType) {
        super(windowId, playerInventory, tile, containerType);

        electricityGuiTileEntity = tile;
    }

    @OnlyIn(Dist.CLIENT)
    public int getEnergy() {
        return electricityGuiTileEntity.getEnergy().getEnergyStored();
    }

    @OnlyIn(Dist.CLIENT)
    public int getMaxEnergy() {
        return electricityGuiTileEntity.getEnergy().getMaxEnergyStored();
    }

    @OnlyIn(Dist.CLIENT)
    public int getEnergyScaled() {
        return getMaxEnergy() != 0 ? (int)((float)getEnergy() / (float)getMaxEnergy() * 48) : 0;
    }
}
