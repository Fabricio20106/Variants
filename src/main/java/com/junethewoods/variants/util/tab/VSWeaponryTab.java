package com.junethewoods.variants.util.tab;

import com.junethewoods.variants.item.VSWeaponry;
import com.junethewoods.variants.item.custom.armor.WoolArmorItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class VSWeaponryTab extends ItemGroup {
    public static final VSWeaponryTab TAB = new VSWeaponryTab(ItemGroup.TABS.length, "variants.weapons");

    public VSWeaponryTab(int index, String label) {
        super(index, label);
    }

    @Override
    public ItemStack makeIcon() {
        return WoolArmorItem.pickRandomColor(new ItemStack(VSWeaponry.WOOL_SWEATER.get()));
    }
}
