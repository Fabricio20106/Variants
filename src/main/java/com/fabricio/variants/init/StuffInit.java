package com.fabricio.variants.init;

import com.fabricio.variants.Variants;
import com.fabricio.variants.items.HoneyBallItem;
import com.fabricio.variants.items.LavaBottleItem;
import com.fabricio.variants.items.MilkBottleItem;
import net.minecraft.block.Blocks;
import net.minecraft.entity.item.minecart.AbstractMinecartEntity;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class StuffInit {
    public static final DeferredRegister<Item> items = DeferredRegister.create(ForgeRegistries.ITEMS, Variants.mod_id);

    public static final RegistryObject<Item> raw_debris_block = items.register("raw_debris_block", () -> new BlockItem(BlockInit.raw_debris_block.get(), new Item.Properties().isImmuneToFire().group(VariantsTab.variant)));
    public static final RegistryObject<Item> painting_planks = items.register("painting_planks", () -> new BlockItem(BlockInit.painting_planks.get(), new Item.Properties().group(VariantsTab.variant)));
    public static final RegistryObject<Item> painting_stairs = items.register("painting_stairs", () -> new BlockItem(BlockInit.painting_stairs.get(), new Item.Properties().group(VariantsTab.variant)));
    public static final RegistryObject<Item> painting_slab = items.register("painting_slab", () -> new BlockItem(BlockInit.painting_slab.get(), new Item.Properties().group(VariantsTab.variant)));
    public static final RegistryObject<Item> quartz_ore = items.register("quartz_ore", () -> new BlockItem(BlockInit.quartz_ore.get(), new Item.Properties().group(VariantsTab.variant)));
    public static final RegistryObject<Item> gold_cauldron = items.register("gold_cauldron", () -> new BlockItem(BlockInit.gold_cauldron.get(), new Item.Properties().rarity(Rarity.UNCOMMON).group(VariantsTab.variant)));
    public static final RegistryObject<Item> gold_beacon = items.register("gold_beacon", () -> new BlockItem(BlockInit.gold_beacon.get(), new Item.Properties().rarity(Rarity.UNCOMMON).group(VariantsTab.variant)));
    public static final RegistryObject<Item> golden_carrots = items.register("golden_carrots", () -> new BlockItem(BlockInit.golden_carrots.get(), new Item.Properties().rarity(Rarity.UNCOMMON).group(VariantsTab.variant)));
    public static final RegistryObject<Item> potted_sugar_cane = items.register("potted_sugar_cane", () -> new BlockItem(BlockInit.potted_sugar_cane.get(), new Item.Properties().group(VariantsTab.variant)));
    public static final RegistryObject<Item> potted_glow_black_tulip = items.register("potted_glow_black_tulip", () -> new BlockItem(BlockInit.potted_glow_black_tulip.get(), new Item.Properties().group(VariantsTab.variant)));
    public static final RegistryObject<Item> glow_black_tulip = items.register("glow_black_tulip", () -> new BlockItem(BlockInit.glow_black_tulip.get(), new Item.Properties().group(VariantsTab.variant)));
    public static final RegistryObject<Item> glow_berry_bush = items.register("glow_berry_bush", () -> new BlockItem(BlockInit.glow_berry_bush.get(), new Item.Properties().group(VariantsTab.variant)));
    public static final RegistryObject<Item> cyan_shulker_shell = items.register("cyan_shulker_shell", () -> new Item(new Item.Properties().group(VariantsTab.variant)));
    public static final RegistryObject<Item> spawner_minecart = items.register("spawner_minecart", () -> new MinecartItem(AbstractMinecartEntity.Type.SPAWNER, new Item.Properties().rarity(Rarity.UNCOMMON).maxStackSize(1).group(VariantsTab.variant)));
    public static final RegistryObject<Item> fungi_stew = items.register("fungi_stew", () -> new SoupItem(new Item.Properties().group(VariantsTab.variant).food(Foods.MUSHROOM_STEW)));
    public static final RegistryObject<Item> splash_experience_bottle = items.register("splash_experience_bottle", () -> new ExperienceBottleItem(new Item.Properties().rarity(Rarity.UNCOMMON).group(VariantsTab.variant)));
    public static final RegistryObject<Item> lingering_experience_bottle = items.register("lingering_experience_bottle", () -> new ExperienceBottleItem(new Item.Properties().rarity(Rarity.UNCOMMON).group(VariantsTab.variant)));
    public static final RegistryObject<Item> enchanted_knowledge_book = items.register("enchanted_knowledge_book", () -> new KnowledgeBookItem(new Item.Properties().maxStackSize(1)));
    public static final RegistryObject<Item> splash_dragon_breath = items.register("splash_dragon_breath", () -> new Item(new Item.Properties().containerItem(Items.SPLASH_POTION).rarity(Rarity.UNCOMMON).group(VariantsTab.variant)));
    public static final RegistryObject<Item> lingering_dragon_breath = items.register("lingering_dragon_breath", () -> new Item(new Item.Properties().containerItem(Items.LINGERING_POTION).rarity(Rarity.UNCOMMON).group(VariantsTab.variant)));
    public static final RegistryObject<Item> splash_glass_bottle = items.register("splash_glass_bottle", () -> new Item(new Item.Properties().group(VariantsTab.variant)));
    public static final RegistryObject<Item> lingering_glass_bottle = items.register("lingering_glass_bottle", () -> new Item(new Item.Properties().group(VariantsTab.variant)));
    public static final RegistryObject<Item> honey_ball = items.register("honey_ball", () -> new HoneyBallItem(new Item.Properties().food(Foods.HONEY).group(VariantsTab.variant)));
    public static final RegistryObject<Item> raw_debris = items.register("raw_debris", () -> new Item(new Item.Properties().isImmuneToFire().group(VariantsTab.variant)));
    public static final RegistryObject<Item> oak_stick = items.register("oak_stick", () -> new Item(new Item.Properties().group(VariantsTab.variant)));
    public static final RegistryObject<Item> spruce_stick = items.register("spruce_stick", () -> new Item(new Item.Properties().group(VariantsTab.variant)));
    public static final RegistryObject<Item> birch_stick = items.register("birch_stick", () -> new Item(new Item.Properties().group(VariantsTab.variant)));
    public static final RegistryObject<Item> jungle_stick = items.register("jungle_stick", () -> new Item(new Item.Properties().group(VariantsTab.variant)));
    public static final RegistryObject<Item> acacia_stick = items.register("acacia_stick", () -> new Item(new Item.Properties().group(VariantsTab.variant)));
    public static final RegistryObject<Item> dark_oak_stick = items.register("dark_oak_stick", () -> new Item(new Item.Properties().group(VariantsTab.variant)));
    public static final RegistryObject<Item> crimson_stick = items.register("crimson_stick", () -> new Item(new Item.Properties().group(VariantsTab.variant)));
    public static final RegistryObject<Item> warped_stick = items.register("warped_stick", () -> new Item(new Item.Properties().group(VariantsTab.variant)));
    public static final RegistryObject<Item> netherite_rod = items.register("netherite_rod", () -> new Item(new Item.Properties().group(VariantsTab.variant).isImmuneToFire()));
    public static final RegistryObject<Item> soul_charge = items.register("soul_charge", () -> new FireChargeItem(new Item.Properties().group(VariantsTab.variant)));
    public static final RegistryObject<Item> soul_rod = items.register("soul_rod", () -> new Item(new Item.Properties().group(VariantsTab.variant)));
    public static final RegistryObject<Item> soul_powder = items.register("soul_powder", () -> new Item(new Item.Properties().group(VariantsTab.variant)));
    public static final RegistryObject<Item> exposed_copper_ingot = items.register("exposed_copper_ingot", () -> new Item(new Item.Properties().group(VariantsTab.variant)));
    public static final RegistryObject<Item> weathered_copper_ingot = items.register("weathered_copper_ingot", () -> new Item(new Item.Properties().group(VariantsTab.variant)));
    public static final RegistryObject<Item> oxidized_copper_ingot = items.register("oxidized_copper_ingot", () -> new Item(new Item.Properties().group(VariantsTab.variant)));
    public static final RegistryObject<Item> milk_glass_bottle = items.register("milk_glass_bottle", () -> new MilkBottleItem(new Item.Properties().maxStackSize(8).group(VariantsTab.variant)));
    public static final RegistryObject<Item> lava_glass_bottle = items.register("lava_glass_bottle", () -> new LavaBottleItem(new Item.Properties().maxStackSize(8).group(VariantsTab.variant)));
    public static final RegistryObject<Item> powder_snow_bottle = items.register("powder_snow_bottle", () -> new Item(new Item.Properties().maxStackSize(8).group(VariantsTab.variant)));
    public static final RegistryObject<Item> glow_black_dye = items.register("glow_black_dye", () -> new Item(new Item.Properties().group(VariantsTab.variant)));
    public static final RegistryObject<Item> elder_prismarine_shard = items.register("elder_prismarine_shard", () -> new Item(new Item.Properties().group(VariantsTab.variant)));
    public static final RegistryObject<Item> elder_prismarine_crystals = items.register("elder_prismarine_crystals", () -> new Item(new Item.Properties().group(VariantsTab.variant)));
    public static final RegistryObject<Item> warped_wart = items.register("warped_wart", () -> new BlockNamedItem(BlockInit.warped_wart.get(), new Item.Properties().group(VariantsTab.variant)));

    public static final RegistryObject<Item> nether_portal = items.register("nether_portal", () -> new BlockItem(Blocks.NETHER_PORTAL, new Item.Properties().group(VariantsTab.variant)));
}
