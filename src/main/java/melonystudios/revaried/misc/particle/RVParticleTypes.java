package melonystudios.revaried.misc.particle;

import melonystudios.revaried.Revaried;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class RVParticleTypes {
    public static final DeferredRegister<ParticleType<?>> TYPES = DeferredRegister.create(Registries.PARTICLE_TYPE, Revaried.MOD_ID);

    public static final Supplier<SimpleParticleType> SOUL_LAVA = TYPES.register("soul_lava", () -> new SimpleParticleType(false));
    public static final Supplier<SimpleParticleType> DRIPPING_SOUL_LAVA = TYPES.register("dripping_soul_lava", () -> new SimpleParticleType(false));
    public static final Supplier<SimpleParticleType> FALLING_SOUL_LAVA = TYPES.register("falling_soul_lava", () -> new SimpleParticleType(false));
    public static final Supplier<SimpleParticleType> LANDING_SOUL_LAVA = TYPES.register("landing_soul_lava", () -> new SimpleParticleType(false));
    public static final Supplier<SimpleParticleType> DRIPPING_DRIPSTONE_SOUL_LAVA = TYPES.register("dripping_dripstone_soul_lava", () -> new SimpleParticleType(false));
    public static final Supplier<SimpleParticleType> FALLING_DRIPSTONE_SOUL_LAVA = TYPES.register("falling_dripstone_soul_lava", () -> new SimpleParticleType(false));
}
