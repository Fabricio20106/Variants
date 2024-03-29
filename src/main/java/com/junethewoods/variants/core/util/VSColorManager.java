package com.junethewoods.variants.core.util;

import com.junethewoods.variants.common.item.DyeableWoolItem;
import com.junethewoods.variants.core.Variants;
import com.junethewoods.variants.core.init.VSBlocks;
import com.junethewoods.variants.core.init.VSItems;
import com.junethewoods.variants.core.init.VSWeaponry;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GrassColor;
import net.minecraft.world.level.block.Blocks;
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
        event.getBlockColors().register((x, reader, pos, u) -> reader != null && pos != null ? BiomeColors.getAverageGrassColor(reader, pos) : GrassColor.get(0.5d, 1), VSBlocks.POTTED_SUGAR_CANE.get(), VSBlocks.POTTED_GRASS.get(),
                VSBlocks.PAINTING_LEAVES.get());

        // Biome Tinted Vanilla Blocks
        event.getBlockColors().register((x, reader, pos, u) -> reader != null && pos != null ? BiomeColors.getAverageGrassColor(reader, pos) : GrassColor.get(0.5d, 1), Blocks.SWEET_BERRY_BUSH, Blocks.POPPY, Blocks.OAK_SAPLING);
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerItemColorHandlers(final ColorHandlerEvent.Item event) {
        event.getItemColors().register((stack, color) -> color > 0 ? -1 : ((DyeableWoolItem) stack.getItem()).getColor(stack), VSWeaponry.WOOL_HELMET.get(), VSWeaponry.WOOL_SWEATCHEST.get(), VSWeaponry.WOOL_LEGGINGS.get(), VSWeaponry.WOOL_BOOTS.get());

        event.getItemColors().register((stack, color) -> GrassColor.get(0.5d, 1), VSItems.PAINTING_LEAVES.get(), VSItems.POTTED_GRASS.get(), VSItems.POTTED_SUGAR_CANE.get());
    }
}
