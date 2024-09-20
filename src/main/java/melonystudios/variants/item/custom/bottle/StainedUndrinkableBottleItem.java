package melonystudios.variants.item.custom.bottle;

import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.*;

import javax.annotation.Nonnull;

public class StainedUndrinkableBottleItem extends StainedFullGlassBottleItem {
    public StainedUndrinkableBottleItem(Properties properties) {
        super(properties);
    }

    @Nonnull
    public UseAction getUseAnimation(ItemStack stack) {
        return getConsumeAnimation(stack, UseAction.DRINK);
    }

    @Override
    public SoundEvent getDefaultConsumeSound() {
        return SoundEvents.GENERIC_DRINK;
    }

    @Override
    @Nonnull
    public SoundEvent getEatingSound() {
        return SoundEvents.GENERIC_DRINK;
    }
}
