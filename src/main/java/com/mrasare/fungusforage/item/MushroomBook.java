package com.mrasare.fungusforage.item;

import com.mrasare.fungusforage.setup.ClientSetup;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;

public class MushroomBook extends Item {
    public MushroomBook(Properties properties) {
        super(properties.maxStackSize(1));
    }


    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT,()-> ClientSetup::showBookScreen);
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
