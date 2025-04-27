package melonystudios.variants.world.feature;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import melonystudios.variants.Variants;
import melonystudios.variants.block.VSBlocks;
import melonystudios.variants.fluid.VSFluids;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.blockplacer.DoublePlantBlockPlacer;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.blockstateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.BushFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.FancyFoliagePlacer;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.NoiseDependant;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.trunkplacer.FancyTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;

import java.util.OptionalInt;
import java.util.function.Supplier;

public class VSConfiguredFeatures {
    public static final HugeFungusConfig NOT_PLANTED_ENDERWOOD_FUNGI_CONFIG = new HugeFungusConfig(VSBlocks.ENDER_NYLIUM.get().defaultBlockState(), VSBlocks.ENDERWOOD_STEM.get().defaultBlockState(), VSBlocks.ENDER_WART_BLOCK.get()
            .defaultBlockState(), Blocks.SHROOMLIGHT.defaultBlockState(), false);
    public static final HugeFungusConfig PLANTED_ENDERWOOD_FUNGI_CONFIG = new HugeFungusConfig(VSBlocks.ENDER_NYLIUM.get().defaultBlockState(), VSBlocks.ENDERWOOD_STEM.get().defaultBlockState(), VSBlocks.ENDER_WART_BLOCK.get().defaultBlockState(),
            Blocks.SHROOMLIGHT.defaultBlockState(), true);
    public static final BlockStateProvidingFeatureConfig ENDERWOOD_FOREST_CONFIG = new BlockStateProvidingFeatureConfig(new WeightedBlockStateProvider().add(VSBlocks.ENDER_ROOTS.get().defaultBlockState(), 85).add(VSBlocks.ENDER_ROOTS.get()
                    .defaultBlockState(), 1).add(VSBlocks.ENDER_FUNGUS.get().defaultBlockState(), 13).add(VSBlocks.ENDER_FUNGUS.get().defaultBlockState(), 1));
    public static final BlockStateProvidingFeatureConfig END_SPROUTS_CONFIG = new BlockStateProvidingFeatureConfig(new SimpleBlockStateProvider(VSBlocks.END_SPROUTS.get().defaultBlockState()));
    public static final BlockClusterFeatureConfig TALL_GRASS_CONFIG = new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.TALL_GRASS.defaultBlockState()), new DoublePlantBlockPlacer()).tries(64).noProjection().build();
    public static final BlockClusterFeatureConfig LARGE_FERN_CONFIG = new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.LARGE_FERN.defaultBlockState()), new DoublePlantBlockPlacer()).tries(64).noProjection().build();
    public static final LiquidsConfig CLOSED_SOUL_LAVA_SPRING_CONFIG = new LiquidsConfig(VSFluids.SOUL_LAVA.get().defaultFluidState(), false, 5, 0, ImmutableSet.of(Blocks.NETHERRACK));

    private static final ImmutableList<Supplier<ConfiguredFeature<?, ?>>> REVARIED_FLOWERS_LIST = ImmutableList.of(
            () -> Feature.RANDOM_PATCH.configured(new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(VSBlocks.GLOW_BLACK_TULIP.get().defaultBlockState()), new SimpleBlockPlacer()).tries(64).noProjection().build()),
            () -> Feature.RANDOM_PATCH.configured(new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(VSBlocks.SUNNY_FLOWER.get().defaultBlockState()), new SimpleBlockPlacer()).tries(64).noProjection().build()));

    public static final ConfiguredFeature<?, ?> REVARIED_FLOWER_PATCH = register("revaried_flower_patch", Feature.SIMPLE_RANDOM_SELECTOR.configured(
                    new SingleRandomFeature(REVARIED_FLOWERS_LIST)).count(FeatureSpread.of(-3, 4)).decorated(Features.Placements.ADD_32)
            .decorated(Features.Placements.HEIGHTMAP_SQUARE).count(5));

    public static final ConfiguredFeature<?, ?> AZURE_BLUET_FEATURE = register("azure_bluet_feature", Feature.FLOWER.configured(new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.AZURE_BLUET.defaultBlockState()),
            SimpleBlockPlacer.INSTANCE).tries(64).build()));
    public static final ConfiguredFeature<?, ?> AZURE_BLUET_PATCH = register("azure_bluet_patch", AZURE_BLUET_FEATURE.decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP).squared().decorated(
            Placement.COUNT_NOISE.configured(new NoiseDependant(-0.8D, 15, 4)).count(12)));

    public static final ConfiguredFeature<?, ?> TALL_PLANT_PATCH = register("tall_plant_patch", Feature.SIMPLE_RANDOM_SELECTOR.configured(new SingleRandomFeature(ImmutableList.of(() -> Feature.RANDOM_PATCH.configured(TALL_GRASS_CONFIG),
            () -> Feature.RANDOM_PATCH.configured(LARGE_FERN_CONFIG)))).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE).count(15));

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
            new MultipleRandomFeatureConfig(ImmutableList.of(PAINTING_TREE_WITH_BEES_005.weighted(0.1F), FANCY_PAINTING_TREE.weighted(0.2F)), PAINTING_TREE))
            .decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(6, 0.2F, 2))));

    public static final ConfiguredFeature<?, ?> AZURE_BUSH = register("azure_bush", Feature.TREE.configured(new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState()), new SimpleBlockStateProvider(
            VSBlocks.AZURE_BLUET_LEAVES.get().defaultBlockState()), new BushFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(1), 2), new StraightTrunkPlacer(1, 0, 0),
            new TwoLayerFeature(0, 0, 0)).heightmap(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES).build()).decorated(Features.Placements.HEIGHTMAP_SQUARE));

    public static final ConfiguredFeature<?, ?> CRIMSON_WHEAT_PATCH = register("crimson_wheat_patch", Feature.SIMPLE_RANDOM_SELECTOR.configured(new SingleRandomFeature(ImmutableList.of(() -> Feature.RANDOM_PATCH.configured(
            new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(VSBlocks.WILD_CRIMSON_WHEAT.get().defaultBlockState()), new SimpleBlockPlacer()).tries(64).noProjection().build()))))
            .count(FeatureSpread.of(-3, 4)).decorated(Placement.COUNT_MULTILAYER.configured(new FeatureSpreadConfig(2))).count(1));

    public static final ConfiguredFeature<?, ?> SOUL_CARROT_PATCH = register("soul_carrot_patch", Feature.SIMPLE_RANDOM_SELECTOR.configured(new SingleRandomFeature(ImmutableList.of(() -> Feature.RANDOM_PATCH.configured(
                    new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(VSBlocks.WILD_SOUL_CARROTS.get().defaultBlockState()), new SimpleBlockPlacer()).tries(64).noProjection().build()))))
            .count(FeatureSpread.of(-3, 4)).decorated(Placement.COUNT_MULTILAYER.configured(new FeatureSpreadConfig(2))).count(1));

    public static final ConfiguredFeature<?, ?> WARPED_POTATO_PATCH = register("warped_potato_patch", Feature.SIMPLE_RANDOM_SELECTOR.configured(new SingleRandomFeature(ImmutableList.of(() -> Feature.RANDOM_PATCH.configured(
                    new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(VSBlocks.WILD_WARPED_POTATOES.get().defaultBlockState()), new SimpleBlockPlacer()).tries(64).noProjection().build()))))
            .count(FeatureSpread.of(-3, 4)).decorated(Placement.COUNT_MULTILAYER.configured(new FeatureSpreadConfig(2))).count(1));

    public static final ConfiguredFeature<?, ?> MELTING_BEET_PATCH = register("melting_beet_patch", Feature.SIMPLE_RANDOM_SELECTOR.configured(new SingleRandomFeature(ImmutableList.of(() -> Feature.RANDOM_PATCH.configured(
                    new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(VSBlocks.WILD_MELTING_BEETS.get().defaultBlockState()), new SimpleBlockPlacer()).tries(64).noProjection().build()))))
            .count(FeatureSpread.of(-3, 4)).decorated(Placement.COUNT_MULTILAYER.configured(new FeatureSpreadConfig(2))).count(1));

    public static final ConfiguredFeature<?, ?> CLOSED_SOUL_LAVA_SPRING = register("closed_soul_lava_spring", Feature.SPRING.configured(CLOSED_SOUL_LAVA_SPRING_CONFIG).decorated(Features.Placements.RANGE_10_20_ROOFED)
            .squared().count(16));
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
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, Variants.variants(name), configuredFeature);
    }

    public static void init() {}
}
