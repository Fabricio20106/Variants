package com.junethewoods.variants.common.block.tree;

import com.junethewoods.variants.core.gen.VariantFeatures;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;

public class PaintingTree extends Tree {
    @Nullable
    @Override
    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getTreeFeature(@Nonnull Random randomIn, boolean largeHive) {
        if (randomIn.nextInt(10) == 0) {
            return largeHive ? VariantFeatures.fancy_painting_tree_bees : VariantFeatures.fancy_painting_tree;
        } else {
            return largeHive ? VariantFeatures.painting_tree_bees : VariantFeatures.painting_tree;
        }
    }
}
