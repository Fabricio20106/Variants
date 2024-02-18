package com.junethewoods.variants.fluid;

import com.junethewoods.variants.Variants;
import com.junethewoods.variants.fluid.custom.MushroomStewFluid;
import com.junethewoods.variants.fluid.custom.SoulLavaFluid;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class VSFluids {
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, Variants.MOD_ID);

    public static final RegistryObject<FlowingFluid> SOUL_LAVA = FLUIDS.register("soul_lava", SoulLavaFluid.Source::new);
    public static final RegistryObject<FlowingFluid> FLOWING_SOUL_LAVA = FLUIDS.register("flowing_soul_lava", SoulLavaFluid.Flowing::new);
    public static final RegistryObject<FlowingFluid> MUSHROOM_STEW = FLUIDS.register("mushroom_stew", MushroomStewFluid.Source::new);
    public static final RegistryObject<FlowingFluid> FLOWING_MUSHROOM_STEW = FLUIDS.register("flowing_mushroom_stew", MushroomStewFluid.Flowing::new);
}
