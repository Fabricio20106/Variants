package melonystudios.variants.item.custom.bottle;

import melonystudios.variants.stew.custom.IgniteBehavior;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.*;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class StainedLavaBottleItem extends StainedFullGlassBottleItem {
    public StainedLavaBottleItem(int secondsOnFire, Properties properties) {
        super(new IgniteBehavior(secondsOnFire * 20), properties);
    }

    @Override
    @Nonnull
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        return DrinkHelper.useDrink(world, player, hand);
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
