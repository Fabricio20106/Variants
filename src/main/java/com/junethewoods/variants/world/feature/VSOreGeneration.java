package com.junethewoods.variants.world.feature;

import com.junethewoods.variants.block.VSBlocks;
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

public class VSOreGeneration {
    public static final RuleTest BASE_STONE_END = new BlockMatchRuleTest(Blocks.END_STONE);

    // Overworld Ores
    public static void generateQuartzOre(final BiomeLoadingEvent event) {
        if (!(event.getCategory().equals(Biome.Category.NETHER) || event.getCategory().equals(Biome.Category.THEEND))) {
            generateOre(event.getGeneration(), OreFeatureConfig.FillerBlockType.NATURAL_STONE, VSBlocks.QUARTZ_ORE.get().defaultBlockState(), 7, 10, 108, 6);
        }
    }

    // End Ores
    public static void generateEndQuartzOre(final BiomeLoadingEvent event) {
        if (event.getCategory().equals(Biome.Category.THEEND)) {
            generateOre(event.getGeneration(), BASE_STONE_END, VSBlocks.END_QUARTZ_ORE.get().defaultBlockState(), 14, 10, 108, 12);
        }
    }

    public static void generateOre(BiomeGenerationSettingsBuilder settings, RuleTest fillerType, BlockState state, int veinSize, int minHeight, int maxHeight, int count) {
        settings.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.configured(new OreFeatureConfig(fillerType,
                state, veinSize)).decorated(Placement.RANGE.configured(new TopSolidRangeConfig(minHeight, 0, maxHeight))).squared().count(count));
    }
}
