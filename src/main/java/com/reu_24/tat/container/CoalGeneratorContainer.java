package com.reu_24.tat.container;

import com.reu_24.tat.init.BlockInit;
import com.reu_24.tat.tilentity.CoalGeneratorTileEntity;
import com.reu_24.tat.tilentity.ElectricityGuiTileEntity;
import com.reu_24.tat.util.FunctionalIntReferenceHolder;
import com.reu_24.tat.util.WhitelistSlotItemHandler;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.RegistryObject;

import java.util.ArrayList;
import java.util.List;

public class CoalGeneratorContainer extends EnergyContainer {

    protected CoalGeneratorTileEntity tileEntity;
    protected FunctionalIntReferenceHolder currentProcessTime;

    // Server Constructor
    public <T extends Container> CoalGeneratorContainer(int windowId, PlayerInventory playerInventory, TileEntity tile, RegistryObject<ContainerType<T>> containerType) {
        super(windowId, playerInventory, (ElectricityGuiTileEntity)tile, containerType);
        tileEntity = (CoalGeneratorTileEntity)tile;

        addPlayerInvSlots(playerInventory);

        // Block Slots
        List<Item> inputs = new ArrayList<>();
        inputs.add(Items.COAL);
        inputs.add(Items.CHARCOAL);
        addSlot(new WhitelistSlotItemHandler(tileEntity.getInventory(), 0, 80, 35, inputs));

        trackInt(currentProcessTime = new FunctionalIntReferenceHolder(() -> tileEntity.currentProcessingTime, v -> tileEntity.currentProcessingTime = v));
    }

    // Client Constructor
    public <T extends Container> CoalGeneratorContainer(int windowId, PlayerInventory playerInventory, PacketBuffer data, RegistryObject<ContainerType<T>> containerType) {
        this(windowId, playerInventory, getTileEntity(playerInventory, data), containerType);
    }

    @OnlyIn(Dist.CLIENT)
    public int getProgressionScaled() {
        return currentProcessTime.get() != 0 ? (int)((float)currentProcessTime.get() / (float)tileEntity.getProcessingTime() * 13.0f) : 13;
    }
}
