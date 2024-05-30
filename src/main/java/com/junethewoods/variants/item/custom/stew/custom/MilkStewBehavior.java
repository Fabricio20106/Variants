package com.junethewoods.variants.item.custom.stew.custom;

import com.junethewoods.variants.item.custom.stew.StewBehavior;
import com.junethewoods.variants.item.custom.stew.VSStewBehaviors;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;

public class MilkStewBehavior extends StewBehavior {
    @Override
    public void executeBehavior(ItemStack stack, World world, LivingEntity livEntity) {
        if (!world.isClientSide) livEntity.curePotionEffects(new ItemStack(Items.MILK_BUCKET));
    }

    @Override
    public StewBehavior getBehaviorRegistry() {
        return VSStewBehaviors.MILK.get();
    }
}
