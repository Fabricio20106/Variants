package com.junethewoods.variants.data.tags;

import com.junethewoods.variants.Variants;
import com.junethewoods.variants.item.VSItems;
import com.junethewoods.variants.item.VSWeaponry;
import com.junethewoods.variants.util.VSTags;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.item.Items;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

public class VSItemTagsProvider extends ItemTagsProvider {
    public VSItemTagsProvider(DataGenerator generator, BlockTagsProvider blockTagsProvider, @Nullable ExistingFileHelper fileHelper) {
        super(generator, blockTagsProvider, Variants.MOD_ID, fileHelper);
    }

    @Override
    public String getName() {
        return "Variants - Item Tags";
    }

    @Override
    protected void addTags() {
        this.tag(VSTags.Items.STORAGE_BLOCKS_NETHERITE_SCRAP).add(VSItems.RAW_DEBRIS_BLOCK.get());
        this.tag(Tags.Items.STORAGE_BLOCKS).addTag(VSTags.Items.STORAGE_BLOCKS_NETHERITE_SCRAP);

        this.tag(VSTags.Items.INGOTS_EXPOSED_COPPER).add(VSItems.EXPOSED_COPPER_INGOT.get());
        this.tag(VSTags.Items.INGOTS_WEATHERED_COPPER).add(VSItems.WEATHERED_COPPER_INGOT.get());
        this.tag(VSTags.Items.INGOTS_OXIDIZED_COPPER).add(VSItems.OXIDIZED_COPPER_INGOT.get());
        this.tag(Tags.Items.INGOTS).addTag(VSTags.Items.INGOTS_EXPOSED_COPPER).addTag(VSTags.Items.INGOTS_WEATHERED_COPPER).addTag(VSTags.Items.INGOTS_OXIDIZED_COPPER);

        this.tag(VSTags.Items.NUGGETS_PURPLE_IRON).add(VSItems.PURPLE_IRON_NUGGET.get());
        this.tag(Tags.Items.NUGGETS).addTag(VSTags.Items.NUGGETS_PURPLE_IRON);

        this.tag(VSTags.Items.RAW_MATERIALS_NETHERITE_SCRAP).add(VSItems.RAW_DEBRIS.get());
        this.tag(VSTags.Items.RAW_MATERIALS).addTag(VSTags.Items.RAW_MATERIALS_NETHERITE_SCRAP);

        this.tag(Tags.Items.ORES_QUARTZ).add(VSItems.QUARTZ_ORE.get()).add(VSItems.END_QUARTZ_ORE.get());

        this.tag(Tags.Items.FENCE_GATES_WOODEN).add(VSItems.PAINTING_FENCE_GATE.get());

        this.tag(VSTags.Items.RODS_DEBUG_WOODEN).add(Items.DEBUG_STICK);
        this.tag(VSTags.Items.RODS_NETHERITE).add(VSItems.NETHERITE_ROD.get());
        this.tag(Tags.Items.RODS).addTag(VSTags.Items.RODS_DEBUG_WOODEN).addTag(VSTags.Items.RODS_NETHERITE);
        this.tag(Tags.Items.RODS_WOODEN).add(VSItems.OAK_STICK.get()).add(VSItems.SPRUCE_STICK.get()).add(VSItems.BIRCH_STICK.get()).add(VSItems.JUNGLE_STICK.get())
                .add(VSItems.ACACIA_STICK.get()).add(VSItems.DARK_OAK_STICK.get()).add(VSItems.PAINTING_STICK.get()).add(VSItems.CRIMSON_STICK.get()).add(VSItems.WARPED_STICK.get());

        this.tag(Tags.Items.SHEARS).add(VSWeaponry.COAL_SHEARS.get()).add(VSWeaponry.GOLDEN_SHEARS.get()).add(VSWeaponry.DIAMOND_SHEARS.get()).add(VSWeaponry.NETHERITE_SHEARS.get())
                .add(VSWeaponry.REDSTONE_SHEARS.get()).add(VSWeaponry.LAPIS_LAZULI_SHEARS.get()).add(VSWeaponry.EMERALD_SHEARS.get()).add(VSWeaponry.QUARTZ_SHEARS.get())
                .add(VSWeaponry.RUBY_SHEARS.get()).add(VSWeaponry.DIAEMERALD_SHEARS.get()).add(VSWeaponry.CRYSTAL_SHEARS.get()).add(VSWeaponry.PLASTEEL_SHEARS.get())
                .add(VSWeaponry.LIGHT_MAGENTA_SHEARS.get()).add(VSWeaponry.ALAN_AI_SHEARS.get()).add(VSWeaponry.ALICE_AI_SHEARS.get()).add(VSWeaponry.INNO_AI_SHEARS.get())
                .add(VSWeaponry.NICOLAS_AI_SHEARS.get());

        this.tag(Tags.Items.BOOKSHELVES).add(VSItems.PLAIN_BIRCH_BOOKSHELF.get());

        this.tag(VSTags.Items.DYES_GLOW_BLACK).add(VSItems.GLOW_BLACK_DYE.get());
        this.tag(Tags.Items.DYES).addTag(VSTags.Items.DYES_GLOW_BLACK);

        // Variants' Tags
        this.tag(VSTags.Items.CM_DIORITE).add(Items.DIORITE);
        this.tag(VSTags.Items.CM_GRANITE).add(Items.GRANITE);
        this.tag(VSTags.Items.CM_ANDESITE).add(Items.ANDESITE);
        this.tag(VSTags.Items.CM_MAGMA_BLOCK).add(Items.MAGMA_BLOCK);
        this.tag(VSTags.Items.CRAFTING_MATERIALS).addTag(VSTags.Items.CM_DIORITE).addTag(VSTags.Items.CM_GRANITE).addTag(VSTags.Items.CM_ANDESITE).addTag(VSTags.Items.CM_MAGMA_BLOCK);

        this.tag(VSTags.Items.BOOKS).add(Items.BOOK).add(Items.WRITABLE_BOOK).add(Items.WRITTEN_BOOK).add(Items.ENCHANTED_BOOK).add(Items.KNOWLEDGE_BOOK);
        this.tag(VSTags.Items.BOOKSHELVES).add(Items.BOOKSHELF).add(VSItems.PLAIN_BIRCH_BOOKSHELF.get());
        this.tag(VSTags.Items.PAINTING_DOORS).add(VSItems.WANDERER_DOOR.get()).add(VSItems.GRAHAM_DOOR.get()).add(VSItems.FIRST_DOOR.get());
        this.tag(VSTags.Items.SHULKER_SHELLS).add(Items.SHULKER_SHELL).add(VSItems.WHITE_SHULKER_SHELL.get()).add(VSItems.INNO_SHULKER_SHELL.get())
                .add(VSItems.ORANGE_SHULKER_SHELL.get()).add(VSItems.MAGENTA_SHULKER_SHELL.get()).add(VSItems.LIGHT_BLUE_SHULKER_SHELL.get()).add(VSItems.GLOW_BLACK_SHULKER_SHELL.get())
                .add(VSItems.YELLOW_SHULKER_SHELL.get()).add(VSItems.LIME_SHULKER_SHELL.get()).add(VSItems.PINK_SHULKER_SHELL.get())
                .add(VSItems.GRAY_SHULKER_SHELL.get()).add(VSItems.LIGHT_GRAY_SHULKER_SHELL.get()).add(VSItems.CYAN_SHULKER_SHELL.get())
                .add(VSItems.PURPLE_SHULKER_SHELL.get()).add(VSItems.BLUE_SHULKER_SHELL.get()).add(VSItems.BROWN_SHULKER_SHELL.get())
                .add(VSItems.GREEN_SHULKER_SHELL.get()).add(VSItems.RED_SHULKER_SHELL.get()).add(VSItems.BLACK_SHULKER_SHELL.get());

        this.tag(VSTags.Items.PAINTING_LOGS).add(VSItems.PAINTING_LOG.get()).add(VSItems.PAINTING_WOOD.get()).add(VSItems.STRIPPED_PAINTING_LOG.get())
                .add(VSItems.STRIPPED_PAINTING_WOOD.get());

        // Minecraft Tags
        this.tag(ItemTags.LOGS_THAT_BURN).addTag(VSTags.Items.PAINTING_LOGS);
        this.tag(ItemTags.LEAVES).add(VSItems.PAINTING_LEAVES.get());
        this.tag(ItemTags.PLANKS).add(VSItems.PAINTING_PLANKS.get());
        this.tag(ItemTags.WOODEN_STAIRS).add(VSItems.PAINTING_STAIRS.get());
        this.tag(ItemTags.WOODEN_SLABS).add(VSItems.PAINTING_SLAB.get());
        this.tag(ItemTags.WOODEN_FENCES).add(VSItems.PAINTING_FENCE.get());
        this.tag(ItemTags.WOODEN_PRESSURE_PLATES).add(VSItems.PAINTING_PRESSURE_PLATE.get());
        this.tag(ItemTags.WOODEN_BUTTONS).add(VSItems.PAINTING_BUTTON.get());
        this.tag(ItemTags.WOODEN_DOORS).addTag(VSTags.Items.PAINTING_DOORS);
        this.tag(ItemTags.WOODEN_TRAPDOORS).add(VSItems.PAINTING_TRAPDOOR.get());

        this.tag(ItemTags.SMALL_FLOWERS).add(VSItems.GLOW_BLACK_TULIP.get());
        this.tag(ItemTags.WOOL).add(VSItems.GLOW_BLACK_WOOL.get());
        this.tag(ItemTags.CARPETS).add(VSItems.GLOW_BLACK_CARPET.get());
        this.tag(ItemTags.CREEPER_DROP_MUSIC_DISCS).add(VSItems.MUSIC_DISC_DOG.get());
        this.tag(ItemTags.PIGLIN_LOVED).add(VSItems.GOLDEN_CAULDRON.get()).add(VSItems.GOLDEN_BEACON.get()).add(VSItems.GOLDEN_CARROTS.get()).add(VSWeaponry.GOLDEN_SHEARS.get());
    }
}
