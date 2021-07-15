package com.junethewoods.variants.init;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class VariantBlocks extends ItemGroup {
    public static final VariantBlocks blocks = new VariantBlocks(ItemGroup.GROUPS.length, "blocks");

    public VariantBlocks(int index, String label) {
        super(index, label);
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(StuffInit.raw_debris_block.get());
    }
}
