package com.junethewoods.variants.world.biome;

import com.junethewoods.variants.Variants;
import com.junethewoods.variants.world.feature.VSFeatures;
import com.junethewoods.variants.world.surface.VSSurfaceBuilders;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStage;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class VSBiomes {
    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, Variants.MOD_ID);

    public static final RegistryObject<Biome> ENDERWOOD_FOREST = BIOMES.register("ender_forest", VSBiomes::enderwoodForest);

    private static Biome enderwoodForest() {
        BiomeGenerationSettings.Builder settings = new BiomeGenerationSettings.Builder().surfaceBuilder(VSSurfaceBuilders.ENDERWOOD_FOREST);
        MobSpawnInfo.Builder spawns = new MobSpawnInfo.Builder();

        DefaultBiomeFeatures.endSpawns(spawns);
        settings.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, VSFeatures.ENDERWOOD_FUNGI);
        settings.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, VSFeatures.ENDERWOOD_FOREST_VEGETATION);
        settings.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, VSFeatures.END_SPROUTS);

        return new Biome.Builder().precipitation(Biome.RainType.NONE).biomeCategory(Biome.Category.THEEND).depth(0.1F).scale(0.2F).temperature(0.5F).downfall(0.5F)
                .specialEffects(new BiomeAmbience.Builder().waterColor(0x62529E).waterFogColor(0x4F4280).fogColor(0xA080A0).skyColor(0).ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS).build())
                .mobSpawnSettings(spawns.build()).generationSettings(settings.build()).build();
    }
}
