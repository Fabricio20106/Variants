package com.junethewoods.variants.config;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class VSConfigs {
    private static final Pair<VSCommonConfigs, ForgeConfigSpec> COMMON_CONFIG_PAIR = new ForgeConfigSpec.Builder().configure(VSCommonConfigs::new);
    public static final VSCommonConfigs COMMON_CONFIGS = COMMON_CONFIG_PAIR.getLeft();
    public static final ForgeConfigSpec COMMON_SPEC = COMMON_CONFIG_PAIR.getRight();
}
