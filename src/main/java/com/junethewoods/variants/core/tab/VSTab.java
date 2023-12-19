package com.junethewoods.variants.core.tab;

import com.junethewoods.variants.core.init.VSItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class VSTab extends CreativeModeTab {
    public static final VSTab TAB = new VSTab("variants_main");

    public VSTab(String label) {
        super(label);
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(VSItems.CYAN_SHULKER_SHELL.get());
    }
}

