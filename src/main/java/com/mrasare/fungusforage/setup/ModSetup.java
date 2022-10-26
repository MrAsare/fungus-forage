package com.mrasare.fungusforage.setup;

import com.mrasare.fungusforage.FungusForage;
import com.mrasare.fungusforage.data.ResearchStorage;
import com.mrasare.fungusforage.entity.custom.EntityMinishroom;
import com.mrasare.fungusforage.network.FungusForageNetwork;
import com.mrasare.fungusforage.setup.init.BlockInit;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid= FungusForage.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModSetup {

    public static final ItemGroup ITEM_GROUP = new ItemGroup("fungusForageTab") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(BlockInit.FLY_AGARIC.get());
        }
    };


    @SubscribeEvent
    public static void init(final FMLCommonSetupEvent event) {
        ResearchStorage.register();
        FungusForageNetwork.init();
    }

//    @SubscribeEvent
//    public static void onEntityAttack(final AttackEntityEvent event) {
//        if(event.getTarget() instanceof EntityMinishroom){
//            event.getTarget().getEntity().
//        }
//    }





}

