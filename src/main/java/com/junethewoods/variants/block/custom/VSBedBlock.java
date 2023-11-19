package com.junethewoods.variants.block.custom;

import com.junethewoods.variants.blockentity.custom.VSBedBlockEntity;
import net.minecraft.block.BedBlock;
import net.minecraft.item.DyeColor;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class VSBedBlock extends BedBlock {
    private final DyeColor color;

    public VSBedBlock(DyeColor color, Properties properties) {
        super(color, properties);
        this.color = color;
    }

    @Override
    public TileEntity newBlockEntity(IBlockReader world) {
        return new VSBedBlockEntity(color);
    }
}
