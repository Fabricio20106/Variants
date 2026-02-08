package melonystudios.revaried.fluid;

import melonystudios.revaried.Revaried;
import melonystudios.revaried.fluid.custom.MushroomStewFluid;
import melonystudios.revaried.fluid.custom.SoulLavaFluid;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class RVFluids {
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(Registries.FLUID, Revaried.MOD_ID);

    public static final DeferredHolder<Fluid, MushroomStewFluid.Source> MUSHROOM_STEW = FLUIDS.register("mushroom_stew", () -> new MushroomStewFluid.Source(MushroomStewFluid.PROPERTIES));
    public static final DeferredHolder<Fluid, MushroomStewFluid.Flowing> FLOWING_MUSHROOM_STEW = FLUIDS.register("flowing_mushroom_stew", () -> new MushroomStewFluid.Flowing(MushroomStewFluid.PROPERTIES));
    public static final DeferredHolder<Fluid, SoulLavaFluid.Source> SOUL_LAVA = FLUIDS.register("soul_lava", () -> new SoulLavaFluid.Source(SoulLavaFluid.PROPERTIES));
    public static final DeferredHolder<Fluid, SoulLavaFluid.Flowing> FLOWING_SOUL_LAVA = FLUIDS.register("flowing_soul_lava", () -> new SoulLavaFluid.Flowing(SoulLavaFluid.PROPERTIES));
}
