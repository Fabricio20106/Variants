package com.junethewoods.variants.item.custom;

import net.minecraft.item.IDyeableArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

public interface IDyeableWoolArmorItem extends IDyeableArmorItem {
    @Override
    default int getColor(ItemStack stack) {
        CompoundNBT nbt = stack.getTagElement("display");
        return nbt != null && nbt.contains("color", 99) ? nbt.getInt("color") : 16777215;
    }
}
