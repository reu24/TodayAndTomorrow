package com.reu_24.tat.object.block;

import com.reu_24.tat.init.ModTileEntityTypes;
import com.reu_24.tat.tilentity.CopperLaserTransmitterTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class CopperLaserTransmitterBlock extends OrientalGuiBlock<CopperLaserTransmitterTileEntity> {

    public CopperLaserTransmitterBlock(Properties properties) {
        super(properties);
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return ModTileEntityTypes.COPPER_LASER_TRANSMITTER.get().create();
    }
}
