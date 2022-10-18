package com.mrasare.fungusforage.util;

import net.minecraft.potion.Effect;

public class MushroomEffect  {
    private Effect effect;
    private int duration;
    private int amplifier;




    public MushroomEffect(Effect effect, int duration, int amplifier){
        this(effect,duration);
        this.amplifier = amplifier;
    }

    public MushroomEffect(Effect effect, int duration){
        this.effect = effect;
        this.duration = duration;
        amplifier =0;
    }

    public Effect getEffect() {
        return effect;
    }

    public int getDuration() {
        return duration;
    }

    public int getAmplifier() {
        return amplifier;
    }




}
