package com.reu_24.tat.object.block;

import com.reu_24.tat.init.BlockInit;
import com.reu_24.tat.init.ModTileEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;

public class SteelPipeBlock extends SixWayConnectionBlock {

    public SteelPipeBlock(Properties properties) {
        super(properties);
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return ModTileEntityTypes.STEEL_PIPE.get().create();
    }

    @Override
    public boolean canConnect(BlockState state, TileEntity tileEntity, Direction direction) {
        if (state.getBlock() == BlockInit.STEEL_PIPE.get()) {
            return true;
        }
        if (tileEntity == null) {
            return false;
        }
        return tileEntity.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, direction).isPresent();
    }
}
