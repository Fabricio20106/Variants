package com.junethewoods.variants.common.block.plant;

import com.junethewoods.variants.core.init.VSItems;
import net.minecraft.world.level.ItemLike;

public class WildSoulCarrotsBlock extends WildCropBlock {
    public WildSoulCarrotsBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return VSItems.SOUL_CARROT.get();
    }
}
