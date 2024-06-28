package com.junethewoods.variants.util;

import com.junethewoods.variants.Variants;
import com.junethewoods.variants.effect.VSDamageSources;
import com.junethewoods.variants.effect.source.DamageBehaviorSource;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class NBTUtils {
    // NBT ID Types:
    public static final int BYTE = 1;
    public static final int INTEGER = 3;
    public static final int FLOAT = 5;
    public static final int STRING = 8;
    public static final int LIST = 9;
    public static final int COMPOUND = 10;
    public static final int INTEGER_ARRAY = 11;
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
                if (effectTag.contains("ambient", NBTUtils.BYTE)) ambient = effectTag.getBoolean("ambient");
                if (effectTag.contains("show_particles", NBTUtils.BYTE)) showParticles = effectTag.getBoolean("show_particles");
                if (effectTag.contains("show_icon", NBTUtils.BYTE)) showIcon = effectTag.getBoolean("show_icon");
                if (effectTag.contains("no_counter", NBTUtils.BYTE)) noCounter = effectTag.getBoolean("no_counter");

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
            if (source.isExplosion()) sourceTag.putBoolean("is_explosion", source.isExplosion());
            if (source.isProjectile()) sourceTag.putBoolean("is_projectile", source.isProjectile());
            if (source.isMagic()) sourceTag.putBoolean("is_magic", source.isMagic());
            if (source.isFire()) sourceTag.putBoolean("is_fire", source.isFire());
            if (source.scalesWithDifficulty()) sourceTag.putBoolean("scales_with_difficulty", source.scalesWithDifficulty());
            if (source.isBypassArmor()) sourceTag.putBoolean("bypasses_armor", source.isBypassArmor());
            if (source.isBypassInvul()) sourceTag.putBoolean("bypasses_invulnerability", source.isBypassInvul());
            if (source.isBypassMagic()) sourceTag.putBoolean("bypasses_magic", source.isBypassMagic());
            propertiesTag.put("source", sourceTag);
        } else {
            propertiesTag.putString("source", toMessageID(source.msgId));
        }
    }

    public static BlockPos readBlockPos(CompoundNBT tag) {
        int[] pos = tag.getIntArray("pos");
        if (pos.length <= 4) return new BlockPos(pos[0], pos[1], pos[2]);
        return new BlockPos(0, 0, 0);
    }

    public static String toMessageID(String messageID) {
        switch (messageID) {
            case "inFire": return "in_fire";
            case "lightningBolt": return "lightning_bolt";
            case "onFire": return "on_fire";
            case "hotFloor": return "hot_floor";
            case "inWall": return "in_wall";
            case "flyIntoWall": return "fly_into_wall";
            case "outOfWorld": return "out_of_world";
            case "fallingBlock": return "falling_block";
            case "dragonBreath": return "dragon_breath";
            case "dryout": return "dry_out";
            case "sweetBerryBush": return "sweet_berry_bush";
            case "witherSkull": return "wither_skull";
            case "indirectMagic": return "indirect_magic";
            case "fireworks": return "firework_rocket";
            case "badRespawnPoint": return "bad_respawn_point";
            case "mob": return "mob_attack";
            default: return messageID;
        }
    }

    public static DamageSource fromMessageID(LivingEntity livEntity, String messageID) {
        switch (messageID) {
            case "in_fire": return DamageSource.IN_FIRE;
            case "on_fire": return DamageSource.ON_FIRE;
            case "lava": return DamageSource.LAVA;
            case "lightning_bolt": return DamageSource.LIGHTNING_BOLT;
            case "hot_floor": return DamageSource.HOT_FLOOR;
            case "in_wall": return DamageSource.IN_WALL;
            case "fly_into_wall": return DamageSource.FLY_INTO_WALL;
            case "cramming": return DamageSource.CRAMMING;
            case "drown": return DamageSource.DROWN;
            case "starve": return DamageSource.STARVE;
            case "cactus": return DamageSource.CACTUS;
            case "fall": return DamageSource.FALL;
            case "magic": return DamageSource.MAGIC;
            case "indirect_magic": return DamageSource.indirectMagic(livEntity, livEntity);
            case "wither": return DamageSource.WITHER;
            case "anvil": return DamageSource.ANVIL;
            case "out_of_world": return DamageSource.OUT_OF_WORLD;
            case "falling_block": return DamageSource.FALLING_BLOCK;
            case "dragon_breath": return DamageSource.DRAGON_BREATH;
            case "dry_out": return DamageSource.DRY_OUT;
            case "sweet_berry_bush": return DamageSource.SWEET_BERRY_BUSH;
            case "bad_respawn_point": return DamageSource.badRespawnPointExplosion();
            case "sting": return DamageSource.sting(livEntity);
            case "mob_attack": return DamageSource.mobAttack(livEntity);
            case "indirect_mob_attack": return DamageSource.indirectMobAttack(livEntity, livEntity);
            case "trident": return DamageSource.trident(livEntity, livEntity);
            case "thrown": return DamageSource.thrown(livEntity, livEntity);
            case "thorns": return DamageSource.thorns(livEntity);
            case "explosion": return DamageSource.explosion(livEntity);
            case "redstone_poisoning": return VSDamageSources.REDSTONE_POISONING;
            case "bluestone_poisoning": return VSDamageSources.BLUESTONE_POISONING;
            case "glowstone_poisoning": return VSDamageSources.GLOWSTONE_POISONING;
            case "gunpowder_poisoning": return VSDamageSources.GUNPOWDER_POISONING;
            case "explosive_blend_poisoning": return VSDamageSources.EXPLOSIVE_BLEND_POISONING;
            default: return DamageSource.GENERIC;
        }
    }

    public static String stringOrDefault(String name, CompoundNBT tag, String fallback) {
        if (tag.contains(name, STRING)) return tag.getString(name);
        return fallback;
    }

    public static boolean booleanOrDefault(String name, CompoundNBT tag, boolean fallback) {
        if (tag.contains(name, BYTE)) return tag.getBoolean(name);
        return fallback;
    }

    public static float floatOrDefault(String name, CompoundNBT tag, float fallback) {
        if (tag.contains(name, FLOAT)) return tag.getFloat(name);
        return fallback;
    }
}
