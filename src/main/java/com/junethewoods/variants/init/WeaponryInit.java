package com.junethewoods.variants.init;

import com.junethewoods.variants.common.Variants;
import com.junethewoods.variants.items.DebugBowItem;
import com.junethewoods.variants.items.MagmaSwordItem;
import com.junethewoods.variants.items.VariantDyeArmorItem;
import com.junethewoods.variants.util.VariantArmors;
import com.junethewoods.variants.util.VariantTools;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class WeaponryInit {
    public static final DeferredRegister<Item> tools = DeferredRegister.create(ForgeRegistries.ITEMS, Variants.mod_id);

    public static final RegistryObject<Item> diorite_sword = tools.register("diorite_sword", () -> new SwordItem(VariantTools.diorite, 3, -2.4F, new Item.Properties().group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> diorite_pickaxe = tools.register("diorite_pickaxe", () -> new PickaxeItem(VariantTools.diorite, 1, -2.8F, new Item.Properties().group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> diorite_shovel = tools.register("diorite_shovel", () -> new ShovelItem(VariantTools.diorite, 1.5F, -3.0F, new Item.Properties().group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> diorite_axe = tools.register("diorite_axe", () -> new AxeItem(VariantTools.diorite, 7.0F, -3.2F, new Item.Properties().group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> diorite_hoe = tools.register("diorite_hoe", () -> new HoeItem(VariantTools.diorite, -2, -2.0F, new Item.Properties().group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> granite_sword = tools.register("granite_sword", () -> new SwordItem(VariantTools.granite, 3, -2.4F, new Item.Properties().group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> granite_pickaxe = tools.register("granite_pickaxe", () -> new PickaxeItem(VariantTools.granite, 1, -2.8F, new Item.Properties().group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> granite_shovel = tools.register("granite_shovel", () -> new ShovelItem(VariantTools.granite, 1.5F, -3.0F, new Item.Properties().group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> granite_axe = tools.register("granite_axe", () -> new AxeItem(VariantTools.granite, 7.0F, -3.2F, new Item.Properties().group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> granite_hoe = tools.register("granite_hoe", () -> new HoeItem(VariantTools.granite, -2, -2.0F, new Item.Properties().group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> andesite_sword = tools.register("andesite_sword", () -> new SwordItem(VariantTools.andesite, 3, -2.4F, new Item.Properties().group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> andesite_pickaxe = tools.register("andesite_pickaxe", () -> new PickaxeItem(VariantTools.andesite, 1, -2.8F, new Item.Properties().group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> andesite_shovel = tools.register("andesite_shovel", () -> new ShovelItem(VariantTools.andesite, 1.5F, -3.0F, new Item.Properties().group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> andesite_axe = tools.register("andesite_axe", () -> new AxeItem(VariantTools.andesite, 7.0F, -3.2F, new Item.Properties().group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> andesite_hoe = tools.register("andesite_hoe", () -> new HoeItem(VariantTools.andesite, -2, -2.0F, new Item.Properties().group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> magma_sword = tools.register("magma_sword", () -> new MagmaSwordItem(VariantTools.magma, 3, -2.4F, new Item.Properties().group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> amethyst_sword = tools.register("amethyst_sword", () -> new SwordItem(VariantTools.amethyst, 3, -2.4F, new Item.Properties().group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> copper_sword = tools.register("copper_sword", () -> new SwordItem(VariantTools.copper, 3, -2.4F, new Item.Properties().group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> debug_bow = tools.register("debug_bow", () -> new DebugBowItem(new Item.Properties().maxDamage(384).group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> redstone_shears = tools.register("redstone_shears", () -> new ShearsItem(new Item.Properties().maxDamage(768).group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> coal_shears = tools.register("coal_shears", () -> new ShearsItem(new Item.Properties().maxDamage(768).group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> diamond_shears = tools.register("diamond_shears", () -> new ShearsItem(new Item.Properties().maxDamage(1561).group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> golden_shears = tools.register("golden_shears", () -> new ShearsItem(new Item.Properties().maxDamage(32).group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> netherite_shears = tools.register("netherite_shears", () -> new ShearsItem(new Item.Properties().maxDamage(2031).isImmuneToFire().group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> lapis_lazuli_shears = tools.register("lapis_lazuli_shears", () -> new ShearsItem(new Item.Properties().maxDamage(768).group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> emerald_shears = tools.register("emerald_shears", () -> new ShearsItem(new Item.Properties().maxDamage(768).group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> quartz_shears = tools.register("quartz_shears", () -> new ShearsItem(new Item.Properties().maxDamage(768).group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> ruby_shears = tools.register("ruby_shears", () -> new ShearsItem(new Item.Properties().maxDamage(768).group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> empty_armor_slot_helmet = tools.register("empty_armor_slot_helmet", () -> new ArmorItem(VariantArmors.EQUIPMENT_SLOT_TYPE, EquipmentSlotType.HEAD ,new Item.Properties().group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> empty_armor_slot_chestplate = tools.register("empty_armor_slot_chestplate", () -> new ArmorItem(VariantArmors.EQUIPMENT_SLOT_TYPE, EquipmentSlotType.CHEST ,new Item.Properties().group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> empty_armor_slot_leggings = tools.register("empty_armor_slot_leggings", () -> new ArmorItem(VariantArmors.EQUIPMENT_SLOT_TYPE, EquipmentSlotType.LEGS ,new Item.Properties().group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> empty_armor_slot_boots = tools.register("empty_armor_slot_boots", () -> new ArmorItem(VariantArmors.EQUIPMENT_SLOT_TYPE, EquipmentSlotType.FEET ,new Item.Properties().group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> empty_armor_slot_shield = tools.register("empty_armor_slot_shield", () -> new ShieldItem(new Item.Properties().maxStackSize(1).maxDamage(496).group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> phantom_membrane_sweatchest = tools.register("phantom_membrane_sweatchest", () -> new ArmorItem(VariantArmors.PHANTOM, EquipmentSlotType.CHEST ,new Item.Properties().group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> rabbit_hide_sweatchest = tools.register("rabbit_hide_sweatchest", () -> new ArmorItem(VariantArmors.RABBIT, EquipmentSlotType.CHEST ,new Item.Properties().group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> wool_sweatchest = tools.register("wool_sweatchest", () -> new VariantDyeArmorItem(VariantArmors.WOOL, EquipmentSlotType.CHEST, new Item.Properties().group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> iron_spyglass = tools.register("iron_spyglass", () -> new Item(new Item.Properties().maxStackSize(1).group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> diamond_spyglass = tools.register("diamond_spyglass", () -> new Item(new Item.Properties().maxStackSize(1).group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> netherite_spyglass = tools.register("netherite_spyglass", () -> new Item(new Item.Properties().maxStackSize(1).isImmuneToFire().group(WeaponryTab.weaponryTab)));

}
