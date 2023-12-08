package com.junethewoods.variants.block.custom.end;

import com.junethewoods.variants.block.VSBlocks;
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

    @Override
    protected GrowingPlantHeadBlock getHeadBlock() {
        return (GrowingPlantHeadBlock) VSBlocks.WARPING_VINES.get();
    }
}
