package com.junethewoods.variants.item;

import com.junethewoods.variants.Variants;
import com.junethewoods.variants.util.tab.VSCompatsTab;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ShearsItem;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class VSCompatItems {
    public static final DeferredRegister<Item> ITEMS_EDITS = DeferredRegister.create(ForgeRegistries.ITEMS, Variants.MOD_ID);
    public static final DeferredRegister<Item> ITEMS_OTHERS = DeferredRegister.create(ForgeRegistries.ITEMS, Variants.MOD_ID);
    public static final DeferredRegister<Item> ITEMS_BACK_MATH = DeferredRegister.create(ForgeRegistries.ITEMS, Variants.MOD_ID);

    public static final RegistryObject<Item> SPLASH_SOPHIE_POTION = ITEMS_EDITS.register("splash_soph_potion", () -> new Item(new Item.Properties().food(new Food.Builder().nutrition(6).saturationMod(3).build()).tab(VSCompatsTab.TAB)));
    public static final RegistryObject<Item> PURPLE_IRON_NUGGET = ITEMS_EDITS.register("purple_nugget", () -> new Item(new Item.Properties().tab(VSCompatsTab.TAB)));
    public static final RegistryObject<Item> DIAEMERALD_SHEARS = ITEMS_OTHERS.register("diaemerald_shears", () -> new ShearsItem(new Item.Properties().durability(725).tab(VSCompatsTab.TAB)));
    public static final RegistryObject<Item> CRYSTAL_SHEARS = ITEMS_OTHERS.register("crystal_shears", () -> new ShearsItem(new Item.Properties().durability(500).tab(VSCompatsTab.TAB)));
    public static final RegistryObject<Item> PLASTEEL_SHEARS = ITEMS_OTHERS.register("plasteel_shears", () -> new ShearsItem(new Item.Properties().durability(450).tab(VSCompatsTab.TAB)));
    public static final RegistryObject<Item> LIGHT_MAGENTA_SHEARS = ITEMS_OTHERS.register("light_magenta_shears", () -> new ShearsItem(new Item.Properties().durability(500).tab(VSCompatsTab.TAB)));
    public static final RegistryObject<Item> ALAN_AI_SHEARS = ITEMS_OTHERS.register("alan_ai_shears", () -> new ShearsItem(new Item.Properties().durability(600).tab(VSCompatsTab.TAB)));
    public static final RegistryObject<Item> ALICE_AI_SHEARS = ITEMS_OTHERS.register("alice_ai_shears", () -> new ShearsItem(new Item.Properties().durability(600).tab(VSCompatsTab.TAB)));
    public static final RegistryObject<Item> INNO_AI_SHEARS = ITEMS_OTHERS.register("inno_ai_shears", () -> new ShearsItem(new Item.Properties().durability(600).tab(VSCompatsTab.TAB)));
    public static final RegistryObject<Item> NICOLAS_AI_SHEARS = ITEMS_OTHERS.register("nicolas_ai_shears", () -> new ShearsItem(new Item.Properties().durability(600).tab(VSCompatsTab.TAB)));
}
