package com.junethewoods.variants.block.custom.nether;

import com.junethewoods.variants.block.VSBlocks;
import com.junethewoods.variants.util.VSTags;
import net.minecraft.block.*;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class EndStoneBlock extends Block implements IGrowable {
    public EndStoneBlock(Properties properties) {
        super(properties);
    }

    public boolean isValidBonemealTarget(IBlockReader world, BlockPos pos, BlockState state, boolean bool) {
        if (world.getBlockState(pos.above()).propagatesSkylightDown(world, pos)) {
            for (BlockPos positions : BlockPos.betweenClosed(pos.offset(-1, -1, -1), pos.offset(1, 1, 1))) {
                if (world.getBlockState(positions).is(BlockTags.NYLIUM)) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isBonemealSuccess(World world, Random rand, BlockPos pos, BlockState state) {
        return true;
    }

    public void performBonemeal(ServerWorld world, Random rand, BlockPos pos, BlockState state) {
        boolean hasEnderNylium = false;

        for(BlockPos cube : BlockPos.betweenClosed(pos.offset(-1, -1, -1), pos.offset(1, 1, 1))) {
            BlockState cubeState = world.getBlockState(cube);
            if (cubeState.is(VSTags.Blocks.HAS_ENDER_NYLIUM)) {
                hasEnderNylium = true;
            }
        }

        if (state.is(Blocks.END_STONE) && hasEnderNylium) {
            world.setBlock(pos, VSBlocks.ENDER_NYLIUM.get().defaultBlockState(), 3);
        } else if (state.is(VSBlocks.END_QUARTZ_ORE.get()) && hasEnderNylium) {
            world.setBlock(pos, VSBlocks.ENDER_NYLIUM_QUARTZ_ORE.get().defaultBlockState(), 3);
        }
    }
}
