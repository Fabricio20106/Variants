package melonystudios.variants.util;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import melonystudios.variants.block.VSBlocks;
import melonystudios.variants.item.VSItems;
import net.minecraft.block.*;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.ShovelItem;
import net.minecraft.state.properties.BedPart;
import net.minecraft.village.PointOfInterestType;

public class VSVanillaCompatibility {
    public static void compostable(float chance, Item item) {
        ComposterBlock.COMPOSTABLES.put(item, chance);
    }

    public static void tillable(Block block, BlockState farmland) {
        HoeItem.TILLABLES = Maps.newHashMap(HoeItem.TILLABLES);
        HoeItem.TILLABLES.put(block, farmland);
    }

    public static void flattenable(Block block, BlockState path) {
        ShovelItem.FLATTENABLES = Maps.newHashMap(ShovelItem.FLATTENABLES);
        ShovelItem.FLATTENABLES.put(block, path);
    }

    public static void addBed(Block bed) {
        PointOfInterestType.BEDS = Sets.newHashSet(PointOfInterestType.BEDS);
        PointOfInterestType.BEDS.add(bed.defaultBlockState().setValue(BedBlock.PART, BedPart.HEAD));
    }

    public static void flammable(Block block, int encouragement, int flammability) {
        FireBlock fireBlock = (FireBlock) Blocks.FIRE;
        fireBlock.setFlammable(block, encouragement, flammability);
    }

    public static void compostables() {
        compostable(0.3F, VSItems.PAINTING_SAPLING.get());
        compostable(0.3F, VSItems.PAINTING_LEAVES.get());
        compostable(0.3F, VSItems.GLOW_BERRY_BUSH.get());
        compostable(0.3F, VSItems.WILD_CRIMSON_WHEAT.get());
        compostable(0.3F, VSItems.WILD_SOUL_CARROTS.get());
        compostable(0.3F, VSItems.WILD_WARPED_POTATOES.get());
        compostable(0.3F, VSItems.WILD_MELTING_BEETS.get());
        compostable(0.3F, VSItems.CRIMSON_WHEAT_SEEDS.get());
        compostable(0.3F, VSItems.MELTING_BEET_SEEDS.get());
        compostable(0.5F, VSItems.END_SPROUTS.get());
        compostable(0.5F, VSItems.WARPING_VINES.get());
        compostable(0.65F, VSItems.WARPED_WART.get());
        compostable(0.65F, VSItems.ENDER_WART.get());
        compostable(0.65F, VSItems.GLOW_BLACK_TULIP.get());
        compostable(0.65F, VSItems.SUNNY_FLOWER.get());
        compostable(0.65F, VSItems.GOLDEN_CARROTS.get());
        compostable(0.65F, VSItems.ENDER_ROOTS.get());
        compostable(0.65F, VSItems.ENDER_FUNGUS.get());
        compostable(0.65F, VSItems.CRIMSON_WHEAT.get());
        compostable(0.65F, VSItems.SOUL_CARROT.get());
        compostable(0.65F, VSItems.WARPED_POTATO.get());
        compostable(0.65F, VSItems.MELTING_BEET.get());
        compostable(0.65F, Items.GOLDEN_CARROT);
        compostable(0.85F, VSItems.ENDER_WART_BLOCK.get());
        compostable(0.85F, VSItems.BAKED_WARPED_POTATO.get());
        compostable(0.85F, VSItems.CRIMSON_LOAF.get());
    }

    public static void tillables() {
        tillable(Blocks.CRIMSON_NYLIUM, VSBlocks.CRIMSON_FARMLAND.get().defaultBlockState());
        tillable(Blocks.WARPED_NYLIUM, VSBlocks.WARPED_FARMLAND.get().defaultBlockState());
        tillable(VSBlocks.ENDER_NYLIUM.get(), VSBlocks.ENDER_FARMLAND.get().defaultBlockState());
    }

    public static void flattenables() {
        flattenable(Blocks.DIRT, Blocks.GRASS_PATH.defaultBlockState());
        flattenable(Blocks.COARSE_DIRT, Blocks.GRASS_PATH.defaultBlockState());
        flattenable(Blocks.MYCELIUM, Blocks.GRASS_PATH.defaultBlockState());
        flattenable(Blocks.PODZOL, Blocks.GRASS_PATH.defaultBlockState());
    }

    public static void flammables() {
        flammable(VSBlocks.GLOW_BLACK_WOOL.get(), 30, 60);
        flammable(VSBlocks.GLOW_BLACK_CARPET.get(), 50, 20);
        flammable(VSBlocks.GLOW_BLACK_TULIP.get(), 60, 100);
        flammable(VSBlocks.SUNNY_FLOWER.get(), 60, 100);
        flammable(VSBlocks.GLOW_BERRY_BUSH.get(), 60, 100);
        flammable(VSBlocks.ENDER_ROOTS.get(), 60, 100);
        flammable(VSBlocks.END_SPROUTS.get(), 60, 100);
        flammable(VSBlocks.ENDER_FUNGUS.get(), 60, 100);

        flammable(VSBlocks.PAINTING_LOG.get(), 5, 5);
        flammable(VSBlocks.PAINTING_WOOD.get(), 5, 5);
        flammable(VSBlocks.STRIPPED_PAINTING_LOG.get(), 5, 5);
        flammable(VSBlocks.STRIPPED_PAINTING_WOOD.get(), 5, 5);
        flammable(VSBlocks.PAINTING_PLANKS.get(), 5, 20);
        flammable(VSBlocks.PAINTING_STAIRS.get(), 5, 20);
        flammable(VSBlocks.PAINTING_SLAB.get(), 5, 20);
        flammable(VSBlocks.PAINTING_FENCE.get(), 5, 20);
        flammable(VSBlocks.PAINTING_FENCE_GATE.get(), 5, 20);
        flammable(VSBlocks.PAINTING_SIGN.get(), 5, 20);
        flammable(VSBlocks.PAINTING_WALL_SIGN.get(), 5, 20);
        flammable(VSBlocks.PAINTING_LEAVES.get(), 30, 60);
        flammable(VSBlocks.PAINTING_SAPLING.get(), 60, 100);

        flammable(VSBlocks.ENDERWOOD_STEM.get(), 5, 5);
        flammable(VSBlocks.ENDERWOOD_HYPHAE.get(), 5, 5);
        flammable(VSBlocks.STRIPPED_ENDERWOOD_STEM.get(), 5, 5);
        flammable(VSBlocks.STRIPPED_ENDERWOOD_HYPHAE.get(), 5, 5);
        flammable(VSBlocks.ENDERWOOD_PLANKS.get(), 5, 20);
        flammable(VSBlocks.ENDERWOOD_BOOKSHELF.get(), 30, 20);
        flammable(VSBlocks.ENDERWOOD_STAIRS.get(), 5, 20);
        flammable(VSBlocks.ENDERWOOD_SLAB.get(), 5, 20);
        flammable(VSBlocks.ENDERWOOD_FENCE.get(), 5, 20);
        flammable(VSBlocks.ENDERWOOD_FENCE_GATE.get(), 5, 20);
        flammable(VSBlocks.ENDERWOOD_SIGN.get(), 5, 20);
        flammable(VSBlocks.ENDERWOOD_WALL_SIGN.get(), 5, 20);
        flammable(VSBlocks.ENDER_WART_BLOCK.get(), 30, 60);
    }
}
