package com.junethewoods.variants.item.custom.tool;

import com.junethewoods.variants.sound.VSSounds;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.level.Level;

public class LegacySpyglassItem extends Item {
    public LegacySpyglassItem(Properties properties) {
        super(properties);
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 1200;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        player.playSound(VSSounds.SPYGLASS_USE.get(), 1, 1);
        player.awardStat(Stats.ITEM_USED.get(this));
        return ItemUtils.startUsingInstantly(level, player, hand);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livEntity) {
        this.stopUsing(livEntity);
        return stack;
    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity livEntity, int i) {
        this.stopUsing(livEntity);
    }

    private void stopUsing(LivingEntity livEntity) {
        livEntity.playSound(VSSounds.SPYGLASS_STOP_USING.get(), 1, 1);
    }
}
