package com.junethewoods.variants.world.feature;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.junethewoods.variants.Variants;
import com.junethewoods.variants.block.VSBlocks;
import com.junethewoods.variants.fluid.VSFluids;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.blockstateprovider.WeightedBlockStateProvider;
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
    public static final HugeFungusConfig NOT_PLANTED_ENDERWOOD_FUNGI_CONFIG = new HugeFungusConfig(VSBlocks.ENDER_NYLIUM.get().defaultBlockState(), VSBlocks.ENDERWOOD_STEM.get().defaultBlockState(), VSBlocks.ENDER_WART_BLOCK.get()
            .defaultBlockState(), Blocks.SHROOMLIGHT.defaultBlockState(), false);
    public static final HugeFungusConfig PLANTED_ENDERWOOD_FUNGI_CONFIG = new HugeFungusConfig(VSBlocks.ENDER_NYLIUM.get().defaultBlockState(), VSBlocks.ENDERWOOD_STEM.get().defaultBlockState(), VSBlocks.ENDER_WART_BLOCK.get().defaultBlockState(),
            Blocks.SHROOMLIGHT.defaultBlockState(), true);
    public static final BlockStateProvidingFeatureConfig ENDERWOOD_FOREST_CONFIG = new BlockStateProvidingFeatureConfig(new WeightedBlockStateProvider().add(VSBlocks.ENDER_ROOTS.get().defaultBlockState(), 85).add(VSBlocks.ENDER_ROOTS.get()
                    .defaultBlockState(), 1).add(VSBlocks.ENDER_FUNGUS.get().defaultBlockState(), 13).add(VSBlocks.ENDER_FUNGUS.get().defaultBlockState(), 1));
    public static final BlockStateProvidingFeatureConfig END_SPROUTS_CONFIG = new BlockStateProvidingFeatureConfig(new SimpleBlockStateProvider(VSBlocks.END_SPROUTS.get().defaultBlockState()));
    public static final LiquidsConfig CLOSED_SOUL_LAVA_SPRING_CONFIG = new LiquidsConfig(VSFluids.SOUL_LAVA.get().defaultFluidState(), false, 5, 0, ImmutableSet.of(Blocks.NETHERRACK));

    private static final ImmutableList<Supplier<ConfiguredFeature<?, ?>>> VARIANTS_FLOWERS_LIST = ImmutableList.of(
            () -> Feature.RANDOM_PATCH.configured(new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(VSBlocks.GLOW_BLACK_TULIP.get().defaultBlockState()), new SimpleBlockPlacer()).tries(64).noProjection().build()),
            () -> Feature.RANDOM_PATCH.configured(new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(VSBlocks.SUNNY_FLOWER.get().defaultBlockState()), new SimpleBlockPlacer()).tries(64).noProjection().build()));

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

    public static final ConfiguredFeature<?, ?> CLOSED_SOUL_LAVA_SPRING = register("closed_soul_lava_spring", Feature.SPRING.configured(CLOSED_SOUL_LAVA_SPRING_CONFIG).decorated(Features.Placements.RANGE_10_20_ROOFED)
            .squared().count(16));
    public static final ConfiguredFeature<?, ?> DOUBLE_CLOSED_SOUL_LAVA_SPRING = register("double_closed_soul_lava_spring", Feature.SPRING.configured(CLOSED_SOUL_LAVA_SPRING_CONFIG).decorated(Features.Placements.RANGE_10_20_ROOFED)
            .squared().count(32));
    public static final ConfiguredFeature<?, ?> OPEN_SOUL_LAVA_SPRING = register("open_soul_lava_spring", Feature.SPRING.configured(new LiquidsConfig(VSFluids.SOUL_LAVA.get().defaultFluidState(), false, 4, 1,
            ImmutableSet.of(Blocks.NETHERRACK))).decorated(Features.Placements.RANGE_4_8_ROOFED).squared().count(8));

    // Enderwood Forest biome features:
    public static final ConfiguredFeature<?, ?> ENDERWOOD_FUNGI = register("enderwood_fungi", Feature.HUGE_FUNGUS.configured(NOT_PLANTED_ENDERWOOD_FUNGI_CONFIG).decorated(Placement.COUNT_MULTILAYER.configured(new
            FeatureSpreadConfig(8))));
    public static final ConfiguredFeature<HugeFungusConfig, ?> PLANTED_ENDERWOOD_FUNGI = register("planted_enderwood_fungi", Feature.HUGE_FUNGUS.configured(PLANTED_ENDERWOOD_FUNGI_CONFIG));

    public static final ConfiguredFeature<?, ?> ENDERWOOD_FOREST_VEGETATION = register("enderwood_forest_vegetation", Feature.NETHER_FOREST_VEGETATION.configured(ENDERWOOD_FOREST_CONFIG).decorated(Placement.COUNT_MULTILAYER.configured(
            new FeatureSpreadConfig(5))));
    public static final ConfiguredFeature<?, ?> END_SPROUTS = register("end_sprouts", Feature.NETHER_FOREST_VEGETATION.configured(END_SPROUTS_CONFIG).decorated(Placement.COUNT_MULTILAYER.configured(new FeatureSpreadConfig(4))));

    public static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, Variants.resourceLoc(name), configuredFeature);
    }
}
