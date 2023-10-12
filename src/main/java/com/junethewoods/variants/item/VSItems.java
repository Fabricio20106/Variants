package com.junethewoods.variants.item;

import com.junethewoods.variants.Variants;
import com.junethewoods.variants.block.VSBlocks;
import com.junethewoods.variants.util.tab.VSTab;
import com.junethewoods.variants.item.custom.HoneyBallItem;
import com.junethewoods.variants.item.custom.LavaBottleItem;
import com.junethewoods.variants.item.custom.MilkBottleItem;
import net.minecraft.block.Blocks;
import net.minecraft.entity.item.minecart.AbstractMinecartEntity;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class VSItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Variants.MOD_ID);

    // Blocks
    public static final RegistryObject<Item> RAW_DEBRIS_BLOCK = ITEMS.register("raw_debris_block", () -> new BlockItem(VSBlocks.RAW_DEBRIS_BLOCK.get(), new Item.Properties().fireResistant().tab(VSTab.TAB)));
    public static final RegistryObject<Item> PAINTING_PLANKS = ITEMS.register("painting_planks", () -> new BlockItem(VSBlocks.PAINTING_PLANKS.get(), new Item.Properties().tab(VSTab.TAB)));
    public static final RegistryObject<Item> PAINTING_STAIRS = ITEMS.register("painting_stairs", () -> new BlockItem(VSBlocks.PAINTING_STAIRS.get(), new Item.Properties().tab(VSTab.TAB)));
    public static final RegistryObject<Item> PAINTING_SLAB = ITEMS.register("painting_slab", () -> new BlockItem(VSBlocks.PAINTING_SLAB.get(), new Item.Properties().tab(VSTab.TAB)));
    public static final RegistryObject<Item> PAINTING_FENCE = ITEMS.register("painting_fence", () -> new BlockItem(VSBlocks.PAINTING_FENCE.get(), new Item.Properties().tab(VSTab.TAB)));
    public static final RegistryObject<Item> PAINTING_FENCE_GATE = ITEMS.register("painting_fence_gate", () -> new BlockItem(VSBlocks.PAINTING_FENCE_GATE.get(), new Item.Properties().tab(VSTab.TAB)));
    public static final RegistryObject<Item> PAINTING_PRESSURE_PLATE = ITEMS.register("painting_pressure_plate", () -> new BlockItem(VSBlocks.PAINTING_PRESSURE_PLATE.get(), new Item.Properties().tab(VSTab.TAB)));
    public static final RegistryObject<Item> PAINTING_BUTTON = ITEMS.register("painting_button", () -> new BlockItem(VSBlocks.PAINTING_BUTTON.get(), new Item.Properties().tab(VSTab.TAB)));
    public static final RegistryObject<Item> PAINTING_TRAPDOOR = ITEMS.register("painting_trapdoor", () -> new BlockItem(VSBlocks.PAINTING_TRAPDOOR.get(), new Item.Properties().tab(VSTab.TAB)));
    public static final RegistryObject<Item> PAINTING_DOOR = ITEMS.register("painting_door", () -> new BlockItem(VSBlocks.PAINTING_DOOR.get(), new Item.Properties().tab(VSTab.TAB)));
    public static final RegistryObject<Item> WANDERER_DOOR = ITEMS.register("wanderer_door", () -> new BlockItem(VSBlocks.WANDERER_DOOR.get(), new Item.Properties().tab(VSTab.TAB)));
    public static final RegistryObject<Item> QUARTZ_ORE = ITEMS.register("quartz_ore", () -> new BlockItem(VSBlocks.QUARTZ_ORE.get(), new Item.Properties().tab(VSTab.TAB)));
    public static final RegistryObject<Item> END_QUARTZ_ORE = ITEMS.register("end_quartz_ore", () -> new BlockItem(VSBlocks.END_QUARTZ_ORE.get(), new Item.Properties().tab(VSTab.TAB)));
    public static final RegistryObject<Item> GOLDEN_CAULDRON = ITEMS.register("gold_cauldron", () -> new BlockItem(VSBlocks.GOLDEN_CAULDRON.get(), new Item.Properties().rarity(Rarity.UNCOMMON).tab(VSTab.TAB)));
    public static final RegistryObject<Item> GOLDEN_BEACON = ITEMS.register("gold_beacon", () -> new BlockItem(VSBlocks.GOLDEN_BEACON.get(), new Item.Properties().rarity(Rarity.UNCOMMON).tab(VSTab.TAB)));
    public static final RegistryObject<Item> GOLDEN_CARROTS = ITEMS.register("golden_carrots", () -> new BlockItem(VSBlocks.GOLDEN_CARROTS.get(), new Item.Properties().rarity(Rarity.UNCOMMON).tab(VSTab.TAB)));
    public static final RegistryObject<Item> POTTED_SUGAR_CANE = ITEMS.register("potted_sugar_cane", () -> new BlockItem(VSBlocks.POTTED_SUGAR_CANE.get(), new Item.Properties().tab(VSTab.TAB)));
    public static final RegistryObject<Item> POTTED_GLOW_BLACK_TULIP = ITEMS.register("potted_glow_black_tulip", () -> new BlockItem(VSBlocks.POTTED_GLOW_BLACK_TULIP.get(), new Item.Properties().tab(VSTab.TAB)));
    public static final RegistryObject<Item> GLOW_BLACK_TULIP = ITEMS.register("glow_black_tulip", () -> new BlockItem(VSBlocks.GLOW_BLACK_TULIP.get(), new Item.Properties().tab(VSTab.TAB)));
    public static final RegistryObject<Item> GLOW_BERRY_BUSH = ITEMS.register("glow_berry_bush", () -> new BlockItem(VSBlocks.GLOW_BERRY_BUSH.get(), new Item.Properties().tab(VSTab.TAB)));

    // Items
    public static final RegistryObject<Item> CYAN_SHULKER_SHELL = ITEMS.register("cyan_shulker_shell", () -> new Item(new Item.Properties().tab(VSTab.TAB)));
    public static final RegistryObject<Item> SPAWNER_MINECART = ITEMS.register("spawner_minecart", () -> new MinecartItem(AbstractMinecartEntity.Type.SPAWNER, new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(1).tab(VSTab.TAB)));
    public static final RegistryObject<Item> FUNGI_STEW = ITEMS.register("fungi_stew", () -> new SoupItem(new Item.Properties().stacksTo(1).tab(VSTab.TAB).food(Foods.MUSHROOM_STEW)));
    public static final RegistryObject<Item> SPLASH_EXPERIENCE_BOTTLE = ITEMS.register("splash_experience_bottle", () -> new ExperienceBottleItem(new Item.Properties().rarity(Rarity.UNCOMMON).tab(VSTab.TAB)));
    public static final RegistryObject<Item> LINGERING_EXPERIENCE_BOTTLE = ITEMS.register("lingering_experience_bottle", () -> new ExperienceBottleItem(new Item.Properties().rarity(Rarity.UNCOMMON).tab(VSTab.TAB)));
    public static final RegistryObject<Item> ENCHANTED_KNOWLEDGE_BOOK = ITEMS.register("enchanted_knowledge_book", () -> new KnowledgeBookItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> SPLASH_DRAGON_BREATH = ITEMS.register("splash_dragon_breath", () -> new Item(new Item.Properties().craftRemainder(Items.SPLASH_POTION).rarity(Rarity.UNCOMMON).tab(VSTab.TAB)));
    public static final RegistryObject<Item> LINGERING_DRAGON_BREATH = ITEMS.register("lingering_dragon_breath", () -> new Item(new Item.Properties().craftRemainder(Items.LINGERING_POTION).rarity(Rarity.UNCOMMON).tab(VSTab.TAB)));
    public static final RegistryObject<Item> SPLASH_GLASS_BOTTLE = ITEMS.register("splash_glass_bottle", () -> new Item(new Item.Properties().tab(VSTab.TAB)));
    public static final RegistryObject<Item> LINGERING_GLASS_BOTTLE = ITEMS.register("lingering_glass_bottle", () -> new Item(new Item.Properties().tab(VSTab.TAB)));
    public static final RegistryObject<Item> HONEY_BALL = ITEMS.register("honey_ball", () -> new HoneyBallItem(new Item.Properties().food(Foods.HONEY_BOTTLE).tab(VSTab.TAB)));
    public static final RegistryObject<Item> RAW_DEBRIS = ITEMS.register("raw_debris", () -> new Item(new Item.Properties().fireResistant().tab(VSTab.TAB)));
    public static final RegistryObject<Item> OAK_STICK = ITEMS.register("oak_stick", () -> new Item(new Item.Properties().tab(VSTab.TAB)));
    public static final RegistryObject<Item> SPRUCE_STICK = ITEMS.register("spruce_stick", () -> new Item(new Item.Properties().tab(VSTab.TAB)));
    public static final RegistryObject<Item> BIRCH_STICK = ITEMS.register("birch_stick", () -> new Item(new Item.Properties().tab(VSTab.TAB)));
    public static final RegistryObject<Item> JUNGLE_STICK = ITEMS.register("jungle_stick", () -> new Item(new Item.Properties().tab(VSTab.TAB)));
    public static final RegistryObject<Item> ACACIA_STICK = ITEMS.register("acacia_stick", () -> new Item(new Item.Properties().tab(VSTab.TAB)));
    public static final RegistryObject<Item> DARK_OAK_STICK = ITEMS.register("dark_oak_stick", () -> new Item(new Item.Properties().tab(VSTab.TAB)));
    public static final RegistryObject<Item> CRIMSON_STICK = ITEMS.register("crimson_stick", () -> new Item(new Item.Properties().tab(VSTab.TAB)));
    public static final RegistryObject<Item> WARPED_STICK = ITEMS.register("warped_stick", () -> new Item(new Item.Properties().tab(VSTab.TAB)));
    public static final RegistryObject<Item> NETHERITE_ROD = ITEMS.register("netherite_rod", () -> new Item(new Item.Properties().tab(VSTab.TAB).fireResistant()));
    public static final RegistryObject<Item> SOUL_O_CHARGE = ITEMS.register("soul_charge", () -> new FireChargeItem(new Item.Properties().tab(VSTab.TAB)));
    public static final RegistryObject<Item> SOUL_BLAZE_ROD = ITEMS.register("soul_rod", () -> new Item(new Item.Properties().tab(VSTab.TAB)));
    public static final RegistryObject<Item> SOUL_BLAZE_POWDER = ITEMS.register("soul_powder", () -> new Item(new Item.Properties().tab(VSTab.TAB)));
    public static final RegistryObject<Item> EXPOSED_COPPER_INGOT = ITEMS.register("exposed_copper_ingot", () -> new Item(new Item.Properties().tab(VSTab.TAB)));
    public static final RegistryObject<Item> WEATHERED_COPPER_INGOT = ITEMS.register("weathered_copper_ingot", () -> new Item(new Item.Properties().tab(VSTab.TAB)));
    public static final RegistryObject<Item> OXIDIZED_COPPER_INGOT = ITEMS.register("oxidized_copper_ingot", () -> new Item(new Item.Properties().tab(VSTab.TAB)));
    public static final RegistryObject<Item> MILK_BOTTLE = ITEMS.register("milk_glass_bottle", () -> new MilkBottleItem(new Item.Properties().stacksTo(8).tab(VSTab.TAB)));
    public static final RegistryObject<Item> LAVA_BOTTLE = ITEMS.register("lava_glass_bottle", () -> new LavaBottleItem(new Item.Properties().stacksTo(8).tab(VSTab.TAB)));
    public static final RegistryObject<Item> POWDER_SNOW_BOTTLE = ITEMS.register("powder_snow_bottle", () -> new Item(new Item.Properties().stacksTo(8).tab(VSTab.TAB)));
    public static final RegistryObject<Item> GLOW_BLACK_DYE = ITEMS.register("glow_black_dye", () -> new Item(new Item.Properties().tab(VSTab.TAB)));
    public static final RegistryObject<Item> ELDER_PRISMARINE_SHARD = ITEMS.register("elder_prismarine_shard", () -> new Item(new Item.Properties().tab(VSTab.TAB)));
    public static final RegistryObject<Item> ELDER_PRISMARINE_CRYSTALS = ITEMS.register("elder_prismarine_crystals", () -> new Item(new Item.Properties().tab(VSTab.TAB)));
    public static final RegistryObject<Item> WARPED_WART = ITEMS.register("warped_wart", () -> new BlockNamedItem(VSBlocks.WARPED_WART.get(), new Item.Properties().tab(VSTab.TAB)));
}
