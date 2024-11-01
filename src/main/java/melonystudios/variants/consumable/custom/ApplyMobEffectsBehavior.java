package melonystudios.variants.consumable.custom;

import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;
import melonystudios.variants.Variants;
import melonystudios.variants.config.VSConfigs;
import melonystudios.variants.consumable.ConsumeBehavior;
import melonystudios.variants.consumable.VSConsumeBehaviors;
import melonystudios.variants.util.Constants;
import melonystudios.variants.util.NBTUtils;
import melonystudios.variants.util.VSStyles;
import melonystudios.variants.util.VSUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.UseAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.*;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

import static melonystudios.variants.util.NBTUtils.writeEffectsOntoNBT;

public class ApplyMobEffectsBehavior extends ConsumeBehavior {
    private final List<? extends EffectInstance> effects;

    public ApplyMobEffectsBehavior(List<? extends EffectInstance> effects) {
        this.effects = effects;
    }

    public List<? extends EffectInstance> getEffects() {
        return this.effects;
    }

    @Override
    public void runBehavior(ItemStack stack, World world, LivingEntity livEntity, @Nullable CompoundNBT propertiesTag) {
        if (propertiesTag != null && propertiesTag.contains("effects", Constants.TagTypes.LIST)) {
            ListNBT effectList = propertiesTag.getList("effects", Constants.TagTypes.COMPOUND);

            for (int i = 0; i < effectList.size(); ++i) NBTUtils.addEffectsFromNBT(effectList.getCompound(i), world, livEntity);
        }
    }

    @Override
    public void loadFromNBT(ItemStack stack, World world, LivingEntity livEntity, @Nullable CompoundNBT propertiesTag) {
        List<EffectInstance> effects = Lists.newArrayList();
        if (propertiesTag != null && propertiesTag.contains("effects", Constants.TagTypes.LIST)) {
            ListNBT effectList = propertiesTag.getList("effects", Constants.TagTypes.COMPOUND);
            for (int i = 0; i < effectList.size(); ++i) {
                List<ItemStack> curativeItemsTemplate = Lists.newArrayList(new ItemStack(Items.MILK_BUCKET));

                int duration = 160; // Default of 8 seconds from Suspicious Stew.
                int amplifier = 0;
                boolean ambient = false;
                boolean showParticles = true;
                boolean showIcon = true;
                boolean noCounter = false; // Finally found out what no_counter does, it just hides the effect duration (shows up as **:**).
                List<ItemStack> curativeItems = Lists.newArrayList();
                CompoundNBT effectTag = effectList.getCompound(i);
                if (effectTag.contains("duration", Constants.TagTypes.ANY_NUMERIC)) duration = effectTag.getInt("duration");
                if (effectTag.contains("amplifier", Constants.TagTypes.ANY_NUMERIC)) amplifier = effectTag.getInt("amplifier");
                if (effectTag.contains("ambient", Constants.TagTypes.ANY_NUMERIC)) ambient = effectTag.getBoolean("ambient");
                if (effectTag.contains("show_particles", Constants.TagTypes.ANY_NUMERIC)) showParticles = effectTag.getBoolean("show_particles");
                if (effectTag.contains("show_icon", Constants.TagTypes.ANY_NUMERIC)) showIcon = effectTag.getBoolean("show_icon");
                if (effectTag.contains("no_counter", Constants.TagTypes.ANY_NUMERIC)) noCounter = effectTag.getBoolean("no_counter");
                if (effectTag.contains("curative_items", Constants.TagTypes.LIST)) {
                    ListNBT curativeList = effectTag.getList("curative_items", Constants.TagTypes.COMPOUND);
                    for (int c = 0; c < curativeList.size(); c++) curativeItems.add(VSUtils.loadStack(curativeList.getCompound(c)));
                }

                Effect effect = ForgeRegistries.POTIONS.getValue(ResourceLocation.tryParse(effectTag.getString("id")));
                if (effect != null) {
                    EffectInstance instance = new EffectInstance(effect, duration, amplifier, ambient, showParticles, showIcon);
                    if (world != null && world.isClientSide) instance.setNoCounter(noCounter);
                    if (!curativeItems.isEmpty() && !curativeItems.equals(curativeItemsTemplate)) instance.setCurativeItems(curativeItems);
                    effects.add(instance);
                }
            }
        }

        ApplyMobEffectsBehavior behavior = new ApplyMobEffectsBehavior(effects);
        behavior.runBehavior(stack, world, livEntity, propertiesTag);
    }

    @Override
    public CompoundNBT writeProperties() {
        CompoundNBT properties = new CompoundNBT();
        ListNBT effectsList = writeEffectsOntoNBT(this.effects);
        if (!effectsList.isEmpty()) properties.put("effects", effectsList);
        return properties;
    }

    @Override
    public List<ITextComponent> addToTooltip(ItemStack stack, @Nullable World world, ITooltipFlag flag) {
        List<ITextComponent> tooltip = super.addToTooltip(stack, world, flag);
        addEffectsTooltip(stack, world, tooltip, 1);
        return tooltip;
    }

    @Override
    public ConsumeBehavior registryEntry() {
        return VSConsumeBehaviors.APPLY_MOB_EFFECTS.get();
    }

    public static void addEffectsTooltip(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, float durationFactor) {
        List<EffectInstance> effectsList = NBTUtils.getEffectsFromNBT(world, stack);
        if (stack.getTag() != null && stack.getTag().contains("duration_factor", Constants.TagTypes.ANY_NUMERIC)) durationFactor = stack.getTag().getFloat("duration_factor");
        float durationPercentage = durationFactor * 100;
        if (durationFactor != 1 && VSConfigs.COMMON_CONFIGS.durationFactorTooltip.get()) tooltip.add(new TranslationTextComponent("tooltip.variants.food_effects.duration_factor", durationPercentage).withStyle(TextFormatting.DARK_GRAY));

        List<Pair<Attribute, AttributeModifier>> attributePairList = Lists.newArrayList();
        if (effectsList != null && !effectsList.isEmpty()) {
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
                tooltip.add(getCategoryTranslation(instance, component.withStyle(VSStyles.getFromRGB(effect.getColor()))));
            }
        } else {
            tooltip.add(new TranslationTextComponent("tooltip.variants.food_effects.no_effects").withStyle(TextFormatting.GRAY));
        }

        if (!attributePairList.isEmpty()) {
            tooltip.add(new TranslationTextComponent("tooltip." + Variants.MOD_ID + ".exponential_stew.newline"));
            tooltip.add(new TranslationTextComponent("tooltip." + Variants.MOD_ID + ".food_effects.when_" + (stack.getItem().getUseAnimation(stack) == UseAction.DRINK ? "drank" : "eaten")).withStyle(TextFormatting.GRAY));

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

    public static IFormattableTextComponent getCategoryTranslation(EffectInstance instance, Object... arguments) {
        switch (instance.getEffect().getCategory()) {
            case NEUTRAL: return new TranslationTextComponent("tooltip.variants.food_effects.neutral_effect", arguments).withStyle(TextFormatting.DARK_GRAY);
            case HARMFUL: return new TranslationTextComponent("tooltip.variants.food_effects.harmful_effect", arguments).withStyle(TextFormatting.DARK_GRAY);
            case BENEFICIAL: default: return new TranslationTextComponent("tooltip.variants.food_effects.beneficial_effect", arguments).withStyle(TextFormatting.DARK_GRAY);
        }
    }
}
