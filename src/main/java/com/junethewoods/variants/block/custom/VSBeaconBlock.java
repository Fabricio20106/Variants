package com.junethewoods.variants.block.custom;

import com.junethewoods.variants.blockentity.custom.VSBeaconBlockEntity;
import net.minecraft.block.BeaconBlock;
import net.minecraft.item.DyeColor;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

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
    public TileEntity newBlockEntity(IBlockReader world) {
        return new VSBeaconBlockEntity();
    }
}
