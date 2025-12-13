package melonystudios.variants.mixin.block;

import melonystudios.variants.Variants;
import melonystudios.variants.util.VSStyles;
import net.minecraft.block.Block;
import net.minecraft.block.StainedGlassBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.*;
import net.minecraft.world.IBlockReader;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import javax.annotation.Nullable;
import java.util.List;

@Mixin(StainedGlassBlock.class)
public class RVStainedGlassBlockMixin extends Block {
    @Shadow
    @Final
    private DyeColor color;

    public RVStainedGlassBlockMixin(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable IBlockReader world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        super.appendHoverText(stack, world, tooltip, flag);
        if (flag.isAdvanced()) tooltip.add(
                new TranslationTextComponent("tooltip." + Variants.MOD_ID + ".glass_beam_color",
                new StringTextComponent(String.format("#%06X", this.color.getColorValue())))
                .withStyle(TextFormatting.GRAY)
                .append(" ")
                .append(new StringTextComponent("█").withStyle(VSStyles.getFromRGB(this.color.getColorValue())))
        );
    }
}
