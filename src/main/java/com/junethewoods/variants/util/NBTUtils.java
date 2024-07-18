package com.junethewoods.variants.util;

import com.junethewoods.variants.Variants;
import com.junethewoods.variants.effect.source.DamageBehaviorSource;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.*;
import java.util.stream.Collectors;

public class NBTUtils {
    // NBT ID Types:
    public static final int END = 0;
    public static final int BYTE = 1;
    public static final int SHORT = 2;
    public static final int INTEGER = 3;
    public static final int LONG = 4;
    public static final int FLOAT = 5;
    public static final int DOUBLE = 6;
    public static final int BYTE_ARRAY = 7;
    public static final int STRING = 8;
    public static final int LIST = 9;
    public static final int COMPOUND = 10;
    public static final int INTEGER_ARRAY = 11;
    public static final int LONG_ARRAY = 12;
    public static final int WILDCARD = 99;

    public static boolean shouldNotHideTooltip(String toHide, ItemStack stack) {
        if (stack.getTag() != null && stack.getTag().contains(toHide)) {
            return !stack.getTag().getBoolean(toHide);
        }
        return true;
    }

    public static void addItemTagsTooltip(ItemStack stack, List<ITextComponent> tooltip, ITooltipFlag flag) {
        if (flag.isAdvanced()) {
            CompoundNBT tagTag = stack.getTag();
            if (tagTag != null) tooltip.add(new TranslationTextComponent("tooltip." + Variants.MOD_ID + ".tags", tagTag.getPrettyDisplay()).withStyle(TextFormatting.GRAY));
        }
    }

    public static ListNBT writeEffectsOntoNBT(EffectInstance[] instances) {
        ListNBT effectsList = new ListNBT();
        if (instances != null) {
            for (EffectInstance instance : instances) {
                CompoundNBT effectTag = new CompoundNBT();

                effectTag.putString("id", instance.getEffect().getRegistryName().toString());
                effectTag.putInt("duration", instance.getDuration());
                if (instance.getAmplifier() > 0) effectTag.putInt("amplifier", instance.getAmplifier());
                if (instance.isAmbient()) effectTag.putBoolean("ambient", instance.isAmbient());
                if (!instance.isVisible()) effectTag.putBoolean("show_particles", instance.isVisible());
                if (!instance.showIcon()) effectTag.putBoolean("show_icon", instance.showIcon());
                if (instance.isNoCounter()) effectTag.putBoolean("no_counter", instance.isNoCounter());
                if (instance.getCurativeItems().size() > 1) {
                    ListNBT curativeItems = new ListNBT();
                    for (ItemStack curativeStack : instance.getCurativeItems()) {
                        CompoundNBT savedStack = curativeStack.save(new CompoundNBT());
                        curativeItems.add(savedStack);
                    }
                    effectTag.put("curative_items", curativeItems);
                }
                effectsList.add(effectTag);
            }
        }
        return effectsList;
    }

    public static Collection<EffectInstance> getEffectsFromNBT(World world, ItemStack stewStack) {
        CompoundNBT behaviorTag = stewStack.getOrCreateTagElement("behavior");
        CompoundNBT propertiesTag = behaviorTag.getCompound("properties");
        Collection<EffectInstance> effects = new ArrayList<>();

        if (propertiesTag.contains("effects", NBTUtils.LIST)) {
            ListNBT effectList = propertiesTag.getList("effects", NBTUtils.COMPOUND);

            for (int i = 0; i < effectList.size(); ++i) {
                int duration = 160; // Default of 8 seconds from Suspicious Stew.
                int amplifier = 0;
                boolean ambient = false;
                boolean showParticles = true;
                boolean showIcon = true;
                boolean noCounter = true;
                CompoundNBT effectTag = effectList.getCompound(i);
                if (effectTag.contains("duration", NBTUtils.INTEGER)) duration = effectTag.getInt("duration");
                if (effectTag.contains("amplifier", NBTUtils.INTEGER)) amplifier = effectTag.getInt("amplifier");
                if (effectTag.contains("ambient")) ambient = effectTag.getBoolean("ambient");
                if (effectTag.contains("show_particles")) showParticles = effectTag.getBoolean("show_particles");
                if (effectTag.contains("show_icon")) showIcon = effectTag.getBoolean("show_icon");
                if (effectTag.contains("no_counter")) noCounter = effectTag.getBoolean("no_counter");

                Effect effect = ForgeRegistries.POTIONS.getValue(ResourceLocation.tryParse(effectTag.getString("id")));
                if (effect != null) {
                    EffectInstance instance = new EffectInstance(effect, duration, amplifier, ambient, showParticles, showIcon);
                    if (world.isClientSide) instance.setNoCounter(noCounter);
                    effects.add(instance);
                }
            }
            return effects;
        }
        return null;
    }

