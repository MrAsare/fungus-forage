package com.mrasare.fungusforage.item;

import com.mrasare.fungusforage.FungusForage;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class MushroomSample extends Item {
    public MushroomSample(Properties properties) {
        super(properties.food(new Food.Builder().setAlwaysEdible().build()));
    }


    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        if(stack.hasTag()){

            CompoundNBT effects = stack.getTag().getCompound(FungusForage.MODID+".effects");
            effects.keySet().forEach(key->{

                CompoundNBT stats = effects.getCompound(key);

                entityLiving.addPotionEffect(new EffectInstance(Effect.get(Integer.parseInt(key)),stats.getShort("duration"),stats.getByte("amplifier")));
            });
        }
        return super.onItemUseFinish(stack, worldIn, entityLiving);
    }


    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if(stack.hasTag()){
            CompoundNBT effects = stack.getTag().getCompound(FungusForage.MODID+".effects");

            effects.keySet().forEach(key->{
//
//                CompoundNBT stats = effects.getCompound(key);
                Effect effect = Effect.get(Integer.parseInt(key));

                tooltip.add(new StringTextComponent((effect.getEffectType()== EffectType.HARMFUL? "§4": "§a")+effect.getDisplayName().getString()));
            });

        }
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}

