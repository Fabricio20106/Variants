package com.junethewoods.variants.compat;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class F10CompatTab extends ItemGroup {
    public static final F10CompatTab compat = new F10CompatTab("f10ModsCompat");

    public F10CompatTab(String label) {
        super(label);
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(F10CompatInit.splash_soph_potion.get());
    }
}
