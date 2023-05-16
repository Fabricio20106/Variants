package com.junethewoods.variants.common.block.plant;

import com.junethewoods.variants.core.init.VSItems;
import net.minecraft.world.level.ItemLike;

public class WildCrimsonWheatBlock extends WildCropBlock {
    public WildCrimsonWheatBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return VSItems.CRIMSON_WHEAT_SEEDS.get();
    }
}
