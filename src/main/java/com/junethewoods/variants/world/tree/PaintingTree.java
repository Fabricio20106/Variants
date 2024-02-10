package com.junethewoods.variants.world.tree;

import com.junethewoods.variants.world.feature.VSConfiguredFeatures;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import javax.annotation.Nullable;
import java.util.Random;

public class PaintingTree extends Tree {
    @Nullable
    @Override
    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getConfiguredFeature(Random rand, boolean beehive) {
        if (rand.nextInt(10) == 0) {
            return beehive ? VSConfiguredFeatures.FANCY_PAINTING_TREE_WITH_BEES_005 : VSConfiguredFeatures.FANCY_PAINTING_TREE;
        } else {
            return beehive ? VSConfiguredFeatures.PAINTING_TREE_WITH_BEES_005 : VSConfiguredFeatures.PAINTING_TREE;
        }
    }
}
