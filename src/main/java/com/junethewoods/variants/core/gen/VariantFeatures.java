package com.junethewoods.variants.core.gen;

import com.google.common.collect.ImmutableList;
import com.junethewoods.variants.core.Variants;
import com.junethewoods.variants.core.init.BlockInit;
import net.minecraft.block.BlockState;
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

public class VariantFeatures {

    // Trees
    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> painting_tree = register("painting",
            Feature.TREE.withConfiguration((new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(States.painting_log),
                    new SimpleBlockStateProvider(States.painting_leaves),
                    new BlobFoliagePlacer(FeatureSpread.func_242252_a(2), FeatureSpread.func_242252_a(0), 3),
                    new StraightTrunkPlacer(4, 2, 0),
                    new TwoLayerFeature(1, 0, 1)))
                    .setIgnoreVines().build()));
    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> painting_tree_bees = register("painting_bees_005",
            Feature.TREE.withConfiguration(painting_tree.getConfig()
                    .func_236685_a_(ImmutableList.of(Features.Placements.BEES_005_PLACEMENT))));
    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> fancy_painting_tree = register("fancy_painting",
            Feature.TREE.withConfiguration((new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(States.painting_log),
                    new SimpleBlockStateProvider(States.painting_leaves),
                    new FancyFoliagePlacer(FeatureSpread.func_242252_a(2), FeatureSpread.func_242252_a(4), 4),
                    new FancyTrunkPlacer(3, 11, 0),
                    new TwoLayerFeature(0, 0, 0, OptionalInt.of(4))))
                    .setIgnoreVines().func_236702_a_(Heightmap.Type.MOTION_BLOCKING).build()));
    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> fancy_painting_tree_bees = register("fancy_painting_bees_005",
            Feature.TREE.withConfiguration(fancy_painting_tree.getConfig()
                    .func_236685_a_(ImmutableList.of(Features.Placements.BEES_005_PLACEMENT))));
    public static final ConfiguredFeature<?, ?> painting_trees = register("painting_trees",
            Feature.RANDOM_SELECTOR.withConfiguration(new MultipleRandomFeatureConfig(ImmutableList.of(fancy_painting_tree.withChance(0.2F),
                    painting_tree_bees.withChance(0.1F)), painting_tree)).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT)
                    .withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(10, 0.1F, 1))));

    public static final ConfiguredFeature<HugeFungusConfig, ?> ender_fungi_planted = register("ender_fungi_planted",
            Feature.HUGE_FUNGUS.withConfiguration(VariantHugeFungusConfig.field_236301_e_));
    public static final BlockStateProvidingFeatureConfig ender_forest_vegetation_config = new BlockStateProvidingFeatureConfig(new WeightedBlockStateProvider()
            .addWeightedBlockstate(States.ender_roots, 85).addWeightedBlockstate(States.ender_fungus, 13).addWeightedBlockstate(States.ender_sprouts,
                    10));
    public static final ConfiguredFeature<?, ?> ender_forest_vegetation = register("ender_forest_vegetation", Feature.NETHER_FOREST_VEGETATION.withConfiguration(
            ender_forest_vegetation_config).withPlacement(Placement.COUNT_MULTILAYER.configure(new FeatureSpreadConfig(5))));
    public static final BlockStateProvidingFeatureConfig ender_sprouts_config = new BlockStateProvidingFeatureConfig(new SimpleBlockStateProvider(States.ender_sprouts));
    public static final ConfiguredFeature<?, ?> ender_nylium_patch = register("ender_nylium_patch", Feature.ICE_PATCH.withConfiguration(new
            SphereReplaceConfig(States.ender_nylium, FeatureSpread.func_242253_a(2, 1), 1, ImmutableList.of(States.DIRT, States.GRASS_BLOCK,
            States.PODZOL, States.COARSE_DIRT, States.MYCELIUM, States.SNOW_BLOCK, States.ICE))).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT)
            .func_242731_b(2));

    // Flowers
    private static final ImmutableList<Supplier<ConfiguredFeature<?, ?>>> roses = ImmutableList.of(
            () -> Feature.RANDOM_PATCH.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(States.glow_black_tulip), new SimpleBlockPlacer())).tries(64).func_227317_b_().build()),
            () -> Feature.RANDOM_PATCH.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(States.glow_berry_bush), new SimpleBlockPlacer())).tries(64).func_227317_b_().build()),
            () -> Feature.RANDOM_PATCH.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(States.sunny_flower), new SimpleBlockPlacer())).tries(64).func_227317_b_().build()));

    public static final ConfiguredFeature<?, ?> glow_black_roses = register("glow_black_roses", Feature.SIMPLE_RANDOM_SELECTOR.withConfiguration(new
            SingleRandomFeature(roses)).func_242730_a(FeatureSpread.func_242253_a(-3, 4)).withPlacement(Features.Placements.VEGETATION_PLACEMENT)
            .withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).func_242731_b(5));

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String identifier, ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, Variants.resourceLoc(identifier), configuredFeature);
    }

    public static final class States {
        public static final BlockState glow_black_tulip = BlockInit.glow_black_tulip.get().getDefaultState();
        public static final BlockState glow_berry_bush = BlockInit.glow_berry_bush.get().getDefaultState();
        public static final BlockState sunny_flower = BlockInit.sunny_flower.get().getDefaultState();
        public static final BlockState painting_leaves = BlockInit.painting_leaves.get().getDefaultState();
        public static final BlockState painting_log = BlockInit.painting_log.get().getDefaultState();
        public static final BlockState ender_wart_block = BlockInit.ender_wart_block.get().getDefaultState();
        public static final BlockState ender_stem = BlockInit.ender_stem.get().getDefaultState();
        public static final BlockState ender_fungus = BlockInit.ender_fungus.get().getDefaultState();
        public static final BlockState ender_roots = BlockInit.ender_roots.get().getDefaultState();
        public static final BlockState ender_sprouts = BlockInit.ender_sprouts.get().getDefaultState();
        public static final BlockState ender_nylium = BlockInit.ender_nylium.get().getDefaultState();
        public static final BlockState GRASS_BLOCK = Blocks.GRASS.getDefaultState();
        public static final BlockState DIRT = Blocks.DIRT.getDefaultState();
        public static final BlockState PODZOL = Blocks.PODZOL.getDefaultState();
        public static final BlockState COARSE_DIRT = Blocks.COARSE_DIRT.getDefaultState();
        public static final BlockState MYCELIUM = Blocks.MYCELIUM.getDefaultState();
        public static final BlockState ICE = Blocks.ICE.getDefaultState();
        public static final BlockState SNOW_BLOCK = Blocks.SNOW_BLOCK.getDefaultState();
    }
}
