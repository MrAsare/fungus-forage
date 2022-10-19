package com.mrasare.fungusforage.block.mushroom;

import com.mrasare.fungusforage.data.Research;
import com.mrasare.fungusforage.util.MushroomEffect;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.Food;
import net.minecraft.potion.Effects;

import java.util.Arrays;

public class ShaggyInkCap extends AbstractShroom {




    private static MushroomEffect BLINDNESS_EFFECT = new MushroomEffect(Effects.BLINDNESS,200);

    public ShaggyInkCap(Research.Mushrooms shroom) {

        super(AbstractBlock.Properties.create(Material.PLANTS),shroom);
//
//        AtomicReference<BlockState> stateAtomic = new AtomicReference<>(this.stateContainer.getBaseState().with(FACING,Direction.NORTH));
//        mushroomProperties.forEach(integerProperty -> {
//            stateAtomic.set(stateAtomic.get().with(integerProperty, Utils.getMaxValFromProperty(integerProperty) / 2));
//        });
//        this.setDefaultState(stateAtomic.get());

        effectsList.addAll(Arrays.asList(BLINDNESS_EFFECT));
        EFFECT_MAP.put(shroom.getName(),effectsList);

    }


    @Override
    public Food.Builder getFoodValues(Food.Builder builder) {
        return builder.saturation(1).hunger(1);
    }

}
