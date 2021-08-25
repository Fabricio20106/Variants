package com.junethewoods.variants.core.datagen;

import com.junethewoods.variants.core.Variants;
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
        withExistingParent("dark_elder_prismarine", modLoc("block/dark_elder_prismarine"));
        withExistingParent("elder_prismarine", modLoc("block/elder_prismarine"));
        withExistingParent("elder_prismarine_bricks", modLoc("block/elder_prismarine_bricks"));
        withExistingParent("elder_sea_lantern", modLoc("block/elder_sea_lantern"));
        withExistingParent("azure_bluet_leaves", modLoc("block/azure_bluet_leaves"));
        withExistingParent("elder_prismarine_slab", modLoc("block/elder_prismarine_slab"));
        withExistingParent("dark_elder_prismarine_slab", modLoc("block/dark_elder_prismarine_slab"));
        withExistingParent("elder_prismarine_brick_slab", modLoc("block/elder_prismarine_brick_slab"));
        withExistingParent("elder_prismarine_stairs", modLoc("block/elder_prismarine_stairs"));
        withExistingParent("elder_prismarine_brick_stairs", modLoc("block/elder_prismarine_brick_stairs"));
        withExistingParent("dark_elder_prismarine_stairs", modLoc("block/dark_elder_prismarine_stairs"));
        withExistingParent("elder_prismarine_wall", modLoc("block/elder_prismarine_wall_inventory"));
        withExistingParent("alban_trapdoor", modLoc("block/alban_trapdoor_bottom"));
        withExistingParent("aztec_trapdoor", modLoc("block/aztec_trapdoor_bottom"));
        withExistingParent("aztec2_trapdoor", modLoc("block/aztec2_trapdoor_bottom"));
        withExistingParent("bomb_trapdoor", modLoc("block/bomb_trapdoor_bottom"));
        withExistingParent("kebab_trapdoor", modLoc("block/kebab_trapdoor_bottom"));
        withExistingParent("plant_trapdoor", modLoc("block/plant_trapdoor_bottom"));
        withExistingParent("wasteland_trapdoor", modLoc("block/wasteland_trapdoor_bottom"));
        withExistingParent("wither_bone_block", modLoc("block/wither_bone_block"));
        withExistingParent("painting_log", modLoc("block/painting_log"));
        withExistingParent("painting_wood", modLoc("block/painting_wood"));
        withExistingParent("stripped_painting_log", modLoc("block/stripped_painting_log"));
        withExistingParent("stripped_painting_wood", modLoc("block/stripped_painting_wood"));
        withExistingParent("chiseled_end_stone_bricks", modLoc("block/chiseled_end_stone_bricks"));
        withExistingParent("chiseled_purpur_block", modLoc("block/chiseled_purpur_block"));
        withExistingParent("end_stone_pillar", modLoc("block/end_stone_pillar"));
        withExistingParent("mossy_end_stone_bricks", modLoc("block/mossy_end_stone_bricks"));
        withExistingParent("mossy_nether_bricks", modLoc("block/mossy_nether_bricks"));
        withExistingParent("mossy_purpur_block", modLoc("block/mossy_purpur_block"));
        withExistingParent("nether_brick_pillar", modLoc("block/nether_brick_pillar"));
        withExistingParent("smooth_purpur", modLoc("block/smooth_purpur"));
        withExistingParent("smooth_purpur_slab", modLoc("block/smooth_purpur_slab"));
        withExistingParent("painting_leaves", modLoc("block/painting_leaves"));
        withExistingParent("mossy_end_stone_brick_slab", modLoc("block/mossy_end_stone_brick_slab"));
        withExistingParent("mossy_purpur_slab", modLoc("block/mossy_purpur_slab"));
        withExistingParent("mossy_nether_brick_slab", modLoc("block/mossy_nether_brick_slab"));
        withExistingParent("mossy_end_stone_brick_stairs", modLoc("block/mossy_end_stone_brick_stairs"));
        withExistingParent("mossy_purpur_stairs", modLoc("block/mossy_purpur_stairs"));
        withExistingParent("mossy_nether_brick_stairs", modLoc("block/mossy_nether_brick_stairs"));
        withExistingParent("red_nether_brick_fence", modLoc("block/red_nether_brick_fence_inventory"));
        withExistingParent("ender_stairs", modLoc("block/ender_stairs"));
        withExistingParent("ender_slab", modLoc("block/ender_slab"));
        withExistingParent("ender_trapdoor", modLoc("block/ender_trapdoor_bottom"));
        withExistingParent("ender_planks", modLoc("block/ender_planks"));
        withExistingParent("ender_stem", modLoc("block/ender_stem"));
        withExistingParent("ender_hyphae", modLoc("block/ender_hyphae"));
        withExistingParent("stripped_ender_stem", modLoc("block/stripped_ender_stem"));
        withExistingParent("stripped_ender_hyphae", modLoc("block/stripped_ender_hyphae"));
        withExistingParent("ender_wart_block", modLoc("block/ender_wart_block"));
        withExistingParent("ender_fence", modLoc("block/ender_fence_inventory"));
        withExistingParent("ender_fence_gate", modLoc("block/ender_fence_gate"));
        withExistingParent("potted_warped_wart", modLoc("block/potted_warped_wart"));
        withExistingParent("quartz_glass", modLoc("block/quartz_glass"));
        withExistingParent("quartz_beacon", modLoc("block/quartz_beacon"));
        withExistingParent("quartz_cauldron", modLoc("block/quartz_cauldron"));

        ModelFile itemGenerated = getExistingFile(mcLoc("item/generated"));
        ModelFile itemHandheld = getExistingFile(mcLoc("item/handheld"));
        ModelFile itemHandheldRod = getExistingFile(mcLoc("item/handheld_rod"));

        builder(itemGenerated, "music_disc_dog");
        builder(itemGenerated, "glow_black_shulker_shell");
        builder(itemGenerated, "hilary_bottle");
        builder(itemHandheld, "magma_sword");
        builder(itemGenerated, "redstone_pot");
        builder(itemGenerated, "glowstone_pot");
        builder(itemGenerated, "gunpowder_pot");
        builder(itemGenerated, "bluestone_pot");
        builder(itemGenerated, "creeper_powder_pot");
        builder(itemGenerated, "stylised_pot");
        builder(itemGenerated, "sweet_berry_pot");
        builder(itemGenerated, "glow_berry_pot");
        builder(itemGenerated, "sugar_pot");
        builder(itemGenerated, "crystallized_honey");
        builder(itemHandheld, "wither_bone");
        builder(itemGenerated, "wither_bone_meal");
        builder(itemGenerated, "emerald_helmet");
        builder(itemGenerated, "emerald_chestplate");
        builder(itemGenerated, "emerald_leggings");
        builder(itemGenerated, "emerald_boots");
        builder(itemGenerated, "copper_chestplate");
        builder(itemGenerated, "painting_sign");
        builder(itemGenerated, "mushroom_stew_bucket");
        builder(itemGenerated, "beetroot_soup_bucket");
        builder(itemGenerated, "rabbit_stew_bucket");
        builder(itemGenerated, "fungi_stew_bucket");
        builder(itemGenerated, "old_cod");
        builder(itemGenerated, "old_cooked_cod");
        builder(itemGenerated, "old_cod_bucket");
        builder(itemGenerated, "red_nether_brick");
        builder(itemGenerated, "corner_iron_tie");
        builder(itemGenerated, "iron_tie");
        builder(itemGenerated, "powered_tie");
        builder(itemGenerated, "detector_plate");
        builder(itemGenerated, "wooden_railbed");
        builder(itemGenerated, "powered_railbed");
        builder(itemGenerated, "painting_boat");
        builder(itemGenerated, "crimson_boat");
        builder(itemGenerated, "warped_boat");
        builder(itemGenerated, "oak_bowl");
        builder(itemGenerated, "spruce_bowl");
        builder(itemGenerated, "birch_bowl");
        builder(itemGenerated, "jungle_bowl");
        builder(itemGenerated, "acacia_bowl");
        builder(itemGenerated, "dark_oak_bowl");
        builder(itemGenerated, "crimson_bowl");
        builder(itemGenerated, "warped_bowl");
        builder(itemGenerated, "painting_bowl");
        builder(itemGenerated, "glassy_oak_bowl");
        builder(itemGenerated, "plain_birch_bowl");
        builder(itemGenerated, "wooden_bowl");
        builder(itemGenerated, "ender_door");
        builder(itemGenerated, "ender_sprouts");
        blockBuilder(itemGenerated, "ender_roots");
        blockBuilder(itemGenerated, "ender_fungus");
        builder(itemHandheld, "end_stone_sword");
        builder(itemHandheld, "end_stone_pickaxe");
        builder(itemHandheld, "end_stone_shovel");
        builder(itemHandheld, "end_stone_axe");
        builder(itemHandheld, "end_stone_hoe");
        builder(itemHandheld, "quartz_sword");
        builder(itemHandheld, "quartz_pickaxe");
        builder(itemHandheld, "quartz_shovel");
        builder(itemHandheld, "quartz_axe");
        builder(itemHandheld, "quartz_hoe");
        builder(itemHandheld, "ender_stick");
        builder(itemGenerated, "ender_bowl");
        builder(itemGenerated, "end_fungi_stew");
        blockBuilder(itemGenerated, "quartz_ladder");
        blockBuilder(itemGenerated, "quartz_bars");
        builder(itemGenerated, "quartz_chain");
        blockBuilder(itemGenerated, "netherrack_lever");
        blockBuilder(itemGenerated, "end_stone_lever");
        builder(itemGenerated, "end_fungi_stew_bucket");
        builder(itemGenerated, "emerald_nugget");
        builder(itemGenerated, "diamond_nugget");
        builder(itemGenerated, "quartz_nugget");
        builder(itemGenerated, "emerald_chain");
        builder(itemGenerated, "diamond_chain");
        builder(itemGenerated, "soul_lava_bucket");
        builder(itemGenerated, "soul_brewing_stand");
        builder(itemGenerated, "quartz_helmet");
        builder(itemGenerated, "quartz_chestplate");
        builder(itemGenerated, "quartz_leggings");
        builder(itemGenerated, "quartz_boots");
        builder(itemGenerated, "quartz_horse_armor");
    }

    private ItemModelBuilder builder(ModelFile itemGenerated, String name) {
        return getBuilder(name).parent(itemGenerated).texture("layer0", "item/" + name);
    }

    private ItemModelBuilder blockBuilder(ModelFile itemGenerated, String name) {
        return getBuilder(name).parent(itemGenerated).texture("layer0", "block/" + name);
    }
}
