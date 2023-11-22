package com.junethewoods.variants.block.custom.theend;

import com.junethewoods.variants.util.VSTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.NetherRootsBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class EnderRootsBlock extends NetherRootsBlock {
    public EnderRootsBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, IBlockReader world, BlockPos pos) {
        return state.is(VSTags.Blocks.END_PLANTS_PLANTABLE_ON) || super.mayPlaceOn(state, world, pos);
    }
}
