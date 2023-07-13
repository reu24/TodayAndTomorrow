package com.reu_24.tat.tilentity;

import com.reu_24.tat.init.ModTileEntityTypes;

public class CopperLaserTransmitterTileEntity extends LaserTransmitterTileEntity {

    public static final int ENERGY_PER_TICK = 20;
    public static final float DAMAGE = 2.0f;
    public static final int CAPACITY = 30000;

    public CopperLaserTransmitterTileEntity() {
        super(ModTileEntityTypes.COPPER_LASER_TRANSMITTER.get());
    }

    @Override
    protected int getCapacity() {
        return CAPACITY;
    }

    @Override
    protected String getRegistryName() {
        return "copper_laser_transmitter";
    }

    @Override
    public int getEnergyPerTick() {
        return ENERGY_PER_TICK;
    }

    @Override
    public float getDamage() {
        return DAMAGE;
    }

    @Override
    public int getEnergyReductionPerEntity() {
        return 5;
    }
}
