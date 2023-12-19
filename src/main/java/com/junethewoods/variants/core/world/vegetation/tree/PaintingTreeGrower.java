package com.junethewoods.variants.core.world.vegetation.tree;

import net.minecraft.core.Holder;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import javax.annotation.Nullable;
import java.util.Random;

public class PaintingTreeGrower extends AbstractTreeGrower {
    @Nullable
    protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(Random random, boolean beehive) {
        if (random.nextInt(10) == 0) {
            return beehive ? VSTreeFeatures.FANCY_PAINTING_TREE_WITH_BEES : VSTreeFeatures.FANCY_PAINTING_TREE;
        } else {
            return beehive ? VSTreeFeatures.PAINTING_TREE_WITH_BEES : VSTreeFeatures.PAINTING_TREE;
        }
    }
}
