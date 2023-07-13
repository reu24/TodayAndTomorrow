package com.reu_24.tat.init;

import com.reu_24.tat.TodayAndTomorrow;
import com.reu_24.tat.container.*;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModContainerTypes {
    public static DeferredRegister<ContainerType<?>> CONTAINER_TYPES = new DeferredRegister<>(ForgeRegistries.CONTAINERS, TodayAndTomorrow.MOD_ID);

    public static final RegistryObject<ContainerType<CoalGeneratorContainer>> COAL_GENERATOR = CONTAINER_TYPES.register("coal_generator",
            () -> IForgeContainerType.create((windowId, playerInventory, tile) -> new CoalGeneratorContainer(windowId, playerInventory, tile, ModContainerTypes.COAL_GENERATOR)));

    public static final RegistryObject<ContainerType<ElectricFurnaceContainer>> ELECTRIC_FURNACE = CONTAINER_TYPES.register("electric_furnace",
            () -> IForgeContainerType.create((windowId, playerInventory, tile) -> new ElectricFurnaceContainer(windowId, playerInventory, tile, ModContainerTypes.ELECTRIC_FURNACE)));

    public static final RegistryObject<ContainerType<AlloyFurnaceContainer>> ALLOY_FURNACE = CONTAINER_TYPES.register("alloy_furnace",
            () -> IForgeContainerType.create((windowId, playerInventory, tile) -> new AlloyFurnaceContainer(windowId, playerInventory, tile, ModContainerTypes.ALLOY_FURNACE)));

    public static final RegistryObject<ContainerType<GeothermalGeneratorContainer>> GEOTHERMAL_GENERATOR = CONTAINER_TYPES.register("geothermal_generator",
            () -> IForgeContainerType.create((windowId, playerInventory, tile) -> new GeothermalGeneratorContainer(windowId, playerInventory, tile, ModContainerTypes.GEOTHERMAL_GENERATOR)));

    public static final RegistryObject<ContainerType<SolarPanelGeneratorBaseContainer>> SOLAR_PANEL_GENERATOR = CONTAINER_TYPES.register("solar_panel_generator",
            () -> IForgeContainerType.create((windowId, playerInventory, tile) -> new SolarPanelGeneratorBaseContainer(windowId, playerInventory, tile, ModContainerTypes.SOLAR_PANEL_GENERATOR)));

    public static final RegistryObject<ContainerType<ElectrolysisMachineContainer>> ELECTROLYSIS_MACHINE = CONTAINER_TYPES.register("electrolysis_machine",
            () -> IForgeContainerType.create((windowId, playerInventory, tile) -> new ElectrolysisMachineContainer(windowId, playerInventory, tile, ModContainerTypes.ELECTROLYSIS_MACHINE)));

    public static final RegistryObject<ContainerType<LaserTransmitterContainer>> LASER_TRANSMITTER = CONTAINER_TYPES.register("laser_transmitter",
            () -> IForgeContainerType.create((windowId, playerInventory, tile) -> new LaserTransmitterContainer(windowId, playerInventory, tile, ModContainerTypes.LASER_TRANSMITTER)));

    public static final RegistryObject<ContainerType<LaserReceiverContainer>> LASER_RECEIVER = CONTAINER_TYPES.register("laser_receiver",
            () -> IForgeContainerType.create((windowId, playerInventory, tile) -> new LaserReceiverContainer(windowId, playerInventory, tile, ModContainerTypes.LASER_RECEIVER)));

    public static final RegistryObject<ContainerType<PurifierContainer>> PURIFIER = CONTAINER_TYPES.register("purifier",
            () -> IForgeContainerType.create((windowId, playerInventory, tile) -> new PurifierContainer(windowId, playerInventory, tile, ModContainerTypes.PURIFIER)));

    public static final RegistryObject<ContainerType<FissionControllerContainer>> FISSION_CONTROLLER = CONTAINER_TYPES.register("fission_controller",
            () -> IForgeContainerType.create((windowId, playerInventory, tile) -> new FissionControllerContainer(windowId, playerInventory, tile, ModContainerTypes.FISSION_CONTROLLER)));

    public static final RegistryObject<ContainerType<FissionHeatGeneratorContainer>> FISSION_HEAT_GENERATOR = CONTAINER_TYPES.register("fission_heat_generator",
            () -> IForgeContainerType.create((windowId, playerInventory, tile) -> new FissionHeatGeneratorContainer(windowId, playerInventory, tile, ModContainerTypes.FISSION_HEAT_GENERATOR)));
}
