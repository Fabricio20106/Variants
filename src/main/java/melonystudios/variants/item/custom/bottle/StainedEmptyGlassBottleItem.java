package melonystudios.variants.item.custom.bottle;

import melonystudios.variants.util.Constants;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class StainedEmptyGlassBottleItem extends Item {
    private final int glassColor;
    private final ResourceLocation colorName;

    public StainedEmptyGlassBottleItem(int glassColor, String colorName, Properties properties) {
        super(properties);
        this.glassColor = glassColor;
        this.colorName = new ResourceLocation(colorName);
    }

    public StainedEmptyGlassBottleItem(DyeColor dyeColor, Properties properties) {
        super(properties);
        this.glassColor = dyeColor.getColorValue();
        this.colorName = new ResourceLocation(dyeColor.getName());
    }

    public int getGlassColor(@Nullable ItemStack stack) {
        if (stack != null) {
            CompoundNBT tag = stack.getTag();
            if (tag != null && tag.contains("glass_color", Constants.TagTypes.ANY_NUMERIC)) return tag.getInt("glass_color");
        }
        return this.glassColor;
    }

    public ResourceLocation getColorName(@Nullable ItemStack stack) {
        if (stack != null) {
            CompoundNBT tag = stack.getTag();
            if (tag != null && tag.contains("color_name", Constants.TagTypes.STRING)) return new ResourceLocation(tag.getString("color_name"));
        }
        return this.colorName;
    }
}
