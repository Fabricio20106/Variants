package com.junethewoods.variants.block.custom.end;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.NyliumBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.lighting.LightEngine;

public class EnderNyliumBlock extends NyliumBlock {
    public EnderNyliumBlock(Properties properties) {
        super(properties);
    }

    private static boolean canBeNylium(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos abovePos = pos.above();
        BlockState aboveState = level.getBlockState(abovePos);
        int lightAtBlock = LightEngine.getLightBlockInto(level, state, pos, aboveState, abovePos, Direction.UP, aboveState.getLightBlock(level, abovePos));
        return lightAtBlock < level.getMaxLightLevel();
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand) {
        if (!canBeNylium(state, level, pos)) {
            level.setBlockAndUpdate(pos, Blocks.END_STONE.defaultBlockState());
        }
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource rand, BlockPos pos, BlockState state) {
        /*BlockState posState = level.getBlockState(pos);
        BlockPos abovePos = pos.above();
         if (posState.is(VSBlocks.ENDER_NYLIUM.get())) {
            NetherVegetationFeature.place(level, rand, abovePos, VSFeatures.ENDERWOOD_FOREST_CONFIG, 3, 1);
            NetherVegetationFeature.place(level, rand, abovePos, VSFeatures.END_SPROUTS_CONFIG, 3, 1);
        }*/
    }
}
