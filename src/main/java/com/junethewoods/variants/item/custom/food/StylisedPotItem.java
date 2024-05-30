package com.junethewoods.variants.item.custom.food;

import com.junethewoods.variants.item.VSItems;
import com.junethewoods.variants.item.custom.poisoning.PoisoningType;
import com.junethewoods.variants.item.custom.poisoning.VSPoisoningTypes;
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
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class StylisedPotItem extends DrinkableContainerItem {
    private final PoisoningType poisoningType;
    private final int poisoningDuration;
    private final String compatMod;

    public StylisedPotItem(Properties properties, String compatMod, PoisoningType poisoningType, int poisoningDuration) {
        super(properties);
        this.poisoningType = poisoningType;
        if (poisoningType.getPoisoningEffect().isPresent() && poisoningType.getPoisoningEffect().get().isInstantenous()) {
            this.poisoningDuration = poisoningDuration;
        } else {
            this.poisoningDuration = poisoningDuration * 20;
        }
        this.compatMod = compatMod;
    }

    public StylisedPotItem(Properties properties, PoisoningType poisoningType, int poisoningDuration) {
        this(properties, null, poisoningType, poisoningDuration);
    }

    @Override
    public ItemStack getDefaultInstance() {
        ItemStack potStack = new ItemStack(this);
        potStack.getOrCreateTag().putString("poisoning_type", getTypeFromNBT(potStack).getRegistryName().toString());
        return super.getDefaultInstance();
    }

    public boolean hasTypeInNBT(ItemStack potStack) {
        return potStack.getTag() != null && potStack.getTag().contains("poisoning_type");
    }

    public PoisoningType getTypeFromNBT(ItemStack potStack) {
        if (hasTypeInNBT(potStack)) {
            ResourceLocation type = ResourceLocation.tryParse(potStack.getTag().getString("poisoning_type"));
            if (VSRegistries.POISONING_TYPE.containsKey(type)) return VSRegistries.POISONING_TYPE.getValue(type);
        } else {
            return this.poisoningType.getTypeRegistry();
        }
        return this.poisoningType;
    }

    private Effect parseEffectFromNBT(ItemStack potStack) {
        CompoundNBT tag = potStack.getOrCreateTag();
        ResourceLocation typeLocation = ResourceLocation.tryParse(tag.getString("poisoning_type"));
        PoisoningType poisoningType1 = VSRegistries.POISONING_TYPE.getValue(typeLocation);
        assert poisoningType1 != null;
        return poisoningType1.getPoisoningEffect().get();
    }

    @Override
    public void bottleFunctionality(ItemStack containerStack, ItemStack stack, World world, LivingEntity livEntity) {
        PoisoningType type = getTypeFromNBT(stack);
        this.containerItem = new ItemStack(VSItems.STYLISED_POT.get());
        if (type != null) {
            if (!world.isClientSide) livEntity.addEffect(new EffectInstance(parseEffectFromNBT(stack), this.poisoningDuration));
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        if (this.compatMod != null && NBTUtils.shouldHideTooltip("hide_compat_mod", stack)) {
            tooltip.add(new TranslationTextComponent("tooltip.variants.compat_item_from", this.compatMod).withStyle(TextFormatting.GRAY).withStyle(TextFormatting.ITALIC));
        }
        super.appendHoverText(stack, world, tooltip, flag);
    }

    @Override
    public void fillItemCategory(ItemGroup tab, NonNullList<ItemStack> list) {
        if (this.allowdedIn(tab)) {
            ItemStack potStack = new ItemStack(this);
            PoisoningType type = getTypeFromNBT(potStack);
            CompoundNBT tag = potStack.getOrCreateTag();
            if (potStack.getTag() != null && type != null) tag.putString("poisoning_type", type.getTypeRegistry().getRegistryName().toString());
            list.add(potStack);
        }
    }
}
