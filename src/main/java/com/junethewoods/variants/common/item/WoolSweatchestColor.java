package com.junethewoods.variants.common.item;

import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.item.ItemStack;

public class WoolSweatchestColor implements ItemColor {
    @Override
    public int getColor(ItemStack stack, int i) {
        return 0xf0f0f0;
    }
}
