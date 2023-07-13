package com.reu_24.tat.container;

import com.reu_24.tat.tilentity.ElectricityGuiTileEntity;
import com.reu_24.tat.tilentity.ElectrolysisMachineTileEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.items.SlotItemHandler;

public class ElectrolysisMachineContainer extends EnergyContainer {

    protected ElectrolysisMachineTileEntity tileEntity;
    protected int progression = 0;

    public static final int PROGRESSION_TIME = 200;

    // Server Constructor
    public <T extends Container> ElectrolysisMachineContainer(int windowId, PlayerInventory playerInventory, TileEntity tile, RegistryObject<ContainerType<T>> containerType) {
        super(windowId, playerInventory, (ElectricityGuiTileEntity)tile, containerType);
        tileEntity = (ElectrolysisMachineTileEntity) tile;

        addPlayerInvSlots(playerInventory);

        addSlot(new SlotItemHandler(tileEntity.getInventory(), 0, 124, 22));
        addSlot(new SlotItemHandler(tileEntity.getInventory(), 1, 124, 46));
    }

    // Client Constructor
    public <T extends Container> ElectrolysisMachineContainer(int windowId, PlayerInventory playerInventory, PacketBuffer data, RegistryObject<ContainerType<T>> containerType) {
        this(windowId, playerInventory, getTileEntity(playerInventory, data), containerType);
    }

    @OnlyIn(Dist.CLIENT)
    public int getProgressionScaled() {
        if (progression++ > PROGRESSION_TIME) {
            progression = 0;
        }
        return tileEntity.canProcess() ? progression * 24 / PROGRESSION_TIME : 0;
    }

    @OnlyIn(Dist.CLIENT)
    public int getWater() {
        return tileEntity.getWater().getFluidAmount();
    }

    @OnlyIn(Dist.CLIENT)
    public int getMaxWater() {
        return tileEntity.getWater().getCapacity();
    }

    @OnlyIn(Dist.CLIENT)
    public int getWaterScaled() {
        return getMaxWater() != 0 ? (int)((float)getWater() / (float)getMaxWater() * 48) : 0;
    }

    @OnlyIn(Dist.CLIENT)
    public int getHydrogen() {
        return tileEntity.getHydrogen().getFluidAmount();
    }

    @OnlyIn(Dist.CLIENT)
    public int getMaxHydrogen() {
        return tileEntity.getHydrogen().getCapacity();
    }

    @OnlyIn(Dist.CLIENT)
    public int getHydrogenScaled() {
        return getMaxHydrogen() != 0 ? (int)((float)getHydrogen() / (float)getMaxHydrogen() * 48) : 0;
    }

    @OnlyIn(Dist.CLIENT)
    public int getOxygen() {
        return tileEntity.getOxygen().getFluidAmount();
    }

    @OnlyIn(Dist.CLIENT)
    public int getMaxOxygen() {
        return tileEntity.getOxygen().getCapacity();
    }

    @OnlyIn(Dist.CLIENT)
    public int getOxygenScaled() {
        return getMaxOxygen() != 0 ? (int)((float)getOxygen() / (float)getMaxOxygen() * 48) : 0;
    }

    public ElectrolysisMachineTileEntity getTileEntity() {
        return tileEntity;
    }

    @OnlyIn(Dist.CLIENT)
    public boolean hasItemInSlot(int slot) {
        return inventorySlots.get(slot + 36).getHasStack();
    }
}
