package com.junethewoods.variants.data.models;

import com.junethewoods.variants.Variants;
import com.junethewoods.variants.block.VSBlocks;
import net.minecraft.block.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nonnull;

public class VSBlockStateProvider extends BlockStateProvider {
    public VSBlockStateProvider(DataGenerator generator, ExistingFileHelper fileHelper) {
        super(generator, Variants.MOD_ID, fileHelper);
    }

    @Nonnull
    @Override
    public String getName() {
        return "Variants - Block States and Models";
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(VSBlocks.QUARTZ_ORE.get());
        simpleBlock(VSBlocks.END_QUARTZ_ORE.get());
        simpleBlock(VSBlocks.RAW_DEBRIS_BLOCK.get());
        simpleBlock(VSBlocks.GLOW_BLACK_TULIP.get(), models().cross("glow_black_tulip", modLoc("block/glow_black_tulip")));
        simpleBlock(VSBlocks.POTTED_GLOW_BLACK_TULIP.get(), models().withExistingParent("potted_glow_black_tulip", modLoc("block/inventory_potted_plant")).texture("plant",
                "block/glow_black_tulip"));
        simpleBlock(VSBlocks.PAINTING_PLANKS.get());
        stairsBlock((StairsBlock) VSBlocks.PAINTING_STAIRS.get(), modLoc("block/painting_planks"));
        slabBlock((SlabBlock) VSBlocks.PAINTING_SLAB.get(), modLoc("block/painting_planks"), modLoc("block/painting_planks"));
        fenceBlock((FenceBlock) VSBlocks.PAINTING_FENCE.get(), modLoc("block/painting_planks"));
        fenceGateBlock((FenceGateBlock) VSBlocks.PAINTING_FENCE_GATE.get(), modLoc("block/painting_planks"));
        doorBlock((DoorBlock) VSBlocks.PAINTING_DOOR.get(), modLoc("block/painting_door_bottom"), modLoc("block/painting_door_top"));
        doorBlock((DoorBlock) VSBlocks.WANDERER_DOOR.get(), modLoc("block/wanderer_door_bottom"), modLoc("block/wanderer_door_top"));
        doorBlock((DoorBlock) VSBlocks.GRAHAM_DOOR.get(), modLoc("block/graham_door_bottom"), modLoc("block/graham_door_top"));
        doorBlock((DoorBlock) VSBlocks.FIRST_DOOR.get(), modLoc("block/first_door_bottom"), modLoc("block/first_door_top"));
        trapdoorBlock((TrapDoorBlock) VSBlocks.PAINTING_TRAPDOOR.get(), modLoc("block/painting_trapdoor"), true);
        simpleBlock(VSBlocks.PLAIN_BIRCH_BOOKSHELF.get(), models().cubeTop("plain_birch_bookshelf", modLoc("block/plain_birch_bookshelf"),
                modLoc("block/plain_birch_planks")));
        logBlock((RotatedPillarBlock) VSBlocks.PAINTING_LOG.get());
        axisBlock((RotatedPillarBlock) VSBlocks.PAINTING_WOOD.get(), modLoc("block/painting_log"), modLoc("block/painting_log"));
        logBlock((RotatedPillarBlock) VSBlocks.STRIPPED_PAINTING_LOG.get());
        axisBlock((RotatedPillarBlock) VSBlocks.STRIPPED_PAINTING_WOOD.get(), modLoc("block/stripped_painting_log"), modLoc("block/stripped_painting_log"));
        simpleBlock(VSBlocks.PAINTING_LEAVES.get(), models().withExistingParent("painting_leaves", mcLoc("block/leaves")).texture("all",
                modLoc("block/painting_leaves")));
        simpleBlock(VSBlocks.PAINTING_SAPLING.get(), models().cross("painting_sapling", modLoc("block/painting_sapling")));
        simpleBlock(VSBlocks.POTTED_PAINTING_SAPLING.get(), models().withExistingParent("potted_painting_sapling", modLoc("block/inventory_potted_plant")).texture("plant",
                "block/painting_sapling"));
        simpleBlock(VSBlocks.GLOW_BLACK_WOOL.get());
        simpleBlock(VSBlocks.GLOW_BLACK_CARPET.get(), models().carpet("glow_black_carpet", modLoc("block/glow_black_wool")));

        // Additions of the biggest commit yet:
        simpleBlock(VSBlocks.ELDER_SEA_LANTERN.get());
        simpleBlock(VSBlocks.ELDER_PRISMARINE.get());
        stairsBlock((StairsBlock) VSBlocks.ELDER_PRISMARINE_STAIRS.get(), modLoc("block/elder_prismarine"));
        slabBlock((SlabBlock) VSBlocks.ELDER_PRISMARINE_SLAB.get(), modLoc("block/elder_prismarine"), modLoc("block/elder_prismarine"));
        wallBlock((WallBlock) VSBlocks.ELDER_PRISMARINE_WALL.get(), modLoc("block/elder_prismarine"));
        simpleBlock(VSBlocks.ELDER_PRISMARINE_BRICKS.get());
        stairsBlock((StairsBlock) VSBlocks.ELDER_PRISMARINE_BRICK_STAIRS.get(), modLoc("block/elder_prismarine_bricks"));
        slabBlock((SlabBlock) VSBlocks.ELDER_PRISMARINE_BRICK_SLAB.get(), modLoc("block/elder_prismarine_bricks"), modLoc("block/elder_prismarine_bricks"));
        simpleBlock(VSBlocks.DARK_ELDER_PRISMARINE.get());
        stairsBlock((StairsBlock) VSBlocks.DARK_ELDER_PRISMARINE_STAIRS.get(), modLoc("block/dark_elder_prismarine"));
        slabBlock((SlabBlock) VSBlocks.DARK_ELDER_PRISMARINE_SLAB.get(), modLoc("block/dark_elder_prismarine"), modLoc("block/dark_elder_prismarine"));

        getVariantBuilder(VSBlocks.GOLDEN_CARROTS.get()).forAllStates(state -> {
            int cropAgeIndex = cropAgeToIndexPotato(state.getValue(BlockStateProperties.AGE_7));
            return ConfiguredModel.builder().modelFile(models().withExistingParent("golden_carrots_stage" + cropAgeIndex, modLoc("block/inventory_crop")).texture("crop",
                    "block/golden_carrots_stage" + cropAgeIndex)).build();
        });
        getVariantBuilder(VSBlocks.WARPED_WART.get()).forAllStates(state -> {
            int cropAgeIndex = cropAgeToIndexWart(state.getValue(BlockStateProperties.AGE_3));
            return ConfiguredModel.builder().modelFile(models().crop("warped_wart_stage" + cropAgeIndex, modLoc("block/warped_wart_stage" + cropAgeIndex))).build();
        });
    }

    public static int cropAgeToIndexPotato(int age) {
        if (age > 6) return 3;
        if (age > 3) return 2;
        if (age > 1) return 1;
        return 0;
    }

    public static int cropAgeToIndexWart(int age) {
        if (age == 3) return 2;
        if (age == 2 || age == 1) return 1;
        return 0;
    }
}
