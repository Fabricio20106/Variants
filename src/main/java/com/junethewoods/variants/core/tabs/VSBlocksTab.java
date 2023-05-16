package com.junethewoods.variants.core.tabs;

import com.junethewoods.variants.core.init.VSItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class VSBlocksTab extends CreativeModeTab {
    public static final VSBlocksTab TAB = new VSBlocksTab(CreativeModeTab.TABS.length, "variants_blocks");

    public VSBlocksTab(int index, String label) {
        super(index, label);
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(VSItems.WANDERER_DOOR.get());
    }
}
