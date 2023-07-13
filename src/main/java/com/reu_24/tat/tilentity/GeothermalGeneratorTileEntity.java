package com.reu_24.tat.tilentity;

import com.reu_24.tat.container.CoalGeneratorContainer;
import com.reu_24.tat.container.GeothermalGeneratorContainer;
import com.reu_24.tat.init.ModContainerTypes;
import com.reu_24.tat.init.ModTileEntityTypes;
import com.reu_24.tat.util.BaseItemHandler;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;

public class GeothermalGeneratorTileEntity extends ElectricityGuiTileEntity<BaseItemHandler> {

    public static final int CAPACITY = 50000;

    public GeothermalGeneratorTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);

        inventory = new BaseItemHandler(0);
    }

    public GeothermalGeneratorTileEntity() {
        this(ModTileEntityTypes.GEOTHERMAL_GENERATOR.get());
    }

    @Override
    protected boolean canReceive() {
        return false;
    }

    @Override
    protected int getCapacity() {
        return CAPACITY;
    }

    @Override
    protected int getMaxExtract() {
        return 70;
    }

    @Override
    public void tick() {
        super.tick();
        energy.receiveEnergyRaw(calculateEnergyProduction(), false);
    }

    protected int calculateEnergyProduction() {
        int y = pos.getY() - 1;
        while (world.getFluidState(new BlockPos(pos.getX(), y--, pos.getZ())).isSource());
        if (world.getBlockState(new BlockPos(pos.getX(), ++y, pos.getZ())).getBlock() == Blocks.SOUL_SAND &&
            y != pos.getY() - 1) {
            setLit(true);
            return getEnergyForY(y);
        }
        setLit(false);
        return 0;
    }

    public static int getEnergyForY(int y) {
        return (255 - y) / 50;
    }

    @Override
    protected String getRegistryName() {
        return "geothermal_generator";
    }

    @Override
    public Container createMenu(int windowId, PlayerInventory playerInventory, PlayerEntity player) {
        return new GeothermalGeneratorContainer(windowId, playerInventory, this, ModContainerTypes.GEOTHERMAL_GENERATOR);
    }

    public boolean isProducing() {
        return calculateEnergyProduction() > 0;
    }
}
