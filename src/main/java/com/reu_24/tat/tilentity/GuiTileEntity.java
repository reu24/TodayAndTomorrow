package com.reu_24.tat.tilentity;

import com.reu_24.tat.TodayAndTomorrow;
import com.reu_24.tat.object.block.LitGuiBlock;
import com.reu_24.tat.util.BaseItemHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;

public abstract class GuiTileEntity<I extends BaseItemHandler> extends TileEntity implements INamedContainerProvider {

    protected ITextComponent customName;
    protected I inventory;

    public static final String CUSTOM_NAME = "CustomName";

    public GuiTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    @Override
    public ITextComponent getDisplayName() {
        return getName();
    }

    public void setCustomName(ITextComponent name) {
        customName = name;
    }

    public ITextComponent getName() {
        return customName != null ? customName : getDefaultName();
    }

    private ITextComponent getDefaultName() {
        return new TranslationTextComponent("container." + TodayAndTomorrow.MOD_ID + "." + getRegistryName());
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        if (compound.contains(CUSTOM_NAME, Constants.NBT.TAG_STRING)) {
            customName = ITextComponent.Serializer.fromJson(compound.getString(CUSTOM_NAME));
        }
        NonNullList<ItemStack> inv = NonNullList.withSize(inventory.getSlots(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, inv);
        inventory.setNonNullList(inv);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        if (customName != null) {
            compound.putString("CustomName", ITextComponent.Serializer.toJson(this.customName));
        }

        ItemStackHelper.saveAllItems(compound, inventory.toNonNullList());

        return compound;
    }

    public void writeGuiTileEntity(CompoundNBT compound) {
        write(compound);
    }

    public void readGuiTileEntity(CompoundNBT compound) {
        this.read(compound);
    }

    public I getInventory() {
        return inventory;
    }

    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        CompoundNBT nbt = new CompoundNBT();
        this.write(nbt);
        return new SUpdateTileEntityPacket(this.pos, 0, nbt);
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        this.read(pkt.getNbtCompound());
    }

    @Override
    public CompoundNBT getUpdateTag() {
        CompoundNBT nbt = new CompoundNBT();
        this.write(nbt);
        return nbt;
    }

    @Override
    public void handleUpdateTag(CompoundNBT nbt) {
        this.read(nbt);
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.orEmpty(cap, LazyOptional.of(() -> inventory));
    }

    @Override
    public Container createMenu(int windowId, PlayerInventory playerInventory, PlayerEntity player) {
        return createMenu(windowId, playerInventory, player);
    }

    protected void setLit(boolean lit) {
        if (getBlockState().getBlock() instanceof LitGuiBlock) {
            world.setBlockState(getPos(), getBlockState().with(LitGuiBlock.LIT, lit));
        }
    }

    public void markDirty() {
        super.markDirty();
        world.notifyBlockUpdate(getPos(), getBlockState(), getBlockState(), Constants.BlockFlags.BLOCK_UPDATE);
    }

    protected abstract String getRegistryName();
}
