package melonystudios.variants.item.custom.food;

import melonystudios.variants.Variants;
import melonystudios.variants.item.VSItems;
import melonystudios.variants.item.custom.poisoning.PoisoningType;
import melonystudios.variants.util.Constants;
import melonystudios.variants.util.NBTUtils;
import melonystudios.variants.util.VSRegistries;
import melonystudios.variants.util.VSStyles;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
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

    public boolean hasTypeInNBT(ItemStack potStack) {
        return potStack.getTag() != null && potStack.getTag().contains("poisoning_type", Constants.TagTypes.STRING);
    }

    public boolean hasEffectsInNBT(ItemStack potStack) {
        return potStack.getTag() != null && potStack.getTag().contains("effect", Constants.TagTypes.COMPOUND);
    }

    public PoisoningType getTypeFromNBT(ItemStack potStack) {
        if (hasTypeInNBT(potStack)) {
            ResourceLocation type = ResourceLocation.tryParse(potStack.getTag().getString("poisoning_type"));
            if (type != null && VSRegistries.POISONING_TYPE.containsKey(type)) return VSRegistries.POISONING_TYPE.getValue(type);
        }
        return this.poisoningType;
    }

    public TranslationTextComponent getPoisoningTranslation(ItemStack potStack) {
        TranslationTextComponent fromConstructor = new TranslationTextComponent("poisoning_type." + this.poisoningType.getTypeRegistry().getRegistryName().getNamespace() + "." + this.poisoningType.getTypeRegistry().getRegistryName().getPath());
        if (hasTypeInNBT(potStack)) {
            ResourceLocation poisoningType = ResourceLocation.tryParse(potStack.getTag().getString("poisoning_type"));
            assert poisoningType != null;
            if (!potStack.getTag().contains("poisoning_type", Constants.TagTypes.STRING) || potStack.getTag().getString("poisoning_type").isEmpty()) return fromConstructor;
            return new TranslationTextComponent("poisoning_type." + poisoningType.getNamespace() + "." + poisoningType.getPath());
        } else {
            return fromConstructor;
        }
    }

    @Override
    public void executeFunctionality(ItemStack containerStack, ItemStack potStack, World world, LivingEntity livEntity) {
        PoisoningType type = getTypeFromNBT(potStack);
        this.containerItem = new ItemStack(VSItems.STYLISED_POT.get());
        if (hasEffectsInNBT(potStack)) {
            addEffectsFromNBT(potStack, world, livEntity);
        } else if (type != null && type.getPoisoningEffect() != null) {
            if (!world.isClientSide) {
                livEntity.addEffect(new EffectInstance(this.poisoningType.getPoisoningEffect().get(), this.poisoningDuration));
            }
        }
    }

    public void addEffectsFromNBT(ItemStack potStack, World world, LivingEntity livEntity) {
        CompoundNBT effectTag = potStack.getOrCreateTagElement("effect");
        int duration = 160; // Default of 8 seconds from Suspicious Stew.
        int amplifier = 0;
        boolean ambient = false;
        boolean showParticles = true;
        boolean showIcon = true;
        boolean noCounter = true;

        if (effectTag.contains("duration", Constants.TagTypes.INTEGER)) duration = effectTag.getInt("duration");
        if (effectTag.contains("amplifier", Constants.TagTypes.INTEGER)) amplifier = effectTag.getInt("amplifier");
        if (effectTag.contains("ambient", Constants.TagTypes.BYTE)) ambient = effectTag.getBoolean("ambient");
        if (effectTag.contains("show_particles", Constants.TagTypes.BYTE)) showParticles = effectTag.getBoolean("show_particles");
        if (effectTag.contains("show_icon", Constants.TagTypes.BYTE)) showIcon = effectTag.getBoolean("show_icon");
        if (effectTag.contains("no_counter", Constants.TagTypes.BYTE)) noCounter = effectTag.getBoolean("no_counter");

        Effect effect = ForgeRegistries.POTIONS.getValue(ResourceLocation.tryParse(effectTag.getString("id")));
        if (effect != null) {
            EffectInstance instance = new EffectInstance(effect, duration, amplifier, ambient, showParticles, showIcon);
            if (world.isClientSide) instance.setNoCounter(noCounter);
            livEntity.addEffect(instance);
        }
    }

    @Override
    public void appendHoverText(ItemStack potStack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        super.appendHoverText(potStack, world, tooltip, flag);
        PoisoningType type = getTypeFromNBT(potStack);

        if (this.compatMod != null && NBTUtils.shouldNotHideTooltip("hide_compat_mod", potStack)) {
            tooltip.add(new TranslationTextComponent("tooltip.variants.compat_item_from", this.compatMod).withStyle(TextFormatting.GRAY).withStyle(TextFormatting.ITALIC));
        }
        if (hasEffectsInNBT(potStack)) {
            CompoundNBT effectTag = potStack.getOrCreateTagElement("effect");
            Effect effect = ForgeRegistries.POTIONS.getValue(ResourceLocation.tryParse(effectTag.getString("id")));
            if (effect != null) {
                tooltip.add(new TranslationTextComponent("tooltip." + Variants.MOD_ID + ".stylised_pot.poisoning_or_effect", new TranslationTextComponent(Util.makeDescriptionId("effect", effect.getRegistryName())).withStyle(VSStyles.getFromRGB(
                        effect.getColor()))).withStyle(TextFormatting.GRAY));
            }
        }
        if (type != null && type.getPoisoningEffect() != null) {
            tooltip.add(new TranslationTextComponent("tooltip." + Variants.MOD_ID + ".stylised_pot.poisoning", getPoisoningTranslation(potStack).withStyle(VSStyles.getFromRGB(type.getPoisoningEffect().get().getColor()))).withStyle(
                    TextFormatting.GRAY));
        }
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
