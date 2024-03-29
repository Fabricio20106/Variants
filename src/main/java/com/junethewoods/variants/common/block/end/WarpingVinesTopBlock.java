package com.junethewoods.variants.common.block.end;

import com.junethewoods.variants.core.init.VSBlocks;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.NetherVines;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Random;

public class WarpingVinesTopBlock extends GrowingPlantHeadBlock {
    protected static final VoxelShape SHAPE = Block.box(4, 9, 4, 12, 16, 12);

    public WarpingVinesTopBlock(Properties properties) {
        super(properties, Direction.DOWN, SHAPE, false, 0.1D);
    }

    protected int getBlocksToGrowWhenBonemealed(Random rand) {
        return NetherVines.getBlocksToGrowWhenBonemealed(rand);
    }

    protected Block getBodyBlock() {
        return VSBlocks.WARPING_VINES_PLANT.get();
    }

    protected boolean canGrowInto(BlockState state) {
        return NetherVines.isValidGrowthState(state);
    }
}
