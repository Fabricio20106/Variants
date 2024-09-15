package melonystudios.variants.item.custom.armor;

import melonystudios.variants.util.Constants;
import net.minecraft.item.IDyeableArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

import java.util.List;

public interface DyeableArmorItem extends IDyeableArmorItem {
    @Override
    default int getColor(ItemStack stack) {
        CompoundNBT displayTag = stack.getTagElement("display");
        return displayTag != null && displayTag.contains("color", Constants.TagTypes.ANY_NUMERIC) ? displayTag.getInt("color") : getDefaultColor();
    }

    int getDefaultColor();

    static ItemStack dyeArmor(ItemStack stack, List<Integer> colors) {
        ItemStack emptyStack = ItemStack.EMPTY;
        int[] rgbColors = new int[3];
        int i = 0;
        int j = 0;
        DyeableArmorItem dyeableArmorItem = null;
        Item item = stack.getItem();

        if (item instanceof DyeableArmorItem) {
            dyeableArmorItem = (DyeableArmorItem) item;
            emptyStack = stack.copy();
            emptyStack.setCount(1);
            if (dyeableArmorItem.hasCustomColor(stack)) {
                int color = dyeableArmorItem.getColor(emptyStack);
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
            dyeableArmorItem.setColor(emptyStack, j2);
            return emptyStack;
        }
    }
}
