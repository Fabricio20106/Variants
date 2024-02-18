package com.junethewoods.variants.data.models;

import com.junethewoods.variants.block.VSBlocks;
import net.minecraft.block.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nonnull;

public class VSBlockStateProvider extends VSBlockStateModels {
    public VSBlockStateProvider(DataGenerator generator, ExistingFileHelper fileHelper) {
        super(generator, fileHelper);
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
        simpleBlock(VSBlocks.POTTED_GLOW_BLACK_TULIP.get(), models().withExistingParent("potted_glow_black_tulip", modLoc("block/inventory_potted_plant")).texture("plant", "block/glow_black_tulip"));
        simpleBlock(VSBlocks.PAINTING_PLANKS.get());
        stairsBlock((StairsBlock) VSBlocks.PAINTING_STAIRS.get(), modLoc("block/painting_planks"));
        slabBlock((SlabBlock) VSBlocks.PAINTING_SLAB.get(), modLoc("block/painting_planks"), modLoc("block/painting_planks"));
        fenceBlock((FenceBlock) VSBlocks.PAINTING_FENCE.get(), modLoc("block/painting_planks"));
        fenceGateBlock((FenceGateBlock) VSBlocks.PAINTING_FENCE_GATE.get(), modLoc("block/painting_planks"));
        buttonBlock((AbstractButtonBlock) VSBlocks.PAINTING_BUTTON.get(), modLoc("block/painting_planks"));
        pressurePlate(VSBlocks.PAINTING_PRESSURE_PLATE.get(), modLoc("block/painting_planks"));
        doorBlock((DoorBlock) VSBlocks.PAINTING_DOOR.get(), modLoc("block/painting_door_bottom"), modLoc("block/painting_door_top"));
        doorBlock((DoorBlock) VSBlocks.PAINTING_DOOR_WANDERER.get(), modLoc("block/wanderer_door_bottom"), modLoc("block/wanderer_door_top"));
        doorBlock((DoorBlock) VSBlocks.PAINTING_DOOR_GRAHAM.get(), modLoc("block/graham_door_bottom"), modLoc("block/graham_door_top"));
        doorBlock((DoorBlock) VSBlocks.PAINTING_DOOR_FIRST.get(), modLoc("block/first_door_bottom"), modLoc("block/first_door_top"));
        trapdoorBlock((TrapDoorBlock) VSBlocks.PAINTING_TRAPDOOR.get(), modLoc("block/painting_trapdoor"), true);
        simpleBlock(VSBlocks.PLAIN_BIRCH_BOOKSHELF.get(), models().cubeTop("plain_birch_bookshelf", modLoc("block/plain_birch_bookshelf"), modLoc("block/plain_birch_planks")));
        logBlock((RotatedPillarBlock) VSBlocks.PAINTING_LOG.get());
        axisBlock((RotatedPillarBlock) VSBlocks.PAINTING_WOOD.get(), modLoc("block/painting_log"), modLoc("block/painting_log"));
        logBlock((RotatedPillarBlock) VSBlocks.STRIPPED_PAINTING_LOG.get());
        axisBlock((RotatedPillarBlock) VSBlocks.STRIPPED_PAINTING_WOOD.get(), modLoc("block/stripped_painting_log"), modLoc("block/stripped_painting_log"));
        simpleBlock(VSBlocks.PAINTING_LEAVES.get(), models().withExistingParent("painting_leaves", mcLoc("block/leaves")).texture("all", modLoc("block/painting_leaves")));
        simpleBlock(VSBlocks.PAINTING_SAPLING.get(), models().cross("painting_sapling", modLoc("block/painting_sapling")));
        simpleBlock(VSBlocks.POTTED_PAINTING_SAPLING.get(), models().withExistingParent("potted_painting_sapling", modLoc("block/inventory_potted_plant")).texture("plant", "block/painting_sapling"));
        simpleBlock(VSBlocks.GLOW_BLACK_WOOL.get());
        simpleBlock(VSBlocks.GLOW_BLACK_CARPET.get(), models().carpet("glow_black_carpet", modLoc("block/glow_black_wool")));
        simpleBlock(VSBlocks.GLOW_BLACK_BED.get(), models().getBuilder("glow_black_bed").parent(models().getExistingFile(mcLoc("block/bed"))));
        simpleBlock(VSBlocks.GLOW_BLACK_STAINED_GLASS.get());
        paneBlock(((PaneBlock) VSBlocks.GLOW_BLACK_STAINED_GLASS_PANE.get()), modLoc("block/glow_black_stained_glass"), modLoc("block/glow_black_stained_glass_pane_top"));

        // Additions of the biggest commit yet (1.4.8):
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
        simpleBlock(VSBlocks.PAINTING_SIGN.get(), models().getBuilder("painting_sign").texture("particle", modLoc("block/painting_planks")));
        simpleBlock(VSBlocks.PAINTING_WALL_SIGN.get(), models().getBuilder("painting_wall_sign").texture("particle", modLoc("block/painting_planks")));
        axisBlock((RotatedPillarBlock) VSBlocks.ENDERWOOD_STEM.get(), modLoc("block/ender_stem"), modLoc("block/ender_stem_top"));
        axisBlock((RotatedPillarBlock) VSBlocks.ENDERWOOD_HYPHAE.get(), modLoc("block/ender_stem"), modLoc("block/ender_stem"));
        axisBlock((RotatedPillarBlock) VSBlocks.STRIPPED_ENDERWOOD_STEM.get(), modLoc("block/stripped_ender_stem"), modLoc("block/stripped_ender_stem_top"));
        axisBlock((RotatedPillarBlock) VSBlocks.STRIPPED_ENDERWOOD_HYPHAE.get(), modLoc("block/stripped_ender_stem"), modLoc("block/stripped_ender_stem"));
        simpleBlock(VSBlocks.ENDER_WART_BLOCK.get());
        simpleBlock(VSBlocks.ENDERWOOD_PLANKS.get(), models().cubeAll("ender_planks", modLoc("block/ender_planks")));
        simpleBlock(VSBlocks.ENDERWOOD_BOOKSHELF.get(), models().cubeTop("ender_bookshelf", modLoc("block/ender_bookshelf"), modLoc("block/ender_planks")));
        stairsBlock((StairsBlock) VSBlocks.ENDERWOOD_STAIRS.get(), modLoc("block/ender_planks"));
        slabBlock((SlabBlock) VSBlocks.ENDERWOOD_SLAB.get(), modLoc("block/ender_planks"), modLoc("block/ender_planks"));
        fenceBlock((FenceBlock) VSBlocks.ENDERWOOD_FENCE.get(), modLoc("block/ender_planks"));
        fenceGateBlock((FenceGateBlock) VSBlocks.ENDERWOOD_FENCE_GATE.get(), modLoc("block/ender_planks"));
        buttonBlock((AbstractButtonBlock) VSBlocks.ENDERWOOD_BUTTON.get(), modLoc("block/ender_planks"));
        pressurePlate(VSBlocks.ENDERWOOD_PRESSURE_PLATE.get(), modLoc("block/ender_planks"));
        doorBlock((DoorBlock) VSBlocks.ENDERWOOD_DOOR.get(), modLoc("block/ender_door_bottom"), modLoc("block/ender_door_top"));
        trapdoorBlock((TrapDoorBlock) VSBlocks.ENDERWOOD_TRAPDOOR.get(), modLoc("block/ender_trapdoor"), true);
        simpleBlock(VSBlocks.ENDERWOOD_SIGN.get(), models().getBuilder("ender_sign").texture("particle", modLoc("block/ender_planks")));
        simpleBlock(VSBlocks.ENDERWOOD_WALL_SIGN.get(), models().getBuilder("ender_wall_sign").texture("particle", modLoc("block/ender_planks")));
        simpleBlock(VSBlocks.ENDER_NYLIUM.get(), models().cubeBottomTop("ender_nylium", modLoc("block/ender_nylium_side"), mcLoc("block/end_stone"), modLoc("block/ender_nylium")));
        simpleBlock(VSBlocks.ENDER_ROOTS.get(), models().cross("ender_roots", modLoc("block/ender_roots")));
        simpleBlock(VSBlocks.POTTED_ENDER_ROOTS.get(), models().withExistingParent("potted_ender_roots", modLoc("block/inventory_potted_plant")).texture("plant",  "block/ender_roots_pot"));
        simpleBlock(VSBlocks.END_SPROUTS.get(), models().cross("ender_sprouts", modLoc("block/ender_sprouts")));
        simpleBlock(VSBlocks.ENDER_FUNGUS.get(), models().cross("ender_fungus", modLoc("block/ender_fungus")));
        simpleBlock(VSBlocks.POTTED_ENDER_FUNGUS.get(), models().withExistingParent("potted_ender_fungus", modLoc("block/inventory_potted_plant")).texture("plant",  "block/ender_fungus"));
        simpleBlock(VSBlocks.WARPING_VINES.get(), models().cross("warping_vines", modLoc("block/warping_vines")));
        simpleBlock(VSBlocks.WARPING_VINES_PLANT.get(), models().cross("warping_vines_plant", modLoc("block/warping_vines_plant")));
        simpleBlock(VSBlocks.SOUL_LAVA.get(), models().getBuilder("soul_lava").texture("particle", modLoc("block/soul_lava_still")));
        simpleBlock(VSBlocks.MUSHROOM_STEW.get(), models().getBuilder("mushroom_stew").texture("particle", modLoc("block/mushroom_stew_still")));
        axisBlock((RotatedPillarBlock) VSBlocks.WITHER_BONE_BLOCK.get(), modLoc("block/wither_bone_block_side"), modLoc("block/wither_bone_block_top"));
        simpleBlock(VSBlocks.POTTED_GRASS.get(), models().withExistingParent("potted_grass", modLoc("block/inventory_tinted_potted_plant")).texture("plant",  "block/grass_pot"));
        simpleBlock(VSBlocks.POTTED_GOLDEN_CARROTS.get(), models().withExistingParent("potted_golden_carrots", modLoc("block/inventory_potted_plant")).texture("dirt", mcLoc("block/farmland")).texture("plant",
                "block/golden_carrots_pot"));
        simpleBlock(VSBlocks.POTTED_NETHER_WART.get(), models().withExistingParent("potted_nether_wart", modLoc("block/inventory_potted_plant")).texture("dirt", mcLoc("block/soul_sand")).texture("plant",
                "block/nether_wart_pot"));
        simpleBlock(VSBlocks.POTTED_WARPED_WART.get(), models().withExistingParent("potted_warped_wart", modLoc("block/inventory_potted_plant")).texture("dirt", mcLoc("block/soul_sand")).texture("plant",
                "block/warped_wart_pot"));
        simpleBlock(VSBlocks.POTTED_ENDER_WART.get(), models().withExistingParent("potted_ender_wart", modLoc("block/inventory_potted_plant")).texture("dirt", "block/ender_farmland").texture("plant",  "block/ender_wart_pot"));
        simpleBlock(VSBlocks.ENDER_NYLIUM_QUARTZ_ORE.get(), models().cubeBottomTop("ender_nylium_quartz_ore", modLoc("block/ender_nylium_quartz_ore_side"), modLoc("block/end_quartz_ore"), modLoc("block/ender_nylium")));
        simpleBlock(VSBlocks.POTTED_TORCH.get(), models().withExistingParent("potted_torch", modLoc("block/template_potted_torch")).texture("dirt", mcLoc("block/smooth_stone")).texture("torch", mcLoc("block/torch")));
        simpleBlock(VSBlocks.POTTED_SOUL_TORCH.get(), models().withExistingParent("potted_soul_torch", modLoc("block/template_potted_torch")).texture("dirt", mcLoc("block/smooth_stone")).texture("torch", mcLoc("block/soul_torch")));

        getVariantBuilder(VSBlocks.GOLDEN_CARROTS.get()).forAllStates(state -> {
            int cropAgeIndex = cropAgeToIndexSeven(state.getValue(BlockStateProperties.AGE_7));
            return ConfiguredModel.builder().modelFile(models().withExistingParent("golden_carrots_stage" + cropAgeIndex, modLoc("block/inventory_crop")).texture("crop", "block/golden_carrots_stage" + cropAgeIndex)).build();
        });
        getVariantBuilder(VSBlocks.WARPED_WART.get()).forAllStates(state -> {
            int cropAgeIndex = cropAgeToIndexWart(state.getValue(BlockStateProperties.AGE_3));
            return ConfiguredModel.builder().modelFile(models().crop("warped_wart_stage" + cropAgeIndex, modLoc("block/warped_wart_stage" + cropAgeIndex))).build();
        });
        getVariantBuilder(VSBlocks.ENDER_WART.get()).forAllStates(state -> {
            int cropAgeIndex = cropAgeToIndexWart(state.getValue(BlockStateProperties.AGE_3));
            return ConfiguredModel.builder().modelFile(models().crop("ender_wart_stage" + cropAgeIndex, modLoc("block/ender_wart_stage" + cropAgeIndex))).build();
        });
        getVariantBuilder(VSBlocks.GLOW_BERRY_BUSH.get()).forAllStates(state -> {
            int cropAgeIndex = cropAgeToIndexBush(state.getValue(BlockStateProperties.AGE_3));
            return ConfiguredModel.builder().modelFile(models().cross("glow_berry_bush_stage" + cropAgeIndex, modLoc("block/glow_berry_bush_stage" + cropAgeIndex))).build();
        });

        // Variants Builder for blocks other than plants.
        getVariantBuilder(VSBlocks.ENDER_FARMLAND.get()).forAllStates(state -> {
            String isMoist = moistIndex(state.getValue(BlockStateProperties.MOISTURE));
            return ConfiguredModel.builder().modelFile(models().getBuilder("ender_farmland" + isMoist).parent(models().getExistingFile(modLoc("block/template_farmland"))).texture("top", modLoc("block/ender_farmland" + isMoist)).texture("side",
                    modLoc("block/ender_farmland_side" + isMoist)).texture("dirt", mcLoc("block/end_stone"))).build();
        });
        getVariantBuilder(VSBlocks.POTTED_REDSTONE_TORCH.get()).forAllStates(state -> {
            String isOff = !state.getValue(BlockStateProperties.LIT) ? "_off" : "";
            return ConfiguredModel.builder().modelFile(models().getBuilder("potted_redstone_torch" + isOff).parent(models().getExistingFile(modLoc("block/template_potted_torch"))).texture("dirt", mcLoc("block/smooth_stone")).texture("torch", mcLoc(
                    "block/redstone_torch" + isOff))).build();
        });
    }
}
