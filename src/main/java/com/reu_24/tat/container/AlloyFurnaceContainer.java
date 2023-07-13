package com.reu_24.tat.container;

import com.reu_24.tat.init.BlockInit;
import com.reu_24.tat.tilentity.AlloyFurnaceTileEntity;
import com.reu_24.tat.tilentity.ElectricFurnaceTileEntity;
import com.reu_24.tat.tilentity.ElectricityGuiTileEntity;
import com.reu_24.tat.util.FunctionalIntReferenceHolder;
import com.reu_24.tat.util.ResultSlotItemHandler;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.items.SlotItemHandler;

public class AlloyFurnaceContainer extends EnergyContainer {

    protected AlloyFurnaceTileEntity tileEntity;
    protected FunctionalIntReferenceHolder currentProcessTime;

    // Server Constructor
    public <T extends Container> AlloyFurnaceContainer(int windowId, PlayerInventory playerInventory, TileEntity tile, RegistryObject<ContainerType<T>> containerType) {
        super(windowId, playerInventory, (ElectricityGuiTileEntity)tile, containerType);
        tileEntity = (AlloyFurnaceTileEntity) tile;

        addPlayerInvSlots(playerInventory);

        // Block Slots
        addSlot(new SlotItemHandler(tileEntity.getInventory(), 0, 56, 25));
        addSlot(new SlotItemHandler(tileEntity.getInventory(), 1, 56, 45));
        addSlot(new ResultSlotItemHandler(tileEntity.getInventory(), 2, 116, 35));

        trackInt(currentProcessTime = new FunctionalIntReferenceHolder(() -> tileEntity.currentProcessingTime, v -> tileEntity.currentProcessingTime = v));
    }

    // Client Constructor
    public <T extends Container> AlloyFurnaceContainer(int windowId, PlayerInventory playerInventory, PacketBuffer data, RegistryObject<ContainerType<T>> containerType) {
        this(windowId, playerInventory, getTileEntity(playerInventory, data), containerType);
    }

    @OnlyIn(Dist.CLIENT)
    public int getProgressionScaled() {
        return currentProcessTime.get() != 0 && tileEntity.getProcessingTime() != 0 ? currentProcessTime.get() * 24 / tileEntity.getProcessingTime() : 0;
    }
    @OnlyIn(Dist.CLIENT)
    public int getEnoughEnergyScaled() {
        int energyNeeded = tileEntity.getProcessingTime() * ElectricFurnaceTileEntity.ENERGY_PER_TICK;
        int energyStored = tileEntity.getEnergy().getEnergyStored();
        return (int)(Math.abs(1 - (Math.min((float)energyStored / (float)energyNeeded, 1.0f))) * 14.0f);
    }
}
