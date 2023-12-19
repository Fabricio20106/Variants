package com.junethewoods.variants.common.item;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.ItemStack;

public interface DyeableWoolItem extends DyeableLeatherItem {
    @Override
    default int getColor(ItemStack stack) {
        CompoundTag nbt = stack.getTagElement("display");
        return nbt != null && nbt.contains("color", 99) ? nbt.getInt("color") : 16777215;
    }
}
