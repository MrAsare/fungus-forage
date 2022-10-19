package com.mrasare.fungusforage.network;

import com.mrasare.fungusforage.data.Research;
import com.mrasare.fungusforage.setup.init.BlockInit;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.function.Supplier;

public class ClientBoundFullPropertySync {
    public EnumMap<Research.Mushrooms, HashMap<String,Integer>> map;

    public ClientBoundFullPropertySync(EnumMap<Research.Mushrooms,HashMap<String,Integer>> map){
        this.map = map;
    }



    public static void encode(ClientBoundFullPropertySync message, PacketBuffer buffer){
//        message.map.keySet().forEach(key-> {
//            buffer.writeEnumValue(key);
//            BlockInit.SHROOM_LIST.get(key).forEach(integerProperty -> {
//                buffer.writeString(integerProperty.getName());
//
//                buffer.writeInt(message.map.get(key).get(integerProperty.getName()));
//            });
//        });
    }

    public static ClientBoundFullPropertySync decode(PacketBuffer buffer){
        EnumMap<Research.Mushrooms,HashMap<String,Integer>> newMap = new EnumMap<>(Research.Mushrooms.class);
//        Arrays.stream(Research.Mushrooms.values())
//                .forEach(shroomName-> {
//                    newMap.put(buffer.readEnumValue(Research.Mushrooms.class),new HashMap<>());
//
//                    BlockInit.SHROOM_LIST.get(shroomName).forEach(integerProperty -> {
//                        newMap.get(shroomName).put(buffer.readString(),buffer.readInt());
//                    });
//                });
        return new ClientBoundFullPropertySync(newMap);
    }

    public static void handle(ClientBoundFullPropertySync message, Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();

//        context.enqueueWork(() -> {
//            DistExecutor.unsafeRunWhenOn(Dist.CLIENT,
//                    () -> () -> ClientOnlyResearch.updateClientPropertyList(message.map));
//        });

        context.setPacketHandled(true);
    }
}
