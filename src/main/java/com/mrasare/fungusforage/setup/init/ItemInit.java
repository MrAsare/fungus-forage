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

    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, FungusForage.MODID);

    public static final RegistryObject<Item> ITEM_FLY_AGARIC = ITEMS.register(Research.Mushrooms.FLY_AGARIC.getName(),()->new MushroomItem(FLY_AGARIC.get()));

    public static final RegistryObject<Item> ITEM_SHAGGY_INK_CAP = ITEMS.register(Research.Mushrooms.SHAGGY_INKCAP.getName(),()->new MushroomItem(SHAGGY_INK_CAP.get()));

    public static final RegistryObject<Item> ITEM_AMETHYST_DECEIVER = ITEMS.register(Research.Mushrooms.AMETHYST_DECEIVER.getName(),()->new MushroomItem(AMETHYST_DECEIVER.get()));

    public static final RegistryObject<Item> ITEM_PUFFBALL = ITEMS.register(Research.Mushrooms.PUFFBALL.getName(),()->new MushroomItem(PUFFBALL.get()));

    public static final RegistryObject<Item> ITEM_BLACK_TRUMPET = ITEMS.register(Research.Mushrooms.BLACK_TRUMPET.getName(),()->new MushroomItem(BLACK_TRUMPET.get()));

    public static final RegistryObject<MushroomBook> MUSHROOM_BOOK = ITEMS.register("mushroom_encyclopedia",()->new MushroomBook(new Item.Properties().group(ModSetup.ITEM_GROUP)));

    public static final RegistryObject<MagnifyingGlass> MAGNIFYING_GLASS = ITEMS.register("magnifying_glass",()->new MagnifyingGlass(new Item.Properties().group(ModSetup.ITEM_GROUP)));

    public static final RegistryObject<MushroomKnife> MUSHROOM_KNIFE = ITEMS.register("mushroom_knife",()->new MushroomKnife(new Item.Properties()));

    public static final RegistryObject<MushroomSample> MUSHROOM_SAMPLE = ITEMS.register("mushroom_sample",()->new MushroomSample(new Item.Properties()));
    public static final RegistryObject<BlockItem> DRYER = ITEMS.register(DryerBlock.name,()->new BlockItem(BlockInit.DRYER.get(),new Item.Properties().group(ModSetup.ITEM_GROUP)));










    public static void init(){
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
