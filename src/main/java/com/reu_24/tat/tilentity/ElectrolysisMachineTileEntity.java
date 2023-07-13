package com.reu_24.tat.tilentity;

import com.reu_24.tat.container.ElectrolysisMachineContainer;
import com.reu_24.tat.init.FluidInit;
import com.reu_24.tat.init.ItemInit;
import com.reu_24.tat.init.ModContainerTypes;
import com.reu_24.tat.init.ModTileEntityTypes;
import com.reu_24.tat.object.block.ElectrolysisMachineBlock;
import com.reu_24.tat.util.BaseFluidTank;
import com.reu_24.tat.util.BaseItemHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;

public class ElectrolysisMachineTileEntity extends ElectricityGuiTileEntity<BaseItemHandler> {

    protected BaseFluidTank water = new BaseFluidTank(WATER_CAPACITY, new FluidStack(Fluids.WATER, 0));
    protected BaseFluidTank hydrogen = new BaseFluidTank(HYDROGEN_CAPACITY, new FluidStack(FluidInit.HYDROGEN.get(), 0));
    protected BaseFluidTank oxygen  = new BaseFluidTank(OXYGEN_CAPACITY, new FluidStack(FluidInit.OXYGEN.get(), 0));

    protected int waterBefore = 0;

    public static final int ENERGY_PER_TICK = 9;
    public static final int WATER_PER_TICK = 2;
    public static final int HYDROGEN_PER_TICK = WATER_PER_TICK * 2;
    public static final int OXYGEN_PER_TICK = WATER_PER_TICK;
    public static final int CAPACITY = 50000;
    public static final int WATER_CAPACITY = 10000;
    public static final int HYDROGEN_CAPACITY = 10000;
    public static final int OXYGEN_CAPACITY = 10000;

    public ElectrolysisMachineTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);

        inventory = new BaseItemHandler(2);
    }

    public ElectrolysisMachineTileEntity() {
        this(ModTileEntityTypes.ELECTROLYSIS_MACHINE.get());
    }

    @Override
    protected boolean canReceive() {
        return true;
    }

    @Override
    protected int getCapacity() {
        return CAPACITY;
    }

    @Override
    protected int getMaxExtract() {
        return 0;
    }

    @Override
    protected String getRegistryName() {
        return "electrolysis_machine";
    }

    @Override
    public Container createMenu(int windowId, PlayerInventory playerInventory, PlayerEntity player) {
        return new ElectrolysisMachineContainer(windowId, playerInventory, this, ModContainerTypes.ELECTROLYSIS_MACHINE);
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        LazyOptional<T> superCap = super.getCapability(cap, side);
        if (superCap.isPresent()) {
            return superCap;
        }

        Direction facing = ((ElectrolysisMachineBlock)getBlockState().getBlock()).getFacing(getBlockState());
        if (side == facing.rotateY()) {
            return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.orEmpty(cap, LazyOptional.of(() -> hydrogen));
        } else if (side == facing.rotateYCCW()) {
            return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.orEmpty(cap, LazyOptional.of(() -> oxygen));
        }
        return LazyOptional.empty();
    }

    public BaseFluidTank getHydrogen() {
        return hydrogen;
    }

    public BaseFluidTank getWater() {
        return water;
    }

    public BaseFluidTank getOxygen() {
        return oxygen;
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        water.writeToNBT(compound, "water");
        hydrogen.writeToNBT(compound, "hydrogen");
        oxygen.writeToNBT(compound, "oxygen");
        return compound;
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        water.readFromNBT(compound, "water");
        hydrogen.readFromNBT(compound, "hydrogen");
        oxygen.readFromNBT(compound, "oxygen");
    }

    @Override
    public void tick() {
        if (!world.isRemote()) {
            if (canProcess()) {
                water.addAmount(-WATER_PER_TICK);
                hydrogen.addAmount(HYDROGEN_PER_TICK);
                oxygen.addAmount(OXYGEN_PER_TICK);
                energy.extractEnergyRaw(ENERGY_PER_TICK, false);
                setLit(true);
            } else {
                setLit(false);
            }
            if (inventory.getStackInSlot(0).getItem() == Items.BUCKET && inventory.getStackInSlot(0).getCount() == 1 && hydrogen.getFluidAmount() >= 1000) {
                hydrogen.addAmount(-1000);
                inventory.setStackInSlot(0, new ItemStack(ItemInit.HYDROGEN_BUCKET.get()));
            }
            if (inventory.getStackInSlot(1).getItem() == Items.BUCKET && inventory.getStackInSlot(1).getCount() == 1 && oxygen.getFluidAmount() >= 1000) {
                oxygen.addAmount(-1000);
                inventory.setStackInSlot(1, new ItemStack(ItemInit.OXYGEN_BUCKET.get()));
            }
        }

        if (waterBefore != water.getFluidAmount()) {
            waterBefore = water.getFluidAmount();
            markDirty();
        }

        super.tick();
    }

    public boolean canProcess() {
        return (hydrogen.getSpace() > 0 || oxygen.getSpace() > 0)
                && water.getFluidAmount() >= WATER_PER_TICK && energy.getEnergyStored() >= ENERGY_PER_TICK;
    }
}
