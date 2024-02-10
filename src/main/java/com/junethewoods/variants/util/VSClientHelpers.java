package com.junethewoods.variants.util;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.junethewoods.variants.block.VSBlocks;
import com.junethewoods.variants.item.VSItems;
import com.junethewoods.variants.item.custom.armor.WoolArmorItem;
import net.minecraft.block.*;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.properties.BedPart;
import net.minecraft.util.ResourceLocation;
import net.minecraft.village.PointOfInterestType;

import static net.minecraft.item.ItemModelsProperties.register;

public class VSClientHelpers {
    // Can be used to add new wool armor (currently only sweater) colors.
    public static void woolArmorColor(String colorName, int colorCode) {
        WoolArmorItem.COLOR_NAME_TO_CODE = Maps.newHashMap(WoolArmorItem.COLOR_NAME_TO_CODE);
        WoolArmorItem.COLOR_NAME_TO_CODE.put(colorName, colorCode);
    }

    public static void compostable(float chance, Item item) {
        ComposterBlock.COMPOSTABLES.put(item, chance);
    }

    public static void tillable(Block block, BlockState farmland) {
        HoeItem.TILLABLES = Maps.newHashMap(HoeItem.TILLABLES);
        HoeItem.TILLABLES.put(block, farmland);
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
        compostable(0.3f, VSItems.PAINTING_SAPLING.get());
        compostable(0.3f, VSItems.PAINTING_LEAVES.get());
        compostable(0.3f, VSItems.GLOW_BERRY_BUSH.get());
        compostable(0.5f, VSItems.END_SPROUTS.get());
        compostable(0.5f, VSItems.WARPING_VINES.get());
        compostable(0.65f, VSItems.WARPED_WART.get());
        compostable(0.65f, VSItems.ENDER_WART.get());
        compostable(0.65f, VSItems.GLOW_BLACK_TULIP.get());
        compostable(0.65f, VSItems.SUNNY_FLOWER.get());
        compostable(0.65f, VSItems.GOLDEN_CARROTS.get());
        compostable(0.65f, VSItems.ENDER_ROOTS.get());
        compostable(0.65f, VSItems.ENDER_FUNGUS.get());
        compostable(0.65f, Items.GOLDEN_CARROT);
        compostable(0.85f, VSItems.ENDER_WART_BLOCK.get());
    }

    public static void tillables() {
        tillable(VSBlocks.ENDER_NYLIUM.get(), VSBlocks.ENDER_FARMLAND.get().defaultBlockState());
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

    public static void makeBow(Item bow) {
        register(bow, new ResourceLocation("pull"), (stack, world, livEntity) -> {
            if (livEntity == null) {
                return 0;
            } else {
                return livEntity.getUseItem() != stack ? 0 : (float) (stack.getUseDuration() - livEntity.getUseItemRemainingTicks()) / 20;
            }
        });
        register(bow, new ResourceLocation("pulling"), (stack, world, livEntity) -> livEntity != null && livEntity.isUsingItem() && livEntity.getUseItem() == stack ? 1 : 0);
    }

    public static void makeShield(Item shield) {
        register(shield, new ResourceLocation("blocking"), (stack, world, livEntity) -> livEntity != null && livEntity.isUsingItem() && livEntity.getUseItem() == stack ? 1 : 0);
    }

    public static void makeCustomWoolSweater(Item sweater) {
        register(sweater, new ResourceLocation("design"), (stack, world, livEntity) -> {
            CompoundNBT tag = stack.getTag();
            if (tag != null && tag.contains("armor_design")) {
                return tag.getInt("armor_design");
            }
            return 0;
        });
    }

    public static void makeExpoStew(Item expoStew) {
        register(expoStew, new ResourceLocation("bowl_id"), (stack, world, livEntity) -> {
            CompoundNBT tag = stack.getOrCreateTagElement("bowl_type");
            if (tag.contains("bowl_id")) {
                return tag.getInt("bowl_id");
            }
            return 0;
        });
    }
}
