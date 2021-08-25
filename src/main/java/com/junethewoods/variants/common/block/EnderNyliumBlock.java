package com.junethewoods.variants.common.block;

import com.junethewoods.variants.core.gen.VariantFeatures;
import com.junethewoods.variants.core.init.BlockInit;
import net.minecraft.block.*;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.NetherVegetationFeature;
import net.minecraft.world.lighting.LightEngine;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class EnderNyliumBlock extends Block implements IGrowable {
    public EnderNyliumBlock(AbstractBlock.Properties properties) {
        super(properties);
    }

    private static boolean isDarkEnough(BlockState state, IWorldReader reader, BlockPos pos) {
        BlockPos blockpos = pos.up();
        BlockState blockstate = reader.getBlockState(blockpos);
        int i = LightEngine.func_215613_a(reader, state, pos, blockstate, blockpos, Direction.UP, blockstate.getOpacity(reader, blockpos));
        return i < reader.getMaxLightLevel();
    }

    /**
     * Performs a random tick on a block.
     */
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        if (!isDarkEnough(state, worldIn, pos)) {
            worldIn.setBlockState(pos, Blocks.END_STONE.getDefaultState());
        }

    }

    /**
     * Whether this IGrowable can grow
     */
    public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
        return worldIn.getBlockState(pos.up()).isAir();
    }

    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
        return true;
    }

    public void grow(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
        BlockState blockstate = worldIn.getBlockState(pos);
        BlockPos blockpos = pos.up();
        if (blockstate.isIn(BlockInit.ender_nylium.get())) {
            NetherVegetationFeature.func_236325_a_(worldIn, rand, blockpos, VariantFeatures.ender_forest_vegetation_config, 3, 1);
            NetherVegetationFeature.func_236325_a_(worldIn, rand, blockpos, VariantFeatures.ender_sprouts_config, 3, 1);
        }
    }
}

