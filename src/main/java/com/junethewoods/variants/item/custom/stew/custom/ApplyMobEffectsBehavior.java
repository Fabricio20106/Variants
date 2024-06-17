package com.junethewoods.variants.item.custom.stew.custom;

import com.junethewoods.variants.item.custom.stew.StewBehavior;
import com.junethewoods.variants.item.custom.stew.VSStewBehaviors;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.World;

import static com.junethewoods.variants.util.NBTUtils.writeEffectsOntoNBT;

public class ApplyMobEffectsBehavior extends StewBehavior {
    private final EffectInstance[] effects;

    public ApplyMobEffectsBehavior(EffectInstance[] effects) {
        this.effects = effects;
    }

    public ApplyMobEffectsBehavior() {
        this(null);
    }

    @Override
    public void executeBehavior(ItemStack stack, World world, LivingEntity livEntity) {}

    @Override
    public CompoundNBT writePropertiesToNBT(ItemStack stewStack) {
        CompoundNBT properties = new CompoundNBT();
        ListNBT effects = writeEffectsOntoNBT(this.effects);
        properties.put("effects", effects);
        return properties;
    }

    @Override
    public EffectInstance[] getEffects() {
        return this.effects;
    }

    @Override
    public StewBehavior getBehaviorRegistry() {
        return VSStewBehaviors.APPLY_MOB_EFFECTS.get();
    }
}
