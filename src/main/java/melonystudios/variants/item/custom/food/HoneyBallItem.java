package melonystudios.variants.item.custom.food;

import melonystudios.variants.consumable.ConsumeBehavior;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class HoneyBallItem extends ConsumableItem {
    public HoneyBallItem(ConsumeBehavior behavior, Properties properties) {
        super(false, behavior, properties);
    }

    @Override
    @Nonnull
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        return DrinkHelper.useDrink(world, player, hand);
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return this.getConsumeTicks(stack, 40);
    }

    @Override
    public SoundEvent getDefaultConsumeSound() {
        return SoundEvents.HONEY_DRINK;
    }

    @Override
    @Nonnull
    public SoundEvent getEatingSound() {
        return SoundEvents.HONEY_DRINK;
    }
}
