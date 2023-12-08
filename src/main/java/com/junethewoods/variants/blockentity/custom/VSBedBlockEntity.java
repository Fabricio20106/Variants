package com.junethewoods.variants.blockentity.custom;

import com.junethewoods.variants.blockentity.VSBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.entity.BedBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class VSBedBlockEntity extends BedBlockEntity {
    public VSBedBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state);
    }

    public VSBedBlockEntity(BlockPos pos, BlockState state, DyeColor color) {
        super(pos, state, color);
    }

    @Override
    public BlockEntityType<?> getType() {
        return VSBlockEntities.VS_BED.get();
    }
}
