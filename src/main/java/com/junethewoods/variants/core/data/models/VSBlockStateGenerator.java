package com.junethewoods.variants.core.data.models;

import com.junethewoods.variants.common.block.VSCraftingTableBlock;
import com.junethewoods.variants.core.Variants;
import com.junethewoods.variants.core.init.VSBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nonnull;

import static com.junethewoods.variants.core.util.VSLocations.*;

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
        simpleBlock(VSBlocks.GLOW_BLACK_WOOL.get());
        simpleBlock(VSBlocks.DARK_ELDER_PRISMARINE.get());
        simpleBlock(VSBlocks.ELDER_PRISMARINE.get());
        simpleBlock(VSBlocks.ELDER_PRISMARINE_BRICKS.get());
        simpleBlock(VSBlocks.ELDER_SEA_LANTERN.get());
        simpleBlock(VSBlocks.SMOOTH_PURPUR.get());
        simpleBlock(VSBlocks.CHISELED_END_STONE_BRICKS.get());
        simpleBlock(VSBlocks.CHISELED_PURPUR_BLOCK.get());
        simpleBlock(VSBlocks.MOSSY_PURPUR_BLOCK.get());
        simpleBlock(VSBlocks.MOSSY_END_STONE_BRICKS.get());
        simpleBlock(VSBlocks.MOSSY_NETHER_BRICKS.get());
        simpleBlock(VSBlocks.ENDER_WART_BLOCK.get());
        simpleBlock(VSBlocks.ENDER_PLANKS.get());
        simpleBlock(VSBlocks.NETHER_COAL_ORE.get());
        simpleBlock(VSBlocks.ENDER_ROOTS.get(), models().cross("ender_roots", modLoc("block/ender_roots")));
        simpleBlock(VSBlocks.ENDER_FUNGUS.get(), models().cross("ender_fungus", modLoc("block/ender_fungus")));
        simpleBlock(VSBlocks.ENDER_SPROUTS.get(), models().cross("ender_sprouts", modLoc("block/ender_sprouts")));
        simpleBlock(VSBlocks.WARPING_VINES.get(), models().cross("warping_vines", modLoc("block/warping_vines")));
        simpleBlock(VSBlocks.WARPING_VINES_PLANT.get(), models().cross("warping_vines_plant", modLoc("block/warping_vines_plant")));
        simpleBlock(VSBlocks.WILD_WARPED_POTATOES.get(), models().crop("wild_warped_potatoes", modLoc("block/warped_potatoes_stage3")));
        simpleBlock(VSBlocks.WILD_CRIMSON_WHEAT.get(), models().crop("wild_crimson_wheat", modLoc("block/crimson_wheat_stage7")));
        simpleBlock(VSBlocks.WILD_SOUL_CARROTS.get(), models().crop("wild_soul_carrots", modLoc("block/soul_carrots_stage3")));
        simpleBlock(VSBlocks.POTTED_ENDER_FUNGUS.get(), models().withExistingParent("potted_ender_fungus", "variants:block/flower_pot_cross_alt").texture("plant", "block/ender_fungus"));
        simpleBlock(VSBlocks.POTTED_ENDER_ROOTS.get(), models().withExistingParent("potted_ender_roots", "variants:block/flower_pot_cross_alt").texture("plant", "block/ender_roots_pot"));
        simpleBlock(VSBlocks.POTTED_WARPED_WART.get(), models().withExistingParent("potted_warped_wart", "variants:block/template_potted_crop").texture("crop", "variants:block/potted_crops/warped_wart"));
        simpleBlock(VSBlocks.QUARTZ_GLASS.get());

        slabBlock((SlabBlock) VSBlocks.ELDER_PRISMARINE_SLAB.get(), ELDER_PRISMARINE, ELDER_PRISMARINE);
        slabBlock((SlabBlock) VSBlocks.DARK_ELDER_PRISMARINE_SLAB.get(), DARK_ELDER_PRISMARINE, DARK_ELDER_PRISMARINE);
        slabBlock((SlabBlock) VSBlocks.ELDER_PRISMARINE_BRICK_SLAB.get(), ELDER_PRISMARINE_BRICKS, ELDER_PRISMARINE_BRICKS);
        slabBlock((SlabBlock) VSBlocks.MOSSY_END_STONE_BRICK_SLAB.get(), modLoc("block/mossy_end_stone_bricks"), modLoc("block/mossy_end_stone_bricks"));
        slabBlock((SlabBlock) VSBlocks.MOSSY_PURPUR_SLAB.get(), modLoc("block/mossy_purpur_block"), modLoc("block/mossy_purpur_block"));
        slabBlock((SlabBlock) VSBlocks.MOSSY_NETHER_BRICK_SLAB.get(), modLoc("block/mossy_nether_bricks"), modLoc("block/mossy_nether_bricks"));
        slabBlock((SlabBlock) VSBlocks.ENDER_SLAB.get(), ENDER_PLANKS, ENDER_PLANKS);

        stairsBlock((StairBlock) VSBlocks.ELDER_PRISMARINE_BRICK_STAIRS.get(), ELDER_PRISMARINE_BRICKS);
        stairsBlock((StairBlock) VSBlocks.DARK_ELDER_PRISMARINE_STAIRS.get(), DARK_ELDER_PRISMARINE);
        stairsBlock((StairBlock) VSBlocks.ELDER_PRISMARINE_STAIRS.get(), ELDER_PRISMARINE);
        stairsBlock((StairBlock) VSBlocks.MOSSY_END_STONE_BRICK_STAIRS.get(), modLoc("block/mossy_end_stone_bricks"));
        stairsBlock((StairBlock) VSBlocks.MOSSY_NETHER_BRICK_STAIRS.get(), modLoc("block/mossy_nether_bricks"));
        stairsBlock((StairBlock) VSBlocks.MOSSY_PURPUR_STAIRS.get(), modLoc("block/mossy_purpur_block"));
        stairsBlock((StairBlock) VSBlocks.ENDER_STAIRS.get(), ENDER_PLANKS);

        trapdoorBlock((TrapDoorBlock) VSBlocks.ALBAN_TRAPDOOR.get(), modLoc("block/alban_trapdoor"), true);
        trapdoorBlock((TrapDoorBlock) VSBlocks.AZTEC_TRAPDOOR.get(), modLoc("block/aztec_trapdoor"), true);
        trapdoorBlock((TrapDoorBlock) VSBlocks.AZTEC2_TRAPDOOR.get(), modLoc("block/aztec2_trapdoor"), true);
        trapdoorBlock((TrapDoorBlock) VSBlocks.BOMB_TRAPDOOR.get(), modLoc("block/bomb_trapdoor"), true);
        trapdoorBlock((TrapDoorBlock) VSBlocks.KEBAB_TRAPDOOR.get(), modLoc("block/kebab_trapdoor"), true);
        trapdoorBlock((TrapDoorBlock) VSBlocks.PLANT_TRAPDOOR.get(), modLoc("block/plant_trapdoor"), true);
        trapdoorBlock((TrapDoorBlock) VSBlocks.WASTELAND_TRAPDOOR.get(), modLoc("block/wasteland_trapdoor"), true);
        trapdoorBlock((TrapDoorBlock) VSBlocks.PAINTING_TRAPDOOR.get(), modLoc("block/painting_trapdoor"), true);
        trapdoorBlock((TrapDoorBlock) VSBlocks.ENDER_TRAPDOOR.get(), modLoc("block/ender_trapdoor"), true);

        doorBlock((DoorBlock) VSBlocks.ENDER_DOOR.get(), modLoc("block/ender_door_bottom"), modLoc("block/ender_door_top"));
        doorBlock((DoorBlock) VSBlocks.ACACIA_TRAPDOOR_DOOR.get(), modLoc("block/acacia_trapdoor_door_bottom"), modLoc("block/acacia_trapdoor_door_top"));
        doorBlock((DoorBlock) VSBlocks.CRIMSON_TRAPDOOR_DOOR.get(), modLoc("block/crimson_trapdoor_door_bottom"), modLoc("block/crimson_trapdoor_door_top"));
        doorBlock((DoorBlock) VSBlocks.DARK_OAK_TRAPDOOR_DOOR.get(), modLoc("block/dark_oak_trapdoor_door_bottom"), modLoc("block/dark_oak_trapdoor_door_top"));
        doorBlock((DoorBlock) VSBlocks.OAK_TRAPDOOR_DOOR.get(), modLoc("block/oak_trapdoor_door_bottom"), modLoc("block/oak_trapdoor_door_top"));

        axisBlock((RotatedPillarBlock) VSBlocks.WITHER_BONE_BLOCK.get(), modLoc("block/wither_bone_block_side"), modLoc("block/wither_bone_block_top"));
        axisBlock((RotatedPillarBlock) VSBlocks.PAINTING_LOG.get(), modLoc("block/painting_log"), modLoc("block/painting_log_top"));
        axisBlock((RotatedPillarBlock) VSBlocks.PAINTING_WOOD.get(), modLoc("block/painting_log"), modLoc("block/painting_log"));
        axisBlock((RotatedPillarBlock) VSBlocks.STRIPPED_PAINTING_LOG.get(), modLoc("block/stripped_painting_log"), modLoc("block/stripped_painting_log_top"));
        axisBlock((RotatedPillarBlock) VSBlocks.STRIPPED_PAINTING_WOOD.get(), modLoc("block/stripped_painting_log"), modLoc("block/stripped_painting_log"));
        axisBlock((RotatedPillarBlock) VSBlocks.END_STONE_PILLAR.get(), modLoc("block/end_stone_pillar"), modLoc("block/end_stone_pillar_top"));
        axisBlock((RotatedPillarBlock) VSBlocks.NETHER_BRICK_PILLAR.get(), modLoc("block/nether_brick_pillar"), modLoc("block/nether_brick_pillar_top"));
        axisBlock((RotatedPillarBlock) VSBlocks.ENDER_STEM.get(), modLoc("block/ender_stem"), modLoc("block/ender_stem_top"));
        axisBlock((RotatedPillarBlock) VSBlocks.STRIPPED_ENDER_STEM.get(), modLoc("block/stripped_ender_stem"), modLoc("block/stripped_ender_stem_top"));
        axisBlock((RotatedPillarBlock) VSBlocks.ENDER_HYPHAE.get(), modLoc("block/ender_stem"), modLoc("block/ender_stem"));
        axisBlock((RotatedPillarBlock) VSBlocks.STRIPPED_ENDER_HYPHAE.get(), modLoc("block/stripped_ender_stem"), modLoc("block/stripped_ender_stem"));

        fenceGateBlock((FenceGateBlock) VSBlocks.ENDER_FENCE_GATE.get(), ENDER_PLANKS);
        fenceBlock((FenceBlock) VSBlocks.RED_NETHER_BRICK_FENCE.get(), mcLoc("block/red_nether_bricks"));
        fenceBlock((FenceBlock) VSBlocks.ENDER_FENCE.get(), ENDER_PLANKS);

        paneBlock((IronBarsBlock) VSBlocks.QUARTZ_GLASS_PANE.get(), modLoc("block/quartz_glass"), modLoc("block/quartz_glass_pane_top"));
        paneBlock((IronBarsBlock) VSBlocks.QUARTZ_BARS.get(), modLoc("block/quartz_bars"), modLoc("block/quartz_bars"));

        wallBlock((WallBlock) VSBlocks.ELDER_PRISMARINE_WALL.get(), ELDER_PRISMARINE);

        // 2.0.0
        doorBlock((DoorBlock) VSBlocks.PAINTING_DOOR.get(), modLoc("block/painting_door_bottom"), modLoc("block/painting_door_top"));
        craftingTableBlock((VSCraftingTableBlock) VSBlocks.SPRUCE_CRAFTING_TABLE.get(), "spruce");
        craftingTableBlock((VSCraftingTableBlock) VSBlocks.BIRCH_CRAFTING_TABLE.get(), "birch");
        craftingTableBlock((VSCraftingTableBlock) VSBlocks.JUNGLE_CRAFTING_TABLE.get(), "jungle");
        craftingTableBlock((VSCraftingTableBlock) VSBlocks.ACACIA_CRAFTING_TABLE.get(), "acacia");
        craftingTableBlock((VSCraftingTableBlock) VSBlocks.DARK_OAK_CRAFTING_TABLE.get(), "dark_oak");
        craftingTableBlock((VSCraftingTableBlock) VSBlocks.PAINTING_CRAFTING_TABLE.get(), "painting", Variants.MOD_ID);
        craftingTableBlock((VSCraftingTableBlock) VSBlocks.CRIMSON_CRAFTING_TABLE.get(), "crimson");
        craftingTableBlock((VSCraftingTableBlock) VSBlocks.WARPED_CRAFTING_TABLE.get(), "warped");
        craftingTableBlock((VSCraftingTableBlock) VSBlocks.ENDER_CRAFTING_TABLE.get(), "ender", Variants.MOD_ID);

        simpleBlock(VSBlocks.POTTED_GRASS.get(), models().withExistingParent("potted_grass", "variants:block/template_potted_crop").texture("crop", "block/grass_pot"));

        getVariantBuilder(VSBlocks.WARPED_POTATOES.get()).forAllStates(state -> {
            int ageIndex = cropAgeToIndexPotato(state.getValue(CropBlock.AGE));
            return ConfiguredModel.builder().modelFile(models().crop("warped_potatoes_stage" + ageIndex, modLoc("block/warped_potatoes_stage" + ageIndex))).build();
        });
        getVariantBuilder(VSBlocks.SOUL_CARROTS.get()).forAllStates(state -> {
            int ageIndex = cropAgeToIndexPotato(state.getValue(CropBlock.AGE));
            return ConfiguredModel.builder().modelFile(models().crop("soul_carrots_stage" + ageIndex, modLoc("block/soul_carrots_stage" + ageIndex))).build();
        });
        getVariantBuilder(VSBlocks.CRIMSON_WHEAT.get()).forAllStates(state -> {
            int ageIndex = cropAgeToIndexWheat(state.getValue(CropBlock.AGE));
            return ConfiguredModel.builder().modelFile(models().crop("crimson_wheat_stage" + ageIndex, modLoc("block/crimson_wheat_stage" + ageIndex))).build();
        });
    }

    public void craftingTableBlock(VSCraftingTableBlock block, String woodType) {
        simpleBlock(block, models().withExistingParent(woodType + "_crafting_table", "block/cube")
                .texture("north", modLoc("block/" + woodType + "_crafting_table_front"))
                .texture("south", modLoc("block/" + woodType + "_crafting_table_front"))
                .texture("east", modLoc("block/" + woodType + "_crafting_table_side"))
                .texture("west", modLoc("block/" + woodType + "_crafting_table_side"))
                .texture("up", modLoc("block/" + woodType + "_crafting_table_top"))
                .texture("down", mcLoc("block/" + woodType + "_planks"))
                .texture("particle", modLoc("block/" + woodType + "_crafting_table_front")));
    }

    public void craftingTableBlock(VSCraftingTableBlock block, String woodType, String modId) {
        simpleBlock(block, models().withExistingParent(woodType + "_crafting_table", mcLoc("block/cube"))
                .texture("north", modLoc("block/" + woodType + "_crafting_table_front"))
                .texture("south", modLoc("block/" + woodType + "_crafting_table_front"))
                .texture("east", modLoc("block/" + woodType + "_crafting_table_side"))
                .texture("west", modLoc("block/" + woodType + "_crafting_table_side"))
                .texture("up", modLoc("block/" + woodType + "_crafting_table_top"))
                .texture("down", modId + ":block/" + woodType + "_planks")
                .texture("particle", modLoc("block/" + woodType + "_crafting_table_front")));
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
