package com.junethewoods.variants.block.custom.end;

import com.junethewoods.variants.util.VSTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.FungusBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.HugeFungusConfig;

import java.util.function.Supplier;

public class EnderFungusBlock extends FungusBlock {
    public EnderFungusBlock(Properties properties, Supplier<ConfiguredFeature<HugeFungusConfig, ?>> fungusConfig) {
        super(properties, fungusConfig);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, IBlockReader world, BlockPos pos) {
        return state.is(VSTags.Blocks.END_PLANTS_PLANTABLE_ON) || super.mayPlaceOn(state, world, pos);
    }
}
