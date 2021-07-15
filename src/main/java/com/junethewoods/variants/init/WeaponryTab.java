package com.junethewoods.variants.init;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class WeaponryTab extends ItemGroup {
    public static final WeaponryTab weaponryTab = new WeaponryTab(ItemGroup.GROUPS.length, "weaponryTab");

    public WeaponryTab(int index, String label) {
        super(index, label);
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(WeaponryInit.phantom_membrane_sweatchest.get());
    }
}
