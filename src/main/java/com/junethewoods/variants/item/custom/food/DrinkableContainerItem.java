package com.junethewoods.variants.item.custom.food;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

public abstract class DrinkableContainerItem extends Item {
    public ItemStack containerItem = new ItemStack(Items.GLASS_BOTTLE);

    public DrinkableContainerItem(Properties properties) {
        super(properties);
    }

    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livEntity) {
        setContainerItem(containerItem);

        if (livEntity instanceof ServerPlayer) {
            ServerPlayer serverPlayer = (ServerPlayer) livEntity;
            CriteriaTriggers.CONSUME_ITEM.trigger(serverPlayer, stack);
            serverPlayer.awardStat(Stats.ITEM_USED.get(this));
        }

        bottleFunctionality(containerItem, stack, level, livEntity);

        if (stack.isEmpty()) {
            return containerItem;
        } else {
            if (livEntity instanceof Player && !((Player) livEntity).getAbilities().instabuild) {
                Player player = (Player) livEntity;
                if (!player.getInventory().add(containerItem)) {
                    player.drop(containerItem, false);
                }
            }

            return stack;
        }
    }

    public int getUseDuration(ItemStack stack) {
        return 32;
    }

    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.DRINK;
    }

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        return ItemUtils.startUsingInstantly(level, player, hand);
    }

    public ItemStack setContainerItem(ItemStack stack) {
        return this.containerItem = stack;
    };

    public abstract void bottleFunctionality(ItemStack containerStack, ItemStack stack, Level level, LivingEntity livEntity);
}