    public static void writeDamageSourceOntoNBT(CompoundNBT propertiesTag, DamageSource source) {
        if (source instanceof DamageBehaviorSource) {
            CompoundNBT sourceTag = new CompoundNBT();
            sourceTag.putString("message_id", source.msgId);
            sourceTag.putFloat("food_exhaustion", source.getFoodExhaustion());
            sourceTag.putString("scaling", ((DamageBehaviorSource) source).scaling.getSerializedName());
            sourceTag.putString("death_message_type", ((DamageBehaviorSource) source).deathMessageType.getSerializedName());
            if (source.isExplosion()) sourceTag.putBoolean("is_explosion", source.isExplosion());
            if (source.isProjectile()) sourceTag.putBoolean("is_projectile", source.isProjectile());
            if (source.isMagic()) sourceTag.putBoolean("is_magic", source.isMagic());
            if (source.isFire()) sourceTag.putBoolean("is_fire", source.isFire());
            if (source.isBypassArmor()) sourceTag.putBoolean("bypasses_armor", source.isBypassArmor());
            if (source.isBypassInvul()) sourceTag.putBoolean("bypasses_invulnerability", source.isBypassInvul());
            if (source.isBypassMagic()) sourceTag.putBoolean("bypasses_magic", source.isBypassMagic());
            propertiesTag.put("source", sourceTag);
        } else {
            Map<ResourceLocation, DamageSource> combinedSourcesMap = new HashMap<>(DamageSourceUtils.DATA_DRIVEN_SOURCES);
            for (ResourceLocation location : DamageSourceUtils.VALID_DAMAGE_SOURCES)
                combinedSourcesMap.put(location, DamageSource.GENERIC);
            List<ResourceLocation> locations = combinedSourcesMap.keySet().stream().filter(location -> location.equals(new ResourceLocation(source.msgId))).collect(Collectors.toList());
            if (!locations.isEmpty()) propertiesTag.putString("source", locations.get(0).toString());
        }
    }

    public static BlockPos readBlockPos(CompoundNBT tag) {
        int[] pos = tag.getIntArray("pos");
        if (pos.length <= 3) return new BlockPos(pos[0], pos[1], pos[2]);
        return new BlockPos(0, 0, 0);
    }

    public static String stringOrDefault(String name, CompoundNBT tag, String fallback) {
        if (tag != null && tag.contains(name, STRING)) return tag.getString(name);
        return fallback;
    }

    public static boolean booleanOrDefault(String name, CompoundNBT tag, boolean fallback) {
        if (tag != null && tag.contains(name, BYTE)) return tag.getBoolean(name);
        return fallback;
    }

    public static int integerOrDefault(String name, CompoundNBT tag, int fallback) {
        if (tag != null && tag.contains(name, INTEGER)) return tag.getInt(name);
        return fallback;
    }

    public static float floatOrDefault(String name, CompoundNBT tag, float fallback) {
        if (tag != null && tag.contains(name, FLOAT)) return tag.getFloat(name);
        return fallback;
    }

    public static CompoundNBT compoundOrDefault(String name, CompoundNBT tag, CompoundNBT fallback) {
        if (tag != null && tag.contains(name, COMPOUND)) return tag.getCompound(name);
        return fallback;
    }
}
