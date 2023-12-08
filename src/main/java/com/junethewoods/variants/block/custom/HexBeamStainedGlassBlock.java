package com.junethewoods.variants.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.AbstractGlassBlock;
import net.minecraft.world.level.block.BeaconBeamBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class HexBeamStainedGlassBlock extends AbstractGlassBlock implements BeaconBeamBlock {
    private final int beaconBeamColor;

    public HexBeamStainedGlassBlock(int beaconBeamColor, Properties properties) {
        super(properties);
        this.beaconBeamColor = beaconBeamColor;
    }

    @Nullable
    public float[] getBeaconColorMultiplier(BlockState state, LevelReader level, BlockPos pos, BlockPos beaconPos) {
        int i = (beaconBeamColor & 16711680) >> 16;
        int j = (beaconBeamColor & '\uff00') >> 8;
        int k = (beaconBeamColor & 255);

        float[] textureDiffuseColors = new float[] {(float) i / 255f, (float) j / 255f, (float) k / 255f};

        if (getBlock() instanceof BeaconBeamBlock) {
            return textureDiffuseColors;
        }

        return null;
    }

    public Block getBlock() {
        return this;
    }

    @Override
    public DyeColor getColor() {
        return DyeColor.WHITE;
    }
}
