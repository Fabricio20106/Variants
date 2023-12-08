package com.junethewoods.variants.block.custom;

import com.junethewoods.variants.blockentity.custom.VSBedBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class VSBedBlock extends BedBlock {
    private final DyeColor color;

    public VSBedBlock(DyeColor color, Properties properties) {
        super(color, properties);
        this.color = color;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new VSBedBlockEntity(pos, state, color);
    }
}
