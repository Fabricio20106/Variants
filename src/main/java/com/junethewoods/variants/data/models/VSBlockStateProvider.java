package com.junethewoods.variants.data.models;

import com.junethewoods.variants.Variants;
import com.junethewoods.variants.block.VSBlocks;
import net.minecraft.block.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;

public class VSBlockStateProvider extends BlockStateProvider {
    public VSBlockStateProvider(DataGenerator generator, ExistingFileHelper fileHelper) {
        super(generator, Variants.MOD_ID, fileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(VSBlocks.QUARTZ_ORE.get());
        simpleBlock(VSBlocks.END_QUARTZ_ORE.get());
        simpleBlock(VSBlocks.RAW_DEBRIS_BLOCK.get());
        simpleBlock(VSBlocks.GLOW_BLACK_TULIP.get(), models().cross("glow_black_tulip", modLoc("block/glow_black_tulip")));
        simpleBlock(VSBlocks.POTTED_GLOW_BLACK_TULIP.get(), models().withExistingParent("potted_glow_black_tulip", modLoc("block/flower_pot_cross_alt")).texture("plant",
                "block/glow_black_tulip"));
        simpleBlock(VSBlocks.PAINTING_PLANKS.get());
        stairsBlock((StairsBlock) VSBlocks.PAINTING_STAIRS.get(), modLoc("block/painting_planks"));
        slabBlock((SlabBlock) VSBlocks.PAINTING_SLAB.get(), modLoc("block/painting_planks"), modLoc("block/painting_planks"));
        fenceBlock((FenceBlock) VSBlocks.PAINTING_FENCE.get(), modLoc("block/painting_planks"));
        fenceGateBlock((FenceGateBlock) VSBlocks.PAINTING_FENCE_GATE.get(), modLoc("block/painting_planks"));
        doorBlock((DoorBlock) VSBlocks.PAINTING_DOOR.get(), modLoc("block/painting_door_bottom"), modLoc("block/painting_door_top"));
        doorBlock((DoorBlock) VSBlocks.WANDERER_DOOR.get(), modLoc("block/wanderer_door_bottom"), modLoc("block/wanderer_door_top"));
        trapdoorBlock((TrapDoorBlock) VSBlocks.PAINTING_TRAPDOOR.get(), modLoc("block/painting_trapdoor"), true);

        getVariantBuilder(VSBlocks.GOLDEN_CARROTS.get()).forAllStates(state -> {
            int cropAgeIndex = cropAgeToIndexPotato(state.getValue(CropsBlock.AGE));
            // return ConfiguredModel.builder().modelFile(models().crop("golden_carrots_stage" + cropAgeIndex, modLoc("block/golden_carrots_stage" + cropAgeIndex))).build();
            return ConfiguredModel.builder().modelFile(models().withExistingParent("golden_carrots_stage" + cropAgeIndex, modLoc("block/block_item_crop")).texture("crop",
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
