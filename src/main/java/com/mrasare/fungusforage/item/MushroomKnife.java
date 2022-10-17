package com.mrasare.fungusforage.item;

import com.google.common.collect.ImmutableSet;
import com.mrasare.fungusforage.block.mushrooms.AbstractShroom;
import com.mrasare.fungusforage.setup.ModSetup;
import com.mrasare.fungusforage.util.ModSoundEvents;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import java.util.Set;

import static com.mrasare.fungusforage.setup.Registration.*;

public class MushroomKnife extends ToolItem {

    public static Set<Block> EFFECTIVE_BLOCKS = ImmutableSet.of(FLY_AGARIC.get(), SHAGGY_INK_CAP.get(), AMETHYST_DECEIVER.get(), PUFFBALL.get(), BLACK_TRUMPET.get());

    public MushroomKnife(Item.Properties properties) {
        super(3f,3f, ItemTier.STONE,EFFECTIVE_BLOCKS,properties.group(ModSetup.ITEM_GROUP).maxStackSize(1));
    }



    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        RayTraceResult block  = playerIn.pick(5.0D, 0.0F, false);
        if(!worldIn.isRemote&&block.getType() == RayTraceResult.Type.BLOCK) {

                BlockPos hitBlock = new BlockPos(block.getHitVec());
                Block blockLookedAt = worldIn.getBlockState(hitBlock).getBlock();

                if (blockLookedAt instanceof AbstractShroom) {

                    worldIn.playSound(null,new BlockPos(block.getHitVec()), ModSoundEvents.MUSHROOM_KNIFE_SLASH.get(), SoundCategory.BLOCKS,1f,1f);
                    worldIn.setBlockState(new BlockPos(block.getHitVec()),Blocks.AIR.getDefaultState());

                    //Drop mushroom
                  worldIn.addEntity(new ItemEntity(worldIn,hitBlock.getX(),hitBlock.getY(),hitBlock.getZ(), new ItemStack(blockLookedAt.asItem())));



//                    //Drop mushroom samples
//                    AbstractShroom shroom = (AbstractShroom)blockLookedAt;
//                    ItemStack stack = new ItemStack(MUSHROOM_SAMPLE.get(),worldIn.rand.nextInt(3)+1);
//                    stack.getOrCreateTag().put(FungusForage.MODID+".effects",MushroomEffect.writeToNBT(shroom.name));
//                    worldIn.addEntity(new ItemEntity(worldIn,hitBlock.getX(),hitBlock.getY(),hitBlock.getZ(),stack));

                    playerIn.getHeldItemMainhand().damageItem(1,playerIn,entity -> entity.sendBreakAnimation(entity.getActiveHand()));
                    return ActionResult.resultPass(playerIn.getHeldItem(handIn));
                }
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }




}
