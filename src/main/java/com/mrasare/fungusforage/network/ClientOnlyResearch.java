package com.mrasare.fungusforage.network;

import com.mrasare.fungusforage.data.Research;
import com.mrasare.fungusforage.data.ResearchStorage;
import net.minecraft.client.Minecraft;

import java.util.EnumMap;
import java.util.HashMap;

public class ClientOnlyResearch {


    public static void updateClientResearch(Research.Shrooms shroom, boolean state){
        Minecraft.getInstance().player.getCapability(ResearchStorage.RESEARCH_CAPABILITY).ifPresent(iResearch ->
            iResearch.setShroomState(shroom,state));
    }

    public static void updateClientResearch(EnumMap<Research.Shrooms,Boolean> map){
        Minecraft.getInstance().player.getCapability(ResearchStorage.RESEARCH_CAPABILITY).ifPresent(iResearch ->
                iResearch.setBoolList(map));
    }

//    public static void updateClientPropertySingle(Research.Shrooms shroom,String name, int newVal){
//        Minecraft.getInstance().player.getCapability(ResearchStorage.RESEARCH_CAPABILITY).ifPresent(iResearch ->
//                iResearch.setPropertyValue(shroom,name,newVal));
//    }
//
//    public static void updateClientPropertyList(EnumMap<Research.Shrooms, HashMap<String,Integer>> map){
//        Minecraft.getInstance().player.getCapability(ResearchStorage.RESEARCH_CAPABILITY).ifPresent(iResearch ->
//                iResearch.setPropertyList(map));
//    }
}
