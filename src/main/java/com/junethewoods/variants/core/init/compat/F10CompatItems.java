package com.junethewoods.variants.core.init.compat;

import com.junethewoods.variants.common.item.*;
import com.junethewoods.variants.core.Variants;
import com.junethewoods.variants.core.init.VSFluids;
import com.junethewoods.variants.core.tabs.VSBlocksTab;
import com.junethewoods.variants.core.tabs.VSTab;
import com.junethewoods.variants.core.tabs.VSWeaponryTab;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ShearsItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class F10CompatItems {
    //public static final RegistryObject<Item> plasteel_shears = othersCompat.register("plasteel_shears", () -> new ShearsItem(new Item.Properties().durability(450).tab(F10CompatTab.compat)));
    public static final DeferredRegister<Item> ITEMS_EDITS = DeferredRegister.create(ForgeRegistries.ITEMS, Variants.MOD_ID);
    public static final DeferredRegister<Item> ITEMS_OTHERS = DeferredRegister.create(ForgeRegistries.ITEMS, Variants.MOD_ID);
    public static final DeferredRegister<Item> ITEMS_BACKMATH = DeferredRegister.create(ForgeRegistries.ITEMS, Variants.MOD_ID);
    public static final DeferredRegister<Item> ITEMS_F10PACK = DeferredRegister.create(ForgeRegistries.ITEMS, Variants.MOD_ID);
    public static final DeferredRegister<Item> ITEMS_CREEPER_EDITS = DeferredRegister.create(ForgeRegistries.ITEMS, Variants.MOD_ID);
    public static final DeferredRegister<Item> ITEMS_DECORATIVELARY = DeferredRegister.create(ForgeRegistries.ITEMS, Variants.MOD_ID);

    // Edits
    public static final RegistryObject<Item> SPLASH_SOPH_POTION = ITEMS_EDITS.register("splash_soph_potion", () -> new Item(new Item.Properties().food(F10CompatFoods.SPLASH_SOPH_POTION).tab(VSTab.TAB)));
    public static final RegistryObject<Item> PURPLE_NUGGET = ITEMS_EDITS.register("purple_nugget", () -> new Item(new Item.Properties().tab(VSTab.TAB)));

    // My Other Stuff
    public static final RegistryObject<Item> DIAEMERALD_SHEARS = ITEMS_OTHERS.register("diaemerald_shears", () -> new ShearsItem(new Item.Properties().durability(725).tab(VSWeaponryTab.TAB)));
    public static final RegistryObject<Item> CRYSTAL_SHEARS = ITEMS_OTHERS.register("crystal_shears", () -> new ShearsItem(new Item.Properties().durability(500).tab(VSWeaponryTab.TAB)));
    public static final RegistryObject<Item> LIGHT_MAGENTA_SHEARS = ITEMS_OTHERS.register("light_magenta_shears", () -> new ShearsItem(new Item.Properties().durability(500).tab(VSWeaponryTab.TAB)));
    public static final RegistryObject<Item> ALAN_AI_SHEARS = ITEMS_OTHERS.register("alan_ai_shears", () -> new ShearsItem(new Item.Properties().durability(600).tab(VSWeaponryTab.TAB)));
    public static final RegistryObject<Item> ALICE_AI_SHEARS = ITEMS_OTHERS.register("alice_ai_shears", () -> new ShearsItem(new Item.Properties().durability(600).tab(VSWeaponryTab.TAB)));
    public static final RegistryObject<Item> INNO_AI_SHEARS = ITEMS_OTHERS.register("inno_ai_shears", () -> new ShearsItem(new Item.Properties().durability(600).tab(VSWeaponryTab.TAB)));
    public static final RegistryObject<Item> NICOLAS_AI_SHEARS = ITEMS_OTHERS.register("nicolas_ai_shears", () -> new ShearsItem(new Item.Properties().durability(600).tab(VSWeaponryTab.TAB)));

    // Fabricio2010's Pack
    public static final RegistryObject<Item> FIRST_DOOR = ITEMS_F10PACK.register("first_door", () -> new BlockItem(F10CompatBlocks.FIRST_DOOR.get(), new Item.Properties().tab(VSBlocksTab.TAB)));
    public static final RegistryObject<Item> PLAIN_BIRCH_BOOKSHELF = ITEMS_F10PACK.register("plain_birch_bookshelf", () -> new BlockItem(F10CompatBlocks.PLAIN_BIRCH_BOOKSHELF.get(), new Item.Properties().tab(VSBlocksTab.TAB)));
    public static final RegistryObject<Item> INNO_SHULKER_SHELL = ITEMS_F10PACK.register("inno_shulker_shell", () -> new Item(new Item.Properties().tab(VSTab.TAB)));
    public static final RegistryObject<Item> BLUESTONE_POT = ITEMS_F10PACK.register("bluestone_pot", () -> new Item(new Item.Properties().stacksTo(1).tab(VSTab.TAB)));
    public static final RegistryObject<Item> GLASSY_OAK_BEETROOT_SOUP = ITEMS_F10PACK.register("glassy_oak_beetroot_soup", () -> new CustomSoupItem.GlassyOakBowl(new Item.Properties().stacksTo(1).tab(VSTab.TAB).food(Foods.BEETROOT_SOUP)));
    public static final RegistryObject<Item> PLAIN_BIRCH_BEETROOT_SOUP = ITEMS_F10PACK.register("plain_birch_beetroot_soup", () -> new CustomSoupItem.PlainBirchBowl(new Item.Properties().stacksTo(1).tab(VSTab.TAB).food(Foods.BEETROOT_SOUP)));
    public static final RegistryObject<Item> GLASSY_OAK_MUSHROOM_STEW = ITEMS_F10PACK.register("glassy_oak_mushroom_stew", () -> new CustomSoupItem.GlassyOakBowl(new Item.Properties().stacksTo(1).tab(VSTab.TAB).food(Foods.MUSHROOM_STEW)));
    public static final RegistryObject<Item> PLAIN_BIRCH_MUSHROOM_STEW = ITEMS_F10PACK.register("plain_birch_mushroom_stew", () -> new CustomSoupItem.PlainBirchBowl(new Item.Properties().stacksTo(1).tab(VSTab.TAB).food(Foods.MUSHROOM_STEW)));
    public static final RegistryObject<Item> GLASSY_OAK_RABBIT_STEW = ITEMS_F10PACK.register("glassy_oak_rabbit_stew", () -> new CustomSoupItem.GlassyOakBowl(new Item.Properties().stacksTo(1).tab(VSTab.TAB).food(Foods.RABBIT_STEW)));
    public static final RegistryObject<Item> PLAIN_BIRCH_RABBIT_STEW = ITEMS_F10PACK.register("plain_birch_rabbit_stew", () -> new CustomSoupItem.PlainBirchBowl(new Item.Properties().stacksTo(1).tab(VSTab.TAB).food(Foods.RABBIT_STEW)));
    public static final RegistryObject<Item> GLASSY_OAK_FUNGI_STEW = ITEMS_F10PACK.register("glassy_oak_fungi_stew", () -> new CustomSoupItem.GlassyOakBowl(new Item.Properties().stacksTo(1).tab(VSTab.TAB).food(Foods.MUSHROOM_STEW)));
    public static final RegistryObject<Item> PLAIN_BIRCH_FUNGI_STEW = ITEMS_F10PACK.register("plain_birch_fungi_stew", () -> new CustomSoupItem.PlainBirchBowl(new Item.Properties().stacksTo(1).tab(VSTab.TAB).food(Foods.MUSHROOM_STEW)));
    public static final RegistryObject<Item> GLASSY_OAK_END_FUNGI_STEW = ITEMS_F10PACK.register("glassy_oak_end_fungi_stew", () -> new CustomSoupItem.GlassyOakBowl(new Item.Properties().stacksTo(1).tab(VSTab.TAB).food(Foods.MUSHROOM_STEW)));
    public static final RegistryObject<Item> PLAIN_BIRCH_END_FUNGI_STEW = ITEMS_F10PACK.register("plain_birch_end_fungi_stew", () -> new CustomSoupItem.PlainBirchBowl(new Item.Properties().stacksTo(1).tab(VSTab.TAB).food(Foods.MUSHROOM_STEW)));
    public static final RegistryObject<Item> GLASSY_OAK_BOWL = ITEMS_F10PACK.register("glassy_oak_bowl", () -> new Item(new Item.Properties().tab(VSTab.TAB)));
    public static final RegistryObject<Item> PLAIN_BIRCH_BOWL = ITEMS_F10PACK.register("plain_birch_bowl", () -> new Item(new Item.Properties().tab(VSTab.TAB)));

    // Back Math
    public static final RegistryObject<Item> HILLARY_BOTTLE = ITEMS_BACKMATH.register("hilary_bottle", () -> new HillaryBottleItem(new Item.Properties().tab(VSTab.TAB).stacksTo(8)));

    // Creeper Edits
    public static final RegistryObject<Item> CREEPER_POWDER_POT = ITEMS_CREEPER_EDITS.register("creeper_powder_pot", () -> new Item(new Item.Properties().stacksTo(1).tab(VSTab.TAB)));
    public static final RegistryObject<Item> MUSHROOM_STEW_CREEPER_BUCKET = ITEMS_CREEPER_EDITS.register("mushroom_stew_creeper_bucket", () -> new OldStewBucketItem(new Item.Properties().stacksTo(1).food(Foods.MUSHROOM_STEW).tab(VSTab.TAB)));
    public static final RegistryObject<Item> BEETROOT_SOUP_CREEPER_BUCKET = ITEMS_CREEPER_EDITS.register("beetroot_soup_creeper_bucket", () -> new OldStewBucketItem(new Item.Properties().stacksTo(1).food(Foods.BEETROOT_SOUP).tab(VSTab.TAB)));
    public static final RegistryObject<Item> RABBIT_STEW_CREEPER_BUCKET = ITEMS_CREEPER_EDITS.register("rabbit_stew_creeper_bucket", () -> new OldStewBucketItem(new Item.Properties().stacksTo(1).food(Foods.RABBIT_STEW).tab(VSTab.TAB)));
    public static final RegistryObject<Item> FUNGI_STEW_CREEPER_BUCKET = ITEMS_CREEPER_EDITS.register("fungi_stew_creeper_bucket", () -> new OldStewBucketItem(new Item.Properties().stacksTo(1).food(Foods.MUSHROOM_STEW).tab(VSTab.TAB)));
    public static final RegistryObject<Item> END_FUNGI_STEW_CREEPER_BUCKET = ITEMS_CREEPER_EDITS.register("end_fungi_stew_creeper_bucket", () -> new OldStewBucketItem(new Item.Properties().stacksTo(1).food(Foods.MUSHROOM_STEW).tab(VSTab.TAB)));
    public static final RegistryObject<Item> CREEPER_SOUL_LAVA_BUCKET = ITEMS_CREEPER_EDITS.register("creeper_soul_lava_bucket", () -> new BucketItem(VSFluids.SOUL_LAVA, new Item.Properties().tab(VSTab.TAB)));

    // Decorativelary (formerly known as F10Blocks)
    public static final RegistryObject<Item> WOODEN_BEETROOT_SOUP = ITEMS_DECORATIVELARY.register("wooden_beetroot_soup", () -> new CustomSoupItem.WoodenBowl(new Item.Properties().stacksTo(1).tab(VSTab.TAB).food(Foods.BEETROOT_SOUP)));
    public static final RegistryObject<Item> WOODEN_MUSHROOM_STEW = ITEMS_DECORATIVELARY.register("wooden_mushroom_stew", () -> new CustomSoupItem.WoodenBowl(new Item.Properties().stacksTo(1).tab(VSTab.TAB).food(Foods.MUSHROOM_STEW)));
    public static final RegistryObject<Item> WOODEN_RABBIT_STEW = ITEMS_DECORATIVELARY.register("wooden_rabbit_stew", () -> new CustomSoupItem.WoodenBowl(new Item.Properties().stacksTo(1).tab(VSTab.TAB).food(Foods.RABBIT_STEW)));
    public static final RegistryObject<Item> WOODEN_FUNGI_STEW = ITEMS_DECORATIVELARY.register("wooden_fungi_stew", () -> new CustomSoupItem.WoodenBowl(new Item.Properties().stacksTo(1).tab(VSTab.TAB).food(Foods.MUSHROOM_STEW)));
    public static final RegistryObject<Item> WOODEN_END_FUNGI_STEW = ITEMS_DECORATIVELARY.register("wooden_end_fungi_stew", () -> new CustomSoupItem.WoodenBowl(new Item.Properties().stacksTo(1).tab(VSTab.TAB).food(Foods.MUSHROOM_STEW)));
    public static final RegistryObject<Item> WOODEN_BOWL = ITEMS_DECORATIVELARY.register("wooden_bowl", () -> new Item(new Item.Properties().tab(VSTab.TAB)));

}
