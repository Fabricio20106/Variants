package com.junethewoods.variants.item.custom.dispenser;

import com.junethewoods.variants.block.dispenser.BucketDispenseBehavior;
import net.minecraft.block.DispenserBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.FishBucketItem;

import java.util.function.Supplier;

public class VSFishBucketItem extends FishBucketItem {
    public VSFishBucketItem(Supplier<? extends EntityType<?>> fish, Supplier<? extends Fluid> fluid, Properties properties) {
        super(fish, fluid, properties);
        DispenserBlock.registerBehavior(this, new BucketDispenseBehavior());
    }
}
