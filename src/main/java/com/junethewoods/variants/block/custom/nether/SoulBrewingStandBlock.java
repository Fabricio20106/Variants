package com.junethewoods.variants.block.custom.nether;

import com.junethewoods.variants.blockentity.custom.VSBrewingStandBlockEntity;
import net.minecraft.block.BrewingStandBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class SoulBrewingStandBlock extends BrewingStandBlock {
    public SoulBrewingStandBlock(Properties properties) {
        super(properties);
    }

    @Override
    public TileEntity newBlockEntity(IBlockReader world) {
        return new VSBrewingStandBlockEntity();
    }
}
