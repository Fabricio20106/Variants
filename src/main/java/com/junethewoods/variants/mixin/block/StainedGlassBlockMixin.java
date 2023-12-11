package com.junethewoods.variants.mixin.block;

import com.junethewoods.variants.Variants;
import net.minecraft.block.Block;
import net.minecraft.block.StainedGlassBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.Color;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import javax.annotation.Nullable;
import java.util.List;

@Mixin(StainedGlassBlock.class)
public class StainedGlassBlockMixin extends Block {
    @Shadow @Final
    private DyeColor color;

    public StainedGlassBlockMixin(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable IBlockReader world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        tooltip.add(new TranslationTextComponent("tooltip." + Variants.MOD_ID + ".glass_beam_color", String.format("#%06X", color.getColorValue())).withStyle(Style.EMPTY.withColor(Color.fromRgb(color.getColorValue()))));
        super.appendHoverText(stack, world, tooltip, flag);
    }
}
