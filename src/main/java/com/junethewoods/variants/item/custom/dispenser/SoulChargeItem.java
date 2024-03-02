package com.junethewoods.variants.item.custom.dispenser;

import com.junethewoods.variants.block.dispenser.SoulChargeDispenseBehavior;
import net.minecraft.block.DispenserBlock;
import net.minecraft.item.FireChargeItem;

public class SoulChargeItem extends FireChargeItem {
    public SoulChargeItem(Properties properties) {
        super(properties);
        DispenserBlock.registerBehavior(this, new SoulChargeDispenseBehavior());
    }
}
