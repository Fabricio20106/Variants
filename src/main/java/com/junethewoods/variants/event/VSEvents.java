package com.junethewoods.variants.event;

import com.junethewoods.variants.Variants;
import com.junethewoods.variants.item.VSItems;
import com.junethewoods.variants.item.VSWeaponry;
import com.junethewoods.variants.item.custom.armor.WoolArmorItem;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MerchantOffer;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.Random;

@Mod.EventBusSubscriber(modid = Variants.MOD_ID)
public class VSEvents {
    @SubscribeEvent
    public static void addWanderingTraderTrades(WandererTradesEvent event) {
         List<VillagerTrades.ITrade> genericTrades = event.getGenericTrades();
         // List<VillagerTrades.ITrade> rareTrades = event.getRareTrades();

         genericTrades.add((trader, rand) -> new MerchantOffer(new ItemStack(Items.EMERALD, 5), new ItemStack(VSItems.PAINTING_SAPLING.get(), 1),
                 // Max Trades, XP, Price Multiplier
                 8, 1, 0.05f));
        genericTrades.add((trader, rand) -> new MerchantOffer(new ItemStack(Items.EMERALD, 1), new ItemStack(VSItems.GLOW_BLACK_TULIP.get(), 1),
                12, 1, 0.05f));
        genericTrades.add((trader, rand) -> new MerchantOffer(new ItemStack(Items.EMERALD, 1), new ItemStack(VSItems.GLOW_BLACK_DYE.get(), 3),
                12, 1, 0.05f));
        genericTrades.add((trader, rand) -> new MerchantOffer(new ItemStack(Items.EMERALD, 5), new ItemStack(VSItems.GLOW_BERRY_BUSH.get(), 1),
                8, 2, 0.05f));
         // rareTrades.add((trader, rand) -> new MerchantOffer(new ItemStack(Items.EMERALD, 24), new ItemStack(ModItems.METAL_DETECTOR.get(), 1), 2, 12, 0.15f));
    }

