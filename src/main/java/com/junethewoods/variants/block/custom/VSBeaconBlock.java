package com.junethewoods.variants.block.custom;

import com.junethewoods.variants.blockentity.custom.VSBeaconBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.BeaconBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class VSBeaconBlock extends BeaconBlock {
    private final DyeColor beaconBeamColor;

    public VSBeaconBlock(DyeColor beaconBeamColor, Properties properties) {
        super(properties);
        this.beaconBeamColor = beaconBeamColor;
    }

    @Override
    public DyeColor getColor() {
        return this.beaconBeamColor;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new VSBeaconBlockEntity(pos, state);
    }
}
