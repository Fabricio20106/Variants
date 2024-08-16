package melonystudios.variants.item.custom;

import melonystudios.variants.util.Constants;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;

import javax.annotation.Nullable;

public class FlammableItem extends Item {
    private final int burnTime;

    public FlammableItem(int burnTime, Properties properties) {
        super(properties);
        this.burnTime = burnTime;
    }

    @Override
    public int getBurnTime(ItemStack stack, @Nullable IRecipeType<?> recipeType) {
        if (stack.getTag() != null && stack.getTag().contains("burn_time", Constants.TagTypes.ANY_NUMERIC)) return stack.getTag().getInt("burn_time");
        return this.burnTime;
    }
}
