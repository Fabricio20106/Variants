package com.junethewoods.variants.item.custom.armor;

import com.junethewoods.variants.util.NBTUtils;
import net.minecraft.item.IDyeableArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

public interface IDyeableWoolArmorItem extends IDyeableArmorItem {
    @Override
    default int getColor(ItemStack stack) {
        CompoundNBT nbt = stack.getTagElement("display");
        return nbt != null && nbt.contains("color", NBTUtils.WILDCARD) ? nbt.getInt("color") : getDefaultColor();
    }

    int getDefaultColor();
}
