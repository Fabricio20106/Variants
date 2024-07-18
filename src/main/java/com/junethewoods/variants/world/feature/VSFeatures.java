package com.junethewoods.variants.world.feature;

import com.junethewoods.variants.Variants;
import com.junethewoods.variants.world.feature.custom.WarpingVinesFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class VSFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, Variants.MOD_ID);

    public static final RegistryObject<Feature<NoFeatureConfig>> WARPING_VINES = FEATURES.register("warping_vines", () -> new WarpingVinesFeature(NoFeatureConfig.CODEC));
}
