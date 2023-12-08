package com.junethewoods.variants.util.tab;

import com.junethewoods.variants.Variants;
import com.junethewoods.variants.config.VSConfigs;
import com.junethewoods.variants.item.VSItems;
import com.junethewoods.variants.item.VSWeaponry;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static com.junethewoods.variants.item.custom.armor.WoolArmorItem.COLOR_NAME_TO_CODE;

public class VSCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Variants.MOD_ID);

    public static final Supplier<CreativeModeTab> BLOCKS_TAB = CREATIVE_TABS.register("variants_blocks", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
            .icon(() -> new ItemStack(VSItems.PAINTING_DOOR_WANDERER.get()))
            .title(Component.translatable("creative_mode_tab.variants.blocks"))
            .displayItems((parameters, output) -> {
                output.accept(VSItems.PLAIN_BIRCH_BOOKSHELF.get());
                output.accept(VSItems.PAINTING_LOG.get());
                output.accept(VSItems.PAINTING_WOOD.get());
                output.accept(VSItems.STRIPPED_PAINTING_LOG.get());
                output.accept(VSItems.STRIPPED_PAINTING_WOOD.get());
                output.accept(VSItems.PAINTING_LEAVES.get());
                output.accept(VSItems.PAINTING_PLANKS.get());
                output.accept(VSItems.PAINTING_STAIRS.get());
                output.accept(VSItems.PAINTING_SLAB.get());
                output.accept(VSItems.PAINTING_FENCE.get());
                output.accept(VSItems.PAINTING_FENCE_GATE.get());
                output.accept(VSItems.PAINTING_PRESSURE_PLATE.get());
                output.accept(VSItems.PAINTING_BUTTON.get());
                output.accept(VSItems.PAINTING_TRAPDOOR.get());
                output.accept(VSItems.PAINTING_TRAPDOOR_ALBAN.get());
                output.accept(VSItems.PAINTING_TRAPDOOR_AZTEC.get());
                output.accept(VSItems.PAINTING_TRAPDOOR_AZTEC2.get());
                output.accept(VSItems.PAINTING_TRAPDOOR_BOMB.get());
                output.accept(VSItems.PAINTING_TRAPDOOR_KEBAB.get());
                output.accept(VSItems.PAINTING_TRAPDOOR_PLANT.get());
                output.accept(VSItems.PAINTING_TRAPDOOR_WASTELAND.get());
                output.accept(VSItems.PAINTING_DOOR.get());
                output.accept(VSItems.PAINTING_DOOR_WANDERER.get());
                output.accept(VSItems.PAINTING_DOOR_GRAHAM.get());
                output.accept(VSItems.PAINTING_DOOR_FIRST.get());
                output.accept(VSItems.PAINTING_SIGN.get());
                output.accept(VSItems.ENDERWOOD_STEM.get());
                output.accept(VSItems.ENDERWOOD_HYPHAE.get());
                output.accept(VSItems.STRIPPED_ENDERWOOD_STEM.get());
                output.accept(VSItems.STRIPPED_ENDERWOOD_HYPHAE.get());
                output.accept(VSItems.ENDER_WART_BLOCK.get());
                output.accept(VSItems.ENDER_NYLIUM.get());
                output.accept(VSItems.ENDER_FARMLAND.get());
                output.accept(VSItems.ENDERWOOD_PLANKS.get());
                output.accept(VSItems.ENDERWOOD_STAIRS.get());
                output.accept(VSItems.ENDERWOOD_SLAB.get());
                output.accept(VSItems.ENDERWOOD_FENCE.get());
                output.accept(VSItems.ENDERWOOD_FENCE_GATE.get());
                output.accept(VSItems.ENDERWOOD_PRESSURE_PLATE.get());
                output.accept(VSItems.ENDERWOOD_BUTTON.get());
                output.accept(VSItems.ENDERWOOD_DOOR.get());
                output.accept(VSItems.ENDERWOOD_SIGN.get());
                output.accept(VSItems.ELDER_SEA_LANTERN.get());
                output.accept(VSItems.ELDER_PRISMARINE.get());
                output.accept(VSItems.ELDER_PRISMARINE_STAIRS.get());
                output.accept(VSItems.ELDER_PRISMARINE_SLAB.get());
                output.accept(VSItems.ELDER_PRISMARINE_WALL.get());
                output.accept(VSItems.ELDER_PRISMARINE_BRICKS.get());
                output.accept(VSItems.ELDER_PRISMARINE_BRICK_STAIRS.get());
                output.accept(VSItems.ELDER_PRISMARINE_BRICK_SLAB.get());
                output.accept(VSItems.DARK_ELDER_PRISMARINE.get());
                output.accept(VSItems.DARK_ELDER_PRISMARINE_STAIRS.get());
                output.accept(VSItems.DARK_ELDER_PRISMARINE_SLAB.get());
                output.accept(VSItems.CHISELED_END_STONE_BRICKS.get());
                output.accept(VSItems.INFESTED_CHISELED_END_STONE_BRICKS.get());
                output.accept(VSItems.CHISELED_PURPUR_BLOCK.get());
                output.accept(VSItems.INFESTED_CHISELED_PURPUR_BLOCK.get());
                output.accept(VSItems.RED_NETHER_BRICK_FENCE.get());
                output.accept(VSItems.WITHER_BONE_BLOCK.get());
                output.accept(VSItems.RAW_DEBRIS_BLOCK.get());
                output.accept(VSItems.QUARTZ_ORE.get());
                output.accept(VSItems.DEEPSLATE_QUARTZ_ORE.get());
                output.accept(VSItems.END_QUARTZ_ORE.get());
                output.accept(VSItems.GOLDEN_CAULDRON.get());
                output.accept(VSItems.GOLDEN_BEACON.get());
                output.accept(VSItems.DIAMOND_BELL.get());
                output.accept(VSItems.GLOW_BLACK_WOOL.get());
                output.accept(VSItems.GLOW_BLACK_CARPET.get());
                output.accept(VSItems.GLOW_BLACK_BED.get());
                output.accept(VSItems.GLOW_BLACK_STAINED_GLASS.get());
                output.accept(VSItems.GLOW_BLACK_STAINED_GLASS_PANE.get());
                output.accept(VSItems.QUARTZ_GLASS.get());
                output.accept(VSItems.QUARTZ_GLASS_PANE.get());
                output.accept(VSItems.QUARTZ_BARS.get());
                output.accept(VSItems.QUARTZ_CHAIN.get());
                output.accept(VSItems.GOLDEN_CHAIN.get());
                output.accept(VSItems.DIAMOND_CHAIN.get());
                output.accept(VSItems.EMERALD_CHAIN.get());
                output.accept(VSItems.POTTED_SUGAR_CANE.get());
                output.accept(VSItems.POTTED_GLOW_BLACK_TULIP.get());
                output.accept(VSItems.POTTED_SUNNY_FLOWER.get());
                output.accept(VSItems.POTTED_PAINTING_SAPLING.get());
                output.accept(VSItems.GOLDEN_CARROTS.get());
                output.accept(VSItems.GLOW_BLACK_TULIP.get());
                output.accept(VSItems.SUNNY_FLOWER.get());
                output.accept(VSItems.GLOW_BERRY_BUSH.get());
                output.accept(VSItems.PAINTING_SAPLING.get());
                output.accept(VSItems.ENDER_ROOTS.get());
                output.accept(VSItems.END_SPROUTS.get());
                output.accept(VSItems.ENDER_FUNGUS.get());
                output.accept(VSItems.WARPING_VINES.get());
                output.accept(VSItems.WARPED_WART.get());
                output.accept(VSItems.ENDER_WART.get());
            }).build());

    public static final Supplier<CreativeModeTab> MAIN_TAB = CREATIVE_TABS.register("variants_main", () -> CreativeModeTab.builder()
            .withTabsBefore(Variants.resourceLoc("variants_blocks"))
            .icon(() -> new ItemStack(VSItems.CYAN_SHULKER_SHELL.get()))
            .title(Component.translatable("creative_mode_tab.variants.main"))
            .displayItems((parameters, output) -> {
                output.accept(VSItems.WHITE_SHULKER_SHELL.get());
                output.accept(VSItems.INNO_SHULKER_SHELL.get());
                output.accept(VSItems.ORANGE_SHULKER_SHELL.get());
                output.accept(VSItems.MAGENTA_SHULKER_SHELL.get());
                output.accept(VSItems.LIGHT_BLUE_SHULKER_SHELL.get());
                output.accept(VSItems.GLOW_BLACK_SHULKER_SHELL.get());
                output.accept(VSItems.YELLOW_SHULKER_SHELL.get());
                output.accept(VSItems.LIME_SHULKER_SHELL.get());
                output.accept(VSItems.PINK_SHULKER_SHELL.get());
                output.accept(VSItems.GRAY_SHULKER_SHELL.get());
                output.accept(VSItems.LIGHT_GRAY_SHULKER_SHELL.get());
                output.accept(VSItems.CYAN_SHULKER_SHELL.get());
                output.accept(VSItems.PURPLE_SHULKER_SHELL.get());
                output.accept(VSItems.BLUE_SHULKER_SHELL.get());
                output.accept(VSItems.BROWN_SHULKER_SHELL.get());
                output.accept(VSItems.GREEN_SHULKER_SHELL.get());
                output.accept(VSItems.RED_SHULKER_SHELL.get());
                output.accept(VSItems.BLACK_SHULKER_SHELL.get());

                output.accept(VSItems.MUSIC_DISC_DOG.get());
                output.accept(VSItems.SPAWNER_MINECART.get());
                output.accept(VSItems.FISH_BUCKET.get());
                output.accept(VSItems.FISH_SPAWN_EGG.get());
                output.accept(VSItems.FUNGI_STEW.get());
                output.accept(VSItems.END_FUNGI_STEW.get());
                output.accept(VSItems.PAINTING_BOAT.get());
                output.accept(VSItems.CRIMSON_BOAT.get());
                output.accept(VSItems.WARPED_BOAT.get());
                output.accept(VSItems.SPLASH_EXPERIENCE_BOTTLE.get());
                output.accept(VSItems.LINGERING_EXPERIENCE_BOTTLE.get());
                output.accept(VSItems.SPLASH_DRAGON_BREATH.get());
                output.accept(VSItems.LINGERING_DRAGON_BREATH.get());
                output.accept(VSItems.SPLASH_GLASS_BOTTLE.get());
                output.accept(VSItems.LINGERING_GLASS_BOTTLE.get());
                output.accept(VSItems.LAVA_BOTTLE.get());
                output.accept(VSItems.SOUL_LAVA_BOTTLE.get());
                output.accept(VSItems.MILK_BOTTLE.get());
                output.accept(VSItems.POWDER_SNOW_BOTTLE.get());
                output.accept(VSItems.SPLASH_SOPHIE_POTION.get());
                output.accept(VSItems.STYLISED_POT.get());
                output.accept(VSItems.REDSTONE_POT.get());
                output.accept(VSItems.BLUESTONE_POT.get());
                output.accept(VSItems.GLOWSTONE_POT.get());
                output.accept(VSItems.GUNPOWDER_POT.get());
                output.accept(VSItems.EXPLOSIVE_BLEND_POT.get());
                output.accept(VSItems.SUGAR_POT.get());
                output.accept(VSItems.SWEET_BERRY_POT.get());
                output.accept(VSItems.GLOW_BERRY_POT.get());
                output.accept(VSItems.HONEY_BALL.get());
                output.accept(VSItems.RAW_FISH.get());
                output.accept(VSItems.COOKED_FISH.get());
                output.accept(VSItems.RAW_DEBRIS.get());
                output.accept(Items.STICK);
                output.accept(Items.DEBUG_STICK);
                output.accept(VSItems.OAK_STICK.get());
                output.accept(VSItems.SPRUCE_STICK.get());
                output.accept(VSItems.BIRCH_STICK.get());
                output.accept(VSItems.JUNGLE_STICK.get());
                output.accept(VSItems.ACACIA_STICK.get());
                output.accept(VSItems.DARK_OAK_STICK.get());
                output.accept(VSItems.PAINTING_STICK.get());
                output.accept(VSItems.CRIMSON_STICK.get());
                output.accept(VSItems.WARPED_STICK.get());
                output.accept(VSItems.ENDERWOOD_STICK.get());
                output.accept(VSItems.NETHERITE_ROD.get());
                output.accept(Items.BOWL);
                output.accept(VSItems.OAK_BOWL.get());
                output.accept(VSItems.SPRUCE_BOWL.get());
                output.accept(VSItems.BIRCH_BOWL.get());
                output.accept(VSItems.JUNGLE_BOWL.get());
                output.accept(VSItems.ACACIA_BOWL.get());
                output.accept(VSItems.DARK_OAK_BOWL.get());
                output.accept(VSItems.PAINTING_BOWL.get());
                output.accept(VSItems.CRIMSON_BOWL.get());
                output.accept(VSItems.WARPED_BOWL.get());
                output.accept(VSItems.ENDERWOOD_BOWL.get());
                output.accept(VSItems.SOUL_BLAZE_ROD.get());
                output.accept(VSItems.SOUL_BLAZE_POWDER.get());
                output.accept(VSItems.SOUL_O_CHARGE.get());
                output.accept(VSItems.RED_NETHER_BRICK.get());
                output.accept(VSItems.EXPOSED_COPPER_INGOT.get());
                output.accept(VSItems.WEATHERED_COPPER_INGOT.get());
                output.accept(VSItems.OXIDIZED_COPPER_INGOT.get());
                output.accept(VSItems.QUARTZ_SHARD.get());
                output.accept(VSItems.PURPLE_IRON_NUGGET.get());
                output.accept(VSItems.GLOW_BLACK_DYE.get());
                output.accept(VSItems.ELDER_PRISMARINE_SHARD.get());
                output.accept(VSItems.ELDER_PRISMARINE_CRYSTALS.get());
                output.accept(VSItems.WITHER_BONE.get());
                output.accept(VSItems.WITHER_BONE_MEAL.get());
                output.accept(VSItems.ENCHANTED_KNOWLEDGE_BOOK.get());
            }).build());

    public static final Supplier<CreativeModeTab> WEAPONRY_TAB = CREATIVE_TABS.register("variants_weaponry", () -> CreativeModeTab.builder()
            .withTabsBefore(Variants.resourceLoc("variants_main"))
            .icon(() -> new ItemStack(VSWeaponry.PHANTOM_MEMBRANE_TUNIC.get()))
            .title(Component.translatable("creative_mode_tab.variants.weaponry"))
            .displayItems((parameters, output) -> {
                output.accept(VSWeaponry.DIORITE_SWORD.get());
                output.accept(VSWeaponry.DIORITE_PICKAXE.get());
                output.accept(VSWeaponry.DIORITE_SHOVEL.get());
                output.accept(VSWeaponry.DIORITE_AXE.get());
                output.accept(VSWeaponry.DIORITE_HOE.get());
                output.accept(VSWeaponry.GRANITE_SWORD.get());
                output.accept(VSWeaponry.GRANITE_PICKAXE.get());
                output.accept(VSWeaponry.GRANITE_SHOVEL.get());
                output.accept(VSWeaponry.GRANITE_AXE.get());
                output.accept(VSWeaponry.GRANITE_HOE.get());
                output.accept(VSWeaponry.ANDESITE_SWORD.get());
                output.accept(VSWeaponry.ANDESITE_PICKAXE.get());
                output.accept(VSWeaponry.ANDESITE_SHOVEL.get());
                output.accept(VSWeaponry.ANDESITE_AXE.get());
                output.accept(VSWeaponry.ANDESITE_HOE.get());
                output.accept(VSWeaponry.END_STONE_SWORD.get());
                output.accept(VSWeaponry.END_STONE_PICKAXE.get());
                output.accept(VSWeaponry.END_STONE_SHOVEL.get());
                output.accept(VSWeaponry.END_STONE_AXE.get());
                output.accept(VSWeaponry.END_STONE_HOE.get());
                output.accept(VSWeaponry.QUARTZ_SWORD.get());
                output.accept(VSWeaponry.QUARTZ_PICKAXE.get());
                output.accept(VSWeaponry.QUARTZ_SHOVEL.get());
                output.accept(VSWeaponry.QUARTZ_AXE.get());
                output.accept(VSWeaponry.QUARTZ_HOE.get());
                output.accept(VSWeaponry.MAGMA_SWORD.get());
                output.accept(VSWeaponry.AMETHYST_SWORD.get());
                output.accept(VSWeaponry.COPPER_SWORD.get());
                output.accept(VSWeaponry.DEBUG_BOW.get());
                output.accept(VSWeaponry.REDSTONE_SHEARS.get());
                output.accept(VSWeaponry.COAL_SHEARS.get());
                output.accept(VSWeaponry.COPPER_SHEARS.get());
                output.accept(VSWeaponry.EXPOSED_COPPER_SHEARS.get());
                output.accept(VSWeaponry.WEATHERED_COPPER_SHEARS.get());
                output.accept(VSWeaponry.OXIDIZED_COPPER_SHEARS.get());
                output.accept(VSWeaponry.AMETHYST_SHEARS.get());
                output.accept(VSWeaponry.DIAMOND_SHEARS.get());
                output.accept(VSWeaponry.GOLDEN_SHEARS.get());
                output.accept(VSWeaponry.NETHERITE_SHEARS.get());
                output.accept(VSWeaponry.LAPIS_LAZULI_SHEARS.get());
                output.accept(VSWeaponry.EMERALD_SHEARS.get());
                output.accept(VSWeaponry.QUARTZ_SHEARS.get());
                output.accept(VSWeaponry.RUBY_SHEARS.get());
                output.accept(VSWeaponry.PLASTEEL_SHEARS.get());
                output.accept(VSWeaponry.DIAEMERALD_SHEARS.get());
                output.accept(VSWeaponry.MAGENTIC_CRYSTAL_SHEARS.get());
                output.accept(VSWeaponry.MAGENTIC_SHEARS.get());
                output.accept(VSWeaponry.ALAN_AI_SHEARS.get());
                output.accept(VSWeaponry.ALICE_AI_SHEARS.get());
                output.accept(VSWeaponry.INNO_AI_SHEARS.get());
                output.accept(VSWeaponry.NICOLAS_AI_SHEARS.get());
                output.accept(VSWeaponry.QUARTZ_HELMET.get());
                output.accept(VSWeaponry.QUARTZ_CHESTPLATE.get());
                output.accept(VSWeaponry.QUARTZ_LEGGINGS.get());
                output.accept(VSWeaponry.QUARTZ_BOOTS.get());
                output.accept(VSWeaponry.QUARTZ_HORSE_ARMOR.get());
                output.accept(VSWeaponry.EMERALD_HELMET.get());
                output.accept(VSWeaponry.EMERALD_CHESTPLATE.get());
                output.accept(VSWeaponry.EMERALD_LEGGINGS.get());
                output.accept(VSWeaponry.EMERALD_BOOTS.get());
                output.accept(VSWeaponry.EMPTY_ARMOR_SLOT_HELMET.get());
                output.accept(VSWeaponry.EMPTY_ARMOR_SLOT_CHESTPLATE.get());
                output.accept(VSWeaponry.EMPTY_ARMOR_SLOT_LEGGINGS.get());
                output.accept(VSWeaponry.EMPTY_ARMOR_SLOT_BOOTS.get());
                output.accept(VSWeaponry.COPPER_CHESTPLATE.get());
                output.accept(VSWeaponry.PHANTOM_MEMBRANE_TUNIC.get());
                output.accept(VSWeaponry.RABBIT_HIDE_TUNIC.get());
                output.accept(VSWeaponry.WOOL_SWEATER.get());

                if (VSConfigs.populateWoolArmorColorInTabs) {
                    for (String i : COLOR_NAME_TO_CODE.keySet()) {
                        ItemStack stack = new ItemStack(VSWeaponry.WOOL_SWEATER.get());
                        CompoundTag displayTag = stack.getOrCreateTagElement("display");
                        CompoundTag tag = stack.getOrCreateTag();

                        displayTag.putInt("color", COLOR_NAME_TO_CODE.get(i));
                        tag.putString("color_name", i);
                        output.accept(stack);
                    }
                }

                output.accept(VSWeaponry.IRON_SPYGLASS.get());
                output.accept(VSWeaponry.DIAMOND_SPYGLASS.get());
                output.accept(VSWeaponry.NETHERITE_SPYGLASS.get());
            }).build());
}
