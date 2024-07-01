package com.junethewoods.variants.item.custom.tool;

import com.junethewoods.variants.dispenser.DebugArrowDispenseBehavior;
import com.junethewoods.variants.entity.custom.DebugArrowEntity;
import com.junethewoods.variants.item.VSWeaponry;
import net.minecraft.block.DispenserBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class DebugArrowItem extends ArrowItem {
    public DebugArrowItem(Properties properties) {
        super(properties);
        DispenserBlock.registerBehavior(this, new DebugArrowDispenseBehavior());
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }

    @Override
    @Nonnull
    public ItemStack getDefaultInstance() {
        ItemStack arrowStack = new ItemStack(VSWeaponry.DEBUG_ARROW.get());
        CompoundNBT tag = arrowStack.getOrCreateTag();
        tag.put("debug_arrow_state", new CompoundNBT());
        return arrowStack;
    }

    @Override
    @Nonnull
    public AbstractArrowEntity createArrow(World world, ItemStack stack, LivingEntity shooter) {
        DebugArrowEntity arrowEntity = new DebugArrowEntity(world, shooter);
        arrowEntity.setPropertyTag(stack.getOrCreateTag().getCompound("debug_arrow_state"));
        return arrowEntity;
    }
}
