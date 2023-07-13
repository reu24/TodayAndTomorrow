package com.reu_24.tat.object.block;

import com.reu_24.tat.init.BlockInit;
import com.reu_24.tat.init.ModTileEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.energy.CapabilityEnergy;

public class CopperWireBlock extends SixWayConnectionBlock {

    public CopperWireBlock(Properties properties) {
        super(properties);
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return ModTileEntityTypes.COPPER_WIRE.get().create();
    }

    public boolean canConnect(BlockState state, TileEntity tileEntity, Direction direction) {
        if (state.getBlock() == BlockInit.COPPER_WIRE.get()) {
            return true;
        }
        if (tileEntity == null) {
            return false;
        }
        return tileEntity.getCapability(CapabilityEnergy.ENERGY, direction).isPresent();
    }
}
