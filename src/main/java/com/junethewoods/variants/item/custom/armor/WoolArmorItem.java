package com.junethewoods.variants.item.custom.armor;

import com.google.common.collect.ImmutableMap;
import com.junethewoods.variants.config.VSConfigs;
import com.junethewoods.variants.item.VSWeaponry;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.Map;

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

    public WoolArmorItem(IArmorMaterial material, EquipmentSlotType slot, Properties properties) {
        super(material, slot, properties);
    }

    @Override
    public ITextComponent getName(ItemStack stack) {
        if (stack.hasTag() && !stack.getTag().getString("color_name").isEmpty()) {
            return new TranslationTextComponent(this.getDescriptionId() + ".colored", new TranslationTextComponent(stack.getTag().getString("color_name")));
        } else {
            return new TranslationTextComponent(this.getDescriptionId());
        }
    }

    @Override
    public void fillItemCategory(ItemGroup itemTab, NonNullList<ItemStack> list) {
        if (this.allowdedIn(itemTab)) {
            list.add(new ItemStack(VSWeaponry.WOOL_SWEATER.get()));

            if (VSConfigs.COMMON_CONFIGS.populateWoolArmorColorInTabs.get()) {
                for (String i : COLOR_NAME_TO_CODE.keySet()) {
                    ItemStack stack = new ItemStack(VSWeaponry.WOOL_SWEATER.get());
                    CompoundNBT displayTag = stack.getOrCreateTagElement("display");
                    CompoundNBT tag = stack.getOrCreateTag();

                    displayTag.putInt("color", COLOR_NAME_TO_CODE.get(i));
                    tag.putString("color_name", i);
                    list.add(stack);
                }
            }
        }
    }

    public static ItemStack pickRandomColor(Item item) {
        ItemStack stack = new ItemStack(item);
        CompoundNBT displayTag = stack.getOrCreateTagElement("display");
        CompoundNBT tag = stack.getOrCreateTag();

        Object[] colorCodes = COLOR_NAME_TO_CODE.values().toArray();
        Object[] colorNames = COLOR_NAME_TO_CODE.keySet().toArray();
        int randomValue = random.nextInt(colorCodes.length);
        int randomCode = (int) colorCodes[randomValue];
        String randomName = (String) colorNames[randomValue];

        displayTag.putInt("color", randomCode);
        tag.putString("color_name", randomName);

        return stack;
    }
}
