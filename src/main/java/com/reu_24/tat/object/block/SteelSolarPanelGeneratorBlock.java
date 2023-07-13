package com.reu_24.tat.object.block;

import com.reu_24.tat.init.ModTileEntityTypes;
import com.reu_24.tat.tilentity.SteelSolarPanelGeneratorTileEntity;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class SteelSolarPanelGeneratorBlock extends GuiBlock<SteelSolarPanelGeneratorTileEntity> implements SolarPanelGenerator {

    public static final int ENERGY_PER_TICK = 17;
    public static final int MAX_EXTRACT = 19;
    public static final int CAPACITY = 70000;

    public SteelSolarPanelGeneratorBlock(Properties properties) {
        super(properties);
    }

    @Override
    public String registryName() {
        return "steel_solar_panel_generator";
    }

    public int getCapacity() {
        return CAPACITY;
    }

    public int getMaxExtract() {
        return MAX_EXTRACT;
    }

    public int getEnergyPerTick() {
        return ENERGY_PER_TICK;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return ModTileEntityTypes.STEEL_SOLAR_PANEL_GENERATOR.get().create();
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    @Override
    public boolean isTransparent(BlockState state) {
        return true;
    }

    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        return 0;
    }
}
