package com.junethewoods.variants.util;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

import static net.minecraft.item.ItemModelsProperties.register;

public class VSItemModelProperties {
    public static void makeBow(Item bow) {
        register(bow, new ResourceLocation("pull"), (stack, world, livingEntity) -> {
            if (livingEntity == null) {
                return 0;
            } else {
                return livingEntity.getUseItem() != stack ? 0 : (float) (stack.getUseDuration() - livingEntity.getUseItemRemainingTicks()) / 20;
            }
        });
        register(bow, new ResourceLocation("pulling"), (stack, world, livingEntity) -> livingEntity != null && livingEntity.isUsingItem() && livingEntity.getUseItem() == stack ? 1 : 0);
    }
}
