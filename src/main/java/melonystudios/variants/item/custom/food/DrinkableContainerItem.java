package melonystudios.variants.item.custom.food;

import melonystudios.variants.stew.StewBehavior;
import melonystudios.variants.stew.custom.DefaultStewBehavior;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.*;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public abstract class DrinkableContainerItem extends TagConfigurableFoodItem {
    public DrinkableContainerItem(StewBehavior behavior, Properties properties) {
        super(true, behavior, properties);
    }

    @Nonnull
    public ItemStack finishUsingItem(ItemStack bottleStack, World world, LivingEntity livEntity) {
        super.finishUsingItem(bottleStack, world, livEntity);
        if (livEntity instanceof ServerPlayerEntity) {
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) livEntity;
            CriteriaTriggers.CONSUME_ITEM.trigger(serverPlayer, bottleStack);
            serverPlayer.awardStat(Stats.ITEM_USED.get(this));
        }

        ItemStack remainderStack = getUseRemainder(bottleStack);
        if (bottleStack.isEmpty()) {
            return remainderStack;
        } else {
            if (livEntity instanceof PlayerEntity && !((PlayerEntity) livEntity).abilities.instabuild) {
                PlayerEntity player = (PlayerEntity) livEntity;
                bottleStack.shrink(1);
                if (!player.inventory.add(remainderStack)) {
                    player.drop(remainderStack, false);
                }
            }
            return bottleStack;
        }
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

    @Nonnull
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        return DrinkHelper.useDrink(world, player, hand);
    }
}
