package com.junethewoods.variants.block.custom;

import com.junethewoods.variants.blockentity.custom.VSBeaconBlockEntity;
import net.minecraft.block.BeaconBlock;
import net.minecraft.item.DyeColor;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class VSBeaconBlock extends BeaconBlock {
    public VSBeaconBlock(Properties properties) {
        super(properties);
    }

    @Override
    public DyeColor getColor() {
        return DyeColor.YELLOW;
    }

    @Override
    public TileEntity newBlockEntity(IBlockReader world) {
        return new VSBeaconBlockEntity();
    }
}
