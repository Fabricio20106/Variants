package com.junethewoods.variants.world.feature.custom;

import com.junethewoods.variants.block.VSBlocks;
import com.junethewoods.variants.util.VSTags;
import com.mojang.serialization.Codec;
import net.minecraft.block.AbstractTopPlantBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

public class WarpingVineFeature extends Feature<NoFeatureConfig> {
    private static final Direction[] DIRECTIONS = Direction.values();

    public WarpingVineFeature(Codec<NoFeatureConfig> codec) {
        super(codec);
    }

    public boolean place(ISeedReader seedReader, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
        if (!seedReader.isEmptyBlock(pos)) {
            return false;
        } else {
            BlockState aboveState = seedReader.getBlockState(pos.above());
            if (!aboveState.is(VSTags.Blocks.WARPING_VINES_CAN_PLACE_ON)) {
                return false;
            } else {
                this.placeRoofEnderWartBlock(seedReader, rand, pos);
                this.placeRoofWarpingVines(seedReader, rand, pos);
                return true;
            }
        }
    }

    private void placeRoofEnderWartBlock(IWorld world, Random rand, BlockPos pos) {
        world.setBlock(pos, VSBlocks.ENDER_WART_BLOCK.get().defaultBlockState(), 2);
        BlockPos.Mutable mutablePos = new BlockPos.Mutable();
        BlockPos.Mutable mutablePos1 = new BlockPos.Mutable();

        for(int i = 0; i < 200; ++i) {
            mutablePos.setWithOffset(pos, rand.nextInt(6) - rand.nextInt(6), rand.nextInt(2) - rand.nextInt(5), rand.nextInt(6) - rand.nextInt(6));
            if (world.isEmptyBlock(mutablePos)) {
                int i2 = 0;
                Direction[] directions = DIRECTIONS;
                int directionsLength = directions.length;

                for(int i1 = 0; i1 < directionsLength; ++i1) {
                    Direction direction = directions[i1];
                    BlockState state = world.getBlockState(mutablePos1.setWithOffset(mutablePos, direction));
                    if (state.is(VSTags.Blocks.WARPING_VINES_CAN_PLACE_ON)) {
                        ++i2;
                    }

                    if (i2 > 1) {
                        break;
                    }
                }

                if (i2 == 1) {
                    world.setBlock(mutablePos, VSBlocks.ENDER_WART_BLOCK.get().defaultBlockState(), 2);
                }
            }
        }
    }

    private void placeRoofWarpingVines(IWorld world, Random rand, BlockPos pos) {
        BlockPos.Mutable mutablePos = new BlockPos.Mutable();

        for(int i = 0; i < 100; ++i) {
            mutablePos.setWithOffset(pos, rand.nextInt(8) - rand.nextInt(8), rand.nextInt(2) - rand.nextInt(7), rand.nextInt(8) - rand.nextInt(8));
            if (world.isEmptyBlock(mutablePos)) {
                BlockState aboveState = world.getBlockState(mutablePos.above());
                if (aboveState.is(VSTags.Blocks.WARPING_VINES_CAN_PLACE_ON)) {
                    int rand1 = MathHelper.nextInt(rand, 1, 8);
                    if (rand.nextInt(6) == 0) {
                        rand1 *= 2;
                    }

                    if (rand.nextInt(5) == 0) {
                        rand1 = 1;
                    }

                    placeWarpingVinesColumn(world, rand, mutablePos, rand1, 17, 25);
                }
            }
        }
    }

    public static void placeWarpingVinesColumn(IWorld world, Random rand, BlockPos.Mutable mutablePos, int range, int min, int max) {
        for(int i = 0; i <= range; ++i) {
            if (world.isEmptyBlock(mutablePos)) {
                if (i == range || !world.isEmptyBlock(mutablePos.below())) {
                    world.setBlock(mutablePos, VSBlocks.WARPING_VINES.get().defaultBlockState().setValue(AbstractTopPlantBlock.AGE, MathHelper.nextInt(rand, min, max)), 2);
                    break;
                }

                world.setBlock(mutablePos, VSBlocks.WARPING_VINES_PLANT.get().defaultBlockState(), 2);
            }

            mutablePos.move(Direction.DOWN);
        }
    }
}
