package com.mrasare.fungusforage.world.gen;

import com.mrasare.fungusforage.setup.init.BlockInit;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;

public class ModConfiguredFeatures {

    public static final ConfiguredFeature<?, ?> FLY_AGARIC = register("fly_agaric", Feature.RANDOM_PATCH.withConfiguration(
            (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(BlockInit.FLY_AGARIC.get().getDefaultState()), SimpleBlockPlacer.PLACER))
                    .tries(1024).preventProjection().build()));

    public static final ConfiguredFeature<?, ?> SHAGGY_INKCAP = register("shaggy_inkcap", Feature.RANDOM_PATCH.withConfiguration(
            (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(BlockInit.SHAGGY_INK_CAP.get().getDefaultState()), SimpleBlockPlacer.PLACER))
                    .tries(1024).preventProjection().build()));

    public static final ConfiguredFeature<?, ?> AMETHYST_DECEIVER = register("amethyst_deceiver", Feature.RANDOM_PATCH.withConfiguration(
            (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(BlockInit.AMETHYST_DECEIVER.get().getDefaultState()), SimpleBlockPlacer.PLACER))
                    .tries(1024).preventProjection().build()));

    public static final ConfiguredFeature<?, ?> PUFFBALL = register("puffball", Feature.RANDOM_PATCH.withConfiguration(
            (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(BlockInit.PUFFBALL.get().getDefaultState()), SimpleBlockPlacer.PLACER))
                    .tries(1024).preventProjection().build()));

    public static final ConfiguredFeature<?, ?> BLACK_TRUMPET = register("black_trumpet", Feature.RANDOM_PATCH.withConfiguration(
            (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(BlockInit.BLACK_TRUMPET.get().getDefaultState()), SimpleBlockPlacer.PLACER))
                    .tries(1024).preventProjection().build()));






    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String key, ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, key, configuredFeature);
    }
}
