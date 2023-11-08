package com.junethewoods.variants.block.custom;

import com.junethewoods.variants.blockentity.custom.VSBedBlockEntity;
import net.minecraft.block.BedBlock;
import net.minecraft.item.DyeColor;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class VSBedBlock extends BedBlock {
    public VSBedBlock(DyeColor color, Properties properties) {
        super(color, properties);
    }

    @Override
    public TileEntity newBlockEntity(IBlockReader world) {
        return new VSBedBlockEntity();
    }
}
