package com.reu_24.tat.entity;

import com.reu_24.tat.init.ModEntityTypes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.network.IPacket;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class HydrogenTNTEntity extends TNTEntity {

    protected LivingEntity tntPlacedBy;

    public static final float EXPLOSION_RADIUS = 10.0f;

    public HydrogenTNTEntity(EntityType<? extends TNTEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public HydrogenTNTEntity(World worldIn, double x, double y, double z, LivingEntity igniter) {
        this(ModEntityTypes.HYDROGEN_TNT.get(), worldIn);
        this.setPosition(x, y, z);
        double d0 = worldIn.rand.nextDouble() * (double)((float)Math.PI * 2F);
        this.setMotion(-Math.sin(d0) * 0.02D, (double)0.2F, -Math.cos(d0) * 0.02D);
        this.setFuse(80);
        this.prevPosX = x;
        this.prevPosY = y;
        this.prevPosZ = z;
        this.tntPlacedBy = igniter;
    }

    @Override
    protected void explode() {
        this.world.createExplosion(this, this.getPosX(), this.getPosYHeight(0.0625D), this.getPosZ(), EXPLOSION_RADIUS, Explosion.Mode.BREAK);
    }

    public LivingEntity getTntPlacedBy() {
        return this.tntPlacedBy;
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
