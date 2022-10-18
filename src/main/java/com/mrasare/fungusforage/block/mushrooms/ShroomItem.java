package com.mrasare.fungusforage.block.mushrooms;

import com.mrasare.fungusforage.data.Research;
import com.mrasare.fungusforage.data.ResearchStorage;
import com.mrasare.fungusforage.setup.ModSetup;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;


public class ShroomItem extends BlockItem {

    private final Research.Shrooms shroom;


    public ShroomItem(AbstractShroom shroom) {
        super(shroom,shroom.getItemProperties().group(ModSetup.ITEM_GROUP));
        this.shroom = shroom.shroom;
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        if(!worldIn.isRemote) {
            entityLiving.getCapability(ResearchStorage.RESEARCH_CAPABILITY).ifPresent(research -> {
                if (!research.isDiscovered(shroom)) {
                    System.out.println("LELELELELELEL");
                    research.setShroomState(shroom, true);
                }
            });
        }
        return super.onItemUseFinish(stack, worldIn, entityLiving);
    }
}
