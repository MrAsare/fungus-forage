package com.mrasare.fungusforage.item;

import com.mrasare.fungusforage.block.mushroom.AbstractShroom;
import com.mrasare.fungusforage.data.Research;
import com.mrasare.fungusforage.data.ResearchStorage;
import com.mrasare.fungusforage.setup.ModSetup;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;


public class MushroomItem extends BlockItem {

    private final Research.Mushrooms mushroom;


    public MushroomItem(AbstractShroom abstractShroom) {
        super(abstractShroom,new Item.Properties().food(abstractShroom.applyMushroomEffects(abstractShroom.mushroom).build()).group(ModSetup.ITEM_GROUP));
        this.mushroom = abstractShroom.mushroom;
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        if(!worldIn.isRemote) {
            entityLiving.getCapability(ResearchStorage.RESEARCH_CAPABILITY).ifPresent(research -> {
                if (!research.isDiscovered(mushroom)) {
                    System.out.println("LELELELELELEL");
                    research.setShroomState(mushroom, true);
                }
            });
        }
        return super.onItemUseFinish(stack, worldIn, entityLiving);
    }
}
