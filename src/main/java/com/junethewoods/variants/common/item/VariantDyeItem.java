package com.junethewoods.variants.common.item;

import com.google.common.collect.Maps;
import com.junethewoods.variants.common.block.VariantDyeColor;
import net.minecraft.item.Item;

import java.util.Map;

public class VariantDyeItem extends Item {
    private static final Map<VariantDyeColor, VariantDyeItem> COLOR_DYE_ITEM_MAP = Maps.newEnumMap(VariantDyeColor.class);
    private final VariantDyeColor dyeColor;

    public VariantDyeItem(VariantDyeColor dyeColorIn, Item.Properties builder) {
        super(builder);
        this.dyeColor = dyeColorIn;
        COLOR_DYE_ITEM_MAP.put(dyeColorIn, this);
    }

    public VariantDyeColor getDyeColor() {
        return this.dyeColor;
    }

    public static VariantDyeItem getItem(VariantDyeColor color) {
        return COLOR_DYE_ITEM_MAP.get(color);
    }
}
