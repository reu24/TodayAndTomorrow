package com.reu_24.tat.tilentity;

import com.reu_24.tat.init.ModTileEntityTypes;

public class CopperLaserReceiverTileEntity extends LaserReceiverTileEntity {

    public static final int MAX_EXTRACT = 50;
    public static final int ENERGY_PER_TICK = 40;
    public static final int CAPACITY = 70000;

    public CopperLaserReceiverTileEntity() {
        super(ModTileEntityTypes.COPPER_LASER_RECEIVER.get());
    }

    @Override
    protected int getCapacity() {
        return CAPACITY;
    }

    @Override
    protected int getMaxExtract() {
        return MAX_EXTRACT;
    }

    @Override
    protected String getRegistryName() {
        return "copper_laser_receiver";
    }
}
