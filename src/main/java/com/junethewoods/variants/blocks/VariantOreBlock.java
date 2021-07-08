package com.junethewoods.variants.blocks;

import com.junethewoods.variants.common.register.BlockInit;
import net.minecraft.block.BlockState;
import net.minecraft.block.OreBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class VariantOreBlock extends OreBlock {
    public VariantOreBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected int getExperience(Random rand) {
        if (this == BlockInit.quartz_ore.get()) {
            return MathHelper.nextInt(rand, 2, 5);
        } else {
            return this == BlockInit.end_quartz_ore.get() ? MathHelper.nextInt(rand, 2, 5) : 0;
        }
    }

    public void spawnAdditionalDrops(BlockState state, ServerWorld worldIn, BlockPos pos, ItemStack stack) {
        super.spawnAdditionalDrops(state, worldIn, pos, stack);
    }

    @Override
    public int getExpDrop(BlockState state, net.minecraft.world.IWorldReader reader, BlockPos pos, int fortune, int silktouch) {
        return silktouch == 0 ? this.getExperience(RANDOM) : 0;
    }
}
