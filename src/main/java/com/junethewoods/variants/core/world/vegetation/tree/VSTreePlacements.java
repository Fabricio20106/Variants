package com.junethewoods.variants.core.world.vegetation.tree;

import com.junethewoods.variants.core.init.VSBlocks;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class VSTreePlacements {
    public static final Holder<PlacedFeature> PAINTING_TREE_CHECKED = PlacementUtils.register("variants:painting_tree_checked", VSTreeFeatures.PAINTING_TREE,
            PlacementUtils.filteredByBlockSurvival(VSBlocks.PAINTING_SAPLING.get()));
    public static final Holder<PlacedFeature> PAINTING_TREE_WITH_BEES_0002 = PlacementUtils.register("variants:painting_tree_with_bees_0002", VSTreeFeatures.PAINTING_TREE_WITH_BEES_002,
            PlacementUtils.filteredByBlockSurvival(VSBlocks.PAINTING_SAPLING.get()));
    public static final Holder<PlacedFeature> FANCY_PAINTING_TREE_CHECKED = PlacementUtils.register("variants:fancy_painting_tree_checked", VSTreeFeatures.FANCY_PAINTING_TREE,
            PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING));
    public static final Holder<PlacedFeature> FANCY_PAINTING_TREE_WITH_BEES = PlacementUtils.register("variants:fancy_painting_tree_with_bees", VSTreeFeatures.FANCY_PAINTING_TREE_WITH_BEES,
            PlacementUtils.filteredByBlockSurvival(VSBlocks.PAINTING_SAPLING.get()));
    public static final Holder<PlacedFeature> FANCY_PAINTING_TREE_WITH_BEES_0002 = PlacementUtils.register("variants:fancy_painting_tree_with_bees_0002", VSTreeFeatures.FANCY_PAINTING_TREE_WITH_BEES_0002,
            PlacementUtils.filteredByBlockSurvival(VSBlocks.PAINTING_SAPLING.get()));
}
