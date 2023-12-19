package com.junethewoods.variants.core.world.ore;

import com.junethewoods.variants.core.init.VSBlocks;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;

import java.util.List;

import static net.minecraft.data.worldgen.features.FeatureUtils.register;
import static net.minecraft.data.worldgen.features.OreFeatures.NETHER_ORE_REPLACEABLES;
import static net.minecraft.data.worldgen.features.OreFeatures.STONE_ORE_REPLACEABLES;

public class VSOreFeatures {
    public static final RuleTest END_ORE_REPLACEABLES = new BlockMatchTest(Blocks.END_STONE);

    // When the game tries to replace a block that's in STONE_ORE_REPLACEABLES, what block should it put there.
    // A.K.A Target List
    public static final List<OreConfiguration.TargetBlockState> QUARTZ_ORE_TARGET_LIST = List.of(
            OreConfiguration.target(STONE_ORE_REPLACEABLES, VSBlocks.QUARTZ_ORE.get().defaultBlockState()));

    public static final List<OreConfiguration.TargetBlockState> END_QUARTZ_ORE_TARGET_LIST = List.of(
            OreConfiguration.target(END_ORE_REPLACEABLES, VSBlocks.END_QUARTZ_ORE.get().defaultBlockState()));

    public static final List<OreConfiguration.TargetBlockState> NETHER_COAL_ORE_TARGET_LIST = List.of(
            OreConfiguration.target(NETHER_ORE_REPLACEABLES, VSBlocks.NETHER_COAL_ORE.get().defaultBlockState()));

    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> QUARTZ_ORE_VEIN = register("variants:quartz_ore_vein",
            Feature.ORE, new OreConfiguration(QUARTZ_ORE_TARGET_LIST, 6));

    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> END_QUARTZ_ORE_VEIN = register("variants:end_quartz_ore_vein",
            Feature.ORE, new OreConfiguration(END_QUARTZ_ORE_TARGET_LIST, 14));

    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> NETHER_COAL_ORE_VEIN = register("variants:nether_coal_ore_vein",
            Feature.ORE, new OreConfiguration(NETHER_COAL_ORE_TARGET_LIST, 17));
}
