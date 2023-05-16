package com.junethewoods.variants.common.block.plant;

import com.junethewoods.variants.core.init.VSItems;
import net.minecraft.world.level.ItemLike;

public class WildWarpedPotatoesBlock extends WildCropBlock {
    public WildWarpedPotatoesBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return VSItems.WARPED_POTATO.get();
    }
}
