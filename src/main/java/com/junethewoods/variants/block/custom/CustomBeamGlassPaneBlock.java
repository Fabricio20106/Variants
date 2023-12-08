package com.junethewoods.variants.block.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.IBeaconBeamColorProvider;
import net.minecraft.block.PaneBlock;
import net.minecraft.item.DyeColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;

import javax.annotation.Nullable;

public class CustomBeamGlassPaneBlock extends PaneBlock implements IBeaconBeamColorProvider {
    private final int beaconBeamColor;

    public CustomBeamGlassPaneBlock(int beaconBeamColor, Properties properties) {
        super(properties);
        this.beaconBeamColor = beaconBeamColor;
        this.registerDefaultState(this.stateDefinition.any().setValue(NORTH, false).setValue(EAST, false).setValue(SOUTH, false).setValue(WEST, false).setValue(WATERLOGGED, false));
    }

    @Nullable
    public float[] getBeaconColorMultiplier(BlockState state, IWorldReader world, BlockPos pos, BlockPos beaconPos) {
        int i = (beaconBeamColor & 16711680) >> 16;
        int j = (beaconBeamColor & '\uff00') >> 8;
        int k = (beaconBeamColor & 255);

        float[] textureDiffuseColors = new float[] {(float) i / 255f, (float) j / 255f, (float) k / 255f};

        if (getBlock() instanceof IBeaconBeamColorProvider) {
            return textureDiffuseColors;
        }

        return null;
    }

    @Override
    public DyeColor getColor() {
        return DyeColor.WHITE;
    }
}
