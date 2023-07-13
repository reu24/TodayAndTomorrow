package com.reu_24.tat.tilentity;

import com.reu_24.tat.init.ModEntityTypes;
import com.reu_24.tat.init.ModTileEntityTypes;
import com.reu_24.tat.object.block.CopperSolarPanelGeneratorBlock;
import net.minecraft.tileentity.TileEntityType;

public class CopperSolarPanelGeneratorTileEntity extends SolarPanelGeneratorBaseTileEntity {

    public CopperSolarPanelGeneratorTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public CopperSolarPanelGeneratorTileEntity() {
        this(ModTileEntityTypes.COPPER_SOLAR_PANEL_GENERATOR.get());
    }

    @Override
    protected int getCapacity() {
        return CopperSolarPanelGeneratorBlock.CAPACITY;
    }

    @Override
    protected int getMaxExtract() {
        return CopperSolarPanelGeneratorBlock.MAX_EXTRACT;
    }
}
