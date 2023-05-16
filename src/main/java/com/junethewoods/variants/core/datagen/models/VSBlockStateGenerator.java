package com.junethewoods.variants.core.datagen.models;

import com.junethewoods.variants.core.Variants;
import com.junethewoods.variants.core.init.VSBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nonnull;

import static com.junethewoods.variants.core.VSLocations.*;

public class VSBlockStateGenerator extends BlockStateProvider {
    public VSBlockStateGenerator(DataGenerator generator, ExistingFileHelper fileHelper) {
        super(generator, Variants.MOD_ID, fileHelper);
    }

    @Nonnull
    @Override
    public String getName() {
        return "Variants: Block States and Models";
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(VSBlocks.glow_black_wool.get());
        simpleBlock(VSBlocks.dark_elder_prismarine.get());
        simpleBlock(VSBlocks.elder_prismarine.get());
        simpleBlock(VSBlocks.elder_prismarine_bricks.get());
        simpleBlock(VSBlocks.elder_sea_lantern.get());
        simpleBlock(VSBlocks.smooth_purpur.get());
        simpleBlock(VSBlocks.chiseled_end_stone_bricks.get());
        simpleBlock(VSBlocks.chiseled_purpur_block.get());
        simpleBlock(VSBlocks.mossy_purpur_block.get());
        simpleBlock(VSBlocks.mossy_end_stone_bricks.get());
        simpleBlock(VSBlocks.mossy_nether_bricks.get());
        simpleBlock(VSBlocks.ender_wart_block.get());
        simpleBlock(VSBlocks.ender_planks.get());
        simpleBlock(VSBlocks.nether_coal_ore.get());
        simpleBlock(VSBlocks.ENDER_ROOTS.get(), models().cross("ender_roots", modLoc("block/ender_roots")));
        simpleBlock(VSBlocks.ENDER_FUNGUS.get(), models().cross("ender_fungus", modLoc("block/ender_fungus")));
        simpleBlock(VSBlocks.ENDER_SPROUTS.get(), models().cross("ender_sprouts", modLoc("block/ender_sprouts")));
        simpleBlock(VSBlocks.WARPING_VINES.get(), models().cross("warping_vines", modLoc("block/warping_vines")));
        simpleBlock(VSBlocks.WARPING_VINES_PLANT.get(), models().cross("warping_vines_plant", modLoc("block/warping_vines_plant")));
        simpleBlock(VSBlocks.WILD_WARPED_POTATOES.get(), models().crop("wild_warped_potatoes", modLoc("block/warped_potatoes_stage3")));
        simpleBlock(VSBlocks.WILD_CRIMSON_WHEAT.get(), models().crop("wild_crimson_wheat", modLoc("block/crimson_wheat_stage7")));
        simpleBlock(VSBlocks.WILD_SOUL_CARROTS.get(), models().crop("wild_soul_carrots", modLoc("block/soul_carrots_stage3")));
        simpleBlock(VSBlocks.POTTED_ENDER_FUNGUS.get(), models().withExistingParent("potted_ender_fungus",
                "block/flower_pot_cross").texture("plant", "block/ender_fungus"));
        simpleBlock(VSBlocks.POTTED_ENDER_ROOTS.get(), models().withExistingParent("potted_ender_roots",
                "block/flower_pot_cross").texture("plant", "block/ender_roots_pot"));
        simpleBlock(VSBlocks.POTTED_WARPED_WART.get(), models().withExistingParent("potted_warped_wart",
                "variants:block/template_potted_crop").texture("crop", "variants:block/potted_crops/warped_wart"));
        simpleBlock(VSBlocks.QUARTZ_GLASS.get());

        slabBlock((SlabBlock) VSBlocks.elder_prismarine_slab.get(), ELDER_PRISMARINE, ELDER_PRISMARINE);
        slabBlock((SlabBlock) VSBlocks.dark_elder_prismarine_slab.get(), DARK_ELDER_PRISMARINE, DARK_ELDER_PRISMARINE);
        slabBlock((SlabBlock) VSBlocks.elder_prismarine_brick_slab.get(), ELDER_PRISMARINE_BRICKS, ELDER_PRISMARINE_BRICKS);
        slabBlock((SlabBlock) VSBlocks.mossy_end_stone_brick_slab.get(), modLoc("block/mossy_end_stone_bricks"), modLoc("block/mossy_end_stone_bricks"));
        slabBlock((SlabBlock) VSBlocks.mossy_purpur_slab.get(), modLoc("block/mossy_purpur_block"), modLoc("block/mossy_purpur_block"));
        slabBlock((SlabBlock) VSBlocks.mossy_nether_brick_slab.get(), modLoc("block/mossy_nether_bricks"), modLoc("block/mossy_nether_bricks"));
        slabBlock((SlabBlock) VSBlocks.ender_slab.get(), ENDER_PLANK, ENDER_PLANK);

        stairsBlock((StairBlock) VSBlocks.elder_prismarine_brick_stairs.get(), ELDER_PRISMARINE_BRICKS);
        stairsBlock((StairBlock) VSBlocks.dark_elder_prismarine_stairs.get(), DARK_ELDER_PRISMARINE);
        stairsBlock((StairBlock) VSBlocks.elder_prismarine_stairs.get(), ELDER_PRISMARINE);
        stairsBlock((StairBlock) VSBlocks.mossy_end_stone_brick_stairs.get(), modLoc("block/mossy_end_stone_bricks"));
        stairsBlock((StairBlock) VSBlocks.mossy_nether_brick_stairs.get(), modLoc("block/mossy_nether_bricks"));
        stairsBlock((StairBlock) VSBlocks.mossy_purpur_stairs.get(), modLoc("block/mossy_purpur_block"));
        stairsBlock((StairBlock) VSBlocks.ender_stairs.get(), ENDER_PLANK);

        trapdoorBlock((TrapDoorBlock) VSBlocks.alban_trapdoor.get(), modLoc("block/alban_trapdoor"), true);
        trapdoorBlock((TrapDoorBlock) VSBlocks.aztec_trapdoor.get(), modLoc("block/aztec_trapdoor"), true);
        trapdoorBlock((TrapDoorBlock) VSBlocks.aztec2_trapdoor.get(), modLoc("block/aztec2_trapdoor"), true);
        trapdoorBlock((TrapDoorBlock) VSBlocks.bomb_trapdoor.get(), modLoc("block/bomb_trapdoor"), true);
        trapdoorBlock((TrapDoorBlock) VSBlocks.kebab_trapdoor.get(), modLoc("block/kebab_trapdoor"), true);
        trapdoorBlock((TrapDoorBlock) VSBlocks.plant_trapdoor.get(), modLoc("block/plant_trapdoor"), true);
        trapdoorBlock((TrapDoorBlock) VSBlocks.wasteland_trapdoor.get(), modLoc("block/wasteland_trapdoor"), true);
        trapdoorBlock((TrapDoorBlock) VSBlocks.PAINTING_TRAPDOOR.get(), modLoc("block/painting_trapdoor"), true);
        trapdoorBlock((TrapDoorBlock) VSBlocks.ENDER_TRAPDOOR.get(), modLoc("block/ender_trapdoor"), true);

        doorBlock((DoorBlock) VSBlocks.ENDER_DOOR.get(), modLoc("block/ender_door_bottom"), modLoc("block/ender_door_top"));
        doorBlock((DoorBlock) VSBlocks.ACACIA_TRAPDOOR_DOOR.get(), modLoc("block/acacia_trapdoor_door_bottom"), modLoc("block/acacia_trapdoor_door_top"));
        doorBlock((DoorBlock) VSBlocks.CRIMSON_TRAPDOOR_DOOR.get(), modLoc("block/crimson_trapdoor_door_bottom"), modLoc("block/crimson_trapdoor_door_top"));
        doorBlock((DoorBlock) VSBlocks.DARK_OAK_TRAPDOOR_DOOR.get(), modLoc("block/dark_oak_trapdoor_door_bottom"), modLoc("block/dark_oak_trapdoor_door_top"));
        doorBlock((DoorBlock) VSBlocks.OAK_TRAPDOOR_DOOR.get(), modLoc("block/oak_trapdoor_door_bottom"), modLoc("block/oak_trapdoor_door_top"));

        axisBlock((RotatedPillarBlock) VSBlocks.wither_bone_block.get(), modLoc("block/wither_bone_block_side"), modLoc("block/wither_bone_block_top"));
        axisBlock((RotatedPillarBlock) VSBlocks.painting_log.get(), modLoc("block/painting_log"), modLoc("block/painting_log_top"));
        axisBlock((RotatedPillarBlock) VSBlocks.painting_wood.get(), modLoc("block/painting_log"), modLoc("block/painting_log"));
        axisBlock((RotatedPillarBlock) VSBlocks.stripped_painting_log.get(), modLoc("block/stripped_painting_log"), modLoc("block/stripped_painting_log_top"));
        axisBlock((RotatedPillarBlock) VSBlocks.stripped_painting_wood.get(), modLoc("block/stripped_painting_log"), modLoc("block/stripped_painting_log"));
        axisBlock((RotatedPillarBlock) VSBlocks.end_stone_pillar.get(), modLoc("block/end_stone_pillar"), modLoc("block/end_stone_pillar_top"));
        axisBlock((RotatedPillarBlock) VSBlocks.nether_brick_pillar.get(), modLoc("block/nether_brick_pillar"), modLoc("block/nether_brick_pillar_top"));
        axisBlock((RotatedPillarBlock) VSBlocks.ender_stem.get(), modLoc("block/ender_stem"), modLoc("block/ender_stem_top"));
        axisBlock((RotatedPillarBlock) VSBlocks.stripped_ender_stem.get(), modLoc("block/stripped_ender_stem"), modLoc("block/stripped_ender_stem_top"));
        axisBlock((RotatedPillarBlock) VSBlocks.ender_hyphae.get(), modLoc("block/ender_stem"), modLoc("block/ender_stem"));
        axisBlock((RotatedPillarBlock) VSBlocks.stripped_ender_hyphae.get(), modLoc("block/stripped_ender_stem"), modLoc("block/stripped_ender_stem"));

        fenceGateBlock((FenceGateBlock) VSBlocks.ender_fence_gate.get(), ENDER_PLANK);
        fenceBlock((FenceBlock) VSBlocks.red_nether_brick_fence.get(), mcLoc("block/red_nether_bricks"));
        fenceBlock((FenceBlock) VSBlocks.ender_fence.get(), ENDER_PLANK);

        paneBlock((IronBarsBlock) VSBlocks.QUARTZ_GLASS_PANE.get(), modLoc("block/quartz_glass"), modLoc("block/quartz_glass_pane_top"));
        paneBlock((IronBarsBlock) VSBlocks.QUARTZ_BARS.get(), modLoc("block/quartz_bars"), modLoc("block/quartz_bars"));

        wallBlock((WallBlock) VSBlocks.elder_prismarine_wall.get(), ELDER_PRISMARINE);

        // 2.0.0
        doorBlock((DoorBlock) VSBlocks.PAINTING_DOOR.get(), modLoc("block/painting_door_bottom"), modLoc("block/painting_door_top"));

        getVariantBuilder(VSBlocks.WARPED_POTATOES.get()).forAllStates(state -> {
            int i = cropAgeToIndexPotato(state.getValue(CropBlock.AGE));
            return ConfiguredModel.builder().modelFile(models().crop("warped_potatoes_stage" + i, modLoc("block/warped_potatoes_stage" + i))).build();
        });
        getVariantBuilder(VSBlocks.SOUL_CARROTS.get()).forAllStates(state -> {
            int i = cropAgeToIndexPotato(state.getValue(CropBlock.AGE));
            return ConfiguredModel.builder().modelFile(models().crop("soul_carrots_stage" + i, modLoc("block/soul_carrots_stage" + i))).build();
        });
        getVariantBuilder(VSBlocks.CRIMSON_WHEAT.get()).forAllStates(state -> {
            int i = cropAgeToIndexWheat(state.getValue(CropBlock.AGE));
            return ConfiguredModel.builder().modelFile(models().crop("crimson_wheat_stage" + i, modLoc("block/crimson_wheat_stage" + i))).build();
        });
    }

    public static int cropAgeToIndexPotato(int age) {
        if (age > 6) return 3;
        if (age > 3) return 2;
        if (age > 1) return 1;
        return 0;
    }

    public static int cropAgeToIndexWheat(int age) {
        if (age == 7) return 7;
        if (age == 6) return 6;
        if (age == 5) return 5;
        if (age == 4) return 4;
        if (age == 3) return 3;
        if (age == 2) return 2;
        if (age == 1) return 1;
        return 0;
    }
}
