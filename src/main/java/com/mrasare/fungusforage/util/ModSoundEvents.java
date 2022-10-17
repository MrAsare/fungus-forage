package com.mrasare.fungusforage.util;

import com.mrasare.fungusforage.FungusForage;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModSoundEvents {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, FungusForage.MODID);

    public static final RegistryObject<SoundEvent> MUSHROOM_KNIFE_SLASH = registerSoundEvents("mushroom_knife_slash");

    public static void register(IEventBus eventBus){
        SOUND_EVENTS.register(eventBus);
    }

    public static RegistryObject<SoundEvent> registerSoundEvents(String name){
        return SOUND_EVENTS.register(name,()->new SoundEvent(new ResourceLocation(FungusForage.MODID,name)));
    }

}
