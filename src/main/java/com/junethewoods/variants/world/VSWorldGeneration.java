package com.junethewoods.variants.world;

import com.junethewoods.variants.Variants;
import com.junethewoods.variants.config.VSConfigs;
import com.junethewoods.variants.world.feature.VSFeatures;
import com.junethewoods.variants.world.feature.VSOreGeneration;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Variants.MOD_ID)
public class VSWorldGeneration {
    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onBiomeLoading(final BiomeLoadingEvent event) {
        BiomeGenerationSettingsBuilder settings = event.getGeneration();

        if (event.getCategory() == Biome.Category.PLAINS || event.getCategory() == Biome.Category.FOREST && VSConfigs.COMMON_CONFIGS.generateFlowerPatches.get()) {
            settings.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, VSFeatures.VARIANTS_FLOWER_PATCH);
        }

        if (VSConfigs.COMMON_CONFIGS.generateQuartzOre.get()) VSOreGeneration.generateQuartzOre(event);
        if (VSConfigs.COMMON_CONFIGS.generateEndQuartzOre.get()) VSOreGeneration.generateEndQuartzOre(event);
    }
}
