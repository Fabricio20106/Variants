package melonystudios.variants.data.model;

import melonystudios.variants.Variants;
import net.minecraft.block.*;
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

    public void wheat(RegistryObject<Block> block) {
        String cropName = block.get().getRegistryName().getPath();
        getVariantBuilder(block.get()).forAllStates(state -> {
            int cropAge = state.getValue(BlockStateProperties.AGE_7);
            return ConfiguredModel.builder().modelFile(models().withExistingParent(cropName + "_stage" + cropAge, modLoc("block/inventory_crop"))
                    .texture("crop", modLoc("block/" + cropName + "_stage" + cropAge))).build();
        });
    }

    public void carrots(RegistryObject<Block> block) {
        String cropName = block.get().getRegistryName().getPath();
        getVariantBuilder(block.get()).forAllStates(state -> {
            int cropAge = cropAgeToIndexSeven(state.getValue(BlockStateProperties.AGE_7));
            return ConfiguredModel.builder().modelFile(models().withExistingParent(cropName + "_stage" + cropAge, modLoc("block/inventory_crop"))
                    .texture("crop", modLoc("block/" + cropName + "_stage" + cropAge))).build();
        });
    }

    public void wart(RegistryObject<Block> block) {
        String cropName = block.get().getRegistryName().getPath();
        getVariantBuilder(block.get()).forAllStates(state -> {
            int cropAge = cropAgeToIndexWart(state.getValue(BlockStateProperties.AGE_3));
            return ConfiguredModel.builder().modelFile(models().crop(cropName + "_stage" + cropAge, modLoc("block/" +
                    cropName + "_stage" + cropAge))).build();
        });
    }

    public void wildCrop(RegistryObject<Block> block) {
        String cropName = block.get().getRegistryName().getPath();
        simpleBlock(block.get(), models().withExistingParent(cropName, modLoc("block/template_wild_crop")).texture("crop", modLoc("block/" + cropName)));
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
            return ConfiguredModel.builder().modelFile(models().getBuilder(pressurePlate.getRegistryName().getPath() + (isPowered ? "_down" : ""))
                    .parent(models().getExistingFile(mcLoc("block/pressure_plate_" + (isPowered ? "down" : "up")))).texture("texture", planks)).build();
        });
    }

    public void buttonBlock(AbstractButtonBlock block, ResourceLocation texture) {
        ModelFile button = button(block.getRegistryName().getPath(), texture);
        ModelFile buttonPressed = buttonPressed(block.getRegistryName().getPath() + "_pressed", texture);
        this.buttonBlock(block, button, buttonPressed);
        models().withExistingParent(block.getRegistryName().getPath() + "_inventory", mcLoc("block/button_inventory")).texture("texture", texture);
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

    public void cauldron(Block cauldron) {
        getVariantBuilder(cauldron).forAllStatesExcept(state -> {
            int level = state.getValue(BlockStateProperties.LEVEL_CAULDRON);
            String name = cauldron.getRegistryName().getPath();
            ModelFile emptyCauldron = models().withExistingParent(name, modLoc("block/template_cauldron")).texture("side", modLoc("block/" + name + "_side")).texture("inside", modLoc("block/" + name + "_inner")).texture("bottom", modLoc("block/" + name + "_bottom"))
                    .texture("top", modLoc("block/" + name + "_top"));

            return ConfiguredModel.builder().modelFile(level == 0 ? emptyCauldron : models().withExistingParent(name + "_level" + level, modLoc("block/template_cauldron_level" + level)).texture("side", modLoc("block/" + name + "_side")).texture("inside", modLoc("block/" + name + "_inner"))
                    .texture("bottom", modLoc("block/" + name + "_bottom")).texture("top", modLoc("block/" + name + "_top")).texture("contents", mcLoc("block/water_still"))).build();
        }, BlockStateProperties.WATERLOGGED);
    }

    public void chain(Block chain) {
        getVariantBuilder(chain).forAllStatesExcept(state -> {
            Direction.Axis axis = state.getValue(ChainBlock.AXIS);

            return ConfiguredModel.builder().modelFile(models().withExistingParent(chain.getRegistryName().getPath(), modLoc("block/template_chain")).texture("chain", modLoc("block/" + chain.getRegistryName().getPath()))).rotationX(axis == Direction.Axis.X ||
                    axis == Direction.Axis.Z ? 90 : 0).rotationY(axis == Direction.Axis.X ? 90 : 0).build();
        }, BlockStateProperties.WATERLOGGED);
    }

    public void fenceBlock(Block fence, ResourceLocation texture) {
        fenceBlock((FenceBlock) fence, texture);
        assert fence.getRegistryName() != null;
        models().withExistingParent(fence.getRegistryName().getPath() + "_inventory", mcLoc("block/fence_inventory")).texture("texture", texture);
    }

    public void wallBlock(Block wall, ResourceLocation texture) {
        wallBlock((WallBlock) wall, texture);
        assert wall.getRegistryName() != null;
        models().withExistingParent(wall.getRegistryName().getPath() + "_inventory", mcLoc("block/wall_inventory")).texture("wall", texture);
    }
}
