package com.junethewoods.variants.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import javax.annotation.Nullable;
import java.util.List;

public class CompatBlockItem extends BlockItem {
    private final String compatMod;

    public CompatBlockItem(Block block, Properties properties, String compatMod) {
        super(block, properties);
        this.compatMod = compatMod;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("tooltip.variants.compat_block_from", compatMod).withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
        super.appendHoverText(stack, level, tooltip, flag);
    }
}
