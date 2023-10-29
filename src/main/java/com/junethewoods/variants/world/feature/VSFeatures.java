package com.junethewoods.variants.world.feature;

import com.google.common.collect.ImmutableList;
import com.junethewoods.variants.Variants;
import com.junethewoods.variants.block.VSBlocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.FancyFoliagePlacer;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.trunkplacer.FancyTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;

import java.util.OptionalInt;
import java.util.function.Supplier;

public class VSFeatures {
    private static final ImmutableList<Supplier<ConfiguredFeature<?, ?>>> VARIANTS_FLOWERS_LIST = ImmutableList.of(
            () -> Feature.RANDOM_PATCH.configured(new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(VSBlocks.GLOW_BLACK_TULIP.get().defaultBlockState()),
                    new SimpleBlockPlacer()).tries(64).noProjection().build()));

    public static final ConfiguredFeature<?, ?> VARIANTS_FLOWER_PATCH = register("variants_flower_patch", Feature.SIMPLE_RANDOM_SELECTOR.configured(
                    new SingleRandomFeature(VARIANTS_FLOWERS_LIST)).count(FeatureSpread.of(-3, 4)).decorated(Features.Placements.ADD_32)
            .decorated(Features.Placements.HEIGHTMAP_SQUARE).count(5));

    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> PAINTING_TREE = register("painting_tree", Feature.TREE.configured(
            new BaseTreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(VSBlocks.PAINTING_LOG.get().defaultBlockState()),
                    new SimpleBlockStateProvider(VSBlocks.PAINTING_LEAVES.get().defaultBlockState()),
                    new BlobFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(0), 3),
                    new StraightTrunkPlacer(4, 2, 0),
                    new TwoLayerFeature(1, 0, 1)).ignoreVines().build()));

    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> FANCY_PAINTING_TREE = register("fancy_painting_tree", Feature.TREE.configured(
            new BaseTreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(VSBlocks.PAINTING_LOG.get().defaultBlockState()),
                    new SimpleBlockStateProvider(VSBlocks.PAINTING_LEAVES.get().defaultBlockState()),
                    new FancyFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(4), 4),
                    new FancyTrunkPlacer(3, 11, 0),
                    new TwoLayerFeature(0, 0, 0, OptionalInt.of(4))).ignoreVines().heightmap(Heightmap.Type.MOTION_BLOCKING).build()));

    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> PAINTING_TREE_WITH_BEES_005 = register("painting_tree_with_bees", Feature.TREE.configured(
            PAINTING_TREE.config().withDecorators(ImmutableList.of(Features.Placements.BEEHIVE_005))));

    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> FANCY_PAINTING_TREE_WITH_BEES_005 = register("fancy_painting_tree_with_bees", Feature.TREE.configured(
            FANCY_PAINTING_TREE.config().withDecorators(ImmutableList.of(Features.Placements.BEEHIVE_005))));

    public static final ConfiguredFeature<?, ?> PAINTINGWOOD_FOREST_TREES = register("paintingwood_forest_trees", Feature.RANDOM_SELECTOR.configured(
            new MultipleRandomFeatureConfig(ImmutableList.of(PAINTING_TREE_WITH_BEES_005.weighted(0.1F), FANCY_PAINTING_TREE.weighted(0.1F)), PAINTING_TREE))
            .decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(2, 0.1F, 1))));

    public static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, Variants.resourceLoc(name), configuredFeature);
    }
}
