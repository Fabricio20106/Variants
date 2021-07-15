package com.junethewoods.variants.compat;

import com.junethewoods.variants.common.Variants;
import com.junethewoods.variants.init.VariantBlocks;
import com.junethewoods.variants.init.VariantsTab;
import com.junethewoods.variants.init.WeaponryTab;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ShearsItem;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class F10CompatItems {
    //public static final RegistryObject<Item> plasteel_shears = othersCompat.register("plasteel_shears", () -> new ShearsItem(new Item.Properties().maxDamage(450).group(F10CompatTab.compat)));
    public static final DeferredRegister<Item> editsCompat = DeferredRegister.create(ForgeRegistries.ITEMS, Variants.mod_id);
    public static final DeferredRegister<Item> othersCompat = DeferredRegister.create(ForgeRegistries.ITEMS, Variants.mod_id);
    public static final DeferredRegister<Item> backMathCompat = DeferredRegister.create(ForgeRegistries.ITEMS, Variants.mod_id);
    public static final DeferredRegister<Item> fabricio2010packCompat = DeferredRegister.create(ForgeRegistries.ITEMS, Variants.mod_id);

    // Edits
    public static final RegistryObject<Item> splash_soph_potion = editsCompat.register("splash_soph_potion",
            () -> new Item(new Item.Properties().food(new Food.Builder().hunger(6).saturation(3).build()).group(VariantsTab.variant)));
    public static final RegistryObject<Item> purple_nugget = editsCompat.register("purple_nugget", () -> new Item(new Item.Properties().group(VariantsTab.variant)));

    // My Other Stuff
    public static final RegistryObject<Item> diaemerald_shears = othersCompat.register("diaemerald_shears", () -> new ShearsItem(new Item.Properties().maxDamage(725).group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> crystal_shears = othersCompat.register("crystal_shears", () -> new ShearsItem(new Item.Properties().maxDamage(500).group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> light_magenta_shears = othersCompat.register("light_magenta_shears", () -> new ShearsItem(new Item.Properties().maxDamage(500).group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> alan_ai_shears = othersCompat.register("alan_ai_shears", () -> new ShearsItem(new Item.Properties().maxDamage(600).group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> alice_ai_shears = othersCompat.register("alice_ai_shears", () -> new ShearsItem(new Item.Properties().maxDamage(600).group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> inno_ai_shears = othersCompat.register("inno_ai_shears", () -> new ShearsItem(new Item.Properties().maxDamage(600).group(WeaponryTab.weaponryTab)));
    public static final RegistryObject<Item> nicolas_ai_shears = othersCompat.register("nicolas_ai_shears", () -> new ShearsItem(new Item.Properties().maxDamage(600).group(WeaponryTab.weaponryTab)));

    // Fabricio2010Pack (i.e. Fabricio2010Mod)
    public static final RegistryObject<Item> first_door = fabricio2010packCompat.register("first_door", () -> new BlockItem(F10CompatBlocks.first_door.get(), new Item.Properties().group(VariantBlocks.blocks)));
    public static final RegistryObject<Item> plain_birch_bookshelf = fabricio2010packCompat.register("plain_birch_bookshelf", () -> new BlockItem(F10CompatBlocks.plain_birch_bookshelf.get(), new Item.Properties().group(VariantBlocks.blocks)));
    public static final RegistryObject<Item> inno_shulker_shell = fabricio2010packCompat.register("inno_shulker_shell", () -> new Item(new Item.Properties().group(VariantsTab.variant)));

    // futurely, Back Math
}
