package melonystudios.variants.item.custom;

import melonystudios.variants.dispenser.vanilla.BucketDispenseBehavior;
import melonystudios.variants.fluid.VSFluids;
import net.minecraft.block.DispenserBlock;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;

import javax.annotation.Nullable;

public class SoulLavaBucketItem extends BucketItem {
    public SoulLavaBucketItem(Properties properties) {
        super(VSFluids.SOUL_LAVA, properties);
        DispenserBlock.registerBehavior(this, new BucketDispenseBehavior());
    }

    @Override
    public int getBurnTime(ItemStack stack, @Nullable IRecipeType<?> recipeType) {
        return 40000; // 200 Items.
    }
}
