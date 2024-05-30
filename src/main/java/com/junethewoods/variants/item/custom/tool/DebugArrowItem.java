package com.junethewoods.variants.item.custom.tool;

import com.junethewoods.variants.entity.custom.DebugArrowEntity;
import com.junethewoods.variants.item.VSWeaponry;
import net.minecraft.block.DispenserBlock;
import net.minecraft.dispenser.IPosition;
import net.minecraft.dispenser.ProjectileDispenseBehavior;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;

public class DebugArrowItem extends ArrowItem {
    public DebugArrowItem(Properties properties) {
        super(properties);
        DispenserBlock.registerBehavior(this, new ProjectileDispenseBehavior() {
            @Override
            protected ProjectileEntity getProjectile(World world, IPosition pos, ItemStack stack) {
                DebugArrowEntity debugArrow = new DebugArrowEntity(world, pos.x(), pos.y(), pos.z());
                debugArrow.setPropertyTag(stack.getOrCreateTag().getCompound("debug_arrow_state"));
                debugArrow.pickup = AbstractArrowEntity.PickupStatus.ALLOWED;
                return debugArrow;
            }
        });
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }

    @Override
    public ItemStack getDefaultInstance() {
        ItemStack arrowStack = new ItemStack(VSWeaponry.DEBUG_ARROW.get());
        CompoundNBT tag = arrowStack.getOrCreateTag();
        tag.put("debug_arrow_state", new CompoundNBT());
        return arrowStack;
    }

    @Override
    public AbstractArrowEntity createArrow(World world, ItemStack stack, LivingEntity shooter) {
        DebugArrowEntity arrowEntity = new DebugArrowEntity(world, shooter);
        arrowEntity.setPropertyTag(stack.getOrCreateTag().getCompound("debug_arrow_state"));
        return arrowEntity;
    }
}
