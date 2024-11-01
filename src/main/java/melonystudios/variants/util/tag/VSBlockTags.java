package melonystudios.variants.util.tag;

import melonystudios.variants.Variants;
import net.minecraft.block.Block;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;

public class VSBlockTags {
    // Forge Tags
    public static final ITag.INamedTag<Block> STORAGE_BLOCKS_NETHERITE_SCRAP = forge("storage_blocks/netherite_scrap");

    public static final ITag.INamedTag<Block> GLASS_GLOW_BLACK = forge("glass/glow_black");
    public static final ITag.INamedTag<Block> GLASS_PANES_GLOW_BLACK = forge("glass_panes/glow_black");

    // Minecraft World generation-Related Tags
    public static final ITag.INamedTag<Block> PLANTS_PLACEABLE_ON = worldGeneration("plants_placeable_on");

    // Variants' Tags
    public static final ITag.INamedTag<Block> PAINTING_DOORS = mod("painting_doors");
    public static final ITag.INamedTag<Block> PAINTING_TRAPDOORS = mod("painting_trapdoors");
    public static final ITag.INamedTag<Block> PAINTING_LOGS = mod("painting_logs");
    public static final ITag.INamedTag<Block> ENDERWOOD_STEMS = mod("enderwood_stems");
    public static final ITag.INamedTag<Block> BOOKSHELVES = mod("bookshelves");
    public static final ITag.INamedTag<Block> CAULDRONS = mod("cauldrons");
    public static final ITag.INamedTag<Block> BEACONS = mod("beacons");
    public static final ITag.INamedTag<Block> SPAWNERS = mod("spawners");
    public static final ITag.INamedTag<Block> SPAWNER_MINECART_CANNOT_REPLACE = mod("spawner_minecart_cannot_replace");
    public static final ITag.INamedTag<Block> NETHER_WART_PLACEABLE_ON = mod("nether_wart_plantable_on");
    public static final ITag.INamedTag<Block> ENDER_WART_PLANTABLE_ON = mod("ender_wart_plantable_on");
    public static final ITag.INamedTag<Block> GOLDEN_CARROTS_PLANTABLE_ON = mod("golden_carrots_plantable_on");
    public static final ITag.INamedTag<Block> END_PLANTS_PLANTABLE_ON = mod("end_plants_plantable_on");
    public static final ITag.INamedTag<Block> WARPING_VINES_CAN_PLACE_ON = mod("warping_vines_feature_can_place_on");
    public static final ITag.INamedTag<Block> CHORUS_FLOWER_PLANTABLE_ON = mod("chorus_flower_plantable_on");
    public static final ITag.INamedTag<Block> CHORUS_PLANT_PLANTABLE_ON = mod("chorus_plant_plantable_on");
    public static final ITag.INamedTag<Block> NETHER_CROPS_PLANTABLE_ON = mod("nether_crops_plantable_on");
    public static final ITag.INamedTag<Block> END_CARVER_REPLACEABLES = mod("end_carver_replaceables");
    public static final ITag.INamedTag<Block> HAS_ENDER_NYLIUM = mod("has_ender_nylium");

    // Melony (convention) Tags
    public static final ITag.INamedTag<Block> DEEPSLATE_REPLACEABLES = melony("deepslate_replaceables");
    public static final ITag.INamedTag<Block> FARMLAND = melony("farmland");
    public static final ITag.INamedTag<Block> NATURAL_FARMLAND = melony("natural_farmland");
    public static final ITag.INamedTag<Block> FARMLAND_TRANSPARENT = melony("farmland_transparent");
    public static final ITag.INamedTag<Block> CONDUIT_FRAME_BLOCKS = melony("conduit_frame_blocks");
    public static final ITag.INamedTag<Block> COMPLETES_FIND_TREE_TUTORIAL = melony("completes_find_tree_tutorial");

    public static ITag.INamedTag<Block> forge(String name) {
        return BlockTags.bind(new ResourceLocation("forge", name).toString());
    }

    public static ITag.INamedTag<Block> melony(String name) {
        return BlockTags.bind(new ResourceLocation("melony", name).toString());
    }

    public static ITag.INamedTag<Block> worldGeneration(String name) {
        return BlockTags.bind(new ResourceLocation("worldgen/" + name).toString());
    }

    public static ITag.INamedTag<Block> mod(String name) {
        return BlockTags.bind(Variants.variants(name).toString());
    }
}
