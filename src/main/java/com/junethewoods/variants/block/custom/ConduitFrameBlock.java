package com.junethewoods.variants.block.custom;

import com.junethewoods.variants.util.VSTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class ConduitFrameBlock extends Block {
    public ConduitFrameBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isConduitFrame(BlockState state, LevelReader level, BlockPos pos, BlockPos conduit) {
        return state.is(VSTags.Blocks.CONDUIT_FRAME_BLOCKS);
    }
}
