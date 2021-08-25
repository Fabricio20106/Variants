package com.junethewoods.variants.common.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.NetherRootsBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class EnderRootsBlock extends NetherRootsBlock {
    public EnderRootsBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return state.isIn(Blocks.END_STONE) || super.isValidGround(state, worldIn, pos);
    }
}
