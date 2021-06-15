package com.fabricio.variants.init;

import com.fabricio.variants.Variants;
import com.fabricio.variants.items.DebugBowItem;
import com.fabricio.variants.util.VariantTools;
import com.fabricio.variants.util.VariantArmors;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.item.minecart.AbstractMinecartEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class StuffInit {
    public static final DeferredRegister<Item> items = DeferredRegister.create(ForgeRegistries.ITEMS, Variants.mod_id);

    public static final RegistryObject<Item> cyan_shulker_shell = items.register("cyan_shulker_shell", () -> new Item(new Item.Properties().tab(VariantsTab.variant)));
    public static final RegistryObject<Item> spawner_minecart = items.register("spawner_minecart", () -> new MinecartItem(AbstractMinecartEntity.Type.SPAWNER, new Item.Properties().tab(VariantsTab.variant)));
    public static final RegistryObject<Item> fungi_stew = items.register("fungi_stew", () -> new SoupItem(new Item.Properties().tab(VariantsTab.variant).food(Foods.MUSHROOM_STEW)));
    public static final RegistryObject<Item> splash_experience_bottle = items.register("splash_experience_bottle", () -> new ExperienceBottleItem(new Item.Properties().rarity(Rarity.UNCOMMON).tab(VariantsTab.variant)));
    public static final RegistryObject<Item> lingering_experience_bottle = items.register("lingering_experience_bottle", () -> new ExperienceBottleItem(new Item.Properties().rarity(Rarity.UNCOMMON).tab(VariantsTab.variant)));
    public static final RegistryObject<Item> enchanted_knowledge_book = items.register("enchanted_knowledge_book", () -> new KnowledgeBookItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> splash_dragon_breath = items.register("splash_dragon_breath", () -> new Item(new Item.Properties().craftRemainder(StuffInit.splash_glass_bottle.get()).rarity(Rarity.UNCOMMON).tab(VariantsTab.variant)));
    public static final RegistryObject<Item> lingering_dragon_breath = items.register("lingering_dragon_breath", () -> new Item(new Item.Properties().craftRemainder(StuffInit.lingering_glass_bottle.get()).rarity(Rarity.UNCOMMON).tab(VariantsTab.variant)));
    public static final RegistryObject<Item> splash_glass_bottle = items.register("splash_glass_bottle", () -> new Item(new Item.Properties().tab(VariantsTab.variant)));
    public static final RegistryObject<Item> lingering_glass_bottle = items.register("lingering_glass_bottle", () -> new Item(new Item.Properties().tab(VariantsTab.variant)));
    public static final RegistryObject<Item> honey_ball = items.register("honey_ball", () -> new Item(new Item.Properties().food(Foods.HONEY_BOTTLE).tab(VariantsTab.variant)));

}
