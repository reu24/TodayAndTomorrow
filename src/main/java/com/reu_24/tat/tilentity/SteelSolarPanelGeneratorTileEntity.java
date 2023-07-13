package com.reu_24.tat.tilentity;

import com.reu_24.tat.init.ModTileEntityTypes;
import com.reu_24.tat.object.block.SteelSolarPanelGeneratorBlock;
import net.minecraft.tileentity.TileEntityType;

public class SteelSolarPanelGeneratorTileEntity extends SolarPanelGeneratorBaseTileEntity {

    public SteelSolarPanelGeneratorTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public SteelSolarPanelGeneratorTileEntity() {
        this(ModTileEntityTypes.STEEL_SOLAR_PANEL_GENERATOR.get());
    }

    @Override
    protected int getCapacity() {
        return SteelSolarPanelGeneratorBlock.CAPACITY;
    }

    @Override
    protected int getMaxExtract() {
        return SteelSolarPanelGeneratorBlock.MAX_EXTRACT;
    }
}
