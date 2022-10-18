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
import net.minecraft.state.IntegerProperty;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static com.mrasare.fungusforage.data.Research.*;

public class Registration {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, FungusForage.MODID);
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, FungusForage.MODID);


    public static final HashMap<Shrooms, ArrayList<IntegerProperty>> SHROOM_LIST = new HashMap<>();



    public static final RegistryObject<AbstractShroom> FLY_AGARIC = BLOCKS.register(Shrooms.FLY_AGARIC.getName(),()->new FlyAgaric(Shrooms.FLY_AGARIC));
    public static final RegistryObject<Item> ITEM_FLY_AGARIC = ITEMS.register(Shrooms.FLY_AGARIC.getName(),()->new ShroomItem(FLY_AGARIC.get()));

    public static final RegistryObject<AbstractShroom> SHAGGY_INK_CAP = BLOCKS.register(Shrooms.SHAGGY_INKCAP.getName(),()->new ShaggyInkCap(Shrooms.SHAGGY_INKCAP));
    public static final RegistryObject<Item> ITEM_SHAGGY_INK_CAP = ITEMS.register(Shrooms.SHAGGY_INKCAP.getName(),()->new ShroomItem(SHAGGY_INK_CAP.get()));

    public static final RegistryObject<AbstractShroom> AMETHYST_DECEIVER = BLOCKS.register(Shrooms.AMETHYST_DECEIVER.getName(),()->new AmethystDeceiver(Shrooms.AMETHYST_DECEIVER));
    public static final RegistryObject<Item> ITEM_AMETHYST_DECEIVER = ITEMS.register(Shrooms.AMETHYST_DECEIVER.getName(),()->new ShroomItem(AMETHYST_DECEIVER.get()));

    public static final RegistryObject<AbstractShroom> PUFFBALL = BLOCKS.register(Shrooms.PUFFBALL.getName(), ()->new Puffball(Shrooms.PUFFBALL));
    public static final RegistryObject<Item> ITEM_PUFFBALL = ITEMS.register(Shrooms.PUFFBALL.getName(),()->new ShroomItem(PUFFBALL.get()));

    public static final RegistryObject<AbstractShroom> BLACK_TRUMPET = BLOCKS.register(Shrooms.BLACK_TRUMPET.getName(),()->new BlackTrumpet(Shrooms.BLACK_TRUMPET));
    public static final RegistryObject<Item> ITEM_BLACK_TRUMPET = ITEMS.register(Shrooms.BLACK_TRUMPET.getName(),()->new ShroomItem(BLACK_TRUMPET.get()));



    public static final RegistryObject<MushroomBook> MUSHROOM_BOOK = ITEMS.register("mushroom_encyclopedia",()->new MushroomBook(new Item.Properties().group(ModSetup.ITEM_GROUP)));
    public static final RegistryObject<MagnifyingGlass> MAGNIFYING_GLASS = ITEMS.register("magnifying_glass",()->new MagnifyingGlass(new Item.Properties().group(ModSetup.ITEM_GROUP)));
    public static final RegistryObject<MushroomKnife> MUSHROOM_KNIFE = ITEMS.register("mushroom_knife",()->new MushroomKnife(new Item.Properties()));
    public static final RegistryObject<MushroomSample> MUSHROOM_SAMPLE = ITEMS.register("mushroom_sample",()->new MushroomSample(new Item.Properties()));




    public static void init() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());


    }









}
