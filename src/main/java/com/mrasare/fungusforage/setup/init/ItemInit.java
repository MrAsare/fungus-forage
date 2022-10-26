package com.mrasare.fungusforage.setup.init;

import com.mrasare.fungusforage.FungusForage;
import com.mrasare.fungusforage.block.DryerBlock;
import com.mrasare.fungusforage.data.Research;
import com.mrasare.fungusforage.item.*;
import com.mrasare.fungusforage.setup.ModSetup;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.mrasare.fungusforage.setup.init.BlockInit.*;

public class ItemInit {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, FungusForage.MODID);


    public static final RegistryObject<MushroomBook> MUSHROOM_BOOK = ITEMS.register("mushroom_encyclopedia",()->new MushroomBook(new Item.Properties()));

    public static final RegistryObject<MagnifyingGlass> MAGNIFYING_GLASS = ITEMS.register("magnifying_glass",()->new MagnifyingGlass(new Item.Properties()));

    public static final RegistryObject<MushroomKnife> MUSHROOM_KNIFE = ITEMS.register("mushroom_knife",()->new MushroomKnife(new Item.Properties()));

    public static final RegistryObject<MushroomSample> MUSHROOM_SAMPLE = ITEMS.register("mushroom_sample",()->new MushroomSample(new Item.Properties()));


    public static final RegistryObject<ModSpawnEggItem> MINISHROOM_EGG = ITEMS.register("minishroom_spawn_egg",
            ()->new ModSpawnEggItem(EntityInit.MINISHROOM,0x11ff66,0x123456, new Item.Properties().group(ModSetup.ITEM_GROUP)));










    public static void init(){
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
