package com.junethewoods.variants.data.models;

import com.junethewoods.variants.Variants;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nonnull;

public class VSItemModelProvider extends ItemModelProvider {
    public VSItemModelProvider(DataGenerator generator, ExistingFileHelper fileHelper) {
        super(generator, Variants.MOD_ID, fileHelper);
    }

    @Nonnull
    @Override
    public String getName() {
        return "Variants - Item Models";
    }

    @Override
    protected void registerModels() {
        ModelFile generated = getExistingFile(mcLoc("item/generated"));
        ModelFile handheld = getExistingFile(mcLoc("item/handheld"));
        ModelFile debugBow = getExistingFile(modLoc("item/debug_bow"));

        withExistingParent("golden_carrots", modLoc("block/golden_carrots_stage3"));
        withExistingParent("gold_cauldron");
        withExistingParent("gold_beacon");
        withExistingParent("quartz_ore");
        withExistingParent("end_quartz_ore");
        withExistingParent("raw_debris_block");
        withExistingParent("plain_birch_bookshelf");
        withExistingParent("painting_planks");
        withExistingParent("painting_stairs");
        withExistingParent("painting_slab");
        withExistingParent("painting_fence", modLoc("block/painting_fence_inventory"));
        withExistingParent("painting_fence_gate");
        withExistingParent("painting_pressure_plate");
        withExistingParent("painting_button", modLoc("block/painting_button_inventory"));
        withExistingParent("painting_trapdoor", modLoc("block/painting_trapdoor_bottom"));
        withExistingParent("potted_glow_black_tulip");
        withExistingParent("potted_sugar_cane");

        standard(generated, "white_shulker_shell");
        standard(generated, "inno_shulker_shell");
        standard(generated, "orange_shulker_shell");
        standard(generated, "magenta_shulker_shell");
        standard(generated, "light_blue_shulker_shell");
        standard(generated, "yellow_shulker_shell");
        standard(generated, "lime_shulker_shell");
        standard(generated, "pink_shulker_shell");
        standard(generated, "gray_shulker_shell");
        standard(generated, "light_gray_shulker_shell");
        standard(generated, "cyan_shulker_shell");
        standard(generated, "purple_shulker_shell");
        standard(generated, "blue_shulker_shell");
        standard(generated, "brown_shulker_shell");
        standard(generated, "green_shulker_shell");
        standard(generated, "red_shulker_shell");
        standard(generated, "black_shulker_shell");
        standard(generated, "spawner_minecart");
        standard(generated, "fungi_stew");
        standard(generated, "enchanted_knowledge_book");
        standard(generated, "splash_experience_bottle");
        standard(generated, "lingering_experience_bottle");
        standard(generated, "splash_dragon_breath");
        standard(generated, "lingering_dragon_breath");
        standard(generated, "splash_glass_bottle");
        standard(generated, "lingering_glass_bottle");
        standard(generated, "honey_ball");
        standard(generated, "raw_debris");
        standard(handheld, "oak_stick");
        standard(handheld, "spruce_stick");
        standard(handheld, "birch_stick");
        standard(handheld, "jungle_stick");
        standard(handheld, "acacia_stick");
        standard(handheld, "dark_oak_stick");
        standard(handheld, "painting_stick");
        standard(handheld, "crimson_stick");
        standard(handheld, "warped_stick");
        standard(handheld, "netherite_rod");
        standard(handheld, "soul_rod");
        standard(generated, "soul_powder");
        standard(generated, "soul_charge");
        standard(generated, "exposed_copper_ingot");
        standard(generated, "weathered_copper_ingot");
        standard(generated, "oxidized_copper_ingot");
        standard(generated, "milk_glass_bottle");
        standard(generated, "lava_glass_bottle");
        standard(generated, "powder_snow_bottle");
        standard(generated, "glow_black_dye");
        standard(generated, "elder_prismarine_shard");
        standard(generated, "elder_prismarine_crystals");
        standard(generated, "warped_wart");
        standard(generated, "painting_door");
        standard(generated, "wanderer_door");
        standard(generated, "graham_door");
        standard(generated, "first_door");
        standard(generated, "splash_soph_potion");
        standard(generated, "purple_nugget");
        getBuilder("glow_berry_bush").parent(generated).texture("layer0", "block/glow_berry_bush_stage3");
        getBuilder("glow_black_tulip").parent(generated).texture("layer0", "block/glow_black_tulip");

        standard(handheld, "amethyst_sword");
        standard(handheld, "copper_sword");
        standard(handheld, "magma_sword");
        standard(generated, "phantom_membrane_sweatchest");
        standard(generated, "rabbit_hide_sweatchest");
        standard(generated, "wool_sweatchest");
        standard(generated, "empty_armor_slot_helmet");
        standard(generated, "empty_armor_slot_chestplate");
        standard(generated, "empty_armor_slot_leggings");
        standard(generated, "empty_armor_slot_boots");
        standard(generated, "empty_armor_slot_shield");

        standard(debugBow, "debug_bow_pulling_0");
        standard(debugBow, "debug_bow_pulling_1");
        standard(debugBow, "debug_bow_pulling_2");

        // Shears
        standard(generated, "coal_shears");
        standard(generated, "golden_shears");
        standard(generated, "diamond_shears");
        standard(generated, "netherite_shears");
        standard(generated, "redstone_shears");
        standard(generated, "lapis_lazuli_shears");
        standard(generated, "emerald_shears");
        standard(generated, "quartz_shears");
        standard(generated, "ruby_shears");
        standard(generated, "diaemerald_shears");
        standard(generated, "crystal_shears");
        standard(generated, "plasteel_shears");
        standard(generated, "light_magenta_shears");
        standard(generated, "alan_ai_shears");
        standard(generated, "alice_ai_shears");
        standard(generated, "inno_ai_shears");
        standard(generated, "nicolas_ai_shears");

        toolSet(handheld, "andesite");
        toolSet(handheld, "granite");
        toolSet(handheld, "diorite");
    }

    public ItemModelBuilder standard(ModelFile parent, String name) {
        return getBuilder(name).parent(parent).texture("layer0", "item/" + name);
    }

    public ItemModelBuilder toolSet(ModelFile parent, String material) {
        getBuilder(material + "_sword").parent(parent).texture("layer0", "item/" + material + "_sword");
        getBuilder(material + "_pickaxe").parent(parent).texture("layer0", "item/" + material + "_pickaxe");
        getBuilder(material + "_shovel").parent(parent).texture("layer0", "item/" + material + "_shovel");
        getBuilder(material + "_axe").parent(parent).texture("layer0", "item/" + material + "_axe");
        return getBuilder(material + "_hoe").parent(parent).texture("layer0", "item/" + material + "_hoe");
    }

    public ItemModelBuilder withExistingParent(String name) {
        return withExistingParent(name, modLoc("block/" + name));
    }
}
