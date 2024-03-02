package com.junethewoods.variants.item.custom.dispenser;

import com.junethewoods.variants.block.dispenser.BoneMealDispenseBehavior;
import net.minecraft.block.DispenserBlock;
import net.minecraft.item.BoneMealItem;

public class WitherBoneMealItem extends BoneMealItem {
    public WitherBoneMealItem(Properties properties) {
        super(properties);
        DispenserBlock.registerBehavior(this, new BoneMealDispenseBehavior());
    }
}
