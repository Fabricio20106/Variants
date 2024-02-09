package com.junethewoods.variants.item.custom.stew.custom;

import com.junethewoods.variants.item.custom.stew.IStewBehavior;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LavaStewBehavior implements IStewBehavior {
    private final boolean containsSoulLava;

    public LavaStewBehavior(boolean containsSoulLava) {
        this.containsSoulLava = containsSoulLava;
    }

    @Override
    public void executeBehavior(ItemStack stack, World world, LivingEntity livEntity) {
        if (!world.isClientSide) livEntity.setSecondsOnFire(this.containsSoulLava ? 10 : 5);
    }
}
