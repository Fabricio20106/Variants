package com.junethewoods.variants.blockentity.custom;

import com.junethewoods.variants.blockentity.VSBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BellBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class VSBellBlockEntity extends BellBlockEntity {
    public VSBellBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state);
    }

    @Override
    public BlockEntityType<?> getType() {
        return VSBlockEntities.VS_BELL.get();
    }
}
