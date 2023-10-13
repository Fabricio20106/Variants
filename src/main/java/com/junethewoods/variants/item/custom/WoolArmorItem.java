package com.junethewoods.variants.item.custom;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;

public class WoolArmorItem extends ArmorItem implements IDyeableWoolArmorItem {
    public WoolArmorItem(IArmorMaterial material, EquipmentSlotType slot, Properties properties) {
        super(material, slot, properties);
    }
}
