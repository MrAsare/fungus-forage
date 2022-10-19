package com.mrasare.fungusforage.network;

import com.mrasare.fungusforage.data.Research;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class ServerBoundPropertyMessage {

    private final Research.Mushrooms shroomName;
    private final String name;
    private final int value;
    public ServerBoundPropertyMessage(Research.Mushrooms shroomName, String name, int value){
        this.shroomName = shroomName;
        this.name = name;
        this.value = value;
    }

    public static void encode(ServerBoundPropertyMessage message, PacketBuffer buffer){
        buffer.writeEnumValue(message.shroomName);
        buffer.writeString(message.name);
        buffer.writeInt(message.value);
    }

    public static ServerBoundPropertyMessage decode(PacketBuffer buffer){
        return new ServerBoundPropertyMessage(buffer.readEnumValue(Research.Mushrooms.class),buffer.readString(),buffer.readInt());
    }

    public static void handle(ServerBoundPropertyMessage message, Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();

//        context.enqueueWork(()-> {
//                    context.getSender().getCapability(ResearchStorage.RESEARCH_CAPABILITY).ifPresent(iResearch ->{
//                                iResearch.setPropertyValue(message.shroomName,message.name ,message.value);
//                                FungusForageNetwork.CHANNEL.send(PacketDistributor.PLAYER.with(context::getSender), new ClientBoundPropertyMessage(message.shroomName, message.name,message.value));
//                            }
//                    );
//                }
//        );

        context.setPacketHandled(true);
    }




}
