package com.junethewoods.variants.effect;

import net.minecraft.util.DamageSource;

public class VSDamageSources {
    public static final DamageSource REDSTONE_POISONING = new DamageSource("redstone_poisoning").bypassArmor().bypassMagic();
    public static final DamageSource BLUESTONE_POISONING = new DamageSource("bluestone_poisoning").bypassArmor().bypassMagic();
    public static final DamageSource GLOWSTONE_POISONING = new DamageSource("glowstone_poisoning").bypassArmor().bypassMagic();
    public static final DamageSource GUNPOWDER_POISONING = new DamageSource("gunpowder_poisoning").bypassArmor().bypassMagic();
    public static final DamageSource EXPLOSIVE_BLEND_POISONING = new DamageSource("explosive_blend_poisoning").bypassArmor().bypassMagic();

    // Unregistered Vanilla Damage Sources:
    public static final DamageSource EVEN_MORE_MAGIC = new DamageSource("even_more_magic").setMagic();
    public static final DamageSource MESSAGE_TOO_LONG = new DamageSource("message_too_long");
}
