package com.junethewoods.variants.common.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.NetherSproutsBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class EnderSproutsBlock extends NetherSproutsBlock {
    public EnderSproutsBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return state.isIn(Blocks.END_STONE) || super.isValidGround(state, worldIn, pos);
    }
}
