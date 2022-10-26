package com.mrasare.fungusforage.world;

import com.mrasare.fungusforage.FungusForage;
import com.mrasare.fungusforage.world.gen.ModMushroomGeneration;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = FungusForage.MODID)
public class ModWorldEvents {
    @SubscribeEvent
    public static void biomeLoadingEvent(final BiomeLoadingEvent event){
        ModMushroomGeneration.generateMushrooms(event);
    }

}
