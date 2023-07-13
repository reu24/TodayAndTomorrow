package com.reu_24.tat.util;

import net.minecraft.fluid.Fluid;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.registries.ForgeRegistries;

public class BaseFluidTank extends FluidTank {

    public BaseFluidTank(int capacity, FluidStack fluidStack) {
        super(capacity);
        setFluid(fluidStack);
    }

    public void addAmount(int amount) {
        int give = Math.min(capacity, fluid.getAmount() + amount);
        fluid.setAmount(give);
    }

    public FluidTank readFromNBT(CompoundNBT nbt, String name) {
        CompoundNBT n = (CompoundNBT)(nbt.get(name));
        ResourceLocation fluidName = new ResourceLocation(n.getString("FluidName"));
        Fluid fluid = ForgeRegistries.FLUIDS.getValue(fluidName);
        FluidStack stack = new FluidStack(fluid, n.getInt("Amount"));
        setFluid(stack);
        return this;
    }

    public CompoundNBT writeToNBT(CompoundNBT nbt, String name) {
        CompoundNBT compoundNBT = new CompoundNBT();
        compoundNBT.putString("FluidName", fluid.getRawFluid().getRegistryName().toString());
        compoundNBT.putInt("Amount", fluid.getAmount());
        nbt.put(name, compoundNBT);
        return compoundNBT;
    }
}
