package com.junethewoods.variants.item.custom.stew.custom;

import com.junethewoods.variants.item.custom.stew.IStewBehavior;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class DefaultStewBehavior implements IStewBehavior {
    @Override
    public void executeBehavior(ItemStack stack, World world, LivingEntity livEntity) {}
}
