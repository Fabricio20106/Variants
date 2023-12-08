package com.junethewoods.variants.effect;

import com.junethewoods.variants.Variants;
import com.junethewoods.variants.effect.custom.*;
import net.minecraft.potion.Effect;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class VSEffects {
    public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, Variants.MOD_ID);

    public static final RegistryObject<Effect> REDSTONE_POISONING = EFFECTS.register("redstone_poisoning", RedstonePoisoningEffect::new);
    public static final RegistryObject<Effect> BLUESTONE_POISONING = EFFECTS.register("bluestone_poisoning", BluestonePoisoningEffect::new);
    public static final RegistryObject<Effect> GLOWSTONE_POISONING = EFFECTS.register("glowstone_poisoning", GlowstonePoisoningEffect::new);
    public static final RegistryObject<Effect> EXPLOSIVE_BLEND_POISONING = EFFECTS.register("creeper_powder_poisoning", ExplosiveBlendPoisoningEffect::new);
    public static final RegistryObject<Effect> GUNPOWDER_POISONING = EFFECTS.register("gunpowder_poisoning", GunpowderPoisoningEffect::new);
}
