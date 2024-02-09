package com.junethewoods.variants.world.carver;

import com.junethewoods.variants.Variants;
import com.junethewoods.variants.world.carver.custom.EndCaveCarver;
import com.junethewoods.variants.world.carver.custom.EndRavineCarver;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class VSWorldCarvers {
    public static final DeferredRegister<WorldCarver<?>> CARVERS = DeferredRegister.create(ForgeRegistries.WORLD_CARVERS, Variants.MOD_ID);

    public static final RegistryObject<WorldCarver<ProbabilityConfig>> END_CAVE = CARVERS.register("end_carver", () -> new EndCaveCarver(ProbabilityConfig.CODEC));
    public static final RegistryObject<WorldCarver<ProbabilityConfig>> END_RAVINE = CARVERS.register("end_ravine", () -> new EndRavineCarver(ProbabilityConfig.CODEC));
}
