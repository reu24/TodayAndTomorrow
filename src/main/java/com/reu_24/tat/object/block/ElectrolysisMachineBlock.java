package com.reu_24.tat.object.block;

import com.reu_24.tat.init.ModTileEntityTypes;
import com.reu_24.tat.tilentity.ElectrolysisMachineTileEntity;
import com.reu_24.tat.util.BaseFluidTank;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import java.util.Random;

public class ElectrolysisMachineBlock extends LitGuiBlock<ElectrolysisMachineTileEntity> {

    public ElectrolysisMachineBlock(Properties properties) {
        super(properties);
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return ModTileEntityTypes.ELECTROLYSIS_MACHINE.get().create();
    }

    @Override
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        Item currentItem = player.inventory.mainInventory.get(player.inventory.currentItem).getItem();
        if (!worldIn.isRemote() && currentItem instanceof BucketItem && ((BucketItem)currentItem).getFluid() == Fluids.WATER) {
            BaseFluidTank water = ((ElectrolysisMachineTileEntity) worldIn.getTileEntity(pos)).getWater();
            if (water.getSpace() > 0) {
                water.addAmount(1000);
                worldIn.playSound(player, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                if (!player.abilities.isCreativeMode) {
                    player.inventory.mainInventory.set(player.inventory.currentItem, new ItemStack(Items.BUCKET));
                }
            }
            return ActionResultType.SUCCESS;
        }
        return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
    }
}