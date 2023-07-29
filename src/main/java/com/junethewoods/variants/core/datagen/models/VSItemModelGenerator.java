package com.junethewoods.variants.core.datagen.models;

import com.junethewoods.variants.core.Variants;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nonnull;

public class VSItemModelGenerator extends ItemModelProvider {
    public VSItemModelGenerator(DataGenerator generator, ExistingFileHelper fileHelper) {
        super(generator, Variants.MOD_ID, fileHelper);
    }

    @Nonnull
    @Override
    public String getName() {
        return "Variants: Item Models";
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
        withExistingParent("crimson_farmland", modLoc("block/crimson_farmland"));
        withExistingParent("warped_farmland", modLoc("block/warped_farmland"));
        withExistingParent("ender_farmland", modLoc("block/ender_farmland"));
        withExistingParent("nether_coal_ore", modLoc("block/nether_coal_ore"));

        ModelFile generated = getExistingFile(mcLoc("item/generated"));
        ModelFile handheld = getExistingFile(mcLoc("item/handheld"));
        ModelFile handheldRod = getExistingFile(mcLoc("item/handheld_rod"));

        standard(generated, "music_disc_dog");
        standard(generated, "glow_black_shulker_shell");
//        standard(generated, "hilary_bottle");
        standard(handheld, "magma_sword");
        standard(generated, "redstone_pot");
        standard(generated, "glowstone_pot");
        standard(generated, "gunpowder_pot");
//        standard(generated, "bluestone_pot");
//        standard(generated, "creeper_powder_pot");
        standard(generated, "stylised_pot");
        standard(generated, "sweet_berry_pot");
        standard(generated, "glow_berry_pot");
        standard(generated, "sugar_pot");
        standard(generated, "crystallized_honey");
        standard(handheld, "wither_bone");
        standard(generated, "wither_bone_meal");
        standard(generated, "emerald_helmet");
        standard(generated, "emerald_chestplate");
        standard(generated, "emerald_leggings");
        standard(generated, "emerald_boots");
        standard(generated, "copper_chestplate");
        standard(generated, "painting_sign");
        standard(generated, "mushroom_stew_bucket");
        standard(generated, "beetroot_soup_bucket");
        standard(generated, "rabbit_stew_bucket");
        standard(generated, "fungi_stew_bucket");
        standard(generated, "old_cod");
        standard(generated, "old_cooked_cod");
        standard(generated, "old_cod_bucket");
        standard(generated, "red_nether_brick");
        standard(generated, "corner_iron_tie");
        standard(generated, "iron_tie");
        standard(generated, "powered_tie");
        standard(generated, "detector_plate");
        standard(generated, "wooden_railbed");
        standard(generated, "powered_railbed");
        standard(generated, "painting_boat");
        standard(generated, "crimson_boat");
        standard(generated, "warped_boat");
        standard(generated, "oak_bowl");
        standard(generated, "spruce_bowl");
        standard(generated, "birch_bowl");
        standard(generated, "jungle_bowl");
        standard(generated, "acacia_bowl");
        standard(generated, "dark_oak_bowl");
        standard(generated, "crimson_bowl");
        standard(generated, "warped_bowl");
        standard(generated, "painting_bowl");
//        standard(generated, "glassy_oak_bowl");
//        standard(generated, "plain_birch_bowl");
//        standard(generated, "wooden_bowl");
        standard(generated, "ender_door");
        standard(generated, "ender_sprouts");
        block(generated, "ender_roots");
        block(generated, "ender_fungus");
        standard(handheld, "end_stone_sword");
        standard(handheld, "end_stone_pickaxe");
        standard(handheld, "end_stone_shovel");
        standard(handheld, "end_stone_axe");
        standard(handheld, "end_stone_hoe");
        standard(handheld, "quartz_sword");
        standard(handheld, "quartz_pickaxe");
        standard(handheld, "quartz_shovel");
        standard(handheld, "quartz_axe");
        standard(handheld, "quartz_hoe");
        standard(handheld, "ender_stick");
        standard(generated, "ender_bowl");
        standard(generated, "end_fungi_stew");
        block(generated, "quartz_ladder");
        block(generated, "quartz_bars");
        standard(generated, "quartz_chain");
        block(generated, "netherrack_lever");
        block(generated, "end_stone_lever");
        standard(generated, "end_fungi_stew_bucket");
        standard(generated, "emerald_nugget");
        standard(generated, "diamond_nugget");
        standard(generated, "quartz_nugget");
        standard(generated, "emerald_chain");
        standard(generated, "diamond_chain");
        standard(generated, "soul_lava_bucket");
        standard(generated, "soul_brewing_stand");
        standard(generated, "quartz_helmet");
        standard(generated, "quartz_chestplate");
        standard(generated, "quartz_leggings");
        standard(generated, "quartz_boots");
        standard(generated, "quartz_horse_armor");
        standard(generated, "oak_trapdoor_door");
        standard(generated, "dark_oak_trapdoor_door");
        standard(generated, "crimson_trapdoor_door");
        standard(generated, "acacia_trapdoor_door");
        standard(generated, "warped_potato");
        standard(generated, "baked_warped_potato");
        standard(generated, "withered_warped_potato");
        standard(generated, "crimson_wheat");
        standard(generated, "crimson_wheat_seeds");
        standard(generated, "crimson_bread");
//        standard(generated, "golden_bucket");
//        standard(generated, "golden_lava_bucket");
//        standard(generated, "golden_soul_lava_bucket");
//        standard(generated, "mushroom_stew_creeper_bucket");
//        standard(generated, "beetroot_soup_creeper_bucket");
//        standard(generated, "rabbit_stew_creeper_bucket");
//        standard(generated, "fungi_stew_creeper_bucket");
//        standard(generated, "end_fungi_stew_creeper_bucket");
//        standard(generated, "creeper_soul_lava_bucket");
        standard(generated, "soul_carrot");
        standard(handheldRod, "soul_carrot_on_a_stick");

        // 1.18.2-2.0.0
        getBuilder("warping_vines").parent(generated).texture("layer0", "block/warping_vines_plant");
        standard(generated, "copper_helmet");
        standard(generated, "copper_leggings");
        standard(generated, "copper_boots");
        standard(generated, "phantom_membrane_helmet");
        standard(generated, "phantom_membrane_leggings");
        standard(generated, "phantom_membrane_boots");
        standard(generated, "rabbit_hide_helmet");
        standard(generated, "rabbit_hide_leggings");
        standard(generated, "rabbit_hide_boots");
        standard(generated, "wool_helmet");
        standard(generated, "wool_leggings");
        standard(generated, "wool_boots");
        standard(generated, "empty_slot_ingot");

        // Tools
        standard(handheld, "deepslate_sword");
        standard(handheld, "deepslate_pickaxe");
        standard(handheld, "deepslate_shovel");
        standard(handheld, "deepslate_axe");
        standard(handheld, "deepslate_hoe");
        standard(handheld, "sandstone_sword");
        standard(handheld, "sandstone_pickaxe");
        standard(handheld, "sandstone_shovel");
        standard(handheld, "sandstone_axe");
        standard(handheld, "sandstone_hoe");
        standard(handheld, "red_sandstone_sword");
        standard(handheld, "red_sandstone_pickaxe");
        standard(handheld, "red_sandstone_shovel");
        standard(handheld, "red_sandstone_axe");
        standard(handheld, "red_sandstone_hoe");
        standard(handheld, "tuff_sword");
        standard(handheld, "tuff_pickaxe");
        standard(handheld, "tuff_shovel");
        standard(handheld, "tuff_axe");
        standard(handheld, "tuff_hoe");
        standard(handheld, "calcite_sword");
        standard(handheld, "calcite_pickaxe");
        standard(handheld, "calcite_shovel");
        standard(handheld, "calcite_axe");
        standard(handheld, "calcite_hoe");
        standard(handheld, "bedrock_sword");
        standard(handheld, "bedrock_pickaxe");
        standard(handheld, "bedrock_shovel");
        standard(handheld, "bedrock_axe");
        standard(handheld, "bedrock_hoe");
        standard(handheld, "netherrack_sword");
        standard(handheld, "netherrack_pickaxe");
        standard(handheld, "netherrack_shovel");
        standard(handheld, "netherrack_axe");
        standard(handheld, "netherrack_hoe");
        standard(handheld, "blackstone_sword");
        standard(handheld, "blackstone_pickaxe");
        standard(handheld, "blackstone_shovel");
        standard(handheld, "blackstone_axe");
        standard(handheld, "blackstone_hoe");
        standard(handheld, "basalt_sword");
        standard(handheld, "basalt_pickaxe");
        standard(handheld, "basalt_shovel");
        standard(handheld, "basalt_axe");
        standard(handheld, "basalt_hoe");
        standard(handheld, "magma_pickaxe");
        standard(handheld, "magma_shovel");
        standard(handheld, "magma_axe");
        standard(handheld, "magma_hoe");
        standard(handheld, "obsidian_sword");
        standard(handheld, "obsidian_pickaxe");
        standard(handheld, "obsidian_shovel");
        standard(handheld, "obsidian_axe");
        standard(handheld, "obsidian_hoe");
        standard(handheld, "crying_obsidian_sword");
        standard(handheld, "crying_obsidian_pickaxe");
        standard(handheld, "crying_obsidian_shovel");
        standard(handheld, "crying_obsidian_axe");
        standard(handheld, "crying_obsidian_hoe");
        toolSet("redstone");
        toolSet("prismarine_crystal");
        toolSet("coal");
        toolSet("copper");
        toolSet("amethyst");
        toolSet("elder_prismarine_crystal");
        toolSet("empty_slot");

        // Wood Tools
        toolSet("oak");
        toolSet("spruce");
        toolSet("birch");
        toolSet("jungle");
        toolSet("acacia");
        toolSet("dark_oak");
        toolSet("mangrove");
        standard(handheld, "bamboo_shovel");
        toolSet("cherry");
        toolSet("painting");
        toolSet("crimson");
        toolSet("warped");
        toolSet("ender");
    }

    private ItemModelBuilder standard(ModelFile generated, String name) {
        return getBuilder(name).parent(generated).texture("layer0", "item/" + name);
    }

    private ItemModelBuilder block(ModelFile generated, String name) {
        return getBuilder(name).parent(generated).texture("layer0", "block/" + name);
    }

    private ItemModelBuilder toolSet(String materialName) {
        getBuilder(materialName + "_sword").parent(getExistingFile(mcLoc("item/handheld"))).texture("layer0", "item/" + materialName + "_sword");
        getBuilder(materialName + "_pickaxe").parent(getExistingFile(mcLoc("item/handheld"))).texture("layer0", "item/" + materialName + "_pickaxe");
        getBuilder(materialName + "_shovel").parent(getExistingFile(mcLoc("item/handheld"))).texture("layer0", "item/" + materialName + "_shovel");
        getBuilder(materialName + "_axe").parent(getExistingFile(mcLoc("item/handheld"))).texture("layer0", "item/" + materialName + "_axe");
        return getBuilder(materialName + "_hoe").parent(getExistingFile(mcLoc("item/handheld"))).texture("layer0", "item/" + materialName + "_hoe");
    }
}
