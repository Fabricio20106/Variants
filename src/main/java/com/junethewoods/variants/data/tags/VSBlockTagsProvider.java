package com.junethewoods.variants.data.tags;

import com.junethewoods.variants.Variants;
import com.junethewoods.variants.block.VSBlocks;
import com.junethewoods.variants.util.VSTags;
import net.minecraft.block.Blocks;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

public class VSBlockTagsProvider extends BlockTagsProvider {
    public VSBlockTagsProvider(DataGenerator generator, @Nullable ExistingFileHelper fileHelper) {
        super(generator, Variants.MOD_ID, fileHelper);
    }

    @Override
    public String getName() {
        return "Variants - Block Tags";
    }

    @Override
    protected void addTags() {
        this.tag(VSTags.Blocks.STORAGE_BLOCKS_NETHERITE_SCRAP).add(VSBlocks.RAW_DEBRIS_BLOCK.get());
        this.tag(Tags.Blocks.STORAGE_BLOCKS).addTag(VSTags.Blocks.STORAGE_BLOCKS_NETHERITE_SCRAP);

        this.tag(Tags.Blocks.ORES_QUARTZ).add(VSBlocks.QUARTZ_ORE.get()).add(VSBlocks.END_QUARTZ_ORE.get());

        this.tag(Tags.Blocks.FENCE_GATES_WOODEN).add(VSBlocks.PAINTING_FENCE_GATE.get());

        // Variants' Tags
        this.tag(VSTags.Blocks.PAINTING_DOORS).add(VSBlocks.WANDERER_DOOR.get()).add(VSBlocks.GRAHAM_DOOR.get()).add(VSBlocks.FIRST_DOOR.get());
        this.tag(VSTags.Blocks.BOOKSHELVES).add(Blocks.BOOKSHELF).add(VSBlocks.PLAIN_BIRCH_BOOKSHELF.get());
        this.tag(VSTags.Blocks.PAINTING_LOGS).add(VSBlocks.PAINTING_LOG.get()).add(VSBlocks.PAINTING_WOOD.get()).add(VSBlocks.STRIPPED_PAINTING_LOG.get())
                .add(VSBlocks.STRIPPED_PAINTING_WOOD.get());
        this.tag(VSTags.Blocks.BEACONS).add(Blocks.BEACON).add(VSBlocks.GOLDEN_BEACON.get());

        // Minecraft Tags
        this.tag(BlockTags.LOGS_THAT_BURN).addTag(VSTags.Blocks.PAINTING_LOGS);
        this.tag(BlockTags.LEAVES).add(VSBlocks.PAINTING_LEAVES.get());
        this.tag(BlockTags.PLANKS).add(VSBlocks.PAINTING_PLANKS.get());
        this.tag(BlockTags.WOODEN_STAIRS).add(VSBlocks.PAINTING_STAIRS.get());
        this.tag(BlockTags.WOODEN_SLABS).add(VSBlocks.PAINTING_SLAB.get());
        this.tag(BlockTags.WOODEN_FENCES).add(VSBlocks.PAINTING_FENCE.get());
        this.tag(BlockTags.FENCE_GATES).add(VSBlocks.PAINTING_FENCE_GATE.get());
        this.tag(BlockTags.WOODEN_PRESSURE_PLATES).add(VSBlocks.PAINTING_PRESSURE_PLATE.get());
        this.tag(BlockTags.WOODEN_BUTTONS).add(VSBlocks.PAINTING_BUTTON.get());
        this.tag(BlockTags.WOODEN_DOORS).add(VSBlocks.PAINTING_DOOR.get()).add(VSBlocks.WANDERER_DOOR.get());
        this.tag(BlockTags.WOODEN_TRAPDOORS).add(VSBlocks.PAINTING_TRAPDOOR.get());

        this.tag(BlockTags.CROPS).add(VSBlocks.GOLDEN_CARROTS.get()).add(VSBlocks.WARPED_WART.get());
        this.tag(BlockTags.BEE_GROWABLES).add(VSBlocks.GLOW_BERRY_BUSH.get());
        this.tag(BlockTags.SMALL_FLOWERS).add(VSBlocks.GLOW_BLACK_TULIP.get());
        this.tag(BlockTags.FLOWER_POTS).add(VSBlocks.POTTED_GLOW_BLACK_TULIP.get()).add(VSBlocks.POTTED_SUGAR_CANE.get());
        this.tag(BlockTags.WOOL).add(VSBlocks.GLOW_BLACK_WOOL.get());
        this.tag(BlockTags.CARPETS).add(VSBlocks.GLOW_BLACK_CARPET.get());
        this.tag(BlockTags.GUARDED_BY_PIGLINS).add(VSBlocks.GOLDEN_CAULDRON.get()).add(VSBlocks.GOLDEN_BEACON.get()).add(VSBlocks.GOLDEN_CARROTS.get());
    }
}
