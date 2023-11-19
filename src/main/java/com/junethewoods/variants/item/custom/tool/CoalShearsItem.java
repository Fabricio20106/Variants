package com.junethewoods.variants.item.custom.tool;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ShearsItem;
import net.minecraft.item.crafting.IRecipeType;

import javax.annotation.Nullable;

public class CoalShearsItem extends ShearsItem {
    public CoalShearsItem(Properties properties) {
        super(properties);
    }

    @Override
    public int getBurnTime(ItemStack stack, @Nullable IRecipeType<?> recipeType) {
        return 3200;
    }
}
