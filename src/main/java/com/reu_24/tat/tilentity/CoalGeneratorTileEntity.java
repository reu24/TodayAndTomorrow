package com.reu_24.tat.tilentity;

import com.reu_24.tat.container.CoalGeneratorContainer;
import com.reu_24.tat.init.ModContainerTypes;
import com.reu_24.tat.init.ModTileEntityTypes;
import com.reu_24.tat.util.BaseItemHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.items.wrapper.RecipeWrapper;

public class CoalGeneratorTileEntity extends ProcessingElectricityTileEntity<BaseItemHandler> {

    protected boolean isProcessing = false;

    public static final int ENERGY_PER_TICK = 60;
    public static final int PROCESSING_TIME = 250;
    public static final int CAPACITY = 40000;

    public CoalGeneratorTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);

        inventory = new BaseItemHandler(1);
    }

    public CoalGeneratorTileEntity() {
        this(ModTileEntityTypes.COAL_GENERATOR.get());
    }

    @Override
    protected int getCapacity() {
        return CAPACITY;
    }

    @Override
    protected int getMaxExtract() {
        return 80;
    }

    @Override
    public int getProcessingTime() {
        return PROCESSING_TIME;
    }

    @Override
    protected int getEnergyNeededPerTick() {
        return 0;
    }

    @Override
    protected int getOutputSlot() {
        return 0;
    }

    @Override
    protected boolean isRecipe(IRecipe<?> recipe) {
        return false;
    }

    @Override
    protected String getRegistryName() {
        return "coal_generator";
    }

    @Override
    protected boolean canReceive() {
        return false;
    }

    @Override
    protected void onStartProcessing() {
        inventory.decrStackSize(0, 1);
        isProcessing = true;
    }

    @Override
    protected void onProcessing() {
        energy.receiveEnergyRaw(ENERGY_PER_TICK, false);
    }

    @Override
    protected void onItemProcessed() {
        isProcessing = false;
    }

    @Override
    public Container createMenu(int windowId, PlayerInventory playerInventory, PlayerEntity player) {
        return new CoalGeneratorContainer(windowId, playerInventory, this, ModContainerTypes.COAL_GENERATOR);
    }

    @Override
    protected boolean shouldProcess() {
        return (energy.getMaxEnergyStored() > energy.getEnergyStored() && inventory.getStackInSlot(0).getCount() > 0)
                || isProcessing;
    }
}
