package com.mrasare.fungusforage.data;

import net.minecraft.state.IntegerProperty;
import net.minecraft.state.Property;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;



public class Research implements IResearch{


    public enum Shrooms{

        FLY_AGARIC("fly_agaric", new ArrayList<>(Arrays.asList(IntegerProperty.create("spots",1,10),
                IntegerProperty.create("height",10,20),
                IntegerProperty.create("width",10,20)))),
        SHAGGY_INKCAP("shaggy_inkcap",new ArrayList<>(Arrays.asList(
                IntegerProperty.create("height",10,20),
                IntegerProperty.create("width",10,20)))),
        AMETHYST_DECEIVER("amethyst_deceiver",new ArrayList<>(Arrays.asList(
                IntegerProperty.create("height",10,20),
                IntegerProperty.create("width",10,20)))),
        PUFFBALL("puffball",new ArrayList<>(Arrays.asList(
                IntegerProperty.create("height",10,20),
                IntegerProperty.create("width",10,20)))),
        BLACK_TRUMPET("black_trumpet",new ArrayList<>(Arrays.asList(
                IntegerProperty.create("height",10,20),
                IntegerProperty.create("width",10,20))));

        private final String name;
        private final ArrayList<Property<Integer>> propertiesList;

        Shrooms(String name, ArrayList<Property<Integer>> propertiesList) {
            this.name = name;
            this.propertiesList = propertiesList;
        }

        public String getName () {
            return name;
        }

        public ArrayList<Property<Integer>> getPropertiesList(){
            return propertiesList;
        }


    }


    public EnumMap<Shrooms,Boolean> mushroomMap = new EnumMap<>(Shrooms.class);
    public EnumMap<Shrooms, HashMap<String,Integer>> propertyMap = new EnumMap<>(Shrooms.class);



    Research(){
        Arrays.stream(Shrooms.values()).forEach(shrooms -> {
            mushroomMap.put(shrooms, false);
            propertyMap.put(shrooms,new HashMap<>());
            shrooms.propertiesList.forEach(property -> {
                propertyMap.get(shrooms).put(property.getName(),0);
            });
        });


    }


    @Override
    public void setPropertyValue(Shrooms shroom, String propertyName, int newValue) {
        propertyMap.get(shroom).put(propertyName,newValue);
    }

    @Override
    public int getPropertyValue(Shrooms shroom, String propertyName) {
        return propertyMap.get(shroom).get(propertyName);
    }

    @Override
    public HashMap<String, Integer> getPropertyList(Shrooms shrooms) {
        return propertyMap.get(shrooms);
    }

    @Override
    public void setShroomState(Shrooms shroom, boolean bool) {
        mushroomMap.put(shroom,bool);
    }

    @Override
    public Boolean isDiscovered(Shrooms shroom) {
        return mushroomMap.getOrDefault(shroom,false);
    }

    @Override
    public EnumMap<Shrooms, Boolean> getBoolList() {
        return mushroomMap;
    }

    @Override
    public void setBoolList(EnumMap<Shrooms, Boolean> list) {
        this.mushroomMap = list;
    }

    @Override
    public void setPropertyList(EnumMap<Shrooms, HashMap<String, Integer>> map) {
        this.propertyMap = map;
    }

    @Override
    public void copyBoolListFrom(IResearch research) {
        this.mushroomMap = research.getBoolList();
    }

    public EnumMap<Shrooms, HashMap<String, Integer>> getPropertiesMap() {
        return propertyMap;
    }

    @Override
    public void copyPropertiesListFrom(IResearch research) {
        this.propertyMap = research.getPropertiesMap();
    }



}
