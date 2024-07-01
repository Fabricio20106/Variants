package com.junethewoods.variants.item.custom.dispenser;

import com.junethewoods.variants.dispenser.vanilla.VSExperienceBottleDispenseBehavior;
import net.minecraft.block.DispenserBlock;
import net.minecraft.item.ExperienceBottleItem;

public class VSExperienceBottleItem extends ExperienceBottleItem {
    public VSExperienceBottleItem(Properties properties) {
        super(properties);
        DispenserBlock.registerBehavior(this, new VSExperienceBottleDispenseBehavior());
    }
}
