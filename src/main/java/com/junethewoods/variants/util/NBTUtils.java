package com.junethewoods.variants.util;

import com.junethewoods.variants.Variants;
import com.junethewoods.variants.effect.VSDamageSources;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.List;

public class NBTUtils {
    public static boolean shouldHideTooltip(String toHide, ItemStack stack) {
        return stack.getTag() != null && !stack.getTag().getBoolean(toHide);
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

    public static BlockPos readBlockPos(CompoundNBT tag) {
        int[] pos = tag.getIntArray("pos");
        return new BlockPos(pos[0], pos[1], pos[2]);
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
            default: return messageID;
        }
    }

    public static DamageSource fromMessageID(String messageID) {
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
            case "wither": return DamageSource.WITHER;
            case "anvil": return DamageSource.ANVIL;
            case "out_of_world": return DamageSource.OUT_OF_WORLD;
            case "falling_block": return DamageSource.FALLING_BLOCK;
            case "dragon_breath": return DamageSource.DRAGON_BREATH;
            case "dry_out": return DamageSource.DRY_OUT;
            case "sweet_berry_bush": return DamageSource.SWEET_BERRY_BUSH;
            case "bad_respawn_point": return DamageSource.badRespawnPointExplosion();
            case "redstone_poisoning": return VSDamageSources.REDSTONE_POISONING;
            case "bluestone_poisoning": return VSDamageSources.BLUESTONE_POISONING;
            case "glowstone_poisoning": return VSDamageSources.GLOWSTONE_POISONING;
            case "gunpowder_poisoning": return VSDamageSources.GUNPOWDER_POISONING;
            case "explosive_blend_poisoning": return VSDamageSources.EXPLOSIVE_POWDER_POISONING;
            default: return DamageSource.GENERIC;
        }
    }
}
