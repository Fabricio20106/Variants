package com.junethewoods.variants.block.custom;

import com.junethewoods.variants.util.VSTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;

public class VSBookshelfBlock extends Block {
    private final int enchantingPower;

    public VSBookshelfBlock(int enchantingPower, Properties properties) {
        super(properties);
        this.enchantingPower = enchantingPower;
    }

    public float getEnchantPowerBonus(BlockState state, IWorldReader world, BlockPos pos) {
        return state.is(VSTags.Blocks.BOOKSHELVES) ? this.enchantingPower : 0;
    }
}
