package com.reu_24.tat.storage;

import com.reu_24.tat.init.BlockInit;
import com.reu_24.tat.util.helper.ElectricityHelper;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import java.util.ArrayList;

public class WireEnergyStorage implements IEnergyStorage {

    protected World world = null;
    protected BlockPos pos = null;

    public void setWorldAndPos(World world, BlockPos pos) {
        this.world = world;
        this.pos = pos;
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        if (world == null || pos == null) {
            return 0;
        }
        ArrayList<BlockPos> visited = new ArrayList<>();
        ArrayList<IEnergyStorage> outputMachineEnergy = new ArrayList<>();
        getOutputMachinesEnergy(null, pos, visited, outputMachineEnergy);

        return ElectricityHelper.scatterEnergy(outputMachineEnergy, maxReceive, world.getRandom(), simulate);
    }

    protected void getOutputMachinesEnergy(Direction from, BlockPos pos, ArrayList<BlockPos> visited, ArrayList<IEnergyStorage> outEnergies) {
        if (!visited.contains(pos)) {
            if (world.getBlockState(pos).getBlock() == BlockInit.COPPER_WIRE.get()) {
                visited.add(pos);
                getOutputMachinesEnergy(Direction.SOUTH, pos.north(), visited, outEnergies);
                getOutputMachinesEnergy(Direction.WEST, pos.east(), visited, outEnergies);
                getOutputMachinesEnergy(Direction.NORTH, pos.south(), visited, outEnergies);
                getOutputMachinesEnergy(Direction.EAST, pos.west(), visited, outEnergies);
                getOutputMachinesEnergy(Direction.DOWN, pos.up(), visited, outEnergies);
                getOutputMachinesEnergy(Direction.UP, pos.down(), visited, outEnergies);
            } else if (world.getTileEntity(pos) != null) {
                world.getTileEntity(pos).getCapability(CapabilityEnergy.ENERGY, from).ifPresent((e) -> {
                    if (e.canReceive()) {
                        outEnergies.add(e);
                    }
                });
            }
        }
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        return 0;
    }

    @Override
    public int getEnergyStored() {
        return 0;
    }

    @Override
    public int getMaxEnergyStored() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean canExtract() {
        return false;
    }

    @Override
    public boolean canReceive() {
        return true;
    }
}
