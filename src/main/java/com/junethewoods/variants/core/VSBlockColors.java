package com.junethewoods.variants.core;

import com.junethewoods.variants.core.init.VSWeaponry;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.world.item.DyeableLeatherItem;

public class VSBlockColors {
    public static ItemColors initiateModColoring() {
        ItemColors itemColors = new ItemColors();
        itemColors.register((stack, color) -> color > 0 ? -1 : ((DyeableLeatherItem) stack.getItem()).getColor(stack), VSWeaponry.WOOL_SWEATCHEST.get());
        return itemColors;
    }
}
