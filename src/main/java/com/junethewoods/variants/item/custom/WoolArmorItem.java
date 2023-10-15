package com.junethewoods.variants.item.custom;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class WoolArmorItem extends ArmorItem implements IDyeableWoolArmorItem {
    public WoolArmorItem(IArmorMaterial material, EquipmentSlotType slot, Properties properties) {
        super(material, slot, properties);
    }

    @Override
    public ITextComponent getName(ItemStack stack) {
        if (stack.hasTag() && !stack.getTag().getString("color_name").isEmpty()) {
            return new TranslationTextComponent(this.getDescriptionId(), new TranslationTextComponent(stack.getTag().getString("color_name")));
        }
        return super.getName(stack);
    }
}
