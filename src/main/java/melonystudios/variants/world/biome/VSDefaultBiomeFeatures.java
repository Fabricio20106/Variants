package melonystudios.variants.world.biome;

import melonystudios.variants.world.feature.VSConfiguredFeatures;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;

public class VSDefaultBiomeFeatures {
    public static void addPaintingTrees(BiomeGenerationSettings.Builder settings) {
        settings.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, VSConfiguredFeatures.PAINTINGWOOD_FOREST_TREES);
        settings.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, VSConfiguredFeatures.PAINTINGWOOD_FOREST_TREES);
    }

    public static void addVariantsFlowers(BiomeGenerationSettings.Builder settings) {
        settings.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, VSConfiguredFeatures.VARIANTS_FLOWER_PATCH);
    }

    public static void addTallPlants(BiomeGenerationSettings.Builder settings) {
        settings.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, VSConfiguredFeatures.TALL_PLANT_PATCH);
    }

    public static void addDefaultOverworldFeatures(BiomeGenerationSettings.Builder settings) {
        DefaultBiomeFeatures.addDefaultOverworldLandStructures(settings);
        DefaultBiomeFeatures.addDefaultCarvers(settings);
        DefaultBiomeFeatures.addDefaultLakes(settings);
        DefaultBiomeFeatures.addDefaultMonsterRoom(settings);
        DefaultBiomeFeatures.addDefaultUndergroundVariety(settings);
        DefaultBiomeFeatures.addDefaultMushrooms(settings);
        DefaultBiomeFeatures.addDefaultExtraVegetation(settings);
        DefaultBiomeFeatures.addDefaultSprings(settings);
        DefaultBiomeFeatures.addSurfaceFreezing(settings);
        DefaultBiomeFeatures.addDefaultOres(settings);
        DefaultBiomeFeatures.addDefaultSoftDisks(settings);
    }

    public static void addAzureFieldsFeatures(BiomeGenerationSettings.Builder settings) {
        settings.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, VSConfiguredFeatures.AZURE_BUSH);
        settings.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, VSConfiguredFeatures.AZURE_BLUET_PATCH);
    }
}
