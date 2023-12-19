package com.junethewoods.variants.common.block.nether;

import com.junethewoods.variants.common.block.WildNetherCropBlock;
import com.junethewoods.variants.core.init.VSItems;
import net.minecraft.world.level.ItemLike;

public class WildCrimsonWheatBlock extends WildNetherCropBlock {
    public WildCrimsonWheatBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return VSItems.CRIMSON_WHEAT_SEEDS.get();
    }
}
