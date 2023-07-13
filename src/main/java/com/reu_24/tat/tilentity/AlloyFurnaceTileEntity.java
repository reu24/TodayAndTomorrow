package com.reu_24.tat.tilentity;

import com.reu_24.tat.container.AlloyFurnaceContainer;
import com.reu_24.tat.init.ModContainerTypes;
import com.reu_24.tat.init.ModTileEntityTypes;
import com.reu_24.tat.init.RecipeSerializerInit;
import com.reu_24.tat.util.BaseItemHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.tileentity.TileEntityType;

public class AlloyFurnaceTileEntity extends ProcessingElectricityTileEntity<BaseItemHandler> {

    public static final int ENERGY_PER_TICK = 9;
    public static final int PROCESSING_TIME = 400;
    public static final int CAPACITY = 50000;

    public AlloyFurnaceTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);

        inventory = new BaseItemHandler(3);
    }

    public AlloyFurnaceTileEntity() {
        this(ModTileEntityTypes.ALLOY_FURNACE.get());
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
        return 2;
    }

    @Override
    protected boolean isRecipe(IRecipe<?> recipe) {
        return recipe.getType() == RecipeSerializerInit.ALLOY_TYPE;
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
        return "alloy_furnace";
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
        inventory.decrStackSize(1, 1);
    }

    @Override
    public Container createMenu(int windowId, PlayerInventory playerInventory, PlayerEntity player) {
        return new AlloyFurnaceContainer(windowId, playerInventory, this, ModContainerTypes.ALLOY_FURNACE);
    }
}
