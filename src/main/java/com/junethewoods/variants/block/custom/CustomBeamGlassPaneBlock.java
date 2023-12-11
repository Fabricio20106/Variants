package com.junethewoods.variants.block.custom;

import com.junethewoods.variants.Variants;
import net.minecraft.block.BlockState;
import net.minecraft.block.IBeaconBeamColorProvider;
import net.minecraft.block.PaneBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.Color;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;

import javax.annotation.Nullable;
import java.util.List;

public class CustomBeamGlassPaneBlock extends PaneBlock implements IBeaconBeamColorProvider {
    private final int beamColor;

    public CustomBeamGlassPaneBlock(int beamColor, Properties properties) {
        super(properties);
        this.beamColor = beamColor;
        this.registerDefaultState(this.stateDefinition.any().setValue(NORTH, false).setValue(EAST, false).setValue(SOUTH, false).setValue(WEST, false).setValue(WATERLOGGED, false));
    }

    @Nullable
    public float[] getBeaconColorMultiplier(BlockState state, IWorldReader world, BlockPos pos, BlockPos beaconPos) {
        int i = (beamColor & 16711680) >> 16;
        int j = (beamColor & '\uff00') >> 8;
        int k = (beamColor & 255);

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

    @Override
    public void appendHoverText(ItemStack stack, @Nullable IBlockReader world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        tooltip.add(new TranslationTextComponent("tooltip." + Variants.MOD_ID + ".glass_beam_color", String.format("#%06X", beamColor)).withStyle(Style.EMPTY.withColor(Color.fromRgb(beamColor))));
        super.appendHoverText(stack, world, tooltip, flag);
    }
}
