package com.junethewoods.variants.util.tab;

import com.junethewoods.variants.item.VSCompatItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class VSCompatsTab extends ItemGroup {
    public static final VSCompatsTab TAB = new VSCompatsTab("f10ModsCompat");

    public VSCompatsTab(String label) {
        super(label);
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(VSCompatItems.SPLASH_SOPHIE_POTION.get());
    }
}