    @SubscribeEvent
    public static void addVillagerTrades(VillagerTradesEvent event) {
        // Todo: Armorer Trades
        if(event.getType() == VillagerProfession.ARMORER) {
            Int2ObjectMap<List<VillagerTrades.ITrade>> trades = event.getTrades();

            // Level 2 "Apprentice"
            trades.get(2).add((trader, rand) -> new MerchantOffer(new ItemStack(Items.EMERALD, 36), new ItemStack(VSItems.DIAMOND_BELL.get(), 1),
                    12, 5, 0.2f));
            trades.get(2).add((trader, rand) -> new MerchantOffer(new ItemStack(Items.EMERALD, 1), new ItemStack(VSWeaponry.EMPTY_ARMOR_SLOT_BOOTS.get(), 1),
                    12, 5, 0.2f));
            trades.get(2).add((trader, rand) -> new MerchantOffer(new ItemStack(Items.EMERALD, 3), new ItemStack(VSWeaponry.EMPTY_ARMOR_SLOT_LEGGINGS.get(), 1),
                    12, 5, 0.2f));

            // Level 3 "Journeyman"
            trades.get(3).add((trader, rand) -> new MerchantOffer(new ItemStack(VSItems.SOUL_LAVA_BUCKET.get(), 1), new ItemStack(Items.EMERALD, 1),
                    12, 20, 0.05f));
            trades.get(3).add((trader, rand) -> new MerchantOffer(new ItemStack(Items.EMERALD, 1), new ItemStack(VSWeaponry.EMPTY_ARMOR_SLOT_HELMET.get(), 1),
                    12, 10, 0.2f));
            trades.get(3).add((trader, rand) -> new MerchantOffer(new ItemStack(Items.EMERALD, 4), new ItemStack(VSWeaponry.EMPTY_ARMOR_SLOT_CHESTPLATE.get(), 1),
                    12, 10, 0.2f));
            trades.get(3).add((trader, rand) -> new MerchantOffer(new ItemStack(Items.EMERALD, 5), new ItemStack(VSWeaponry.EMPTY_ARMOR_SLOT_SHIELD.get(), 1),
                    12, 10, 0.2f));
        }

        // Todo: Cartographer Trades
        if(event.getType() == VillagerProfession.CARTOGRAPHER) {
            Int2ObjectMap<List<VillagerTrades.ITrade>> trades = event.getTrades();

            // Level 2 "Apprentice"
            trades.get(2).add((trader, rand) -> new MerchantOffer(new ItemStack(VSItems.QUARTZ_GLASS_PANE.get(), 11), new ItemStack(Items.EMERALD, 1),
                    16, 5, 0.05f));
        }

        // Todo: Cleric Trades
        if(event.getType() == VillagerProfession.CLERIC) {
            Int2ObjectMap<List<VillagerTrades.ITrade>> trades = event.getTrades();

            // Level 5 "Master"
            trades.get(5).add((trader, rand) -> new MerchantOffer(new ItemStack(VSItems.WARPED_WART.get(), 22), new ItemStack(Items.EMERALD, 1),
                    12, 30, 0.05f));
        }

        // Todo: Fletcher Trades
        if(event.getType() == VillagerProfession.FLETCHER) {
            Int2ObjectMap<List<VillagerTrades.ITrade>> trades = event.getTrades();

            // Level 1 "Novice"
            trades.get(1).add((trader, rand) -> new MerchantOffer(new ItemStack(VSItems.OAK_STICK.get(), 32), new ItemStack(Items.EMERALD, 1),
                    16, 2, 0.05f));
            trades.get(1).add((trader, rand) -> new MerchantOffer(new ItemStack(VSItems.SPRUCE_STICK.get(), 32), new ItemStack(Items.EMERALD, 1),
                    16, 2, 0.05f));
            trades.get(1).add((trader, rand) -> new MerchantOffer(new ItemStack(VSItems.BIRCH_STICK.get(), 32), new ItemStack(Items.EMERALD, 1),
                    16, 2, 0.05f));
            trades.get(1).add((trader, rand) -> new MerchantOffer(new ItemStack(VSItems.JUNGLE_STICK.get(), 32), new ItemStack(Items.EMERALD, 1),
                    16, 2, 0.05f));
            trades.get(1).add((trader, rand) -> new MerchantOffer(new ItemStack(VSItems.ACACIA_STICK.get(), 32), new ItemStack(Items.EMERALD, 1),
                    16, 2, 0.05f));
            trades.get(1).add((trader, rand) -> new MerchantOffer(new ItemStack(VSItems.DARK_OAK_STICK.get(), 32), new ItemStack(Items.EMERALD, 1),
                    16, 2, 0.05f));
            trades.get(1).add((trader, rand) -> new MerchantOffer(new ItemStack(VSItems.PAINTING_STICK.get(), 32), new ItemStack(Items.EMERALD, 1),
                    16, 2, 0.05f));
            trades.get(1).add((trader, rand) -> new MerchantOffer(new ItemStack(VSItems.CRIMSON_STICK.get(), 32), new ItemStack(Items.EMERALD, 1),
                    16, 2, 0.05f));
            trades.get(1).add((trader, rand) -> new MerchantOffer(new ItemStack(VSItems.WARPED_STICK.get(), 32), new ItemStack(Items.EMERALD, 1),
                    16, 2, 0.05f));
            trades.get(1).add((trader, rand) -> new MerchantOffer(new ItemStack(VSItems.ENDERWOOD_STICK.get(), 32), new ItemStack(Items.EMERALD, 1),
                    16, 2, 0.05f));

            // Level 5 "Master"
            Random rand = new Random();
            int i = 5 + rand.nextInt(15);
            ItemStack debugBow = EnchantmentHelper.enchantItem(rand, new ItemStack(VSWeaponry.DEBUG_BOW.get()), i, false);

            trades.get(5).add((trader, rand1) -> new MerchantOffer(new ItemStack(Items.EMERALD, 48), debugBow,
                    4, 30, 0.5f));
        }

        // Todo: Librarian Trades
        if(event.getType() == VillagerProfession.LIBRARIAN) {
            Int2ObjectMap<List<VillagerTrades.ITrade>> trades = event.getTrades();

            // Level 1 "Novice"
            trades.get(1).add((trader, rand) -> new MerchantOffer(new ItemStack(Items.EMERALD, 9), new ItemStack(VSItems.PLAIN_BIRCH_BOOKSHELF.get(), 1),
                    12, 1, 0.05f));

            // Level 3 "Journeyman"
            trades.get(3).add((trader, rand) -> new MerchantOffer(new ItemStack(Items.EMERALD, 1), new ItemStack(VSItems.QUARTZ_GLASS.get(), 4),
                    12, 10, 0.05f));
        }

        // Todo: Shepherd Trades
        if(event.getType() == VillagerProfession.SHEPHERD) {
            Int2ObjectMap<List<VillagerTrades.ITrade>> trades = event.getTrades();

            // Level 2 "Apprentice"
            trades.get(2).add((trader, rand) -> new MerchantOffer(new ItemStack(VSItems.GLOW_BLACK_DYE.get(), 12), new ItemStack(Items.EMERALD, 1),
                    16, 10, 0.05f));
            trades.get(2).add((trader, rand) -> new MerchantOffer(new ItemStack(Items.EMERALD, 1), new ItemStack(VSItems.GLOW_BLACK_WOOL.get(), 1),
                    16, 5, 0.05f));
            trades.get(2).add((trader, rand) -> new MerchantOffer(new ItemStack(Items.EMERALD, 1), new ItemStack(VSItems.GLOW_BLACK_CARPET.get(), 4),
                    16, 5, 0.05f));

            // Level 3 "Journeyman"
            trades.get(3).add((trader, rand) -> new MerchantOffer(new ItemStack(Items.EMERALD, 3), new ItemStack(VSItems.GLOW_BLACK_BED.get(), 1),
                    12, 10, 0.05f));
            trades.get(3).add((trader, rand) -> new MerchantOffer(new ItemStack(Items.EMERALD, 2), WoolArmorItem.pickRandomColor(VSWeaponry.WOOL_SWEATER.get()),
                    12, 5, 0.2f));
            trades.get(3).add((trader, rand) -> new MerchantOffer(new ItemStack(Items.EMERALD, 4), new ItemStack(VSWeaponry.RABBIT_HIDE_TUNIC.get(), 1),
                    10, 5, 0.25f));
            trades.get(3).add((trader, rand) -> new MerchantOffer(new ItemStack(Items.EMERALD, 3), new ItemStack(VSWeaponry.PHANTOM_MEMBRANE_TUNIC.get(), 1),
                    10, 5, 0.25f));

            // Level 5 "Master"
            trades.get(5).add((trader, rand) -> new MerchantOffer(new ItemStack(Items.EMERALD, 2), new ItemStack(VSItems.PAINTING_DOOR_WANDERER.get(), 2),
                    10, 30, 0.05f));
            trades.get(5).add((trader, rand) -> new MerchantOffer(new ItemStack(Items.EMERALD, 2), new ItemStack(VSItems.PAINTING_DOOR_GRAHAM.get(), 2),
                    10, 30, 0.05f));
            trades.get(5).add((trader, rand) -> new MerchantOffer(new ItemStack(Items.EMERALD, 2), new ItemStack(VSItems.PAINTING_DOOR_FIRST.get(), 2),
                    10, 30, 0.05f));
        }

         // Todo: Toolsmith Trades
        if(event.getType() == VillagerProfession.TOOLSMITH) {
            Int2ObjectMap<List<VillagerTrades.ITrade>> trades = event.getTrades();

            // Level 1 "Novice"
            // Diorite Tools
            trades.get(1).add((trader, rand) -> new MerchantOffer(new ItemStack(Items.EMERALD, 1), new ItemStack(VSWeaponry.DIORITE_AXE.get(), 1), 12, 1, 0.2f));
            trades.get(1).add((trader, rand) -> new MerchantOffer(new ItemStack(Items.EMERALD, 1), new ItemStack(VSWeaponry.DIORITE_SHOVEL.get(), 1), 12, 1, 0.2f));
            trades.get(1).add((trader, rand) -> new MerchantOffer(new ItemStack(Items.EMERALD, 1), new ItemStack(VSWeaponry.DIORITE_PICKAXE.get(), 1), 12, 1, 0.2f));
            trades.get(1).add((trader, rand) -> new MerchantOffer(new ItemStack(Items.EMERALD, 1), new ItemStack(VSWeaponry.DIORITE_HOE.get(), 1), 12, 1, 0.2f));

            // Granite Tools
            trades.get(1).add((trader, rand) -> new MerchantOffer(new ItemStack(Items.EMERALD, 1), new ItemStack(VSWeaponry.GRANITE_AXE.get(), 1), 12, 1, 0.2f));
            trades.get(1).add((trader, rand) -> new MerchantOffer(new ItemStack(Items.EMERALD, 1), new ItemStack(VSWeaponry.GRANITE_SHOVEL.get(), 1), 12, 1, 0.2f));
            trades.get(1).add((trader, rand) -> new MerchantOffer(new ItemStack(Items.EMERALD, 1), new ItemStack(VSWeaponry.GRANITE_PICKAXE.get(), 1), 12, 1, 0.2f));
            trades.get(1).add((trader, rand) -> new MerchantOffer(new ItemStack(Items.EMERALD, 1), new ItemStack(VSWeaponry.GRANITE_HOE.get(), 1), 12, 1, 0.2f));

            // Andesite Tools
            trades.get(1).add((trader, rand) -> new MerchantOffer(new ItemStack(Items.EMERALD, 1), new ItemStack(VSWeaponry.ANDESITE_AXE.get(), 1), 12, 1, 0.2f));
            trades.get(1).add((trader, rand) -> new MerchantOffer(new ItemStack(Items.EMERALD, 1), new ItemStack(VSWeaponry.ANDESITE_SHOVEL.get(), 1), 12, 1, 0.2f));
            trades.get(1).add((trader, rand) -> new MerchantOffer(new ItemStack(Items.EMERALD, 1), new ItemStack(VSWeaponry.ANDESITE_PICKAXE.get(), 1), 12, 1, 0.2f));
            trades.get(1).add((trader, rand) -> new MerchantOffer(new ItemStack(Items.EMERALD, 1), new ItemStack(VSWeaponry.ANDESITE_HOE.get(), 1), 12, 1, 0.2f));

            // Level 2 "Apprentice"
            trades.get(2).add((trader, rand) -> new MerchantOffer(new ItemStack(Items.EMERALD, 36), new ItemStack(VSItems.DIAMOND_BELL.get(), 1),
                    12, 5, 0.2f));
            trades.get(2).add((trader, rand) -> new MerchantOffer(new ItemStack(Items.EMERALD, 3), new ItemStack(VSWeaponry.MAGMA_SWORD.get(), 1),
                    10, 2, 0.2f));
        }

        // Todo: Weaponsmith Trades
        if(event.getType() == VillagerProfession.WEAPONSMITH) {
            Int2ObjectMap<List<VillagerTrades.ITrade>> trades = event.getTrades();

            // Level 2 "Apprentice"
            trades.get(2).add((trader, rand) -> new MerchantOffer(new ItemStack(Items.EMERALD, 36), new ItemStack(VSItems.DIAMOND_BELL.get(), 1),
                    12, 5, 0.2f));

            // Level 3 "Journeyman"
            trades.get(3).add((trader, rand) -> new MerchantOffer(new ItemStack(Items.EMERALD, 12), new ItemStack(VSWeaponry.IRON_SPYGLASS.get(), 1),
                    4, 5, 0.2f));
        }
    }
}
