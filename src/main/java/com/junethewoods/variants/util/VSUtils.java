package com.junethewoods.variants.util;

import com.google.common.collect.Maps;
import com.junethewoods.variants.Variants;
import com.junethewoods.variants.item.custom.armor.WoolArmorItem;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import org.apache.logging.log4j.LogManager;

import static net.minecraft.item.ItemModelsProperties.register;

public class VSUtils {
    // Can be used to add new wool armor (currently only sweater) colors.
    public static void woolArmorColor(String colorName, int colorCode) {
        WoolArmorItem.COLOR_NAME_TO_CODE = Maps.newHashMap(WoolArmorItem.COLOR_NAME_TO_CODE);
        WoolArmorItem.COLOR_NAME_TO_CODE.put(colorName, colorCode);
    }

    // Adds an item as a villager food (needs to be in #melony:villager_wanted_items item tag).
    public static void addVillagerFoodItem(Item item, int foodPoints) {
        if (!item.is(VSTags.Items.VILLAGER_WANTED_ITEMS)) LogManager.getLogger().info(new TranslationTextComponent("console.variants.villager_food.item_not_in_tag", new TranslationTextComponent(item.getDescriptionId()), VSTags.Items.VILLAGER_WANTED_ITEMS.getName()).getString());
        VillagerEntity.FOOD_POINTS.put(item, foodPoints);
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
            if (tag != null && tag.contains("armor_design")) {
                return tag.getInt("armor_design");
            }
            return 0;
        });
    }

    // Adds properties for exponential stews.
    public static void makeExpoStew(Item expoStew) {
        register(expoStew, Variants.resourceLoc("texture_id"), (stack, world, livEntity) -> {
            CompoundNBT tag = stack.getOrCreateTagElement("bowl");
            if (tag.contains("texture_id")) {
                return tag.getInt("texture_id");
            }
            return 0;
        });
    }
}
