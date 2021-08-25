package com.junethewoods.variants.core.datagen;

import com.junethewoods.variants.core.Variants;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BowlItemModels extends ItemModelProvider {
    public BowlItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Variants.mod_id, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        ModelFile itemGenerated = getExistingFile(mcLoc("item/generated"));

        bowlBuilder(itemGenerated, "oak_beetroot_soup", "oak_bowl", "beetroot");
        bowlBuilder(itemGenerated, "spruce_beetroot_soup", "spruce_bowl", "beetroot");
        bowlBuilder(itemGenerated, "birch_beetroot_soup", "birch_bowl", "beetroot");
        bowlBuilder(itemGenerated, "jungle_beetroot_soup", "jungle_bowl", "beetroot");
        bowlBuilder(itemGenerated, "acacia_beetroot_soup", "acacia_bowl", "beetroot");
        bowlBuilder(itemGenerated, "dark_oak_beetroot_soup", "dark_oak_bowl", "beetroot");
        bowlBuilder(itemGenerated, "crimson_beetroot_soup", "crimson_bowl", "beetroot");
        bowlBuilder(itemGenerated, "warped_beetroot_soup", "warped_bowl", "beetroot");
        bowlBuilder(itemGenerated, "painting_beetroot_soup", "painting_bowl", "beetroot");
        bowlBuilder(itemGenerated, "ender_beetroot_soup", "ender_bowl", "beetroot");
        bowlBuilder(itemGenerated, "glassy_oak_beetroot_soup", "glassy_oak_bowl", "beetroot");
        bowlBuilder(itemGenerated, "plain_birch_beetroot_soup", "plain_birch_bowl", "beetroot");
        bowlBuilder(itemGenerated, "wooden_beetroot_soup", "wooden_bowl", "beetroot");
        bowlBuilder(itemGenerated, "oak_mushroom_stew", "oak_bowl", "mushroom");
        bowlBuilder(itemGenerated, "spruce_mushroom_stew", "spruce_bowl", "mushroom");
        bowlBuilder(itemGenerated, "birch_mushroom_stew", "birch_bowl", "mushroom");
        bowlBuilder(itemGenerated, "jungle_mushroom_stew", "jungle_bowl", "mushroom");
        bowlBuilder(itemGenerated, "acacia_mushroom_stew", "acacia_bowl", "mushroom");
        bowlBuilder(itemGenerated, "dark_oak_mushroom_stew", "dark_oak_bowl", "mushroom");
        bowlBuilder(itemGenerated, "crimson_mushroom_stew", "crimson_bowl", "mushroom");
        bowlBuilder(itemGenerated, "warped_mushroom_stew", "warped_bowl", "mushroom");
        bowlBuilder(itemGenerated, "painting_mushroom_stew", "painting_bowl", "mushroom");
        bowlBuilder(itemGenerated, "ender_mushroom_stew", "ender_bowl", "mushroom");
        bowlBuilder(itemGenerated, "glassy_oak_mushroom_stew", "glassy_oak_bowl", "mushroom");
        bowlBuilder(itemGenerated, "plain_birch_mushroom_stew", "plain_birch_bowl", "mushroom");
        bowlBuilder(itemGenerated, "wooden_mushroom_stew", "wooden_bowl", "mushroom");
        bowlBuilder(itemGenerated, "oak_rabbit_stew", "oak_bowl", "rabbit");
        bowlBuilder(itemGenerated, "spruce_rabbit_stew", "spruce_bowl", "rabbit");
        bowlBuilder(itemGenerated, "birch_rabbit_stew", "birch_bowl", "rabbit");
        bowlBuilder(itemGenerated, "jungle_rabbit_stew", "jungle_bowl", "rabbit");
        bowlBuilder(itemGenerated, "acacia_rabbit_stew", "acacia_bowl", "rabbit");
        bowlBuilder(itemGenerated, "dark_oak_rabbit_stew", "dark_oak_bowl", "rabbit");
        bowlBuilder(itemGenerated, "crimson_rabbit_stew", "crimson_bowl", "rabbit");
        bowlBuilder(itemGenerated, "warped_rabbit_stew", "warped_bowl", "rabbit");
        bowlBuilder(itemGenerated, "painting_rabbit_stew", "painting_bowl", "rabbit");
        bowlBuilder(itemGenerated, "ender_rabbit_stew", "ender_bowl", "rabbit");
        bowlBuilder(itemGenerated, "glassy_oak_rabbit_stew", "glassy_oak_bowl", "rabbit");
        bowlBuilder(itemGenerated, "plain_birch_rabbit_stew", "plain_birch_bowl", "rabbit");
        bowlBuilder(itemGenerated, "wooden_rabbit_stew", "wooden_bowl", "rabbit");
        bowlBuilder(itemGenerated, "oak_suspicious_stew", "oak_bowl", "suspicious");
        bowlBuilder(itemGenerated, "spruce_suspicious_stew", "spruce_bowl", "suspicious");
        bowlBuilder(itemGenerated, "birch_suspicious_stew", "birch_bowl", "suspicious");
        bowlBuilder(itemGenerated, "jungle_suspicious_stew", "jungle_bowl", "suspicious");
        bowlBuilder(itemGenerated, "acacia_suspicious_stew", "acacia_bowl", "suspicious");
        bowlBuilder(itemGenerated, "dark_oak_suspicious_stew", "dark_oak_bowl", "suspicious");
        bowlBuilder(itemGenerated, "crimson_suspicious_stew", "crimson_bowl", "suspicious");
        bowlBuilder(itemGenerated, "warped_suspicious_stew", "warped_bowl", "suspicious");
        bowlBuilder(itemGenerated, "painting_suspicious_stew", "painting_bowl", "suspicious");
        bowlBuilder(itemGenerated, "ender_suspicious_stew", "ender_bowl", "suspicious");
        bowlBuilder(itemGenerated, "glassy_oak_suspicious_stew", "glassy_oak_bowl", "suspicious");
        bowlBuilder(itemGenerated, "plain_birch_suspicious_stew", "plain_birch_bowl", "suspicious");
        bowlBuilder(itemGenerated, "wooden_suspicious_stew", "wooden_bowl", "suspicious");
        bowlBuilder(itemGenerated, "oak_fungi_stew", "oak_bowl", "fungi");
        bowlBuilder(itemGenerated, "spruce_fungi_stew", "spruce_bowl", "fungi");
        bowlBuilder(itemGenerated, "birch_fungi_stew", "birch_bowl", "fungi");
        bowlBuilder(itemGenerated, "jungle_fungi_stew", "jungle_bowl", "fungi");
        bowlBuilder(itemGenerated, "acacia_fungi_stew", "acacia_bowl", "fungi");
        bowlBuilder(itemGenerated, "dark_oak_fungi_stew", "dark_oak_bowl", "fungi");
        bowlBuilder(itemGenerated, "crimson_fungi_stew", "crimson_bowl", "fungi");
        bowlBuilder(itemGenerated, "warped_fungi_stew", "warped_bowl", "fungi");
        bowlBuilder(itemGenerated, "painting_fungi_stew", "painting_bowl", "fungi");
        bowlBuilder(itemGenerated, "ender_fungi_stew", "ender_bowl", "fungi");
        bowlBuilder(itemGenerated, "glassy_oak_fungi_stew", "glassy_oak_bowl", "fungi");
        bowlBuilder(itemGenerated, "plain_birch_fungi_stew", "plain_birch_bowl", "fungi");
        bowlBuilder(itemGenerated, "wooden_fungi_stew", "wooden_bowl", "fungi");
        bowlBuilder(itemGenerated, "oak_end_fungi_stew", "oak_bowl", "end_fungi");
        bowlBuilder(itemGenerated, "spruce_end_fungi_stew", "spruce_bowl", "end_fungi");
        bowlBuilder(itemGenerated, "birch_end_fungi_stew", "birch_bowl", "end_fungi");
        bowlBuilder(itemGenerated, "jungle_end_fungi_stew", "jungle_bowl", "end_fungi");
        bowlBuilder(itemGenerated, "acacia_end_fungi_stew", "acacia_bowl", "end_fungi");
        bowlBuilder(itemGenerated, "dark_oak_end_fungi_stew", "dark_oak_bowl", "end_fungi");
        bowlBuilder(itemGenerated, "crimson_end_fungi_stew", "crimson_bowl", "end_fungi");
        bowlBuilder(itemGenerated, "warped_end_fungi_stew", "warped_bowl", "end_fungi");
        bowlBuilder(itemGenerated, "painting_end_fungi_stew", "painting_bowl", "end_fungi");
        bowlBuilder(itemGenerated, "ender_end_fungi_stew", "ender_bowl", "end_fungi");
        bowlBuilder(itemGenerated, "glassy_oak_end_fungi_stew", "glassy_oak_bowl", "end_fungi");
        bowlBuilder(itemGenerated, "plain_birch_end_fungi_stew", "plain_birch_bowl", "end_fungi");
        bowlBuilder(itemGenerated, "wooden_end_fungi_stew", "wooden_bowl", "end_fungi");

        bowlBuilder(itemGenerated, "oak_powder_snow_stew", "oak_bowl", "powder_snow");
        bowlBuilder(itemGenerated, "spruce_powder_snow_stew", "spruce_bowl", "powder_snow");
        bowlBuilder(itemGenerated, "birch_powder_snow_stew", "birch_bowl", "powder_snow");
        bowlBuilder(itemGenerated, "jungle_powder_snow_stew", "jungle_bowl", "powder_snow");
        bowlBuilder(itemGenerated, "acacia_powder_snow_stew", "acacia_bowl", "powder_snow");
        bowlBuilder(itemGenerated, "dark_oak_powder_snow_stew", "dark_oak_bowl", "powder_snow");
        bowlBuilder(itemGenerated, "crimson_powder_snow_stew", "crimson_bowl", "powder_snow");
        bowlBuilder(itemGenerated, "warped_powder_snow_stew", "warped_bowl", "powder_snow");
        bowlBuilder(itemGenerated, "painting_powder_snow_stew", "painting_bowl", "powder_snow");
        bowlBuilder(itemGenerated, "ender_powder_snow_stew", "ender_bowl", "powder_snow");
        bowlBuilder(itemGenerated, "glassy_oak_powder_snow_stew", "glassy_oak_bowl", "powder_snow");
        bowlBuilder(itemGenerated, "plain_birch_powder_snow_stew", "plain_birch_bowl", "powder_snow");
        bowlBuilder(itemGenerated, "wooden_powder_snow_stew", "wooden_bowl", "powder_snow");

        bowlBuilder(itemGenerated, "oak_water_stew", "oak_bowl", "water");
        bowlBuilder(itemGenerated, "spruce_water_stew", "spruce_bowl", "water");
        bowlBuilder(itemGenerated, "birch_water_stew", "birch_bowl", "water");
        bowlBuilder(itemGenerated, "jungle_water_stew", "jungle_bowl", "water");
        bowlBuilder(itemGenerated, "acacia_water_stew", "acacia_bowl", "water");
        bowlBuilder(itemGenerated, "dark_oak_water_stew", "dark_oak_bowl", "water");
        bowlBuilder(itemGenerated, "crimson_water_stew", "crimson_bowl", "water");
        bowlBuilder(itemGenerated, "warped_water_stew", "warped_bowl", "water");
        bowlBuilder(itemGenerated, "painting_water_stew", "painting_bowl", "water");
        bowlBuilder(itemGenerated, "ender_water_stew", "ender_bowl", "water");
        bowlBuilder(itemGenerated, "glassy_oak_water_stew", "glassy_oak_bowl", "water");
        bowlBuilder(itemGenerated, "plain_birch_water_stew", "plain_birch_bowl", "water");
        bowlBuilder(itemGenerated, "wooden_water_stew", "wooden_bowl", "water");

        bowlBuilder(itemGenerated, "oak_lava_stew", "oak_bowl", "lava");
        bowlBuilder(itemGenerated, "spruce_lava_stew", "spruce_bowl", "lava");
        bowlBuilder(itemGenerated, "birch_lava_stew", "birch_bowl", "lava");
        bowlBuilder(itemGenerated, "jungle_lava_stew", "jungle_bowl", "lava");
        bowlBuilder(itemGenerated, "acacia_lava_stew", "acacia_bowl", "lava");
        bowlBuilder(itemGenerated, "dark_oak_lava_stew", "dark_oak_bowl", "lava");
        bowlBuilder(itemGenerated, "crimson_lava_stew", "crimson_bowl", "lava");
        bowlBuilder(itemGenerated, "warped_lava_stew", "warped_bowl", "lava");
        bowlBuilder(itemGenerated, "painting_lava_stew", "painting_bowl", "lava");
        bowlBuilder(itemGenerated, "ender_lava_stew", "ender_bowl", "lava");
        bowlBuilder(itemGenerated, "glassy_oak_lava_stew", "glassy_oak_bowl", "lava");
        bowlBuilder(itemGenerated, "plain_birch_lava_stew", "plain_birch_bowl", "lava");
        bowlBuilder(itemGenerated, "wooden_lava_stew", "wooden_bowl", "lava");

        bowlBuilder(itemGenerated, "oak_milk_stew", "oak_bowl", "milk");
        bowlBuilder(itemGenerated, "spruce_milk_stew", "spruce_bowl", "milk");
        bowlBuilder(itemGenerated, "birch_milk_stew", "birch_bowl", "milk");
        bowlBuilder(itemGenerated, "jungle_milk_stew", "jungle_bowl", "milk");
        bowlBuilder(itemGenerated, "acacia_milk_stew", "acacia_bowl", "milk");
        bowlBuilder(itemGenerated, "dark_oak_milk_stew", "dark_oak_bowl", "milk");
        bowlBuilder(itemGenerated, "crimson_milk_stew", "crimson_bowl", "milk");
        bowlBuilder(itemGenerated, "warped_milk_stew", "warped_bowl", "milk");
        bowlBuilder(itemGenerated, "painting_milk_stew", "painting_bowl", "milk");
        bowlBuilder(itemGenerated, "ender_milk_stew", "ender_bowl", "milk");
        bowlBuilder(itemGenerated, "glassy_oak_milk_stew", "glassy_oak_bowl", "milk");
        bowlBuilder(itemGenerated, "plain_birch_milk_stew", "plain_birch_bowl", "milk");
        bowlBuilder(itemGenerated, "wooden_milk_stew", "wooden_bowl", "milk");
    }

    private ItemModelBuilder bowlBuilder(ModelFile model, String name, String bowlTex, String stewTex) {
        return getBuilder(name).parent(model).texture("layer0", "item/" + bowlTex).texture("layer1", "item/" + stewTex);
    }
}
