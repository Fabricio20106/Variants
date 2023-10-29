package com.junethewoods.variants.block.custom;

import com.junethewoods.variants.util.VSTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;

public class ElderPrismarineBlock extends Block {
    public ElderPrismarineBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isConduitFrame(BlockState state, IWorldReader world, BlockPos pos, BlockPos conduit) {
        return state.is(VSTags.Blocks.CONDUIT_FRAME_BLOCKS);
    }
}
