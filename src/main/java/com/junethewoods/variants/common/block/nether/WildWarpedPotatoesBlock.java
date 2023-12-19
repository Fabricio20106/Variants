package com.junethewoods.variants.common.block.nether;

import com.junethewoods.variants.common.block.WildNetherCropBlock;
import com.junethewoods.variants.core.init.VSItems;
import net.minecraft.world.level.ItemLike;

public class WildWarpedPotatoesBlock extends WildNetherCropBlock {
    public WildWarpedPotatoesBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return VSItems.WARPED_POTATO.get();
    }
}
