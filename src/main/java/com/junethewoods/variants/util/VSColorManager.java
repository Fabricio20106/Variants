package com.junethewoods.variants.util;

import com.junethewoods.variants.Variants;
import com.junethewoods.variants.block.VSBlocks;
import com.junethewoods.variants.item.VSItems;
import com.junethewoods.variants.item.VSWeaponry;
import com.junethewoods.variants.item.custom.IDyeableWoolArmorItem;
import net.minecraft.item.IDyeableArmorItem;
import net.minecraft.world.GrassColors;
import net.minecraft.world.biome.BiomeColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Variants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class VSColorManager {
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerBlockColorHandlers(final ColorHandlerEvent.Block event) {
        event.getBlockColors().register((x, reader, pos, u) -> reader != null && pos != null ? BiomeColors.getAverageWaterColor(reader, pos) : -1,
                VSBlocks.GOLDEN_CAULDRON.get());

        event.getBlockColors().register((x, reader, pos, u) -> reader != null && pos != null ? BiomeColors.getAverageGrassColor(reader, pos) : GrassColors.get(0.5d, 1),
                VSBlocks.POTTED_SUGAR_CANE.get());
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerItemColorHandlers(final ColorHandlerEvent.Item event) {
        event.getItemColors().register((stack, color) -> GrassColors.get(0.5d, 1), VSItems.POTTED_SUGAR_CANE.get());

        event.getItemColors().register((stack, color) -> color > 0 ? -1 : ((IDyeableWoolArmorItem) stack.getItem()).getColor(stack), VSWeaponry.WOOL_SWEATER.get());
    }
}
