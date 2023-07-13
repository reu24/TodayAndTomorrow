package com.reu_24.tat.init;

import com.reu_24.tat.TodayAndTomorrow;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.EmptyFluid;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Rarity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class FluidInit {
    public static final DeferredRegister<Fluid> FLUIDS = new DeferredRegister<>(ForgeRegistries.FLUIDS, TodayAndTomorrow.MOD_ID);

    public static final RegistryObject<Fluid> HYDROGEN = FLUIDS.register("hydrogen", EmptyFluid::new);
    public static final RegistryObject<Fluid> OXYGEN = FLUIDS.register("oxygen", EmptyFluid::new);
}
