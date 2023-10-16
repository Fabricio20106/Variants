package com.junethewoods.variants.item.custom.tool;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShearsItem;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class DescriptiveCompatShearsItem extends ShearsItem {
    private final String compatMod;

    public DescriptiveCompatShearsItem(Properties properties, String compatMod) {
        super(properties);
        this.compatMod = compatMod;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        tooltip.add(new TranslationTextComponent("messages.variants.compat_item_from", compatMod).withStyle(TextFormatting.GRAY).withStyle(TextFormatting.ITALIC));
        super.appendHoverText(stack, world, tooltip, flag);
    }
}
