package com.junethewoods.variants.util.tab;

import com.junethewoods.variants.item.VSItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class VSTab extends ItemGroup {
    public static final VSTab TAB = new VSTab(ItemGroup.TABS.length, "variants.main");

    public VSTab(int index, String label) {
        super(index, label);
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(VSItems.CYAN_SHULKER_SHELL.get());
    }
}

