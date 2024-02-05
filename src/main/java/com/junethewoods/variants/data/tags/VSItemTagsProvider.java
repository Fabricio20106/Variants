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
import net.minecraft.util.ResourceLocation;
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

        this.tag(VSTags.Items.INGOTS_RED_NETHER_BRICK).add(VSItems.RED_NETHER_BRICK.get());
        this.tag(VSTags.Items.INGOTS_EXPOSED_COPPER).add(VSItems.EXPOSED_COPPER_INGOT.get());
        this.tag(VSTags.Items.INGOTS_WEATHERED_COPPER).add(VSItems.WEATHERED_COPPER_INGOT.get());
        this.tag(VSTags.Items.INGOTS_OXIDIZED_COPPER).add(VSItems.OXIDIZED_COPPER_INGOT.get());
        this.tag(Tags.Items.INGOTS).addTag(VSTags.Items.INGOTS_RED_NETHER_BRICK).addTag(VSTags.Items.INGOTS_EXPOSED_COPPER).addTag(VSTags.Items.INGOTS_WEATHERED_COPPER).addTag(VSTags.Items.INGOTS_OXIDIZED_COPPER);

        this.tag(VSTags.Items.NUGGETS_PURPLE_IRON).add(VSItems.PURPLE_IRON_NUGGET.get());
        this.tag(VSTags.Items.NUGGETS_QUARTZ).add(VSItems.QUARTZ_SHARD.get());
        this.tag(Tags.Items.NUGGETS).addTag(VSTags.Items.NUGGETS_QUARTZ).addTag(VSTags.Items.NUGGETS_PURPLE_IRON);

        this.tag(VSTags.Items.RAW_MATERIALS_NETHERITE_SCRAP).add(VSItems.RAW_DEBRIS.get());
        this.tag(VSTags.Items.RAW_MATERIALS).addTag(VSTags.Items.RAW_MATERIALS_NETHERITE_SCRAP);

        this.tag(Tags.Items.ORES_QUARTZ).add(VSItems.QUARTZ_ORE.get()).add(VSItems.DEEPSLATE_QUARTZ_ORE.get()).add(VSItems.END_QUARTZ_ORE.get());

        this.tag(Tags.Items.FENCE_GATES_WOODEN).add(VSItems.PAINTING_FENCE_GATE.get());

        this.tag(Tags.Items.STAINED_GLASS).add(VSItems.GLOW_BLACK_STAINED_GLASS.get());
        this.tag(Tags.Items.STAINED_GLASS_PANES).add(VSItems.GLOW_BLACK_STAINED_GLASS_PANE.get());
        this.tag(VSTags.Items.GLASS_GLOW_BLACK).add(VSItems.GLOW_BLACK_STAINED_GLASS.get());
        this.tag(VSTags.Items.GLASS_PANES_GLOW_BLACK).add(VSItems.GLOW_BLACK_STAINED_GLASS_PANE.get());

        this.tag(Tags.Items.GLASS_COLORLESS).add(VSItems.QUARTZ_GLASS.get());
        this.tag(Tags.Items.GLASS_PANES_COLORLESS).add(VSItems.QUARTZ_GLASS_PANE.get());

        this.tag(Tags.Items.FENCES_NETHER_BRICK).add(VSItems.RED_NETHER_BRICK_FENCE.get());

        this.tag(VSTags.Items.CROPS_GOLDEN_CARROT).add(Items.GOLDEN_CARROT).add(VSItems.GOLDEN_CARROTS.get());
        this.tag(VSTags.Items.CROPS_WARPED_WART).add(VSItems.WARPED_WART.get());
        this.tag(VSTags.Items.CROPS_ENDER_WART).add(VSItems.ENDER_WART.get());
        this.tag(Tags.Items.CROPS).addTag(VSTags.Items.CROPS_GOLDEN_CARROT).addTag(VSTags.Items.CROPS_WARPED_WART).addTag(VSTags.Items.CROPS_ENDER_WART);

        this.tag(VSTags.Items.RODS_DEBUG_WOODEN).add(Items.DEBUG_STICK);
        this.tag(VSTags.Items.RODS_NETHERITE).add(VSItems.NETHERITE_ROD.get());
        this.tag(VSTags.Items.RODS_SOUL_BLAZE).add(VSItems.SOUL_BLAZE_ROD.get());
        this.tag(VSTags.Items.FISHING_WOODEN_RODS).add(Items.STICK).add(VSItems.OAK_STICK.get()).add(VSItems.SPRUCE_STICK.get()).add(VSItems.BIRCH_STICK.get()).add(VSItems.JUNGLE_STICK.get()).add(VSItems.ACACIA_STICK.get())
                .add(VSItems.DARK_OAK_STICK.get()).add(VSItems.PAINTING_STICK.get());
        this.tag(Tags.Items.RODS_WOODEN).add(VSItems.OAK_STICK.get()).add(VSItems.SPRUCE_STICK.get()).add(VSItems.BIRCH_STICK.get()).add(VSItems.JUNGLE_STICK.get()).add(VSItems.ACACIA_STICK.get()).add(VSItems.DARK_OAK_STICK.get())
                .add(VSItems.PAINTING_STICK.get()).add(VSItems.CRIMSON_STICK.get()).add(VSItems.WARPED_STICK.get()).add(VSItems.ENDERWOOD_STICK.get());
        this.tag(Tags.Items.RODS).addTag(VSTags.Items.FISHING_WOODEN_RODS).addTag(VSTags.Items.RODS_DEBUG_WOODEN).addTag(VSTags.Items.RODS_NETHERITE).addTag(VSTags.Items.RODS_SOUL_BLAZE);

        this.tag(Tags.Items.SHEARS).add(VSWeaponry.COAL_SHEARS.get()).add(VSWeaponry.GOLDEN_SHEARS.get()).add(VSWeaponry.COPPER_SHEARS.get()).add(VSWeaponry.EXPOSED_COPPER_SHEARS.get())
                .add(VSWeaponry.WEATHERED_COPPER_SHEARS.get()).add(VSWeaponry.OXIDIZED_COPPER_SHEARS.get()).add(VSWeaponry.AMETHYST_SHEARS.get()).add(VSWeaponry.DIAMOND_SHEARS.get())
                .add(VSWeaponry.NETHERITE_SHEARS.get()).add(VSWeaponry.REDSTONE_SHEARS.get()).add(VSWeaponry.LAPIS_LAZULI_SHEARS.get()).add(VSWeaponry.EMERALD_SHEARS.get())
                .add(VSWeaponry.QUARTZ_SHEARS.get()).add(VSWeaponry.RUBY_SHEARS.get()).add(VSWeaponry.DIAEMERALD_SHEARS.get()).add(VSWeaponry.CRYSTAL_SHEARS.get())
                .add(VSWeaponry.PLASTEEL_SHEARS.get()).add(VSWeaponry.LIGHT_MAGENTA_SHEARS.get()).add(VSWeaponry.ALAN_AI_SHEARS.get()).add(VSWeaponry.ALICE_AI_SHEARS.get())
                .add(VSWeaponry.INNO_AI_SHEARS.get()).add(VSWeaponry.NICOLAS_AI_SHEARS.get());

        this.tag(Tags.Items.BOOKSHELVES).add(VSItems.ENDERWOOD_BOOKSHELF.get()).add(VSItems.PLAIN_BIRCH_BOOKSHELF.get());

        this.tag(VSTags.Items.DYES_GLOW_BLACK).add(VSItems.GLOW_BLACK_DYE.get());
        this.tag(Tags.Items.DYES).addTag(VSTags.Items.DYES_GLOW_BLACK);

        this.tag(Tags.Items.BONES).add(VSItems.WITHER_BONE.get());
        this.tag(VSTags.Items.BONE_MEALS).add(Items.BONE_MEAL).add(VSItems.WITHER_BONE_MEAL.get());

        this.tag(VSTags.Items.DUSTS_GUNPOWDER).add(Items.GUNPOWDER);
        this.tag(VSTags.Items.DUSTS_SUGAR).add(Items.SUGAR);
        this.tag(VSTags.Items.DUSTS_BLAZE).add(Items.BLAZE_POWDER);
        this.tag(VSTags.Items.DUSTS_SOUL_BLAZE).add(VSItems.SOUL_BLAZE_POWDER.get());
        this.tag(VSTags.Items.DUSTS_ELDER_PRISMARINE).add(VSItems.ELDER_PRISMARINE_SHARD.get());
        this.tag(Tags.Items.DUSTS).addTag(VSTags.Items.DUSTS_SUGAR).addTag(VSTags.Items.DUSTS_GUNPOWDER).addTag(VSTags.Items.DUSTS_BLAZE).addTag(VSTags.Items.DUSTS_SOUL_BLAZE).addTag(VSTags.Items.DUSTS_ELDER_PRISMARINE);

        this.tag(VSTags.Items.GEMS_ELDER_PRISMARINE).add(VSItems.ELDER_PRISMARINE_CRYSTALS.get());
        this.tag(Tags.Items.GEMS).addTag(VSTags.Items.GEMS_ELDER_PRISMARINE);

        this.tag(VSTags.Items.ARMORS_BOOTS).add(VSWeaponry.EMPTY_ARMOR_SLOT_BOOTS.get()).add(VSWeaponry.EMERALD_BOOTS.get()).add(VSWeaponry.QUARTZ_BOOTS.get());
        this.tag(VSTags.Items.TOOLS_HOES).add(Items.WOODEN_HOE).add(Items.STONE_HOE).add(Items.IRON_HOE).add(Items.DIAMOND_HOE).add(Items.GOLDEN_HOE).add(Items.NETHERITE_HOE).add(VSWeaponry.DIORITE_HOE.get()).add(VSWeaponry.GRANITE_HOE.get())
                .add(VSWeaponry.ANDESITE_HOE.get()).add(VSWeaponry.END_STONE_HOE.get()).add(VSWeaponry.QUARTZ_HOE.get());

        // Variants' Tags
        this.tag(VSTags.Items.CM_DIORITE).add(Items.DIORITE);
        this.tag(VSTags.Items.CM_GRANITE).add(Items.GRANITE);
        this.tag(VSTags.Items.CM_ANDESITE).add(Items.ANDESITE);
        this.tag(VSTags.Items.CM_MAGMA_BLOCK).add(Items.MAGMA_BLOCK);
        this.tag(VSTags.Items.CM_END_STONE).add(Items.END_STONE);
        this.tag(VSTags.Items.CRAFTING_MATERIALS).addTag(VSTags.Items.CM_DIORITE).addTag(VSTags.Items.CM_GRANITE).addTag(VSTags.Items.CM_ANDESITE).addTag(VSTags.Items.CM_MAGMA_BLOCK).addTag(VSTags.Items.CM_END_STONE);

        this.tag(VSTags.Items.PAINTING_DOORS).add(VSItems.PAINTING_DOOR_WANDERER.get()).add(VSItems.PAINTING_DOOR_GRAHAM.get()).add(VSItems.PAINTING_DOOR_FIRST.get());
        this.tag(VSTags.Items.PAINTING_TRAPDOORS).add(VSItems.PAINTING_TRAPDOOR_ALBAN.get()).add(VSItems.PAINTING_TRAPDOOR_AZTEC.get()).add(VSItems.PAINTING_TRAPDOOR_AZTEC2.get())
                .add(VSItems.PAINTING_TRAPDOOR_BOMB.get()).add(VSItems.PAINTING_TRAPDOOR_KEBAB.get()).add(VSItems.PAINTING_TRAPDOOR_PLANT.get()).add(VSItems.PAINTING_TRAPDOOR_WASTELAND.get());
        this.tag(VSTags.Items.CAULDRONS).add(Items.CAULDRON).add(VSItems.GOLDEN_CAULDRON.get());
        this.tag(VSTags.Items.BEACONS).add(Items.BEACON).add(VSItems.GOLDEN_BEACON.get());
        this.tag(VSTags.Items.POTS).add(VSItems.STYLISED_POT.get()).add(VSItems.REDSTONE_POT.get()).add(VSItems.BLUESTONE_POT.get()).add(VSItems.GLOWSTONE_POT.get())
                .add(VSItems.GUNPOWDER_POT.get()).add(VSItems.EXPLOSIVE_BLEND_POT.get()).add(VSItems.SUGAR_POT.get()).add(VSItems.SWEET_BERRY_POT.get()).add(VSItems.GLOW_BERRY_POT.get());
        this.tag(VSTags.Items.SPYGLASSES).add(VSWeaponry.IRON_SPYGLASS.get()).add(VSWeaponry.DIAMOND_SPYGLASS.get()).add(VSWeaponry.NETHERITE_SPYGLASS.get());
        this.tag(VSTags.Items.SHULKER_SHELLS).add(Items.SHULKER_SHELL).add(VSItems.WHITE_SHULKER_SHELL.get()).add(VSItems.INNO_SHULKER_SHELL.get())
                .add(VSItems.ORANGE_SHULKER_SHELL.get()).add(VSItems.MAGENTA_SHULKER_SHELL.get()).add(VSItems.LIGHT_BLUE_SHULKER_SHELL.get()).add(VSItems.GLOW_BLACK_SHULKER_SHELL.get())
                .add(VSItems.YELLOW_SHULKER_SHELL.get()).add(VSItems.LIME_SHULKER_SHELL.get()).add(VSItems.PINK_SHULKER_SHELL.get())
                .add(VSItems.GRAY_SHULKER_SHELL.get()).add(VSItems.LIGHT_GRAY_SHULKER_SHELL.get()).add(VSItems.CYAN_SHULKER_SHELL.get())
                .add(VSItems.PURPLE_SHULKER_SHELL.get()).add(VSItems.BLUE_SHULKER_SHELL.get()).add(VSItems.BROWN_SHULKER_SHELL.get())
                .add(VSItems.GREEN_SHULKER_SHELL.get()).add(VSItems.RED_SHULKER_SHELL.get()).add(VSItems.BLACK_SHULKER_SHELL.get());

        this.tag(VSTags.Items.FLOWER_POTS).add(VSItems.POTTED_GLOW_BLACK_TULIP.get()).add(VSItems.POTTED_SUNNY_FLOWER.get()).add(VSItems.POTTED_SUGAR_CANE.get()).add(VSItems.POTTED_GRASS.get()).add(VSItems.POTTED_PAINTING_SAPLING.get())
                .add(VSItems.POTTED_GOLDEN_CARROTS.get()).add(VSItems.POTTED_ENDER_ROOTS.get()).add(VSItems.POTTED_ENDER_FUNGUS.get()).add(VSItems.POTTED_NETHER_WART.get()).add(VSItems.POTTED_WARPED_WART.get())
                .add(VSItems.POTTED_ENDER_WART.get());

        // Bowl/Bucket Foods
        this.tag(VSTags.Items.BOWL_FOODS_MUSHROOM).add(Items.MUSHROOM_STEW);
        this.tag(VSTags.Items.BOWL_FOODS_BEETROOT).add(Items.BEETROOT_SOUP);
        this.tag(VSTags.Items.BOWL_FOODS_RABBIT).add(Items.RABBIT_STEW);
        this.tag(VSTags.Items.BOWL_FOODS_FUNGI).add(VSItems.FUNGI_STEW.get());
        this.tag(VSTags.Items.BOWL_FOODS_END_FUNGI).add(VSItems.END_FUNGI_STEW.get());
        this.tag(VSTags.Items.BOWL_FOODS_ALJAN_FUNGI).addOptional(backMath("aljan_fungi_stew"));

        this.tag(VSTags.Items.BOWL_FOODS).addTag(VSTags.Items.BOWL_FOODS_MUSHROOM).addTag(VSTags.Items.BOWL_FOODS_BEETROOT).addTag(VSTags.Items.BOWL_FOODS_RABBIT).addTag(VSTags.Items.BOWL_FOODS_FUNGI)
                .addTag(VSTags.Items.BOWL_FOODS_END_FUNGI).addTag(VSTags.Items.BOWL_FOODS_ALJAN_FUNGI);

        this.tag(VSTags.Items.PAINTING_LOGS).add(VSItems.PAINTING_LOG.get()).add(VSItems.PAINTING_WOOD.get()).add(VSItems.STRIPPED_PAINTING_LOG.get())
                .add(VSItems.STRIPPED_PAINTING_WOOD.get());
        this.tag(VSTags.Items.ENDERWOOD_STEMS).add(VSItems.ENDERWOOD_STEM.get()).add(VSItems.ENDERWOOD_HYPHAE.get()).add(VSItems.STRIPPED_ENDERWOOD_STEM.get())
                .add(VSItems.STRIPPED_ENDERWOOD_HYPHAE.get());

        // Melony Tags
        this.tag(VSTags.Items.BOOKS).addTag(ItemTags.LECTERN_BOOKS).add(Items.BOOK).add(Items.ENCHANTED_BOOK).add(Items.KNOWLEDGE_BOOK).add(VSItems.ENCHANTED_KNOWLEDGE_BOOK.get())
                .addOptional(backMath("regular_molds_book")).addOptional(backMath("advanced_molds_book")).addOptional(new ResourceLocation("ftbquests:book"));
        this.tag(VSTags.Items.BOOKSHELVES).add(Items.BOOKSHELF).add(VSItems.ENDERWOOD_BOOKSHELF.get()).add(VSItems.PLAIN_BIRCH_BOOKSHELF.get());
        this.tag(VSTags.Items.SHIELDS).add(Items.SHIELD).add(VSWeaponry.EMPTY_ARMOR_SLOT_SHIELD.get()).addOptional(backMath("devil_shield")).addOptional(backMath("angelic_shield")).addOptional(backMath("mid_term_shield"))
                .addOptional(backMath("aljameed_shield")).addOptional(backMath("moonering_shield"));
        this.tag(VSTags.Items.CATLIKE_TAME_ITEMS).add(Items.COD).add(Items.SALMON).add(Items.TROPICAL_FISH).add(VSItems.RAW_FISH.get());
        this.tag(VSTags.Items.FISHING_BOWLS).add(Items.BOWL).add(VSItems.OAK_BOWL.get()).add(VSItems.SPRUCE_BOWL.get()).add(VSItems.BIRCH_BOWL.get()).add(VSItems.JUNGLE_BOWL.get()).add(VSItems.ACACIA_BOWL.get()).add(VSItems.DARK_OAK_BOWL.get())
                .add(VSItems.PAINTING_BOWL.get());
        this.tag(VSTags.Items.WOODEN_BOWLS).add(Items.BOWL).add(VSItems.OAK_BOWL.get()).add(VSItems.SPRUCE_BOWL.get()).add(VSItems.BIRCH_BOWL.get()).add(VSItems.JUNGLE_BOWL.get()).add(VSItems.ACACIA_BOWL.get()).add(VSItems.DARK_OAK_BOWL.get())
                .add(VSItems.PAINTING_BOWL.get()).add(VSItems.CRIMSON_BOWL.get()).add(VSItems.WARPED_BOWL.get()).add(VSItems.ENDERWOOD_BOWL.get());
        this.tag(VSTags.Items.BOWLS).addTag(VSTags.Items.WOODEN_BOWLS).addTag(VSTags.Items.FISHING_BOWLS);

        // Minecraft Tags
        this.tag(ItemTags.LOGS_THAT_BURN).addTag(VSTags.Items.PAINTING_LOGS).addTag(VSTags.Items.ENDERWOOD_STEMS);
        this.tag(ItemTags.LEAVES).add(VSItems.PAINTING_LEAVES.get());
        this.tag(ItemTags.SAPLINGS).add(VSItems.PAINTING_SAPLING.get());
        this.tag(ItemTags.PLANKS).add(VSItems.PAINTING_PLANKS.get()).add(VSItems.ENDERWOOD_PLANKS.get());
        this.tag(ItemTags.WOODEN_STAIRS).add(VSItems.PAINTING_STAIRS.get()).add(VSItems.ENDERWOOD_STAIRS.get());
        this.tag(ItemTags.WOODEN_SLABS).add(VSItems.PAINTING_SLAB.get()).add(VSItems.ENDERWOOD_SLAB.get());
        this.tag(ItemTags.WOODEN_FENCES).add(VSItems.PAINTING_FENCE.get()).add(VSItems.ENDERWOOD_FENCE.get());
        this.tag(ItemTags.WOODEN_PRESSURE_PLATES).add(VSItems.PAINTING_PRESSURE_PLATE.get()).add(VSItems.ENDERWOOD_PRESSURE_PLATE.get());
        this.tag(ItemTags.WOODEN_BUTTONS).add(VSItems.PAINTING_BUTTON.get()).add(VSItems.ENDERWOOD_BUTTON.get());
        this.tag(ItemTags.WOODEN_DOORS).addTag(VSTags.Items.PAINTING_DOORS).add(VSItems.PAINTING_DOOR.get()).add(VSItems.ENDERWOOD_DOOR.get());
        this.tag(ItemTags.WOODEN_TRAPDOORS).addTag(VSTags.Items.PAINTING_TRAPDOORS).add(VSItems.PAINTING_TRAPDOOR.get()).add(VSItems.ENDERWOOD_TRAPDOOR.get());
        this.tag(ItemTags.SIGNS).add(VSItems.PAINTING_SIGN.get()).add(VSItems.ENDERWOOD_SIGN.get());

        this.tag(ItemTags.STAIRS).add(VSItems.ELDER_PRISMARINE_STAIRS.get()).add(VSItems.ELDER_PRISMARINE_BRICK_STAIRS.get()).add(VSItems.DARK_ELDER_PRISMARINE_STAIRS.get());
        this.tag(ItemTags.SLABS).add(VSItems.ELDER_PRISMARINE_SLAB.get()).add(VSItems.ELDER_PRISMARINE_BRICK_SLAB.get()).add(VSItems.DARK_ELDER_PRISMARINE_SLAB.get());
        this.tag(ItemTags.WALLS).add(VSItems.ELDER_PRISMARINE_WALL.get());
        this.tag(ItemTags.FENCES).add(VSItems.RED_NETHER_BRICK_FENCE.get());

        this.tag(ItemTags.SMALL_FLOWERS).add(VSItems.GLOW_BLACK_TULIP.get()).add(VSItems.SUNNY_FLOWER.get());
        this.tag(ItemTags.WOOL).add(VSItems.GLOW_BLACK_WOOL.get());
        this.tag(ItemTags.CARPETS).add(VSItems.GLOW_BLACK_CARPET.get());
        this.tag(ItemTags.BEDS).add(VSItems.GLOW_BLACK_BED.get());
        this.tag(ItemTags.CREEPER_DROP_MUSIC_DISCS).add(VSItems.MUSIC_DISC_DOG.get());
        this.tag(ItemTags.PIGLIN_LOVED).add(VSItems.GOLDEN_CAULDRON.get()).add(VSItems.GOLDEN_BEACON.get()).add(VSItems.POTTED_GOLDEN_CARROTS.get()).add(VSItems.GOLDEN_CARROTS.get()).add(VSItems.GOLDEN_CHAIN.get())
                .add(VSItems.POWERED_TIE.get()).add(VSWeaponry.GOLDEN_SHEARS.get()).add(VSWeaponry.ALICE_AI_SHEARS.get());
        this.tag(ItemTags.BOATS).add(VSItems.PAINTING_BOAT.get()).add(VSItems.CRIMSON_BOAT.get()).add(VSItems.WARPED_BOAT.get()).add(VSItems.ENDERWOOD_BOAT.get());
    }

    private static ResourceLocation backMath(String item) {
        return new ResourceLocation("backmath", item);
    }
}
