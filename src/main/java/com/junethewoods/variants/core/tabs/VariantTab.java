package com.junethewoods.variants.core.tabs;

import com.junethewoods.variants.core.init.StuffInit;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class VariantTab extends ItemGroup {
    public static final VariantTab variant = new VariantTab(ItemGroup.GROUPS.length, "variants");

    public VariantTab(int index, String label) {
        super(index, label);
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(StuffInit.cyan_shulker_shell.get());
    }
}

