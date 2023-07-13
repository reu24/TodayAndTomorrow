package com.reu_24.tat.tilentity;

import com.reu_24.tat.storage.BaseEnergyStorage;
import com.reu_24.tat.util.BaseItemHandler;
import com.reu_24.tat.util.helper.ElectricityHelper;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public abstract class ElectricityGuiTileEntity<I extends BaseItemHandler> extends GuiTileEntity<I> implements ITickableTileEntity {

    protected BaseEnergyStorage energy = new BaseEnergyStorage(getCapacity(), canReceive(), getMaxExtract(), getStartEnergy());
    int energyBefore = 0;

    public ElectricityGuiTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        energy.readFromNBT(compound);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        energy.writeToNBT(compound);
        return compound;
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        LazyOptional<T> superResult = super.getCapability(cap, side);
        if (superResult.isPresent()) {
            return superResult;
        }
        return CapabilityEnergy.ENERGY.orEmpty(cap, LazyOptional.of(() -> energy));
    }

    @Override
    public void tick() {
        if (!world.isRemote()) {
            if (energy.canExtract()) {
                int toExtract = ElectricityHelper.scatterEnergy(getNeighborEnergies(), Math.min(energy.getMaxExtract(), energy.getEnergyStored()), world.getRandom(), false);
                energy.extractEnergy(toExtract, false);
            }
            if (energyBefore != energy.getEnergyStored()) {
                energyBefore = energy.getEnergyStored();
                markDirty();
            }
        }
    }

    protected ArrayList<IEnergyStorage> getNeighborEnergies() {
        ArrayList<IEnergyStorage> neighborEnergies = new ArrayList<>();
        add(neighborEnergies, getMachineEnergy(pos.north(), Direction.NORTH, world));
        add(neighborEnergies, getMachineEnergy(pos.east(), Direction.EAST, world));
        add(neighborEnergies, getMachineEnergy(pos.south(), Direction.SOUTH, world));
        add(neighborEnergies, getMachineEnergy(pos.west(), Direction.WEST, world));
        add(neighborEnergies, getMachineEnergy(pos.up(), Direction.UP, world));
        add(neighborEnergies, getMachineEnergy(pos.down(), Direction.DOWN, world));
        return neighborEnergies;
    }

    public static void add(ArrayList<IEnergyStorage> arr, IEnergyStorage energy) {
        if (energy != null) {
            arr.add(energy);
        }
    }

    public static IEnergyStorage getMachineEnergy(BlockPos pos, Direction direction, World world) {
        TileEntity tileEntity = world.getTileEntity(pos);
        if (tileEntity != null) {
            AtomicReference<IEnergyStorage> energy = new AtomicReference<>();
            tileEntity.getCapability(CapabilityEnergy.ENERGY, direction.getOpposite()).ifPresent((e) -> {
                if (e.canReceive()) {
                    energy.set(e);
                }
            });
            return energy.get();
        }
        return null;
    }

    public BaseEnergyStorage getEnergy() {
        return energy;
    }

    protected abstract boolean canReceive();
    protected abstract int getCapacity();
    protected abstract int getMaxExtract();
    protected int getStartEnergy() {
        return 0;
    }
}
