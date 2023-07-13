package com.reu_24.tat.init;

import com.reu_24.tat.TodayAndTomorrow;
import com.reu_24.tat.tilentity.*;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTileEntityTypes {
	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPE = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, TodayAndTomorrow.MOD_ID);

	public static final RegistryObject<TileEntityType<CoalGeneratorTileEntity>> COAL_GENERATOR = TILE_ENTITY_TYPE.register("coal_generator", () -> TileEntityType.Builder.create(CoalGeneratorTileEntity::new, BlockInit.COAL_GENERATOR.get()).build(null));
	public static final RegistryObject<TileEntityType<ElectricFurnaceTileEntity>> ELECTRIC_FURNACE = TILE_ENTITY_TYPE.register("electric_furnace", () -> TileEntityType.Builder.create(ElectricFurnaceTileEntity::new, BlockInit.ELECTRIC_FURNACE.get()).build(null));
	public static final RegistryObject<TileEntityType<CopperWireTileEntity>> COPPER_WIRE = TILE_ENTITY_TYPE.register("copper_wire", () -> TileEntityType.Builder.create(CopperWireTileEntity::new, BlockInit.COPPER_WIRE.get()).build(null));
	public static final RegistryObject<TileEntityType<AlloyFurnaceTileEntity>> ALLOY_FURNACE = TILE_ENTITY_TYPE.register("alloy_furnace", () -> TileEntityType.Builder.create(AlloyFurnaceTileEntity::new, BlockInit.ALLOY_FURNACE.get()).build(null));
	public static final RegistryObject<TileEntityType<GeothermalGeneratorTileEntity>> GEOTHERMAL_GENERATOR = TILE_ENTITY_TYPE.register("geothermal_generator", () -> TileEntityType.Builder.create(GeothermalGeneratorTileEntity::new, BlockInit.GEOTHERMAL_GENERATOR.get()).build(null));
	public static final RegistryObject<TileEntityType<CopperSolarPanelGeneratorTileEntity>> COPPER_SOLAR_PANEL_GENERATOR = TILE_ENTITY_TYPE.register("copper_solar_panel_generator", () -> TileEntityType.Builder.create(CopperSolarPanelGeneratorTileEntity::new, BlockInit.COPPER_SOLAR_PANEL_GENERATOR.get()).build(null));
	public static final RegistryObject<TileEntityType<SteelSolarPanelGeneratorTileEntity>> STEEL_SOLAR_PANEL_GENERATOR = TILE_ENTITY_TYPE.register("steel_solar_panel_generator", () -> TileEntityType.Builder.create(SteelSolarPanelGeneratorTileEntity::new, BlockInit.STEEL_SOLAR_PANEL_GENERATOR.get()).build(null));
	public static final RegistryObject<TileEntityType<ElectrolysisMachineTileEntity>> ELECTROLYSIS_MACHINE = TILE_ENTITY_TYPE.register("electrolysis_machine", () -> TileEntityType.Builder.create(ElectrolysisMachineTileEntity::new, BlockInit.ELECTROLYSIS_MACHINE.get()).build(null));
	public static final RegistryObject<TileEntityType<SteelPipeTileEntity>> STEEL_PIPE = TILE_ENTITY_TYPE.register("steel_pipe", () -> TileEntityType.Builder.create(SteelPipeTileEntity::new, BlockInit.STEEL_PIPE.get()).build(null));
	public static final RegistryObject<TileEntityType<CopperLaserTransmitterTileEntity>> COPPER_LASER_TRANSMITTER = TILE_ENTITY_TYPE.register("copper_laser_transmitter", () -> TileEntityType.Builder.create(CopperLaserTransmitterTileEntity::new, BlockInit.COPPER_LASER_TRANSMITTER.get()).build(null));
	public static final RegistryObject<TileEntityType<CopperLaserReceiverTileEntity>> COPPER_LASER_RECEIVER = TILE_ENTITY_TYPE.register("copper_laser_receiver", () -> TileEntityType.Builder.create(CopperLaserReceiverTileEntity::new, BlockInit.COPPER_LASER_RECEIVER.get()).build(null));
	public static final RegistryObject<TileEntityType<PurifierTileEntity>> PURIFIER = TILE_ENTITY_TYPE.register("purifier", () -> TileEntityType.Builder.create(PurifierTileEntity::new, BlockInit.PURIFIER.get()).build(null));
	public static final RegistryObject<TileEntityType<FissionControllerTileEntity>> FISSION_CONTROLLER = TILE_ENTITY_TYPE.register("fission_controller", () -> TileEntityType.Builder.create(FissionControllerTileEntity::new, BlockInit.FISSION_CONTROLLER.get()).build(null));
	public static final RegistryObject<TileEntityType<FissionHeatGeneratorTileEntity>> FISSION_HEAT_GENERATOR = TILE_ENTITY_TYPE.register("fission_heat_generator", () -> TileEntityType.Builder.create(FissionHeatGeneratorTileEntity::new, BlockInit.FISSION_CONTROLLER.get()).build(null));
}
