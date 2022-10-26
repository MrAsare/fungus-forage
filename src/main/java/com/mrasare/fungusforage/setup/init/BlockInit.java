package com.mrasare.fungusforage.setup.init;

import com.mrasare.fungusforage.FungusForage;
import com.mrasare.fungusforage.block.DryerBlock;
import com.mrasare.fungusforage.block.mushroom.*;
import com.mrasare.fungusforage.item.MushroomItem;
import com.mrasare.fungusforage.setup.ModSetup;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.state.IntegerProperty;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Supplier;

import static com.mrasare.fungusforage.data.Research.*;

public class BlockInit {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, FungusForage.MODID);


    public static final RegistryObject<AbstractShroom> FLY_AGARIC = registerShroom(Mushrooms.FLY_AGARIC,()->new FlyAgaric(Mushrooms.FLY_AGARIC));
    public static final RegistryObject<AbstractShroom> SHAGGY_INK_CAP = registerShroom(Mushrooms.SHAGGY_INKCAP,()->new ShaggyInkCap(Mushrooms.SHAGGY_INKCAP));
    public static final RegistryObject<AbstractShroom> AMETHYST_DECEIVER = registerShroom(Mushrooms.AMETHYST_DECEIVER,()->new AmethystDeceiver(Mushrooms.AMETHYST_DECEIVER));
    public static final RegistryObject<AbstractShroom> PUFFBALL = registerShroom(Mushrooms.PUFFBALL, ()->new Puffball(Mushrooms.PUFFBALL));
    public static final RegistryObject<AbstractShroom> BLACK_TRUMPET = registerShroom(Mushrooms.BLACK_TRUMPET,()->new BlackTrumpet(Mushrooms.BLACK_TRUMPET));

    public static final RegistryObject<Block> DRYER = registerBlockAndItem(DryerBlock.name, DryerBlock::new,new Item.Properties().group(ModSetup.ITEM_GROUP));


    public static <T extends AbstractShroom>RegistryObject<AbstractShroom> registerShroom(Mushrooms shroom, Supplier<T> supplier){
        RegistryObject<AbstractShroom> mushroom = BLOCKS.register(shroom.getName(),supplier);

        ItemInit.ITEMS.register(shroom.getName(),()->new MushroomItem(mushroom.get()));


        return mushroom;
    }

    public static<T extends Block> RegistryObject<T> registerBlockAndItem(String name, Supplier<T> supplier,Item.Properties properties){
        RegistryObject<T> block = BLOCKS.register(name,supplier);

        ItemInit.ITEMS.register(name,()->new BlockItem(block.get(),properties));

        return block;
    }





    public static void init() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }









}
