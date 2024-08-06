package melonystudios.variants.item.custom.poisoning;

import melonystudios.variants.Variants;
import melonystudios.variants.effect.VSEffects;
import melonystudios.variants.util.VSRegistries;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

public class VSPoisoningTypes {
    public static final DeferredRegister<PoisoningType> POISONING_TYPES = DeferredRegister.create(VSRegistries.POISONING_TYPE, Variants.MOD_ID);

    public static final RegistryObject<PoisoningType> NONE = POISONING_TYPES.register("none", () -> new PoisoningType(null));
    public static final RegistryObject<PoisoningType> SUGAR = POISONING_TYPES.register("sugar", () -> new PoisoningType(VSEffects.SUGAR_POT_SPEED));
    public static final RegistryObject<PoisoningType> REDSTONE = POISONING_TYPES.register("redstone", () -> new PoisoningType(VSEffects.REDSTONE_POISONING));
    public static final RegistryObject<PoisoningType> BLUESTONE = POISONING_TYPES.register("bluestone", () -> new PoisoningType(VSEffects.BLUESTONE_POISONING));
    public static final RegistryObject<PoisoningType> GLOWSTONE = POISONING_TYPES.register("glowstone", () -> new PoisoningType(VSEffects.GLOWSTONE_POISONING));
    public static final RegistryObject<PoisoningType> GUNPOWDER = POISONING_TYPES.register("gunpowder", () -> new PoisoningType(VSEffects.GUNPOWDER_POISONING));
    public static final RegistryObject<PoisoningType> EXPLOSIVE_BLEND = POISONING_TYPES.register("explosive_blend", () -> new PoisoningType(VSEffects.EXPLOSIVE_BLEND_POISONING));
}
