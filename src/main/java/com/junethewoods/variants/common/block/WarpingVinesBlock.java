package com.junethewoods.variants.common.block;

import com.junethewoods.variants.core.init.BlockInit;
import net.minecraft.block.AbstractBodyPlantBlock;
import net.minecraft.block.AbstractTopPlantBlock;
import net.minecraft.block.Block;
import net.minecraft.util.Direction;
import net.minecraft.util.math.shapes.VoxelShape;

public class WarpingVinesBlock extends AbstractBodyPlantBlock {
    public static final VoxelShape SHAPE = Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);

    public WarpingVinesBlock(Properties properties) {
        super(properties, Direction.DOWN, SHAPE, false);
    }

    @Override
    public AbstractTopPlantBlock getTopPlantBlock() {
        return (AbstractTopPlantBlock) BlockInit.warping_vines.get();
    }
}
