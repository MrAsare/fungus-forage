package com.mrasare.fungusforage.block.mushroom;

import com.mrasare.fungusforage.data.Research;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.Food;

public class Puffball extends AbstractShroom {


    public Puffball(Research.Mushrooms shroom) {
        super(AbstractBlock.Properties.create(Material.PLANTS),shroom);
//        AtomicReference<BlockState> stateAtomic = new AtomicReference<>(this.stateContainer.getBaseState().with(FACING,Direction.NORTH));
//        mushroomProperties.forEach(integerProperty -> {
//            stateAtomic.set(stateAtomic.get().with(integerProperty, Utils.getMaxValFromProperty(integerProperty) / 2));
//        });
//        this.setDefaultState(stateAtomic.get());


        EFFECT_MAP.put(shroom.getName(),effectsList);
    }


    @Override
    public Food.Builder getFoodValues(Food.Builder builder) {
        return builder.saturation(1).hunger(1);
    }
}

