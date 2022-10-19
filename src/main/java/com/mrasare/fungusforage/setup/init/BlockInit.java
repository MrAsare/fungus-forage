package com.mrasare.fungusforage.setup.init;

import com.mrasare.fungusforage.FungusForage;
import com.mrasare.fungusforage.block.DryerBlock;
import com.mrasare.fungusforage.block.mushroom.*;
import net.minecraft.block.Block;
import net.minecraft.state.IntegerProperty;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.HashMap;

import static com.mrasare.fungusforage.data.Research.*;

public class BlockInit {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, FungusForage.MODID);



    public static final RegistryObject<AbstractShroom> FLY_AGARIC = BLOCKS.register(Mushrooms.FLY_AGARIC.getName(),()->new FlyAgaric(Mushrooms.FLY_AGARIC));


    public static final RegistryObject<AbstractShroom> SHAGGY_INK_CAP = BLOCKS.register(Mushrooms.SHAGGY_INKCAP.getName(),()->new ShaggyInkCap(Mushrooms.SHAGGY_INKCAP));


    public static final RegistryObject<AbstractShroom> AMETHYST_DECEIVER = BLOCKS.register(Mushrooms.AMETHYST_DECEIVER.getName(),()->new AmethystDeceiver(Mushrooms.AMETHYST_DECEIVER));


    public static final RegistryObject<AbstractShroom> PUFFBALL = BLOCKS.register(Mushrooms.PUFFBALL.getName(), ()->new Puffball(Mushrooms.PUFFBALL));


    public static final RegistryObject<AbstractShroom> BLACK_TRUMPET = BLOCKS.register(Mushrooms.BLACK_TRUMPET.getName(),()->new BlackTrumpet(Mushrooms.BLACK_TRUMPET));

    public static final RegistryObject<Block> DRYER = BLOCKS.register(DryerBlock.name, DryerBlock::new);





    public static void init() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }









}
