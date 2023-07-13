package com.reu_24.tat.tilentity;

import com.reu_24.tat.init.ModTileEntityTypes;
import com.reu_24.tat.storage.PipeStorage;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;

public class SteelPipeTileEntity extends TileEntity implements ITickableTileEntity {

    public SteelPipeTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public SteelPipeTileEntity() {
        this(ModTileEntityTypes.STEEL_PIPE.get());
    }

    protected PipeStorage fluid = new PipeStorage();

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        LazyOptional<T> superResult = super.getCapability(cap, side);
        if (superResult.isPresent()) {
            return superResult;
        }
        return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.orEmpty(cap, LazyOptional.of(() -> fluid));
    }

    @Override
    public void tick() {
        if (!world.isRemote()) {
            fluid.setWorldAndPos(world, pos);
        }
    }
}
