package com.mrasare.fungusforage;

import com.mrasare.fungusforage.setup.init.BlockInit;
import com.mrasare.fungusforage.setup.ClientSetup;
import com.mrasare.fungusforage.setup.ModSetup;
import com.mrasare.fungusforage.setup.init.ItemInit;
import com.mrasare.fungusforage.setup.init.TileEntityInit;
import com.mrasare.fungusforage.setup.init.SoundInit;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(FungusForage.MODID)
public class FungusForage
{
    public static final String MODID = "fungusforage";

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();



    public FungusForage() {
        // Register the setup method for modloading
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();


        BlockInit.init();
        ItemInit.init();
        TileEntityInit.init();
        SoundInit.init();

        eventBus.addListener(ModSetup::init);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT,()->()-> {
            eventBus.addListener(ClientSetup::init);
        });

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }




}
