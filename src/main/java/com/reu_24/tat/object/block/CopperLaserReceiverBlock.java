package com.reu_24.tat.object.block;

import com.reu_24.tat.init.ModTileEntityTypes;
import com.reu_24.tat.tilentity.CopperLaserReceiverTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class CopperLaserReceiverBlock extends OrientalGuiBlock<CopperLaserReceiverTileEntity> {

    public CopperLaserReceiverBlock(Properties properties) {
        super(properties);
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return ModTileEntityTypes.COPPER_LASER_RECEIVER.get().create();
    }
}
