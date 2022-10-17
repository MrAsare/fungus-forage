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

public class AmethystDeceiver extends AbstractShroom {

    public static Research.Shrooms SHROOM = Research.Shrooms.AMETHYST_DECEIVER;


    public AmethystDeceiver() {
        super(AbstractBlock.Properties.create(Material.PLANTS));
        shroom = SHROOM;
        AtomicReference<BlockState> stateAtomic = new AtomicReference<>(this.stateContainer.getBaseState().with(FACING,Direction.NORTH));
        shroom.getPropertiesList().forEach(integerProperty -> {
            stateAtomic.set(stateAtomic.get().with(integerProperty, Utils.getMaxValFromProperty(integerProperty) / 2));
        });
        EFFECT_MAP.put(SHROOM.getName(),effectsList);
        this.setDefaultState(stateAtomic.get());
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        shroom = SHROOM;
        super.fillStateContainer(builder);
        shroom.getPropertiesList().forEach(builder::add);
    }

    @Override
    public Item.Properties getItemProperties() {
        Food.Builder builder = new Food.Builder().hunger(2).saturation(1).setAlwaysEdible();
        EFFECT_MAP.get(shroom.getName()).forEach(mushroomEffect -> {
            builder.effect(()->new EffectInstance(mushroomEffect.getEffect(), mushroomEffect.getDuration(),mushroomEffect.getAmplifier()),1f);
        });
        return new Item.Properties().food(builder.build());
    }


}
