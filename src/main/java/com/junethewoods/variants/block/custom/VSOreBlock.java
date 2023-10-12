package com.junethewoods.variants.block.custom;

import com.junethewoods.variants.block.VSBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.OreBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class VSOreBlock extends OreBlock {
    public VSOreBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected int xpOnDrop(Random rand) {
        if (this == VSBlocks.QUARTZ_ORE.get()) {
            return MathHelper.nextInt(rand, 2, 5);
        } else {
            return this == VSBlocks.END_QUARTZ_ORE.get() ? MathHelper.nextInt(rand, 2, 5) : 0;
        }
    }

    public void spawnAfterBreak(BlockState state, ServerWorld world, BlockPos pos, ItemStack stack) {
        super.spawnAfterBreak(state, world, pos, stack);
    }

    @Override
    public int getExpDrop(BlockState state, IWorldReader reader, BlockPos pos, int fortune, int silkTouch) {
        return silkTouch == 0 ? this.xpOnDrop(RANDOM) : 0;
    }
}
