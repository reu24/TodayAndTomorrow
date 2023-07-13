package com.reu_24.tat.util;

import com.reu_24.tat.tilentity.LaserTransmitterTileEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

import java.util.function.Supplier;

public class TatPacketHandler {

    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel LASER_CHANNEL = NetworkRegistry.newSimpleChannel(
            new ModResourceLocation("laser_transmitter"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals);

    private static int id = 0;

    public static class LaserPacket {

        BlockPos pos;
        boolean alwaysActive;

        public LaserPacket(PacketBuffer buffer) {
            this(buffer.readBlockPos(), buffer.readBoolean());
        }

        public LaserPacket(BlockPos pos, boolean alwaysActive) {
            this.pos = pos;
            this.alwaysActive = alwaysActive;
        }

        public void encode(PacketBuffer packetBuffer) {
            packetBuffer.writeBlockPos(pos);
            packetBuffer.writeBoolean(alwaysActive);
        }

        public void handle(Supplier<NetworkEvent.Context> ctx) {
            ctx.get().enqueueWork(() -> {
                World world = ctx.get().getSender().world;
                if (!world.isRemote()) {
                    LaserTransmitterTileEntity tileEntity = (LaserTransmitterTileEntity)world.getTileEntity(pos);
                    tileEntity.setAlwaysActive(alwaysActive);
                }
            });
            ctx.get().setPacketHandled(true);
        }
    }

    public static void register() {
        LASER_CHANNEL.registerMessage(id++, LaserPacket.class, LaserPacket::encode, LaserPacket::new, LaserPacket::handle);
    }
}
