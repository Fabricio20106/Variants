package com.junethewoods.variants.item.custom.armor;

import com.google.common.collect.ImmutableMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.Map;
import java.util.Random;

public class WoolArmorItem extends ArmorItem implements IDyeableWoolArmorItem {
    // If you add an item to this list through the method used in Back Math, it will brick the order of the colors in the creative menu! Which is the whole point I wrote this thing.

    // Upsides to this method:
    // - You may register new color easily, and add them straight to the creative tab.
    // - It's the first time I use "for" loops ever for something that I came up with.
    // Downsides to this method:
    // - When you add a new color, it breaks the order of the colors, and it becomes a mess in the menu.
    public static Map<String, Integer> COLOR_NAME_TO_CODE = new ImmutableMap.Builder<String, Integer>()
            .put("color.minecraft.white", 16383998).put("color.f10elements.inno", 15457757).put("color.minecraft.orange", 16351261).put("color.minecraft.magenta", 13061821)
            .put("color.backmath.aljan_light_blue", 13429739).put("color.minecraft.light_blue", 3847130).put("color.variants.glow_black", 8454080).put("color.minecraft.yellow", 16701501)
            .put("color.minecraft.lime", 8439583).put("color.minecraft.pink", 15961002).put("color.minecraft.gray", 4673362).put("color.minecraft.light_gray", 10329495)
            .put("color.minecraft.cyan", 1481884).put("color.minecraft.purple", 8991416).put("color.backmath.insomnian", 4418465).put("color.minecraft.blue", 3949738)
            .put("color.minecraft.brown", 8606770).put("color.backmath.poison_brown", 8921856).put("color.minecraft.green", 6192150).put("color.minecraft.red", 11546150)
            .put("color.backmath.red_yellow", 15731456).put("color.minecraft.black", 1908001).build();

    public WoolArmorItem(ArmorMaterial material, ArmorItem.Type slot, Properties properties) {
        super(material, slot, properties);
    }

    @Override
    public Component getName(ItemStack stack) {
        if (stack.hasTag() && !stack.getTag().getString("color_name").isEmpty()) {
            return Component.translatable(this.getDescriptionId() + ".colored", Component.translatable(stack.getTag().getString("color_name")));
        } else {
            return Component.translatable(this.getDescriptionId());
        }
    }

    public static ItemStack pickRandomColor(Item item) {
        ItemStack stack = new ItemStack(item);
        CompoundTag displayTag = stack.getOrCreateTagElement("display");
        CompoundTag tag = stack.getOrCreateTag();

        Object[] colorCodes = COLOR_NAME_TO_CODE.values().toArray();
        Object[] colorNames = COLOR_NAME_TO_CODE.keySet().toArray();
        int randomValue = new Random().nextInt(colorCodes.length);
        int randomCode = (int) colorCodes[randomValue];
        String randomName = (String) colorNames[randomValue];

        displayTag.putInt("color", randomCode);
        tag.putString("color_name", randomName);

        return stack;
    }
}
