package com.junethewoods.variants.item.custom;

import com.junethewoods.variants.block.VSFluids;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;

import javax.annotation.Nullable;

public class SoulLavaBucketItem extends BucketItem {
    public SoulLavaBucketItem(Properties properties) {
        super(VSFluids.SOUL_LAVA, properties);
    }

    @Override
    public int getBurnTime(ItemStack stack, @Nullable IRecipeType<?> recipeType) {
        return 40000; // 200 Items.
    }
}
