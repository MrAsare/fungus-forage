package com.mrasare.fungusforage.setup;

import com.mrasare.fungusforage.FungusForage;
import com.mrasare.fungusforage.entity.custom.EntityMinishroom;
import com.mrasare.fungusforage.item.ModSpawnEggItem;
import com.mrasare.fungusforage.setup.init.EntityInit;
import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = FungusForage.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void addEntityAttributes(EntityAttributeCreationEvent event){
        event.put(EntityInit.MINISHROOM.get(), EntityMinishroom.setCustomAttributes().create());
    }

    @SubscribeEvent
    public static void onRegisterEntities(RegistryEvent.Register<EntityType<?>> event){
        ModSpawnEggItem.initSpawnEggs();
    }

}
