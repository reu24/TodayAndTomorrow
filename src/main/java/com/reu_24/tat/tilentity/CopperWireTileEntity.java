package com.reu_24.tat.tilentity;

import com.reu_24.tat.init.ModTileEntityTypes;
import com.reu_24.tat.storage.WireEnergyStorage;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;

public class CopperWireTileEntity extends TileEntity implements ITickableTileEntity {

    protected WireEnergyStorage energy = new WireEnergyStorage();

    public CopperWireTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        LazyOptional<T> superResult = super.getCapability(cap, side);
        if (superResult.isPresent()) {
            return superResult;
        }
        return CapabilityEnergy.ENERGY.orEmpty(cap, LazyOptional.of(() -> energy));
    }

    public CopperWireTileEntity() {
        this(ModTileEntityTypes.COPPER_WIRE.get());
    }

    @Override
    public void tick() {
        if (!world.isRemote()) {
            energy.setWorldAndPos(world, pos);
        }
    }
}
