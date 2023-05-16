package com.junethewoods.variants.common.item;

import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.item.ItemStack;

public class LeavesItemColor implements ItemColor {
    @Override
    public int getColor(ItemStack stack, int i) {
        return 0x79C05A;
    }
}
