package com.junethewoods.variants.block.dispenser;

import net.minecraft.dispenser.IPosition;
import net.minecraft.dispenser.ProjectileDispenseBehavior;
import net.minecraft.entity.item.ExperienceBottleEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Util;
import net.minecraft.world.World;

public class VSExperienceBottleDispenseBehavior extends ProjectileDispenseBehavior {
    @Override
    protected ProjectileEntity getProjectile(World world, IPosition pos, ItemStack stack) {
        return Util.make(new ExperienceBottleEntity(world, pos.x(), pos.y(), pos.z()), (bottle) -> bottle.setItem(stack));
    }

    protected float getUncertainty() {
        return super.getUncertainty() * 0.5F;
    }

    protected float getPower() {
        return super.getPower() * 1.25F;
    }
}
