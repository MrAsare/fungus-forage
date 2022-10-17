package com.mrasare.fungusforage.util;

import com.mrasare.fungusforage.block.mushrooms.AbstractShroom;
import com.mrasare.fungusforage.data.Research;
import net.minecraft.nbt.CompoundNBT;
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

    public static CompoundNBT writeToNBT(Research.Shrooms shroom){

        CompoundNBT nbt = new CompoundNBT();
        AbstractShroom.EFFECT_MAP.get(shroom.getName()).forEach(mushroomEffect -> {
            CompoundNBT nbtStats = new CompoundNBT();
            nbtStats.putShort("duration", (short) mushroomEffect.getDuration());
            if(mushroomEffect.getAmplifier()>0){
                nbtStats.putByte("amplifier",(byte) mushroomEffect.getAmplifier());
            }


            nbt.put(String.valueOf(Effect.getId(mushroomEffect.effect)),nbtStats);
        });

        return nbt;

    }



}
