package com.junethewoods.variants.core.init;

import com.junethewoods.variants.common.item.CustomSoupItem;
import com.junethewoods.variants.core.Variants;
import com.junethewoods.variants.common.item.F10CompatFoods;
import com.junethewoods.variants.core.tabs.VariantBlocks;
import com.junethewoods.variants.core.tabs.VariantTab;
import com.junethewoods.variants.core.tabs.WeaponryTab;
import com.junethewoods.variants.common.item.HilaryBottleItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Foods;
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
    public static final DeferredRegister<Item> creeperEditsCompat = DeferredRegister.create(ForgeRegistries.ITEMS, Variants.mod_id);
    public static final DeferredRegister<Item> f10BlocksCompat = DeferredRegister.create(ForgeRegistries.ITEMS, Variants.mod_id);

    // Edits
    public static final RegistryObject<Item> splash_soph_potion = editsCompat.register("splash_soph_potion", () -> new Item(new Item.Properties().food(F10CompatFoods.splash_soph).group(VariantTab.variant)));
    public static final RegistryObject<Item> purple_nugget = editsCompat.register("purple_nugget", () -> new Item(new Item.Properties().group(VariantTab.variant)));

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
    public static final RegistryObject<Item> inno_shulker_shell = fabricio2010packCompat.register("inno_shulker_shell", () -> new Item(new Item.Properties().group(VariantTab.variant)));
    public static final RegistryObject<Item> bluestone_pot = fabricio2010packCompat.register("bluestone_pot", () -> new Item(new Item.Properties().maxStackSize(1).group(VariantTab.variant)));
    public static final RegistryObject<Item> glassy_oak_beetroot_soup = fabricio2010packCompat.register("glassy_oak_beetroot_soup", () -> new CustomSoupItem.GlassyOakBowl(new Item.Properties().maxStackSize(1).group(VariantTab.variant).food(Foods.BEETROOT_SOUP)));
    public static final RegistryObject<Item> plain_birch_beetroot_soup = fabricio2010packCompat.register("plain_birch_beetroot_soup", () -> new CustomSoupItem.PlainBirchBowl(new Item.Properties().maxStackSize(1).group(VariantTab.variant).food(Foods.BEETROOT_SOUP)));
    public static final RegistryObject<Item> glassy_oak_mushroom_stew = fabricio2010packCompat.register("glassy_oak_mushroom_stew", () -> new CustomSoupItem.GlassyOakBowl(new Item.Properties().maxStackSize(1).group(VariantTab.variant).food(Foods.MUSHROOM_STEW)));
    public static final RegistryObject<Item> plain_birch_mushroom_stew = fabricio2010packCompat.register("plain_birch_mushroom_stew", () -> new CustomSoupItem.PlainBirchBowl(new Item.Properties().maxStackSize(1).group(VariantTab.variant).food(Foods.MUSHROOM_STEW)));
    public static final RegistryObject<Item> glassy_oak_rabbit_stew = fabricio2010packCompat.register("glassy_oak_rabbit_stew", () -> new CustomSoupItem.GlassyOakBowl(new Item.Properties().maxStackSize(1).group(VariantTab.variant).food(Foods.RABBIT_STEW)));
    public static final RegistryObject<Item> plain_birch_rabbit_stew = fabricio2010packCompat.register("plain_birch_rabbit_stew", () -> new CustomSoupItem.PlainBirchBowl(new Item.Properties().maxStackSize(1).group(VariantTab.variant).food(Foods.RABBIT_STEW)));
    public static final RegistryObject<Item> glassy_oak_fungi_stew = fabricio2010packCompat.register("glassy_oak_fungi_stew", () -> new CustomSoupItem.GlassyOakBowl(new Item.Properties().maxStackSize(1).group(VariantTab.variant).food(Foods.MUSHROOM_STEW)));
    public static final RegistryObject<Item> plain_birch_fungi_stew = fabricio2010packCompat.register("plain_birch_fungi_stew", () -> new CustomSoupItem.PlainBirchBowl(new Item.Properties().maxStackSize(1).group(VariantTab.variant).food(Foods.MUSHROOM_STEW)));
    public static final RegistryObject<Item> glassy_oak_end_fungi_stew = fabricio2010packCompat.register("glassy_oak_end_fungi_stew", () -> new CustomSoupItem.GlassyOakBowl(new Item.Properties().maxStackSize(1).group(VariantTab.variant).food(Foods.MUSHROOM_STEW)));
    public static final RegistryObject<Item> plain_birch_end_fungi_stew = fabricio2010packCompat.register("plain_birch_end_fungi_stew", () -> new CustomSoupItem.PlainBirchBowl(new Item.Properties().maxStackSize(1).group(VariantTab.variant).food(Foods.MUSHROOM_STEW)));
    public static final RegistryObject<Item> glassy_oak_bowl = fabricio2010packCompat.register("glassy_oak_bowl", () -> new Item(new Item.Properties().group(VariantTab.variant)));
    public static final RegistryObject<Item> plain_birch_bowl = fabricio2010packCompat.register("plain_birch_bowl", () -> new Item(new Item.Properties().group(VariantTab.variant)));

    // Back Math
    public static final RegistryObject<Item> hilary_bottle = backMathCompat.register("hilary_bottle", () -> new HilaryBottleItem(new Item.Properties().group(VariantTab.variant).maxStackSize(8)));

    // CreeperEdits
    public static final RegistryObject<Item> creeper_powder_pot = creeperEditsCompat.register("creeper_powder_pot", () -> new Item(new Item.Properties().maxStackSize(1).group(VariantTab.variant)));

    // F10Blocks
    public static final RegistryObject<Item> wooden_beetroot_soup = f10BlocksCompat.register("wooden_beetroot_soup", () -> new CustomSoupItem.WoodenBowl(new Item.Properties().maxStackSize(1).group(VariantTab.variant).food(Foods.BEETROOT_SOUP)));
    public static final RegistryObject<Item> wooden_mushroom_stew = f10BlocksCompat.register("wooden_mushroom_stew", () -> new CustomSoupItem.WoodenBowl(new Item.Properties().maxStackSize(1).group(VariantTab.variant).food(Foods.MUSHROOM_STEW)));
    public static final RegistryObject<Item> wooden_rabbit_stew = f10BlocksCompat.register("wooden_rabbit_stew", () -> new CustomSoupItem.WoodenBowl(new Item.Properties().maxStackSize(1).group(VariantTab.variant).food(Foods.RABBIT_STEW)));
    public static final RegistryObject<Item> wooden_fungi_stew = f10BlocksCompat.register("wooden_fungi_stew", () -> new CustomSoupItem.WoodenBowl(new Item.Properties().maxStackSize(1).group(VariantTab.variant).food(Foods.MUSHROOM_STEW)));
    public static final RegistryObject<Item> wooden_end_fungi_stew = f10BlocksCompat.register("wooden_end_fungi_stew", () -> new CustomSoupItem.WoodenBowl(new Item.Properties().maxStackSize(1).group(VariantTab.variant).food(Foods.MUSHROOM_STEW)));
    public static final RegistryObject<Item> wooden_bowl = f10BlocksCompat.register("wooden_bowl", () -> new Item(new Item.Properties().group(VariantTab.variant)));

}
