package melonystudios.variants.item.custom.food;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.UseAction;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DrinkHelper;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public abstract class DrinkableContainerItem extends Item {
    public ItemStack containerItem = new ItemStack(Items.GLASS_BOTTLE);

    public DrinkableContainerItem(Properties properties) {
        super(properties);
    }

    @Nonnull
    public ItemStack finishUsingItem(ItemStack bottleStack, World world, LivingEntity livEntity) {
        if (livEntity instanceof ServerPlayerEntity) {
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) livEntity;
            CriteriaTriggers.CONSUME_ITEM.trigger(serverPlayer, bottleStack);
            serverPlayer.awardStat(Stats.ITEM_USED.get(this));
        }

        executeFunctionality(this.containerItem, bottleStack, world, livEntity);

        if (bottleStack.isEmpty()) {
            return this.containerItem;
        } else {
            if (livEntity instanceof PlayerEntity && !((PlayerEntity) livEntity).abilities.instabuild) {
                PlayerEntity player = (PlayerEntity) livEntity;
                bottleStack.shrink(1);
                if (!player.inventory.add(this.containerItem)) {
                    player.drop(this.containerItem, false);
                }
            }
            return bottleStack;
        }
    }

    public int getUseDuration(ItemStack stack) {
        return 32;
    }

    @Nonnull
    public UseAction getUseAnimation(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Nonnull
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        return DrinkHelper.useDrink(world, player, hand);
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        return this.containerItem;
    }

    public abstract void executeFunctionality(ItemStack containerStack, ItemStack bottleStack, World world, LivingEntity livEntity);
}
