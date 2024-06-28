package com.junethewoods.variants.item.custom.food;

import com.junethewoods.variants.Variants;
import com.junethewoods.variants.item.VSItems;
import com.junethewoods.variants.item.custom.poisoning.PoisoningType;
import com.junethewoods.variants.util.NBTUtils;
import com.junethewoods.variants.util.VSRegistries;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.*;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.List;

public class StylisedPotItem extends DrinkableContainerItem {
    private final PoisoningType poisoningType;
    private final int poisoningDuration;
    private final String compatMod;

    public StylisedPotItem(PoisoningType poisoningType, int duration, String compatMod, Properties properties) {
        super(properties);
        this.poisoningType = poisoningType;
        if (poisoningType.getPoisoningEffect() != null && poisoningType.getPoisoningEffect().isPresent() && poisoningType.getPoisoningEffect().get().isInstantenous()) {
            this.poisoningDuration = duration;
        } else {
            this.poisoningDuration = duration * 20;
        }
        this.compatMod = compatMod;
    }

    public StylisedPotItem(PoisoningType poisoningType, int duration, Properties properties) {
        this(poisoningType, duration, null, properties);
    }

    @Override
    public ItemStack getDefaultInstance() {
        ItemStack potStack = new ItemStack(this);
        CompoundNBT tag = potStack.getOrCreateTag();
        CompoundNBT effectTag = new CompoundNBT();
        tag.putString("poisoning_type", getTypeFromNBT(potStack).getRegistryName().toString());
        effectTag.putString("id", this.poisoningType.getPoisoningEffect().get().getRegistryName().toString());
        effectTag.putInt("duration", this.poisoningDuration);
        tag.put("effect", effectTag);
        return potStack;
    }

    public boolean hasTypeInNBT(ItemStack potStack) {
        return potStack.getTag() != null && potStack.getTag().contains("poisoning_type");
    }

    public boolean hasEffectsInNBT(ItemStack potStack) {
        return potStack.getTag() != null && potStack.getTag().contains("effect");
    }

    public PoisoningType getTypeFromNBT(ItemStack potStack) {
        if (hasTypeInNBT(potStack)) {
            ResourceLocation type = ResourceLocation.tryParse(potStack.getTag().getString("poisoning_type"));
            if (VSRegistries.POISONING_TYPE.containsKey(type)) return VSRegistries.POISONING_TYPE.getValue(type);
        }
        return this.poisoningType;
    }

    @Override
    public void executeFunctionality(ItemStack containerStack, ItemStack bottleStack, World world, LivingEntity livEntity) {
        PoisoningType type = getTypeFromNBT(bottleStack);
        this.containerItem = new ItemStack(VSItems.STYLISED_POT.get());
        if (hasEffectsInNBT(bottleStack)) {
            addEffectsFromNBT(bottleStack, livEntity);
        } else if (type != null && type.getPoisoningEffect() != null) {
            if (!world.isClientSide) {
                livEntity.addEffect(new EffectInstance(this.poisoningType.getPoisoningEffect().get(), this.poisoningDuration));
            }
        }
    }

    public void addEffectsFromNBT(ItemStack potStack, LivingEntity livEntity) {
        CompoundNBT tag = potStack.getOrCreateTagElement("effect");
        int duration = 160; // Default of 8 seconds.
        int amplifier = 0;
        boolean ambient = false;
        boolean showParticles = true;
        boolean showIcon = true;
        if (tag.contains("duration", 3)) duration = tag.getInt("duration");
        if (tag.contains("amplifier", 3)) amplifier = tag.getInt("amplifier");
        if (tag.contains("ambient")) ambient = tag.getBoolean("ambient");
        if (tag.contains("show_particles")) showParticles = tag.getBoolean("show_particles");
        if (tag.contains("show_icon")) showIcon = tag.getBoolean("show_icon");

        Effect effect = ForgeRegistries.POTIONS.getValue(ResourceLocation.tryParse(tag.getString("id")));
        if (effect != null) {
            livEntity.addEffect(new EffectInstance(effect, duration, amplifier, ambient, showParticles, showIcon));
        }
    }

    @Override
    public void appendHoverText(ItemStack potStack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        PoisoningType type = getTypeFromNBT(potStack);
        if (this.compatMod != null && NBTUtils.shouldNotHideTooltip("hide_compat_mod", potStack)) {
            tooltip.add(new TranslationTextComponent("tooltip.variants.compat_item_from", this.compatMod).withStyle(TextFormatting.GRAY).withStyle(TextFormatting.ITALIC));
        }
        if (hasEffectsInNBT(potStack)) {
            CompoundNBT effectTag = potStack.getOrCreateTagElement("effect");
            Effect effect = ForgeRegistries.POTIONS.getValue(ResourceLocation.tryParse(effectTag.getString("id")));
            if (effect != null) {
                tooltip.add(new TranslationTextComponent("tooltip." + Variants.MOD_ID + ".stylised_pot.poisoning_or_effect", new
                        TranslationTextComponent("effect." + effect.getRegistryName().getNamespace() + "." + effect.getRegistryName()
                        .getPath()).withStyle(Style.EMPTY.withColor(Color.fromRgb(effect.getColor())))).withStyle(TextFormatting.GRAY));
            }
        }
        if (type != null && type.getPoisoningEffect() != null) {
            tooltip.add(new TranslationTextComponent("tooltip." + Variants.MOD_ID + ".stylised_pot.poisoning", new TranslationTextComponent(
                    type.getDescriptionId()).withStyle(Style.EMPTY.withColor(Color.fromRgb(type.getPoisoningEffect().get().getColor()))))
                    .withStyle(TextFormatting.GRAY));
        }
        super.appendHoverText(potStack, world, tooltip, flag);
    }

    @Override
    public void fillItemCategory(ItemGroup tab, NonNullList<ItemStack> list) {
        if (this.allowdedIn(tab)) {
            ItemStack potStack = new ItemStack(this);
            PoisoningType type = getTypeFromNBT(potStack);
            CompoundNBT tag = potStack.getOrCreateTag();
            if (potStack.getTag() != null && type != null) tag.putString("poisoning_type", type.getTypeRegistry().getRegistryName().toString());
            if (potStack.getTag() != null) {
                CompoundNBT effectTag = potStack.getOrCreateTagElement("effect");
                effectTag.putInt("duration", this.poisoningDuration);
                tag.put("effect", effectTag);
            }
            list.add(potStack);
        }
    }
}
