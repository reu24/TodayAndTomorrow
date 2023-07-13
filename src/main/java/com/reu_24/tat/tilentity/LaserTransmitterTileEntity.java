package com.reu_24.tat.tilentity;

import com.mojang.datafixers.util.Pair;
import com.reu_24.tat.container.LaserTransmitterContainer;
import com.reu_24.tat.init.DamageSourceInit;
import com.reu_24.tat.init.ModContainerTypes;
import com.reu_24.tat.object.block.OrientalGuiBlock;
import com.reu_24.tat.util.BaseItemHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.List;

public abstract class LaserTransmitterTileEntity extends ElectricityGuiTileEntity<BaseItemHandler> {

    protected List<Pair<Integer, Float>> beamLengthRadius = new ArrayList<>();
    protected boolean alwaysActive = true;

    public LaserTransmitterTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);

        inventory = new BaseItemHandler(2);
    }

    @Override
    protected boolean canReceive() {
        return true;
    }

    @Override
    protected int getMaxExtract() {
        return 0;
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        alwaysActive = compound.getBoolean("AlwaysActive");
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        compound.putBoolean("AlwaysActive", alwaysActive);
        return compound;
    }

    @Override
    public void tick() {
        if (isShootingLaser()) {
            int energyAvailable = getEnergyPerTick();
            if (!world.isRemote()) {
                energy.extractEnergyRaw(energyAvailable, false);
            }
            Direction facing = getDirection();
            BlockPos rayPos = new BlockPos(pos.getX(), pos.getY(), pos.getZ());

            List<Entity> hurtEntities = new ArrayList<>();

            beamLengthRadius.clear();
            beamLengthRadius.add(new Pair<>(0, 1.0f));
            for (int laserLength = 0; laserLength < 500; laserLength++) {
                rayPos = rayPos.add(facing.getDirectionVec());

                boolean hasHurt = false;
                List<Entity> entitiesToDamage = world.getEntitiesWithinAABBExcludingEntity(null, new AxisAlignedBB(rayPos, rayPos.add(1, 1, 1)));
                for (Entity entityToDamage : entitiesToDamage) {
                    if (entityToDamage instanceof LivingEntity && !hurtEntities.contains(entityToDamage)) {
                        hurtEntity((LivingEntity) entityToDamage);
                        hurtEntities.add(entityToDamage);
                        energyAvailable -= getEnergyReductionPerEntity();
                        hasHurt = true;
                    }
                }
                if (energyAvailable <= 0) {
                    break;
                }
                if (hasHurt) {
                    beamLengthRadius.add(new Pair<>(0, (float)energyAvailable / (float)getEnergyPerTick()));
                }

                BlockState block = world.getBlockState(rayPos);
                if (block.getOpacity(this.world, rayPos) >= 15 && block.getBlock() != Blocks.BEDROCK) {
                    TileEntity tileEntity = world.getTileEntity(rayPos);
                    if (tileEntity instanceof LaserReceiverTileEntity) {
                        ((LaserReceiverTileEntity) tileEntity).receive(facing.getOpposite(), energyAvailable);
                    }

                    break;
                }

                Pair<Integer, Float> lastElement = beamLengthRadius.get(beamLengthRadius.size() - 1);
                beamLengthRadius.set(beamLengthRadius.size() - 1, new Pair<>(lastElement.getFirst() + 1, lastElement.getSecond()));
            }
        } else {
            beamLengthRadius.clear();
        }

        super.tick();
    }

    protected void hurtEntity(LivingEntity entity) {
        if (!world.isRemote()) {
            entity.attackEntityFrom(DamageSourceInit.LASER, getDamage());
        }
    }

    public Direction getDirection() {
        return ((OrientalGuiBlock)getBlockState().getBlock()).getFacing(getBlockState());
    }

    @OnlyIn(Dist.CLIENT)
    public List<Pair<Integer, Float>> getBeamLengthRadius() {
        return beamLengthRadius;
    }

    public boolean isShootingLaser() {
        return energy.getEnergyStored() >= getEnergyPerTick() &&
                (alwaysActive || world.isBlockPowered(pos));
    }

    public boolean isAlwaysActive() {
        return alwaysActive;
    }

    public void setAlwaysActive(boolean alwaysActive) {
        this.alwaysActive = alwaysActive;
        markDirty();
    }

    @Override
    public Container createMenu(int windowId, PlayerInventory playerInventory, PlayerEntity player) {
        return new LaserTransmitterContainer(windowId, playerInventory, this, ModContainerTypes.LASER_TRANSMITTER);
    }

    @OnlyIn(Dist.CLIENT)
    public double getMaxRenderDistanceSquared() {
        return 65536.0D;
    }

    @OnlyIn(Dist.CLIENT)
    public AxisAlignedBB getRenderBoundingBox()
    {
        return INFINITE_EXTENT_AABB;
    }

    public abstract int getEnergyPerTick();
    public abstract float getDamage();
    public abstract int getEnergyReductionPerEntity();
}
