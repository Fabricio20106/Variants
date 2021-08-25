package com.junethewoods.variants.core.gen;

import com.junethewoods.variants.core.init.BlockInit;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.template.BlockMatchRuleTest;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class VariantGeneration {
    public static final RuleTest end_stone_filler = new BlockMatchRuleTest(Blocks.END_STONE);

    // "overworld" ores
    public static void generateQuartzOre(final BiomeLoadingEvent event) {
        if (!(event.getCategory().equals(Biome.Category.NETHER) || event.getCategory().equals(Biome.Category.THEEND))) {
            generateOre(event.getGeneration(), OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, BlockInit.quartz_ore.get().getDefaultState(), 6, 10, 117, 4);
        }
    }
    // "the end" ores
    public static void generateEndQuartzOre(final BiomeLoadingEvent event) {
        if (event.getCategory().equals(Biome.Category.THEEND)) {
            generateOre(event.getGeneration(), end_stone_filler, BlockInit.end_quartz_ore.get().getDefaultState(), 6, 10, 117, 4);
        }
    }
    // world generation outside from ore generation
    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void generateSurfaceStuff(final BiomeLoadingEvent event) {
        BiomeGenerationSettingsBuilder settings = event.getGeneration();

        // Flowers
        if (event.getCategory() == Biome.Category.PLAINS) {
            settings.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, VariantFeatures.glow_black_roses);
        }
        if (event.getCategory() == Biome.Category.FOREST) {
            settings.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, VariantFeatures.painting_trees);
        }
        if (event.getCategory().equals(Biome.Category.EXTREME_HILLS)) {
            settings.withFeature(GenerationStage.Decoration.TOP_LAYER_MODIFICATION, VariantFeatures.ender_nylium_patch);
            settings.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, VariantFeatures.ender_forest_vegetation);
            settings.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, VariantFeatures.ender_fungi_planted);
        }
    }

    // base for ore generation
    public static void generateOre(BiomeGenerationSettingsBuilder settings, RuleTest fillerType, BlockState state, int veinSize, int minHeight, int maxHeight, int amount) {
        settings.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(fillerType, state, veinSize)
        ).withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(minHeight, 0, maxHeight))).square().func_242731_b(amount));
    }
    // to actually generate the stuff (and gets called from the main class)
    public static void actuallyGenerateStuff() {
        MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, VariantGeneration::generateQuartzOre);
        MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, VariantGeneration::generateEndQuartzOre);
        MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, VariantGeneration::generateSurfaceStuff);
    }
}
