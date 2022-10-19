package com.mrasare.fungusforage.setup.init;

import com.mojang.datafixers.DataFixer;
import com.mojang.datafixers.DataFixerBuilder;
import com.mrasare.fungusforage.FungusForage;
import com.mrasare.fungusforage.block.DryerBlock;
import com.mrasare.fungusforage.block.DryerBlockTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntityInit {

    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, FungusForage.MODID);
    public static final RegistryObject<TileEntityType<DryerBlockTileEntity>> DRYER_TILE_ENTITY = TILE_ENTITIES.register(
            DryerBlock.name,()->TileEntityType.Builder.create(DryerBlockTileEntity::new,BlockInit.DRYER.get()).build(null));


    public static void init(){
        TILE_ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

}
