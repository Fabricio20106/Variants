package com.junethewoods.variants.world.feature;

import com.google.common.collect.ImmutableList;
import com.junethewoods.variants.Variants;
import com.junethewoods.variants.block.VSBlocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;

import java.util.function.Supplier;

public class VSFeatures {
    private static final ImmutableList<Supplier<ConfiguredFeature<?, ?>>> VARIANTS_FLOWERS_LIST = ImmutableList.of(
            () -> Feature.RANDOM_PATCH.configured(new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(VSBlocks.GLOW_BLACK_TULIP.get().defaultBlockState()), new SimpleBlockPlacer()).tries(64).noProjection().build())
    );

    public static final ConfiguredFeature<?, ?> VARIANTS_FLOWER_PATCH = register("variants_flower_patch", Feature.SIMPLE_RANDOM_SELECTOR.configured(
                    new SingleRandomFeature(VARIANTS_FLOWERS_LIST)).count(FeatureSpread.of(-3, 4)).decorated(Features.Placements.ADD_32)
            .decorated(Features.Placements.HEIGHTMAP_SQUARE).count(5));

    public static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, Variants.resourceLoc(name), configuredFeature);
    }
}
