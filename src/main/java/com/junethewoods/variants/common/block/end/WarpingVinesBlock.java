package com.junethewoods.variants.common.block.end;

import com.junethewoods.variants.core.init.VSBlocks;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantBodyBlock;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.phys.shapes.VoxelShape;

public class WarpingVinesBlock extends GrowingPlantBodyBlock {
    public static final VoxelShape SHAPE = Block.box(1, 0, 1, 15, 16, 15);

    public WarpingVinesBlock(Properties properties) {
        super(properties, Direction.DOWN, SHAPE, false);
    }

    protected GrowingPlantHeadBlock getHeadBlock() {
        return (GrowingPlantHeadBlock) VSBlocks.WARPING_VINES.get();
    }
}
