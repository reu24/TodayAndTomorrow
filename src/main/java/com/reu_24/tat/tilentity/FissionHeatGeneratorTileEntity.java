package com.reu_24.tat.tilentity;

import com.reu_24.tat.init.ModTileEntityTypes;
import com.reu_24.tat.util.BaseItemHandler;
import net.minecraft.tileentity.TileEntityType;

public class FissionHeatGeneratorTileEntity extends ElectricityGuiTileEntity<BaseItemHandler> {

    public static final int CAPACITY = 200000;

    public FissionHeatGeneratorTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);

        inventory = new BaseItemHandler(0);
    }

    public FissionHeatGeneratorTileEntity() {
        this(ModTileEntityTypes.FISSION_HEAT_GENERATOR.get());
    }

    @Override
    protected boolean canReceive() {
        return false;
    }

    @Override
    protected int getCapacity() {
        return CAPACITY;
    }

    @Override
    protected int getMaxExtract() {
        return 200;
    }

    @Override
    protected String getRegistryName() {
        return "fission_heat_generator";
    }
}
