package com.junethewoods.variants.block.custom.end;

import com.junethewoods.variants.block.VSBlocks;
import com.junethewoods.variants.util.VSTags;
import net.minecraft.core.BlockPos;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.FungusBlock;
import net.minecraft.world.level.block.state.BlockState;

public class EnderFungusBlock extends FungusBlock {
    public EnderFungusBlock(Properties properties) {
        super(properties, TreeFeatures.CRIMSON_FUNGUS_PLANTED, VSBlocks.ENDER_NYLIUM.get());
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(VSTags.Blocks.END_PLANTS_PLANTABLE_ON) || super.mayPlaceOn(state, level, pos);
    }
}
