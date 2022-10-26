package com.mrasare.fungusforage.world.gen;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.FeatureSpread;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Supplier;

public class ModMushroomGeneration {


    public static void generateMushrooms(BiomeLoadingEvent event){
        RegistryKey<Biome> key = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, Objects.requireNonNull(event.getName()));
        Set<Type> types = BiomeDictionary.getTypes(key);
        List<Supplier<ConfiguredFeature<?, ?>>> base = event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION);

        if(types.contains(Type.FOREST) || types.contains(Type.CONIFEROUS)||types.contains(Type.DENSE)) {
            base.add(() -> ModConfiguredFeatures.FLY_AGARIC.withPlacement(Features.Placements.PATCH_PLACEMENT.chance(1)));
        }

        if(types.contains(Type.PLAINS)||types.contains(Type.HILLS)) {

            base.add(() -> ModConfiguredFeatures.SHAGGY_INKCAP.withPlacement(Features.Placements.PATCH_PLACEMENT.chance(1)));
            base.add(() -> ModConfiguredFeatures.PUFFBALL.withPlacement(Features.Placements.PATCH_PLACEMENT.chance(1)));
        }

        if(types.contains(Type.CONIFEROUS)) {
            base.add(() -> ModConfiguredFeatures.AMETHYST_DECEIVER.withPlacement(Features.Placements.PATCH_PLACEMENT.chance(1)));
        }

        if(types.contains(Type.LUSH) || types.contains(Type.WET)) {
            base.add(() -> ModConfiguredFeatures.BLACK_TRUMPET.withPlacement(Features.Placements.PATCH_PLACEMENT.chance(1)));
        }







    }
}
