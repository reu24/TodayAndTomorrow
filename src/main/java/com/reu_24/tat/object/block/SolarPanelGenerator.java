package com.reu_24.tat.object.block;

import net.minecraft.block.Block;
import net.minecraft.util.math.shapes.VoxelShape;

public interface SolarPanelGenerator {
    VoxelShape SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D);

    String registryName();
    int getCapacity();
    int getMaxExtract();
    int getEnergyPerTick();
}
