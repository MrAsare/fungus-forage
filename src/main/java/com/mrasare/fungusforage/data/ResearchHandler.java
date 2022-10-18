package com.mrasare.fungusforage.data;

import com.mrasare.fungusforage.FungusForage;
import com.mrasare.fungusforage.network.ClientBoundFullPropertySync;
import com.mrasare.fungusforage.network.ClientBoundFullSync;
import com.mrasare.fungusforage.network.FungusForageNetwork;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.PacketDistributor;


@Mod.EventBusSubscriber( bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ResearchHandler
{
    public static final ResourceLocation RESEARCH = new ResourceLocation(FungusForage.MODID, "research");



    @SubscribeEvent
    public static void attachCapability(AttachCapabilitiesEvent<Entity> event) {
        if(event.getObject() instanceof PlayerEntity) {
            ResearchProvider provider = new ResearchProvider();
            event.addCapability(RESEARCH, provider);
            event.addListener(provider::invalidate);
        }
    }

    @SubscribeEvent
    public static void PlayerLogin(EntityJoinWorldEvent event){
        if((!event.getWorld().isRemote )&&event.getEntity() instanceof ServerPlayerEntity) {
            ServerPlayerEntity entity = (ServerPlayerEntity) event.getEntity();

            entity.getCapability(ResearchStorage.RESEARCH_CAPABILITY).ifPresent(iResearch ->
                    {
                        FungusForageNetwork.CHANNEL.send(PacketDistributor.PLAYER.with(() -> entity), new ClientBoundFullSync(iResearch.getBoolList()));
//                        FungusForageNetwork.CHANNEL.send(PacketDistributor.PLAYER.with(()->entity),new ClientBoundFullPropertySync(iResearch.getPropertiesMap()));

                    }
            );
        }
    }


    @SubscribeEvent
    public static void PlayerDeath(PlayerEvent.Clone cloneEvent){
        if(cloneEvent.isWasDeath()){
            cloneEvent.getOriginal().getCapability(ResearchStorage.RESEARCH_CAPABILITY).ifPresent(oldPl->{
                cloneEvent.getPlayer().getCapability(ResearchStorage.RESEARCH_CAPABILITY).ifPresent(newPl->{
                    newPl.copyBoolListFrom(oldPl);
//                    newPl.copyPropertiesListFrom(oldPl);
                });
            });
        }

    }







}