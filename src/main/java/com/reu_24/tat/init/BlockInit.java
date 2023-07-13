package com.reu_24.tat.init;

import com.reu_24.tat.TodayAndTomorrow;
import com.reu_24.tat.entity.HydrogenTNTEntity;
import com.reu_24.tat.itemgroup.TodayAndTomorrowItemGroup;
import com.reu_24.tat.object.block.*;
import com.reu_24.tat.object.item.TooltipBlockItem;
import com.reu_24.tat.tilentity.*;
import com.reu_24.tat.util.helper.ElectricityHelper;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class BlockInit {
	public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, TodayAndTomorrow.MOD_ID);

	protected static RegistryObject<Item> register(RegistryObject<Block> block, String name) {
		return ItemInit.ITEMS.register(name,
				() -> new BlockItem(block.get(), new Item.Properties().group(TodayAndTomorrowItemGroup.INSTANCE)));
	}

	protected static RegistryObject<Item> register(RegistryObject<Block> block, String name, Supplier<String> tooltip, Supplier<String> shiftTooltip, Supplier<String> controlTooltip) {
		return ItemInit.ITEMS.register(name,
				() -> new TooltipBlockItem(block.get(), new Item.Properties().group(TodayAndTomorrowItemGroup.INSTANCE), tooltip, shiftTooltip, controlTooltip));
	}

	// Blocks
	public static final RegistryObject<Block> COAL_GENERATOR = BLOCKS.register("coal_generator",
			() -> new CoalGeneratorBlock(Block.Properties.from(Blocks.FURNACE)));
	public static final RegistryObject<Block> COPPER_WIRE = BLOCKS.register("copper_wire",
			() -> new CopperWireBlock(Block.Properties.create(Material.WOOL, MaterialColor.WOOL).hardnessAndResistance(0.8F).sound(SoundType.CLOTH)));
	public static final RegistryObject<Block> ELECTRIC_FURNACE = BLOCKS.register("electric_furnace",
			() -> new ElectricFurnaceBlock(Block.Properties.from(Blocks.FURNACE)));
	public static final RegistryObject<Block> COPPER_ORE = BLOCKS.register("copper_ore",
			() -> new Block(Block.Properties.from(Blocks.IRON_ORE)));
	public static final RegistryObject<Block> ALLOY_FURNACE = BLOCKS.register("alloy_furnace",
			() -> new AlloyFurnaceBlock(Block.Properties.from(Blocks.FURNACE)));
	public static final RegistryObject<Block> GEOTHERMAL_GENERATOR = BLOCKS.register("geothermal_generator",
			() -> new GeothermalGeneratorBlock(Block.Properties.from(Blocks.FURNACE)));
	public static final RegistryObject<Block> COPPER_SOLAR_PANEL_GENERATOR = BLOCKS.register("copper_solar_panel_generator",
			() -> new CopperSolarPanelGeneratorBlock(Block.Properties.from(Blocks.FURNACE)));
	public static final RegistryObject<Block> STEEL_SOLAR_PANEL_GENERATOR = BLOCKS.register("steel_solar_panel_generator",
			() -> new SteelSolarPanelGeneratorBlock(Block.Properties.from(Blocks.FURNACE)));
	public static final RegistryObject<Block> ELECTROLYSIS_MACHINE = BLOCKS.register("electrolysis_machine",
			() -> new ElectrolysisMachineBlock(Block.Properties.from(Blocks.FURNACE)));
	public static final RegistryObject<Block> STEEL_PIPE = BLOCKS.register("steel_pipe",
			() -> new SteelPipeBlock(Block.Properties.from(Blocks.IRON_BLOCK)));
	public static final RegistryObject<Block> HYDROGEN_TNT = BLOCKS.register("hydrogen_tnt",
			() -> new HydrogenTNTBlock(Block.Properties.from(Blocks.TNT)));
	public static final RegistryObject<Block> COPPER_LASER_TRANSMITTER = BLOCKS.register("copper_laser_transmitter",
			() -> new CopperLaserTransmitterBlock(Block.Properties.from(Blocks.FURNACE)));
	public static final RegistryObject<Block> COPPER_LASER_RECEIVER = BLOCKS.register("copper_laser_receiver",
			() -> new CopperLaserReceiverBlock(Block.Properties.from(Blocks.FURNACE)));
	public static final RegistryObject<Block> URANIUM_ORE = BLOCKS.register("uranium_ore",
			() -> new XPBlock(Block.Properties.from(Blocks.DIAMOND_ORE), 4, 10));
	public static final RegistryObject<Block> STEEL_BLOCK = BLOCKS.register("steel_block",
			() -> new Block(Block.Properties.from(Blocks.IRON_BLOCK)));
	public static final RegistryObject<Block> PURIFIER = BLOCKS.register("purifier",
			() -> new PurifierBlock(Block.Properties.from(Blocks.FURNACE)));
	public static final RegistryObject<Block> FISSION_CONTROLLER = BLOCKS.register("fission_controller",
			() -> new FissionControllerBlock(Block.Properties.from(Blocks.FURNACE)));
	public static final RegistryObject<Block> FISSION_HEAT_GENERATOR = BLOCKS.register("fission_heat_generator",
			() -> new FissionHeatGeneratorBlock(Block.Properties.from(Blocks.FURNACE)));


