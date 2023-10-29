package com.junethewoods.variants.block.custom;

import com.junethewoods.variants.util.VSTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropsBlock;
import net.minecraft.item.Items;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class GoldenCarrotsBlock extends CropsBlock {
    private static final VoxelShape[] SHAPES = new VoxelShape[] {
            Block.box(0d, 0d, 0d, 16d, 2d, 16d),
            Block.box(0d, 0d, 0d, 16d, 3d, 16d),
            Block.box(0d, 0d, 0d, 16d, 4d, 16d),
            Block.box(0d, 0d, 0d, 16d, 5d, 16d),
            Block.box(0d, 0d, 0d, 16d, 6d, 16d),
            Block.box(0d, 0d, 0d, 16d, 7d, 16d),
            Block.box(0d, 0d, 0d, 16d, 8d, 16d),
            Block.box(0d, 0d, 0d, 16d, 9d, 16d)};

    public GoldenCarrotsBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, IBlockReader world, BlockPos pos) {
        return state.is(VSTags.Blocks.GOLDEN_CARROTS_PLANTABLE_ON);
    }

    protected IItemProvider getBaseSeedId() {
        return Items.GOLDEN_CARROT;
    }

    public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
        return SHAPES[state.getValue(this.getAgeProperty())];
    }
}
