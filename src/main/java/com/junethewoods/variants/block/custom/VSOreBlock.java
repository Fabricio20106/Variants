package com.junethewoods.variants.block.custom;

import com.junethewoods.variants.common.register.BlockInit;
import net.minecraft.block.BlockState;
import net.minecraft.block.OreBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class VSOreBlock extends OreBlock {
    public VSOreBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected int xpOnDrop(Random rand) {
        if (this == BlockInit.quartz_ore.get()) {
            return MathHelper.nextInt(rand, 2, 5);
        } else {
            return this == BlockInit.end_quartz_ore.get() ? MathHelper.nextInt(rand, 2, 5) : 0;
        }
    }

    public void spawnAfterBreak(BlockState state, ServerWorld worldIn, BlockPos pos, ItemStack stack) {
        super.spawnAfterBreak(state, worldIn, pos, stack);
    }

    @Override
    public int getExpDrop(BlockState state, net.minecraft.world.IWorldReader reader, BlockPos pos, int fortune, int silktouch) {
        return silktouch == 0 ? this.xpOnDrop(RANDOM) : 0;
    }
}
