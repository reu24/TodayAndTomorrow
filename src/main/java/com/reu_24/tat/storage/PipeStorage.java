package com.reu_24.tat.storage;

import com.reu_24.tat.init.BlockInit;
import com.reu_24.tat.util.helper.FluidHelper;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;

import java.util.ArrayList;

public class PipeStorage extends FluidTank {

    protected World world = null;
    protected BlockPos pos = null;

    public PipeStorage() {
        super(0);
    }

    public void setWorldAndPos(World world, BlockPos pos) {
        this.world = world;
        this.pos = pos;
    }

    @Override
    public int fill(FluidStack resource, IFluidHandler.FluidAction action) {
        if (world == null || pos == null) {
            return 0;
        }
        ArrayList<BlockPos> visited = new ArrayList<>();
        ArrayList<IFluidHandler> outputMachineFluid = new ArrayList<>();
        getOutputMachinesFluid(null, pos, visited, outputMachineFluid);

        return FluidHelper.scatterFluid(outputMachineFluid, resource.getAmount(), world.getRandom(), action.simulate());
    }

    protected void getOutputMachinesFluid(Direction from, BlockPos pos, ArrayList<BlockPos> visited, ArrayList<IFluidHandler> outEnergies) {
        if (!visited.contains(pos)) {
            if (world.getBlockState(pos).getBlock() == BlockInit.STEEL_PIPE.get()) {
                visited.add(pos);
                getOutputMachinesFluid(Direction.SOUTH, pos.north(), visited, outEnergies);
                getOutputMachinesFluid(Direction.WEST, pos.east(), visited, outEnergies);
                getOutputMachinesFluid(Direction.NORTH, pos.south(), visited, outEnergies);
                getOutputMachinesFluid(Direction.EAST, pos.west(), visited, outEnergies);
                getOutputMachinesFluid(Direction.DOWN, pos.up(), visited, outEnergies);
                getOutputMachinesFluid(Direction.UP, pos.down(), visited, outEnergies);
            } else if (world.getTileEntity(pos) != null) {
                world.getTileEntity(pos).getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, from).ifPresent((e) -> {
                    if (e.fill(new FluidStack(e.getFluidInTank(0).getFluid(), 1), FluidAction.SIMULATE) == 1) {
                        outEnergies.add(e);
                    }
                });
            }
        }
    }
}
