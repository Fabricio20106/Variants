package com.junethewoods.variants.mixin.block;

import com.junethewoods.variants.Variants;
import net.minecraft.block.Block;
import net.minecraft.block.StainedGlassPaneBlock;
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

@Mixin(StainedGlassPaneBlock.class)
public class VSStainedGlassPaneBlockMixin extends Block {
    @Shadow
    @Final
    private DyeColor color;

    public VSStainedGlassPaneBlockMixin(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable IBlockReader world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        tooltip.add(new TranslationTextComponent("tooltip." + Variants.MOD_ID + ".glass_beam_color", new StringTextComponent(String.format("#%06X", this.color.getColorValue())).withStyle(Style.EMPTY.withColor(Color.fromRgb(this.color.getColorValue()))))
                .withStyle(TextFormatting.GRAY));
        super.appendHoverText(stack, world, tooltip, flag);
    }
}
