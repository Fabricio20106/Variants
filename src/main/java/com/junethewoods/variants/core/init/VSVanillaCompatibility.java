package com.junethewoods.variants.core.init;

import com.google.common.collect.Maps;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;
import net.minecraftforge.event.world.BlockEvent.BlockToolModificationEvent;

/**
 * Todo: Move this entire class to a {@link BlockToolModificationEvent} subscriber method.
 * */
@Deprecated(since = "1.18")
public class VSVanillaCompatibility {
    public static void register() {
        strippable(VSBlocks.PAINTING_LOG.get(), VSBlocks.STRIPPED_PAINTING_LOG.get());
        strippable(VSBlocks.PAINTING_WOOD.get(), VSBlocks.STRIPPED_PAINTING_WOOD.get());
        strippable(VSBlocks.ENDER_STEM.get(), VSBlocks.STRIPPED_ENDER_STEM.get());
        strippable(VSBlocks.ENDER_HYPHAE.get(), VSBlocks.STRIPPED_ENDER_HYPHAE.get());

        flammable(VSBlocks.PAINTING_LOG.get(), 5, 5);
        flammable(VSBlocks.PAINTING_WOOD.get(), 5, 5);
        flammable(VSBlocks.STRIPPED_PAINTING_LOG.get(), 5, 5);
        flammable(VSBlocks.STRIPPED_PAINTING_WOOD.get(), 5, 5);
        flammable(VSBlocks.PAINTING_STAIRS.get(), 5, 20);
        flammable(VSBlocks.PAINTING_SLAB.get(), 5, 20);
        flammable(VSBlocks.PAINTING_FENCE_GATE.get(), 5, 20);
        flammable(VSBlocks.PAINTING_FENCE.get(), 5, 20);
        flammable(VSBlocks.PAINTING_PLANKS.get(), 5, 20);
        flammable(VSBlocks.PAINTING_LEAVES.get(), 30, 60);
        flammable(VSBlocks.GLOW_BLACK_WOOL.get(), 30, 60);
        flammable(VSBlocks.GLOW_BLACK_CARPET.get(), 60, 20);
        flammable(VSBlocks.SUNNY_FLOWER.get(), 60, 100);
        flammable(VSBlocks.GLOW_BLACK_TULIP.get(), 60, 100);
        flammable(VSBlocks.GLOW_BERRY_BUSH.get(), 60, 100);
    }

    private static void strippable(Block log, Block strippedLog) {
        AxeItem.STRIPPABLES = Maps.newHashMap(AxeItem.STRIPPABLES);
        AxeItem.STRIPPABLES.put(log, strippedLog);
    }

    private static void flammable(Block block, int encouragement, int flammability) {
        FireBlock fireBlock = (FireBlock) Blocks.FIRE;
        fireBlock.setFlammable(block, encouragement, flammability);
    }
}
