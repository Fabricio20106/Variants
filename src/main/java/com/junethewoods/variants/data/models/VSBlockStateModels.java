package com.junethewoods.variants.data.models;

import com.junethewoods.variants.Variants;
import net.minecraft.block.AbstractButtonBlock;
import net.minecraft.block.Block;
import net.minecraft.block.TrapDoorBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.state.properties.AttachFace;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.RegistryObject;

public abstract class VSBlockStateModels extends BlockStateProvider {
    public VSBlockStateModels(DataGenerator generator, ExistingFileHelper fileHelper) {
        super(generator, Variants.MOD_ID, fileHelper);
    }

    public void paintingTrapdoor(RegistryObject<Block> block, ResourceLocation painting) {
        trapdoorBlock((TrapDoorBlock) block.get(), painting, true);
    }

    public static int cropAgeToIndexSeven(int age) {
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

    public static int cropAgeToIndexBush(int age) {
        if (age == 3) return 3;
        if (age == 2) return 2;
        if (age == 1) return 1;
        return 0;
    }

    public static String moistIndex(int moistLevel) {
        if (moistLevel != 7) return "";
        return "_moist";
    }

    public void pressurePlate(Block pressurePlate, ResourceLocation planks) {
        getVariantBuilder(pressurePlate).forAllStates(state -> {
            boolean isPowered = state.getValue(BlockStateProperties.POWERED);
            return ConfiguredModel.builder().modelFile(models().getBuilder(pressurePlate.getRegistryName().getPath() + (isPowered ? "_powered" : ""))
                    .parent(models().getExistingFile(mcLoc("block/pressure_plate_" + (isPowered ? "down" : "up")))).texture("texture", planks)).build();
        });
    }

    public void buttonBlock(AbstractButtonBlock block, ResourceLocation texture) {
        ModelFile button = button(block.getRegistryName().getPath(), texture);
        ModelFile buttonPressed = buttonPressed(block.getRegistryName().getPath() + "_pressed", texture);
        this.buttonBlock(block, button, buttonPressed);
    }

    public void buttonBlock(AbstractButtonBlock block, ModelFile button, ModelFile buttonPressed) {
        this.getVariantBuilder(block).forAllStates((state) -> {
            Direction facing = state.getValue(AbstractButtonBlock.FACING);
            AttachFace face = state.getValue(AbstractButtonBlock.FACE);
            boolean powered = state.getValue(AbstractButtonBlock.POWERED);
            return ConfiguredModel.builder().modelFile(powered ? buttonPressed : button).rotationX(face == AttachFace.FLOOR ? 0 : (face == AttachFace.WALL ? 90 : 180)).rotationY((int)(face == AttachFace.CEILING ? facing : facing.getOpposite()).toYRot()).uvLock(face == AttachFace.WALL).build();
        });
    }

    public ModelFile button(String name, ResourceLocation texture) {
        return models().singleTexture(name, mcLoc("block/button"), texture);
    }

    public ModelFile buttonPressed(String name, ResourceLocation texture) {
        return models().singleTexture(name, mcLoc("block/button_pressed"), texture);
    }
}
