package melonystudios.variants.block.custom;

import melonystudios.variants.Variants;
import melonystudios.variants.util.VSStyles;
import net.minecraft.block.AbstractGlassBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.IBeaconBeamColorProvider;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.*;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class CustomBeamGlassBlock extends AbstractGlassBlock implements IBeaconBeamColorProvider {
    private final int beamColor;

    public CustomBeamGlassBlock(int beamColor, Properties properties) {
        super(properties);
        this.beamColor = beamColor;
    }

    @Nullable
    public float[] getBeaconColorMultiplier(BlockState state, IWorldReader world, BlockPos pos, BlockPos beaconPos) {
        int red = (this.beamColor & 16711680) >> 16;
        int green = (this.beamColor & '\uff00') >> 8;
        int blue = (this.beamColor & 255);

        float[] textureDiffuseColors = new float[] {(float) red / 255F, (float) green / 255F, (float) blue / 255F};

        if (getBlock() instanceof IBeaconBeamColorProvider) return textureDiffuseColors;
        return null;
    }

    @Override
    @Nonnull
    public DyeColor getColor() {
        return DyeColor.WHITE;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable IBlockReader world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        super.appendHoverText(stack, world, tooltip, flag);
        if (flag.isAdvanced()) tooltip.add(
                new TranslationTextComponent("tooltip." + Variants.MOD_ID + ".glass_beam_color",
                        new StringTextComponent(String.format("#%06X", this.beamColor)))
                        .withStyle(TextFormatting.GRAY)
                        .append(" ")
                        .append(new StringTextComponent("█").withStyle(VSStyles.getFromRGB(this.beamColor)))
        );
    }
}
