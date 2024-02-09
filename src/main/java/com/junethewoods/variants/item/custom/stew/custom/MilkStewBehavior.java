package com.junethewoods.variants.item.custom.stew.custom;

import com.junethewoods.variants.item.custom.stew.IStewBehavior;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;

public class MilkStewBehavior implements IStewBehavior {
    @Override
    public void executeBehavior(ItemStack stack, World world, LivingEntity livEntity) {
        if (!world.isClientSide) livEntity.curePotionEffects(new ItemStack(Items.MILK_BUCKET));
    }
}
