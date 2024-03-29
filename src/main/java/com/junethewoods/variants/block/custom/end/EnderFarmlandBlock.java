package com.junethewoods.variants.block.custom.end;

import com.junethewoods.variants.block.custom.AbstractFarmlandBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;

public class EnderFarmlandBlock extends AbstractFarmlandBlock {
    public EnderFarmlandBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockState getDirtLikeBlock() {
        return Blocks.END_STONE.defaultBlockState();
    }
}
