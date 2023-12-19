package com.junethewoods.variants.core.tab;

import com.junethewoods.variants.core.init.VSItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class VSFoodstuffsTab extends CreativeModeTab {
    public static final VSFoodstuffsTab TAB = new VSFoodstuffsTab("variant.food");

    public VSFoodstuffsTab(String label) {
        super(label);
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(VSItems.FUNGI_STEW.get());
    }
}
