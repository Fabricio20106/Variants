package com.fabricio.variants.init;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class VariantsTab extends ItemGroup {
    public static final VariantsTab variant = new VariantsTab(ItemGroup.TABS.length, "variants");

    public VariantsTab(int index, String label) {
        super(index, label);
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(StuffInit.cyan_shulker_shell.get());
    }
}

