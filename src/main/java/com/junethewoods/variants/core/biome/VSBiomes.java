package com.junethewoods.variants.core.biome;

import com.junethewoods.variants.core.Variants;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.Musics;
import net.minecraft.util.Mth;
import net.minecraft.world.level.biome.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;

public class VSBiomes {
    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, Variants.MOD_ID);

    public static final RegistryObject<Biome> PAINTING_WOODED_FOREST = BIOMES.register("painting_wooded_forest", VSBiomes::paintingWoodedForest);
    // public static final RegistryObject<Biome> AZURE_FIELDS = BIOMES.register("azure_fields", VSBiomes::azureFields);
    // public static final RegistryObject<Biome> ENDERWOOD_FOREST = BIOMES.register("ender_forest", VSBiomes::enderwoodForest);

    // Helper methods from the "OverworldBiomes" class.
    private static void globalOverworldGeneration(BiomeGenerationSettings.Builder settings) {
        BiomeDefaultFeatures.addDefaultCarversAndLakes(settings);
        BiomeDefaultFeatures.addDefaultCrystalFormations(settings);
        BiomeDefaultFeatures.addDefaultMonsterRoom(settings);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(settings);
        BiomeDefaultFeatures.addDefaultSprings(settings);
        BiomeDefaultFeatures.addSurfaceFreezing(settings);
    }

    protected static int calculateSkyColor(float skyColor) {
        float scDivThree = skyColor / 3;
        scDivThree = Mth.clamp(scDivThree, -1, 1);
        return Mth.hsvToRgb(0.62222224f - scDivThree * 0.05f, 0.5f + scDivThree * 0.1f, 1);
    }

    private static Biome biome(Biome.Precipitation precipitation, Biome.BiomeCategory category, float skyColor, float downfall, int waterColor, int waterFogColor, MobSpawnSettings.Builder spawns, BiomeGenerationSettings.Builder settings, @Nullable Music music) {
        return new Biome.BiomeBuilder().precipitation(precipitation).biomeCategory(category).temperature(skyColor).downfall(downfall).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(waterColor).waterFogColor(waterFogColor).fogColor(12638463).skyColor(calculateSkyColor(skyColor)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).backgroundMusic(music).build()).mobSpawnSettings(spawns.build()).generationSettings(settings.build()).build();
    }

    // Biomes
    public static Biome paintingWoodedForest() {
        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder settings = new BiomeGenerationSettings.Builder();

        BiomeDefaultFeatures.farmAnimals(spawns);
        BiomeDefaultFeatures.commonSpawns(spawns);

        globalOverworldGeneration(settings);
        VSBiomeDefaultFeatures.addPaintingTrees(settings);
        BiomeDefaultFeatures.addDefaultOres(settings);
        BiomeDefaultFeatures.addDefaultSoftDisks(settings);
        BiomeDefaultFeatures.addForestFlowers(settings);
        BiomeDefaultFeatures.addForestGrass(settings);
        BiomeDefaultFeatures.addDefaultExtraVegetation(settings);
        return biome(Biome.Precipitation.RAIN, Biome.BiomeCategory.FOREST, 0.7f, 0.8f, 4159204, 329011, spawns, settings, Musics.GAME);
    }

    /*public static Biome createPaintingWoodedForest() {
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
