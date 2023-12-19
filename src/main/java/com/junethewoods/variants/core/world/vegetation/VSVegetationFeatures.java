package com.junethewoods.variants.core.world.vegetation;

import com.junethewoods.variants.core.init.VSBlocks;
import com.junethewoods.variants.core.world.vegetation.tree.VSTreePlacements;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleRandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

import java.util.List;

public class VSVegetationFeatures {
    public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> PAINTING_TREES = FeatureUtils.register("variants:painting_trees", Feature.RANDOM_SELECTOR,
            new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(VSTreePlacements.PAINTING_TREE_WITH_BEES_0002, 0.2f),
                    new WeightedPlacedFeature(VSTreePlacements.FANCY_PAINTING_TREE_WITH_BEES_0002, 0.1f)), VSTreePlacements.PAINTING_TREE_WITH_BEES_0002));

    public static final Holder<ConfiguredFeature<SimpleRandomFeatureConfiguration, ?>> VARIANTS_FLOWER_PATCH = FeatureUtils.register("variants:variants_flower_patch",
            Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(HolderSet.direct(PlacementUtils.inlinePlaced(Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(
                    Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(VSBlocks.GLOW_BLACK_TULIP.get())))), PlacementUtils.inlinePlaced(Feature.RANDOM_PATCH,
                    FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(VSBlocks.SUNNY_FLOWER.get())))))));

    public static final Holder<ConfiguredFeature<SimpleRandomFeatureConfiguration, ?>> GLOW_BLACK_TULIP_PATCH = FeatureUtils.register("variants:glow_black_tulip_patch",
            Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(HolderSet.direct(PlacementUtils.inlinePlaced(Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(
                    Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(VSBlocks.GLOW_BLACK_TULIP.get())))))));

    public static final Holder<ConfiguredFeature<SimpleRandomFeatureConfiguration, ?>> SUNNY_FLOWER_PATCH = FeatureUtils.register("variants:sunny_flower_patch",
            Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(HolderSet.direct(PlacementUtils.inlinePlaced(Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(
                    Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(VSBlocks.SUNNY_FLOWER.get())))))));

    public static final Holder<ConfiguredFeature<SimpleRandomFeatureConfiguration, ?>> GLOW_BERRY_BUSH_PATCH = FeatureUtils.register("variants:glow_berry_bush_patch",
            Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(HolderSet.direct(PlacementUtils.inlinePlaced(Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(
                    Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(VSBlocks.GLOW_BERRY_BUSH.get())))))));
}
