package com.junethewoods.variants.blockentity.custom;

import com.junethewoods.variants.blockentity.VSBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class VSSignBlockEntity extends SignBlockEntity {
    public VSSignBlockEntity(BlockPos pos, BlockState state) {
        super(VSBlockEntities.VS_SIGN.get(), pos, state);
    }

    @Override
    public BlockEntityType<?> getType() {
        return VSBlockEntities.VS_SIGN.get();
    }
}
