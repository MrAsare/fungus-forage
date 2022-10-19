package com.mrasare.fungusforage.network;

import com.mrasare.fungusforage.data.Research;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.function.Supplier;

public class ClientBoundFullSync {

        public EnumMap<Research.Mushrooms,Boolean> map;
        public ClientBoundFullSync(EnumMap<Research.Mushrooms,Boolean> map){
            this.map = map;
        }



        public static void encode(ClientBoundFullSync message, PacketBuffer buffer){
            message.map.keySet().forEach(key-> {
                buffer.writeEnumValue(key);
                buffer.writeBoolean(message.map.get(key));
            });
        }

        public static ClientBoundFullSync decode(PacketBuffer buffer){
            EnumMap<Research.Mushrooms,Boolean> newMap = new EnumMap<>(Research.Mushrooms.class);
            Arrays.stream(Research.Mushrooms.values())
                    .forEach(shroomName-> {
                        newMap.put(buffer.readEnumValue(Research.Mushrooms.class),buffer.readBoolean());
                    });
            return new ClientBoundFullSync(newMap);
        }

        public static void handle(ClientBoundFullSync message, Supplier<NetworkEvent.Context> supplier){
            NetworkEvent.Context context = supplier.get();

            context.enqueueWork(() -> {
                DistExecutor.unsafeRunWhenOn(Dist.CLIENT,
                        () -> () -> ClientOnlyResearch.updateClientResearch(message.map));
            });

            context.setPacketHandled(true);
        }
    }
