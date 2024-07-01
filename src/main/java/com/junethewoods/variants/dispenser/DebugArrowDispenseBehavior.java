package com.junethewoods.variants.dispenser;

import com.junethewoods.variants.entity.custom.DebugArrowEntity;
import net.minecraft.dispenser.IPosition;
import net.minecraft.dispenser.ProjectileDispenseBehavior;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class DebugArrowDispenseBehavior extends ProjectileDispenseBehavior {
    @Override
    protected ProjectileEntity getProjectile(World world, IPosition pos, ItemStack stack) {
        DebugArrowEntity debugArrow = new DebugArrowEntity(world, pos.x(), pos.y(), pos.z());
        debugArrow.setPropertyTag(stack.getOrCreateTag().getCompound("debug_arrow_state"));
        debugArrow.pickup = AbstractArrowEntity.PickupStatus.ALLOWED;
        return debugArrow;
    }
}
