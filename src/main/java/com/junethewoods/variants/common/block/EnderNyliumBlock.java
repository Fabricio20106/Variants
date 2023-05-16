package com.junethewoods.variants.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.data.worldgen.features.NetherFeatures;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.lighting.LayerLightEngine;

import java.util.Random;

public class EnderNyliumBlock extends Block implements BonemealableBlock {
    public EnderNyliumBlock(Properties properties) {
        super(properties);
    }

    private static boolean canBeNylium(BlockState state, LevelReader reader, BlockPos pos) {
        BlockPos abovePos = pos.above();
        BlockState state1 = reader.getBlockState(abovePos);
        int i = LayerLightEngine.getLightBlockInto(reader, state, pos, state1, abovePos, Direction.UP, state1.getLightBlock(reader, abovePos));
        return i < reader.getMaxLightLevel();
    }

    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, Random rand) {
        if (!canBeNylium(state, world, pos)) {
            world.setBlockAndUpdate(pos, Blocks.END_STONE.defaultBlockState());
        }
    }

    public boolean isValidBonemealTarget(BlockGetter getter, BlockPos pos, BlockState state, boolean p_176473_4_) {
        return getter.getBlockState(pos.above()).isAir();
    }

    public boolean isBonemealSuccess(Level world, Random rand, BlockPos pos, BlockState state) {
        return true;
    }

    public void performBonemeal(ServerLevel world, Random rand, BlockPos pos, BlockState state) {
        BlockState state1 = world.getBlockState(pos);
        BlockPos abovePos = pos.above();
        ChunkGenerator chunkGenerator = world.getChunkSource().getGenerator();
        if (state1.is(Blocks.CRIMSON_NYLIUM)) {
            NetherFeatures.CRIMSON_FOREST_VEGETATION_BONEMEAL.value().place(world, chunkGenerator, rand, abovePos);
        } else if (state1.is(Blocks.WARPED_NYLIUM)) {
            NetherFeatures.WARPED_FOREST_VEGETATION_BONEMEAL.value().place(world, chunkGenerator, rand, abovePos);
            NetherFeatures.NETHER_SPROUTS_BONEMEAL.value().place(world, chunkGenerator, rand, abovePos);
            if (rand.nextInt(8) == 0) {
                NetherFeatures.TWISTING_VINES_BONEMEAL.value().place(world, chunkGenerator, rand, abovePos);
            }
        }
    }
}

