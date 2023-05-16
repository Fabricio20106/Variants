package com.junethewoods.variants.core.init;

import com.google.common.collect.Maps;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;

public class VSVanillaCompatibility {
    public static void register() {
        registerStrippables(VSBlocks.painting_log.get(), VSBlocks.stripped_painting_log.get());
        registerStrippables(VSBlocks.painting_wood.get(), VSBlocks.stripped_painting_wood.get());
        registerStrippables(VSBlocks.ender_stem.get(), VSBlocks.stripped_ender_stem.get());
        registerStrippables(VSBlocks.ender_hyphae.get(), VSBlocks.stripped_ender_hyphae.get());
        /*registerHoeables(Blocks.CRIMSON_NYLIUM, VSBlocks.CRIMSON_FARMLAND.get().defaultBlockState());
        registerHoeables(Blocks.WARPED_NYLIUM, VSBlocks.WARPED_FARMLAND.get().defaultBlockState());
        registerHoeables(VSBlocks.ender_nylium.get(), VSBlocks.ENDER_FARMLAND.get().defaultBlockState());*/

        registerFlammable(VSBlocks.painting_log.get(), 5, 5);
        registerFlammable(VSBlocks.painting_wood.get(), 5, 5);
        registerFlammable(VSBlocks.stripped_painting_log.get(), 5, 5);
        registerFlammable(VSBlocks.stripped_painting_wood.get(), 5, 5);
        registerFlammable(VSBlocks.painting_stairs.get(), 5, 20);
        registerFlammable(VSBlocks.painting_slab.get(), 5, 20);
        registerFlammable(VSBlocks.painting_fence_gate.get(), 5, 20);
        registerFlammable(VSBlocks.painting_fence.get(), 5, 20);
        registerFlammable(VSBlocks.PAINTING_PLANKS.get(), 5, 20);
        registerFlammable(VSBlocks.PAINTING_LEAVES.get(), 30, 60);
        registerFlammable(VSBlocks.glow_black_wool.get(), 30, 60);
        registerFlammable(VSBlocks.glow_black_carpet.get(), 60, 20);
        registerFlammable(VSBlocks.SUNNY_FLOWER.get(), 60, 100);
        registerFlammable(VSBlocks.GLOW_BLACK_TULIP.get(), 60, 100);
        registerFlammable(VSBlocks.GLOW_BERRY_BUSH.get(), 60, 100);
    }

    private static void registerStrippables(Block log, Block strippedLog) {
        AxeItem.STRIPPABLES = Maps.newHashMap(AxeItem.STRIPPABLES);
        AxeItem.STRIPPABLES.put(log, strippedLog);
    }

    /*private static void registerHoeables(Block block, BlockState hoedBlock) {
        HoeItem.TILLABLES.put(block, Pair.of(HoeItem::onlyIfAirAbove, hoedBlock));
        //ImmutableMap.of(Blocks.GRASS_BLOCK, Pair.of(HoeItem::onlyIfAirAbove, changeIntoState(Blocks.FARMLAND.defaultBlockState()))
    }*/

    private static void registerFlammable(Block block, int encouragement, int flammability) {
        FireBlock fireBlock = (FireBlock) Blocks.FIRE;
        fireBlock.setFlammable(block, encouragement, flammability);
    }
}
