package com.junethewoods.variants.item;

import com.junethewoods.variants.Variants;
import com.junethewoods.variants.block.VSBlocks;
import com.junethewoods.variants.item.custom.CompatBlockItem;
import com.junethewoods.variants.item.custom.CompatItem;
import com.junethewoods.variants.item.custom.EnchantedKnowledgeBookItem;
import com.junethewoods.variants.item.custom.PoisoningTypes;
import com.junethewoods.variants.item.custom.food.*;
import com.junethewoods.variants.sound.VSSounds;
import com.junethewoods.variants.util.tab.VSBlockTab;
import com.junethewoods.variants.util.tab.VSTab;
import net.minecraft.entity.item.minecart.AbstractMinecartEntity;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class VSItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Variants.MOD_ID);

    // Blocks
    public static final RegistryObject<Item> PLAIN_BIRCH_BOOKSHELF = ITEMS.register("plain_birch_bookshelf", () -> new CompatBlockItem(VSBlocks.PLAIN_BIRCH_BOOKSHELF.get(), new Item.Properties().tab(VSBlockTab.TAB), "Fabricio2010's Pack"));
    public static final RegistryObject<Item> PAINTING_LOG = ITEMS.register("painting_log", () -> new BlockItem(VSBlocks.PAINTING_LOG.get(), new Item.Properties().tab(VSBlockTab.TAB)));
    public static final RegistryObject<Item> PAINTING_WOOD = ITEMS.register("painting_wood", () -> new BlockItem(VSBlocks.PAINTING_WOOD.get(), new Item.Properties().tab(VSBlockTab.TAB)));
    public static final RegistryObject<Item> STRIPPED_PAINTING_LOG = ITEMS.register("stripped_painting_log", () -> new BlockItem(VSBlocks.STRIPPED_PAINTING_LOG.get(), new Item.Properties().tab(VSBlockTab.TAB)));
    public static final RegistryObject<Item> STRIPPED_PAINTING_WOOD = ITEMS.register("stripped_painting_wood", () -> new BlockItem(VSBlocks.STRIPPED_PAINTING_WOOD.get(), new Item.Properties().tab(VSBlockTab.TAB)));
    public static final RegistryObject<Item> PAINTING_LEAVES = ITEMS.register("painting_leaves", () -> new BlockItem(VSBlocks.PAINTING_LEAVES.get(), new Item.Properties().tab(VSBlockTab.TAB)));
    public static final RegistryObject<Item> PAINTING_PLANKS = ITEMS.register("painting_planks", () -> new BlockItem(VSBlocks.PAINTING_PLANKS.get(), new Item.Properties().tab(VSBlockTab.TAB)));
    public static final RegistryObject<Item> PAINTING_STAIRS = ITEMS.register("painting_stairs", () -> new BlockItem(VSBlocks.PAINTING_STAIRS.get(), new Item.Properties().tab(VSBlockTab.TAB)));
    public static final RegistryObject<Item> PAINTING_SLAB = ITEMS.register("painting_slab", () -> new BlockItem(VSBlocks.PAINTING_SLAB.get(), new Item.Properties().tab(VSBlockTab.TAB)));
    public static final RegistryObject<Item> PAINTING_FENCE = ITEMS.register("painting_fence", () -> new BlockItem(VSBlocks.PAINTING_FENCE.get(), new Item.Properties().tab(VSBlockTab.TAB)));
    public static final RegistryObject<Item> PAINTING_FENCE_GATE = ITEMS.register("painting_fence_gate", () -> new BlockItem(VSBlocks.PAINTING_FENCE_GATE.get(), new Item.Properties().tab(VSBlockTab.TAB)));
    public static final RegistryObject<Item> PAINTING_PRESSURE_PLATE = ITEMS.register("painting_pressure_plate", () -> new BlockItem(VSBlocks.PAINTING_PRESSURE_PLATE.get(), new Item.Properties().tab(VSBlockTab.TAB)));
    public static final RegistryObject<Item> PAINTING_BUTTON = ITEMS.register("painting_button", () -> new BlockItem(VSBlocks.PAINTING_BUTTON.get(), new Item.Properties().tab(VSBlockTab.TAB)));
    public static final RegistryObject<Item> PAINTING_TRAPDOOR = ITEMS.register("painting_trapdoor", () -> new BlockItem(VSBlocks.PAINTING_TRAPDOOR.get(), new Item.Properties().tab(VSBlockTab.TAB)));
    public static final RegistryObject<Item> PAINTING_DOOR = ITEMS.register("painting_door", () -> new BlockItem(VSBlocks.PAINTING_DOOR.get(), new Item.Properties().tab(VSBlockTab.TAB)));
    public static final RegistryObject<Item> WANDERER_DOOR = ITEMS.register("wanderer_door", () -> new BlockItem(VSBlocks.WANDERER_DOOR.get(), new Item.Properties().tab(VSBlockTab.TAB)));
    public static final RegistryObject<Item> GRAHAM_DOOR = ITEMS.register("graham_door", () -> new BlockItem(VSBlocks.GRAHAM_DOOR.get(), new Item.Properties().tab(VSBlockTab.TAB)));
    public static final RegistryObject<Item> FIRST_DOOR = ITEMS.register("first_door", () -> new CompatBlockItem(VSBlocks.FIRST_DOOR.get(), new Item.Properties().tab(VSBlockTab.TAB), "Fabricio2010's Pack"));
    public static final RegistryObject<Item> RAW_DEBRIS_BLOCK = ITEMS.register("raw_debris_block", () -> new BlockItem(VSBlocks.RAW_DEBRIS_BLOCK.get(), new Item.Properties().fireResistant().tab(VSBlockTab.TAB)));
    public static final RegistryObject<Item> QUARTZ_ORE = ITEMS.register("quartz_ore", () -> new BlockItem(VSBlocks.QUARTZ_ORE.get(), new Item.Properties().tab(VSBlockTab.TAB)));
    public static final RegistryObject<Item> END_QUARTZ_ORE = ITEMS.register("end_quartz_ore", () -> new BlockItem(VSBlocks.END_QUARTZ_ORE.get(), new Item.Properties().tab(VSBlockTab.TAB)));
    public static final RegistryObject<Item> GOLDEN_CAULDRON = ITEMS.register("gold_cauldron", () -> new BlockItem(VSBlocks.GOLDEN_CAULDRON.get(), new Item.Properties().tab(VSBlockTab.TAB)));
    public static final RegistryObject<Item> GOLDEN_BEACON = ITEMS.register("gold_beacon", () -> new BlockItem(VSBlocks.GOLDEN_BEACON.get(), new Item.Properties().rarity(Rarity.RARE).tab(VSBlockTab.TAB)));
    public static final RegistryObject<Item> DIAMOND_BELL = ITEMS.register("diamond_bell", () -> new BlockItem(VSBlocks.DIAMOND_BELL.get(), new Item.Properties().tab(VSBlockTab.TAB)));
    public static final RegistryObject<Item> GLOW_BLACK_WOOL = ITEMS.register("glow_black_wool", () -> new BlockItem(VSBlocks.GLOW_BLACK_WOOL.get(), new Item.Properties().tab(VSBlockTab.TAB)));
    public static final RegistryObject<Item> GLOW_BLACK_CARPET = ITEMS.register("glow_black_carpet", () -> new BlockItem(VSBlocks.GLOW_BLACK_CARPET.get(), new Item.Properties().tab(VSBlockTab.TAB)));
    public static final RegistryObject<Item> POTTED_SUGAR_CANE = ITEMS.register("potted_sugar_cane", () -> new BlockItem(VSBlocks.POTTED_SUGAR_CANE.get(), new Item.Properties().tab(VSBlockTab.TAB)));
    public static final RegistryObject<Item> POTTED_GLOW_BLACK_TULIP = ITEMS.register("potted_glow_black_tulip", () -> new BlockItem(VSBlocks.POTTED_GLOW_BLACK_TULIP.get(), new Item.Properties().tab(VSBlockTab.TAB)));
    public static final RegistryObject<Item> POTTED_PAINTING_SAPLING = ITEMS.register("potted_painting_sapling", () -> new BlockItem(VSBlocks.POTTED_PAINTING_SAPLING.get(), new Item.Properties().tab(VSBlockTab.TAB)));
    public static final RegistryObject<Item> GOLDEN_CARROTS = ITEMS.register("golden_carrots", () -> new BlockItem(VSBlocks.GOLDEN_CARROTS.get(), new Item.Properties().rarity(Rarity.UNCOMMON).tab(VSBlockTab.TAB)));
    public static final RegistryObject<Item> GLOW_BLACK_TULIP = ITEMS.register("glow_black_tulip", () -> new BlockItem(VSBlocks.GLOW_BLACK_TULIP.get(), new Item.Properties().tab(VSBlockTab.TAB)));
    public static final RegistryObject<Item> GLOW_BERRY_BUSH = ITEMS.register("glow_berry_bush", () -> new BlockItem(VSBlocks.GLOW_BERRY_BUSH.get(), new Item.Properties().tab(VSBlockTab.TAB)));
    public static final RegistryObject<Item> PAINTING_SAPLING = ITEMS.register("painting_sapling", () -> new BlockItem(VSBlocks.PAINTING_SAPLING.get(), new Item.Properties().tab(VSBlockTab.TAB)));
    public static final RegistryObject<Item> WARPED_WART = ITEMS.register("warped_wart", () -> new BlockNamedItem(VSBlocks.WARPED_WART.get(), new Item.Properties().tab(VSBlockTab.TAB)));

    // Items
    public static final RegistryObject<Item> WHITE_SHULKER_SHELL = ITEMS.register("white_shulker_shell", () -> new Item(new Item.Properties().tab(VSTab.TAB)));
    public static final RegistryObject<Item> INNO_SHULKER_SHELL = ITEMS.register("inno_shulker_shell", () -> new CompatItem(new Item.Properties().tab(VSTab.TAB), "F10 Elements"));
    public static final RegistryObject<Item> ORANGE_SHULKER_SHELL = ITEMS.register("orange_shulker_shell", () -> new Item(new Item.Properties().tab(VSTab.TAB)));
    public static final RegistryObject<Item> MAGENTA_SHULKER_SHELL = ITEMS.register("magenta_shulker_shell", () -> new Item(new Item.Properties().tab(VSTab.TAB)));
    public static final RegistryObject<Item> LIGHT_BLUE_SHULKER_SHELL = ITEMS.register("light_blue_shulker_shell", () -> new Item(new Item.Properties().tab(VSTab.TAB)));
    public static final RegistryObject<Item> GLOW_BLACK_SHULKER_SHELL = ITEMS.register("glow_black_shulker_shell", () -> new Item(new Item.Properties().tab(VSTab.TAB)));
    public static final RegistryObject<Item> YELLOW_SHULKER_SHELL = ITEMS.register("yellow_shulker_shell", () -> new Item(new Item.Properties().tab(VSTab.TAB)));
    public static final RegistryObject<Item> LIME_SHULKER_SHELL = ITEMS.register("lime_shulker_shell", () -> new Item(new Item.Properties().tab(VSTab.TAB)));
    public static final RegistryObject<Item> PINK_SHULKER_SHELL = ITEMS.register("pink_shulker_shell", () -> new Item(new Item.Properties().tab(VSTab.TAB)));
    public static final RegistryObject<Item> GRAY_SHULKER_SHELL = ITEMS.register("gray_shulker_shell", () -> new Item(new Item.Properties().tab(VSTab.TAB)));
    public static final RegistryObject<Item> LIGHT_GRAY_SHULKER_SHELL = ITEMS.register("light_gray_shulker_shell", () -> new Item(new Item.Properties().tab(VSTab.TAB)));
    public static final RegistryObject<Item> CYAN_SHULKER_SHELL = ITEMS.register("cyan_shulker_shell", () -> new Item(new Item.Properties().tab(VSTab.TAB)));
    public static final RegistryObject<Item> PURPLE_SHULKER_SHELL = ITEMS.register("purple_shulker_shell", () -> new Item(new Item.Properties().tab(VSTab.TAB)));
    public static final RegistryObject<Item> BLUE_SHULKER_SHELL = ITEMS.register("blue_shulker_shell", () -> new Item(new Item.Properties().tab(VSTab.TAB)));
    public static final RegistryObject<Item> BROWN_SHULKER_SHELL = ITEMS.register("brown_shulker_shell", () -> new Item(new Item.Properties().tab(VSTab.TAB)));
    public static final RegistryObject<Item> GREEN_SHULKER_SHELL = ITEMS.register("green_shulker_shell", () -> new Item(new Item.Properties().tab(VSTab.TAB)));
    public static final RegistryObject<Item> RED_SHULKER_SHELL = ITEMS.register("red_shulker_shell", () -> new Item(new Item.Properties().tab(VSTab.TAB)));
    public static final RegistryObject<Item> BLACK_SHULKER_SHELL = ITEMS.register("black_shulker_shell", () -> new Item(new Item.Properties().tab(VSTab.TAB)));
    public static final RegistryObject<Item> MUSIC_DISC_DOG = ITEMS.register("music_disc_dog", () -> new MusicDiscItem(2, VSSounds.MUSIC_DISC_DOG, new Item.Properties().rarity(Rarity.RARE).stacksTo(1).tab(VSTab.TAB)));
    public static final RegistryObject<Item> SPAWNER_MINECART = ITEMS.register("spawner_minecart", () -> new MinecartItem(AbstractMinecartEntity.Type.SPAWNER, new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(1).tab(VSTab.TAB)));
    public static final RegistryObject<Item> FUNGI_STEW = ITEMS.register("fungi_stew", () -> new SoupItem(new Item.Properties().stacksTo(1).tab(VSTab.TAB).food(Foods.MUSHROOM_STEW)));
    public static final RegistryObject<Item> SPLASH_EXPERIENCE_BOTTLE = ITEMS.register("splash_experience_bottle", () -> new ExperienceBottleItem(new Item.Properties().rarity(Rarity.UNCOMMON).tab(VSTab.TAB)));
    public static final RegistryObject<Item> LINGERING_EXPERIENCE_BOTTLE = ITEMS.register("lingering_experience_bottle", () -> new ExperienceBottleItem(new Item.Properties().rarity(Rarity.UNCOMMON).tab(VSTab.TAB)));
    public static final RegistryObject<Item> SPLASH_DRAGON_BREATH = ITEMS.register("splash_dragon_breath", () -> new Item(new Item.Properties().craftRemainder(Items.SPLASH_POTION).rarity(Rarity.UNCOMMON).tab(VSTab.TAB)));
    public static final RegistryObject<Item> LINGERING_DRAGON_BREATH = ITEMS.register("lingering_dragon_breath", () -> new Item(new Item.Properties().craftRemainder(Items.LINGERING_POTION).rarity(Rarity.UNCOMMON).tab(VSTab.TAB)));
    public static final RegistryObject<Item> SPLASH_GLASS_BOTTLE = ITEMS.register("splash_glass_bottle", () -> new Item(new Item.Properties().tab(VSTab.TAB)));
    public static final RegistryObject<Item> LINGERING_GLASS_BOTTLE = ITEMS.register("lingering_glass_bottle", () -> new Item(new Item.Properties().tab(VSTab.TAB)));
    public static final RegistryObject<Item> LAVA_BOTTLE = ITEMS.register("lava_glass_bottle", () -> new LavaBottleItem(new Item.Properties().stacksTo(8).craftRemainder(Items.GLASS_BOTTLE).tab(VSTab.TAB)));
    public static final RegistryObject<Item> MILK_BOTTLE = ITEMS.register("milk_glass_bottle", () -> new MilkBottleItem(new Item.Properties().stacksTo(8).craftRemainder(Items.GLASS_BOTTLE).tab(VSTab.TAB)));
    public static final RegistryObject<Item> POWDER_SNOW_BOTTLE = ITEMS.register("powder_snow_bottle", () -> new PowderSnowBottleItem(new Item.Properties().stacksTo(8).craftRemainder(Items.GLASS_BOTTLE).tab(VSTab.TAB)));
    public static final RegistryObject<Item> SPLASH_SOPHIE_POTION = ITEMS.register("splash_soph_potion", () -> new SophiePotionItem(new Item.Properties().food(VSFoods.SOPHIE_POTION).tab(VSTab.TAB), "Edits"));
    public static final RegistryObject<Item> STYLISED_POT = ITEMS.register("stylised_pot", () -> new Item(new Item.Properties().tab(VSTab.TAB)));
    public static final RegistryObject<Item> REDSTONE_POT = ITEMS.register("redstone_pot", () -> new StylisedPotItem(new Item.Properties().stacksTo(16).tab(VSTab.TAB), PoisoningTypes.REDSTONE, 10));
    public static final RegistryObject<Item> BLUESTONE_POT = ITEMS.register("bluestone_pot", () -> new StylisedPotItem(new Item.Properties().stacksTo(16).tab(VSTab.TAB), "Fabricio2010's Pack", PoisoningTypes.BLUESTONE, 10));
    public static final RegistryObject<Item> GLOWSTONE_POT = ITEMS.register("glowstone_pot", () -> new StylisedPotItem(new Item.Properties().stacksTo(16).tab(VSTab.TAB), PoisoningTypes.GLOWSTONE, 10));
    public static final RegistryObject<Item> GUNPOWDER_POT = ITEMS.register("gunpowder_pot", () -> new StylisedPotItem(new Item.Properties().stacksTo(16).tab(VSTab.TAB), PoisoningTypes.GUNPOWDER, 10));
    public static final RegistryObject<Item> CREEPER_POWDER_POT = ITEMS.register("creeper_powder_pot", () -> new StylisedPotItem(new Item.Properties().stacksTo(16).tab(VSTab.TAB), "Creeper Edits", PoisoningTypes.CREEPER_POWDER, 10));
    public static final RegistryObject<Item> SUGAR_POT = ITEMS.register("sugar_pot", () -> new SugarPotItem(new Item.Properties().stacksTo(16).tab(VSTab.TAB)));
    public static final RegistryObject<Item> SWEET_BERRY_POT = ITEMS.register("sweet_berry_pot", () -> new Item(new Item.Properties().stacksTo(16).food(VSFoods.BERRY_POTS).craftRemainder(VSItems.STYLISED_POT.get()).tab(VSTab.TAB)));
    public static final RegistryObject<Item> GLOW_BERRY_POT = ITEMS.register("glow_berry_pot", () -> new Item(new Item.Properties().stacksTo(16).food(VSFoods.BERRY_POTS).craftRemainder(VSItems.STYLISED_POT.get()).tab(VSTab.TAB)));
    public static final RegistryObject<Item> HONEY_BALL = ITEMS.register("honey_ball", () -> new HoneyBallItem(new Item.Properties().food(Foods.HONEY_BOTTLE).tab(VSTab.TAB)));
    public static final RegistryObject<Item> RAW_DEBRIS = ITEMS.register("raw_debris", () -> new Item(new Item.Properties().fireResistant().tab(VSTab.TAB)));
    public static final RegistryObject<Item> OAK_STICK = ITEMS.register("oak_stick", () -> new Item(new Item.Properties().tab(VSTab.TAB)));
    public static final RegistryObject<Item> SPRUCE_STICK = ITEMS.register("spruce_stick", () -> new Item(new Item.Properties().tab(VSTab.TAB)));
    public static final RegistryObject<Item> BIRCH_STICK = ITEMS.register("birch_stick", () -> new Item(new Item.Properties().tab(VSTab.TAB)));
    public static final RegistryObject<Item> JUNGLE_STICK = ITEMS.register("jungle_stick", () -> new Item(new Item.Properties().tab(VSTab.TAB)));
    public static final RegistryObject<Item> ACACIA_STICK = ITEMS.register("acacia_stick", () -> new Item(new Item.Properties().tab(VSTab.TAB)));
    public static final RegistryObject<Item> DARK_OAK_STICK = ITEMS.register("dark_oak_stick", () -> new Item(new Item.Properties().tab(VSTab.TAB)));
    public static final RegistryObject<Item> PAINTING_STICK = ITEMS.register("painting_stick", () -> new Item(new Item.Properties().tab(VSTab.TAB)));
    public static final RegistryObject<Item> CRIMSON_STICK = ITEMS.register("crimson_stick", () -> new Item(new Item.Properties().tab(VSTab.TAB)));
    public static final RegistryObject<Item> WARPED_STICK = ITEMS.register("warped_stick", () -> new Item(new Item.Properties().tab(VSTab.TAB)));
    public static final RegistryObject<Item> NETHERITE_ROD = ITEMS.register("netherite_rod", () -> new Item(new Item.Properties().tab(VSTab.TAB).fireResistant()));
    public static final RegistryObject<Item> SOUL_BLAZE_ROD = ITEMS.register("soul_rod", () -> new Item(new Item.Properties().tab(VSTab.TAB)));
    public static final RegistryObject<Item> SOUL_BLAZE_POWDER = ITEMS.register("soul_powder", () -> new Item(new Item.Properties().tab(VSTab.TAB)));
    public static final RegistryObject<Item> SOUL_O_CHARGE = ITEMS.register("soul_charge", () -> new FireChargeItem(new Item.Properties().tab(VSTab.TAB)));
    public static final RegistryObject<Item> EXPOSED_COPPER_INGOT = ITEMS.register("exposed_copper_ingot", () -> new Item(new Item.Properties().tab(VSTab.TAB)));
    public static final RegistryObject<Item> WEATHERED_COPPER_INGOT = ITEMS.register("weathered_copper_ingot", () -> new Item(new Item.Properties().tab(VSTab.TAB)));
    public static final RegistryObject<Item> OXIDIZED_COPPER_INGOT = ITEMS.register("oxidized_copper_ingot", () -> new Item(new Item.Properties().tab(VSTab.TAB)));
    public static final RegistryObject<Item> PURPLE_IRON_NUGGET = ITEMS.register("purple_nugget", () -> new CompatItem(new Item.Properties().tab(VSTab.TAB), "Edits"));
    public static final RegistryObject<Item> GLOW_BLACK_DYE = ITEMS.register("glow_black_dye", () -> new Item(new Item.Properties().tab(VSTab.TAB)));
    public static final RegistryObject<Item> ELDER_PRISMARINE_SHARD = ITEMS.register("elder_prismarine_shard", () -> new Item(new Item.Properties().tab(VSTab.TAB)));
    public static final RegistryObject<Item> ELDER_PRISMARINE_CRYSTALS = ITEMS.register("elder_prismarine_crystals", () -> new Item(new Item.Properties().tab(VSTab.TAB)));
    public static final RegistryObject<Item> ENCHANTED_KNOWLEDGE_BOOK = ITEMS.register("enchanted_knowledge_book", () -> new EnchantedKnowledgeBookItem(new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(1)));
}
