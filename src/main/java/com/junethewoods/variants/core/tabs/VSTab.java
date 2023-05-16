package com.junethewoods.variants.core.tabs;

import com.junethewoods.variants.core.init.VSItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class VSTab extends CreativeModeTab {
    public static final VSTab TAB = new VSTab(CreativeModeTab.TABS.length, "variants_main");

    public VSTab(int index, String label) {
        super(index, label);
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(VSItems.CYAN_SHULKER_SHELL.get());
    }
}

