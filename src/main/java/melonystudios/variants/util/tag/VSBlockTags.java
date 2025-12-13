package melonystudios.variants.util.tag;

import melonystudios.variants.Variants;
import net.minecraft.block.Block;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;

public class VSBlockTags {
    // Forge Tags
    public static final ITag.INamedTag<Block> STORAGE_BLOCKS_NETHERITE_SCRAP = forge("storage_blocks/netherite_scrap");

    public static final ITag.INamedTag<Block> ORES_CRYSTALLIZED_MAGMA_CREAM = forge("ores/crystallized_magma_cream");

    public static final ITag.INamedTag<Block> GLASS_GLOW_BLACK = forge("glass/glow_black");
    public static final ITag.INamedTag<Block> GLASS_PANES_GLOW_BLACK = forge("glass_panes/glow_black");

    // Revaried's Tags
    public static final ITag.INamedTag<Block> PAINTING_DOORS = revaried("painting_doors");
    public static final ITag.INamedTag<Block> PAINTING_TRAPDOORS = revaried("painting_trapdoors");
    public static final ITag.INamedTag<Block> PAINTING_LOGS = revaried("painting_logs");
    public static final ITag.INamedTag<Block> ENDERWOOD_STEMS = revaried("enderwood_stems");
    public static final ITag.INamedTag<Block> BOOKSHELVES = revaried("bookshelves");
    public static final ITag.INamedTag<Block> CAULDRONS = revaried("cauldrons");
    public static final ITag.INamedTag<Block> BEACONS = revaried("beacons");
    public static final ITag.INamedTag<Block> SPAWNERS = revaried("spawners");
    public static final ITag.INamedTag<Block> SPAWNER_MINECART_CANNOT_REPLACE = revaried("spawner_minecart_cannot_replace");
    public static final ITag.INamedTag<Block> PLANTS_MAY_PLACE_ON = revaried("may_place_on/plants");
    public static final ITag.INamedTag<Block> NETHER_WART_MAY_PLACE_ON = revaried("may_place_on/nether_wart");
    public static final ITag.INamedTag<Block> ENDER_WART_MAY_PLACE_ON = revaried("may_place_on/ender_wart");
    public static final ITag.INamedTag<Block> GOLDEN_CARROTS_PLANTABLE_ON = revaried("may_place_on/golden_carrots");
    public static final ITag.INamedTag<Block> END_PLANTS_MAY_PLACE_ON = revaried("may_place_on/end_plants");
    public static final ITag.INamedTag<Block> WARPING_VINES_MAY_PLACE_ON = revaried("may_place_on/warping_vines_feature");
    public static final ITag.INamedTag<Block> CHORUS_FLOWER_MAY_PLACE_ON = revaried("may_place_on/chorus_flower");
    public static final ITag.INamedTag<Block> CHORUS_PLANT_MAY_PLACE_ON = revaried("may_place_on/chorus_plant");
    public static final ITag.INamedTag<Block> NETHER_CROPS_MAY_PLACE_ON = revaried("may_place_on/nether_crops");
    public static final ITag.INamedTag<Block> END_CARVER_REPLACEABLES = revaried("end_carver_replaceables");
    public static final ITag.INamedTag<Block> HAS_ENDER_NYLIUM = revaried("has_ender_nylium");

    // Melony (convention) Tags
    public static final ITag.INamedTag<Block> DEEPSLATE_REPLACEABLES = melony("deepslate_replaceables");
    public static final ITag.INamedTag<Block> FARMLAND = melony("farmland");
    public static final ITag.INamedTag<Block> NATURAL_FARMLAND = melony("natural_farmland");
    public static final ITag.INamedTag<Block> FARMLAND_TRANSPARENT = melony("farmland_transparent");
    public static final ITag.INamedTag<Block> CONDUIT_FRAME_BLOCKS = melony("conduit_frame_blocks");
    public static final ITag.INamedTag<Block> COMPLETES_FIND_TREE_TUTORIAL = melony("completes_find_tree_tutorial");

    // Other mods' tags
    public static final ITag.INamedTag<Block> CHORUS_PLANTABLE = mod("endergetic", "chorus_plantable");
    public static final ITag.INamedTag<Block> ENDER_FIRE_BASE_BLOCKS = mod("endergetic", "ender_fire_base_blocks");

    public static ITag.INamedTag<Block> revaried(String name) {
        return BlockTags.bind(Variants.variants(name).toString());
    }

    public static ITag.INamedTag<Block> melony(String name) {
        return BlockTags.bind(new ResourceLocation("melony", name).toString());
    }

    public static ITag.INamedTag<Block> forge(String name) {
        return BlockTags.bind(new ResourceLocation("forge", name).toString());
    }

    public static ITag.INamedTag<Block> mod(String namespace, String name) {
        return BlockTags.bind(new ResourceLocation(namespace, name).toString());
    }
}
