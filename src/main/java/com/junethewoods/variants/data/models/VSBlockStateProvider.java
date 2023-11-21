package com.junethewoods.variants.data.models;

import com.junethewoods.variants.Variants;
import com.junethewoods.variants.block.VSBlocks;
import net.minecraft.block.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.RegistryObject;

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
        simpleBlock(VSBlocks.DEEPSLATE_QUARTZ_ORE.get());
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
        doorBlock((DoorBlock) VSBlocks.PAINTING_DOOR_WANDERER.get(), modLoc("block/wanderer_door_bottom"), modLoc("block/wanderer_door_top"));
        doorBlock((DoorBlock) VSBlocks.PAINTING_DOOR_GRAHAM.get(), modLoc("block/graham_door_bottom"), modLoc("block/graham_door_top"));
        doorBlock((DoorBlock) VSBlocks.PAINTING_DOOR_FIRST.get(), modLoc("block/first_door_bottom"), modLoc("block/first_door_top"));
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
        simpleBlock(VSBlocks.GLOW_BLACK_STAINED_GLASS.get());
        paneBlock(((PaneBlock) VSBlocks.GLOW_BLACK_STAINED_GLASS_PANE.get()), modLoc("block/glow_black_stained_glass"), modLoc("block/glow_black_stained_glass_pane_top"));

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
        simpleBlock(VSBlocks.CHISELED_END_STONE_BRICKS.get());
        simpleBlock(VSBlocks.INFESTED_CHISELED_END_STONE_BRICKS.get(), models().cubeAll("infested_chiseled_end_stone_bricks", modLoc("block/chiseled_end_stone_bricks")));
        simpleBlock(VSBlocks.CHISELED_PURPUR_BLOCK.get());
        simpleBlock(VSBlocks.INFESTED_CHISELED_PURPUR_BLOCK.get(), models().cubeAll("infested_chiseled_purpur_block", modLoc("block/chiseled_purpur_block")));
        simpleBlock(VSBlocks.QUARTZ_GLASS.get());
        paneBlock(((PaneBlock) VSBlocks.QUARTZ_GLASS_PANE.get()), modLoc("block/quartz_glass"), modLoc("block/quartz_glass_pane_top"));
        fenceBlock((FenceBlock) VSBlocks.RED_NETHER_BRICK_FENCE.get(), mcLoc("block/red_nether_bricks"));
        paintingTrapdoor(VSBlocks.PAINTING_TRAPDOOR_ALBAN, mcLoc("painting/alban"));
        paintingTrapdoor(VSBlocks.PAINTING_TRAPDOOR_AZTEC, mcLoc("painting/aztec"));
        paintingTrapdoor(VSBlocks.PAINTING_TRAPDOOR_AZTEC2, mcLoc("painting/aztec2"));
        paintingTrapdoor(VSBlocks.PAINTING_TRAPDOOR_BOMB, mcLoc("painting/bomb"));
        paintingTrapdoor(VSBlocks.PAINTING_TRAPDOOR_KEBAB, mcLoc("painting/kebab"));
        paintingTrapdoor(VSBlocks.PAINTING_TRAPDOOR_PLANT, mcLoc("painting/plant"));
        paintingTrapdoor(VSBlocks.PAINTING_TRAPDOOR_WASTELAND, mcLoc("painting/wasteland"));
        axisBlock((RotatedPillarBlock) VSBlocks.ENDERWOOD_STEM.get(), modLoc("block/ender_stem"), modLoc("block/ender_stem_top"));
        axisBlock((RotatedPillarBlock) VSBlocks.ENDERWOOD_HYPHAE.get(), modLoc("block/ender_stem"), modLoc("block/ender_stem"));
        axisBlock((RotatedPillarBlock) VSBlocks.STRIPPED_ENDERWOOD_STEM.get(), modLoc("block/stripped_ender_stem"), modLoc("block/stripped_ender_stem_top"));
        axisBlock((RotatedPillarBlock) VSBlocks.STRIPPED_ENDERWOOD_HYPHAE.get(), modLoc("block/stripped_ender_stem"), modLoc("block/stripped_ender_stem"));
        simpleBlock(VSBlocks.ENDER_WART_BLOCK.get());
        simpleBlock(VSBlocks.ENDERWOOD_PLANKS.get(), models().cubeAll("ender_planks", modLoc("block/ender_planks")));
        stairsBlock((StairsBlock) VSBlocks.ENDERWOOD_STAIRS.get(), modLoc("block/ender_planks"));
        slabBlock((SlabBlock) VSBlocks.ENDERWOOD_SLAB.get(), modLoc("block/ender_planks"), modLoc("block/ender_planks"));
        fenceBlock((FenceBlock) VSBlocks.ENDERWOOD_FENCE.get(), modLoc("block/ender_planks"));
        fenceGateBlock((FenceGateBlock) VSBlocks.ENDERWOOD_FENCE_GATE.get(), modLoc("block/ender_planks"));
        doorBlock((DoorBlock) VSBlocks.ENDERWOOD_DOOR.get(), modLoc("block/ender_door_bottom"), modLoc("block/ender_door_top"));
        trapdoorBlock((TrapDoorBlock) VSBlocks.ENDERWOOD_TRAPDOOR.get(), modLoc("block/ender_trapdoor"), true);

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

    public void paintingTrapdoor(RegistryObject<Block> block, ResourceLocation painting) {
        trapdoorBlock((TrapDoorBlock) block.get(), painting, true);
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
