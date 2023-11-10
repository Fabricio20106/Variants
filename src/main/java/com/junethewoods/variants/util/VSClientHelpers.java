package com.junethewoods.variants.util;

import com.google.common.collect.Maps;
import com.junethewoods.variants.block.VSBlocks;
import com.junethewoods.variants.item.custom.armor.WoolArmorItem;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

import static net.minecraft.item.ItemModelsProperties.register;

public class VSClientHelpers {
    public static void setRenderTypesForBlocks() {
        RenderTypeLookup.setRenderLayer(VSBlocks.POTTED_GLOW_BLACK_TULIP.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.POTTED_SUGAR_CANE.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.POTTED_PAINTING_SAPLING.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.GOLDEN_CARROTS.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.GLOW_BLACK_TULIP.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.GLOW_BERRY_BUSH.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.PAINTING_SAPLING.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.WARPED_WART.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.GOLDEN_BEACON.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.GOLDEN_CAULDRON.get(), RenderType.translucent());
        RenderTypeLookup.setRenderLayer(VSBlocks.PAINTING_TRAPDOOR.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.PAINTING_DOOR.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.WANDERER_DOOR.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.GRAHAM_DOOR.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.FIRST_DOOR.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.QUARTZ_GLASS.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VSBlocks.QUARTZ_GLASS_PANE.get(), RenderType.cutout());

        RenderTypeLookup.setRenderLayer(VSBlocks.GLOW_BLACK_STAINED_GLASS.get(), RenderType.translucent());
        RenderTypeLookup.setRenderLayer(VSBlocks.GLOW_BLACK_STAINED_GLASS_PANE.get(), RenderType.translucent());
    }

    // Can be used to add new wool armor (currently only sweater) colors.
    public static void woolArmorColor(String colorName, int colorCode) {
        WoolArmorItem.COLOR_NAME_TO_CODE = Maps.newHashMap(WoolArmorItem.COLOR_NAME_TO_CODE);
        WoolArmorItem.COLOR_NAME_TO_CODE.put(colorName, colorCode);
    }

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
