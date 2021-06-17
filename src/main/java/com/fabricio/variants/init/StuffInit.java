package com.fabricio.variants.init;

import com.fabricio.variants.Variants;
import com.fabricio.variants.items.HoneyBallItem;
import net.minecraft.block.Blocks;
import net.minecraft.entity.item.minecart.AbstractMinecartEntity;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class StuffInit {
    public static final DeferredRegister<Item> items = DeferredRegister.create(ForgeRegistries.ITEMS, Variants.mod_id);

    public static final RegistryObject<Item> gold_cauldron = items.register("gold_cauldron", () -> new BlockItem(BlockInit.gold_cauldron.get(), new Item.Properties().rarity(Rarity.UNCOMMON).tab(VariantsTab.variant)));
    public static final RegistryObject<Item> gold_beacon = items.register("gold_beacon", () -> new BlockItem(BlockInit.gold_beacon.get(), new Item.Properties().rarity(Rarity.UNCOMMON).tab(VariantsTab.variant)));
    public static final RegistryObject<Item> golden_carrots = items.register("golden_carrots", () -> new BlockItem(BlockInit.golden_carrots.get(), new Item.Properties().rarity(Rarity.UNCOMMON).tab(VariantsTab.variant)));
    public static final RegistryObject<Item> potted_sugar_cane = items.register("potted_sugar_cane", () -> new BlockItem(BlockInit.potted_sugar_cane.get(), new Item.Properties().rarity(Rarity.UNCOMMON).tab(VariantsTab.variant)));
    public static final RegistryObject<Item> cyan_shulker_shell = items.register("cyan_shulker_shell", () -> new Item(new Item.Properties().tab(VariantsTab.variant)));
    public static final RegistryObject<Item> spawner_minecart = items.register("spawner_minecart", () -> new MinecartItem(AbstractMinecartEntity.Type.SPAWNER, new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(1).tab(VariantsTab.variant)));
    public static final RegistryObject<Item> fungi_stew = items.register("fungi_stew", () -> new SoupItem(new Item.Properties().tab(VariantsTab.variant).food(Foods.MUSHROOM_STEW)));
    public static final RegistryObject<Item> splash_experience_bottle = items.register("splash_experience_bottle", () -> new ExperienceBottleItem(new Item.Properties().rarity(Rarity.UNCOMMON).tab(VariantsTab.variant)));
    public static final RegistryObject<Item> lingering_experience_bottle = items.register("lingering_experience_bottle", () -> new ExperienceBottleItem(new Item.Properties().rarity(Rarity.UNCOMMON).tab(VariantsTab.variant)));
    public static final RegistryObject<Item> enchanted_knowledge_book = items.register("enchanted_knowledge_book", () -> new KnowledgeBookItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> splash_dragon_breath = items.register("splash_dragon_breath", () -> new Item(new Item.Properties().craftRemainder(Items.SPLASH_POTION).rarity(Rarity.UNCOMMON).tab(VariantsTab.variant)));
    public static final RegistryObject<Item> lingering_dragon_breath = items.register("lingering_dragon_breath", () -> new Item(new Item.Properties().craftRemainder(Items.LINGERING_POTION).rarity(Rarity.UNCOMMON).tab(VariantsTab.variant)));
    public static final RegistryObject<Item> splash_glass_bottle = items.register("splash_glass_bottle", () -> new Item(new Item.Properties().tab(VariantsTab.variant)));
    public static final RegistryObject<Item> lingering_glass_bottle = items.register("lingering_glass_bottle", () -> new Item(new Item.Properties().tab(VariantsTab.variant)));
    public static final RegistryObject<Item> honey_ball = items.register("honey_ball", () -> new HoneyBallItem(new Item.Properties().food(Foods.HONEY_BOTTLE).tab(VariantsTab.variant)));
    public static final RegistryObject<Item> oak_stick = items.register("oak_stick", () -> new Item(new Item.Properties().tab(VariantsTab.variant)));
    public static final RegistryObject<Item> spruce_stick = items.register("spruce_stick", () -> new Item(new Item.Properties().tab(VariantsTab.variant)));
    public static final RegistryObject<Item> birch_stick = items.register("birch_stick", () -> new Item(new Item.Properties().tab(VariantsTab.variant)));
    public static final RegistryObject<Item> jungle_stick = items.register("jungle_stick", () -> new Item(new Item.Properties().tab(VariantsTab.variant)));
    public static final RegistryObject<Item> acacia_stick = items.register("acacia_stick", () -> new Item(new Item.Properties().tab(VariantsTab.variant)));
    public static final RegistryObject<Item> dark_oak_stick = items.register("dark_oak_stick", () -> new Item(new Item.Properties().tab(VariantsTab.variant)));
    public static final RegistryObject<Item> crimson_stick = items.register("crimson_stick", () -> new Item(new Item.Properties().tab(VariantsTab.variant)));
    public static final RegistryObject<Item> warped_stick = items.register("warped_stick", () -> new Item(new Item.Properties().tab(VariantsTab.variant)));
    public static final RegistryObject<Item> soul_charge = items.register("soul_charge", () -> new FireChargeItem(new Item.Properties().tab(VariantsTab.variant)));
    public static final RegistryObject<Item> soul_rod = items.register("soul_rod", () -> new Item(new Item.Properties().tab(VariantsTab.variant)));
    public static final RegistryObject<Item> soul_powder = items.register("soul_powder", () -> new Item(new Item.Properties().tab(VariantsTab.variant)));
    public static final RegistryObject<Item> exposed_copper_ingot = items.register("exposed_copper_ingot", () -> new Item(new Item.Properties().tab(VariantsTab.variant)));
    public static final RegistryObject<Item> weathered_copper_ingot = items.register("weathered_copper_ingot", () -> new Item(new Item.Properties().tab(VariantsTab.variant)));
    public static final RegistryObject<Item> oxidized_copper_ingot = items.register("oxidized_copper_ingot", () -> new Item(new Item.Properties().tab(VariantsTab.variant)));

}
