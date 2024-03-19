package com.junethewoods.variants.data.tags;

import com.junethewoods.variants.Variants;
import com.junethewoods.variants.block.VSBlocks;
import com.junethewoods.variants.util.VSTags;
import net.minecraft.block.Blocks;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class VSBlockTagsProvider extends BlockTagsProvider {
    public VSBlockTagsProvider(DataGenerator generator, @Nullable ExistingFileHelper fileHelper) {
        super(generator, Variants.MOD_ID, fileHelper);
    }

    @Override
    @Nonnull
    public String getName() {
        return "Variants - Block Tags";
    }

    @Override
    protected void addTags() {
        this.tag(VSTags.Blocks.STORAGE_BLOCKS_NETHERITE_SCRAP).add(VSBlocks.RAW_DEBRIS_BLOCK.get());
        this.tag(Tags.Blocks.STORAGE_BLOCKS).addTag(VSTags.Blocks.STORAGE_BLOCKS_NETHERITE_SCRAP);

        this.tag(Tags.Blocks.ORES_QUARTZ).add(VSBlocks.QUARTZ_ORE.get()).add(VSBlocks.DEEPSLATE_QUARTZ_ORE.get()).add(VSBlocks.END_QUARTZ_ORE.get()).add(VSBlocks.ENDER_NYLIUM_QUARTZ_ORE.get());

        this.tag(Tags.Blocks.FENCE_GATES_WOODEN).add(VSBlocks.PAINTING_FENCE_GATE.get());

        this.tag(Tags.Blocks.STAINED_GLASS).add(VSBlocks.GLOW_BLACK_STAINED_GLASS.get());
        this.tag(Tags.Blocks.STAINED_GLASS_PANES).add(VSBlocks.GLOW_BLACK_STAINED_GLASS_PANE.get());
        this.tag(VSTags.Blocks.GLASS_GLOW_BLACK).add(VSBlocks.GLOW_BLACK_STAINED_GLASS.get());
        this.tag(VSTags.Blocks.GLASS_PANES_GLOW_BLACK).add(VSBlocks.GLOW_BLACK_STAINED_GLASS_PANE.get());

        this.tag(Tags.Blocks.GLASS_COLORLESS).add(VSBlocks.QUARTZ_GLASS.get());
        this.tag(Tags.Blocks.GLASS_PANES_COLORLESS).add(VSBlocks.QUARTZ_GLASS_PANE.get());

        this.tag(Tags.Blocks.FENCES_NETHER_BRICK).add(VSBlocks.RED_NETHER_BRICK_FENCE.get());

        // Variants' Tags
        this.tag(VSTags.Blocks.PAINTING_DOORS).add(VSBlocks.PAINTING_DOOR_WANDERER.get()).add(VSBlocks.PAINTING_DOOR_GRAHAM.get()).add(VSBlocks.PAINTING_DOOR_FIRST.get());
        this.tag(VSTags.Blocks.PAINTING_TRAPDOORS).add(VSBlocks.PAINTING_TRAPDOOR_ALBAN.get()).add(VSBlocks.PAINTING_TRAPDOOR_AZTEC.get()).add(VSBlocks.PAINTING_TRAPDOOR_AZTEC2.get()).add(VSBlocks.PAINTING_TRAPDOOR_BOMB.get())
                .add(VSBlocks.PAINTING_TRAPDOOR_KEBAB.get()).add(VSBlocks.PAINTING_TRAPDOOR_PLANT.get()).add(VSBlocks.PAINTING_TRAPDOOR_WASTELAND.get());
        this.tag(VSTags.Blocks.BOOKSHELVES).add(Blocks.BOOKSHELF).add(VSBlocks.ENDERWOOD_BOOKSHELF.get()).add(VSBlocks.PLAIN_BIRCH_BOOKSHELF.get());
        this.tag(VSTags.Blocks.PAINTING_LOGS).add(VSBlocks.PAINTING_LOG.get()).add(VSBlocks.PAINTING_WOOD.get()).add(VSBlocks.STRIPPED_PAINTING_LOG.get()).add(VSBlocks.STRIPPED_PAINTING_WOOD.get());
        this.tag(VSTags.Blocks.ENDERWOOD_STEMS).add(VSBlocks.ENDERWOOD_STEM.get()).add(VSBlocks.ENDERWOOD_HYPHAE.get()).add(VSBlocks.STRIPPED_ENDERWOOD_STEM.get()).add(VSBlocks.STRIPPED_ENDERWOOD_HYPHAE.get());
        this.tag(VSTags.Blocks.CAULDRONS).add(Blocks.CAULDRON).add(VSBlocks.GOLDEN_CAULDRON.get()).add(VSBlocks.QUARTZ_CAULDRON.get());
        this.tag(VSTags.Blocks.BEACONS).add(Blocks.BEACON).add(VSBlocks.GOLDEN_BEACON.get()).add(VSBlocks.QUARTZ_BEACON.get());
        this.tag(VSTags.Blocks.NETHER_WART_PLACEABLE_ON).add(Blocks.SOUL_SAND).add(Blocks.SOUL_SOIL);
        this.tag(VSTags.Blocks.ENDER_WART_PLANTABLE_ON).addTag(VSTags.Blocks.HAS_ENDER_NYLIUM).addTag(Tags.Blocks.END_STONES).add(VSBlocks.ENDER_FARMLAND.get()).add(Blocks.OBSIDIAN);
        this.tag(VSTags.Blocks.GOLDEN_CARROTS_PLANTABLE_ON).addTag(VSTags.Blocks.FARMLAND);
        this.tag(VSTags.Blocks.END_PLANTS_PLANTABLE_ON).addTag(BlockTags.NYLIUM).addTag(Tags.Blocks.END_STONES).add(Blocks.OBSIDIAN);
        this.tag(VSTags.Blocks.WARPING_VINES_CAN_PLACE_ON).addTag(Tags.Blocks.END_STONES).add(VSBlocks.ENDER_NYLIUM.get()).add(VSBlocks.ENDER_WART_BLOCK.get());
        this.tag(VSTags.Blocks.CHORUS_FLOWER_PLANTABLE_ON).addTag(VSTags.Blocks.HAS_ENDER_NYLIUM).addTag(Tags.Blocks.END_STONES);
        this.tag(VSTags.Blocks.CHORUS_PLANT_PLANTABLE_ON).add(Blocks.CHORUS_FLOWER).add(Blocks.CHORUS_PLANT);
        this.tag(VSTags.Blocks.END_CARVER_REPLACEABLES).addTag(Tags.Blocks.END_STONES).add(VSBlocks.ENDER_NYLIUM.get());
        this.tag(VSTags.Blocks.HAS_ENDER_NYLIUM).add(VSBlocks.ENDER_NYLIUM.get()).add(VSBlocks.ENDER_NYLIUM_QUARTZ_ORE.get());
        this.tag(VSTags.Blocks.FARMLAND_TRANSPARENT).addTag(Tags.Blocks.FENCE_GATES).addTag(BlockTags.FENCE_GATES).add(Blocks.MOVING_PISTON);

        // Melony Tags
        this.tag(VSTags.Blocks.DEEPSLATE_REPLACEABLES).addOptional(new ResourceLocation("minecraft:deepslate")).addOptional(new ResourceLocation("cavesandcliffs:deepslate")).addOptional(new ResourceLocation("quark:deepslate"))
                .addOptional(new ResourceLocation("decorativelary:deepslate"));
        this.tag(VSTags.Blocks.FARMLAND).add(Blocks.FARMLAND).add(VSBlocks.ENDER_FARMLAND.get()).addOptional(backMath("aljamic_farmland"));
        this.tag(VSTags.Blocks.CONDUIT_FRAME_BLOCKS).add(Blocks.PRISMARINE).add(Blocks.PRISMARINE_BRICKS).add(Blocks.DARK_PRISMARINE).add(Blocks.SEA_LANTERN).add(VSBlocks.ELDER_PRISMARINE.get()).add(VSBlocks.ELDER_PRISMARINE_BRICKS.get())
                .add(VSBlocks.DARK_ELDER_PRISMARINE.get()).add(VSBlocks.ELDER_SEA_LANTERN.get());
        this.tag(VSTags.Blocks.COMPLETES_FIND_TREE_TUTORIAL).addTag(BlockTags.LOGS).addTag(BlockTags.LEAVES).addTag(BlockTags.WART_BLOCKS);

        // Minecraft Tags
        this.tag(BlockTags.LOGS_THAT_BURN).addTag(VSTags.Blocks.PAINTING_LOGS).addTag(VSTags.Blocks.ENDERWOOD_STEMS);
        this.tag(BlockTags.LEAVES).add(VSBlocks.AZURE_BLUET_LEAVES.get()).add(VSBlocks.PAINTING_LEAVES.get());
        this.tag(BlockTags.WART_BLOCKS).add(VSBlocks.ENDER_WART_BLOCK.get());
        this.tag(BlockTags.SAPLINGS).add(VSBlocks.PAINTING_SAPLING.get());
        this.tag(BlockTags.PLANKS).add(VSBlocks.PAINTING_PLANKS.get()).add(VSBlocks.ENDERWOOD_PLANKS.get());
        this.tag(BlockTags.WOODEN_STAIRS).add(VSBlocks.PAINTING_STAIRS.get()).add(VSBlocks.ENDERWOOD_STAIRS.get());
        this.tag(BlockTags.WOODEN_SLABS).add(VSBlocks.PAINTING_SLAB.get()).add(VSBlocks.ENDERWOOD_SLAB.get());
        this.tag(BlockTags.WOODEN_FENCES).add(VSBlocks.PAINTING_FENCE.get()).add(VSBlocks.ENDERWOOD_FENCE.get());
        this.tag(BlockTags.FENCE_GATES).add(VSBlocks.PAINTING_FENCE_GATE.get()).add(VSBlocks.ENDERWOOD_FENCE_GATE.get());
        this.tag(BlockTags.WOODEN_PRESSURE_PLATES).add(VSBlocks.PAINTING_PRESSURE_PLATE.get()).add(VSBlocks.ENDERWOOD_PRESSURE_PLATE.get());
        this.tag(BlockTags.WOODEN_BUTTONS).add(VSBlocks.PAINTING_BUTTON.get()).add(VSBlocks.ENDERWOOD_BUTTON.get());
        this.tag(BlockTags.WOODEN_DOORS).addTag(VSTags.Blocks.PAINTING_DOORS).add(VSBlocks.PAINTING_DOOR.get()).add(VSBlocks.ENDERWOOD_DOOR.get());
        this.tag(BlockTags.WOODEN_TRAPDOORS).addTag(VSTags.Blocks.PAINTING_TRAPDOORS).add(VSBlocks.PAINTING_TRAPDOOR.get()).add(VSBlocks.ENDERWOOD_TRAPDOOR.get());
        this.tag(BlockTags.STANDING_SIGNS).add(VSBlocks.PAINTING_SIGN.get()).add(VSBlocks.ENDERWOOD_SIGN.get());
        this.tag(BlockTags.WALL_SIGNS).add(VSBlocks.PAINTING_WALL_SIGN.get()).add(VSBlocks.ENDERWOOD_WALL_SIGN.get());

        this.tag(BlockTags.STAIRS).add(VSBlocks.ELDER_PRISMARINE_STAIRS.get()).add(VSBlocks.ELDER_PRISMARINE_BRICK_STAIRS.get()).add(VSBlocks.DARK_ELDER_PRISMARINE_STAIRS.get()).add(VSBlocks.MOSSY_END_STONE_BRICK_STAIRS.get())
                .add(VSBlocks.MOSSY_PURPUR_BLOCK_STAIRS.get()).add(VSBlocks.MOSSY_NETHER_BRICK_STAIRS.get());
        this.tag(BlockTags.SLABS).add(VSBlocks.ELDER_PRISMARINE_SLAB.get()).add(VSBlocks.ELDER_PRISMARINE_BRICK_SLAB.get()).add(VSBlocks.DARK_ELDER_PRISMARINE_SLAB.get()).add(VSBlocks.MOSSY_END_STONE_BRICK_SLAB.get())
                .add(VSBlocks.MOSSY_PURPUR_BLOCK_SLAB.get()).add(VSBlocks.SMOOTH_PURPUR_SLAB.get()).add(VSBlocks.MOSSY_NETHER_BRICK_SLAB.get());
        this.tag(BlockTags.WALLS).add(VSBlocks.ELDER_PRISMARINE_WALL.get()).add(VSBlocks.MOSSY_END_STONE_BRICK_WALL.get()).add(VSBlocks.MOSSY_PURPUR_BLOCK_WALL.get()).add(VSBlocks.MOSSY_NETHER_BRICK_WALL.get());
        this.tag(BlockTags.FENCES).add(VSBlocks.RED_NETHER_BRICK_FENCE.get());

        this.tag(BlockTags.CROPS).add(VSBlocks.GOLDEN_CARROTS.get()).add(Blocks.NETHER_WART).add(VSBlocks.WARPED_WART.get()).add(VSBlocks.ENDER_WART.get());
        this.tag(BlockTags.BEE_GROWABLES).add(VSBlocks.GLOW_BERRY_BUSH.get());
        this.tag(BlockTags.FLOWERS).add(VSBlocks.AZURE_BLUET_LEAVES.get());
        this.tag(BlockTags.SMALL_FLOWERS).add(VSBlocks.GLOW_BLACK_TULIP.get()).add(VSBlocks.SUNNY_FLOWER.get());
        this.tag(BlockTags.FLOWER_POTS).add(VSBlocks.POTTED_GLOW_BLACK_TULIP.get()).add(VSBlocks.POTTED_SUNNY_FLOWER.get()).add(VSBlocks.POTTED_SUGAR_CANE.get()).add(VSBlocks.POTTED_GRASS.get()).add(VSBlocks.POTTED_PAINTING_SAPLING.get())
                .add(VSBlocks.POTTED_GOLDEN_CARROTS.get()).add(VSBlocks.POTTED_ENDER_ROOTS.get()).add(VSBlocks.POTTED_ENDER_FUNGUS.get()).add(VSBlocks.POTTED_NETHER_WART.get()).add(VSBlocks.POTTED_WARPED_WART.get())
                .add(VSBlocks.POTTED_ENDER_WART.get()).add(VSBlocks.POTTED_TORCH.get()).add(VSBlocks.POTTED_SOUL_TORCH.get()).add(VSBlocks.POTTED_REDSTONE_TORCH.get());
        this.tag(BlockTags.WOOL).add(VSBlocks.GLOW_BLACK_WOOL.get());
        this.tag(BlockTags.CARPETS).add(VSBlocks.GLOW_BLACK_CARPET.get());
        this.tag(BlockTags.GUARDED_BY_PIGLINS).add(VSBlocks.GOLDEN_CAULDRON.get()).add(VSBlocks.GOLDEN_BEACON.get()).add(VSBlocks.GOLDEN_CARROTS.get()).add(VSBlocks.GOLDEN_CHAIN.get());
        this.tag(BlockTags.DRAGON_IMMUNE).add(VSBlocks.ENDER_NYLIUM.get()).add(VSBlocks.END_QUARTZ_ORE.get()).add(VSBlocks.ENDER_NYLIUM_QUARTZ_ORE.get()).add(VSBlocks.QUARTZ_BARS.get());
        this.tag(BlockTags.IMPERMEABLE).add(VSBlocks.QUARTZ_GLASS.get()).add(VSBlocks.GLOW_BLACK_STAINED_GLASS.get());
        this.tag(BlockTags.NYLIUM).add(VSBlocks.ENDER_NYLIUM.get()).add(VSBlocks.ENDER_NYLIUM_QUARTZ_ORE.get());
        this.tag(BlockTags.CLIMBABLE).add(VSBlocks.WARPING_VINES.get()).add(VSBlocks.WARPING_VINES_PLANT.get());
        this.tag(BlockTags.BEDS).add(VSBlocks.GLOW_BLACK_BED.get());
        this.tag(BlockTags.PIGLIN_REPELLENTS).add(VSBlocks.POTTED_SOUL_TORCH.get());
        this.tag(BlockTags.STRIDER_WARM_BLOCKS).add(VSBlocks.SOUL_LAVA.get());
    }

    private static ResourceLocation backMath(String block) {
        return new ResourceLocation("backmath", block);
    }
}
