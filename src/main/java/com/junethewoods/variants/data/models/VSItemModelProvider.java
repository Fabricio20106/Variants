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

        block("golden_carrots", "_stage3");
        block("gold_cauldron");
        block("gold_beacon");
        block("quartz_ore");
        block("deepslate_quartz_ore");
        block("end_quartz_ore");
        block("raw_debris_block");
        block("glow_black_wool");
        block("glow_black_carpet");
        block("glow_black_stained_glass");
        block("quartz_glass");
        block("plain_birch_bookshelf");
        block("painting_log");
        block("painting_wood");
        block("stripped_painting_log");
        block("stripped_painting_wood");
        block("painting_leaves");
        block("painting_planks");
        block("painting_stairs");
        block("painting_slab");
        block("painting_fence", "_inventory");
        block("painting_fence_gate");
        block("painting_pressure_plate");
        block("painting_button", "_inventory");
        block("painting_trapdoor", "_bottom");
        block("alban_trapdoor", "_bottom");
        block("aztec_trapdoor", "_bottom");
        block("aztec2_trapdoor", "_bottom");
        block("bomb_trapdoor", "_bottom");
        block("kebab_trapdoor", "_bottom");
        block("plant_trapdoor", "_bottom");
        block("wasteland_trapdoor", "_bottom");
        block("ender_stem");
        block("ender_hyphae");
        block("stripped_ender_stem");
        block("stripped_ender_hyphae");
        block("ender_wart_block");
        block("ender_nylium");
        block("ender_farmland");
        block("ender_planks");
        block("ender_bookshelf");
        block("ender_stairs");
        block("ender_slab");
        block("ender_fence", "_inventory");
        block("ender_fence_gate");
        block("ender_pressure_plate");
        block("ender_button", "_inventory");
        block("ender_trapdoor", "_bottom");
        block("potted_glow_black_tulip");
        block("potted_sugar_cane");
        block("potted_painting_sapling");
        block("elder_sea_lantern");
        block("elder_prismarine");
        block("elder_prismarine_stairs");
        block("elder_prismarine_slab");
        block("elder_prismarine_wall", "_inventory");
        block("elder_prismarine_bricks");
        block("elder_prismarine_brick_stairs");
        block("elder_prismarine_brick_slab");
        block("dark_elder_prismarine");
        block("dark_elder_prismarine_stairs");
        block("dark_elder_prismarine_slab");
        block("chiseled_end_stone_bricks");
        block("infested_chiseled_end_stone_bricks");
        block("chiseled_purpur_block");
        block("infested_chiseled_purpur_block");
        block("red_nether_brick_fence", "_inventory");

        standard(generated, "quartz_chain");
        standard(generated, "golden_chain");
        standard(generated, "diamond_chain");
        standard(generated, "emerald_chain");
        standard(generated, "ender_sprouts");
        standard(generated, "white_shulker_shell");
        standard(generated, "inno_shulker_shell");
        standard(generated, "orange_shulker_shell");
        standard(generated, "magenta_shulker_shell");
        standard(generated, "light_blue_shulker_shell");
        standard(generated, "glow_black_shulker_shell");
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
        standard(generated, "music_disc_dog");
        standard(generated, "spawner_minecart");
        standard(generated, "fungi_stew");
        standard(generated, "enchanted_knowledge_book");
        standard(generated, "splash_experience_bottle");
        standard(generated, "lingering_experience_bottle");
        standard(generated, "splash_dragon_breath");
        standard(generated, "lingering_dragon_breath");
        standard(generated, "splash_glass_bottle");
        standard(generated, "lingering_glass_bottle");
        standard(generated, "stylised_pot");
        standard(generated, "redstone_pot");
        standard(generated, "bluestone_pot");
        standard(generated, "glowstone_pot");
        standard(generated, "gunpowder_pot");
        standard(generated, "creeper_powder_pot");
        standard(generated, "sugar_pot");
        standard(generated, "sweet_berry_pot");
        standard(generated, "glow_berry_pot");
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
        standard(handheld, "ender_stick");
        standard(handheld, "netherite_rod");
        standard(handheld, "soul_rod");
        standard(generated, "soul_powder");
        standard(generated, "soul_charge");
        standard(generated, "red_nether_brick");
        standard(generated, "exposed_copper_ingot");
        standard(generated, "weathered_copper_ingot");
        standard(generated, "oxidized_copper_ingot");
        standard(generated, "soul_lava_bucket");
        standard(generated, "milk_glass_bottle");
        standard(generated, "lava_glass_bottle");
        standard(generated, "soul_lava_bottle");
        standard(generated, "powder_snow_bottle");
        standard(generated, "glow_black_dye");
        standard(generated, "elder_prismarine_shard");
        standard(generated, "elder_prismarine_crystals");
        standard(generated, "warped_wart");
        standard(generated, "ender_wart");
        standard(generated, "painting_door");
        standard(generated, "wanderer_door");
        standard(generated, "graham_door");
        standard(generated, "first_door");
        standard(generated, "ender_door");
        standard(generated, "diamond_bell");
        standard(generated, "splash_soph_potion");
        standard(generated, "quartz_nugget");
        standard(generated, "purple_nugget");

        blockItem("glow_berry_bush", "_stage3");
        blockItem("glow_black_tulip");
        blockItem("painting_sapling");
        blockItem("netherrack_lever");
        blockItem("end_stone_lever");
        blockItem("quartz_bars");
        blockItem("ender_roots");
        blockItem("ender_fungus");
        blockItem("warping_vines", "_plant");
        glassPane("glow_black_stained_glass");
        glassPane("quartz_glass");

        standard(handheld, "magma_sword");
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
        standard(generated, "copper_shears");
        standard(generated, "exposed_copper_shears");
        standard(generated, "weathered_copper_shears");
        standard(generated, "oxidized_copper_shears");
        standard(generated, "amethyst_shears");
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

    public void standard(ModelFile parent, String name) {
        getBuilder(name).parent(parent).texture("layer0", "item/" + name);
    }

    public void toolSet(ModelFile parent, String material) {
        getBuilder(material + "_sword").parent(parent).texture("layer0", "item/" + material + "_sword");
        getBuilder(material + "_pickaxe").parent(parent).texture("layer0", "item/" + material + "_pickaxe");
        getBuilder(material + "_shovel").parent(parent).texture("layer0", "item/" + material + "_shovel");
        getBuilder(material + "_axe").parent(parent).texture("layer0", "item/" + material + "_axe");
        getBuilder(material + "_hoe").parent(parent).texture("layer0", "item/" + material + "_hoe");
    }

    public void block(String name) {
        withExistingParent(name, modLoc("block/" + name));
    }

    public void block(String name, String extras) {
        withExistingParent(name, modLoc("block/" + name + extras));
    }

    public void blockItem(String name) {
        getBuilder(name).parent(getExistingFile(mcLoc("item/generated"))).texture("layer0", "block/" + name);
    }

    public void blockItem(String name, String extras) {
        getBuilder(name).parent(getExistingFile(mcLoc("item/generated"))).texture("layer0", "block/" + name + extras);
    }

    public void glassPane(String name) {
        getBuilder(name + "_pane").parent(getExistingFile(mcLoc("item/generated"))).texture("layer0", "block/" + name);
    }
}
