package com.junethewoods.variants.item.custom.stew;

import com.junethewoods.variants.util.VSRegistries;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.util.text.*;
import net.minecraft.util.text.event.HoverEvent;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;

public abstract class StewBehavior extends ForgeRegistryEntry<StewBehavior> {
    @Nullable
    private String descriptionID;

    public abstract void executeBehavior(ItemStack stack, World world, LivingEntity livEntity);

    public abstract void executeFromStewNBT(ItemStack stewStack, World world, LivingEntity livEntity, CompoundNBT propertiesTag);

    public abstract CompoundNBT writePropertiesToNBT();

    public EffectInstance[] getEffects() {
        return null;
    }

    public StewBehavior getBehaviorRegistry() {
        return VSStewBehaviors.DEFAULT.get();
    }

    public boolean is(ITag<StewBehavior> behaviorTag) {
        return behaviorTag.contains(this);
    }

    protected String getOrCreateDescriptionId() {
        if (this.descriptionID == null) this.descriptionID = Util.makeDescriptionId("stew_behavior", VSRegistries.STEW_BEHAVIOR.getKey(this));
        return this.descriptionID;
    }

    public String getDescriptionID() {
        return this.getOrCreateDescriptionId();
    }

    public ITextComponent getCommandDisplayName() {
        IFormattableTextComponent component = TextComponentUtils.wrapInSquareBrackets(new TranslationTextComponent(this.getDescriptionID())).withStyle(TextFormatting.LIGHT_PURPLE);
        component.withStyle(style -> style.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new StringTextComponent("")
                .append(new TranslationTextComponent(this.getDescriptionID()).withStyle(TextFormatting.LIGHT_PURPLE).withStyle(TextFormatting.BOLD)).append("\n")
                .append(new TranslationTextComponent(this.getDescriptionID() + ".desc").withStyle(TextFormatting.GRAY)))));
        return component;
    }

    public CompoundNBT writeBehaviorToNBT(ItemStack stewStack) {
        CompoundNBT behaviorTag = stewStack.getOrCreateTagElement("behavior");
        behaviorTag.putString("id", getBehaviorFromNBT(stewStack).getRegistryName().toString());
        behaviorTag.put("properties", writePropertiesToNBT());
        return behaviorTag;
    }

    public boolean hasBehaviorIDInNBT(ItemStack stewStack) {
        CompoundNBT behaviorTag = stewStack.getOrCreateTagElement("behavior");
        return behaviorTag.contains("id");
    }

    public StewBehavior getBehaviorFromNBT(ItemStack stewStack) {
        if (hasBehaviorIDInNBT(stewStack)) {
            ResourceLocation behavior = ResourceLocation.tryParse(stewStack.getTagElement("behavior").getString("id"));
            if (VSRegistries.STEW_BEHAVIOR.containsKey(behavior)) return VSRegistries.STEW_BEHAVIOR.getValue(behavior);
        } else {
            return getBehaviorRegistry();
        }
        return this;
    }

    public CompoundNBT getBehaviorProperties(ItemStack stewStack) {
        CompoundNBT tag = stewStack.getTagElement("behavior");
        if (tag != null && tag.contains("properties")) return tag.getCompound("properties");
        return null;
    }
}
