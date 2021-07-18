package com.junethewoods.variants.items;

import com.google.common.collect.Maps;
import com.junethewoods.variants.blocks.ModDyeColor;
import net.minecraft.item.Item;

import java.util.Map;

public class ModDyeItem extends Item {
    private static final Map<ModDyeColor, ModDyeItem> COLOR_DYE_ITEM_MAP = Maps.newEnumMap(ModDyeColor.class);
    private final ModDyeColor dyeColor;

    public ModDyeItem(ModDyeColor dyeColorIn, Item.Properties builder) {
        super(builder);
        this.dyeColor = dyeColorIn;
        COLOR_DYE_ITEM_MAP.put(dyeColorIn, this);
    }

    public ModDyeColor getDyeColor() {
        return this.dyeColor;
    }

    public static ModDyeItem getItem(ModDyeColor color) {
        return COLOR_DYE_ITEM_MAP.get(color);
    }
}