// TODO Tooltips translation & TooltipBlockItem translation
	// Block items
	public static final RegistryObject<Item> COAL_GENERATOR_ITEM = register(COAL_GENERATOR, "coal_generator", () -> "Generates FE out of coal.", null, () -> "Energy generation: " + ElectricityHelper.toFE(CoalGeneratorTileEntity.ENERGY_PER_TICK) + "/t\nCoal processing time: " + CoalGeneratorTileEntity.PROCESSING_TIME + "t\nEnergy per coal: " + ElectricityHelper.toFE(CoalGeneratorTileEntity.PROCESSING_TIME * CoalGeneratorTileEntity.ENERGY_PER_TICK) + "\nCapacity: " + ElectricityHelper.toFE(CoalGeneratorTileEntity.CAPACITY));
	public static final RegistryObject<Item> COPPER_WIRE_ITEM = register(COPPER_WIRE, "copper_wire");
	public static final RegistryObject<Item> ELECTRIC_FURNACE_ITEM = register(ELECTRIC_FURNACE, "electric_furnace", () -> "Smelts items like a furnace but with FE.", null, () -> "Energy usage: " + ElectricityHelper.toFE(ElectricFurnaceTileEntity.ENERGY_PER_TICK) + "/t\nProcessing time: " + ElectricFurnaceTileEntity.PROCESSING_TIME + "t\nEnergy needed per item: " + ElectricityHelper.toFE(ElectricFurnaceTileEntity.PROCESSING_TIME * ElectricFurnaceTileEntity.ENERGY_PER_TICK) + "\nCapacity: " + ElectricityHelper.toFE(ElectricFurnaceTileEntity.CAPACITY));
	public static final RegistryObject<Item> COPPER_ORE_ITEM = register(COPPER_ORE, "copper_ore");
	public static final RegistryObject<Item> ALLOY_FURNACE_ITEM = register(ALLOY_FURNACE, "alloy_furnace", () -> "Combines two metals to create alloys.", null, () -> "Energy usage: " + ElectricityHelper.toFE(AlloyFurnaceTileEntity.ENERGY_PER_TICK) + "/t\nProcessing time: " + AlloyFurnaceTileEntity.PROCESSING_TIME + "t\nEnergy needed per item: " + ElectricityHelper.toFE(AlloyFurnaceTileEntity.PROCESSING_TIME * AlloyFurnaceTileEntity.ENERGY_PER_TICK) + "\nCapacity: " + ElectricityHelper.toFE(AlloyFurnaceTileEntity.CAPACITY));
	public static final RegistryObject<Item> GEOTHERMAL_GENERATOR_ITEM = register(GEOTHERMAL_GENERATOR, "geothermal_generator", () -> "Generates energy out of the heat of the earth.", null, () -> "Energy generation at y=1: " + ElectricityHelper.toFE(GeothermalGeneratorTileEntity.getEnergyForY(1)) + "/t\nCapacity: " + ElectricityHelper.toFE(GeothermalGeneratorTileEntity.CAPACITY));
	public static final RegistryObject<Item> COPPER_SOLAR_PANEL_GENERATOR_ITEM = register(COPPER_SOLAR_PANEL_GENERATOR, "copper_solar_panel_generator", () -> "Generates energy out of the sun.", null, () -> "Energy generation: " + ElectricityHelper.toFE(CopperSolarPanelGeneratorBlock.ENERGY_PER_TICK) + "/t\nCapacity: " + ElectricityHelper.toFE(CopperSolarPanelGeneratorBlock.CAPACITY));
	public static final RegistryObject<Item> STEEL_SOLAR_PANEL_GENERATOR_ITEM = register(STEEL_SOLAR_PANEL_GENERATOR, "steel_solar_panel_generator", () -> "Generates energy out of the sun.", null, () -> "Energy generation: " + ElectricityHelper.toFE(SteelSolarPanelGeneratorBlock.ENERGY_PER_TICK) + "/t\nCapacity: " + ElectricityHelper.toFE(SteelSolarPanelGeneratorBlock.CAPACITY));
	public static final RegistryObject<Item> ELECTROLYSIS_MACHINE_ITEM = register(ELECTROLYSIS_MACHINE, "electrolysis_machine", () -> "Converts water and FE to hydrogen and oxygen.", null,() -> "Energy usage: " + ElectricityHelper.toFE(ElectrolysisMachineTileEntity.ENERGY_PER_TICK) + "/t\nEnergy needed per bucket hydrogen: " + ElectricityHelper.toFE(1000 / ElectrolysisMachineTileEntity.HYDROGEN_PER_TICK * ElectrolysisMachineTileEntity.ENERGY_PER_TICK) + "\nCapacity: " + ElectricityHelper.toFE(ElectrolysisMachineTileEntity.CAPACITY));
	public static final RegistryObject<Item> STEEL_PIPE_ITEM = register(STEEL_PIPE, "steel_pipe");
	public static final RegistryObject<Item> HYDROGEN_TNT_ITEM = register(HYDROGEN_TNT, "hydrogen_tnt", () -> "Can be used for more efficient mining.", null, () -> "Explosion Radius: " + HydrogenTNTEntity.EXPLOSION_RADIUS);
	public static final RegistryObject<Item> COPPER_LASER_TRANSMITTER_ITEM = register(COPPER_LASER_TRANSMITTER, "copper_laser_transmitter", () -> "Sends dangerous lasers to hurt mobs or transfer FE.", null, () -> "Energy sending per tick: " + ElectricityHelper.toFE(CopperLaserTransmitterTileEntity.ENERGY_PER_TICK) + "\nCapacity: " + ElectricityHelper.toFE(CopperLaserTransmitterTileEntity.CAPACITY));
	public static final RegistryObject<Item> COPPER_LASER_RECEIVER_ITEM = register(COPPER_LASER_RECEIVER, "copper_laser_receiver", () -> "Receives energy from lasers.", null, () -> "Energy receiving per tick: " + ElectricityHelper.toFE(CopperLaserReceiverTileEntity.ENERGY_PER_TICK) + "\nCapacity: " + ElectricityHelper.toFE(CopperLaserReceiverTileEntity.CAPACITY));
	public static final RegistryObject<Item> URANIUM_ORE_ITEM = register(URANIUM_ORE, "uranium_ore");
	public static final RegistryObject<Item> STEEL_BLOCK_ITEM = register(STEEL_BLOCK, "steel_block");
	public static final RegistryObject<Item> PURIFIER_ITEM = register(PURIFIER, "purifier", null, null, () -> "Energy usage: " + ElectricityHelper.toFE(PurifierTileEntity.ENERGY_PER_TICK) + "/t\nProcessing time: " + PurifierTileEntity.PROCESSING_TIME + "t\nEnergy needed per item: " + ElectricityHelper.toFE(PurifierTileEntity.PROCESSING_TIME * PurifierTileEntity.ENERGY_PER_TICK) + "\nCapacity: " + ElectricityHelper.toFE(PurifierTileEntity.CAPACITY));
	public static final RegistryObject<Item> FISSION_CONTROLLER_ITEM = register(FISSION_CONTROLLER, "fission_controller", () -> "The base of the fission reactor. Uses enriched uranium as fuel.", null, () -> "Energy generation: " + ElectricityHelper.toFE(FissionControllerTileEntity.ENERGY_PER_TICK) + "/t\nUranium processing time: " + FissionControllerTileEntity.PROCESSING_TIME + "t\nEnergy per uranium: " + ElectricityHelper.toFE(FissionControllerTileEntity.PROCESSING_TIME * FissionControllerTileEntity.ENERGY_PER_TICK));
	public static final RegistryObject<Item> FISSION_HEAT_GENERATOR_ITEM = register(FISSION_HEAT_GENERATOR, "fission_heat_generator", () -> "A part of the the fission reactor", null, () -> "Capacity: " + ElectricityHelper.toFE(FissionHeatGeneratorTileEntity.CAPACITY));
}
