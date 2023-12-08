package com.junethewoods.variants.item.custom.tool;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class CompatShearsItem extends ShearsItem {
    private final String compatMod;

    public CompatShearsItem(Properties properties, String compatMod) {
        super(properties);
        this.compatMod = compatMod;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("tooltip.variants.compat_item_from", compatMod).withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
        super.appendHoverText(stack, level, tooltip, flag);
    }
}
