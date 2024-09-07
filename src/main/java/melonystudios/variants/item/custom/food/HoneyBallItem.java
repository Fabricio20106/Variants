package melonystudios.variants.item.custom.food;

import melonystudios.variants.stew.StewBehavior;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class HoneyBallItem extends TagConfigurableFoodItem {
    public HoneyBallItem(StewBehavior behavior, Properties properties) {
        super(false, behavior, properties);
    }

    @Override
    @Nonnull
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        return DrinkHelper.useDrink(world, player, hand);
    }

    @Override
    @Nonnull
    public ItemStack finishUsingItem(ItemStack stack, World world, LivingEntity livEntity) {
        super.finishUsingItem(stack, world, livEntity);
        if (livEntity instanceof ServerPlayerEntity) {
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) livEntity;
            CriteriaTriggers.CONSUME_ITEM.trigger(serverPlayer, stack);
            serverPlayer.awardStat(Stats.ITEM_USED.get(this));
        }

        if (!world.isClientSide) executeConsumeBehavior(stack, world, livEntity, this.behavior);
        return stack;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return getConsumeTicks(stack, 40);
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
