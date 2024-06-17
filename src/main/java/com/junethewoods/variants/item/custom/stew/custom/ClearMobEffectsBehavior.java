package com.junethewoods.variants.item.custom.stew.custom;

import com.junethewoods.variants.item.custom.stew.StewBehavior;
import com.junethewoods.variants.item.custom.stew.VSStewBehaviors;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;

public class ClearMobEffectsBehavior extends StewBehavior {
    private final ItemStack curativeStack;

    public ClearMobEffectsBehavior(ItemStack curativeStack) {
        this.curativeStack = curativeStack;
    }

    public ClearMobEffectsBehavior() {
        this(new ItemStack(Items.MILK_BUCKET));
    }

    @Override
    public void executeBehavior(ItemStack stack, World world, LivingEntity livEntity) {
        if (!world.isClientSide) {
            CompoundNBT behaviorTag = stack.getOrCreateTagElement("behavior");
            CompoundNBT propertiesTag = behaviorTag.getCompound("properties");
            ItemStack curativeStack = ItemStack.of(propertiesTag.getCompound("curative_item"));
            livEntity.curePotionEffects(curativeStack);
        }
    }

    @Override
    public CompoundNBT writePropertiesToNBT(ItemStack stewStack) {
        CompoundNBT properties = new CompoundNBT();
        properties.put("curative_item", this.curativeStack.save(new CompoundNBT()));
        return properties;
    }

    @Override
    public StewBehavior getBehaviorRegistry() {
        return VSStewBehaviors.CLEAR_MOB_EFFECTS.get();
    }
}
