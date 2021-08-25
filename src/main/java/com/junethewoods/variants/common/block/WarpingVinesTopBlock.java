package com.junethewoods.variants.common.block;

import com.junethewoods.variants.core.init.BlockInit;
import net.minecraft.block.AbstractTopPlantBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PlantBlockHelper;
import net.minecraft.util.Direction;
import net.minecraft.util.math.shapes.VoxelShape;

import java.util.Random;

public class WarpingVinesTopBlock extends AbstractTopPlantBlock {
    protected static final VoxelShape SHAPE = Block.makeCuboidShape(4.0D, 9.0D, 4.0D, 12.0D, 16.0D, 12.0D);

    public WarpingVinesTopBlock(Properties properties) {
        super(properties, Direction.DOWN, SHAPE, false, 1.0D);
    }

    @Override
    protected int getGrowthAmount(Random rand) {
        return PlantBlockHelper.getGrowthAmount(rand);
    }

    @Override
    protected boolean canGrowIn(BlockState state) {
        return PlantBlockHelper.isAir(state);
    }

    @Override
    protected Block getBodyPlantBlock() {
        return BlockInit.warping_vines_plant.get();
    }
}
