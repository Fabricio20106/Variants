package com.junethewoods.variants.core.tab;

import com.junethewoods.variants.core.init.VSWeaponry;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class VSWeaponryTab extends CreativeModeTab {
    public static final VSWeaponryTab TAB = new VSWeaponryTab("variants_weaponry");

    public VSWeaponryTab(String label) {
        super(label);
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(VSWeaponry.PHANTOM_MEMBRANE_SWEATCHEST.get());
    }
}
