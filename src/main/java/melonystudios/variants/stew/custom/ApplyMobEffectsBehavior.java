package melonystudios.variants.stew.custom;

import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;
import melonystudios.variants.Variants;
import melonystudios.variants.stew.StewBehavior;
import melonystudios.variants.stew.VSStewBehaviors;
import melonystudios.variants.util.NBTUtils;
import melonystudios.variants.util.VSStyles;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectUtils;
import net.minecraft.util.text.*;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

import static melonystudios.variants.util.NBTUtils.writeEffectsOntoNBT;

public class ApplyMobEffectsBehavior extends StewBehavior {
    private final EffectInstance[] effects;

    public ApplyMobEffectsBehavior(EffectInstance... effects) {
        this.effects = effects;
    }

    @Override
    public void executeBehavior(ItemStack stack, World world, LivingEntity livEntity, @Nullable CompoundNBT propertiesTag) {}

    @Override
    public void executeFromStewNBT(ItemStack stewStack, World world, LivingEntity livEntity, @Nullable CompoundNBT propertiesTag) {
        executeBehavior(stewStack, world, livEntity, propertiesTag);
    }

    @Override
    public CompoundNBT writePropertiesToNBT() {
        CompoundNBT properties = new CompoundNBT();
        properties.put("effects", writeEffectsOntoNBT(this.effects));
        return properties;
    }

    @Override
    public List<ITextComponent> addToStewTooltip(ItemStack stack, @Nullable World world, ITooltipFlag flag) {
        List<ITextComponent> tooltip = super.addToStewTooltip(stack, world, flag);
        addEffectsTooltip(stack, world, tooltip, 1);
        return tooltip;
    }

    @Override
    public EffectInstance[] getEffects() {
        return this.effects;
    }

    @Override
    public StewBehavior getBehaviorRegistry() {
        return VSStewBehaviors.APPLY_MOB_EFFECTS.get();
    }

    public void addEffectsTooltip(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, float durationFactor) {
        List<EffectInstance> effectsList = NBTUtils.getEffectsFromNBT(world, stack);

        List<Pair<Attribute, AttributeModifier>> attributesList = Lists.newArrayList();
        if (effectsList != null && !effectsList.isEmpty()) {
            for (EffectInstance instance : effectsList) {
                IFormattableTextComponent component = new TranslationTextComponent(instance.getDescriptionId());
                Effect effect = instance.getEffect();
                Map<Attribute, AttributeModifier> attributeMap = effect.getAttributeModifiers();
                if (!attributeMap.isEmpty()) {
                    for (Map.Entry<Attribute, AttributeModifier> entry : attributeMap.entrySet()) {
                        AttributeModifier modifier = entry.getValue();
                        AttributeModifier newModifier = new AttributeModifier(modifier.getName(), effect.getAttributeModifierValue(instance.getAmplifier(), modifier), modifier.getOperation());
                        attributesList.add(new Pair<>(entry.getKey(), newModifier));
                    }
                }

                if (instance.getAmplifier() > 0) component = new TranslationTextComponent("potion.withAmplifier", component, new TranslationTextComponent("potion.potency." + instance.getAmplifier()));
                if (instance.getDuration() > 20) component = new TranslationTextComponent("potion.withDuration", component, EffectUtils.formatDuration(instance, durationFactor));
                tooltip.add(component.withStyle(VSStyles.getFromRGB(effect.getColor())));
            }
        }

        if (!attributesList.isEmpty()) {
            tooltip.add(new TranslationTextComponent("tooltip." + Variants.MOD_ID + ".exponential_stew.newline"));
            tooltip.add(new TranslationTextComponent("tooltip." + Variants.MOD_ID + ".food_effects.when_" + (stack.getItem().getUseAnimation(stack) == UseAction.DRINK ? "drank" : "eaten")).withStyle(TextFormatting.GRAY));

            for (Pair<Attribute, AttributeModifier> attributePair : attributesList) {
                AttributeModifier modifier = attributePair.getSecond();
                double baseAmount = modifier.getAmount();
                double amount;

                if (modifier.getOperation() != AttributeModifier.Operation.MULTIPLY_BASE && modifier.getOperation() != AttributeModifier.Operation.MULTIPLY_TOTAL) {
                    amount = modifier.getAmount();
                } else {
                    amount = modifier.getAmount() * 100;
                }

                if (baseAmount > 0) {
                    tooltip.add(new TranslationTextComponent("attribute.modifier.plus." + modifier.getOperation().toValue(), ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(amount), new TranslationTextComponent(attributePair.getFirst().getDescriptionId())).withStyle(TextFormatting.BLUE));
                } else if (baseAmount < 0) {
                    amount = amount * -1;
                    tooltip.add(new TranslationTextComponent("attribute.modifier.take." + modifier.getOperation().toValue(), ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(amount), new TranslationTextComponent(attributePair.getFirst().getDescriptionId())).withStyle(TextFormatting.RED));
                }
            }
        }
    }
}
