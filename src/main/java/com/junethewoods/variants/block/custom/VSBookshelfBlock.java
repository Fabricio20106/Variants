package com.junethewoods.variants.block.custom;

import com.junethewoods.variants.util.VSTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class VSBookshelfBlock extends Block {
    private final int enchantingPower;

    public VSBookshelfBlock(int enchantingPower, Properties properties) {
        super(properties);
        this.enchantingPower = enchantingPower;
    }

    public float getEnchantPowerBonus(BlockState state, LevelReader world, BlockPos pos) {
        return state.is(VSTags.Blocks.BOOKSHELVES) ? this.enchantingPower : 0;
    }
}
