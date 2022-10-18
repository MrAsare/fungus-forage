package com.mrasare.fungusforage.block.mushrooms;

import com.mrasare.fungusforage.data.Research;
import com.mrasare.fungusforage.util.MushroomEffect;
import com.mrasare.fungusforage.util.Utils;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;

import javax.print.attribute.SetOfIntegerSyntax;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

public class FlyAgaric extends AbstractShroom {




    private  MushroomEffect WITHER_EFFECT = new MushroomEffect(Effects.WITHER,200,2);
    private  MushroomEffect POISON_EFFECT = new MushroomEffect(Effects.POISON,200,2);
    private  MushroomEffect NAUSEA_EFFECT = new MushroomEffect(Effects.NAUSEA,200,1);

    public static final IntegerProperty SPOTS = IntegerProperty.create("spots",1,10);





    public FlyAgaric(Research.Shrooms shroom) {
        super(AbstractBlock.Properties.create(Material.PLANTS),shroom);
//        AtomicReference<BlockState> stateAtomic = new AtomicReference<>(this.stateContainer.getBaseState().with(FACING,Direction.NORTH));
//
//        mushroomProperties.forEach(integerProperty -> {
//            stateAtomic.set(stateAtomic.get().with(integerProperty, Utils.getMaxValFromProperty(integerProperty) / 2));
//        });
//        this.setDefaultState(stateAtomic.get());

        effectsList.addAll(Arrays.asList(NAUSEA_EFFECT,POISON_EFFECT,WITHER_EFFECT));
        EFFECT_MAP.put(shroom.getName(),effectsList);
    }


    @Override
    public Item.Properties getItemProperties() {
        Food.Builder builder = new Food.Builder().hunger(1).saturation(1).setAlwaysEdible();
        EFFECT_MAP.get(shroom.getName()).forEach(mushroomEffect -> {
            builder.effect(()->new EffectInstance(mushroomEffect.getEffect(), mushroomEffect.getDuration(),mushroomEffect.getAmplifier()),1f);
        });
        return new Item.Properties().food(builder.build());
    }




}
