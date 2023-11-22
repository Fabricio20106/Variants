package com.junethewoods.variants.block.custom.theend;

import com.junethewoods.variants.block.VSBlocks;
import net.minecraft.block.*;
import net.minecraft.util.Direction;
import net.minecraft.util.math.shapes.VoxelShape;

import java.util.Random;

public class WarpingVinesTopBlock extends AbstractTopPlantBlock {
    protected static final VoxelShape SHAPE = Block.box(4, 9, 4, 12, 16, 12);

    public WarpingVinesTopBlock(Properties properties) {
        super(properties, Direction.DOWN, SHAPE, false, 0.1D);
    }

    protected int getBlocksToGrowWhenBonemealed(Random rand) {
        return PlantBlockHelper.getBlocksToGrowWhenBonemealed(rand);
    }

    protected Block getBodyBlock() {
        return VSBlocks.WARPING_VINES_PLANT.get();
    }

    protected boolean canGrowInto(BlockState state) {
        return PlantBlockHelper.isValidGrowthState(state);
    }
}