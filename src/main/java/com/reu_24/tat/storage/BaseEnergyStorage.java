package com.reu_24.tat.storage;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.energy.EnergyStorage;

public class BaseEnergyStorage extends EnergyStorage {

    public BaseEnergyStorage(int capacity, boolean canReceive, int maxExtract, int energy) {
        super(capacity, 0, maxExtract, energy);
        maxReceive = canReceive ? 1 : 0;
    }

    public int receiveEnergyRaw(int maxReceive, boolean simulate)
    {
        int energyReceived = Math.min(capacity - energy, maxReceive);
        if (!simulate)
            energy += energyReceived;
        return energyReceived;
    }

    public int extractEnergyRaw(int maxExtract, boolean simulate)
    {
        int energyExtracted = Math.min(energy, maxExtract);
        if (!simulate)
            energy -= energyExtracted;
        return energyExtracted;
    }

    @Override
    public boolean canReceive() {
        return maxReceive == 1;
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        if (!canReceive()) {
            return 0;
        }

        int energyReceived = Math.min(capacity - energy, maxReceive);
        if (!simulate)
            energy += energyReceived;
        return energyReceived;
    }

    public int getMaxExtract() {
        return maxExtract;
    }

    public void readFromNBT(CompoundNBT compound)
    {
        energy = compound.getInt("Energy");
        capacity = compound.getInt("Capacity");
        maxReceive = compound.getInt("MaxReceive");
        maxExtract = compound.getInt("MaxExtract");
    }

    public void writeToNBT(CompoundNBT compound)
    {
        compound.putInt("Energy", energy);
        compound.putInt("Capacity", capacity);
        compound.putInt("MaxReceive", maxReceive);
        compound.putInt("MaxExtract", maxExtract);
    }
}
