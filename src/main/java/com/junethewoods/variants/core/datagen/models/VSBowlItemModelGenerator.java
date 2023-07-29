package com.junethewoods.variants.core.datagen.models;

import com.junethewoods.variants.core.Variants;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nonnull;

public class VSBowlItemModelGenerator extends ItemModelProvider {
    public VSBowlItemModelGenerator(DataGenerator generator, ExistingFileHelper fileHelper) {
        super(generator, Variants.MOD_ID, fileHelper);
    }

    @Nonnull
    @Override
    public String getName() {
        return "Variants: Bowl Exponentiality (Item Models)";
    }

    @Override
    protected void registerModels() {
        ModelFile generated = getExistingFile(mcLoc("item/generated"));

        standard(generated, "oak_beetroot_soup", "oak_bowl", "beetroot");
        standard(generated, "spruce_beetroot_soup", "spruce_bowl", "beetroot");
        standard(generated, "birch_beetroot_soup", "birch_bowl", "beetroot");
        standard(generated, "jungle_beetroot_soup", "jungle_bowl", "beetroot");
        standard(generated, "acacia_beetroot_soup", "acacia_bowl", "beetroot");
        standard(generated, "dark_oak_beetroot_soup", "dark_oak_bowl", "beetroot");
        standard(generated, "crimson_beetroot_soup", "crimson_bowl", "beetroot");
        standard(generated, "warped_beetroot_soup", "warped_bowl", "beetroot");
        standard(generated, "painting_beetroot_soup", "painting_bowl", "beetroot");
        standard(generated, "ender_beetroot_soup", "ender_bowl", "beetroot");
//        standard(generated, "glassy_oak_beetroot_soup", "glassy_oak_bowl", "beetroot");
//        standard(generated, "plain_birch_beetroot_soup", "plain_birch_bowl", "beetroot");
//        standard(generated, "wooden_beetroot_soup", "wooden_bowl", "beetroot");
        standard(generated, "oak_mushroom_stew", "oak_bowl", "mushroom");
        standard(generated, "spruce_mushroom_stew", "spruce_bowl", "mushroom");
        standard(generated, "birch_mushroom_stew", "birch_bowl", "mushroom");
        standard(generated, "jungle_mushroom_stew", "jungle_bowl", "mushroom");
        standard(generated, "acacia_mushroom_stew", "acacia_bowl", "mushroom");
        standard(generated, "dark_oak_mushroom_stew", "dark_oak_bowl", "mushroom");
        standard(generated, "crimson_mushroom_stew", "crimson_bowl", "mushroom");
        standard(generated, "warped_mushroom_stew", "warped_bowl", "mushroom");
        standard(generated, "painting_mushroom_stew", "painting_bowl", "mushroom");
        standard(generated, "ender_mushroom_stew", "ender_bowl", "mushroom");
//        standard(generated, "glassy_oak_mushroom_stew", "glassy_oak_bowl", "mushroom");
//        standard(generated, "plain_birch_mushroom_stew", "plain_birch_bowl", "mushroom");
//        standard(generated, "wooden_mushroom_stew", "wooden_bowl", "mushroom");
        standard(generated, "oak_rabbit_stew", "oak_bowl", "rabbit");
        standard(generated, "spruce_rabbit_stew", "spruce_bowl", "rabbit");
        standard(generated, "birch_rabbit_stew", "birch_bowl", "rabbit");
        standard(generated, "jungle_rabbit_stew", "jungle_bowl", "rabbit");
        standard(generated, "acacia_rabbit_stew", "acacia_bowl", "rabbit");
        standard(generated, "dark_oak_rabbit_stew", "dark_oak_bowl", "rabbit");
        standard(generated, "crimson_rabbit_stew", "crimson_bowl", "rabbit");
        standard(generated, "warped_rabbit_stew", "warped_bowl", "rabbit");
        standard(generated, "painting_rabbit_stew", "painting_bowl", "rabbit");
        standard(generated, "ender_rabbit_stew", "ender_bowl", "rabbit");
//        standard(generated, "glassy_oak_rabbit_stew", "glassy_oak_bowl", "rabbit");
//        standard(generated, "plain_birch_rabbit_stew", "plain_birch_bowl", "rabbit");
//        standard(generated, "wooden_rabbit_stew", "wooden_bowl", "rabbit");
        standard(generated, "oak_suspicious_stew", "oak_bowl", "suspicious");
        standard(generated, "spruce_suspicious_stew", "spruce_bowl", "suspicious");
        standard(generated, "birch_suspicious_stew", "birch_bowl", "suspicious");
        standard(generated, "jungle_suspicious_stew", "jungle_bowl", "suspicious");
        standard(generated, "acacia_suspicious_stew", "acacia_bowl", "suspicious");
        standard(generated, "dark_oak_suspicious_stew", "dark_oak_bowl", "suspicious");
        standard(generated, "crimson_suspicious_stew", "crimson_bowl", "suspicious");
        standard(generated, "warped_suspicious_stew", "warped_bowl", "suspicious");
        standard(generated, "painting_suspicious_stew", "painting_bowl", "suspicious");
        standard(generated, "ender_suspicious_stew", "ender_bowl", "suspicious");
//        standard(generated, "glassy_oak_suspicious_stew", "glassy_oak_bowl", "suspicious");
//        standard(generated, "plain_birch_suspicious_stew", "plain_birch_bowl", "suspicious");
//        standard(generated, "wooden_suspicious_stew", "wooden_bowl", "suspicious");
        standard(generated, "oak_fungi_stew", "oak_bowl", "fungi");
        standard(generated, "spruce_fungi_stew", "spruce_bowl", "fungi");
        standard(generated, "birch_fungi_stew", "birch_bowl", "fungi");
        standard(generated, "jungle_fungi_stew", "jungle_bowl", "fungi");
        standard(generated, "acacia_fungi_stew", "acacia_bowl", "fungi");
        standard(generated, "dark_oak_fungi_stew", "dark_oak_bowl", "fungi");
        standard(generated, "crimson_fungi_stew", "crimson_bowl", "fungi");
        standard(generated, "warped_fungi_stew", "warped_bowl", "fungi");
        standard(generated, "painting_fungi_stew", "painting_bowl", "fungi");
        standard(generated, "ender_fungi_stew", "ender_bowl", "fungi");
//        standard(generated, "glassy_oak_fungi_stew", "glassy_oak_bowl", "fungi");
//        standard(generated, "plain_birch_fungi_stew", "plain_birch_bowl", "fungi");
//        standard(generated, "wooden_fungi_stew", "wooden_bowl", "fungi");
        standard(generated, "oak_end_fungi_stew", "oak_bowl", "end_fungi");
        standard(generated, "spruce_end_fungi_stew", "spruce_bowl", "end_fungi");
        standard(generated, "birch_end_fungi_stew", "birch_bowl", "end_fungi");
        standard(generated, "jungle_end_fungi_stew", "jungle_bowl", "end_fungi");
        standard(generated, "acacia_end_fungi_stew", "acacia_bowl", "end_fungi");
        standard(generated, "dark_oak_end_fungi_stew", "dark_oak_bowl", "end_fungi");
        standard(generated, "crimson_end_fungi_stew", "crimson_bowl", "end_fungi");
        standard(generated, "warped_end_fungi_stew", "warped_bowl", "end_fungi");
        standard(generated, "painting_end_fungi_stew", "painting_bowl", "end_fungi");
        standard(generated, "ender_end_fungi_stew", "ender_bowl", "end_fungi");
//        standard(generated, "glassy_oak_end_fungi_stew", "glassy_oak_bowl", "end_fungi");
//        standard(generated, "plain_birch_end_fungi_stew", "plain_birch_bowl", "end_fungi");
//        standard(generated, "wooden_end_fungi_stew", "wooden_bowl", "end_fungi");

        standard(generated, "oak_powder_snow_stew", "oak_bowl", "powder_snow");
        standard(generated, "spruce_powder_snow_stew", "spruce_bowl", "powder_snow");
        standard(generated, "birch_powder_snow_stew", "birch_bowl", "powder_snow");
        standard(generated, "jungle_powder_snow_stew", "jungle_bowl", "powder_snow");
        standard(generated, "acacia_powder_snow_stew", "acacia_bowl", "powder_snow");
        standard(generated, "dark_oak_powder_snow_stew", "dark_oak_bowl", "powder_snow");
        standard(generated, "crimson_powder_snow_stew", "crimson_bowl", "powder_snow");
        standard(generated, "warped_powder_snow_stew", "warped_bowl", "powder_snow");
        standard(generated, "painting_powder_snow_stew", "painting_bowl", "powder_snow");
        standard(generated, "ender_powder_snow_stew", "ender_bowl", "powder_snow");
//        standard(generated, "glassy_oak_powder_snow_stew", "glassy_oak_bowl", "powder_snow");
//        standard(generated, "plain_birch_powder_snow_stew", "plain_birch_bowl", "powder_snow");
//        standard(generated, "wooden_powder_snow_stew", "wooden_bowl", "powder_snow");

        standard(generated, "oak_water_stew", "oak_bowl", "water");
        standard(generated, "spruce_water_stew", "spruce_bowl", "water");
        standard(generated, "birch_water_stew", "birch_bowl", "water");
        standard(generated, "jungle_water_stew", "jungle_bowl", "water");
        standard(generated, "acacia_water_stew", "acacia_bowl", "water");
        standard(generated, "dark_oak_water_stew", "dark_oak_bowl", "water");
        standard(generated, "crimson_water_stew", "crimson_bowl", "water");
        standard(generated, "warped_water_stew", "warped_bowl", "water");
        standard(generated, "painting_water_stew", "painting_bowl", "water");
        standard(generated, "ender_water_stew", "ender_bowl", "water");
//        standard(generated, "glassy_oak_water_stew", "glassy_oak_bowl", "water");
//        standard(generated, "plain_birch_water_stew", "plain_birch_bowl", "water");
//        standard(generated, "wooden_water_stew", "wooden_bowl", "water");

        standard(generated, "oak_lava_stew", "oak_bowl", "lava");
        standard(generated, "spruce_lava_stew", "spruce_bowl", "lava");
        standard(generated, "birch_lava_stew", "birch_bowl", "lava");
        standard(generated, "jungle_lava_stew", "jungle_bowl", "lava");
        standard(generated, "acacia_lava_stew", "acacia_bowl", "lava");
        standard(generated, "dark_oak_lava_stew", "dark_oak_bowl", "lava");
        standard(generated, "crimson_lava_stew", "crimson_bowl", "lava");
        standard(generated, "warped_lava_stew", "warped_bowl", "lava");
        standard(generated, "painting_lava_stew", "painting_bowl", "lava");
        standard(generated, "ender_lava_stew", "ender_bowl", "lava");
//        standard(generated, "glassy_oak_lava_stew", "glassy_oak_bowl", "lava");
//        standard(generated, "plain_birch_lava_stew", "plain_birch_bowl", "lava");
//        standard(generated, "wooden_lava_stew", "wooden_bowl", "lava");

        standard(generated, "oak_milk_stew", "oak_bowl", "milk");
        standard(generated, "spruce_milk_stew", "spruce_bowl", "milk");
        standard(generated, "birch_milk_stew", "birch_bowl", "milk");
        standard(generated, "jungle_milk_stew", "jungle_bowl", "milk");
        standard(generated, "acacia_milk_stew", "acacia_bowl", "milk");
        standard(generated, "dark_oak_milk_stew", "dark_oak_bowl", "milk");
        standard(generated, "crimson_milk_stew", "crimson_bowl", "milk");
        standard(generated, "warped_milk_stew", "warped_bowl", "milk");
        standard(generated, "painting_milk_stew", "painting_bowl", "milk");
        standard(generated, "ender_milk_stew", "ender_bowl", "milk");
//        standard(generated, "glassy_oak_milk_stew", "glassy_oak_bowl", "milk");
//        standard(generated, "plain_birch_milk_stew", "plain_birch_bowl", "milk");
//        standard(generated, "wooden_milk_stew", "wooden_bowl", "milk");
    }

    private ItemModelBuilder standard(ModelFile model, String name, String bowl, String stew) {
        return getBuilder(name).parent(model).texture("layer0", "item/" + bowl).texture("layer1", "item/" + stew);
    }
}
