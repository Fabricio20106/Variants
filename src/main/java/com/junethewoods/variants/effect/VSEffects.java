package com.junethewoods.variants.effect;

import com.junethewoods.variants.Variants;
import com.junethewoods.variants.effect.custom.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class VSEffects {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, Variants.MOD_ID);

    public static final Supplier<MobEffect> REDSTONE_POISONING = EFFECTS.register("redstone_poisoning", RedstonePoisoningEffect::new);
    public static final Supplier<MobEffect> BLUESTONE_POISONING = EFFECTS.register("bluestone_poisoning", BluestonePoisoningEffect::new);
    public static final Supplier<MobEffect> GLOWSTONE_POISONING = EFFECTS.register("glowstone_poisoning", GlowstonePoisoningEffect::new);
    public static final Supplier<MobEffect> CREEPER_POWDER_POISONING = EFFECTS.register("creeper_powder_poisoning", ExplosiveBlendPoisoningEffect::new);
    public static final Supplier<MobEffect> GUNPOWDER_POISONING = EFFECTS.register("gunpowder_poisoning", GunpowderPoisoningEffect::new);
}
