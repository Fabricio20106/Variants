package com.junethewoods.variants.util;

import com.junethewoods.variants.Variants;
import com.junethewoods.variants.block.VSBlocks;
import com.junethewoods.variants.item.VSItems;
import com.junethewoods.variants.item.VSWeaponry;
import com.junethewoods.variants.item.custom.armor.IDyeableWoolArmorItem;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.world.level.GrassColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

@Mod.EventBusSubscriber(modid = Variants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class VSColorManager {
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerBlockColorHandlers(final RegisterColorHandlersEvent.Block event) {
        event.getBlockColors().register((x, reader, pos, u) -> reader != null && pos != null ? BiomeColors.getAverageWaterColor(reader, pos) : -1,
                VSBlocks.GOLDEN_CAULDRON.get());

        event.getBlockColors().register((x, reader, pos, u) -> reader != null && pos != null ? BiomeColors.getAverageFoliageColor(reader, pos) : 0x48B518, // Default color for leaf blocks.
                VSBlocks.PAINTING_LEAVES.get());

        event.getBlockColors().register((x, reader, pos, u) -> reader != null && pos != null ? BiomeColors.getAverageGrassColor(reader, pos) : -1,
                VSBlocks.POTTED_SUGAR_CANE.get());
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerItemColorHandlers(final RegisterColorHandlersEvent.Item event) {
        event.getItemColors().register((stack, color) -> GrassColor.get(0.5d, 1), VSItems.POTTED_SUGAR_CANE.get(), VSItems.PAINTING_LEAVES.get());

        event.getItemColors().register((stack, color) -> {
            if (stack.getTag() != null && stack.getTag().contains("armor_design")) {
                return -1;
            } else {
                return color > 0 ? -1 : ((IDyeableWoolArmorItem) stack.getItem()).getColor(stack);
            }
        }, VSWeaponry.WOOL_SWEATER.get());
    }
}
