package com.reu_24.tat.tilentity;

import com.reu_24.tat.container.SolarPanelGeneratorBaseContainer;
import com.reu_24.tat.init.ModContainerTypes;
import com.reu_24.tat.object.block.SolarPanelGenerator;
import com.reu_24.tat.util.BaseItemHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.dimension.DimensionType;

public abstract class SolarPanelGeneratorBaseTileEntity extends ElectricityGuiTileEntity<BaseItemHandler> {

    public SolarPanelGeneratorBaseTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);

        inventory = new BaseItemHandler(0);
    }

    @Override
    protected boolean canReceive() {
        return false;
    }

    @Override
    protected abstract int getCapacity();

    @Override
    protected abstract int getMaxExtract();

    @Override
    protected String getRegistryName() {
        return ((SolarPanelGenerator)(getBlockState().getBlock())).registryName();
    }

    @Override
    public void tick() {
        super.tick();

        if (isProducing()) {
            int energyPerTick = ((SolarPanelGenerator)(getBlockState().getBlock())).getEnergyPerTick();
            energy.receiveEnergyRaw(energyPerTick, false);
        }
    }

    @Override
    public Container createMenu(int windowId, PlayerInventory playerInventory, PlayerEntity player) {
        return new SolarPanelGeneratorBaseContainer(windowId, playerInventory, this, ModContainerTypes.SOLAR_PANEL_GENERATOR);
    }

    public boolean isProducing() {
        if (world.isNightTime() || world.getDimension().getType() != DimensionType.OVERWORLD) {
            return false;
        }

        for (int y = pos.getY() + 1; y < 256; y++) {
            BlockPos blockPos = new BlockPos(pos.getX(), y, pos.getZ());
            BlockState block = world.getBlockState(pos);
            if (block.getOpacity(this.world, blockPos) >= 15 && block.getBlock() != Blocks.BEDROCK) {
                return false;
            }
        }
        return true;
    }
}
