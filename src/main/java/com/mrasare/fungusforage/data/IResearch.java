package com.mrasare.fungusforage.data;


import java.util.EnumMap;
import java.util.HashMap;

public interface IResearch{

     void setShroomState(Research.Shrooms shroom,boolean bool);
     Boolean isDiscovered(Research.Shrooms shroom);
    EnumMap<Research.Shrooms, Boolean> getBoolList();
    void setBoolList(EnumMap<Research.Shrooms,Boolean> list);
    void copyBoolListFrom(IResearch research);
//    void copyPropertiesListFrom(IResearch research);
//    void setPropertyList(EnumMap<Research.Shrooms, HashMap<String,Integer>> map);
//    EnumMap<Research.Shrooms, HashMap<String, Integer>> getPropertiesMap();
//    HashMap<String, Integer> getPropertyList(Research.Shrooms shrooms);
//    void setPropertyValue(Research.Shrooms shroom,String propertyName,int newValue);
//    int getPropertyValue(Research.Shrooms shroom,String propertyName);


}
