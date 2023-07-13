package com.reu_24.tat.object.block;

import com.reu_24.tat.init.ModTileEntityTypes;
import com.reu_24.tat.tilentity.FissionControllerTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import java.util.Random;

public class FissionControllerBlock extends LitGuiBlock<FissionControllerTileEntity> {

    public FissionControllerBlock(Properties properties) {
        super(properties);
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return ModTileEntityTypes.FISSION_CONTROLLER.get().create();
    }

    @Override
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
    }
}
