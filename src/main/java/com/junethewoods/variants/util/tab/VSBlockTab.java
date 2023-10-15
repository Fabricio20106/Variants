package com.junethewoods.variants.util.tab;

import com.junethewoods.variants.item.VSItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class VSBlockTab extends ItemGroup {
    public static final VSBlockTab TAB = new VSBlockTab("variants_blocks");

    public VSBlockTab(String label) {
        super(label);
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(VSItems.WANDERER_DOOR.get());
    }
}
