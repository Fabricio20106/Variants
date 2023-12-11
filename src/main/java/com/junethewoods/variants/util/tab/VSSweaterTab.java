package com.junethewoods.variants.util.tab;

import com.junethewoods.variants.item.VSWeaponry;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class VSSweaterTab extends ItemGroup {
    public static final VSSweaterTab TAB = (VSSweaterTab) new VSSweaterTab("variants.infinity_sweaters").setBackgroundSuffix("item_search.png");

    public VSSweaterTab(String label) {
        super(label);
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(VSWeaponry.INFINITY_SWEATERS_TAB_ICON.get());
    }

    @Override
    public boolean hasSearchBar() {
        return true;
    }

    @Override
    public boolean showTitle() {
        return false;
    }
}
