package com.junethewoods.variants.block.custom;

import com.junethewoods.variants.blockentity.custom.VSBellBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BellBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class VSBellBlock extends BellBlock {
    public VSBellBlock(Properties properties) {
        super(properties);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new VSBellBlockEntity(pos, state);
    }
}
