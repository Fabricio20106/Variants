package com.junethewoods.variants.block.custom;

import com.junethewoods.variants.blockentity.custom.VSSignBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.StandingSignBlock;
import net.minecraft.block.WoodType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class VSStandingSignBlock extends StandingSignBlock {
    public VSStandingSignBlock(Properties properties, WoodType type) {
        super(properties, type);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new VSSignBlockEntity();
    }
}
