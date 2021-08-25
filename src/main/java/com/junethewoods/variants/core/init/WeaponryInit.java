package com.junethewoods.variants.core.init;

import com.junethewoods.variants.core.Variants;
import com.junethewoods.variants.common.item.DebugBowItem;
import com.junethewoods.variants.common.item.MagmaSwordItem;
import com.junethewoods.variants.common.item.VariantAxeItem;
import com.junethewoods.variants.common.item.VariantDyeArmorItem;
import com.junethewoods.variants.core.tabs.WeaponryTab;
import com.junethewoods.variants.common.util.VariantArmors;
import com.junethewoods.variants.common.util.VariantTools;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class WeaponryInit {
    public static final DeferredRegister<Item> tools = DeferredRegister.create(ForgeRegistries.ITEMS, Variants.mod_id);

    public static final RegistryObject<Item> diorite_sword = tools.register("diorite_sword", () -> new SwordItem(VariantTools.diorite, 3, -2.4F, new Item.Properties().group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> diorite_pickaxe = tools.register("diorite_pickaxe", () -> new PickaxeItem(VariantTools.diorite, 1, -2.8F, new Item.Properties().group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> diorite_shovel = tools.register("diorite_shovel", () -> new ShovelItem(VariantTools.diorite, 1.5F, -3.0F, new Item.Properties().group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> diorite_axe = tools.register("diorite_axe", () -> new VariantAxeItem(VariantTools.diorite, 7.0F, -3.2F, new Item.Properties().group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> diorite_hoe = tools.register("diorite_hoe", () -> new HoeItem(VariantTools.diorite, -2, -2.0F, new Item.Properties().group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> granite_sword = tools.register("granite_sword", () -> new SwordItem(VariantTools.granite, 3, -2.4F, new Item.Properties().group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> granite_pickaxe = tools.register("granite_pickaxe", () -> new PickaxeItem(VariantTools.granite, 1, -2.8F, new Item.Properties().group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> granite_shovel = tools.register("granite_shovel", () -> new ShovelItem(VariantTools.granite, 1.5F, -3.0F, new Item.Properties().group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> granite_axe = tools.register("granite_axe", () -> new VariantAxeItem(VariantTools.granite, 7.0F, -3.2F, new Item.Properties().group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> granite_hoe = tools.register("granite_hoe", () -> new HoeItem(VariantTools.granite, -2, -2.0F, new Item.Properties().group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> andesite_sword = tools.register("andesite_sword", () -> new SwordItem(VariantTools.andesite, 3, -2.4F, new Item.Properties().group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> andesite_pickaxe = tools.register("andesite_pickaxe", () -> new PickaxeItem(VariantTools.andesite, 1, -2.8F, new Item.Properties().group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> andesite_shovel = tools.register("andesite_shovel", () -> new ShovelItem(VariantTools.andesite, 1.5F, -3.0F, new Item.Properties().group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> andesite_axe = tools.register("andesite_axe", () -> new VariantAxeItem(VariantTools.andesite, 7.0F, -3.2F, new Item.Properties().group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> andesite_hoe = tools.register("andesite_hoe", () -> new HoeItem(VariantTools.andesite, -2, -2.0F, new Item.Properties().group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> end_stone_sword = tools.register("end_stone_sword", () -> new SwordItem(VariantTools.end_stone, 3, -2.4F, new Item.Properties().group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> end_stone_pickaxe = tools.register("end_stone_pickaxe", () -> new PickaxeItem(VariantTools.end_stone, 1, -2.8F, new Item.Properties().group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> end_stone_shovel = tools.register("end_stone_shovel", () -> new ShovelItem(VariantTools.end_stone, 1.5F, -3.0F, new Item.Properties().group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> end_stone_axe = tools.register("end_stone_axe", () -> new VariantAxeItem(VariantTools.end_stone, 7.0F, -3.2F, new Item.Properties().group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> end_stone_hoe = tools.register("end_stone_hoe", () -> new HoeItem(VariantTools.end_stone, -2, -2.0F, new Item.Properties().group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> quartz_sword = tools.register("quartz_sword", () -> new SwordItem(VariantTools.quartz, 3, -2.4F, new Item.Properties().group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> quartz_pickaxe = tools.register("quartz_pickaxe", () -> new PickaxeItem(VariantTools.quartz, 1, -2.8F, new Item.Properties().group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> quartz_shovel = tools.register("quartz_shovel", () -> new ShovelItem(VariantTools.quartz, 1.5F, -3.0F, new Item.Properties().group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> quartz_axe = tools.register("quartz_axe", () -> new VariantAxeItem(VariantTools.quartz, 7.0F, -3.2F, new Item.Properties().group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> quartz_hoe = tools.register("quartz_hoe", () -> new HoeItem(VariantTools.quartz, -2, -2.0F, new Item.Properties().group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> quartz_horse_armor = tools.register("quartz_horse_armor", () -> new HorseArmorItem(8, new ResourceLocation(
            Variants.mod_id, "textures/entity/horse/armor/horse_armor_quartz.png"), new Item.Properties().maxStackSize(1).group(WeaponryTab.weaponryTab)));
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
    public static final RegistryObject<Item> empty_armor_slot_shield = tools.register("empty_armor_slot_shield", () -> new ShieldItem(new Item.Properties().maxStackSize(1).maxDamage(496).group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> empty_armor_slot_helmet = tools.register("empty_armor_slot_helmet", () -> new ArmorItem(VariantArmors.EQUIPMENT_SLOT_TYPE, EquipmentSlotType.HEAD ,new Item.Properties().group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> empty_armor_slot_chestplate = tools.register("empty_armor_slot_chestplate", () -> new ArmorItem(VariantArmors.EQUIPMENT_SLOT_TYPE, EquipmentSlotType.CHEST ,new Item.Properties().group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> empty_armor_slot_leggings = tools.register("empty_armor_slot_leggings", () -> new ArmorItem(VariantArmors.EQUIPMENT_SLOT_TYPE, EquipmentSlotType.LEGS ,new Item.Properties().group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> empty_armor_slot_boots = tools.register("empty_armor_slot_boots", () -> new ArmorItem(VariantArmors.EQUIPMENT_SLOT_TYPE, EquipmentSlotType.FEET ,new Item.Properties().group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> quartz_helmet = tools.register("quartz_helmet", () -> new ArmorItem(VariantArmors.QUARTZ, EquipmentSlotType.HEAD, new Item.Properties().group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> quartz_chestplate = tools.register("quartz_chestplate", () -> new ArmorItem(VariantArmors.QUARTZ, EquipmentSlotType.CHEST, new Item.Properties().group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> quartz_leggings = tools.register("quartz_leggings", () -> new ArmorItem(VariantArmors.QUARTZ, EquipmentSlotType.LEGS, new Item.Properties().group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> quartz_boots = tools.register("quartz_boots", () -> new ArmorItem(VariantArmors.QUARTZ, EquipmentSlotType.FEET, new Item.Properties().group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> emerald_helmet = tools.register("emerald_helmet", () -> new ArmorItem(VariantArmors.EMERALD, EquipmentSlotType.HEAD, new Item.Properties().group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> emerald_chestplate = tools.register("emerald_chestplate", () -> new ArmorItem(VariantArmors.EMERALD, EquipmentSlotType.CHEST, new Item.Properties().group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> emerald_leggings = tools.register("emerald_leggings", () -> new ArmorItem(VariantArmors.EMERALD, EquipmentSlotType.LEGS, new Item.Properties().group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> emerald_boots = tools.register("emerald_boots", () -> new ArmorItem(VariantArmors.EMERALD, EquipmentSlotType.FEET, new Item.Properties().group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> copper_chestplate = tools.register("copper_chestplate", () -> new ArmorItem(VariantArmors.COPPER, EquipmentSlotType.CHEST, new Item.Properties().group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> phantom_membrane_sweatchest = tools.register("phantom_membrane_sweatchest", () -> new ArmorItem(VariantArmors.PHANTOM, EquipmentSlotType.CHEST, new Item.Properties().group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> rabbit_hide_sweatchest = tools.register("rabbit_hide_sweatchest", () -> new ArmorItem(VariantArmors.RABBIT, EquipmentSlotType.CHEST, new Item.Properties().group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> wool_sweatchest = tools.register("wool_sweatchest", () -> new VariantDyeArmorItem(VariantArmors.WOOL, EquipmentSlotType.CHEST, new Item.Properties().group(WeaponryTab.weaponryTab)));
    //public static final RegistryObject<Item> iron_bow = tools.register("iron_bow", () -> new BowItem(new Item.Properties().maxDamage(450).maxStackSize(1).group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> iron_spyglass = tools.register("iron_spyglass", () -> new Item(new Item.Properties().maxStackSize(1).group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> diamond_spyglass = tools.register("diamond_spyglass", () -> new Item(new Item.Properties().maxStackSize(1).group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> netherite_spyglass = tools.register("netherite_spyglass", () -> new Item(new Item.Properties().maxStackSize(1).isImmuneToFire().group(WeaponryTab.weaponryTab)));

}