package com.junethewoods.variants.item.custom;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.Color;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TranslationTextComponent;

public class ColoredNameItem extends Item {
    private final int nameColor;

    public ColoredNameItem(int nameColor, Properties properties) {
        super(properties);
        this.nameColor = nameColor;
    }

    @Override
    public ITextComponent getName(ItemStack stack) {
        return new TranslationTextComponent(this.getDescriptionId()).withStyle(Style.EMPTY.withColor(Color.fromRgb(this.nameColor)));
    }
}
