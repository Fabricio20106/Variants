package com.junethewoods.variants.common.block.plant.theend;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RootsBlock;
import net.minecraft.world.level.block.state.BlockState;

public class EnderRootsBlock extends RootsBlock {
    public EnderRootsBlock(Properties properties) {
        super(properties);
    }

    protected boolean mayPlaceOn(BlockState state, BlockGetter getter, BlockPos pos) {
        return state.is(BlockTags.NYLIUM) || state.is(Blocks.END_STONE) || super.mayPlaceOn(state, getter, pos);
    }
}
