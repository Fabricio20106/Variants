package com.junethewoods.variants.item.custom.tool;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.item.crafting.RecipeType;

import javax.annotation.Nullable;

public class CoalShearsItem extends ShearsItem {
    public CoalShearsItem(Properties properties) {
        super(properties);
    }

    @Override
    public int getBurnTime(ItemStack stack, @Nullable RecipeType<?> recipeType) {
        return 3200;
    }
}
