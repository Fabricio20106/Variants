package melonystudios.variants.item.custom.food;

import melonystudios.variants.stew.StewBehavior;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public abstract class DrinkableContainerItem extends TagConfigurableFoodItem {
    public DrinkableContainerItem(StewBehavior behavior, Properties properties) {
        super(true, behavior, properties);
    }

    @Override
    public ItemStack getContainerItem(ItemStack stack) {
        return getDefaultUseRemainder();
    }

    @Override
    public SoundEvent getDefaultConsumeSound() {
        return SoundEvents.GENERIC_DRINK;
    }

    @Nonnull
    public UseAction getUseAnimation(ItemStack stack) {
        return getConsumeAnimation(stack, UseAction.DRINK);
    }

    @Override
    public boolean hasUseRemainder() {
        return true;
    }

    @Nonnull
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        return DrinkHelper.useDrink(world, player, hand);
    }
}
