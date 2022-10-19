package com.mrasare.fungusforage.data;


import java.util.EnumMap;

public interface IResearch{

     void setShroomState(Research.Mushrooms shroom, boolean bool);
     Boolean isDiscovered(Research.Mushrooms shroom);
    EnumMap<Research.Mushrooms, Boolean> getBoolList();
    void setBoolList(EnumMap<Research.Mushrooms,Boolean> list);
    void copyBoolListFrom(IResearch research);
//    void copyPropertiesListFrom(IResearch research);
//    void setPropertyList(EnumMap<Research.Shrooms, HashMap<String,Integer>> map);
//    EnumMap<Research.Shrooms, HashMap<String, Integer>> getPropertiesMap();
//    HashMap<String, Integer> getPropertyList(Research.Shrooms shrooms);
//    void setPropertyValue(Research.Shrooms shroom,String propertyName,int newValue);
//    int getPropertyValue(Research.Shrooms shroom,String propertyName);


}
