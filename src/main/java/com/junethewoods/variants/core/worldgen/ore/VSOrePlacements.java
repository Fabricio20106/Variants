package com.junethewoods.variants.core.worldgen.ore;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

public class VSOrePlacements {
    public static final Holder<PlacedFeature> QUARTZ_ORE_VEIN = PlacementUtils.register("variants:quartz_ore_vein",
            VSOreFeatures.QUARTZ_ORE_VEIN, commonOrePlacement(16, HeightRangePlacement.triangle(VerticalAnchor.absolute(0),
                    VerticalAnchor.absolute(50))));

    public static final Holder<PlacedFeature> END_QUARTZ_ORE_VEIN = PlacementUtils.register("variants:end_quartz_ore_vein",
            VSOreFeatures.END_QUARTZ_ORE_VEIN, commonOrePlacement(16, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(10),
                    VerticalAnchor.belowTop(10))));

    public static final Holder<PlacedFeature> NETHER_COAL_ORE_VEIN = PlacementUtils.register("variants:nether_coal_ore_vein",
            VSOreFeatures.NETHER_COAL_ORE_VEIN, commonOrePlacement(30, HeightRangePlacement.triangle(VerticalAnchor.absolute(0),
                    VerticalAnchor.absolute(192))));

    private static List<PlacementModifier> orePlacement(PlacementModifier modifier, PlacementModifier modifier1) {
        return List.of(modifier, InSquarePlacement.spread(), modifier1, BiomeFilter.biome());
    }

    private static List<PlacementModifier> commonOrePlacement(int count, PlacementModifier modifier) {
        return orePlacement(CountPlacement.of(count), modifier);
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onBiomeLoad(final BiomeLoadingEvent event) {
        BiomeGenerationSettings.Builder settings = event.getGeneration();
        if (!(event.getCategory() == Biome.BiomeCategory.NETHER || event.getCategory() == Biome.BiomeCategory.THEEND)) {
            settings.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, QUARTZ_ORE_VEIN);
        }
        if (event.getCategory() == Biome.BiomeCategory.THEEND) {
            settings.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, END_QUARTZ_ORE_VEIN);
        }
        if (event.getCategory() == Biome.BiomeCategory.NETHER) {
            settings.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, NETHER_COAL_ORE_VEIN);
        }
    }
}
