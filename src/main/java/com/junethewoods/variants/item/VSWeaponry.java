package com.junethewoods.variants.item;

import com.junethewoods.variants.Variants;
import com.junethewoods.variants.util.tab.VSWeaponryTab;
import com.junethewoods.variants.item.custom.DebugBowItem;
import com.junethewoods.variants.item.tool.VSArmors;
import com.junethewoods.variants.item.tool.VSTools;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class VSWeaponry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Variants.MOD_ID);

    public static final RegistryObject<Item> DIORITE_SWORD = ITEMS.register("diorite_sword", () -> new SwordItem(VSTools.DIORITE, 3, -2.4F, new Item.Properties().tab(VSWeaponryTab.TAB)));
    public static final RegistryObject<Item> DIORITE_PICKAXE = ITEMS.register("diorite_pickaxe", () -> new PickaxeItem(VSTools.DIORITE, 1, -2.8F, new Item.Properties().tab(VSWeaponryTab.TAB)));
    public static final RegistryObject<Item> DIORITE_SHOVEL = ITEMS.register("diorite_shovel", () -> new ShovelItem(VSTools.DIORITE, 1.5F, -3f, new Item.Properties().tab(VSWeaponryTab.TAB)));
    public static final RegistryObject<Item> DIORITE_AXE = ITEMS.register("diorite_axe", () -> new AxeItem(VSTools.DIORITE, 7f, -3.2F, new Item.Properties().tab(VSWeaponryTab.TAB)));
    public static final RegistryObject<Item> DIORITE_HOE = ITEMS.register("diorite_hoe", () -> new HoeItem(VSTools.DIORITE, -2, -1f, new Item.Properties().tab(VSWeaponryTab.TAB)));
    public static final RegistryObject<Item> GRANITE_SWORD = ITEMS.register("granite_sword", () -> new SwordItem(VSTools.GRANITE, 3, -2.4F, new Item.Properties().tab(VSWeaponryTab.TAB)));
    public static final RegistryObject<Item> GRANITE_PICKAXE = ITEMS.register("granite_pickaxe", () -> new PickaxeItem(VSTools.GRANITE, 1, -2.8F, new Item.Properties().tab(VSWeaponryTab.TAB)));
    public static final RegistryObject<Item> GRANITE_SHOVEL = ITEMS.register("granite_shovel", () -> new ShovelItem(VSTools.GRANITE, 1.5F, -3f, new Item.Properties().tab(VSWeaponryTab.TAB)));
    public static final RegistryObject<Item> GRANITE_AXE = ITEMS.register("granite_axe", () -> new AxeItem(VSTools.GRANITE, 7f, -3.2F, new Item.Properties().tab(VSWeaponryTab.TAB)));
    public static final RegistryObject<Item> GRANITE_HOE = ITEMS.register("granite_hoe", () -> new HoeItem(VSTools.GRANITE, -2, -1f, new Item.Properties().tab(VSWeaponryTab.TAB)));
    public static final RegistryObject<Item> ANDESITE_SWORD = ITEMS.register("andesite_sword", () -> new SwordItem(VSTools.ANDESITE, 3, -2.4F, new Item.Properties().tab(VSWeaponryTab.TAB)));
    public static final RegistryObject<Item> ANDESITE_PICKAXE = ITEMS.register("andesite_pickaxe", () -> new PickaxeItem(VSTools.ANDESITE, 1, -2.8F, new Item.Properties().tab(VSWeaponryTab.TAB)));
    public static final RegistryObject<Item> ANDESITE_SHOVEL = ITEMS.register("andesite_shovel", () -> new ShovelItem(VSTools.ANDESITE, 1.5F, -3f, new Item.Properties().tab(VSWeaponryTab.TAB)));
    public static final RegistryObject<Item> ANDESITE_AXE = ITEMS.register("andesite_axe", () -> new AxeItem(VSTools.ANDESITE, 7f, -3.2F, new Item.Properties().tab(VSWeaponryTab.TAB)));
    public static final RegistryObject<Item> ANDESITE_HOE = ITEMS.register("andesite_hoe", () -> new HoeItem(VSTools.ANDESITE, -2, -1f, new Item.Properties().tab(VSWeaponryTab.TAB)));
    public static final RegistryObject<Item> AMETHYST_SWORD = ITEMS.register("amethyst_sword", () -> new SwordItem(VSTools.AMETHYST, 3, -2.4F, new Item.Properties().tab(VSWeaponryTab.TAB)));
    public static final RegistryObject<Item> COPPER_SWORD = ITEMS.register("copper_sword", () -> new SwordItem(VSTools.COPPER, 3, -2.4F, new Item.Properties().tab(VSWeaponryTab.TAB)));
    public static final RegistryObject<Item> DEBUG_BOW = ITEMS.register("debug_bow", () -> new DebugBowItem(new Item.Properties().durability(384).tab(VSWeaponryTab.TAB)));
    public static final RegistryObject<Item> REDSTONE_SHEARS = ITEMS.register("redstone_shears", () -> new ShearsItem(new Item.Properties().durability(1871).tab(VSWeaponryTab.TAB)));
    public static final RegistryObject<Item> COAL_SHEARS = ITEMS.register("coal_shears", () -> new ShearsItem(new Item.Properties().durability(1871).tab(VSWeaponryTab.TAB)));
    public static final RegistryObject<Item> DIAMOND_SHEARS = ITEMS.register("diamond_shears", () -> new ShearsItem(new Item.Properties().durability(1561).tab(VSWeaponryTab.TAB)));
    public static final RegistryObject<Item> GOLDEN_SHEARS = ITEMS.register("golden_shears", () -> new ShearsItem(new Item.Properties().durability(32).tab(VSWeaponryTab.TAB)));
    public static final RegistryObject<Item> NETHERITE_SHEARS = ITEMS.register("netherite_shears", () -> new ShearsItem(new Item.Properties().durability(2031).fireResistant().tab(VSWeaponryTab.TAB)));
    public static final RegistryObject<Item> LAPIS_LAZULI_SHEARS = ITEMS.register("lapis_lazuli_shears", () -> new ShearsItem(new Item.Properties().durability(1871).tab(VSWeaponryTab.TAB)));
    public static final RegistryObject<Item> EMERALD_SHEARS = ITEMS.register("emerald_shears", () -> new ShearsItem(new Item.Properties().durability(1871).tab(VSWeaponryTab.TAB)));
    public static final RegistryObject<Item> QUARTZ_SHEARS = ITEMS.register("quartz_shears", () -> new ShearsItem(new Item.Properties().durability(1871).tab(VSWeaponryTab.TAB)));
    public static final RegistryObject<Item> RUBY_SHEARS = ITEMS.register("ruby_shears", () -> new ShearsItem(new Item.Properties().durability(1871).tab(VSWeaponryTab.TAB)));
    public static final RegistryObject<Item> EMPTY_ARMOR_SLOT_HELMET = ITEMS.register("empty_armor_slot_helmet", () -> new ArmorItem(VSArmors.EQUIPMENT_SLOT_TYPE, EquipmentSlotType.HEAD ,new Item.Properties().tab(VSWeaponryTab.TAB)));
    public static final RegistryObject<Item> EMPTY_ARMOR_SLOT_CHESTPLATE = ITEMS.register("empty_armor_slot_chestplate", () -> new ArmorItem(VSArmors.EQUIPMENT_SLOT_TYPE, EquipmentSlotType.CHEST ,new Item.Properties().tab(VSWeaponryTab.TAB)));
    public static final RegistryObject<Item> EMPTY_ARMOR_SLOT_LEGGINGS = ITEMS.register("empty_armor_slot_leggings", () -> new ArmorItem(VSArmors.EQUIPMENT_SLOT_TYPE, EquipmentSlotType.LEGS ,new Item.Properties().tab(VSWeaponryTab.TAB)));
    public static final RegistryObject<Item> EMPTY_ARMOR_SLOT_BOOTS = ITEMS.register("empty_armor_slot_boots", () -> new ArmorItem(VSArmors.EQUIPMENT_SLOT_TYPE, EquipmentSlotType.FEET ,new Item.Properties().tab(VSWeaponryTab.TAB)));
    public static final RegistryObject<Item> EMPTY_ARMOR_SLOT_SHIELD = ITEMS.register("empty_armor_slot_shield", () -> new ShieldItem(new Item.Properties().stacksTo(1).durability(496).tab(VSWeaponryTab.TAB)));
    public static final RegistryObject<Item> PHANTOM_MEMBRANE_TUNIC = ITEMS.register("phantom_membrane_sweatchest", () -> new ArmorItem(VSArmors.PHANTOM, EquipmentSlotType.CHEST ,new Item.Properties().tab(VSWeaponryTab.TAB)));
    public static final RegistryObject<Item> RABBIT_HIDE_TUNIC = ITEMS.register("rabbit_hide_sweatchest", () -> new ArmorItem(VSArmors.RABBIT, EquipmentSlotType.CHEST ,new Item.Properties().tab(VSWeaponryTab.TAB)));
    public static final RegistryObject<Item> WOOL_SWEATER = ITEMS.register("wool_sweatchest", () -> new DyeableArmorItem(VSArmors.WOOL, EquipmentSlotType.CHEST ,new Item.Properties().tab(VSWeaponryTab.TAB)));
    public static final RegistryObject<Item> IRON_SPYGLASS = ITEMS.register("iron_spyglass", () -> new Item(new Item.Properties().stacksTo(1).tab(VSWeaponryTab.TAB)));
    public static final RegistryObject<Item> DIAMOND_SPYGLASS = ITEMS.register("diamond_spyglass", () -> new Item(new Item.Properties().stacksTo(1).tab(VSWeaponryTab.TAB)));
    public static final RegistryObject<Item> NETHERITE_SPYGLASS = ITEMS.register("netherite_spyglass", () -> new Item(new Item.Properties().stacksTo(1).fireResistant().tab(VSWeaponryTab.TAB)));
}
