package com.junethewoods.variants.common.item;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

public class OldStewBucketItem extends Item {
    public OldStewBucketItem(Properties properties) {
        super(properties);
    }

    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livEntity) {
        super.finishUsingItem(stack, level, livEntity);
        if (livEntity instanceof ServerPlayer) {
            ServerPlayer serverPlayer = (ServerPlayer) livEntity;
            CriteriaTriggers.CONSUME_ITEM.trigger(serverPlayer, stack);
            serverPlayer.awardStat(Stats.ITEM_USED.get(this));
        }

        if (stack.isEmpty()) {
            return new ItemStack(Items.BUCKET);
        } else {
            if (livEntity instanceof Player && !((Player) livEntity).getAbilities().instabuild) {
                ItemStack bucketStack = new ItemStack(Items.BUCKET);
                Player player = (Player) livEntity;
                if (!player.getInventory().add(bucketStack)) {
                    player.drop(bucketStack, false);
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
}
