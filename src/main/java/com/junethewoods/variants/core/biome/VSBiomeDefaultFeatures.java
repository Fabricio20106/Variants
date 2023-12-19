package com.junethewoods.variants.core.biome;

import com.junethewoods.variants.core.world.vegetation.VSVegetationPlacements;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.levelgen.GenerationStep;

public class VSBiomeDefaultFeatures {
    public static void addPaintingTrees(BiomeGenerationSettings.Builder settings) {
        settings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VSVegetationPlacements.PAINTING_TREES);
    }
}
