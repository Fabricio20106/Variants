package com.junethewoods.variants.core.biome;

import com.junethewoods.variants.core.Variants;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class VSBiomes {
    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, Variants.MOD_ID);

    /*public static final RegistryObject<Biome> painting_wooded_forest = biomes.register("painting_wooded_forest", BiomeInit::createPaintingWoodedForest);
    public static final RegistryObject<Biome> azure_fields = biomes.register("azure_fields", BiomeInit::createAzureFields);
    public static final RegistryObject<Biome> ender_forest = biomes.register("ender_forest", BiomeInit::createEnderForest);

    public static Biome createPaintingWoodedForest() {
        MobSpawnInfo.Builder mobSpawnInfo = new MobSpawnInfo.Builder().setPlayerCanSpawn();

        BiomeGenerationSettings.Builder biomeGenerationSettings = new BiomeGenerationSettings.Builder().surfaceBuilder(ConfiguredSurfaceBuilders.GRASS);

        // Structures
        biomeGenerationSettings.addStructureStart(StructureFeatures.RUINED_PORTAL_STANDARD);
        biomeGenerationSettings.addStructureStart(StructureFeatures.MINESHAFT);
        biomeGenerationSettings.addStructureStart(StructureFeatures.STRONGHOLD);

        DefaultBiomeFeatures.addDefaultCarvers(biomeGenerationSettings);
        DefaultBiomeFeatures.addDefaultLakes(biomeGenerationSettings);
        DefaultBiomeFeatures.addDefaultMonsterRoom(biomeGenerationSettings);

        // Underground Ores / Disks
        DefaultBiomeFeatures.addDefaultUndergroundVariety(biomeGenerationSettings);
        DefaultBiomeFeatures.addDefaultOres(biomeGenerationSettings);
        DefaultBiomeFeatures.addDefaultSoftDisks(biomeGenerationSettings);

        // Vegetal Decoration
        VariantBiomeFeatures.addPaintingTrees(biomeGenerationSettings);
        VariantBiomeFeatures.addVariantFlowers(biomeGenerationSettings);
        DefaultBiomeFeatures.addDefaultFlowers(biomeGenerationSettings);
        DefaultBiomeFeatures.addDefaultGrass(biomeGenerationSettings);

        DefaultBiomeFeatures.addDefaultMushrooms(biomeGenerationSettings);
        DefaultBiomeFeatures.addDefaultExtraVegetation(biomeGenerationSettings);
        DefaultBiomeFeatures.addDefaultSprings(biomeGenerationSettings);
        return new Biome.BiomeBuilder().precipitation(Biome.Precipitation.RAIN).biomeCategory(Biome.BiomeCategory.FOREST).depth(0.1F).scale(0.2F).temperature(0.7F).downfall(0.8F).specialEffects((new
                BiomeAmbience.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(12638463).skyColor(calculateSkyColor(0.7F))
                .ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(mobSpawnInfo.build()).generationSettings(biomeGenerationSettings
                .build()).build();
    }

    public static Biome createAzureFields() {
        MobSpawnInfo.Builder mobSpawnInfo = new MobSpawnInfo.Builder().setPlayerCanSpawn();
        BiomeGenerationSettings.Builder biomeGenerationSettings = new BiomeGenerationSettings.Builder().surfaceBuilder(ConfiguredSurfaceBuilders.GRASS);

        // Structures
        biomeGenerationSettings.addStructureStart(StructureFeatures.RUINED_PORTAL_STANDARD);
        biomeGenerationSettings.addStructureStart(StructureFeatures.MINESHAFT);
        biomeGenerationSettings.addStructureStart(StructureFeatures.STRONGHOLD);

        DefaultBiomeFeatures.addDefaultCarvers(biomeGenerationSettings);
        DefaultBiomeFeatures.addDefaultLakes(biomeGenerationSettings);
        DefaultBiomeFeatures.addDefaultMonsterRoom(biomeGenerationSettings);

        // Underground Ores / Disks
        DefaultBiomeFeatures.addDefaultUndergroundVariety(biomeGenerationSettings);
        DefaultBiomeFeatures.addDefaultOres(biomeGenerationSettings);
        DefaultBiomeFeatures.addDefaultSoftDisks(biomeGenerationSettings);

        // Vegetal Decoration
        VariantBiomeFeatures.addVariantFlowers(biomeGenerationSettings);
        VariantBiomeFeatures.addAzureBluetLeavesPile(biomeGenerationSettings);
        DefaultBiomeFeatures.addDefaultFlowers(biomeGenerationSettings);
        DefaultBiomeFeatures.addDefaultGrass(biomeGenerationSettings);

        DefaultBiomeFeatures.addDefaultMushrooms(biomeGenerationSettings);
        DefaultBiomeFeatures.addDefaultExtraVegetation(biomeGenerationSettings);
        DefaultBiomeFeatures.addDefaultSprings(biomeGenerationSettings);
        return new Biome.Builder().precipitation(Biome.Precipitation.RAIN).biomeCategory(Biome.Category.PLAINS).depth(0.1F).scale(0.2F).temperature(0.7F).downfall(0.8F).specialEffects((new
                BiomeAmbience.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(12638463).skyColor(calculateSkyColor(0.7F))
                .ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(mobSpawnInfo.build()).generationSettings(biomeGenerationSettings
                .build()).build();
    }

    public static Biome createEnderForest() {
        MobSpawnInfo.Builder mobSpawnInfo = new MobSpawnInfo.Builder().setPlayerCanSpawn();
        BiomeGenerationSettings.Builder biomeGenerationSettings = new BiomeGenerationSettings.Builder().surfaceBuilder(() -> VariantFeatures.ender_forest_builder)
                .addStructureStart(StructureFeatures.END_CITY).addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Features.END_GATEWAY).addFeature(GenerationStage.
                        Decoration.VEGETAL_DECORATION, Features.CHORUS_PLANT).addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, VariantFeatures.ender_fungi);

        biomeGenerationSettings.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, VariantFeatures.ender_forest_vegetation);
        biomeGenerationSettings.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, VariantFeatures.ender_fungi_planted);
        DefaultBiomeFeatures.endSpawns(mobSpawnInfo);
        return new Biome.Builder().precipitation(Biome.RainType.NONE).biomeCategory(Biome.Category.THEEND).depth(0.1F).scale(0.2F).temperature(0.5F).downfall(0.5F)
                .specialEffects(new BiomeAmbience.Builder().waterColor(4159204).waterFogColor(329011).fogColor(10518688).skyColor(0)
                        .ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(mobSpawnInfo.build()).generationSettings(biomeGenerationSettings
                        .build()).build();
    }*/
}
