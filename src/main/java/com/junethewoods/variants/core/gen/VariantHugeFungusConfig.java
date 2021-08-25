package com.junethewoods.variants.core.gen;

import com.junethewoods.variants.core.init.BlockInit;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.feature.HugeFungusConfig;

public class VariantHugeFungusConfig extends HugeFungusConfig {
    public VariantHugeFungusConfig(BlockState state, BlockState state2, BlockState state3, BlockState state4, boolean p_i231958_5_) {
        super(state, state2, state3, state4, p_i231958_5_);
    }

    public static final HugeFungusConfig field_236301_e_ = new HugeFungusConfig(BlockInit.ender_nylium.get().getDefaultState(), BlockInit.ender_stem.get().getDefaultState(), BlockInit.ender_wart_block.get().getDefaultState(), Blocks.SHROOMLIGHT.getDefaultState(), true);

}
