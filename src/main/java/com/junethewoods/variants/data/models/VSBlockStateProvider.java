package com.junethewoods.variants.data.models;

import com.junethewoods.variants.Variants;
import com.junethewoods.variants.block.VSBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public class VSBlockStateProvider extends BlockStateProvider {
    public VSBlockStateProvider(PackOutput output, ExistingFileHelper fileHelper) {
        super(output, Variants.MOD_ID, fileHelper);
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
        simpleBlock(VSBlocks.GLOW_BLACK_TULIP.get(), models().cross("glow_black_tulip", modLoc("block/glow_black_tulip")).renderType("cutout"));
        simpleBlock(VSBlocks.POTTED_GLOW_BLACK_TULIP.get(), models().withExistingParent("potted_glow_black_tulip", modLoc("block/inventory_potted_plant")).texture("plant", "block/glow_black_tulip").renderType("cutout"));
        simpleBlock(VSBlocks.PAINTING_PLANKS.get());
        stairsBlock((StairBlock) VSBlocks.PAINTING_STAIRS.get(), modLoc("block/painting_planks"));
        slabBlock((SlabBlock) VSBlocks.PAINTING_SLAB.get(), modLoc("block/painting_planks"), modLoc("block/painting_planks"));
        fenceBlock((FenceBlock) VSBlocks.PAINTING_FENCE.get(), modLoc("block/painting_planks"));
        fenceGateBlock((FenceGateBlock) VSBlocks.PAINTING_FENCE_GATE.get(), modLoc("block/painting_planks"));
        doorBlockWithRenderType((DoorBlock) VSBlocks.PAINTING_DOOR.get(), modLoc("block/painting_door_bottom"), modLoc("block/painting_door_top"), "cutout");
        doorBlockWithRenderType((DoorBlock) VSBlocks.PAINTING_DOOR_WANDERER.get(), modLoc("block/wanderer_door_bottom"), modLoc("block/wanderer_door_top"), "cutout");
        doorBlockWithRenderType((DoorBlock) VSBlocks.PAINTING_DOOR_GRAHAM.get(), modLoc("block/graham_door_bottom"), modLoc("block/graham_door_top"), "cutout");
        doorBlockWithRenderType((DoorBlock) VSBlocks.PAINTING_DOOR_FIRST.get(), modLoc("block/first_door_bottom"), modLoc("block/first_door_top"), "cutout");
        trapdoorBlockWithRenderType((TrapDoorBlock) VSBlocks.PAINTING_TRAPDOOR.get(), modLoc("block/painting_trapdoor"), true, "cutout");
        simpleBlock(VSBlocks.PLAIN_BIRCH_BOOKSHELF.get(), models().cubeTop("plain_birch_bookshelf", modLoc("block/plain_birch_bookshelf"), modLoc("block/plain_birch_planks")));
        logBlock((RotatedPillarBlock) VSBlocks.PAINTING_LOG.get());
        axisBlock((RotatedPillarBlock) VSBlocks.PAINTING_WOOD.get(), modLoc("block/painting_log"), modLoc("block/painting_log"));
        logBlock((RotatedPillarBlock) VSBlocks.STRIPPED_PAINTING_LOG.get());
        axisBlock((RotatedPillarBlock) VSBlocks.STRIPPED_PAINTING_WOOD.get(), modLoc("block/stripped_painting_log"), modLoc("block/stripped_painting_log"));
        simpleBlock(VSBlocks.PAINTING_LEAVES.get(), models().withExistingParent("painting_leaves", mcLoc("block/leaves")).texture("all", modLoc("block/painting_leaves")).renderType("cutout"));
        simpleBlock(VSBlocks.PAINTING_SAPLING.get(), models().cross("painting_sapling", modLoc("block/painting_sapling")).renderType("cutout"));
        simpleBlock(VSBlocks.POTTED_PAINTING_SAPLING.get(), models().withExistingParent("potted_painting_sapling", modLoc("block/inventory_potted_plant")).texture("plant", "block/painting_sapling").renderType("cutout"));
        simpleBlock(VSBlocks.GLOW_BLACK_WOOL.get());
        simpleBlock(VSBlocks.GLOW_BLACK_CARPET.get(), models().carpet("glow_black_carpet", modLoc("block/glow_black_wool")));
        simpleBlock(VSBlocks.GLOW_BLACK_STAINED_GLASS.get(), models().cubeAll("glow_black_stained_glass", modLoc("block/glow_black_stained_glass")).renderType("translucent"));
        paneBlockWithRenderType(((IronBarsBlock) VSBlocks.GLOW_BLACK_STAINED_GLASS_PANE.get()), modLoc("block/glow_black_stained_glass"), modLoc("block/glow_black_stained_glass_pane_top"), "translucent");

        // Additions of the biggest commit yet:
        simpleBlock(VSBlocks.ELDER_SEA_LANTERN.get());
        simpleBlock(VSBlocks.ELDER_PRISMARINE.get());
        stairsBlock((StairBlock) VSBlocks.ELDER_PRISMARINE_STAIRS.get(), modLoc("block/elder_prismarine"));
        slabBlock((SlabBlock) VSBlocks.ELDER_PRISMARINE_SLAB.get(), modLoc("block/elder_prismarine"), modLoc("block/elder_prismarine"));
        wallBlock((WallBlock) VSBlocks.ELDER_PRISMARINE_WALL.get(), modLoc("block/elder_prismarine"));
        simpleBlock(VSBlocks.ELDER_PRISMARINE_BRICKS.get());
        stairsBlock((StairBlock) VSBlocks.ELDER_PRISMARINE_BRICK_STAIRS.get(), modLoc("block/elder_prismarine_bricks"));
        slabBlock((SlabBlock) VSBlocks.ELDER_PRISMARINE_BRICK_SLAB.get(), modLoc("block/elder_prismarine_bricks"), modLoc("block/elder_prismarine_bricks"));
        simpleBlock(VSBlocks.DARK_ELDER_PRISMARINE.get());
        stairsBlock((StairBlock) VSBlocks.DARK_ELDER_PRISMARINE_STAIRS.get(), modLoc("block/dark_elder_prismarine"));
        slabBlock((SlabBlock) VSBlocks.DARK_ELDER_PRISMARINE_SLAB.get(), modLoc("block/dark_elder_prismarine"), modLoc("block/dark_elder_prismarine"));
        simpleBlock(VSBlocks.CHISELED_END_STONE_BRICKS.get());
        simpleBlock(VSBlocks.INFESTED_CHISELED_END_STONE_BRICKS.get(), models().cubeAll("infested_chiseled_end_stone_bricks", modLoc("block/chiseled_end_stone_bricks")));
        simpleBlock(VSBlocks.CHISELED_PURPUR_BLOCK.get());
        simpleBlock(VSBlocks.INFESTED_CHISELED_PURPUR_BLOCK.get(), models().cubeAll("infested_chiseled_purpur_block", modLoc("block/chiseled_purpur_block")));
        simpleBlock(VSBlocks.QUARTZ_GLASS.get(), models().cubeAll("quartz_glass", modLoc("block/quartz_glass")).renderType("cutout"));
        paneBlockWithRenderType(((IronBarsBlock) VSBlocks.QUARTZ_GLASS_PANE.get()), modLoc("block/quartz_glass"), modLoc("block/quartz_glass_pane_top"), "cutout");
        fenceBlock((FenceBlock) VSBlocks.RED_NETHER_BRICK_FENCE.get(), mcLoc("block/red_nether_bricks"));
        paintingTrapdoor(VSBlocks.PAINTING_TRAPDOOR_ALBAN.get(), mcLoc("painting/alban"));
        paintingTrapdoor(VSBlocks.PAINTING_TRAPDOOR_AZTEC.get(), mcLoc("painting/aztec"));
        paintingTrapdoor(VSBlocks.PAINTING_TRAPDOOR_AZTEC2.get(), mcLoc("painting/aztec2"));
        paintingTrapdoor(VSBlocks.PAINTING_TRAPDOOR_BOMB.get(), mcLoc("painting/bomb"));
        paintingTrapdoor(VSBlocks.PAINTING_TRAPDOOR_KEBAB.get(), mcLoc("painting/kebab"));
        paintingTrapdoor(VSBlocks.PAINTING_TRAPDOOR_PLANT.get(), mcLoc("painting/plant"));
        paintingTrapdoor(VSBlocks.PAINTING_TRAPDOOR_WASTELAND.get(), mcLoc("painting/wasteland"));
        axisBlock((RotatedPillarBlock) VSBlocks.ENDERWOOD_STEM.get(), modLoc("block/ender_stem"), modLoc("block/ender_stem_top"));
        axisBlock((RotatedPillarBlock) VSBlocks.ENDERWOOD_HYPHAE.get(), modLoc("block/ender_stem"), modLoc("block/ender_stem"));
        axisBlock((RotatedPillarBlock) VSBlocks.STRIPPED_ENDERWOOD_STEM.get(), modLoc("block/stripped_ender_stem"), modLoc("block/stripped_ender_stem_top"));
        axisBlock((RotatedPillarBlock) VSBlocks.STRIPPED_ENDERWOOD_HYPHAE.get(), modLoc("block/stripped_ender_stem"), modLoc("block/stripped_ender_stem"));
        simpleBlock(VSBlocks.ENDER_WART_BLOCK.get());
        simpleBlock(VSBlocks.ENDERWOOD_PLANKS.get(), models().cubeAll("ender_planks", modLoc("block/ender_planks")));
        simpleBlock(VSBlocks.ENDERWOOD_BOOKSHELF.get(), models().cubeTop("ender_bookshelf", modLoc("block/ender_bookshelf"), modLoc("block/ender_planks")));
        stairsBlock((StairBlock) VSBlocks.ENDERWOOD_STAIRS.get(), modLoc("block/ender_planks"));
        slabBlock((SlabBlock) VSBlocks.ENDERWOOD_SLAB.get(), modLoc("block/ender_planks"), modLoc("block/ender_planks"));
        fenceBlock((FenceBlock) VSBlocks.ENDERWOOD_FENCE.get(), modLoc("block/ender_planks"));
        fenceGateBlock((FenceGateBlock) VSBlocks.ENDERWOOD_FENCE_GATE.get(), modLoc("block/ender_planks"));
        doorBlockWithRenderType((DoorBlock) VSBlocks.ENDERWOOD_DOOR.get(), modLoc("block/ender_door_bottom"), modLoc("block/ender_door_top"), "cutout");
        trapdoorBlockWithRenderType((TrapDoorBlock) VSBlocks.ENDERWOOD_TRAPDOOR.get(), modLoc("block/ender_trapdoor"), true, "cutout");
        simpleBlock(VSBlocks.ENDER_NYLIUM.get(), models().cubeBottomTop("ender_nylium", modLoc("block/ender_nylium_side"), mcLoc("block/end_stone"), modLoc("block/ender_nylium")));
        simpleBlock(VSBlocks.ENDER_ROOTS.get(), models().cross("ender_roots", modLoc("block/ender_roots")).renderType("cutout"));
        simpleBlock(VSBlocks.POTTED_ENDER_ROOTS.get(), models().withExistingParent("potted_ender_roots", modLoc("block/inventory_potted_plant")).texture("plant",  "block/ender_roots_pot").renderType("cutout"));
        simpleBlock(VSBlocks.END_SPROUTS.get(), models().cross("ender_sprouts", modLoc("block/ender_sprouts")).renderType("cutout"));
        simpleBlock(VSBlocks.ENDER_FUNGUS.get(), models().cross("ender_fungus", modLoc("block/ender_fungus")).renderType("cutout"));
        simpleBlock(VSBlocks.POTTED_ENDER_FUNGUS.get(), models().withExistingParent("potted_ender_fungus", modLoc("block/inventory_potted_plant")).texture("plant",  "block/ender_fungus").renderType("cutout"));
        simpleBlock(VSBlocks.WARPING_VINES.get(), models().cross("warping_vines", modLoc("block/warping_vines")).renderType("cutout"));
        simpleBlock(VSBlocks.WARPING_VINES_PLANT.get(), models().cross("warping_vines_plant", modLoc("block/warping_vines_plant")).renderType("cutout"));
        axisBlock((RotatedPillarBlock) VSBlocks.WITHER_BONE_BLOCK.get(), modLoc("block/wither_bone_block_side"), modLoc("block/wither_bone_block_top"));

        getVariantBuilder(VSBlocks.GOLDEN_CARROTS.get()).forAllStates(state -> {
            int cropAgeIndex = cropAgeToIndexPotato(state.getValue(BlockStateProperties.AGE_7));
            return ConfiguredModel.builder().modelFile(models().withExistingParent("golden_carrots_stage" + cropAgeIndex, modLoc("block/inventory_crop")).texture("crop", "block/golden_carrots_stage" + cropAgeIndex).renderType("cutout"))
                    .build();
        });
        getVariantBuilder(VSBlocks.WARPED_WART.get()).forAllStates(state -> {
            int cropAgeIndex = cropAgeToIndexWart(state.getValue(BlockStateProperties.AGE_3));
            return ConfiguredModel.builder().modelFile(models().crop("warped_wart_stage" + cropAgeIndex, modLoc("block/warped_wart_stage" + cropAgeIndex)).renderType("cutout")).build();
        });
        getVariantBuilder(VSBlocks.ENDER_WART.get()).forAllStates(state -> {
            int cropAgeIndex = cropAgeToIndexWart(state.getValue(BlockStateProperties.AGE_3));
            return ConfiguredModel.builder().modelFile(models().crop("ender_wart_stage" + cropAgeIndex, modLoc("block/ender_wart_stage" + cropAgeIndex)).renderType("cutout")).build();
        });
    }

    public void paintingTrapdoor(Block block, ResourceLocation painting) {
        trapdoorBlockWithRenderType((TrapDoorBlock) block, painting, true, "cutout");
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
