package com.mrasare.fungusforage.item;

import com.mrasare.fungusforage.block.mushroom.AbstractShroom;
import com.mrasare.fungusforage.data.ResearchStorage;
import com.mrasare.fungusforage.setup.ClientSetup;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;

public class MagnifyingGlass extends Item {


    public MagnifyingGlass(Properties properties) {
        super(properties.maxStackSize(1));
    }


    @Override
    public ActionResultType onItemUse(ItemUseContext context) {


            BlockState blockstate = context.getWorld().getBlockState(context.getPos());
            Block block = blockstate.getBlock();

            if (block instanceof AbstractShroom) {
                AbstractShroom shroom = (AbstractShroom) block;
                context.getPlayer().getCapability(ResearchStorage.RESEARCH_CAPABILITY).ifPresent(iResearch -> {

                    DistExecutor.unsafeRunWhenOn(Dist.CLIENT,()->()->{
                        ClientSetup.updateBool(shroom.mushroom,true);

//                        Registration.SHROOM_LIST.get(shroom).forEach(property-> {
//                                    int oldval = iResearch.getPropertyList(shroom.shroom).get(property.getName());
//                                    int blockVal = blockstate.get(property);
//                                    if(blockVal>oldval){
//                                        ClientSetup.updateProp(shroom.shroom,property.getName(),blockVal);
//                                    }
//                                }
//                        );

                    });



                });
            }

        return super.onItemUse(context);

    }


}
