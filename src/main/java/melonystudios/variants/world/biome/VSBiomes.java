package melonystudios.variants.world.biome;

import melonystudios.variants.Variants;
import melonystudios.variants.sound.VSSounds;
import melonystudios.variants.world.feature.VSConfiguredFeatures;
import melonystudios.variants.world.surface.VSSurfaceBuilders;
import net.minecraft.client.audio.BackgroundMusicTracks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.structure.StructureFeatures;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilders;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class VSBiomes {
    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, Variants.MOD_ID);

    public static final RegistryObject<Biome> PAINTINGWOOD_FOREST = BIOMES.register("paintingwood_forest", VSBiomes::paintingwoodForest);
    public static final RegistryObject<Biome> AZURE_FIELDS = BIOMES.register("azure_fields", VSBiomes::azureFields);
    public static final RegistryObject<Biome> ENDERWOOD_FOREST = BIOMES.register("enderwood_forest", VSBiomes::enderwoodForest);

    protected static int calculateSkyColor(float temperature) {
        float f = temperature / 3;
        f = MathHelper.clamp(f, -1, 1);
        return MathHelper.hsvToRgb(0.62222224F - f * 0.05F, 0.5F + f * 0.1F, 1);
    }

    private static Biome paintingwoodForest() {
        BiomeGenerationSettings.Builder settings = new BiomeGenerationSettings.Builder().surfaceBuilder(ConfiguredSurfaceBuilders.GRASS);
        MobSpawnInfo.Builder spawns = new MobSpawnInfo.Builder().setPlayerCanSpawn();

        // Structures
        settings.addStructureStart(StructureFeatures.VILLAGE_PLAINS);
        settings.addStructureStart(StructureFeatures.RUINED_PORTAL_STANDARD);

        // Mob Spawns
        DefaultBiomeFeatures.farmAnimals(spawns);
        DefaultBiomeFeatures.commonSpawns(spawns);

        // Vegetal Decoration
        VSDefaultBiomeFeatures.addDefaultOverworldFeatures(settings);
        VSDefaultBiomeFeatures.addPaintingTrees(settings);
        VSDefaultBiomeFeatures.addVariantsFlowers(settings);
        DefaultBiomeFeatures.addForestFlowers(settings);
        DefaultBiomeFeatures.addForestGrass(settings);

        return new Biome.Builder().precipitation(Biome.RainType.RAIN).biomeCategory(Biome.Category.FOREST).depth(0.45F).scale(0.2F).temperature(0.7F).downfall(0.8F).specialEffects(new BiomeAmbience.Builder()
                .waterColor(4159204).waterFogColor(329011).fogColor(12638463).skyColor(calculateSkyColor(0.7F)).backgroundMusic(BackgroundMusicTracks.createGameMusic(VSSounds.PAINTINGWOOD_FOREST_MUSIC.get()))
                .ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawns.build()).generationSettings(settings.build()).build();
    }

    private static Biome azureFields() {
        BiomeGenerationSettings.Builder settings = new BiomeGenerationSettings.Builder().surfaceBuilder(ConfiguredSurfaceBuilders.GRASS);
        MobSpawnInfo.Builder spawns = new MobSpawnInfo.Builder().setPlayerCanSpawn();

        // Mob Spawns
        spawns.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.SHEEP, 12, 4, 4));
        spawns.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.CHICKEN, 10, 4, 4));
        DefaultBiomeFeatures.commonSpawns(spawns);

        // Vegetal Decoration
        settings.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.PATCH_SUGAR_CANE);
        VSDefaultBiomeFeatures.addDefaultOverworldFeatures(settings);
        VSDefaultBiomeFeatures.addAzureFieldsFeatures(settings);
        VSDefaultBiomeFeatures.addTallPlants(settings);
        DefaultBiomeFeatures.addJungleGrass(settings);

        return new Biome.Builder().precipitation(Biome.RainType.RAIN).biomeCategory(Biome.Category.PLAINS).depth(0.4F).scale(0.1F).temperature(0.3F).downfall(0.9F).specialEffects(new BiomeAmbience.Builder()
                .waterColor(0x12ABA2).waterFogColor(0x108887).fogColor(12638463).skyColor(calculateSkyColor(0.3F)).backgroundMusic(BackgroundMusicTracks.createGameMusic(VSSounds.AZURE_FIELDS_MUSIC.get()))
                .ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawns.build()).generationSettings(settings.build()).build();
    }

    private static Biome enderwoodForest() {
        BiomeGenerationSettings.Builder settings = new BiomeGenerationSettings.Builder().surfaceBuilder(VSSurfaceBuilders.ENDERWOOD_FOREST);
        MobSpawnInfo.Builder spawns = new MobSpawnInfo.Builder();

        DefaultBiomeFeatures.endSpawns(spawns);
        settings.addStructureStart(StructureFeatures.END_CITY);
        settings.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Features.END_GATEWAY);
        settings.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Features.END_SPIKE);
        settings.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.CHORUS_PLANT);
        settings.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, VSConfiguredFeatures.ENDERWOOD_FUNGI);
        settings.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, VSConfiguredFeatures.ENDERWOOD_FOREST_VEGETATION);
        settings.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, VSConfiguredFeatures.END_SPROUTS);

        return new Biome.Builder().precipitation(Biome.RainType.NONE).biomeCategory(Biome.Category.THEEND).depth(0.1F).scale(0.2F).temperature(0.5F).downfall(0.5F).specialEffects(new BiomeAmbience.Builder()
                        .waterColor(0x62529E).waterFogColor(0x4F4280).fogColor(0xA080A0).skyColor(0).ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawns.build())
                .generationSettings(settings.build()).build();
    }
}
