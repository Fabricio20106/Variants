package com.junethewoods.variants.effect;

import net.minecraft.util.DamageSource;

public class VSDamageSources {
    public static final DamageSource REDSTONE_POISONING = new DamageSource("poisoning.redstone").bypassArmor().bypassMagic();
    public static final DamageSource BLUESTONE_POISONING = new DamageSource("poisoning.bluestone").bypassArmor().bypassMagic();
    public static final DamageSource GLOWSTONE_POISONING = new DamageSource("poisoning.glowstone").bypassArmor().bypassMagic();
    public static final DamageSource GUNPOWDER_POISONING = new DamageSource("poisoning.gunpowder").bypassArmor().bypassMagic();
    public static final DamageSource EXPLOSIVE_POWDER_POISONING = new DamageSource("poisoning.explosive_blend").bypassArmor().bypassMagic();
}
