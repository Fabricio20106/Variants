package melonystudios.variants.mixin.item;

import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;
import melonystudios.variants.Variants;
import melonystudios.variants.config.VSConfigs;
import melonystudios.variants.stew.custom.ApplyMobEffectsBehavior;
import melonystudios.variants.util.Constants;
import melonystudios.variants.util.VSStyles;
import melonystudios.variants.util.tag.VSItemTags;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectUtils;
import net.minecraft.potion.PotionUtils;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.text.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Map;

@Mixin(PotionUtils.class)
public abstract class VSPotionUtilsMixin {
    @Unique
    private static final IFormattableTextComponent NO_EFFECT = new TranslationTextComponent("tooltip.variants.food_effects.no_effects").withStyle(TextFormatting.GRAY);

    @Shadow
    public static List<EffectInstance> getMobEffects(ItemStack stack) {
        return null;
    }

    @OnlyIn(Dist.CLIENT)
    @Inject(method = "addPotionTooltip", at = @At("HEAD"), cancellable = true)
    private static void addPotionTooltip(ItemStack stack, List<ITextComponent> tooltip, float durationFactor, CallbackInfo ci) {
        if (VSConfigs.COMMON_CONFIGS.customPotionDescriptions.get()) {
            ci.cancel();
            List<EffectInstance> effectsList = getMobEffects(stack);
            List<Pair<Attribute, AttributeModifier>> attributePairList = Lists.newArrayList();
            assert effectsList != null;

            if (stack.getTag() != null && stack.getTag().contains("duration_factor", Constants.TagTypes.ANY_NUMERIC)) durationFactor = stack.getTag().getFloat("duration_factor");
            float durationPercentage = durationFactor * 100;
            if (durationFactor != 1 && VSConfigs.COMMON_CONFIGS.durationFactorTooltip.get()) tooltip.add(new TranslationTextComponent("tooltip.variants.food_effects.duration_factor", durationPercentage).withStyle(TextFormatting.DARK_GRAY));

            if (effectsList.isEmpty()) {
                tooltip.add(NO_EFFECT);
            } else {
                for (EffectInstance instance : effectsList) {
                    IFormattableTextComponent component = new TranslationTextComponent(instance.getDescriptionId());
                    Effect effect = instance.getEffect();
                    Map<Attribute, AttributeModifier> attributeMap = effect.getAttributeModifiers();
                    if (!attributeMap.isEmpty()) {
                        for (Map.Entry<Attribute, AttributeModifier> entry : attributeMap.entrySet()) {
                            AttributeModifier modifier = entry.getValue();
                            AttributeModifier newModifier = new AttributeModifier(modifier.getName(), effect.getAttributeModifierValue(instance.getAmplifier(), modifier), modifier.getOperation());
                            attributePairList.add(new Pair<>(entry.getKey(), newModifier));
                        }
                    }

                    if (instance.getAmplifier() > 0) component = new TranslationTextComponent("potion.withAmplifier", component, new TranslationTextComponent("potion.potency." + instance.getAmplifier()));
                    if (instance.getDuration() > 20) component = new TranslationTextComponent("potion.withDuration", component, EffectUtils.formatDuration(instance, durationFactor));
                    tooltip.add(ApplyMobEffectsBehavior.getCategoryTranslation(instance, component.withStyle(VSStyles.getFromRGB(effect.getColor()))));
                }
            }

            if (!attributePairList.isEmpty()) {
                tooltip.add(new TranslationTextComponent("tooltip." + Variants.MOD_ID + ".exponential_stew.newline"));
                tooltip.add(new TranslationTextComponent("tooltip." + Variants.MOD_ID + ".food_effects.when_" + getConsumeString(stack)).withStyle(TextFormatting.GRAY));

                for (Pair<Attribute, AttributeModifier> attributePair : attributePairList) {
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

    @Unique
    private static String getConsumeString(ItemStack stack) {
        if (!ItemTags.getAllTags().getAllTags().isEmpty()) {
            if (stack.getItem().is(VSItemTags.THROWABLE_POTIONS)) return "thrown";
        }
        return stack.getItem().getUseAnimation(stack) == UseAction.DRINK ? "drank" : "eaten";
    }
}
