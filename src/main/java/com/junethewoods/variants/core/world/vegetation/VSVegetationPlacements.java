package com.junethewoods.variants.core.world.vegetation;

import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.util.valueproviders.ClampedInt;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Objects;

import static net.minecraft.data.worldgen.placement.VegetationPlacements.treePlacement;

public class VSVegetationPlacements {
    public static final Holder<PlacedFeature> PAINTING_TREES = PlacementUtils.register("variants:painting_trees", VSVegetationFeatures.PAINTING_TREES,
            treePlacement(PlacementUtils.countExtra(10, 0.1f, 1)));
    public static final Holder<PlacedFeature> PAINTING_TREES_FOREST = PlacementUtils.register("variants:painting_trees_forest", VSVegetationFeatures.PAINTING_TREES,
            treePlacement(PlacementUtils.countExtra(3, 0.1f, 1)));

    public static final Holder<PlacedFeature> VARIANTS_FLOWER_PATCH = PlacementUtils.register("variants:variants_flower_patch", VSVegetationFeatures.VARIANTS_FLOWER_PATCH,
            RarityFilter.onAverageOnceEvery(7), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, CountPlacement.of(ClampedInt.of(UniformInt.of(-3, 1),
                    0, 1)), BiomeFilter.biome());
    public static final Holder<PlacedFeature> GLOW_BLACK_TULIP_PATCH = PlacementUtils.register("variants:glow_black_tulip_patch", VSVegetationFeatures.GLOW_BLACK_TULIP_PATCH,
            RarityFilter.onAverageOnceEvery(7), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, CountPlacement.of(ClampedInt.of(UniformInt.of(-3, 1),
                    0, 1)), BiomeFilter.biome());
    public static final Holder<PlacedFeature> SUNNY_FLOWER_PATCH = PlacementUtils.register("variants:sunny_flower_patch", VSVegetationFeatures.SUNNY_FLOWER_PATCH,
            RarityFilter.onAverageOnceEvery(3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, CountPlacement.of(ClampedInt.of(UniformInt.of(-3, 1),
                    0, 1)), BiomeFilter.biome());

    public static final Holder<PlacedFeature> GLOW_BERRY_BUSH_PATCH = PlacementUtils.register("variants:glow_berry_bush_patch", VSVegetationFeatures.GLOW_BLACK_TULIP_PATCH,
            CountPlacement.of(45), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
            EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12),
            RandomOffsetPlacement.vertical(ConstantInt.of(1)), BiomeFilter.biome());

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onBiomeLoad(final BiomeLoadingEvent event) {
        BiomeGenerationSettings.Builder settings = event.getGeneration();
        if (!(event.getCategory() == Biome.BiomeCategory.NETHER || event.getCategory() == Biome.BiomeCategory.THEEND)) {
            settings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VARIANTS_FLOWER_PATCH);
        }
        if (event.getName() != null && Objects.equals(Biomes.PLAINS.getRegistryName(), event.getName())) {
            settings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PAINTING_TREES_FOREST);
        }
        // Currently generates in Lush Caves and Dripstone Caves.
        if (event.getCategory() == Biome.BiomeCategory.UNDERGROUND) {
            settings.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, GLOW_BERRY_BUSH_PATCH);
        }
    }
}
