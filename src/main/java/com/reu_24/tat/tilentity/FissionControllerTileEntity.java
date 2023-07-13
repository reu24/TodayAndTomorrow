package com.reu_24.tat.tilentity;

import com.reu_24.tat.TodayAndTomorrow;
import com.reu_24.tat.container.FissionControllerContainer;
import com.reu_24.tat.init.BlockInit;
import com.reu_24.tat.init.ModContainerTypes;
import com.reu_24.tat.init.ModTileEntityTypes;
import com.reu_24.tat.object.block.GuiBlock;
import com.reu_24.tat.util.BaseItemHandler;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;

public class FissionControllerTileEntity extends ProcessingElectricityTileEntity<BaseItemHandler> {

    protected boolean isProcessing = false;
    protected Direction direction;

    public static final int ENERGY_PER_TICK = 80;
    public static final int PROCESSING_TIME = 1000;

    public FissionControllerTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);

        inventory = new BaseItemHandler(1);
        this.energy = null;
    }

    public FissionControllerTileEntity() {
        this(ModTileEntityTypes.FISSION_CONTROLLER.get());
    }

    @Override
    protected int getCapacity() {
        return 0;
    }

    @Override
    protected int getMaxExtract() {
        return 0;
    }

    @Override
    public int getProcessingTime() {
        return PROCESSING_TIME;
    }

    @Override
    protected int getEnergyNeededPerTick() {
        return 0;
    }

    @Override
    protected int getOutputSlot() {
        return 0;
    }

    @Override
    protected boolean isRecipe(IRecipe<?> recipe) {
        return false;
    }

    @Override
    protected String getRegistryName() {
        return "fission_controller";
    }

    @Override
    protected boolean canReceive() {
        return false;
    }

    @Override
    protected void onStartProcessing() {
        inventory.decrStackSize(0, 1);
        isProcessing = true;
    }

    @Override
    protected void onProcessing() {
        if (energy == null) {
            TodayAndTomorrow.LOGGER.error("Tried to process in a fission reactor without an Energy Storage!");
        }
        energy.receiveEnergyRaw(ENERGY_PER_TICK, false);
    }

    @Override
    protected void onItemProcessed() {
        isProcessing = false;
    }

    @Override
    protected void onCanceledProcessing() {
        isProcessing = false;
    }

    @Override
    public Container createMenu(int windowId, PlayerInventory playerInventory, PlayerEntity player) {
        return new FissionControllerContainer(windowId, playerInventory, this, ModContainerTypes.FISSION_CONTROLLER);
    }

    @Override
    protected boolean shouldProcess() {
        return energy != null && ((energy.getMaxEnergyStored() > energy.getEnergyStored() && inventory.getStackInSlot(0).getCount() > 0)
                || isProcessing) && hasCorrectStructure();
    }

    @Override
    public void read(CompoundNBT compound) {
        readGuiTileEntity(compound);
        currentProcessingTime = compound.getInt(CURRENT_PROCESSING_TIME);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        writeGuiTileEntity(compound);
        compound.putInt(CURRENT_PROCESSING_TIME, currentProcessingTime);
        return compound;
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        LazyOptional<T> energyCap = CapabilityEnergy.ENERGY.orEmpty(cap, LazyOptional.of(() -> energy));
        if (energyCap.isPresent()) {
            return LazyOptional.empty();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void tick() {
        direction = getDirection();
        findHeatGenerator();
        try {
            super.tick();
        } catch (NullPointerException ignored) {
        }
    }

    protected void findHeatGenerator() {
        TileEntity heatTileEntity = world.getTileEntity(up(up(forwards(forwards(pos)))));
        if (heatTileEntity != null && heatTileEntity instanceof FissionHeatGeneratorTileEntity) {
            energy = ((FissionHeatGeneratorTileEntity)(heatTileEntity)).getEnergy();
        }
    }

    protected boolean hasCorrectStructure() {
        direction = getDirection();
        final Block steel = BlockInit.STEEL_BLOCK.get();
        final Block air = Blocks.AIR;
        final Block water = Blocks.WATER;
        final Block soulSand = Blocks.SOUL_SAND;
        final Block fissionHeatGenerator = Blocks.BLAST_FURNACE; // TODO

        return
                // 8 blocks surrounding controller
                blocksFrom(up(left(pos)), up(right(pos)), steel) &&
                blocksFrom(left(pos), down(left(pos)), steel) &&
                blocksFrom(down(pos), down(right(pos)), steel) &&
                blockAt(right(pos), steel) &&

                // 9 blocks left behind
                blocksFrom(up(left(left(forwards(pos)))), down(left(left(forwards(forwards(forwards(pos)))))), steel) &&
                // 9 blocks behind
                blocksFrom(up(left(forwards(forwards(forwards(forwards(pos)))))), down(right(forwards(forwards(forwards(forwards(pos)))))), steel) &&
                // 9 blocks right behind
                blocksFrom(up(right(right(forwards(pos)))), down(right(right(forwards(forwards(forwards(pos)))))), steel) &&
                // 9 blocks down behind
                blocksFrom(down(down(left(forwards(pos)))), down(down(right(forwards(forwards(forwards(pos)))))), steel);
    }

    protected boolean blockAt(BlockPos pos, Block block) {
        System.out.println(pos);
        return world.getBlockState(pos).getBlock() == block;
    }

    protected Direction getDirection() {
        return ((GuiBlock)getBlockState().getBlock()).getFacing(getBlockState());
    }

    protected BlockPos right(BlockPos pos) {
        switch (direction) {
            case NORTH:
                return pos.add(Direction.WEST.getDirectionVec());
            case EAST:
                return pos.add(Direction.NORTH.getDirectionVec());
            case SOUTH:
                return pos.add(Direction.EAST.getDirectionVec());
            case WEST:
                return pos.add(Direction.SOUTH.getDirectionVec());
        }
        throw new IllegalArgumentException();
    }

    protected BlockPos left(BlockPos pos) {
        switch (direction) {
            case NORTH:
                return pos.add(Direction.EAST.getDirectionVec());
            case EAST:
                return pos.add(Direction.SOUTH.getDirectionVec());
            case SOUTH:
                return pos.add(Direction.WEST.getDirectionVec());
            case WEST:
                return pos.add(Direction.NORTH.getDirectionVec());
        }
        throw new IllegalArgumentException();
    }

    protected BlockPos up(BlockPos pos) {
        return pos.up();
    }

    protected BlockPos down(BlockPos pos) {
        return pos.down();
    }

    protected BlockPos forwards(BlockPos pos) {
        return pos.subtract(direction.getDirectionVec());
    }

    protected BlockPos backwards(BlockPos pos) {
        return pos.add(direction.getDirectionVec());
    }

    protected boolean blocksFrom(BlockPos from, BlockPos to, Block block) {

        if (from.getX() > to.getX()) {
            to = to.west();
        } else {
            to = to.east();
        }
        if (from.getZ() > to.getZ()) {
            to = to.north();
        } else {
            to = to.south();
        }
        if (from.getY() > to.getY()) {
            to = to.down();
        } else {
            to = to.up();
        }

        for (int x = from.getX(); x != to.getX();) {
            for (int y = from.getY(); y != to.getY();) {
                for (int z = from.getZ(); z != to.getZ();) {
                    if (!blockAt(new BlockPos(x, y, z), block)) {
                        return false;
                    }

                    if (from.getZ() > to.getZ()) {
                        z--;
                    } else {
                        z++;
                    }
                }

                if (from.getY() > to.getY()) {
                    y--;
                } else {
                    y++;
                }
            }

            if (from.getX() > to.getX()) {
                x--;
            } else {
                x++;
            }
        }
        return true;
    }
}
