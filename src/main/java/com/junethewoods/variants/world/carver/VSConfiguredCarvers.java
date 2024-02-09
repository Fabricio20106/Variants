package com.junethewoods.variants.world.carver;

import com.junethewoods.variants.Variants;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.carver.ConfiguredCarver;
import net.minecraft.world.gen.carver.ICarverConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;

public class VSConfiguredCarvers {
    public static final ConfiguredCarver<ProbabilityConfig> END_CAVE = register("end_cave", VSWorldCarvers.END_CAVE.get().configured(new ProbabilityConfig(0.14285715f))); // 14.285715%
    public static final ConfiguredCarver<ProbabilityConfig> END_RAVINE = register("end_ravine", VSWorldCarvers.END_RAVINE.get().configured(new ProbabilityConfig(0.02f))); // 2%

    private static <WC extends ICarverConfig> ConfiguredCarver<WC> register(String name, ConfiguredCarver<WC> carver) {
        return WorldGenRegistries.register(WorldGenRegistries.CONFIGURED_CARVER, Variants.resourceLoc(name), carver);
    }

    public static void init() {}
}
