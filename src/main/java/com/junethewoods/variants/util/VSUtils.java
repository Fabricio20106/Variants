package com.junethewoods.variants.util;

import com.google.common.collect.Maps;
import com.junethewoods.variants.Variants;
import com.junethewoods.variants.crafting.custom.WoolArmorDyeingRecipe;
import com.junethewoods.variants.item.custom.armor.WoolArmorItem;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.ModList;
import org.apache.logging.log4j.LogManager;

import static net.minecraft.item.ItemModelsProperties.register;

public class VSUtils {
    // Puts an item in the player's hands without playing the "Gear equips" sound.
    public static void setItemInHand(PlayerEntity player, Hand hand, ItemStack stack) {
        if (hand == Hand.MAIN_HAND) {
            setItemSlot(player, EquipmentSlotType.MAINHAND, stack);
        } else {
            if (hand != Hand.OFF_HAND) throw new IllegalArgumentException("Invalid hand: " + hand);
            setItemSlot(player, EquipmentSlotType.OFFHAND, stack);
        }
    }

    // Puts an item any of the player's slots without playing the "Gear equips" sound.
    public static void setItemSlot(PlayerEntity player, EquipmentSlotType slot, ItemStack stack) {
        if (slot == EquipmentSlotType.MAINHAND) {
            player.inventory.items.set(player.inventory.selected, stack);
        } else if (slot == EquipmentSlotType.OFFHAND) {
            player.inventory.offhand.set(0, stack);
        } else if (slot.getType() == EquipmentSlotType.Group.ARMOR) {
            player.inventory.armor.set(slot.getIndex(), stack);
        }
    }

    // Can be used to add new wool armor (currently only sweater) colors.
    public static void woolArmorColor(String colorName, int colorCode) {
        WoolArmorItem.COLOR_NAME_TO_CODE = Maps.newHashMap(WoolArmorItem.COLOR_NAME_TO_CODE);
        WoolArmorItem.COLOR_NAME_TO_CODE.put(colorName, colorCode);
    }

    // Can be used to add items as a valid dye for dyeing wool armor (currently only sweater).
    public static void woolArmorDyeingColor(Item dyeItem, int color, String loadedMod) {
        WoolArmorDyeingRecipe.DYE_COLORS_MAP = Maps.newHashMap(WoolArmorDyeingRecipe.DYE_COLORS_MAP);
        if (ModList.get().isLoaded(loadedMod)) WoolArmorDyeingRecipe.DYE_COLORS_MAP.put(dyeItem, color);
    }

    // Adds an item as a villager food (needs to be in #melony:villager_wanted_items item tag).
    public static void addVillagerFoodItem(Item item, int foodPoints) {
        if (!item.is(VSTags.Items.VILLAGER_WANTED_ITEMS)) LogManager.getLogger().info(new TranslationTextComponent("console.variants.villager_food.item_not_in_tag", new TranslationTextComponent(item.getDescriptionId()), VSTags.Items.VILLAGER_WANTED_ITEMS.getName()).getString());
        VillagerEntity.FOOD_POINTS.put(item, foodPoints);
    }

    public static ResourceLocation minecraft(String name) {
        return new ResourceLocation(name);
    }

    // Adds properties for a bow.
    public static void makeBow(Item bow) {
        register(bow, new ResourceLocation("pull"), (stack, world, livEntity) -> {
            if (livEntity == null) {
                return 0;
            } else {
                return livEntity.getUseItem() != stack ? 0 : (float) (stack.getUseDuration() - livEntity.getUseItemRemainingTicks()) / 20;
            }
        });
        register(bow, new ResourceLocation("pulling"), (stack, world, livEntity) -> livEntity != null && livEntity.isUsingItem() && livEntity.getUseItem() == stack ? 1 : 0);
    }

    // Adds properties for a shield.
    public static void makeShield(Item shield) {
        register(shield, new ResourceLocation("blocking"), (stack, world, livEntity) -> livEntity != null && livEntity.isUsingItem() && livEntity.getUseItem() == stack ? 1 : 0);
    }

    // Adds properties for armor designs.
    public static void addArmorDesigns(Item sweater) {
        register(sweater, Variants.resourceLoc("design"), (stack, world, livEntity) -> {
            CompoundNBT tag = stack.getTag();
            if (tag != null && tag.contains("armor_design", NBTUtils.INTEGER)) return tag.getInt("armor_design");
            return 0;
        });
    }

    // Add properties for mob ids for spawner minecarts.
    public static void addSpawnerMinecartMobs(Item spawnerMinecart) {
        register(spawnerMinecart, Variants.resourceLoc("mob_id"), (stack, world, livEntity) -> {
            CompoundNBT spawnData = stack.getTagElement("spawn_data");
            if (spawnData != null && spawnData.contains("SpawnData", NBTUtils.COMPOUND)) {
                CompoundNBT subSpawnData = spawnData.getCompound("SpawnData");
                if (subSpawnData.contains("id", NBTUtils.STRING)) {
                    switch (subSpawnData.getString("id")) {
                        case "minecraft:zombie": return 1;
                        case "minecraft:skeleton": return 2;
                        case "minecraft:spider": return 3;
                        case "minecraft:cave_spider": return 4;
                        case "minecraft:silverfish": return 5;
                        case "minecraft:blaze": return 6;
                        case "minecraft:magma_cube": return 7;
                        default: return 0;
                    }
                }
            }
            return 0;
        });
    }

    // Adds properties for exponential stews.
    public static void makeExpoStew(Item expoStew) {
        register(expoStew, Variants.resourceLoc("texture_id"), (stack, world, livEntity) -> {
            CompoundNBT bowlTag = stack.getTagElement("bowl");
            if (bowlTag != null && bowlTag.contains("texture_id", NBTUtils.INTEGER)) return bowlTag.getInt("texture_id");
            return 0;
        });
    }
}
