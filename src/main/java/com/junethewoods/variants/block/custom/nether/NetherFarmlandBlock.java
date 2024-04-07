package com.junethewoods.variants.block.custom.nether;

import com.junethewoods.variants.block.custom.AbstractFarmlandBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluid;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ITag;

public class NetherFarmlandBlock extends AbstractFarmlandBlock {
    public NetherFarmlandBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockState getDirtLikeBlock() {
        return Blocks.NETHERRACK.defaultBlockState();
    }

    @Override
    public ITag<Fluid> getHydrationFluid() {
        return FluidTags.LAVA;
    }
}
