package com.reu_24.tat.tilentity;

import com.reu_24.tat.container.ElectricFurnaceContainer;
import com.reu_24.tat.init.ModContainerTypes;
import com.reu_24.tat.init.ModTileEntityTypes;
import com.reu_24.tat.util.BaseItemHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.tileentity.TileEntityType;

// TODO Starting & instantly stopping cause of lack of energy problem
public class ElectricFurnaceTileEntity extends ProcessingElectricityTileEntity<BaseItemHandler> {

    public static final int ENERGY_PER_TICK = 12;
    public static final int PROCESSING_TIME = 200;
    public static final int CAPACITY = 50000;

    public ElectricFurnaceTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);

        inventory = new BaseItemHandler(2);
    }

    public ElectricFurnaceTileEntity() {
        this(ModTileEntityTypes.ELECTRIC_FURNACE.get());
    }

    @Override
    public int getProcessingTime() {
        return PROCESSING_TIME;
    }

    @Override
    protected int getEnergyNeededPerTick() {
        return ENERGY_PER_TICK;
    }

    @Override
    protected int getOutputSlot() {
        return 1;
    }

    @Override
    protected int getCapacity() {
        return CAPACITY;
    }

    @Override
    protected int getMaxExtract() {
        return 0;
    }

    @Override
    protected String getRegistryName() {
        return "electric_furnace";
    }

    @Override
    protected boolean canReceive() {
        return true;
    }

    @Override
    protected void onProcessing() {
        energy.extractEnergyRaw(ENERGY_PER_TICK, false);
    }

    @Override
    protected void onItemProcessed() {
        inventory.decrStackSize(0, 1);
    }

    @Override
    public Container createMenu(int windowId, PlayerInventory playerInventory, PlayerEntity player) {
        return new ElectricFurnaceContainer(windowId, playerInventory, this, ModContainerTypes.ELECTRIC_FURNACE);
    }

    @Override
    protected boolean isRecipe(IRecipe<?> recipe) {
        return recipe.getType().toString().equals("smelting");
    }
}
