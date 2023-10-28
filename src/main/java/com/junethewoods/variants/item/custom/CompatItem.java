package com.junethewoods.variants.item.custom;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class CompatItem extends Item {
    private final String compatMod;

    public CompatItem(Properties properties, String compatMod) {
        super(properties);
        this.compatMod = compatMod;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        tooltip.add(new TranslationTextComponent("tooltip.variants.compat_item_from", compatMod).withStyle(TextFormatting.GRAY).withStyle(TextFormatting.ITALIC));
        super.appendHoverText(stack, world, tooltip, flag);
    }
}
