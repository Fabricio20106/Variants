package com.junethewoods.variants.item.custom.stew;

import com.junethewoods.variants.util.VSRegistries;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;

public abstract class StewBehavior extends ForgeRegistryEntry<StewBehavior> {
    @Nullable
    private String descriptionId;

    public abstract void executeBehavior(ItemStack stack, World world, LivingEntity livEntity);

    public abstract CompoundNBT writePropertiesToNBT(ItemStack stewStack);

    public EffectInstance[] getEffects() {
        return null;
    }

    public StewBehavior getBehaviorRegistry() {
        return VSStewBehaviors.DEFAULT.get();
    }

    protected String getOrCreateDescriptionId() {
        if (this.descriptionId == null) this.descriptionId = Util.makeDescriptionId("stew_behavior", VSRegistries.STEW_BEHAVIOR.getKey(this));
        return this.descriptionId;
    }

    public String getDescriptionId() {
        return this.getOrCreateDescriptionId();
    }

    public ITextComponent getDisplayName() {
        return new TranslationTextComponent(this.getDescriptionId());
    }

    public CompoundNBT writeBehaviorToNBT(ItemStack stewStack) {
        CompoundNBT behaviorTag = stewStack.getOrCreateTagElement("behavior");
        behaviorTag.putString("id", getBehaviorFromNBT(stewStack).getRegistryName().toString());
        behaviorTag.put("properties", writePropertiesToNBT(stewStack));
        return behaviorTag;
    }

    public boolean hasBehaviorInNBT(ItemStack stewStack) {
        return stewStack.getTag() != null && stewStack.getTag().contains("stew_behavior");
    }

    public StewBehavior getBehaviorFromNBT(ItemStack stewStack) {
        if (hasBehaviorInNBT(stewStack)) {
            ResourceLocation behavior = ResourceLocation.tryParse(stewStack.getTag().getString("stew_behavior"));
            if (VSRegistries.STEW_BEHAVIOR.containsKey(behavior)) return VSRegistries.STEW_BEHAVIOR.getValue(behavior);
        } else {
            return getBehaviorRegistry();
        }
        return this;
    }
}
