package com.mrasare.fungusforage.data;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;


public class Research implements IResearch{


    public enum Mushrooms {

        FLY_AGARIC("fly_agaric"),
        SHAGGY_INKCAP("shaggy_inkcap"),
        AMETHYST_DECEIVER("amethyst_deceiver"),
        PUFFBALL("puffball"),
        BLACK_TRUMPET("black_trumpet");

        private final String name;

        Mushrooms(String name) {
            this.name = name;
        }

        public String getName () {
            return name;
        }


    }


    public EnumMap<Mushrooms,Boolean> mushroomMap = new EnumMap<>(Mushrooms.class);
    public EnumMap<Mushrooms, HashMap<String,Integer>> propertyMap = new EnumMap<>(Mushrooms.class);



    Research(){
        Arrays.stream(Mushrooms.values()).forEach(shrooms -> {
            mushroomMap.put(shrooms,false);
//            propertyMap.put(shrooms,new HashMap<>());
//            Registration.SHROOM_LIST.get(shrooms).forEach(integerProperty -> {
//                propertyMap.get(shrooms).put(integerProperty.getName(),0);
//            });
        });
    }

//
//    @Override
//    public void setPropertyValue(Shrooms shroom, String propertyName, int newValue) {
//        propertyMap.get(shroom).put(propertyName,newValue);
//    }
//
//    @Override
//    public int getPropertyValue(Shrooms shroom, String propertyName) {
//        return propertyMap.get(shroom).get(propertyName);
//    }
//
//    @Override
//    public HashMap<String, Integer> getPropertyList(Shrooms shrooms) {
//        return propertyMap.get(shrooms);
//    }

    @Override
    public void setShroomState(Mushrooms shroom, boolean bool) {
        mushroomMap.put(shroom,bool);
    }

    @Override
    public Boolean isDiscovered(Mushrooms shroom) {
        return mushroomMap.getOrDefault(shroom,false);
    }

    @Override
    public EnumMap<Mushrooms, Boolean> getBoolList() {
        return mushroomMap;
    }

    @Override
    public void setBoolList(EnumMap<Mushrooms, Boolean> list) {
        this.mushroomMap = list;
    }
//
//    @Override
//    public void setPropertyList(EnumMap<Shrooms, HashMap<String, Integer>> map) {
//        this.propertyMap = map;
//    }

    @Override
    public void copyBoolListFrom(IResearch research) {
        this.mushroomMap = research.getBoolList();
    }

    public EnumMap<Mushrooms, HashMap<String, Integer>> getPropertiesMap() {
        return propertyMap;
    }
//
//    @Override
//    public void copyPropertiesListFrom(IResearch research) {
//        this.propertyMap = research.getPropertiesMap();
//    }



}
