package com.mrasare.fungusforage.block.mushrooms;

import com.mrasare.fungusforage.data.Research;
import com.mrasare.fungusforage.util.Utils;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;

import java.util.concurrent.atomic.AtomicReference;

public class BlackTrumpet extends AbstractShroom{


    public BlackTrumpet(Research.Shrooms shroom) {
        super(AbstractBlock.Properties.create(Material.PLANTS),shroom);
//        AtomicReference<BlockState> stateAtomic = new AtomicReference<>(this.stateContainer.getBaseState().with(FACING,Direction.NORTH));
//        mushroomProperties.forEach(integerProperty -> {
//            stateAtomic.set(stateAtomic.get().with(integerProperty, Utils.getMaxValFromProperty(integerProperty) / 2));
//        });
        EFFECT_MAP.put(shroom.getName(),effectsList);
//        this.setDefaultState(stateAtomic.get());
    }

    @Override
    public Item.Properties getItemProperties() {
        Food.Builder builder = new Food.Builder().hunger(4).saturation(3).setAlwaysEdible();
        EFFECT_MAP.get(shroom.getName()).forEach(mushroomEffect -> {
            builder.effect(()->new EffectInstance(mushroomEffect.getEffect(), mushroomEffect.getDuration(),mushroomEffect.getAmplifier()),1f);
        });
        return new Item.Properties().food(builder.build());
    }


}
