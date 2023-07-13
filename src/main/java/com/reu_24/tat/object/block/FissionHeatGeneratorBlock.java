package com.reu_24.tat.object.block;

import com.reu_24.tat.init.ModTileEntityTypes;
import com.reu_24.tat.tilentity.FissionHeatGeneratorTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class FissionHeatGeneratorBlock extends GuiBlock<FissionHeatGeneratorTileEntity> {

    public FissionHeatGeneratorBlock(Properties properties) {
        super(properties);
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return ModTileEntityTypes.FISSION_HEAT_GENERATOR.get().create();
    }
}
