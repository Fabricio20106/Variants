package com.junethewoods.variants.core.tabs;

import com.junethewoods.variants.core.init.VSWeaponry;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class VSWeaponryTab extends CreativeModeTab {
    public static final VSWeaponryTab TAB = new VSWeaponryTab(CreativeModeTab.TABS.length, "variants_weaponry");

    public VSWeaponryTab(int index, String label) {
        super(index, label);
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(VSWeaponry.PHANTOM_MEMBRANE_SWEATCHEST.get());
    }
}
