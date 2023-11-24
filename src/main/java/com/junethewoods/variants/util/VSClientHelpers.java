package com.junethewoods.variants.util;

import com.google.common.collect.Maps;
import com.junethewoods.variants.item.custom.armor.WoolArmorItem;
import net.minecraft.block.ComposterBlock;
import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;

import static net.minecraft.item.ItemModelsProperties.register;

public class VSClientHelpers {
    // Can be used to add new wool armor (currently only sweater) colors.
    public static void woolArmorColor(String colorName, int colorCode) {
        WoolArmorItem.COLOR_NAME_TO_CODE = Maps.newHashMap(WoolArmorItem.COLOR_NAME_TO_CODE);
        WoolArmorItem.COLOR_NAME_TO_CODE.put(colorName, colorCode);
    }

    public static void compostable(float chance, Item item) {
        ComposterBlock.COMPOSTABLES.put(item, chance);
    }

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

    public static void makeShield(Item shield) {
        register(shield, new ResourceLocation("blocking"), (stack, world, livEntity) -> livEntity != null && livEntity.isUsingItem() && livEntity.getUseItem() == stack ? 1 : 0);
    }

    public static void makeCustomWoolSweaters(Item sweater) {
        register(sweater, new ResourceLocation("design"), (stack, world, livEntity) -> {
            CompoundNBT tag = stack.getTag();
            if (tag != null && tag.contains("armor_design")) {
                return tag.getInt("armor_design");
            }
            return 0;
        });
    }
}
