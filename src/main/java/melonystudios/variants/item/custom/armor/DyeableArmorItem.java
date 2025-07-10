package melonystudios.variants.item.custom.armor;

import melonystudios.variants.Variants;
import melonystudios.variants.item.custom.armor.color.WoolArmorColor;
import melonystudios.variants.util.Constants;
import net.minecraft.item.IDyeableArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.List;
import java.util.Random;

public interface DyeableArmorItem extends IDyeableArmorItem {
    @Override
    default int getColor(ItemStack stack) {
        CompoundNBT displayTag = stack.getTagElement("display");
        return displayTag != null && displayTag.contains("color", Constants.TagTypes.ANY_NUMERIC) ? displayTag.getInt("color") : this.getDefaultColor();
    }

    @Override
    default boolean hasCustomColor(ItemStack stack) {
        CompoundNBT displayTag = stack.getTagElement("display");
        if (displayTag != null && displayTag.contains("color", Constants.TagTypes.ANY_NUMERIC)) {
            return displayTag.getInt("color") != WoolArmorColor.WHITE.getColor();
        } else {
            return false;
        }
    }

    /// The default color used for this dyeable armor item.
    /// <p>
    /// Defaults to <code>16777215</code> for wool armor pieces and <code>10511680</code> for leather armor pieces.
    int getDefaultColor();

    static ItemStack dyeArmor(ItemStack stack, List<Integer> colors) {
        ItemStack sweaterStack = ItemStack.EMPTY;
        int[] rgbColors = new int[3];
        int i = 0;
        int j = 0;
        DyeableArmorItem dyeableArmorItem = null;
        Item item = stack.getItem();

        if (item instanceof DyeableArmorItem) {
            dyeableArmorItem = (DyeableArmorItem) item;
            sweaterStack = stack.copy();
            sweaterStack.setCount(1);
            if (dyeableArmorItem.hasCustomColor(stack)) {
                int color = dyeableArmorItem.getColor(sweaterStack);
                float red = (float) (color >> 16 & 255) / 255;
                float green = (float) (color >> 8 & 255) / 255;
                float blue = (float) (color & 255) / 255;
                i = (int) ((float) i + Math.max(red, Math.max(green, blue)) * 255);
                rgbColors[0] = (int) ((float) rgbColors[0] + red * 255);
                rgbColors[1] = (int) ((float) rgbColors[1] + green * 255);
                rgbColors[2] = (int) ((float) rgbColors[2] + blue * 255);
                ++j;
            }

            for (Integer color : colors) {
                int red = (color & 16711680) >> 16;
                int green = (color & '\uff00') >> 8;
                int blue = color & 255;
                i += Math.max(red, Math.max(green, blue));
                rgbColors[0] += red;
                rgbColors[1] += green;
                rgbColors[2] += blue;
                ++j;
            }
        }

        if (dyeableArmorItem == null) {
            return ItemStack.EMPTY;
        } else {
            int red = rgbColors[0] / j;
            int green = rgbColors[1] / j;
            int blue = rgbColors[2] / j;
            float f3 = (float) i / (float) j;
            float f4 = (float) Math.max(red, Math.max(green, blue));
            red = (int) ((float) red * f3 / f4);
            green = (int) ((float) green * f3 / f4);
            blue = (int) ((float) blue * f3 / f4);
            int j2 = (red << 8) + green;
            j2 = (j2 << 8) + blue;
            dyeableArmorItem.setColor(sweaterStack, j2);
            return sweaterStack;
        }
    }

    /// Picks a random wool armor color (that's not an armor design) and applies it to the provided item stack.
    /// @param stack The item stack to apply the color to.
    /// @param random Used by {@link #rollArmorColor} to pick a random wool armor color.
    static ItemStack pickRandomColor(ItemStack stack, Random random) {
        CompoundNBT displayTag = stack.getOrCreateTagElement("display");
        CompoundNBT tag = stack.getOrCreateTag();

        WoolArmorColor armorColor = rollArmorColor(random);
        while (armorColor.getArmorDesign().isPresent()) armorColor = rollArmorColor(random);
        displayTag.putInt("color", armorColor.getColor());
        tag.putString("color_name", armorColor.getColorName());

        return stack;
    }

    /// Rolls a random wool armor color, used by {@link #pickRandomColor}.
    /// @param random Used to pick a random wool armor color, be it a color or armor design.
    static WoolArmorColor rollArmorColor(Random random) {
        // fix a crash in multiplayer when rendering tabs due to wool armor colors not loading or something ~isa 4-7-25
        try {
            Object[] armorColors = WoolArmorColor.DATA_DRIVEN_COLORS.values().toArray();
            int randomValue = random.nextInt(armorColors.length);
            return (WoolArmorColor) armorColors[randomValue];
        } catch (IllegalArgumentException exception) {
            Variants.LOGGER.error(new TranslationTextComponent("error.variants.wool_armor_color.randomizing").getString(), exception);
            return WoolArmorColor.GLOW_BLACK;
        }
    }

    /// Whether this item stack has an <code>armor_design</code> tag.
    static boolean hasArmorDesign(ItemStack stack) {
        return stack.getTag() != null && stack.getTag().contains("armor_design", Constants.TagTypes.ANY_NUMERIC);
    }

    /// Sets an item stack's armor color and name.
    /// @param stack The item stack to set the color and name. Is usually a wool armor piece.
    /// @param color Color to apply, preferably should be between 0-16777215.
    /// @param colorName Name to apply. Can be either a raw string or a translation key.
    static ItemStack setColorAndName(ItemStack stack, int color, String colorName) {
        CompoundNBT tag = stack.getOrCreateTag();
        CompoundNBT displayTag = stack.getOrCreateTagElement("display");
        displayTag.putInt("color", color);
        tag.putString("color_name", colorName);
        return stack;
    }

    /// Sets an armor design to an item stack.
    /// @param stack The item stack to set the armor design.
    /// @param armorDesign Non-negative integer to define as the design.
    static ItemStack setArmorDesign(ItemStack stack, int armorDesign) {
        CompoundNBT tag = stack.getOrCreateTag();
        tag.putInt("armor_design", armorDesign);
        tag.putString("color_name", "armor_design." + Variants.MOD_ID + "." + armorDesign);
        return stack;
    }

    /// Clears the <code>color_name</code> tag from an item stack.
    static void clearColorName(ItemStack stack) {
        if (stack.getTag() != null && stack.getTag().contains("color_name", Constants.TagTypes.STRING)) {
            stack.getTag().remove("color_name");
        }
    }

    /// Clears the <code>armor_design</code> tag from an item stack.
    static void clearArmorDesign(ItemStack stack) {
        if (stack.getTag() != null && stack.getTag().contains("armor_design", Constants.TagTypes.ANY_NUMERIC)) {
            stack.getTag().remove("armor_design");
        }
    }
}
