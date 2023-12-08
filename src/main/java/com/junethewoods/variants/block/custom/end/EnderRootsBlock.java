package com.junethewoods.variants.block.custom.end;

import com.junethewoods.variants.util.VSTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.RootsBlock;
import net.minecraft.world.level.block.state.BlockState;

public class EnderRootsBlock extends RootsBlock {
    public EnderRootsBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(VSTags.Blocks.END_PLANTS_PLANTABLE_ON) || super.mayPlaceOn(state, level, pos);
    }
}
