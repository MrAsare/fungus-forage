package com.mrasare.fungusforage.network;

import com.mrasare.fungusforage.FungusForage;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class FungusForageNetwork {
    public static final String NETWORK_VERSION = "0.1.0";

    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(new ResourceLocation(FungusForage.MODID,"network"),()->NETWORK_VERSION,version->version.equals(NETWORK_VERSION),version->version.equals(NETWORK_VERSION));

    public static void init(){
        int index =0;
        CHANNEL.messageBuilder(ServerBoundInputMessage.class,index++,NetworkDirection.PLAY_TO_SERVER).encoder(ServerBoundInputMessage::encode).decoder(ServerBoundInputMessage::decode).consumer(ServerBoundInputMessage::handle).add();
        CHANNEL.messageBuilder(ClientBoundInputMessage.class,index++,NetworkDirection.PLAY_TO_CLIENT).encoder(ClientBoundInputMessage::encode).decoder(ClientBoundInputMessage::decode).consumer(ClientBoundInputMessage::handle).add();
        CHANNEL.messageBuilder(ClientBoundFullSync.class,index++,NetworkDirection.PLAY_TO_CLIENT).encoder(ClientBoundFullSync::encode).decoder(ClientBoundFullSync::decode).consumer(ClientBoundFullSync::handle).add();

        CHANNEL.messageBuilder(ServerBoundPropertyMessage.class,index++,NetworkDirection.PLAY_TO_SERVER).encoder(ServerBoundPropertyMessage::encode).decoder(ServerBoundPropertyMessage::decode).consumer(ServerBoundPropertyMessage::handle).add();
        CHANNEL.messageBuilder(ClientBoundPropertyMessage.class,index++,NetworkDirection.PLAY_TO_CLIENT).encoder(ClientBoundPropertyMessage::encode).decoder(ClientBoundPropertyMessage::decode).consumer(ClientBoundPropertyMessage::handle).add();
        CHANNEL.messageBuilder(ClientBoundFullPropertySync.class,index++,NetworkDirection.PLAY_TO_CLIENT).encoder(ClientBoundFullPropertySync::encode).decoder(ClientBoundFullPropertySync::decode).consumer(ClientBoundFullPropertySync::handle).add();

    }

}


