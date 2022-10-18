package com.mrasare.fungusforage.network;

import com.mrasare.fungusforage.data.Research;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class ClientBoundPropertyMessage {
    private final Research.Shrooms shroom;
    private final String name;
    private final int value;
    public ClientBoundPropertyMessage(Research.Shrooms shroomName,String name, int value){
        this.shroom = shroomName;
        this.name = name;
        this.value = value;
    }




    public static void encode(ClientBoundPropertyMessage message, PacketBuffer buffer){
        buffer.writeEnumValue(message.shroom);
        buffer.writeString(message.name);
        buffer.writeInt(message.value);
    }

    public static ClientBoundPropertyMessage decode(PacketBuffer buffer){
        return new ClientBoundPropertyMessage(buffer.readEnumValue(Research.Shrooms.class),buffer.readString(),buffer.readInt());
    }

    public static void handle(ClientBoundPropertyMessage message, Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();

//        context.enqueueWork(() -> {
//            DistExecutor.unsafeRunWhenOn(Dist.CLIENT,
//                    () -> () -> ClientOnlyResearch.updateClientPropertySingle(message.shroom,message.name,message.value));
//        });

        context.setPacketHandled(true);
    }
}
