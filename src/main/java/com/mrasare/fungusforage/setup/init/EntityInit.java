package com.mrasare.fungusforage.setup.init;

import com.mrasare.fungusforage.FungusForage;
import com.mrasare.fungusforage.data.Research;
import com.mrasare.fungusforage.entity.custom.EntityMinishroom;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EntityInit  {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, FungusForage.MODID);

    public static final RegistryObject<EntityType<EntityMinishroom>> MINISHROOM = ENTITIES.register("minishroom",
            ()->EntityType.Builder.create(EntityMinishroom::new, EntityClassification.CREATURE).size(0.5f,0.5f)
                    .build(new ResourceLocation(FungusForage.MODID,"minishroom").toString()));








    public static void init() {
        ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }


}
