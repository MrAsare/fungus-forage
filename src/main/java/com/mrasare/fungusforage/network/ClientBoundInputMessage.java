package com.mrasare.fungusforage.network;

import com.mrasare.fungusforage.data.Research;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class ClientBoundInputMessage {

    private Research.Shrooms shroom;
    private boolean state = false;

    public ClientBoundInputMessage(Research.Shrooms shroomName, boolean state){
        this.shroom = shroomName;
        this.state = state;
    }




    public static void encode(ClientBoundInputMessage message, PacketBuffer buffer){
        buffer.writeEnumValue(message.shroom) ;
        buffer.writeBoolean(message.state);
    }

    public static ClientBoundInputMessage decode(PacketBuffer buffer){
        return new ClientBoundInputMessage(buffer.readEnumValue(Research.Shrooms.class),buffer.readBoolean());
    }

    public static void handle(ClientBoundInputMessage message, Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();

        context.enqueueWork(() -> {
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT,
                    () -> () -> ClientOnlyResearch.updateClientResearch(message.shroom,message.state));
        });

        context.setPacketHandled(true);
    }
}
