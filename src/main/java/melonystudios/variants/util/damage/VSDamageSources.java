package melonystudios.variants.util.damage;

import melonystudios.variants.util.damage.custom.MidasTouchDamageSource;
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

    // April Fools Damage Sources
    // 3D Shareware 1.34
    public static final DamageSource TOO_SOFT = new DamageSource("nightmare").bypassArmor().bypassMagic();

    // 23w13a_or_b
    public static final DamageSource ON_MOON = new DamageSource("on_moon");
    public static final DamageSource MIDAS_TOUCH = new MidasTouchDamageSource();

    // 24w14potato
    public static final DamageSource HOT_POTATO = new DamageSource("potato_heat").setIsFire();
    public static final DamageSource POTATO_BATTERY = new DamageSource("potato_magic").setMagic();
}
