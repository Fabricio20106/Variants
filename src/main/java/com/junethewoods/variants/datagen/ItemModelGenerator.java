package com.junethewoods.variants.datagen;

import com.junethewoods.variants.common.Variants;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemModelGenerator extends ItemModelProvider {
    public ItemModelGenerator(DataGenerator dataGenerator, ExistingFileHelper existingFileHelper) {
        super(dataGenerator, Variants.mod_id, existingFileHelper);
    }

    @Override
    public void registerModels() {
        withExistingParent("glow_black_wool", modLoc("block/glow_black_wool"));
        withExistingParent("glow_black_carpet", modLoc("block/glow_black_carpet"));

        ModelFile itemGenerated = getExistingFile(mcLoc("item/generated"));
        ModelFile itemHandheld = getExistingFile(mcLoc("item/handheld"));

        builder(itemGenerated, "music_disc_dog");
        builder(itemGenerated, "glow_black_shulker_shell");
        builder(itemGenerated, "hilary_bottle");
        builder(itemHandheld, "magma_sword");
    }

    private ItemModelBuilder builder(ModelFile itemGenerated, String name) {
        return getBuilder(name).parent(itemGenerated).texture("layer0", "item/" + name);
    }
}
