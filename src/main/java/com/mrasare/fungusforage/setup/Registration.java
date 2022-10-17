package com.mrasare.fungusforage.setup;

import com.mrasare.fungusforage.FungusForage;
import com.mrasare.fungusforage.block.mushrooms.*;
import com.mrasare.fungusforage.data.Research;
import com.mrasare.fungusforage.item.MagnifyingGlass;
import com.mrasare.fungusforage.item.MushroomBook;
import com.mrasare.fungusforage.item.MushroomKnife;
import com.mrasare.fungusforage.item.MushroomSample;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class Registration {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, FungusForage.MODID);
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, FungusForage.MODID);






    public static void init() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static final RegistryObject<AbstractShroom> FLY_AGARIC = BLOCKS.register(FlyAgaric.NAME.getName(), FlyAgaric::new);
    public static final RegistryObject<Item> ITEM_FLY_AGARIC = ITEMS.register(FlyAgaric.NAME.getName(),()->new ShroomItem(FLY_AGARIC.get()));

    public static final RegistryObject<AbstractShroom> SHAGGY_INK_CAP = BLOCKS.register(ShaggyInkCap.NAME.getName(), ShaggyInkCap::new);
    public static final RegistryObject<Item> ITEM_SHAGGY_INK_CAP = ITEMS.register(ShaggyInkCap.NAME.getName(),()->new ShroomItem(SHAGGY_INK_CAP.get()));

    public static final RegistryObject<AbstractShroom> AMETHYST_DECEIVER = BLOCKS.register(AmethystDeceiver.SHROOM.getName(), AmethystDeceiver::new);
    public static final RegistryObject<Item> ITEM_AMETHYST_DECEIVER = ITEMS.register(AmethystDeceiver.SHROOM.getName(),()->new ShroomItem(AMETHYST_DECEIVER.get()));

    public static final RegistryObject<AbstractShroom> PUFFBALL = BLOCKS.register(Puffball.NAME.getName(), Puffball::new);
    public static final RegistryObject<Item> ITEM_PUFFBALL = ITEMS.register(Puffball.NAME.getName(),()->new ShroomItem(PUFFBALL.get()));

    public static final RegistryObject<AbstractShroom> BLACK_TRUMPET = BLOCKS.register(BlackTrumpet.NAME.getName(), BlackTrumpet::new);
    public static final RegistryObject<Item> ITEM_BLACK_TRUMPET = ITEMS.register(BlackTrumpet.NAME.getName(),()->new ShroomItem(BLACK_TRUMPET.get()));

    public static final HashMap<Research.Shrooms,RegistryObject<AbstractShroom>> SHROOM_LIST = new HashMap<>();




    public static final RegistryObject<MushroomBook> MUSHROOM_BOOK = ITEMS.register("mushroom_encyclopedia",()->new MushroomBook(new Item.Properties().group(ModSetup.ITEM_GROUP)));

    public static final RegistryObject<MagnifyingGlass> MAGNIFYING_GLASS = ITEMS.register("magnifying_glass",()->new MagnifyingGlass(new Item.Properties().group(ModSetup.ITEM_GROUP)));
    public static final RegistryObject<MushroomKnife> MUSHROOM_KNIFE = ITEMS.register("mushroom_knife",()->new MushroomKnife(new Item.Properties()));
    public static final RegistryObject<MushroomSample> MUSHROOM_SAMPLE = ITEMS.register("mushroom_sample",()->new MushroomSample(new Item.Properties()));




}
