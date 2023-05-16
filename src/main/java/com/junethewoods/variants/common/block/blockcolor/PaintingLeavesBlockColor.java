package com.junethewoods.variants.common.block.blockcolor;

import net.minecraft.client.color.block.BlockColor;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class PaintingLeavesBlockColor implements BlockColor {
    @Override
    public int getColor(BlockState state, @Nullable BlockAndTintGetter blockReader, @Nullable BlockPos pos, int i) {
        return 0x79C05A;
    }
}
