package melonystudios.variants.item.custom.bottle;

import com.google.common.collect.Lists;
import melonystudios.variants.stew.custom.RemoveEffectsBehavior;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.potion.Effects;
import net.minecraft.util.*;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class StainedHoneyBottleItem extends StainedFullGlassBottleItem {
    public StainedHoneyBottleItem(Properties properties) {
        super(new RemoveEffectsBehavior(Lists.newArrayList(Effects.POISON)), properties);
    }

    @Override
    @Nonnull
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        return DrinkHelper.useDrink(world, player, hand);
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return getConsumeTicks(stack, 40);
    }

    @Nonnull
    public UseAction getUseAnimation(ItemStack stack) {
        return getConsumeAnimation(stack, UseAction.DRINK);
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
