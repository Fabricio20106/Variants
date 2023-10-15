package com.junethewoods.variants.block.custom;

import com.junethewoods.variants.blockentity.custom.VSBellBlockEntity;
import net.minecraft.block.BellBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class VSBellBlock extends BellBlock {
    public VSBellBlock(Properties properties) {
        super(properties);
    }

    @Nullable
    @Override
    public TileEntity newBlockEntity(IBlockReader world) {
        return new VSBellBlockEntity();
    }
}
