package com.junethewoods.variants.util;

import com.junethewoods.variants.Variants;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.List;

public class NBTUtils {
    public static boolean shouldHideTooltip(String toHide, ItemStack stack) {
        CompoundNBT hideTooltipsTag = stack.getTagElement("hide_vs_tooltips");
        if (hideTooltipsTag != null) return !hideTooltipsTag.getBoolean(toHide);
        return true;
    }

    public static void addItemTagsTooltip(ItemStack stack, List<ITextComponent> tooltip, ITooltipFlag flag) {
        if (flag.isAdvanced()) {
            CompoundNBT tagTag = stack.getTag();
            if (tagTag != null) tooltip.add(new TranslationTextComponent("tooltip." + Variants.MOD_ID + ".tags", tagTag.getPrettyDisplay()).withStyle(TextFormatting.GRAY));
        }
    }
}
