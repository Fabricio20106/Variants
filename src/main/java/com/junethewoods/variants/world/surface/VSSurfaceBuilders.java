package com.junethewoods.variants.world.surface;

import com.junethewoods.variants.Variants;
import com.junethewoods.variants.block.VSBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class VSSurfaceBuilders {
    public static final BlockState END_STONE = Blocks.END_STONE.defaultBlockState();
    public static final BlockState ENDER_NYLIUM = VSBlocks.ENDER_NYLIUM.get().defaultBlockState();
    public static final BlockState ENDER_WART_BLOCK = VSBlocks.ENDER_WART_BLOCK.get().defaultBlockState();

    public static final SurfaceBuilderConfig ENDERWOOD_FOREST_NYLIUM_CONFIG = new SurfaceBuilderConfig(ENDER_NYLIUM, END_STONE, ENDER_WART_BLOCK);

    public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> ENDERWOOD_FOREST = register("enderwood_forest_old", SurfaceBuilder.DEFAULT.configured(ENDERWOOD_FOREST_NYLIUM_CONFIG));

    private static <SC extends ISurfaceBuilderConfig> ConfiguredSurfaceBuilder<SC> register(String name, ConfiguredSurfaceBuilder<SC> csb) {
        return WorldGenRegistries.register(WorldGenRegistries.CONFIGURED_SURFACE_BUILDER, Variants.resourceLoc(name), csb);
    }

    public static void init() {}
}
