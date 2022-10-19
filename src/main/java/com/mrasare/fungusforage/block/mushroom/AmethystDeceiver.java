package com.mrasare.fungusforage.block.mushroom;

import com.mrasare.fungusforage.data.Research;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.Food;

public class AmethystDeceiver extends AbstractShroom {


    public AmethystDeceiver(Research.Mushrooms shroom) {
        super(AbstractBlock.Properties.create(Material.PLANTS),shroom);
//        AtomicReference<BlockState> stateAtomic = new AtomicReference<>(this.stateContainer.getBaseState().with(FACING,Direction.NORTH));
//        mushroomProperties.forEach(integerProperty -> {
//            stateAtomic.set(stateAtomic.get().with(integerProperty, Utils.getMaxValFromProperty(integerProperty) / 2));
//        });
        EFFECT_MAP.put(shroom.getName(),this.effectsList);
//        this.setDefaultState(stateAtomic.get());
    }

    @Override
    public Food.Builder getFoodValues(Food.Builder builder) {
        return builder.saturation(1).hunger(1);
    }

}
