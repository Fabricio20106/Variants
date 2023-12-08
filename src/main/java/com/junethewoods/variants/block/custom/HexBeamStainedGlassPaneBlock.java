package com.junethewoods.variants.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BeaconBeamBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class HexBeamStainedGlassPaneBlock extends IronBarsBlock implements BeaconBeamBlock {
    private final int beaconBeamColor;

    public HexBeamStainedGlassPaneBlock(int beaconBeamColor, Properties properties) {
        super(properties);
        this.beaconBeamColor = beaconBeamColor;
        this.registerDefaultState(this.stateDefinition.any().setValue(NORTH, false).setValue(EAST, false).setValue(SOUTH, false).setValue(WEST, false).setValue(WATERLOGGED, false));
    }

    @Nullable
    public float[] getBeaconColorMultiplier(BlockState state, LevelReader level, BlockPos pos, BlockPos beaconPos) {
        int i = (beaconBeamColor & 16711680) >> 16;
        int j = (beaconBeamColor & '\uff00') >> 8;
        int k = (beaconBeamColor & 255) >> 0;

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
