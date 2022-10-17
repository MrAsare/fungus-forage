package com.mrasare.fungusforage.setup;

import com.mrasare.fungusforage.FungusForage;
import com.mrasare.fungusforage.data.Research;
import com.mrasare.fungusforage.data.ResearchStorage;
import com.mrasare.fungusforage.gui.ShroomBookScreen;
import com.mrasare.fungusforage.gui.ShroomSelect;
import com.mrasare.fungusforage.network.FungusForageNetwork;
import com.mrasare.fungusforage.network.ServerBoundInputMessage;
import com.mrasare.fungusforage.network.ServerBoundPropertyMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = FungusForage.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ClientSetup {




    @SubscribeEvent
    public static void init(final FMLClientSetupEvent event) {
    }




    public static void showBookScreen(Screen scr)
    {
        Minecraft.getInstance().displayGuiScreen(scr);
    }

    public static void showBookScreen()
    {
        Minecraft.getInstance().displayGuiScreen(new ShroomSelect());
    }


    public static void showBookScreen(Research.Shrooms shroom)
    {
        Minecraft.getInstance().player.getCapability(ResearchStorage.RESEARCH_CAPABILITY).ifPresent(iResearch -> {
            if(iResearch.isDiscovered(shroom)){
                Minecraft.getInstance().displayGuiScreen(new ShroomBookScreen(ITextComponent.getTextComponentOrEmpty("Screen")).addDesc(shroom));
            }
        });
    }

    public static void updateBool(Research.Shrooms shroom, boolean bool){
        FungusForageNetwork.CHANNEL.sendToServer(new ServerBoundInputMessage(shroom, bool));
    }

    public static void updateProp(Research.Shrooms shroom, String name, int val){
        FungusForageNetwork.CHANNEL.sendToServer(new ServerBoundPropertyMessage(shroom, name,val));
    }








}
