package com.junethewoods.variants.world.carver.custom;

import com.junethewoods.variants.util.VSTags;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.world.gen.carver.CaveWorldCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;

public class EndCaveCarver extends CaveWorldCarver {
    public EndCaveCarver(Codec<ProbabilityConfig> codec) {
        super(codec, 256);
    }

    @Override
    protected boolean canReplaceBlock(BlockState state) {
        return state.is(VSTags.Blocks.END_CARVER_REPLACEABLES);
    }
}
