package com.junethewoods.variants.core.world.vegetation.tree;

import com.junethewoods.variants.core.init.VSBlocks;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.BeehiveDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

import java.util.List;
import java.util.OptionalInt;

public class VSTreeFeatures {
    private static final BeehiveDecorator BEEHIVE_02_PERCENT = new BeehiveDecorator(0.002f);
    private static final BeehiveDecorator BEEHIVE_2_PERCENT = new BeehiveDecorator(0.02f);
    private static final BeehiveDecorator BEEHIVE_5_PERCENT = new BeehiveDecorator(0.05f);
    private static final BeehiveDecorator BEEHIVE_100_PERCENT = new BeehiveDecorator(1);

    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> PAINTING_TREE = FeatureUtils.register("variants:painting_tree", Feature.TREE, createPaintingTree().build());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> FANCY_PAINTING_TREE = FeatureUtils.register("variants:fancy_painting_tree", Feature.TREE, createFancyPaintingTree().build());

    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> PAINTING_TREE_WITH_BEES_0002 = FeatureUtils.register("variants:painting_tree_with_bees_0002", Feature.TREE, createPaintingTree().decorators(List.of(BEEHIVE_02_PERCENT)).build());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> PAINTING_TREE_WITH_BEES_002 = FeatureUtils.register("variants:painting_tree_with_bees_002", Feature.TREE, createPaintingTree().decorators(List.of(BEEHIVE_2_PERCENT)).build());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> PAINTING_TREE_WITH_BEES_005 = FeatureUtils.register("variants:painting_tree_with_bees_005", Feature.TREE, createPaintingTree().decorators(List.of(BEEHIVE_5_PERCENT)).build());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> PAINTING_TREE_WITH_BEES = FeatureUtils.register("variants:painting_tree_with_bees", Feature.TREE, createPaintingTree().decorators(List.of(BEEHIVE_100_PERCENT)).build());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> FANCY_PAINTING_TREE_WITH_BEES_0002 = FeatureUtils.register("variants:fancy_painting_tree_with_bees_0002", Feature.TREE, createFancyPaintingTree().decorators(List.of(BEEHIVE_02_PERCENT)).build());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> FANCY_PAINTING_TREE_WITH_BEES_002 = FeatureUtils.register("variants:fancy_painting_tree_with_bees_002", Feature.TREE, createFancyPaintingTree().decorators(List.of(BEEHIVE_2_PERCENT)).build());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> FANCY_PAINTING_TREE_WITH_BEES_005 = FeatureUtils.register("variants:fancy_painting_tree_with_bees_005", Feature.TREE, createFancyPaintingTree().decorators(List.of(BEEHIVE_5_PERCENT)).build());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> FANCY_PAINTING_TREE_WITH_BEES = FeatureUtils.register("variants:fancy_painting_tree_with_bees", Feature.TREE, createFancyPaintingTree().decorators(List.of(BEEHIVE_100_PERCENT)).build());

    private static TreeConfiguration.TreeConfigurationBuilder createPaintingTree() {
        return createStraightBlobTree(VSBlocks.PAINTING_LOG.get(), VSBlocks.PAINTING_LEAVES.get(), 4, 2, 0, 2).ignoreVines();
    }

    private static TreeConfiguration.TreeConfigurationBuilder createFancyPaintingTree() {
        return new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(VSBlocks.PAINTING_LOG.get()),
                new FancyTrunkPlacer(3, 11, 0),
                BlockStateProvider.simple(VSBlocks.PAINTING_LEAVES.get()),
                new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4),
                new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4))).ignoreVines();
    }

    private static TreeConfiguration.TreeConfigurationBuilder createStraightBlobTree(Block log, Block leaves, int trunkHeight, int trunkHeightRandA, int trunkHeightRandB, int foliageRadius) {
        return new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(log),
                new StraightTrunkPlacer(trunkHeight, trunkHeightRandA, trunkHeightRandB),
                BlockStateProvider.simple(leaves),
                new BlobFoliagePlacer(ConstantInt.of(foliageRadius), ConstantInt.of(0), 3),
                new TwoLayersFeatureSize(1, 0, 1));
    }
}
