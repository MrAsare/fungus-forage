package com.mrasare.fungusforage.network;

import com.mrasare.fungusforage.data.Research;
import com.mrasare.fungusforage.data.ResearchStorage;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.PacketDistributor;

import java.util.function.Supplier;

public class ServerBoundInputMessage {

    private final Research.Shrooms shroomName;
    private final boolean state;
    public ServerBoundInputMessage(Research.Shrooms shroomName, boolean state){
        this.shroomName = shroomName;
        this.state = state;
    }

    public static void encode(ServerBoundInputMessage message, PacketBuffer buffer){
        buffer.writeEnumValue(message.shroomName);
        buffer.writeBoolean(message.state);
    }

    public static ServerBoundInputMessage decode(PacketBuffer buffer){
        return new ServerBoundInputMessage(buffer.readEnumValue(Research.Shrooms.class),buffer.readBoolean());
    }

    public static void handle(ServerBoundInputMessage message, Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();

        context.enqueueWork(()-> {
            context.getSender().getCapability(ResearchStorage.RESEARCH_CAPABILITY).ifPresent(iResearch ->{
                iResearch.setShroomState(message.shroomName, message.state);
                FungusForageNetwork.CHANNEL.send(PacketDistributor.PLAYER.with(context::getSender), new ClientBoundInputMessage(message.shroomName, message.state));
            }
            );
        }
    );

        context.setPacketHandled(true);
    }





}
