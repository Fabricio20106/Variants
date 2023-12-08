package com.junethewoods.variants.block.custom;

import com.junethewoods.variants.util.VSTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class GoldenCarrotsBlock extends CropBlock {
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
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(VSTags.Blocks.GOLDEN_CARROTS_PLANTABLE_ON);
    }

    protected ItemLike getBaseSeedId() {
        return Items.GOLDEN_CARROT;
    }

    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPES[state.getValue(this.getAgeProperty())];
    }
}
