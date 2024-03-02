package com.junethewoods.variants.block.custom;

import net.minecraft.block.*;
import net.minecraft.potion.Effect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class SunnyFlowerBlock extends FlowerBlock implements IGrowable {
    public SunnyFlowerBlock(Effect effect, int duration, Properties properties) {
        super(effect, duration, properties);
    }

    @Override
    public boolean isValidBonemealTarget(IBlockReader world, BlockPos pos, BlockState state, boolean bool) {
        return true;
    }

    @Override
    public boolean isBonemealSuccess(World world, Random rand, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerWorld world, Random rand, BlockPos pos, BlockState state) {
        DoublePlantBlock sunflower = (DoublePlantBlock) Blocks.SUNFLOWER;
        if (sunflower.defaultBlockState().canSurvive(world, pos) && world.isEmptyBlock(pos.above())) sunflower.placeAt(world, pos, 2);
    }
}
