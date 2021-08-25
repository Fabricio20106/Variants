package com.junethewoods.variants.core.datagen;

import com.junethewoods.variants.core.Variants;
import com.junethewoods.variants.core.init.BlockInit;
import net.minecraft.block.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockStateGenerator extends BlockStateProvider {
    public BlockStateGenerator(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, Variants.mod_id, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        ResourceLocation elder_prismarine = modLoc("block/elder_prismarine");
        ResourceLocation elder_prismarine_bricks = modLoc("block/elder_prismarine_bricks");
        ResourceLocation dark_elder_prismarine = modLoc("block/dark_elder_prismarine");
        ResourceLocation ender_plank = modLoc("block/ender_planks");

        simpleBlock(BlockInit.glow_black_wool.get());
        simpleBlock(BlockInit.dark_elder_prismarine.get());
        simpleBlock(BlockInit.elder_prismarine.get());
        simpleBlock(BlockInit.elder_prismarine_bricks.get());
        simpleBlock(BlockInit.elder_sea_lantern.get());
        slabBlock((SlabBlock) BlockInit.elder_prismarine_slab.get(), elder_prismarine, elder_prismarine);
        slabBlock((SlabBlock) BlockInit.dark_elder_prismarine_slab.get(), dark_elder_prismarine, dark_elder_prismarine);
        slabBlock((SlabBlock) BlockInit.elder_prismarine_brick_slab.get(), elder_prismarine_bricks, elder_prismarine_bricks);
        stairsBlock((StairsBlock) BlockInit.elder_prismarine_brick_stairs.get(), elder_prismarine_bricks);
        stairsBlock((StairsBlock) BlockInit.dark_elder_prismarine_stairs.get(), dark_elder_prismarine);
        stairsBlock((StairsBlock) BlockInit.elder_prismarine_stairs.get(), elder_prismarine);
        wallBlock((WallBlock) BlockInit.elder_prismarine_wall.get(), elder_prismarine);
        trapdoorBlock((TrapDoorBlock) BlockInit.alban_trapdoor.get(), modLoc("block/alban_trapdoor"), true);
        trapdoorBlock((TrapDoorBlock) BlockInit.aztec_trapdoor.get(), modLoc("block/aztec_trapdoor"), true);
        trapdoorBlock((TrapDoorBlock) BlockInit.aztec2_trapdoor.get(), modLoc("block/aztec2_trapdoor"), true);
        trapdoorBlock((TrapDoorBlock) BlockInit.bomb_trapdoor.get(), modLoc("block/bomb_trapdoor"), true);
        trapdoorBlock((TrapDoorBlock) BlockInit.kebab_trapdoor.get(), modLoc("block/kebab_trapdoor"), true);
        trapdoorBlock((TrapDoorBlock) BlockInit.plant_trapdoor.get(), modLoc("block/plant_trapdoor"), true);
        trapdoorBlock((TrapDoorBlock) BlockInit.wasteland_trapdoor.get(), modLoc("block/wasteland_trapdoor"), true);
        trapdoorBlock((TrapDoorBlock) BlockInit.painting_trapdoor.get(), modLoc("block/painting_trapdoor"), true);
        axisBlock((RotatedPillarBlock) BlockInit.wither_bone_block.get(), modLoc("block/wither_bone_block_side"), modLoc("block/wither_bone_block_top"));
        axisBlock((RotatedPillarBlock) BlockInit.painting_log.get(), modLoc("block/painting_log"), modLoc("block/painting_log_top"));
        axisBlock((RotatedPillarBlock) BlockInit.painting_wood.get(), modLoc("block/painting_log"), modLoc("block/painting_log"));
        axisBlock((RotatedPillarBlock) BlockInit.stripped_painting_log.get(), modLoc("block/stripped_painting_log"), modLoc("block/stripped_painting_log_top"));
        axisBlock((RotatedPillarBlock) BlockInit.stripped_painting_wood.get(), modLoc("block/stripped_painting_log"), modLoc("block/stripped_painting_log"));
        simpleBlock(BlockInit.smooth_purpur.get());
        simpleBlock(BlockInit.chiseled_end_stone_bricks.get());
        simpleBlock(BlockInit.chiseled_purpur_block.get());
        simpleBlock(BlockInit.mossy_purpur_block.get());
        simpleBlock(BlockInit.mossy_end_stone_bricks.get());
        simpleBlock(BlockInit.mossy_nether_bricks.get());
        axisBlock((RotatedPillarBlock) BlockInit.end_stone_pillar.get(), modLoc("block/end_stone_pillar"), modLoc("block/end_stone_pillar_top"));
        axisBlock((RotatedPillarBlock) BlockInit.nether_brick_pillar.get(), modLoc("block/nether_brick_pillar"), modLoc("block/nether_brick_pillar_top"));
        stairsBlock((StairsBlock) BlockInit.mossy_end_stone_brick_stairs.get(), modLoc("block/mossy_end_stone_bricks"));
        stairsBlock((StairsBlock) BlockInit.mossy_nether_brick_stairs.get(), modLoc("block/mossy_nether_bricks"));
        stairsBlock((StairsBlock) BlockInit.mossy_purpur_stairs.get(), modLoc("block/mossy_purpur_block"));
        slabBlock((SlabBlock) BlockInit.mossy_end_stone_brick_slab.get(), modLoc("block/mossy_end_stone_bricks"), modLoc("block/mossy_end_stone_bricks"));
        slabBlock((SlabBlock) BlockInit.mossy_purpur_slab.get(), modLoc("block/mossy_purpur_block"), modLoc("block/mossy_purpur_block"));
        slabBlock((SlabBlock) BlockInit.mossy_nether_brick_slab.get(), modLoc("block/mossy_nether_bricks"), modLoc("block/mossy_nether_bricks"));
        fenceBlock((FenceBlock) BlockInit.red_nether_brick_fence.get(), mcLoc("block/red_nether_bricks"));
        simpleBlock(BlockInit.ender_wart_block.get());
        simpleBlock(BlockInit.ender_planks.get());
        axisBlock((RotatedPillarBlock) BlockInit.ender_stem.get(), modLoc("block/ender_stem"), modLoc("block/ender_stem_top"));
        axisBlock((RotatedPillarBlock) BlockInit.stripped_ender_stem.get(), modLoc("block/stripped_ender_stem"), modLoc("block/stripped_ender_stem_top"));
        axisBlock((RotatedPillarBlock) BlockInit.ender_hyphae.get(), modLoc("block/ender_stem"), modLoc("block/ender_stem"));
        axisBlock((RotatedPillarBlock) BlockInit.stripped_ender_hyphae.get(), modLoc("block/stripped_ender_stem"), modLoc("block/stripped_ender_stem"));
        slabBlock((SlabBlock) BlockInit.ender_slab.get(), ender_plank, ender_plank);
        stairsBlock((StairsBlock) BlockInit.ender_stairs.get(), ender_plank);
        trapdoorBlock((TrapDoorBlock) BlockInit.ender_trapdoor.get(), modLoc("block/ender_trapdoor"), true);
        doorBlock((DoorBlock) BlockInit.ender_door.get(), modLoc("block/ender_door_bottom"), modLoc("block/ender_door_top"));
        fenceBlock((FenceBlock) BlockInit.ender_fence.get(), ender_plank);
        fenceGateBlock((FenceGateBlock) BlockInit.ender_fence_gate.get(), ender_plank);
        simpleBlock(BlockInit.ender_roots.get(), models().cross("ender_roots", modLoc("block/ender_roots")));
        simpleBlock(BlockInit.ender_fungus.get(), models().cross("ender_fungus", modLoc("block/ender_fungus")));
        simpleBlock(BlockInit.ender_sprouts.get(), models().cross("ender_sprouts", modLoc("block/ender_sprouts")));
        simpleBlock(BlockInit.warping_vines.get(), models().cross("warping_vines", modLoc("block/warping_vines")));
        simpleBlock(BlockInit.warping_vines_plant.get(), models().cross("warping_vines_plant", modLoc("block/warping_vines_plant")));
        simpleBlock(BlockInit.potted_ender_fungus.get(), models().withExistingParent("potted_ender_fungus",
                "block/flower_pot_cross").texture("plant", "block/ender_fungus"));
        simpleBlock(BlockInit.potted_ender_roots.get(), models().withExistingParent("potted_ender_roots",
                "block/flower_pot_cross").texture("plant", "block/ender_roots_pot"));
        simpleBlock(BlockInit.potted_warped_wart.get(), models().withExistingParent("potted_warped_wart",
                "variants:block/potted_crop").texture("crop", "variants:block/potted_crops/warped_wart"));
        simpleBlock(BlockInit.quartz_glass.get());
        paneBlock((PaneBlock) BlockInit.quartz_glass_pane.get(), modLoc("block/quartz_glass"), modLoc("block/quartz_glass_pane_top"));
        paneBlock((PaneBlock) BlockInit.quartz_bars.get(), modLoc("block/quartz_bars"), modLoc("block/quartz_bars"));
    }
}
