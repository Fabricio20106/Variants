package melonystudios.variants.item.custom.tool;

import melonystudios.variants.util.Constants;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShearsItem;
import net.minecraft.item.crafting.IRecipeType;

import javax.annotation.Nullable;

public class CoalShearsItem extends ShearsItem {
    public CoalShearsItem(Properties properties) {
        super(properties);
    }

    @Override
    public int getBurnTime(ItemStack stack, @Nullable IRecipeType<?> recipeType) {
        if (stack.getTag() != null && stack.getTag().contains("burn_time", Constants.TagTypes.ANY_NUMERIC)) return stack.getTag().getInt("burn_time");
        return 3200;
    }
}
