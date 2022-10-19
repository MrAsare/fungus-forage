package com.mrasare.fungusforage.setup.init;

import com.mrasare.fungusforage.FungusForage;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SoundInit {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, FungusForage.MODID);

    public static final RegistryObject<SoundEvent> MUSHROOM_KNIFE_SLASH = registerSoundEvents("mushroom_knife_slash");

    public static void init(){
        SOUND_EVENTS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static RegistryObject<SoundEvent> registerSoundEvents(String name){
        return SOUND_EVENTS.register(name,()->new SoundEvent(new ResourceLocation(FungusForage.MODID,name)));
    }

}
