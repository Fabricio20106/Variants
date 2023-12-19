package com.junethewoods.variants.common.block.nether;

import com.junethewoods.variants.core.init.VSBlocks;
import com.junethewoods.variants.core.init.VSItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.PotatoBlock;
import net.minecraft.world.level.block.state.BlockState;

public class WarpedPotatoesBlock extends PotatoBlock {
    public WarpedPotatoesBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return VSItems.WARPED_POTATO.get();
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter getter, BlockPos pos) {
        return state.is(VSBlocks.CRIMSON_FARMLAND.get()) || state.is(VSBlocks.WARPED_FARMLAND.get()) || state.is(VSBlocks.ENDER_FARMLAND.get());
    }
}
